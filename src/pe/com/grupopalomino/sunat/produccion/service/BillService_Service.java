
package pe.com.grupopalomino.sunat.produccion.service;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10
 * Generated source version: 2.2
 * 
 */
/*
 -- ESTA URL TAMBI�N ES V�LIDA CUANDO SU SERVICIO DE LA SUNAT EST� CAIDO SOLO HACER EL CAMBIO DE LOS WSDL EN ESTA CLASE.
 -- 08/05/2018
 
 -- URL PRODUCCION
 https://e-factura.sunat.gob.pe/ol-ti-itcpfegem/billService?wsdl
 
 -- URL DE RESPALDO
 https://www.sunat.gob.pe/ol-ti-itcpfegem/billService?wsdl
 

 * */
@WebServiceClient(name = "billService", targetNamespace = "http://service.gem.factura.comppago.registro.servicio.sunat.gob.pe/", wsdlLocation = "https://e-factura.sunat.gob.pe/ol-ti-itcpfegem/billService?wsdl")
public class BillService_Service
    extends Service
{

    private final static URL BILLSERVICE_WSDL_LOCATION;
    private final static WebServiceException BILLSERVICE_EXCEPTION;
    private final static QName BILLSERVICE_QNAME = new QName("http://service.gem.factura.comppago.registro.servicio.sunat.gob.pe/", "billService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("https://e-factura.sunat.gob.pe/ol-ti-itcpfegem/billService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        BILLSERVICE_WSDL_LOCATION = url;
        BILLSERVICE_EXCEPTION = e;
    }

    public BillService_Service() {
        super(__getWsdlLocation(), BILLSERVICE_QNAME);
    }

    public BillService_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), BILLSERVICE_QNAME, features);
    }

    public BillService_Service(URL wsdlLocation) {
        super(wsdlLocation, BILLSERVICE_QNAME);
    }

    public BillService_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, BILLSERVICE_QNAME, features);
    }

    public BillService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public BillService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns BillService
     */
    @WebEndpoint(name = "BillServicePort")
    public BillService getBillServicePort() {
        return super.getPort(new QName("http://service.gem.factura.comppago.registro.servicio.sunat.gob.pe/", "BillServicePort"), BillService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns BillService
     */
    @WebEndpoint(name = "BillServicePort")
    public BillService getBillServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://service.gem.factura.comppago.registro.servicio.sunat.gob.pe/", "BillServicePort"), BillService.class, features);
    }

    /**
     * 
     * @return
     *     returns BillService
     */
    @WebEndpoint(name = "BillServicePort.3")
    public BillService getBillServicePort3() {
        return super.getPort(new QName("http://service.gem.factura.comppago.registro.servicio.sunat.gob.pe/", "BillServicePort.3"), BillService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns BillService
     */
    @WebEndpoint(name = "BillServicePort.3")
    public BillService getBillServicePort3(WebServiceFeature... features) {
        return super.getPort(new QName("http://service.gem.factura.comppago.registro.servicio.sunat.gob.pe/", "BillServicePort.3"), BillService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (BILLSERVICE_EXCEPTION!= null) {
            throw BILLSERVICE_EXCEPTION;
        }
        return BILLSERVICE_WSDL_LOCATION;
    }

}