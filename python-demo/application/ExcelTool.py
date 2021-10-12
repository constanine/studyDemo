import zipfile as z
import win32com.client as win32
import os
pwd = os.getcwd()

def __getManualCellValueXlsx(filepath,sheetIdx,columnIdxA,columnIdxB,sourceVal):
    try:
        workbook = openpyxl.load_workbook(filepath, read_only=True)
        sheet_names = workbook.sheetnames;
        sheet = workbook[sheet_names[sheetIdx]]
        rowSize = sheet.max_row+1
        for rowIdx in range(1, rowSize):
            cell = sheet.cell(row=rowIdx, column=columnIdxA)
        if (cell.value == sourceVal):
            return sheet.cell(row=rowIdx, column=columnIdxB).value
    finally:
        workbook.close();
        workbook = None;

def __getManualCellValueXls(filepath,sheetIdx,columnIdxA,columnIdxB,sourceVal):
    try:
        workbook = xlrd.open_workbook(filepath)
        sheet = workbook.sheet_by_index(sheetIdx)
        sheet.rich_text_runlist_map
        rowSize = sheet.nrows
        for rowIdx in range(rowSize):
            rowData = sheet.row_values(rowIdx)
            if (rowData[columnIdxA] == sourceVal):
                return rowData[columnIdxB]
    finally:
        workbook.release_resources();
        workbook = None;

def getXlsTexts(filepath):
    s = z.ZipFile(filepath)
    p='drs/shapexml.xml'  # shapes path, *.xls default
    return XlsParser(s.read(p)).text;

def getXlsTexts2(filepath):
    excel_app = win32.DispatchEx('Excel.Application')
    #excel_app.DisplayAlerts = False
    try:
        wb = excel_app.Workbooks.Open(filepath)
#        xlsx_file = filepath + "x"
        xlsx_file = pwd+"/TestTransfA.xlsx"
        wb.SaveAs(xlsx_file, FileFormat=51)
        #wb.Close()
    finally:
        excel_app.Quit()
    return getXlsxTexts(xlsx_file)

def getXlsxTexts(filepath):
    s = z.ZipFile(filepath)
    p='xl/drawings/drawing1.xml'  # shapes path, *.xlsx default
    return XlsxParser(s.read(p)).text;

def decodeString4Chinese(str):
    idx = 0;
    result = '';
    blist = []
    for bstr in str.split('\\x'):
        if idx > 0:
            if len(bstr) > 2:
                blist.append(int(bstr[0:2], 16));
                result += bytes(blist).decode();
                blist = [];
                result += bstr[2:];
            else:
                blist.append(int(bstr, 16));
        else:
            result += bstr;
        idx += 1;
    result += bytes(blist).decode();
    return result;


class XlsxParser(object):
    def __init__(self, value):
        self.value = str(value)

    def __repr__(self):
        return repr(self.value)

    def __getitem__(self, i):
        return self.value[i]

    def tag_content(self, tag):
        return [XlsxParser(i) for i in self.value.split(tag)[1::2]]

    @property
    def text(self):
        t = self.tag_content('xdr:txBody')  # list of XML codes, each containing a seperate textboxes, messy (with extra xml that is)
        l = [i.tag_content('a:p>') for i in t]  # split into sublists by line breaks (inside the textbox), messy
        w = [[[h[1:-2] for h in i.tag_content('a:t')] if i else ['\n'] for i in j] for j in l]  # clean into sublists by cell-by-cell basis (and mind empty lines)
        l = [[''.join(i) for i in j] for j in w]  #  join lines overlapping multiple cells into one sublist
        return [ decodeString4Chinese(i) for j in l for i in j]  #  join sublists of lines into strings seperated by newline char

class XlsParser(object):
    def __init__(self, value):
        self.value = str(value)

    def __repr__(self):
        return repr(self.value)

    def __getitem__(self, i):
        return self.value[i]

    def txbody_content(self, tag):
        return [XlsParser(i) for i in self.value.split(tag)[1::2]]

    def p_content(self, tag):
        return [XlsParser(i) for i in self.value.split(tag)[1:]]

    def tag_content(self, tag):
        return [XlsParser(i) for i in self.value.split(tag)[1::2]]

    @property
    def text(self):
        t = self.txbody_content('xdr:txBody')  # list of XML codes, each containing a seperate textboxes, messy (with extra xml that is)
        l = [i.p_content('a:p>') for i in t]# split into sublists by line breaks (inside the textbox), messy
        w = [[[h[0:-2] for h in i.tag_content('a:t>')] if i else ['\n'] for i in j] for j in l]  # clean into sublists by cell-by-cell basis (and mind empty lines)
        l = [[''.join(i) for i in j] for j in w]  #  join lines overlapping multiple cells into one sublist
        return [ decodeString4Chinese(i) for j in l for i in j]  #  join sublists of lines into strings seperated by newline char