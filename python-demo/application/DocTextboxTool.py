from docx import  Document
from win32com import client #doc

def getDocxTexts(filename):
    result = [];
    document = Document(filename);
    for child in document.element.body.iter():
        if child.tag.endswith('textbox'):
            textContent = '';
            for c in child.iter():
                c_tag = c.tag;
                if c_tag.endswith('main}r'):
                    textContent += c.text;
            result.append(textContent);
    return result;

def getDocTexts(filename):
    result = [];
    word = client.Dispatch('Word.Application')
    doc = word.Documents.Open(FileName=filename, Encoding='gbk')
    shapes = doc.Shapes;
    for shape in shapes:
        result.append(shape.TextFrame.TextRange.Text)
    return result;