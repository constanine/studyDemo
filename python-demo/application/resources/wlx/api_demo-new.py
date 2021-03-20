import api_wlx;
import rardemo;

#读取各文件及路径
reportpath_dev = api_wlx._checkfileexist("应用数据过程报告的本地路径是（到最小目录，例以subsystem V&V\A520028_WHL16结尾）:")
deliverysheet = api_wlx._checkfileexist("请输入子系统发布单下载到本地的完整路径名称（文档格式须为.xls）：")
# VVreport = api_wlx._checkfileexist("请输入子系统发布单中VV报告列表下载到本地的完整路径名称：")
platform_deliverysheet = api_wlx._checkfileexist("请输入子系统平台发布单下载到本地的完整路径名称(平台发布单删除封面页和修订记录页)：")
requestsheet_app = api_wlx._checkfileexist("请输入APP_Conf_Request sheet的完整路径名称：")
requestsheet_univic = api_wlx._checkfileexist("请输入UNIVIC_Conf_Request sheet的完整路径名称：")
# requestsheet_beacon = api_wlx._checkfileexist("请输入Beacon_Layout_Request_sheet的完整路径名称（若无，请直接回车）：")
# requestsheet_leu = api_wlx._checkfileexist("请输入LEU_Request_sheet的完整路径名称（若无，请直接回车）：")
#des_baseline_deliverysheet = api_wlx._checkfileexist("请输入基于的系统设计基线发布报告的完整路径名称：")

#获得设计基线和平台基线版本
# temp1 = des_baseline_deliverysheet.partition("(")
# temp2 = temp1[2].partition(")")
# des_version = temp2[0]#设计基线版本
# sysplatform_version = api_wlx.getManualCellValue(des_baseline_deliverysheet,0,3,6,des_version)#大平台基线版本

print('检查结果如下：')

#是否具有UNIVIC配置？和发布单是否一致？
deliverysheet_univic = reportpath_dev + "_CASCO_5118_UNIVIC Conf Delivery sheet.xls"

temp1 = api_wlx.getManualCellValue(deliverysheet_univic,3,0,1,'UNIVIC Conf version delivered')
temp2 = api_wlx.getManualCellValuestart(deliverysheet,0,0,0,'UNIVIC Conf')
temp3 = temp2.rsplit(" ",1)
temp2 = temp3[1]
if temp1.upper() != temp2.upper():
    print("UNIVIC_Conf_delivery_sheet中的UNIVIC Conf version delivered与ATC数据发布单中的UNIVIC Conf版本不一致")

temp1 = api_wlx.getManualCellValue(deliverysheet_univic,3,0,1,'Checksum for UNIVIC Conf package')
temp2 = api_wlx.getManualCellValuestart(deliverysheet,0,0,1,'UNIVIC Conf')
if temp1.upper() != temp2.upper():
    print("UNIVIC_Conf_delivery_sheet中的Checksum for UNIVIC Conf package与ATC数据发布单中的UNIVIC Conf MD5码不一致")

temp1 = api_wlx.getManualCellValue(deliverysheet_univic,3,0,1,'Platform ATC Baseline applicable')
temp2 = api_wlx.getManualCellValue(deliverysheet,0,0,1,'Subsystem Platform Baseline')
if temp1.upper() != temp2.upper():
    print("UNIVIC_Conf_delivery_sheet中的Platform ATC Baseline applicable与ATC数据发布单中的Subsystem Platform Baseline版本不一致")

temp1 = api_wlx.getManualCellValue(deliverysheet_univic,3,0,1,'Platform STR UNIVIC (if necessary)')
temp2 = api_wlx.getManualCellValue(deliverysheet,0,0,1,'UNIVIC STR')
if temp1.upper() != temp2.upper():
    print("UNIVIC_Conf_delivery_sheet中的Platform STR UNIVIC (if necessary)与ATC数据发布单中的UNIVIC STR版本不一致")

temp1 = api_wlx.getManualCellValue(deliverysheet_univic,3,0,1,'SyDB')
temp2 = api_wlx.getManualCellValue(deliverysheet,0,0,1,'SYDB')
temp3 = temp2.rsplit("_",1)
temp2 = temp3[1]
if temp1.upper() != temp2.upper():
    print("UNIVIC_Conf_delivery_sheet中的SyDB与ATC数据发布单中的SYDB版本不一致")

temp1 = api_wlx.getManualCellValue(deliverysheet_univic,3,0,1,'Rolling Stock Characteristics')
temp2 = api_wlx.getManualCellValue(requestsheet_univic,2,0,1,'Rolling Stock Characteristics')
if temp1.upper() != temp2.upper():
    print("UNIVIC_Conf_delivery_sheet中的Rolling Stock Characteristics与UNIVIC Conf Request sheet中的Rolling Stock Characteristics文档名和/或版本不一致")

temp1 = api_wlx.getManualCellValue(deliverysheet_univic,3,0,1,'SIG-PSD SyID')
temp2 = api_wlx.getManualCellValue(requestsheet_univic,2,0,1,'SIG-PSD SyID')
if temp1.upper() != temp2.upper():
    print("UNIVIC_Conf_delivery_sheet中的SIG-PSD SyID与UNIVIC Conf Request sheet的SIG-PSD SyID文档名和/或版本不一致")

temp1 = api_wlx.getManualCellValue(deliverysheet_univic,3,0,1,'SIG-RS SyID')
temp2 = api_wlx.getManualCellValue(requestsheet_univic,2,0,1,'SIG-RS SyID')
if temp1.upper() != temp2.upper():
    print("UNIVIC_Conf_delivery_sheet中的SIG-RS SyID与UNIVIC Conf Request sheet的SIG-RS SyID文档名和/或版本不一致")

temp1 = api_wlx.getManualCellValue(deliverysheet_univic,3,0,1,'RTI')
temp2 = api_wlx.getManualCellValue(requestsheet_univic,2,0,1,'RTI')
if temp1.upper() != temp2.upper():
    print("UNIVIC_Conf_delivery_sheet中的RTI与UNIVIC Conf Request sheet的RTI文档名和/或版本不一致")

temp1 = api_wlx.getManualCellValue(deliverysheet_univic,3,0,1,'Addressing Plan')
temp2 = api_wlx.getManualCellValue(requestsheet_univic,2,0,1,'Addressing Plan')
if temp1.upper() != temp2.upper():
    print("UNIVIC_Conf_delivery_sheet中的Addressing Plan与UNIVIC Conf Request sheet的Addressing Plan文档名和/或版本不一致")

temp1 = api_wlx.getManualCellValue(deliverysheet_univic,3,0,1,'CC-TIMS SyID')
temp2 = api_wlx.getManualCellValue(requestsheet_univic,2,0,1,'CC-TIMS SyID')
if temp1.upper() != temp2.upper():
    print("UNIVIC_Conf_delivery_sheet中的CC-TIMS SyID与UNIVIC Conf Request sheet的CC-TIMS SyID文档名和/或版本不一致")

temp1 = api_wlx.getManualCellValue(deliverysheet_univic,3,0,1,'CC-ATS Project')
temp2 = api_wlx.getManualCellValue(requestsheet_univic,2,0,1,'CC-ATS Project')
if temp1 or temp2 == 0:
    print("CC-ATS Project填写项已删除，和模板定义不同")
elif temp1.upper() != temp2.upper():
    print("UNIVIC_Conf_delivery_sheet中的CC-ATS Project与UNIVIC Conf Request sheet的CC-ATS Project文档名和/或版本不一致")

temp1 = api_wlx.getManualCellValue(deliverysheet_univic,3,0,1,'SGD(.xml)')
temp2 = api_wlx.getManualCellValue(requestsheet_univic,2,0,1,'SGD(.xml)')
temp3 = temp2.rsplit("_",1)
temp2 = temp3[1]
temp3 = temp2.rsplit(".",1)
temp2 = temp3[0]
if temp1.upper() != temp2.upper():
    print("UNIVIC_Conf_delivery_sheet中的SGD(.xml)与UNIVIC Conf Request sheet的SGD(.xml)版本不一致")

temp1 = api_wlx.getManualCellValuestart(deliverysheet_univic,3,0,1,'Statistic Regulation Database (.xml)')
if temp1 == None:
    temp1 = "NA"
temp2 = api_wlx.getManualCellValue(deliverysheet,0,0,1,'SRD (.xml)')
if temp1.upper() != temp2.upper():
    print("UNIVIC_Conf_delivery_sheet中的Config与ATC数据发布单中UNIVIC Conf下的Configuration版本不一致")


temp1 = api_wlx.getManualCellValuestart(deliverysheet_univic,3,0,1,'Config(tgt')
temp2 = api_wlx.getManualCellValue(deliverysheet,0,0,1,'Configuration')
if temp1.upper() != temp2.upper():
    print("UNIVIC_Conf_delivery_sheet中的Config与ATC数据发布单中UNIVIC Conf下的Configuration版本不一致")

temp1 = api_wlx.getManualCellValue(deliverysheet_univic,3,0,1,'Vital Setting (.xml)')
temp2 = api_wlx.getManualCellValue(deliverysheet,0,0,1,'VS (.xml)')
if temp1.upper() != temp2.upper():
    print("UNIVIC_Conf_delivery_sheet中的Vital Setting (.xml)与ATC数据发布单中App Conf下的VS (.xml)版本不一致")

temp1 = api_wlx.getManualCellValue(deliverysheet_univic, 3, 0, 1, 'NonVital Setting version (.xml)')
temp2 = api_wlx.getManualCellValue(deliverysheet, 0, 0, 1, 'NVS (.xml)')
if temp1.upper() != temp2.upper():
    print("UNIVIC_Conf_delivery_sheet中的NonVital Setting version (.xml)与ATC数据发布单中App Conf下的NVS (.xml)版本不一致")

temp1 = api_wlx.getManualCellValue(deliverysheet_univic, 3, 0, 1, 'Conf UNIVIC version (.xml)')
temp2 = api_wlx.getManualCellValue(deliverysheet, 0, 0, 1, 'Configuration')
if temp1.upper() != temp2.upper():
    print("UNIVIC_Conf_delivery_sheet中的Conf UNIVIC version (.xml)与ATC数据发布单中UNIVIC Conf下的Configuration版本不一致")

temp1 = api_wlx.getManualCellValue(deliverysheet_univic, 3, 0, 1, 'Dataplugs')
temp2 = api_wlx.getManualCellValue(deliverysheet, 0, 0, 1, 'Dataplug')
if temp1.upper() != temp2.upper():
    print("UNIVIC_Conf_delivery_sheet中的Dataplugs与ATC数据发布单中UNIVIC Conf下的Dataplug版本不一致")

temp1 = api_wlx.getManualCellValue(deliverysheet_univic, 3, 0, 1, 'DMI')
temp2 = api_wlx.getManualCellValue(deliverysheet, 0, 0, 1, 'DMI')
if temp1 == None:
    temp1 = "NA"
if temp1.upper() != temp2.upper():
    print("UNIVIC_Conf_delivery_sheet中的DMI与ATC数据发布单中UNIVIC Conf下的DMI版本不一致")

temp1 = api_wlx.getManualCellValue(deliverysheet_univic, 3, 0, 1, 'E-train')
temp2 = api_wlx.getManualCellValue(deliverysheet, 0, 0, 1, 'E-train (.etr)')
if temp1.upper() != temp2.upper():
    print("UNIVIC_Conf_delivery_sheet中的E-train与ATC数据发布单中UNIVIC Conf下的E-train (.etr)版本不一致")

temp1 = api_wlx.getManualCellValuestart(deliverysheet_univic, 3, 0, 1, 'Host')
temp2 = api_wlx.getManualCellValue(deliverysheet, 0, 0, 1, 'Host')
if temp1 == None:
    temp1 = "NA"
if temp1.upper() != temp2.upper():
    print("UNIVIC_Conf_delivery_sheet中的OVLI base与ATC数据发布单中UNIVIC Conf下的Host版本不一致")

temp1 = api_wlx.getManualCellValue(deliverysheet_univic, 3, 0, 1, 'OVLI base')
temp2 = api_wlx.getManualCellValue(deliverysheet, 0, 0, 1, 'OVLI base (.xml)')
if temp1.upper() != temp2.upper():
    print("UNIVIC_Conf_delivery_sheet中的OVLI base与ATC数据发布单中UNIVIC Conf下的OVLI base (.xml)版本不一致")

temp1 = api_wlx.getManualCellValue(deliverysheet_univic, 3, 0, 1, 'Omap base')
temp2 = api_wlx.getManualCellValue(deliverysheet, 0, 0, 1, 'OMAP base (.xml)')
if temp1.upper() != temp2.upper():
    print("UNIVIC_Conf_delivery_sheet中的Omap base与ATC数据发布单中UNIVIC Conf下的OMAP base (.xml)版本不一致")

#检查univic delivery sheet中版本和具体数据包中的版本是否一致
UNIVIC_Conf_rarname = input("请输入UNIVIC_Conf的数据压缩包的文件完整路径：")
aa = rardemo.rarfirstForlderList(UNIVIC_Conf_rarname)
Conf_UNIVIC_version = Dataplug_version = Etrain_version = Omap_bases_version = OVLI_base_version = Tgt_dmi_version = Host_version = None
for i in range(len(aa)):
        temp = aa[i-1].split("/")
        temp2 = temp[1]
        if temp2.startswith("Config"):
            temp = temp2.rsplit("_", 1)
            Conf_UNIVIC_version = temp[1]

        if temp2.startswith("Dataplug"):
            temp = temp2.rsplit("_", 1)
            Dataplug_version = temp[1]

        if temp2.startswith("E-train"):
            temp = temp2.rsplit("_", 1)
            Etrain_version = temp[1]

        if temp2.startswith("Omap"):
            temp = temp2.rsplit("_", 1)
            Omap_bases_version = temp[1]

        if temp2.startswith("OVLI"):
            temp = temp2.rsplit("_", 1)
            OVLI_base_version = temp[1]

        if temp2.startswith("Tgt"):
            temp = temp2.rsplit("_", 1)
            Tgt_dmi_version = temp[1]

        if temp2.startswith("Host"):
            temp = temp2.rsplit("_", 1)
            Host_version = temp[1]

temp = api_wlx.getManualCellValue(deliverysheet_univic, 3, 0, 1, 'Conf UNIVIC version (.xml)')
if Conf_UNIVIC_version.upper() != temp.upper():
    print("UNIVIC_Conf_delivery_sheet与UNIVIC conf.rar中的Conf UNIVIC版本不一致")

temp = api_wlx.getManualCellValue(deliverysheet_univic, 3, 0, 1, 'Dataplugs')
if Dataplug_version.upper() != temp.upper():
    print("UNIVIC_Conf_delivery_sheet与UNIVIC conf.rar中的Dataplugs版本不一致")

temp = api_wlx.getManualCellValue(deliverysheet_univic, 3, 0, 1, 'DMI')
if Tgt_dmi_version.upper() != temp.upper():
    print("UNIVIC_Conf_delivery_sheet与UNIVIC conf.rar中的DMI版本不一致")

temp = api_wlx.getManualCellValue(deliverysheet_univic, 3, 0, 1, 'E-train')
if Etrain_version.upper() != temp.upper():
    print("UNIVIC_Conf_delivery_sheet与UNIVIC conf.rar中的E-train版本不一致")

temp = api_wlx.getManualCellValue(deliverysheet_univic, 3, 0, 1, 'OVLI base')
if OVLI_base_version.upper() != temp.upper():
    print("UNIVIC_Conf_delivery_sheet与UNIVIC conf.rar中的OVLI base版本不一致")

temp = api_wlx.getManualCellValue(deliverysheet_univic, 3, 0, 1, 'Omap base')
if Omap_bases_version.upper() != temp.upper():
    print("UNIVIC_Conf_delivery_sheet与UNIVIC conf.rar中的Omap base版本不一致")

temp = api_wlx.getManualCellValuestart(deliverysheet_univic, 3, 0, 1, 'Host')
if Host_version.upper() != temp.upper():
    print("UNIVIC_Conf_delivery_sheet与UNIVIC conf.rar中的Host版本不一致")

#检查数据发布单中软件是否与产品发布单中版本一致？
temp1 = api_wlx.getManualCellValue(deliverysheet, 0, 0, 1, 'Subsystem Platform Baseline')
temp2 = api_wlx.getManualCellValue(platform_deliverysheet, 0, 2, 3, 'URBALIS ATC SUBSYSTEM')
if temp1.upper() != temp2.upper():
    print("ATC数据发布单中的Subsystem Platform Baseline与ATC子系统产品发布单中的URBALIS ATC SUBSYSTEM版本不一致")

temp1 = api_wlx.getManualCellValue(deliverysheet, 0, 0, 1, 'Platform SW ATC')
temp2 = api_wlx.getManualCellValue(platform_deliverysheet, 3, 1, 3, 'ATC_SW')
if temp1.upper() != temp2.upper():
    print("ATC数据发布单中的Platform SW ATC与ATC子系统产品发布单中的ATC_SW版本不一致")

temp1 = api_wlx.getManualCellValue(deliverysheet, 0, 0, 1, 'CC_V_ATP')
temp2 = api_wlx.getManualCellValue(platform_deliverysheet, 3, 1, 3, 'CC_VITAL_SW')
if temp1.upper() != temp2.upper():
    print("ATC数据发布单中的Platform SW ATC与ATC子系统产品发布单中的ATC_SW版本不一致")

temp1 = api_wlx.getManualCellValue(deliverysheet, 0, 0, 1, 'TK_LC')
temp2 = api_wlx.getManualCellValue(platform_deliverysheet, 3, 1, 3, 'WS_LC_SW')
if temp1.upper() != temp2.upper():
    print("ATC数据发布单中的TK_LC与ATC子系统产品发布单中的WS_LC_SW版本不一致")

temp1 = api_wlx.getManualCellValue(deliverysheet, 0, 0, 1, 'TK_ZC')
temp2 = api_wlx.getManualCellValue(platform_deliverysheet, 3, 1, 3, 'WS_ZC_SW')
if temp1.upper() != temp2.upper():
    print("ATC数据发布单中的TK_ZC与ATC子系统产品发布单中的WS_ZC_SW版本不一致")

temp1 = api_wlx.getManualCellValue(deliverysheet, 0, 0, 1, 'UNIVIC STR')
temp2 = api_wlx.getManualCellValue(platform_deliverysheet, 3, 2, 3, 'UNIVIC-STR')
if temp1.upper() != temp2.upper():
    print("ATC数据发布单中的UNIVIC STR与ATC子系统产品发布单中的UNIVIC-STR版本不一致")

temp1 = api_wlx.getManualCellValue(deliverysheet, 0, 0, 1, 'OVLI')
temp2 = api_wlx.getManualCellValue(platform_deliverysheet, 3, 1, 3, 'CC_OVLI')
if temp1.upper() != temp2.upper():
    print("ATC数据发布单中的OVLI与ATC子系统产品发布单中的CC_OVLI版本不一致")

temp1 = api_wlx.getManualCellValue(deliverysheet, 0, 0, 1, 'User Switch')
temp2 = api_wlx.getManualCellValue(platform_deliverysheet, 3, 1, 3, 'USER_SWITCH')
if temp1.upper() != temp2.upper():
    print("ATC数据发布单中的User Switch与ATC子系统产品发布单中的USER_SWITCH版本不一致")

temp1 = api_wlx.getManualCellValue(deliverysheet, 0, 0, 1, 'DMI')
temp2 = api_wlx.getManualCellValue(platform_deliverysheet, 3, 1, 3, 'DMI_UEVOL_SW_PACKAGE')
if temp1.upper() != temp2.upper():
    print("ATC数据发布单中的DMI与ATC子系统产品发布单中的DMI_UEVOL_SW_PACKAGE版本不一致")

temp1 = api_wlx.getManualCellValue(deliverysheet, 0, 0, 1, 'OMAP')
temp2 = api_wlx.getManualCellValue(platform_deliverysheet, 3, 1, 3, 'UEVOL_OMAP')
if temp1.upper() != temp2.upper():
    print("ATC数据发布单中的OMAP与ATC子系统产品发布单中的UEVOL_OMAP版本不一致")