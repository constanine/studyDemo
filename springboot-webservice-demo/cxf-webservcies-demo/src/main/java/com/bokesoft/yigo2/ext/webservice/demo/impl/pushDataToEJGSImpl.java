package com.bokesoft.yigo2.ext.webservice.demo.impl;

import java.util.logging.Logger;

import com.bokedee.DEEServlet;
import com.bokesoft.yigo2.ext.webservice.demo.intf.pushDataToEJGS;

@javax.jws.WebService(
        serviceName = "pushDataToEJGS",
        portName = "pushDataToEJGS",
        targetNamespace = "http://localhost:8080/",
        endpointInterface = "com.bokesoft.yigo2.ext.webservice.demo.intf.pushDataToEJGS")
public class pushDataToEJGSImpl implements pushDataToEJGS {
	
	private static final Logger LOG = Logger.getLogger(pushDataToEJGSImpl.class.getName());


    public String pushDataToEJGSR(String encryptStr, Short shiXiangType, String shiXiangId, String xmlContent) { 
        LOG.info("Executing operation pushDataToEJGSR");
        try {
        	
        	
    		System.out.println("encryptStr:"+encryptStr);
    		System.out.println("shiXiangType:"+shiXiangType);
    		System.out.println("shiXiangId:"+shiXiangId);
    		String result = "";
    		String DEEIfNname = "";
    		if(shiXiangType==610) {
    			DEEIfNname = "AssessmentResults";
    		}else if(shiXiangType==620) {
    			DEEIfNname = "ResultNotice";
    		}else {
    			result = "003,�������";
    		}
        	//ȥ����dee�ӿ�
    		if(DEEIfNname.length() > 0) {
            	DEEServlet deeservlet = new DEEServlet();
            	String args[] = {DEEIfNname,xmlContent};
            	result = deeservlet.main(args);
    		}

        	//������Ϣ
//        	result =  "";
    		return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}
