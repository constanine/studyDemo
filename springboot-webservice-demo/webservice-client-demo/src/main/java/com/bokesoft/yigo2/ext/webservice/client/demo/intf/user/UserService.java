/**
 * UserService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bokesoft.yigo2.ext.webservice.client.demo.intf.user;

public interface UserService extends java.rmi.Remote {
    public java.lang.String getName(java.lang.String userId) throws java.rmi.RemoteException;
    public com.bokesoft.yigo2.ext.webservice.client.demo.intf.user.User[] getAllUser() throws java.rmi.RemoteException;
    public void dealUserRequest(com.bokesoft.yigo2.ext.webservice.client.demo.intf.user.User arg0) throws java.rmi.RemoteException;
    public com.bokesoft.yigo2.ext.webservice.client.demo.intf.user.User getUser(java.lang.String arg0) throws java.rmi.RemoteException;
}
