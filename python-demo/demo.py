import xlrd; #xls
import openpyxl; #xlsx
import os; 
import docx; #docx



def startXls():
    data = xlrd.open_workbook("D:/work/GIT_WORK/test-A.xls")
    sheet = data.sheet_by_index(0)
    rowSize = sheet.nrows
    dataB = xlrd.open_workbook("D:/work/GIT_WORK/test-B.xls")
    sheetB = dataB.sheet_by_index(0)
    rowSizeB = sheetB.nrows
    for rowIdx in range(rowSize):
        rowData = sheet.row_values(rowIdx)
        rowDataB = sheetB.row_values(rowIdx)
        print(rowDataB[1]+","+rowData[0])
        if (rowDataB[1] == rowData[0]):
            print("相匹配")
        else:
            print("不匹配")

def startDocx():
    doc = docx.Document("D:/work/GIT_WORK/test-A.docx");
    paragraphs = doc.paragraphs;
    for paragraph in paragraphs:
        print(paragraph.text)
    tables = doc.tables;
    #for table in tables:
    #    for row in table.rows:  # 读每行
    #        #row_content = []
    #       for cell in row.cells:  # 读一行中的所有单元格
    #            c = cell.text
    #            print(c)
    #            row_content.append(c)
    #            print(row_content)  # 以列表形式导出每一行数据
    testTxt = tables[0].rows[0].cells[0].text
    print(testTxt)
    if(testTxt.startswith("表1")) :
        print("匹配")
    else :
        print("不匹配")
    if("表2" in testTxt) :
        print("匹配")
    else :
        print("不匹配")
#startXls();
startDocx();