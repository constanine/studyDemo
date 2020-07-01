package com.bokesoft.yigo2.ext.webservice.client.demo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import com.alibaba.fastjson.JSON;
import com.bokesoft.yigo2.ext.webservice.client.demo.intf.dep.Department;
import com.bokesoft.yigo2.ext.webservice.client.demo.intf.user.User;
import com.bokesoft.yigo2.ext.webservice.client.demo.servercfg.dep.DepartmentServiceImplServiceLocator;
import com.bokesoft.yigo2.ext.webservice.client.demo.servercfg.dep.DepartmentServiceImplServiceSoapBindingStub;
import com.bokesoft.yigo2.ext.webservice.client.demo.servercfg.user.UserServiceImplServiceLocator;
import com.bokesoft.yigo2.ext.webservice.client.demo.servercfg.user.UserServiceImplServiceSoapBindingStub;

public class ClientDemo {
	public static void main(String[] args) throws IOException, ServiceException {
		getUser("411001");
		getUser("411002");
		getAllUser();
		getAllDepartment();
		getDepartment("1001");
    }
	
	public static void getUser(String userId) throws MalformedURLException, RemoteException, ServiceException {
		URL url = new URL("http://localhost:8080/webservice/user?wsdl");
		UserServiceImplServiceLocator service = new UserServiceImplServiceLocator();
		UserServiceImplServiceSoapBindingStub stub = (UserServiceImplServiceSoapBindingStub) service.getUserServiceImplPort(url);
		User user = stub.getUser(userId);
		System.out.println(JSON.toJSONString(user));
	}
	
	public static void getAllUser() throws MalformedURLException, RemoteException, ServiceException {
		URL url = new URL("http://localhost:8080/webservice/user?wsdl");
		UserServiceImplServiceLocator service = new UserServiceImplServiceLocator();
		UserServiceImplServiceSoapBindingStub stub = (UserServiceImplServiceSoapBindingStub) service.getUserServiceImplPort(url);
		User[] users = stub.getAllUser();
		System.out.println(JSON.toJSONString(users));
	}
	
	public static void getDepartment(String depId) throws MalformedURLException, RemoteException, ServiceException {
		URL url = new URL("http://localhost:8080/webservice/dep?wsdl");
		DepartmentServiceImplServiceLocator service = new DepartmentServiceImplServiceLocator();
		DepartmentServiceImplServiceSoapBindingStub stub = (DepartmentServiceImplServiceSoapBindingStub) service.getDepartmentServiceImplPort();
		Department user = stub.getDepartment(depId);
		System.out.println(JSON.toJSONString(user));
	}
	
	public static void getAllDepartment() throws MalformedURLException, RemoteException, ServiceException {
		URL url = new URL("http://localhost:8080/webservice/dep?wsdl");
		DepartmentServiceImplServiceLocator service = new DepartmentServiceImplServiceLocator();
		DepartmentServiceImplServiceSoapBindingStub stub = (DepartmentServiceImplServiceSoapBindingStub) service.getDepartmentServiceImplPort();
		Department[] users = stub.getAllDepartment();
		System.out.println(JSON.toJSONString(users));
	}
}
