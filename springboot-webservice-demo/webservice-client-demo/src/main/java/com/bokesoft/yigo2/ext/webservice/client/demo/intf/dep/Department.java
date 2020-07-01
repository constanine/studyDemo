/**
 * Department.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bokesoft.yigo2.ext.webservice.client.demo.intf.dep;

public class Department  implements java.io.Serializable {
    private java.lang.String depId;

    private java.lang.String depname;

    public Department() {
    }

    public Department(
           java.lang.String depId,
           java.lang.String depname) {
           this.depId = depId;
           this.depname = depname;
    }


    /**
     * Gets the depId value for this Department.
     * 
     * @return depId
     */
    public java.lang.String getDepId() {
        return depId;
    }


    /**
     * Sets the depId value for this Department.
     * 
     * @param depId
     */
    public void setDepId(java.lang.String depId) {
        this.depId = depId;
    }


    /**
     * Gets the depname value for this Department.
     * 
     * @return depname
     */
    public java.lang.String getDepname() {
        return depname;
    }


    /**
     * Sets the depname value for this Department.
     * 
     * @param depname
     */
    public void setDepname(java.lang.String depname) {
        this.depname = depname;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Department)) return false;
        Department other = (Department) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.depId==null && other.getDepId()==null) || 
             (this.depId!=null &&
              this.depId.equals(other.getDepId()))) &&
            ((this.depname==null && other.getDepname()==null) || 
             (this.depname!=null &&
              this.depname.equals(other.getDepname())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getDepId() != null) {
            _hashCode += getDepId().hashCode();
        }
        if (getDepname() != null) {
            _hashCode += getDepname().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Department.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intf.demo.webservice.ext.yigo2.bokesoft.com/", "department"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("depId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "depId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("depname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "depname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
