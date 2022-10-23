/**
 * BillServicePortServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package pe.gob.sunat.service;

public class BillServicePortServiceLocator extends org.apache.axis.client.Service implements pe.gob.sunat.service.BillServicePortService {

    public BillServicePortServiceLocator() {
    }


    public BillServicePortServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public BillServicePortServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BillServicePortSoap11
    //private java.lang.String BillServicePortSoap11_address = "https://calidad.escondatagate.net:443/wsValidator/ol-ti-itcpe";//DIRECCION DE UBL 2.0
    //private java.lang.String BillServicePortSoap11_address = "https://calidad.escondatagate.net/wsValidator_2_1/ol-ti-itcpe";//DIRECCION DE UBL 2.1
    //private java.lang.String BillServicePortSoap11_address = "https://www.escondatagate.net/wsValidator/ol-ti-itcpe";//DIRECCION DE UBL 2.0 prod
    
    //private java.lang.String BillServicePortSoap11_address = "https://www.escondatagate.net:443/wsValidator_2_1/ol-ti-itcpe"; // DIRECCION DE UBL 2.0 prod JCHC 25/03/2022
    private java.lang.String BillServicePortSoap11_address = "https://osetesting.bizlinks.com.pe/ol-ti-itcpe"; // DIRECCION DE UBL 2.0 prueba JCHC 28/03/2022
    
    //https://www.escondatagate.net/wsValidator/ol-ti-itcpe/billService.wsdl/
    public java.lang.String getBillServicePortSoap11Address() {
        return BillServicePortSoap11_address;
    }

    // The WSDD service name defaults to the port name.
    //private java.lang.String BillServicePortSoap11WSDDServiceName = "BillServicePortSoap11"; //este estaba con excontech
    private java.lang.String BillServicePortSoap11WSDDServiceName = "billService"; //nuevo de la nueva OSE

    public java.lang.String getBillServicePortSoap11WSDDServiceName() {
        return BillServicePortSoap11WSDDServiceName;
    }

    public void setBillServicePortSoap11WSDDServiceName(java.lang.String name) {
        BillServicePortSoap11WSDDServiceName = name;
    }

    public pe.gob.sunat.service.BillServicePort getBillServicePortSoap11() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BillServicePortSoap11_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBillServicePortSoap11(endpoint);
    }

    public pe.gob.sunat.service.BillServicePort getBillServicePortSoap11(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            pe.gob.sunat.service.BillServicePortSoap11Stub _stub = new pe.gob.sunat.service.BillServicePortSoap11Stub(portAddress, this);
            _stub.setPortName(getBillServicePortSoap11WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBillServicePortSoap11EndpointAddress(java.lang.String address) {
        BillServicePortSoap11_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (pe.gob.sunat.service.BillServicePort.class.isAssignableFrom(serviceEndpointInterface)) {
                pe.gob.sunat.service.BillServicePortSoap11Stub _stub = new pe.gob.sunat.service.BillServicePortSoap11Stub(new java.net.URL(BillServicePortSoap11_address), this);
                _stub.setPortName(getBillServicePortSoap11WSDDServiceName());
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
        if (portName == null) { return getPort(serviceEndpointInterface); }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("BillServicePortSoap11".equals(inputPortName)) {
            return getBillServicePortSoap11();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.sunat.gob.pe", "BillServicePortService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.sunat.gob.pe", "BillServicePortSoap11"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BillServicePortSoap11".equals(portName)) {
            setBillServicePortSoap11EndpointAddress(address);
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
