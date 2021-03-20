import zipfile as z


def getXlsTexts(this):
    s = z.ZipFile(this)
    p='drs/shapexml.xml'  # shapes path, *.xls default
    return XlsParser(s.read(p)).text;


def getXlsxTexts(this):
    s = z.ZipFile(this)
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
        l = [i.p_content('a:p xmlns:a="http://schemas.openxmlformats.org/drawingml/2006/main"') for i in t]  # split into sublists by line breaks (inside the textbox), messy
        w = [[[h[0:-2] for h in i.tag_content('a:t>')] if i else ['\n'] for i in j] for j in l]  # clean into sublists by cell-by-cell basis (and mind empty lines)
        l = [[''.join(i) for i in j] for j in w]  #  join lines overlapping multiple cells into one sublist
        return [ decodeString4Chinese(i) for j in l for i in j]  #  join sublists of lines into strings seperated by newline char