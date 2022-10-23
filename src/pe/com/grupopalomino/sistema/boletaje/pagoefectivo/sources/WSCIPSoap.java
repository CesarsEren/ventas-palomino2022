
package pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "WSCIPSoap", targetNamespace = "http://tempuri.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WSCIPSoap {


    /**
     * 
     * @param request
     * @return
     *     returns org.tempuri.BEWSGenCIPResponse
     */
    @WebMethod(operationName = "GenerarCIP", action = "http://tempuri.org/GenerarCIP")
    @WebResult(name = "GenerarCIPResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "GenerarCIP", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GenerarCIP")
    @ResponseWrapper(localName = "GenerarCIPResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GenerarCIPResponse")
    public BEWSGenCIPResponse generarCIP(
        @WebParam(name = "request", targetNamespace = "http://tempuri.org/")
        BEWSGenCIPRequest request);

    /**
     * 
     * @param request
     * @return
     *     returns org.tempuri.BEWSElimCIPResponse
     */
    @WebMethod(operationName = "EliminarCIP", action = "http://tempuri.org/EliminarCIP")
    @WebResult(name = "EliminarCIPResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "EliminarCIP", targetNamespace = "http://tempuri.org/", className = "org.tempuri.EliminarCIP")
    @ResponseWrapper(localName = "EliminarCIPResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.EliminarCIPResponse")
    public BEWSElimCIPResponse eliminarCIP(
        @WebParam(name = "request", targetNamespace = "http://tempuri.org/")
        BEWSElimCIPRequest request);

    /**
     * 
     * @param request
     * @return
     *     returns org.tempuri.BEWSConsCIPsConciliadosResponse
     */
    @WebMethod(operationName = "ConsultarCIPConciliados", action = "http://tempuri.org/ConsultarCIPConciliados")
    @WebResult(name = "ConsultarCIPConciliadosResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "ConsultarCIPConciliados", targetNamespace = "http://tempuri.org/", className = "org.tempuri.ConsultarCIPConciliados")
    @ResponseWrapper(localName = "ConsultarCIPConciliadosResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.ConsultarCIPConciliadosResponse")
    public BEWSConsCIPsConciliadosResponse consultarCIPConciliados(
        @WebParam(name = "request", targetNamespace = "http://tempuri.org/")
        BEWSConsCIPsConciliadosRequest request);

    /**
     * 
     * @param request
     * @return
     *     returns org.tempuri.BEWSConsultarCIPResponse
     */
    @WebMethod(operationName = "ConsultarCIP", action = "http://tempuri.org/ConsultarCIP")
    @WebResult(name = "ConsultarCIPResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "ConsultarCIP", targetNamespace = "http://tempuri.org/", className = "org.tempuri.ConsultarCIP")
    @ResponseWrapper(localName = "ConsultarCIPResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.ConsultarCIPResponse")
    public BEWSConsultarCIPResponse consultarCIP(
        @WebParam(name = "request", targetNamespace = "http://tempuri.org/")
        BEWSConsultarCIPRequest request);

    /**
     * 
     * @param request
     * @return
     *     returns org.tempuri.BEWSSolicitarResponse
     */
    @WebMethod(operationName = "SolicitarPago", action = "http://tempuri.org/SolicitarPago")
    @WebResult(name = "SolicitarPagoResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "SolicitarPago", targetNamespace = "http://tempuri.org/", className = "org.tempuri.SolicitarPago")
    @ResponseWrapper(localName = "SolicitarPagoResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.SolicitarPagoResponse")
    public BEWSSolicitarResponse solicitarPago(
        @WebParam(name = "request", targetNamespace = "http://tempuri.org/")
        BEWSSolicitarRequest request);

    /**
     * 
     * @param request
     * @return
     *     returns org.tempuri.BEWSConsultarSolicitudResponse
     */
    @WebMethod(operationName = "ConsultarSolicitudPago", action = "http://tempuri.org/ConsultarSolicitudPago")
    @WebResult(name = "ConsultarSolicitudPagoResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "ConsultarSolicitudPago", targetNamespace = "http://tempuri.org/", className = "org.tempuri.ConsultarSolicitudPago")
    @ResponseWrapper(localName = "ConsultarSolicitudPagoResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.ConsultarSolicitudPagoResponse")
    public BEWSConsultarSolicitudResponse consultarSolicitudPago(
        @WebParam(name = "request", targetNamespace = "http://tempuri.org/")
        BEWSConsultarSolicitudRequest request);

}