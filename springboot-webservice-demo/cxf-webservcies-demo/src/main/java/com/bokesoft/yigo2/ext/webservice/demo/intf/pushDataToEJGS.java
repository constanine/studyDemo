package com.bokesoft.yigo2.ext.webservice.demo.intf;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
@WebService(targetNamespace = "http://localhost:8080/", name = "pushDataToEJGS")
public interface pushDataToEJGS {
	@WebMethod(operationName = "pushDataToEJGS", action = "")
    public String pushDataToEJGSR(
			@WebParam(name = "encryptStr", targetNamespace = "http://localhost:8080/") java.lang.String encryptStr,
			@WebParam(name = "shiXiangType", targetNamespace = "http://localhost:8080/") java.lang.Short shiXiangType,
			@WebParam(name = "shiXiangId", targetNamespace = "http://localhost:8080/") java.lang.String shiXiangId,
			@WebParam(name = "xmlContent", targetNamespace = "http://localhost:8080/") java.lang.String xmlContent)
			throws Exception;
}
