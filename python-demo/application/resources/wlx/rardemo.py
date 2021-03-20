import rarfile

def rarfirstForlderList(filename):
    rar=rarfile.RarFile(filename, mode='r') # mode的值只能为'r'
    # 判断同名文件夹是否存在，若不存在则创建同名文件夹
    firstForlderList = [];
    rf_list = rar.namelist();
    for sonfile in rf_list:
       sonfileInfo = rar.getinfo(sonfile);
       if sonfileInfo.file_flags == 5 and sonfile.count('/') == 2 :
           firstForlderList.append(sonfile);
    return firstForlderList


