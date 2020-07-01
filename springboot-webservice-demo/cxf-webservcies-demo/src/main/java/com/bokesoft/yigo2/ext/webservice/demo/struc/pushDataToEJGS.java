package com.bokesoft.yigo2.ext.webservice.demo.struc;

import java.io.Serializable;
import java.util.Date;

public class pushDataToEJGS implements Serializable {
	private String encryptStr;
	private String shiXiangType;
	private String shiXiangId;
	private Date xmlContent;
	public String getencryptStr() {
		return encryptStr;
	}
	public void setUserId(String encryptStr) {
		this.encryptStr = encryptStr;
	}
	public String getshiXiangType() {
		return shiXiangType;
	}
	public void setshiXiangType(String shiXiangType) {
		this.shiXiangType = shiXiangType;
	}
	public String getshiXiangId() {
		return shiXiangId;
	}
	public void setshiXiangId(String shiXiangId) {
		this.shiXiangId = shiXiangId;
	}
	public Date getxmlContent() {
		return xmlContent;
	}
	public void setxmlContent(Date xmlContent) {
		this.xmlContent = xmlContent;
	}

}
