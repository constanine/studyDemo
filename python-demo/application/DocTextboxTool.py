from docx import  Document

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