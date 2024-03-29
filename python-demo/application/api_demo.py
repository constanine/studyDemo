import api_wlx;
import ExcelTextboxTool;
import DocTextboxTool;

def test_getRightCellValue():
   print("xls,"+api_wlx.getRightCellValue("resources/api-demo/test-A.xls",0,0,"MD5"));
   print("xlsx,"+api_wlx.getRightCellValue("resources/api-demo/test-A.xlsx", 0, 1, "MD5"));
   print("doc,"+api_wlx.getRightCellValue("D:/work/GIT_WORK/studyDemo/python-demo/application/resources/api-demo/test-A.doc", 0, 0, "葡萄"));
   print("docx,"+api_wlx.getRightCellValue("resources/api-demo/test-A.docx", 0, 0, "MD5"));

def test_getManualCellValue():
   print("xls,"+api_wlx.getManualCellValue("resources/api-demo/test-A.xls", 1 , 0, 2, "MD5"));
   print("xlsx,"+api_wlx.getManualCellValue("resources/api-demo/test-A.xlsx", 1, 1, 3, "MD5"));
   print("doc,"+api_wlx.getManualCellValue("D:/work/GIT_WORK/studyDemo/python-demo/application/resources/api-demo/test-A.doc", 1, 0, 2, "葡萄"));
   print("docx,"+api_wlx.getManualCellValue("resources/api-demo/test-A.docx", 1, 0, 2, "MD5"));



#test_getRightCellValue();
#test_getManualCellValue();
#result = ExcelTextboxTool.getXlsxTexts("resources/api-demo/test-A.xlsx");
#print(result);
#result = ExcelTextboxTool.getXlsTexts("resources/api-demo/TEST-demo1.xls");
#result = ExcelTextboxTool.getXlsTexts("resources/api-demo/TEST-demo3.xls");
#result = ExcelTextboxTool.getXlsTexts2("D:/work/GIT_WORK/studyDemo/python-demo/application/resources/api-demo/TEST-A1.xls");
#print(result);
#result = DocTextboxTool.getDocxTexts("resources/api-demo/test-A.docx");
#result = DocTextboxTool.getDocTexts("D:/work/GIT_WORK/studyDemo/python-demo/application/resources/api-demo/test-A.doc");
#print(result);
#test-A.doc
result = DocTextboxTool.getPageHeader4Doc("D:/work/GIT_WORK/studyDemo/python-demo/application/resources/api-demo/test-A.doc");
print(result);
