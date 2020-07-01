package com.bokesoft.yigo2.ext.webservice.client.demo.intf.user;

public class UserServiceProxy implements com.bokesoft.yigo2.ext.webservice.client.demo.intf.user.UserService {
  private String _endpoint = null;
  private com.bokesoft.yigo2.ext.webservice.client.demo.intf.user.UserService userService = null;
  
  public UserServiceProxy() {
    _initUserServiceProxy();
  }
  
  public UserServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initUserServiceProxy();
  }
  
  private void _initUserServiceProxy() {
    try {
      userService = (new com.bokesoft.yigo2.ext.webservice.client.demo.servercfg.user.UserServiceImplServiceLocator()).getUserServiceImplPort();
      if (userService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)userService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)userService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (userService != null)
      ((javax.xml.rpc.Stub)userService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.bokesoft.yigo2.ext.webservice.client.demo.intf.user.UserService getUserService() {
    if (userService == null)
      _initUserServiceProxy();
    return userService;
  }
  
  public java.lang.String getName(java.lang.String userId) throws java.rmi.RemoteException{
    if (userService == null)
      _initUserServiceProxy();
    return userService.getName(userId);
  }
  
  public com.bokesoft.yigo2.ext.webservice.client.demo.intf.user.User[] getAllUser() throws java.rmi.RemoteException{
    if (userService == null)
      _initUserServiceProxy();
    return userService.getAllUser();
  }
  
  public void dealUserRequest(com.bokesoft.yigo2.ext.webservice.client.demo.intf.user.User arg0) throws java.rmi.RemoteException{
    if (userService == null)
      _initUserServiceProxy();
    userService.dealUserRequest(arg0);
  }
  
  public com.bokesoft.yigo2.ext.webservice.client.demo.intf.user.User getUser(java.lang.String arg0) throws java.rmi.RemoteException{
    if (userService == null)
      _initUserServiceProxy();
    return userService.getUser(arg0);
  }
  
  
}