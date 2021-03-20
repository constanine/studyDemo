import os
import openpyxl

wb = openpyxl.Workbook()
ws = wb.active
ws.append(['文件名','归档路径'])
path = input("请输入要遍历文件名的文件夹: ")
fns = [os.path.join(root,fn) for root, dirs, files in os.walk(path) for fn in files]
for f in fns:
    partnew = f.rpartition("\\")
    #t = '.copyarea'
    if (partnew[2].find('.copyarea') < 0) and (partnew[2].startswith("~") == False) and (partnew[2].endswith(".loading") == False):
        ws.append([partnew[2],partnew[0]])
    #print(partnew[0] + "*" + partnew[2])
wb.save(r"D:\导出的归档文件清单.xlsx")
print("归档文件总数: ")
print(len(fns))
print("文件清单请见导出的excel表格（在D盘根目录下,命名为：导出的归档文件清单.xlsx）")
input('Press Enter to exit...')