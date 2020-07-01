/**
 * DepartmentServiceImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bokesoft.yigo2.ext.webservice.client.demo.servercfg.dep;

public class DepartmentServiceImplServiceLocator extends org.apache.axis.client.Service implements com.bokesoft.yigo2.ext.webservice.client.demo.servercfg.dep.DepartmentServiceImplService {

    public DepartmentServiceImplServiceLocator() {
    }


    public DepartmentServiceImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public DepartmentServiceImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for DepartmentServiceImplPort
    private java.lang.String DepartmentServiceImplPort_address = "http://localhost:8080/webservice/dep";

    public java.lang.String getDepartmentServiceImplPortAddress() {
        return DepartmentServiceImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String DepartmentServiceImplPortWSDDServiceName = "DepartmentServiceImplPort";

    public java.lang.String getDepartmentServiceImplPortWSDDServiceName() {
        return DepartmentServiceImplPortWSDDServiceName;
    }

    public void setDepartmentServiceImplPortWSDDServiceName(java.lang.String name) {
        DepartmentServiceImplPortWSDDServiceName = name;
    }

    public com.bokesoft.yigo2.ext.webservice.client.demo.intf.dep.DepartmentService getDepartmentServiceImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(DepartmentServiceImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getDepartmentServiceImplPort(endpoint);
    }

    public com.bokesoft.yigo2.ext.webservice.client.demo.intf.dep.DepartmentService getDepartmentServiceImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.bokesoft.yigo2.ext.webservice.client.demo.servercfg.dep.DepartmentServiceImplServiceSoapBindingStub _stub = new com.bokesoft.yigo2.ext.webservice.client.demo.servercfg.dep.DepartmentServiceImplServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getDepartmentServiceImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setDepartmentServiceImplPortEndpointAddress(java.lang.String address) {
        DepartmentServiceImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.bokesoft.yigo2.ext.webservice.client.demo.intf.dep.DepartmentService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.bokesoft.yigo2.ext.webservice.client.demo.servercfg.dep.DepartmentServiceImplServiceSoapBindingStub _stub = new com.bokesoft.yigo2.ext.webservice.client.demo.servercfg.dep.DepartmentServiceImplServiceSoapBindingStub(new java.net.URL(DepartmentServiceImplPort_address), this);
                _stub.setPortName(getDepartmentServiceImplPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("DepartmentServiceImplPort".equals(inputPortName)) {
            return getDepartmentServiceImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://localhost:8080/", "DepartmentServiceImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://localhost:8080/", "DepartmentServiceImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("DepartmentServiceImplPort".equals(portName)) {
            setDepartmentServiceImplPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
