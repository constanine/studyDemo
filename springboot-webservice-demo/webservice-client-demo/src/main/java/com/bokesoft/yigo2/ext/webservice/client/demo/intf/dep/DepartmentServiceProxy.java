package com.bokesoft.yigo2.ext.webservice.client.demo.intf.dep;

public class DepartmentServiceProxy implements com.bokesoft.yigo2.ext.webservice.client.demo.intf.dep.DepartmentService {
  private String _endpoint = null;
  private com.bokesoft.yigo2.ext.webservice.client.demo.intf.dep.DepartmentService departmentService = null;
  
  public DepartmentServiceProxy() {
    _initDepartmentServiceProxy();
  }
  
  public DepartmentServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initDepartmentServiceProxy();
  }
  
  private void _initDepartmentServiceProxy() {
    try {
      departmentService = (new com.bokesoft.yigo2.ext.webservice.client.demo.servercfg.dep.DepartmentServiceImplServiceLocator()).getDepartmentServiceImplPort();
      if (departmentService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)departmentService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)departmentService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (departmentService != null)
      ((javax.xml.rpc.Stub)departmentService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.bokesoft.yigo2.ext.webservice.client.demo.intf.dep.DepartmentService getDepartmentService() {
    if (departmentService == null)
      _initDepartmentServiceProxy();
    return departmentService;
  }
  
  public com.bokesoft.yigo2.ext.webservice.client.demo.intf.dep.Department getDepartment(java.lang.String arg0) throws java.rmi.RemoteException{
    if (departmentService == null)
      _initDepartmentServiceProxy();
    return departmentService.getDepartment(arg0);
  }
  
  public com.bokesoft.yigo2.ext.webservice.client.demo.intf.dep.Department[] getAllDepartment() throws java.rmi.RemoteException{
    if (departmentService == null)
      _initDepartmentServiceProxy();
    return departmentService.getAllDepartment();
  }
  
  
}