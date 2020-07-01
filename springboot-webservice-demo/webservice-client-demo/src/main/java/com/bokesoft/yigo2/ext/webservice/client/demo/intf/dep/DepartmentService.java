/**
 * DepartmentService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bokesoft.yigo2.ext.webservice.client.demo.intf.dep;

public interface DepartmentService extends java.rmi.Remote {
    public com.bokesoft.yigo2.ext.webservice.client.demo.intf.dep.Department getDepartment(java.lang.String arg0) throws java.rmi.RemoteException;
    public com.bokesoft.yigo2.ext.webservice.client.demo.intf.dep.Department[] getAllDepartment() throws java.rmi.RemoteException;
}
