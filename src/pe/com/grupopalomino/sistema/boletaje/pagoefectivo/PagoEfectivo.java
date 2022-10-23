/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.grupopalomino.sistema.boletaje.pagoefectivo;
import java.nio.file.*;  
import javax.xml.ws.BindingProvider;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import java.io.ByteArrayInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources.BEWSActualizaCIPRequestMod1;
import pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources.BEWSActualizaCIPResponseMod1;
import pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources.BEWSConsultarCIPRequestMod1;
import pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources.BEWSConsultarCIPResponseMod1;
import pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources.BEWSElimCIPRequestMod1;
import pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources.BEWSElimCIPResponseMod1;
import pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources.BEWSGenCIPRequestMod1;
import pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources.BEWSGenCIPResponseMod1;
import pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources.Service;
import pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources.ServiceSoap;
import pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources.WSCrypto;
import pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources.WSCryptoSoap;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

/**
 *
 * @author PROYPERU
 */
@SuppressWarnings("serial")
public class PagoEfectivo extends HttpServlet {
    //Configuramos nuestros parámetros de WS y rutas de las claves.
    public static String PublicPath;
    public static String PrivatePath;
    public static String UrlPECriptography;
    public static String UrlPEService;
    public static String CodServ;
    
    private static final Logger log = Logger.getLogger(PagoEfectivo.class);
    
    public static BEpaymentResponse GenerarCIP(BEGenRequest paymentRequest) throws SAXException, ParserConfigurationException {           
        //construimos el objeto request:
        BEWSGenCIPRequestMod1 solicitud = new BEWSGenCIPRequestMod1();
        solicitud.setCodServ(CodServ);
        //construimos el xml
        bl_helper helper = new bl_helper();
        //Asignamos los datos de pago en XML
        solicitud.setXml(helper.convertToXml(paymentRequest).trim());
            
        BEWSGenCIPResponseMod1 response = new BEWSGenCIPResponseMod1();
        WSCrypto wscrypto = new WSCrypto();
        WSCryptoSoap wscryptoSoap = wscrypto.getWSCryptoSoap();
        ((BindingProvider)wscryptoSoap).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,UrlPECriptography);       
        //Convertimos a bytes los archivos que contienen las claves (proporcionado por PagoEfectivo)
        BEpaymentResponse paymentResponse = new BEpaymentResponse();
        try {
            byte[] dataPublic = Files.readAllBytes(Paths.get(PublicPath));
            byte[] dataPrivate = Files.readAllBytes(Paths.get(PrivatePath));
            //Firmamos la trama xml antes de encriptarla y el resultado lo asiganamos en el campo firma
            solicitud.setFirma(wscryptoSoap.signer(solicitud.getXml(), dataPrivate));
            //Encriptamos la trama xml y la asignamos al mismo campo xml
            solicitud.setXml(wscryptoSoap.encryptText(solicitud.getXml(), dataPublic));
            
            
            Service wscip = new Service();            
            ServiceSoap wscipSoap = wscip.getServiceSoap();
            ((BindingProvider)wscipSoap).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,UrlPEService);            
            response = wscipSoap.generarCIPMod1(solicitud);
            //Desencriptamos el contenido xml donde vienen los datos a usar.
            response.setXml(wscryptoSoap.decryptText(response.getXml(), dataPrivate));
            String xml = response.getXml();
                        
            paymentResponse.Estado = response.getEstado();
            paymentResponse.Mensaje = response.getMensaje();
            paymentResponse.Xml = response.getXml();
            
            if(response != null)
            {
                DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document doc = db.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
                NodeList nlSolpago = doc.getElementsByTagName("ConfirSolPago");
                if (nlSolpago.getLength() > 0) {
                    NodeList nlCIP = ((Element) nlSolpago.item(0)).getElementsByTagName("CIP");
                    if (nlCIP.getLength() > 0) {
                        NodeList nlNumeroCIP = ((Element)nlCIP.item(0)).getElementsByTagName("NumeroOrdenPago");
                        paymentResponse.NumeroCip = nlNumeroCIP.item(0).getTextContent();
                        
                        NodeList nlIdComercio = ((Element)nlCIP.item(0)).getElementsByTagName("MerchantID");
                        paymentResponse.IdComercio = nlIdComercio.item(0).getTextContent();
                    }
                    //Del mismo modo recuperar el campo Token
                    NodeList nlToken = ((Element) nlSolpago.item(0)).getElementsByTagName("Token");
                    paymentResponse.Token = nlToken.item(0).getTextContent();
                 
                }
            }
            
        } catch (IOException ex) {
            //Logger.getLogger(PagoEfectivo.class.getName()).log(Level.SEVERE, null, ex);
        	log.info(Utils.printStackTraceToString(ex));
        }
        return paymentResponse;
    }
    
    public static BEWSConsultarCIPResponseMod1 ConsultarCIP(BEWSConsultarCIPRequestMod1 request){
    	log.info("ANTES DE INICIALICAR EL LLAMADO DE LOS WEBSERVICES");
    	
        BEWSConsultarCIPResponseMod1 response = new BEWSConsultarCIPResponseMod1();
        
        log.info("DESPUES DE INICIALIZAR DE LOS WEBSERVICES");
        
        Service wscip = new Service();
        ServiceSoap wscipSoap = wscip.getServiceSoap();
        ((BindingProvider)wscipSoap).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,UrlPEService);
        
        WSCrypto wscrypto = new WSCrypto();
        WSCryptoSoap wscryptoSoap = wscrypto.getWSCryptoSoap();
        ((BindingProvider)wscryptoSoap).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,UrlPECriptography);
        
        log.info("DESPUES DE INICIALIZAR LAS CONFIGURACIONES --> "+"SERVICE :"+UrlPEService +" CRYPTO:"+UrlPECriptography); 
        
        //Convertimos a bytes los archivos que contienen las claves (proporcionado por PagoEfectivo)
        try {
            byte[] dataPublic = Files.readAllBytes(Paths.get(PublicPath));
            byte[] dataPrivate = Files.readAllBytes(Paths.get(PrivatePath));
            
            log.info("LLAVES --> "+"PUBLICA :"+PublicPath +" PRIVADA:"+PrivatePath);
            
            log.info("BYTES --> "+"PUBLICA :"+dataPublic.length);
            log.info("BYTES --> "+"PPRIVADA :"+dataPrivate.length);
            
            //Firmamos la trama xml antes de encriptarla y el resultado lo asiganamos en el campo firma
            request.setFirma(wscryptoSoap.signer(request.getCIPS(), dataPrivate));
            log.info("TRAMA FIRMADA---");
            //Encriptamos la trama xml y la asignamos al mismo campo xml
            request.setCIPS(wscryptoSoap.encryptText(request.getCIPS(), dataPublic));
            log.info("TRAMA ENCRYPTADA---");
            
            response = wscipSoap.consultarCIPMod1(request);
            log.info("TRAMA CONSULTADA---");
            //Desencriptamos el contenido xml donde vienen los datos a usar.
            response.setXML(wscryptoSoap.decryptText(response.getXML(), dataPrivate));
            log.info("OBTENIENDO RESPUESTA FINAL---");
        } catch (IOException ex) {
            //Logger.getLogger(PagoEfectivo.class.getName()).log(Level.SEVERE, null, ex);
        	log.info(Utils.printStackTraceToString(ex));
        }
        return response;
    }
    
    public static BEWSActualizaCIPResponseMod1 ActualizarCIP(BEWSActualizaCIPRequestMod1 request){
        BEWSActualizaCIPResponseMod1 response = new BEWSActualizaCIPResponseMod1();
        
        Service wscip = new Service();
        ServiceSoap wscipSoap = wscip.getServiceSoap();
        ((BindingProvider)wscipSoap).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,UrlPEService);
        
        WSCrypto wscrypto = new WSCrypto();
        WSCryptoSoap wscryptoSoap = wscrypto.getWSCryptoSoap();
        ((BindingProvider)wscryptoSoap).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,UrlPECriptography);
        
        //Convertimos a bytes los archivos que contienen las claves (proporcionado por PagoEfectivo)
        try {
            byte[] dataPublic = Files.readAllBytes(Paths.get(PublicPath));
            byte[] dataPrivate = Files.readAllBytes(Paths.get(PrivatePath));
            //Firmamos la trama xml antes de encriptarla y el resultado lo asiganamos en el campo firma
            request.setFirma(wscryptoSoap.signer(request.getCIP(), dataPrivate));
            //Encriptamos la trama xml y la asignamos al mismo campo xml
            request.setCIP(wscryptoSoap.encryptText(request.getCIP(), dataPublic));
            
            response = wscipSoap.actualizarCIPMod1(request);
        } catch (IOException ex) {
            //Logger.getLogger(PagoEfectivo.class.getName()).log(Level.SEVERE, null, ex);
        	log.info(Utils.printStackTraceToString(ex));
        }
        return response;
    }
    
    public static BEWSElimCIPResponseMod1 EliminarCIP(BEWSElimCIPRequestMod1 request){
        BEWSElimCIPResponseMod1 response = new BEWSElimCIPResponseMod1();
        
        Service wscip = new Service();
        ServiceSoap wscipSoap = wscip.getServiceSoap();
        ((BindingProvider)wscipSoap).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,UrlPEService);
        
        WSCrypto wscrypto = new WSCrypto();
        WSCryptoSoap wscryptoSoap = wscrypto.getWSCryptoSoap();
        ((BindingProvider)wscryptoSoap).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,UrlPECriptography);
        
        //Convertimos a bytes los archivos que contienen las claves (proporcionado por PagoEfectivo)
        try {
            byte[] dataPublic = Files.readAllBytes(Paths.get(PublicPath));
            byte[] dataPrivate = Files.readAllBytes(Paths.get(PrivatePath));
            //Firmamos la trama xml antes de encriptarla y el resultado lo asiganamos en el campo firma
            request.setFirma(wscryptoSoap.signer(request.getCIP(), dataPrivate));
            //Encriptamos la trama xml y la asignamos al mismo campo xml
            request.setCIP(wscryptoSoap.encryptText(request.getCIP(), dataPublic));
            
            response = wscipSoap.eliminarCIPMod1(request);
        } catch (IOException ex) {
            //Logger.getLogger(PagoEfectivo.class.getName()).log(Level.SEVERE, null, ex);
        	log.info(Utils.printStackTraceToString(ex));
        }
        return response;
    }
    public static BEnotificacionResponse DesenciptarData(String TextoEncriptado) throws SAXException, ParserConfigurationException{
        String response = "";
        WSCrypto wscrypto = new WSCrypto();
        WSCryptoSoap wscryptoSoap = wscrypto.getWSCryptoSoap();
        ((BindingProvider)wscryptoSoap).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,UrlPECriptography);
        BEnotificacionResponse notificacionResponse = new BEnotificacionResponse();
         try {
        	 
        	 
            byte[] dataPrivate = Files.readAllBytes(Paths.get(PrivatePath));
            
            log.info("RUTA GLOBAL -->"+PrivatePath);
            
            log.info("TAMANIO BYTES -->"+dataPrivate.length);
            
            response = wscryptoSoap.decryptText(TextoEncriptado, dataPrivate);
            
            notificacionResponse.Xml = response;
            
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = db.parse(new ByteArrayInputStream(response.getBytes("UTF-8")));
            
            NodeList nlSolpago = doc.getElementsByTagName("ConfirSolPago");
            if (nlSolpago.getLength() > 0) {
                //NodeList nliNumeroOrdenPago = ((Element)nlSolpago.item(0)).getElementsByTagName("CodTrans");
                //notificacionResponse.NumeroCip = nliNumeroOrdenPago.item(0).getTextContent();
                
                NodeList nlEstado = ((Element)nlSolpago.item(0)).getElementsByTagName("Estado");
                notificacionResponse.Estado = nlEstado.item(0).getTextContent();
                
                
                NodeList nlCIP = ((Element) nlSolpago.item(0)).getElementsByTagName("CIP");
                if (nlCIP.getLength() > 0) {
                    NodeList nlNumeroCIP = ((Element)nlCIP.item(0)).getElementsByTagName("NumeroOrdenPago");
                    notificacionResponse.NumeroCip = nlNumeroCIP.item(0).getTextContent();
                        
                    NodeList nlIdComercio = ((Element)nlCIP.item(0)).getElementsByTagName("MerchantID");
                    notificacionResponse.IdComercio = nlIdComercio.item(0).getTextContent();
                }
            }
        } catch (IOException ex) {
            //Logger.getLogger(PagoEfectivo.class.getName()).log(Level.SEVERE, null, ex);
        	log.info(Utils.printStackTraceToString(ex));
        }
        return notificacionResponse;
    }
}