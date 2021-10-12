import rarfile
import os
import hashlib


#filename = "resources/test-rar-demo.rar"
filename = "resources/LEU.rar"
rar=rarfile.RarFile(filename, mode='r') # mode的值只能为'r'



for fileInfo in rar.infolist():
   if fileInfo.is_file() :
       if 'bookB' in fileInfo.filename :
           if 'A' in fileInfo.filename:
               data = rar.read(fileInfo);
               md5hash = hashlib.md5(data);
               md5 = md5hash.hexdigest();
               print("rarpath:"+fileInfo.filename+",md5:"+md5);




# 判断同名文件夹是否存在，若不存在则创建同名文件夹
###################### 不要看 ##################
#firstForlderList = [];
#rf_list = rar.namelist();
#for sonfile in rf_list:
#   sonfileInfo = rar.getinfo(sonfile);
#   if sonfileInfo.file_flags == 5 and sonfile.count('/') == 2 :
#       firstForlderList.append(sonfile);
#print('一级目录',firstForlderList);