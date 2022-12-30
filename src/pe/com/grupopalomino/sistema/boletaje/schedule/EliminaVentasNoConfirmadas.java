// 
// Decompiled by Procyon v0.5.36
// 
package pe.com.grupopalomino.sistema.boletaje.schedule;

import org.apache.commons.io.FileUtils;
import java.io.File;
import pe.com.grupopalomino.sistema.boletaje.action.ConsultasAction;
import java.net.MalformedURLException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import pe.com.grupopalomino.sistema.boletaje.util.FuncionesFacturacionPDF;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Ventas_FacturacionBean;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.Image;
import org.apache.commons.io.IOUtils;
import java.net.URL;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Rectangle;
import pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources.BEWSConsultarCIPResponseMod1;
import java.io.ByteArrayInputStream;
import pe.com.grupopalomino.sistema.boletaje.pagoefectivo.sources.BEWSConsultarCIPRequestMod1;
import pe.com.grupopalomino.sistema.boletaje.pagoefectivo.PagoEfectivo;
import java.util.Calendar;
import java.io.OutputStream;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import java.util.zip.ZipEntry;
import pe.gob.sunat.service.BillServicePort;
import pe.gob.sunat.service.BillServicePortService;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Varios_FacturacionBean;
import javax.xml.ws.soap.SOAPFaultException;
import com.sun.xml.internal.ws.fault.ServerSOAPFaultException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import java.io.Reader;
import java.io.StringReader;
import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.zip.ZipInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import pe.gob.sunat.service.BillServicePortServiceLocator;
import javax.activation.DataSource;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import pe.com.grupopalomino.sistema.boletaje.util.GeneraDocumentoFe;
import java.time.LocalDate;
import pe.com.grupopalomino.sistema.boletaje.bean.B_FacturacionBean;
import java.text.SimpleDateFormat;
import java.util.Date;
import pe.com.grupopalomino.sistema.boletaje.bean.ReporteContabilidad;
import java.io.IOException;
import pe.com.grupopalomino.sistema.boletaje.util.GenerarEmail;
import java.io.ByteArrayOutputStream;
import pe.com.grupopalomino.sistema.boletaje.util.GenerarEmailInformes;
import java.util.HashMap;
import org.springframework.scheduling.annotation.Scheduled;
import pe.com.grupopalomino.sistema.boletaje.transaction.interfaces.VentaInterface;
import java.util.Map;
import java.util.Iterator;
import java.util.List;
import pe.com.grupopalomino.sistema.boletaje.formviews.TarjetaCredito;
import pe.com.grupopalomino.sistema.boletaje.bean.B_Correlativos;
import pe.com.grupopalomino.sistema.boletaje.bean.B_CuentaCorrienteBean;
import pe.com.grupopalomino.sistema.boletaje.transaction.interfaces.VentaInterfaceI;
import pe.com.grupopalomino.sistema.boletaje.visarespuesta.ConsultaEticketVisa;
import pe.com.grupopalomino.sistema.boletaje.bean.B_VentaBean;
import java.util.ArrayList;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;
import pe.com.grupopalomino.sistema.boletaje.service.V_Ventas_FacturacionServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.FacturacionServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.Varios_FacturacionServiceI;
import pe.com.grupopalomino.sistema.boletaje.dao.B_ReporteContabilidadIDao;
import pe.com.grupopalomino.sistema.boletaje.service.CuentaCorrienteServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.CorrelativoServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.VentaBoletaServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.V_Ventas_FacturacionService;
import pe.com.grupopalomino.sistema.boletaje.service.FacturacionService;
import pe.com.grupopalomino.sistema.boletaje.service.Varios_FacturacionService;
import org.apache.log4j.Logger;
import pe.com.grupopalomino.sistema.boletaje.dao.B_ReporteContabilidadDao;
import pe.com.grupopalomino.sistema.boletaje.service.CuentaCorrienteService;
import pe.com.grupopalomino.sistema.boletaje.service.CorrelativosService;
import pe.com.grupopalomino.sistema.boletaje.service.VentaBoletaService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class EliminaVentasNoConfirmadas
{
    HttpServletRequest request;
    VentaBoletaService venta;
    private CorrelativosService servicecorrelativo;
    private CuentaCorrienteService servicecuentacorriente;
    B_ReporteContabilidadDao reportecontabilidadservice;
    private static final Logger log;
    VentaBoletaService serviceventas;
    VentaBoletaService boletajecipservice;
    static String URL_BASE;
    private Varios_FacturacionService variosservice;
    private FacturacionService facturacionservice;
    private V_Ventas_FacturacionService ventasfacturacionservice;
    
    static {
        log = Logger.getLogger((Class)EliminaVentasNoConfirmadas.class);
        //EliminaVentasNoConfirmadas.URL_BASE = "http://172.16.10.230:8443/wspalomino/palomino/fe/generadocumento";
        
        EliminaVentasNoConfirmadas.URL_BASE = "https://ventas.grupopalomino.com.pe:8443/wspalomino/palomino/fe/generadocumento";
        
        //EliminaVentasNoConfirmadas.URL_BASE = "http://190.81.173.132:8443/wspalomino/palomino/fe/generadocumento";
        
    }
    
    public EliminaVentasNoConfirmadas() {
        this.venta = (VentaBoletaService)new VentaBoletaServiceI();
        this.servicecorrelativo = (CorrelativosService)new CorrelativoServiceI();
        this.servicecuentacorriente = (CuentaCorrienteService)new CuentaCorrienteServiceI();
        this.reportecontabilidadservice = (B_ReporteContabilidadDao)new B_ReporteContabilidadIDao();
        this.serviceventas = (VentaBoletaService)new VentaBoletaServiceI();
        this.boletajecipservice = (VentaBoletaService)new VentaBoletaServiceI();
        this.variosservice = (Varios_FacturacionService)new Varios_FacturacionServiceI();
        this.facturacionservice = (FacturacionService)new FacturacionServiceI();
        this.ventasfacturacionservice = (V_Ventas_FacturacionService)new V_Ventas_FacturacionServiceI();
    }
    
    @Scheduled(fixedRate = 300000L)
    public void verificaVentasPendiente() {
    	log.info("[INICIO]======= verificaVentasPendiente =======");
        if (Utils.isProduccion()) {
            List<B_VentaBean> listaPendientes = new ArrayList<B_VentaBean>();
            List<B_VentaBean> listaPendientesEmbarcar = new ArrayList<B_VentaBean>();
            try {
                listaPendientes = (List<B_VentaBean>)this.venta.SelectListVentasPendientesVisa();
                listaPendientesEmbarcar = (List<B_VentaBean>)this.venta.SelectListVentasPendientesEmbarcar();
                EliminaVentasNoConfirmadas.log.info((Object)("********* INGRESO A LAS VENTAS PENDIENTES DE EMBARCAR *********** CANTIDAD : " + listaPendientesEmbarcar.size()));
                if (listaPendientesEmbarcar.size() > 0) {
	                for (final B_VentaBean item : listaPendientesEmbarcar) {
	                	 EliminaVentasNoConfirmadas.log.info((Object)("********* INGRESO A LAS VENTAS PENDIENTES DE EMBARCAR *********** : " + item.toString()));
	                	final String empresaD = this.CodigoDeEmpresaCondicionado(item.getNroDestino(), new StringBuilder().append(item.getNroServicio()).toString());
	                    item.setEmpresa(empresaD);
	                    EliminaVentasNoConfirmadas.log.info((Object)("********* GENERANDO EL BOLETO *********** : " ));
	                    this.facturacionservice.USP_GENERARBOLETO(new StringBuilder(String.valueOf(item.getNro())).toString(), "9999", (item.getRuc() == null) ? 16 : 17, empresaD);
	                    EliminaVentasNoConfirmadas.log.info((Object)("********* CORREO DEL CLIENTE QUE PAGO *********** : " + item.getUsuarioVisa()));
	                    this.consumirservicio(new StringBuilder().append(item.getNro()).toString(), "B");
	                    List<B_VentaBean> ventaEmbarcar = new ArrayList<B_VentaBean>();
	                    ventaEmbarcar = (List<B_VentaBean>) item;
	                    EliminaVentasNoConfirmadas.log.info((Object)("********* ENVIANDO CORREO A *********** : " + item.getUsuarioVisa()));
	                    //item.getUsuarioVisa()
	                    GenerarEmail.enviarCorreoAdjunto(GenerarEmail.parametros("evelasquez@embarcar.com.pe,desarrolladorweb@grupopalomino.com.pe",ventaEmbarcar.get(0).getEticketVisa()), this.DescargaPDFTicket(ventaEmbarcar));
	                    EliminaVentasNoConfirmadas.log.info((Object)("********* CORREO ENVIADO *********** A : " + item.getUsuarioVisa()));
	                } 
            	}
                
                if (listaPendientes.size() > 0) {
                    
                	int operacion = -1;
                    String userCliente = "";
                    EliminaVentasNoConfirmadas.log.info((Object)("********* INGRESO A LAS VENTAS PENDIENTES DE VISA *********** CANTIDAD : " + listaPendientes.size()));
                    EliminaVentasNoConfirmadas.log.info((Object)("********* INGRESO A LAS VENTAS PENDIENTES DE VISA *********** CANTIDAD : " + listaPendientes.toString()));
                    
                    for (final B_VentaBean bean : listaPendientes) {
                    	try {
                    		final Map<String, String> respuestaVisa = (Map<String, String>)ConsultaEticketVisa.obtieneRespuestaVisa(bean.getEticket());
                            EliminaVentasNoConfirmadas.log.info((Object)("RESPUESTA DE VISA PEDIENTES : XML -->  " + respuestaVisa));
                            EliminaVentasNoConfirmadas.log.info((Object)("RESPUESTA DE VISA PENDIENTES : RESPUESTA -->  " + respuestaVisa.get("respuesta")));
                            EliminaVentasNoConfirmadas.log.info((Object)("TICKET GENERADO   PENDIENTES : TICKET    -->  " + bean.getEticket()));
                            if (respuestaVisa.get("respuesta").equals("1")) {
                                final String EticketVisa = respuestaVisa.get("pedidoID");
                                final String TarjetaHabiente = respuestaVisa.get("nombre_th");
                                final VentaInterface ventadatos = (VentaInterface)new VentaInterfaceI();
                                B_CuentaCorrienteBean cuentacorriente = new B_CuentaCorrienteBean();
                                B_CuentaCorrienteBean cuentacorrienteVerifica = new B_CuentaCorrienteBean();
                                List<B_VentaBean> ventas = new ArrayList<B_VentaBean>();
                                ventas = (List<B_VentaBean>)this.venta.getVerificaVentaNoConfirmadaVisa(bean.getEticket());
                                for (final B_VentaBean beanupdate : ventas) {
                                    userCliente = beanupdate.getUsuarioVisa();
                                    operacion = this.venta.updateVentaConfirmada(beanupdate.getNro(), beanupdate.getSalida(), "C", EticketVisa, beanupdate.getUsuarioVisa(), (String)null, TarjetaHabiente, beanupdate.getEticket(), 1);
                                    if (operacion != -1) {
                                        cuentacorrienteVerifica = this.servicecuentacorriente.ListaVentaPagoEfectivoCuentaCorriente(beanupdate.getNro());
                                        if (cuentacorrienteVerifica == null) {
                                            EliminaVentasNoConfirmadas.log.info((Object)("PREPARANDO EL REGISTRO EN LA CUENTA CORRIENTE VENTAS PENDIENTES VISA  -- NÂ° VENTA --> " + beanupdate.getNro()));
                                            B_Correlativos correlativos = new B_Correlativos();
                                            correlativos = this.servicecorrelativo.generaCorrelativo();
                                            cuentacorriente = ventadatos.DatosEstaticosCuentaCorriente(cuentacorriente);
                                            cuentacorriente = ventadatos.DatosDinamicosCuentaCorrienteVisa(beanupdate, cuentacorriente, correlativos, EticketVisa);
                                            operacion = this.servicecuentacorriente.insertCuentaCorriente(cuentacorriente);
                                        }
                                        else {
                                            EliminaVentasNoConfirmadas.log.info((Object)("LA VENTA YA EXISTE EN LA CUENTA CORRIENTE DE LAS VENTAS PENDIENTES  TICKET --> " + beanupdate.getNro()));
                                        }
                                        if (operacion != -1) {
                                            continue;
                                        }
                                        EliminaVentasNoConfirmadas.log.info((Object)("EROR AL INGRESAR EN LA CUENTA CORRIENTE LOS DATOS DE LAS VENTAS PENDIENTES  TICKET --> " + beanupdate.getEticket()));
                                    }
                                    else {
                                        EliminaVentasNoConfirmadas.log.info((Object)("EROR AL ACTUALIZAR LOS DATOS DE LAS VENTAS PENDIENTES  TICKET --> " + beanupdate.getEticket()));
                                    }
                                }
                                List<B_VentaBean> ventaVisa = new ArrayList<B_VentaBean>();
                                ventaVisa = (List<B_VentaBean>)this.venta.getVentaImprimirVisa(EticketVisa.trim().equals("") ? "" : EticketVisa);
                                EliminaVentasNoConfirmadas.log.info((Object)("VENTA PENDIENTE CONFIRMADA Y ENVIADA POR CORREO : TICKET    -->  " + bean.getEticket()));
                            }
                            else {
                                List<B_VentaBean> ventas2 = new ArrayList<B_VentaBean>();
                                ventas2 = (List<B_VentaBean>)this.venta.getVerificaVentaNoConfirmadaVisa(bean.getEticket());
                                for (final B_VentaBean ventarechazada : ventas2) {
                                    operacion = this.venta.updateVentaConfirmada(ventarechazada.getNro(), ventarechazada.getSalida(), "R", (String)null, ventarechazada.getUsuarioVisa(), (String)null, (String)null, ventarechazada.getEticket(), 1);
                                    userCliente = ventarechazada.getUsuarioVisa();
                                }
                                final TarjetaCredito tarjetaCredito = new TarjetaCredito();
                                tarjetaCredito.setCod_accion(Integer.parseInt(respuestaVisa.get("cod_accion")));
                                EliminaVentasNoConfirmadas.log.info((Object)("VENTA PENDIENTE DENEGADA Y ENVIADA POR CORREO : TICKET    -->  " + bean.getEticket()));
                            }
							
						} catch (Exception e) {
							EliminaVentasNoConfirmadas.log.info((Object)Utils.printStackTraceToString(e));
						}
                    }                                  
                }
            }
            catch (Exception e) {
                EliminaVentasNoConfirmadas.log.info((Object)"****************** ENTRO AL TRY CATCH ERROR AL ACTUALIZAR  LAS VENTAS PENDIENTES ******************");
                EliminaVentasNoConfirmadas.log.info((Object)Utils.printStackTraceToString(e));
            }
        }
        log.info("[FIN]======= verificaVentasPendiente =======");
    }
    
    public void EnviarCorreoPrueba(final int dato) {
        EliminaVentasNoConfirmadas.log.info((Object)"***** ENTRANDO AL ENVIO DE CORREO AUTOM\u00c1TICO DE PRUEBA******");
        final Map<String, String> parametros = new HashMap<String, String>();
        parametros.put("to", "desarrolladorweb@grupopalomino.com.pe");
        parametros.put("cc", "soporte@grupopalomino.com.pe");
        parametros.put("subject", "PRUEBA DE CORREO PALOMINO WEB");
        parametros.put("header", "PRUEBA DE PALOMINO WEB");
        parametros.put("body", "PRUEBA DE PALOMINO WEB PRUEBA DE PALOMINO WEB PRUEBA DE PALOMINO WEB PRUEBA DE PALOMINO WEB PRUEBA DE PALOMINO WEB PRUEBA DE PALOMINO WEB");
        try {
            if (dato == 1) {
                GenerarEmailInformes.enviarCorreoAdjunto((Map)parametros, (ByteArrayOutputStream)null);
            }
            else {
                GenerarEmail.enviarCorreoAdjunto((Map)parametros, (ByteArrayOutputStream)null);
            }
        }
        catch (IOException e) {
            EliminaVentasNoConfirmadas.log.info((Object)"****************** ENTRO AL TRY CATCH ERROR DE CORREO AUTOM\u00c1TICO ******************");
            EliminaVentasNoConfirmadas.log.info((Object)Utils.printStackTraceToString((Exception)e));
        }
    }
    
    @Scheduled(cron = "0 0 10 * * *")
    public void EnviarInformeContabilidad() {
    	log.info("[INICIO] ======= ENVIAR REPORTE A CONTABILIDAD TODO LOS DÍAS A LAS 10 AM =======");
        final Map<String, String> parametros = new HashMap<String, String>();
        
        parametros.put("to", "desarrolladorweb@grupopalomino.com.pe");
        parametros.put("cc", "asistentecontabilidad2@grupopalomino.com.pe,asistentecontabilidad3@grupopalomino.com.pe,asistentecontabilidad4@grupopalomino.com.pe,asistentecontabilidad5@grupopalomino.com.pe,earce@grupopalomino.com.pe,rcapdevila@grupopalomino.com.pe,erendon@grupopalomino.com.pe,creditoycobranza@grupopalomino.com.pe,asistentecontabilidad6@grupopalomino.com.pe");
        parametros.put("subject", "REPORTE CONTABILIDAD");
        parametros.put("header", "INFORMES WEB - GRUPOPALOMINO (DOCUMENTOS ELECTRONICOS)");
        
        final List<ReporteContabilidad> ListadocumentosPorEnviar = (List<ReporteContabilidad>)this.reportecontabilidadservice.GetDocumentosPorEnviar();
        final List<ReporteContabilidad> ListadocumentosRechazados = (List<ReporteContabilidad>)this.reportecontabilidadservice.GetDocumentosRechazados();
        StringBuilder consulta = new StringBuilder();
        for (final ReporteContabilidad bean : ListadocumentosPorEnviar) {
            consulta.append("<tr><td>" + bean.getFechaEmision() + "</td>");
            consulta.append("<td>" + bean.getEmpresaD() + "</td>");
            consulta.append("<td>" + bean.getDocumentos() + "</td></tr>");
        }
        final Date dia1 = this.sumarRestarDiasFecha(new Date(), -4);
        final Date dia2 = this.sumarRestarDiasFecha(dia1, -30);
        final StringBuilder sb = new StringBuilder();
        sb.append("Srs: <br>");
        sb.append("Para su conocimiento se les indica que hay: <br>");
        sb.append("Desde el dia " + new SimpleDateFormat("yyyy-MM-dd").format(dia2) + " hasta el dia " + new SimpleDateFormat("yyyy-MM-dd").format(dia1));
        sb.append("<h4>DOCUMENTOS POR ENVIAR</h4>");
        sb.append("<table border='1'>");
        sb.append("<thead>");
        sb.append("<th>Fecha Emisi\u00f3n</th>");
        sb.append("<th>Empresa</th>");
        sb.append("<th>Cantidad de Documentos</th>");
        sb.append("</thead>");
        sb.append("</thbody>");
        sb.append(consulta.toString());
        sb.append("</thbody>");
        sb.append("</table>");
        sb.append("<p>Nota: </p>");
        sb.append("<p>Link de envio de documentos </p> </br>");
        sb.append("<a href='https://ventas.grupopalomino.com.pe:8443/ventas/inicio'>https://ventas.grupopalomino.com.pe:8443/ventas/inicio</a>");
        sb.append("</br>");
        consulta = new StringBuilder();
        for (final ReporteContabilidad bean2 : ListadocumentosRechazados) {
            consulta.append("<tr><td>" + bean2.getFechaEmision() + "</td>");
            consulta.append("<td>" + bean2.getEmpresaD() + "</td>");
            consulta.append("<td>" + bean2.getDocumentos() + "</td></tr>");
        }
        sb.append("<h4>DOCUMENTOS RECHAZADOS</h4>");
        sb.append("<br>");
        sb.append("<table border='1'>");
        sb.append("<thead>");
        sb.append("<th>Fecha Emisi\u00f3n </th>");
        sb.append("<th>Empresa</th>");
        sb.append("<th>Cantidad de Documentos</th>");
        sb.append("</thead>");
        sb.append("</thbody>");
        sb.append(consulta.toString());
        sb.append("</thbody>");
        sb.append("</table>");
        parametros.put("body", sb.toString());
        EliminaVentasNoConfirmadas.log.info((Object)("********* REPORTE CONTABILIDAD INICIA PROCESO*********** : " + sb.toString()));
        try {
            GenerarEmailInformes.enviarCorreoAdjunto2((Map)parametros, (ByteArrayOutputStream)null);
            EliminaVentasNoConfirmadas.log.info((Object)("********* ENVIANDO REPORTE CONTABILIDAD *********** : "));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    	log.info("[FIN]======= ENVIAR REPORTE A CONTABILIDAD TODO LOS DÍAS A LAS 10 AM =======");
    }
    

    @Scheduled(cron = "0 0 23 * * *")
    public void EnviarDocumentosOse() {
    	log.info("[INICIO]======= ENVIO AUTOMÁTICO DE DOCUMENTOS ELECTRÓNICOS A LAS 11:00 PM =======");
        if (Utils.isProduccion()) {
        	EliminaVentasNoConfirmadas.log.info((Object)("********* INICIANDO ENVIO AUTOMATICO SUNAT*********** : "));
            final Map<String, Object> mapaEnvio = new HashMap<String, Object>();
            final List<Map<String, Object>> lstResultado = new ArrayList<Map<String, Object>>();
            Map<String, Object> mapaFacturacion = new HashMap<String, Object>();
            V_Varios_FacturacionBean facturacion = null;
            final List<String> ListEmpresas = new ArrayList<String>();
            ListEmpresas.add("002");
            ListEmpresas.add("003");
            ListEmpresas.add("004");
            final Varios_FacturacionService variosservice = (Varios_FacturacionService)new Varios_FacturacionServiceI();
            final FacturacionService facturacionservice = (FacturacionService)new FacturacionServiceI();
            
            for (final String empresa : ListEmpresas) {
                try {
                    byte[] respuestaSunat = null;
                    final List<Map<String, Object>> lstData = new ArrayList<Map<String, Object>>();
                    final List<B_FacturacionBean> listaEnviar = new ArrayList<B_FacturacionBean>();
                    facturacion = variosservice.SQL_SELECT_LISTA_PARAMETROS_FACTURADOR(empresa);
                    final Map<String, Object> foo = new HashMap<String, Object>();
                    final LocalDate FechaHoy = LocalDate.now();
                    final String fechahoy = FechaHoy.minusDays(2L).toString();
                    final String[] fech = fechahoy.split("-");
                    final String fechaformato = String.valueOf(fech[2]) + "/" + fech[1] + "/" + fech[0];
                    foo.put("fechaEmision", fechaformato);
                    foo.put("documentos", "0");
                    lstData.add(foo);
                    final List<Map<String, Object>> ventas = (List<Map<String, Object>>)facturacionservice.SQL_SELECT_VENTA_ENVIO_FACTURADOR_AUTOMATICO(facturacion.getEmpresa(), Utils.FormatoFecha(lstData.get(0).get("fechaEmision").toString()), Integer.valueOf(Integer.parseInt(lstData.get(0).get("documentos").toString())), 0, 0);
                    for (final Map<String, Object> map : ventas) {
                        final B_FacturacionBean documentos = new B_FacturacionBean();
                        documentos.setId(Integer.valueOf(Integer.parseInt(map.get("Nro").toString())));
                        documentos.setAgencia(map.get("AgenciaD").toString());
                        documentos.setDocumentoElectronico(map.get("DocumentoElectronico").toString());
                        documentos.setEmpresa(map.get("Empresa").toString());
                        documentos.setEstado("S");
                        documentos.setEnviar("S");
                        documentos.setFechaEmision(map.get("FechaEmisionD").toString());
                        documentos.setObservaciones("");
                        documentos.setServicio(map.get("Servicio").toString());
                        documentos.setReferencia(map.get("NroOrdenRef").toString());
                        documentos.setTodos(true);
                        listaEnviar.add(documentos);
                    }
                    for (final B_FacturacionBean data : listaEnviar) {
                        mapaFacturacion = (Map<String, Object>)facturacionservice.SQL_SELECT_VENTA_FACTURADOR(data.getId(), data.getServicio());
                        if (mapaFacturacion.get("Servicio").toString().equals("N")) {
                            GeneraDocumentoFe.GeneraDocumentoNotaCreditoXMLUBL21((Map)mapaFacturacion, facturacion);
                        }
                        else if (mapaFacturacion.get("Servicio").toString().equals("T")) {
                            GeneraDocumentoFe.GeneraDocumentoNotaDebitoXMLUBL21((Map)mapaFacturacion, facturacion);
                        }
                        else if (mapaFacturacion.get("Servicio").toString().equals("B") || mapaFacturacion.get("Servicio").toString().equals("E") || mapaFacturacion.get("Servicio").toString().equals("C")) {
                            if (mapaFacturacion.get("Bus_Carga") == null) {
                                GeneraDocumentoFe.GeneraDocumentoFacturaXMLPruebaUBL21((Map)mapaFacturacion, facturacion);
                            }
                            if (mapaFacturacion.get("Bus_Carga").toString().equals("VI")) {
                                System.out.println("BUS CARGA :  " + mapaFacturacion.get("Bus_Carga").toString());
                                GeneraDocumentoFe.GeneraDocumentoFacturaXMLPruebaUBL21ViajeEspecial((Map)mapaFacturacion, facturacion);
                            }
                            else {
                                GeneraDocumentoFe.GeneraDocumentoFacturaXMLPruebaUBL21((Map)mapaFacturacion, facturacion);
                            }
                        }
                        try {
                            final FileDataSource dataSource = new FileDataSource(String.valueOf(facturacion.getRutaEnvioSunat()) + mapaFacturacion.get("DocumentoZip").toString());
                            final DataHandler dataHandler = new DataHandler((DataSource)dataSource);
                            Utils.USERNAME = facturacion.getUsernameSunat();
                            Utils.PASSWORD = facturacion.getPasswordSunat();
                            final BillServicePortService service = (BillServicePortService)new BillServicePortServiceLocator();
                            final BillServicePort billService = service.getBillServicePortSoap11();
                            respuestaSunat = billService.sendBill(mapaFacturacion.get("DocumentoZip").toString(), toBytes(dataHandler), "");
                            final FileOutputStream fos = new FileOutputStream(String.valueOf(facturacion.getRutaRespuestaSunat()) + "R-" + mapaFacturacion.get("DocumentoZip").toString());
                            fos.write(respuestaSunat);
                            fos.close();
                            final ZipInputStream zisEnvio = new ZipInputStream(new FileInputStream(String.valueOf(facturacion.getRutaRespuestaSunat()) + "R-" + mapaFacturacion.get("DocumentoZip").toString()));
                            ZipEntry entrada;
                            while ((entrada = zisEnvio.getNextEntry()) != null) {
                                final byte[] buf = new byte[1024];
                                final StringBuffer contentXml = new StringBuffer();
                                if (!entrada.isDirectory()) {
                                    int len;
                                    while ((len = zisEnvio.read(buf)) > 0) {
                                        contentXml.append(new String(buf, 0, len, "UTF-8"));
                                    }
                                    final DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                                    final InputSource inputSource = new InputSource();
                                    inputSource.setCharacterStream(new StringReader(contentXml.toString()));
                                    final Document doc = documentBuilder.parse(inputSource);
                                    final NodeList nodeListhash = doc.getElementsByTagName("cbc:ResponseCode");
                                    final NodeList nodeListdescripcion = doc.getElementsByTagName("cbc:Description");
                                    final Node nodohash = nodeListhash.item(0);
                                    final Node nodohashDesc = nodeListdescripcion.item(0);
                                    EliminaVentasNoConfirmadas.log.info((Object)"Enviando a SUNAT");
                                    if (!(nodohash instanceof Element) || !(nodohashDesc instanceof Element)) {
                                        continue;
                                    }
                                    if (nodohash.getTextContent().trim().equals("0")) {
                                        facturacionservice.SQL_UPDATE_VENTA_FACTURADOR(Integer.valueOf(Integer.parseInt(mapaFacturacion.get("Nro").toString())), mapaFacturacion.get("Servicio").toString(), Integer.valueOf(1), nodohashDesc.getTextContent().trim());
                                        EliminaVentasNoConfirmadas.log.info((Object)"Actualizando Estado a Enviado");
                                    }
                                    else {
                                        facturacionservice.SQL_UPDATE_VENTA_FACTURADOR(Integer.valueOf(Integer.parseInt(mapaFacturacion.get("Nro").toString())), mapaFacturacion.get("Servicio").toString(), Integer.valueOf(2), nodohashDesc.getTextContent().trim());
                                    }
                                }
                            }
                            zisEnvio.close();
                            mapaEnvio.put("respuesta", true);
                            mapaEnvio.put("mensaje", "Los documentos fueron enviados correctamente");
                            lstResultado.add(mapaEnvio);
                        }
                        catch (IOException io) {
                            System.out.println("EXCEPTION --- IOException " + mapaFacturacion.get("DocumentoElectronico").toString() + " Servicio : " + mapaFacturacion.get("ServicioD").toString() + " " + io.getMessage().substring(0, io.getMessage().length()));
                            final String code = io.getMessage().substring(0, io.getMessage().length());
                            System.out.println("EXCEPTION --- IOException2 " + io.getMessage().substring(0, io.getMessage().length()));
                            System.out.println("EXCEPTION --- IOException3 " + code);
                            mapaEnvio.put("respuesta", false);
                            mapaEnvio.put("documentoElectronico", mapaFacturacion.get("DocumentoElectronico").toString());
                            mapaEnvio.put("mensaje", "Ocurri\u00f3 un error al enviar los documentos:\nDocumentos: " + mapaFacturacion.get("DocumentoElectronico").toString() + "\n" + Utils.printStackTraceToString((Exception)io));
                            lstResultado.add(mapaEnvio);
                            if (code.equals("1033")) {
                                facturacionservice.SQL_UPDATE_VENTA_FACTURADOR(Integer.valueOf(Integer.parseInt(mapaFacturacion.get("Nro").toString())), mapaFacturacion.get("Servicio").toString(), Integer.valueOf(1), "1033");
                            }
                            else {
                                facturacionservice.SQL_UPDATE_VENTA_FACTURADOR(Integer.valueOf(Integer.parseInt(mapaFacturacion.get("Nro").toString())), mapaFacturacion.get("Servicio").toString(), Integer.valueOf(2), code);
                            }
                        }
                        catch (ParserConfigurationException pa) {
                            System.out.println("EXCEPTION --- ParserConfigurationException " + mapaFacturacion.get("DocumentoElectronico").toString() + " Servicio : " + mapaFacturacion.get("ServicioD").toString() + " " + pa.getMessage().substring(0, pa.getMessage().length()));
                            mapaEnvio.put("respuesta", false);
                            mapaEnvio.put("documentoElectronico", mapaFacturacion.get("DocumentoElectronico").toString());
                            mapaEnvio.put("mensaje", "Ocurri\u00f3 un error al enviar los documentos:\nDocumentos: " + mapaFacturacion.get("DocumentoElectronico").toString() + "\n" + Utils.printStackTraceToString((Exception)pa));
                            lstResultado.add(mapaEnvio);
                            facturacionservice.SQL_UPDATE_VENTA_FACTURADOR(Integer.valueOf(Integer.parseInt(mapaFacturacion.get("Nro").toString())), mapaFacturacion.get("Servicio").toString(), Integer.valueOf(2), "Error al Enviar");
                        }
                        catch (SAXException sa) {
                            System.out.println("EXCEPTION --- SAXException" + mapaFacturacion.get("DocumentoElectronico").toString() + " Servicio : " + mapaFacturacion.get("ServicioD").toString() + " " + sa.getMessage().substring(0, sa.getMessage().length()));
                            mapaEnvio.put("respuesta", false);
                            mapaEnvio.put("documentoElectronico", mapaFacturacion.get("DocumentoElectronico").toString());
                            mapaEnvio.put("mensaje", "Ocurri\u00f3 un error al enviar los documentos:\nDocumentos: " + mapaFacturacion.get("DocumentoElectronico").toString() + "\n" + Utils.printStackTraceToString((Exception)sa));
                            lstResultado.add(mapaEnvio);
                            facturacionservice.SQL_UPDATE_VENTA_FACTURADOR(Integer.valueOf(Integer.parseInt(mapaFacturacion.get("Nro").toString())), mapaFacturacion.get("Servicio").toString(), Integer.valueOf(2), "Error al Enviar 2 ");
                        }
                        catch (ServerSOAPFaultException se) {
                            System.out.println("EXCEPTION --- ServerSOAPFaultException " + mapaFacturacion.get("DocumentoElectronico").toString() + " Servicio : " + mapaFacturacion.get("ServicioD").toString() + " " + se.getMessage().substring(0, se.getMessage().length()));
                            mapaEnvio.put("respuesta", false);
                            mapaEnvio.put("documentoElectronico", mapaFacturacion.get("DocumentoElectronico").toString());
                            mapaEnvio.put("mensaje", "Ocurri\u00f3 un error al enviar los documentos: \nDocumentos: " + mapaFacturacion.get("DocumentoElectronico").toString() + "\n" + "Mensaje : " + Utils.printStackTraceToString((Exception)se));
                            lstResultado.add(mapaEnvio);
                            facturacionservice.SQL_UPDATE_VENTA_FACTURADOR(Integer.valueOf(Integer.parseInt(mapaFacturacion.get("Nro").toString())), mapaFacturacion.get("Servicio").toString(), Integer.valueOf(2), "Error al Enviar 3 ");
                        }
                        catch (SOAPFaultException so) {
                            System.out.println("EXCEPTION --- SOAPFaultException " + mapaFacturacion.get("DocumentoElectronico").toString() + " Servicio : " + mapaFacturacion.get("ServicioD").toString() + " " + so.getMessage().substring(0, so.getMessage().length()));
                            mapaEnvio.put("respuesta", false);
                            mapaEnvio.put("documentoElectronico", mapaFacturacion.get("DocumentoElectronico").toString());
                            mapaEnvio.put("mensaje", "Ocurri\u00f3 un error al enviar los documentos : \nDocumentos: " + mapaFacturacion.get("DocumentoElectronico").toString() + "\n" + "Codigo Error: " + so.getMessage().substring(so.getMessage().lastIndexOf("INFO:") + 5, so.getMessage().lastIndexOf("INFO:") + 11).trim() + "\n" + "Observaciones: " + so.getMessage().substring(so.getMessage().lastIndexOf("server:") + 7, so.getMessage().lastIndexOf("-")).trim() + " \n" + "Mensaje : " + so.getMessage().substring(so.getMessage().lastIndexOf("INFO:") + 5, so.getMessage().lastIndexOf("INFO:") + 11).trim());
                            lstResultado.add(mapaEnvio);
                            facturacionservice.SQL_UPDATE_VENTA_FACTURADOR(Integer.valueOf(Integer.parseInt(mapaFacturacion.get("Nro").toString())), mapaFacturacion.get("Servicio").toString(), Integer.valueOf(2), "Error al Enviar 4");
                        }
                    }
                }
                catch (Exception ex) {
                    System.out.println("PADRE DE LA EXCEPTION ERROR " + mapaFacturacion.get("DocumentoElectronico").toString() + " Servicio : " + mapaFacturacion.get("ServicioD").toString() + " " + ex.getMessage().substring(0, ex.getMessage().length()));
                    mapaEnvio.put("respuesta", false);
                    mapaEnvio.put("documentoElectronico", mapaFacturacion.get("DocumentoElectronico").toString());
                    mapaEnvio.put("mensaje", "Ocurri\u00f3 un error al enviar los documentos: \nDocumentos: " + mapaFacturacion.get("DocumentoElectronico").toString() + "\n" + "Mensaje : " + Utils.printStackTraceToString(ex));
                    lstResultado.add(mapaEnvio);
                    facturacionservice.SQL_UPDATE_VENTA_FACTURADOR(Integer.valueOf(Integer.parseInt(mapaFacturacion.get("Nro").toString())), mapaFacturacion.get("Servicio").toString(), Integer.valueOf(2), "Error al Enviar 5");
                }
            }
        }
        log.info("[FIN]======= ENVIO AUTOMÁTICO DE DOCUMENTOS ELECTRÓNICOS A LAS 11:00 PM=======");
    }
    
    public static byte[] toBytes(final DataHandler handler) throws IOException {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        handler.writeTo((OutputStream)output);
        return output.toByteArray();
    }
    
    @Scheduled(cron = "0 0 22 * * *")
    public void GenerarCodigoPDF417() {
    	log.info("[INICIO]======= REPORTE A CONTABILIDAD DOCUMETNOS SIN PDF417 =======");
        if (Utils.isProduccion()) {
            final List<String> numeros = (List<String>)this.reportecontabilidadservice.GetDocumentosSinPDF417();
            for (final String temp : numeros) {
                Utils.consumirservicio(temp, "B");
            }
        }
        log.info("[FIN]======= REPORTE A CONTABILIDAD DOCUMETNOS SIN PDF417 =======");
    }
    
    public Date sumarRestarDiasFecha(final Date fecha, final int dias) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(6, dias);
        return calendar.getTime();
    }
    
    public void activomanualCodigoCIP(final String CodigoCIP) throws Exception {
        try {
            int operacion = -1;
            EliminaVentasNoConfirmadas.log.info((Object)("VENTAS EXPIRADAS POR VERIFICAR PAGOEFECTIVO CIP -->  " + CodigoCIP));
            final Map<String, String> resultados = new HashMap<String, String>();
            resultados.put("estado", "593");
            EliminaVentasNoConfirmadas.log.info((Object)("ESTADO SERVICIO PAGOEFECTIVO  --> ESTADO : " + resultados.get("estado") + " CIP : " + CodigoCIP));
            if (resultados.get("estado").trim().equals("593")) {
                final List<B_VentaBean> ventaPagada = new ArrayList<B_VentaBean>();
                final VentaInterface ventadatos = (VentaInterface)new VentaInterfaceI();
                for (final B_VentaBean bean : ventaPagada) {
                    B_Correlativos correlativos = new B_Correlativos();
                    correlativos = this.servicecorrelativo.generaCorrelativo();
                    if (correlativos != null) {
                        B_CuentaCorrienteBean cuentacorriente = new B_CuentaCorrienteBean();
                        operacion = this.venta.updateVentaPagoEfectivoVerificaConfirmacion(bean.getNro(), bean.getSalida(), "C", 2, Integer.parseInt(resultados.get("estado").trim()));
                        if (operacion != -1) {
                            B_CuentaCorrienteBean beanverifica = new B_CuentaCorrienteBean();
                            beanverifica = this.servicecuentacorriente.ListaVentaPagoEfectivoCuentaCorriente(bean.getNro());
                            if (beanverifica == null) {
                                cuentacorriente = ventadatos.DatosEstaticosCuentaCorriente(cuentacorriente);
                                cuentacorriente = ventadatos.DatosDinamicosCuentaCorrientePagoEfectivo(bean, cuentacorriente, correlativos);
                                operacion = this.servicecuentacorriente.insertCuentaCorriente(cuentacorriente);
                            }
                            else {
                                EliminaVentasNoConfirmadas.log.info((Object)("VENTA EXPIRADA YA EXISTE EN LA CUENTACORRIENTE  CIP --> " + bean.getEticket()));
                            }
                            if (operacion != -1) {
                                EliminaVentasNoConfirmadas.log.info((Object)("PROCESO DE TRANSACCION DE VENTA EXPIRADA TERMINADA CON EXITO  CIP --> " + bean.getEticket()));
                            }
                            else {
                                EliminaVentasNoConfirmadas.log.info((Object)("ERROR AL ELIMINAR EN LA CUENTA CORRIENTE CIP--> " + bean.getEticket()));
                            }
                        }
                        else {
                            EliminaVentasNoConfirmadas.log.info((Object)("ERROR AL ACTUALIZAR EN LA VENTA EXPIRADA  CIP--> " + bean.getEticket()));
                        }
                    }
                    else {
                        EliminaVentasNoConfirmadas.log.info((Object)("ERROR AL GENERAR CORRELATIVOS CIP--> " + bean.getEticket()));
                    }
                }
                List<B_VentaBean> ventaPagoEfectivo = new ArrayList<B_VentaBean>();
                ventaPagoEfectivo = (List<B_VentaBean>)this.venta.getVentaImprimirPagoEfectivo(CodigoCIP);
                GenerarEmail.enviarCorreoAdjunto(GenerarEmail.parametros("sjvh0611@gmail.com", ventaPagoEfectivo.get(0).getEticketVisa()), Utils.BaosPagoEfectivo((List)ventaPagoEfectivo));
            }
        }
        catch (Exception e) {
            EliminaVentasNoConfirmadas.log.info((Object)"****************** ENTRO AL TRY CATCH DE  LAS VENTAS QUE EST\u00c1N FUERA DE HORA DE PAGOEFECTIVO ******************");
            EliminaVentasNoConfirmadas.log.info((Object)Utils.printStackTraceToString(e));
        }
    }
    
    @Scheduled(fixedRate = 300000L)
    public void ReconfirmarCIPs() throws Exception { 
    	log.info("[INICIO]======= RECONFIRMANDO CIPS  ¿===> 593? =======");
    	if(Utils.isProduccion()) {
        final List<String> cips = (List<String>)this.boletajecipservice.getEticketsPorConfirmar();
        for (final String cip : cips) { 
            final Map<String, Object> res = this.consultapagoefectivoVentaTelefonica(cip);
            if (res.get("estado").toString().equals("593")) {
                final List<B_VentaBean> ventasPagoEfectivo = (List<B_VentaBean>)this.serviceventas.SelectVentasPagoEfectivoReconfirmar(cip);
                if (ventasPagoEfectivo.isEmpty()) {
                    continue;
                }
                this.activomanualCodigoCIP(cip, ventasPagoEfectivo.get(0).getUsuarioVisa()); 
            }
        }
    	}
    	log.info("[FIN]======= RECONFIRMANDO CIPS  ¿===> 593? =======");
    }
    
    public void activomanualCodigoCIP(final String CodigoCIP, final String Correo) throws Exception {
        try {
            int operacion = -1;
            final Map<String, String> resultados = new HashMap<String, String>();
            resultados.put("estado", "593");
            EliminaVentasNoConfirmadas.log.info((Object)("ESTADO SERVICIO PAGOEFECTIVO  --> ESTADO : " + resultados.get("estado") + " CIP : " + CodigoCIP));
            if (resultados.get("estado").trim().equals("593")) {
                List<B_VentaBean> ventaPagada = new ArrayList<B_VentaBean>();
                ventaPagada = (List<B_VentaBean>)this.venta.SelectListVentaPagoEfectivo(CodigoCIP);
                final VentaInterface ventadatos = (VentaInterface)new VentaInterfaceI();
                for (final B_VentaBean bean : ventaPagada) {
                    B_Correlativos correlativos = new B_Correlativos();
                    correlativos = this.servicecorrelativo.generaCorrelativo();
                    if (correlativos != null) {
                        B_CuentaCorrienteBean cuentacorriente = new B_CuentaCorrienteBean();
                        operacion = this.venta.updateVentaPagoEfectivoVerificaConfirmacion(bean.getNro(), bean.getSalida(), "C", 2, Integer.parseInt(resultados.get("estado").trim()));
                        if (operacion != -1) {
                            B_CuentaCorrienteBean beanverifica = new B_CuentaCorrienteBean();
                            beanverifica = this.servicecuentacorriente.ListaVentaPagoEfectivoCuentaCorriente(bean.getNro());
                            if (beanverifica == null) {
                                cuentacorriente = ventadatos.DatosEstaticosCuentaCorriente(cuentacorriente);
                                cuentacorriente = ventadatos.DatosDinamicosCuentaCorrientePagoEfectivo(bean, cuentacorriente, correlativos);
                                operacion = this.servicecuentacorriente.insertCuentaCorriente(cuentacorriente);
                            }
                            else {
                                EliminaVentasNoConfirmadas.log.info((Object)("VENTA EXPIRADA YA EXISTE EN LA CUENTACORRIENTE  CIP --> " + bean.getEticket()));
                            }
                            if (operacion != -1) {
                                EliminaVentasNoConfirmadas.log.info((Object)("PROCESO DE TRANSACCION DE VENTA EXPIRADA TERMINADA CON EXITO  CIP --> " + bean.getEticket()));
                            }
                            else {
                                EliminaVentasNoConfirmadas.log.info((Object)("ERROR AL ELIMINAR EN LA CUENTA CORRIENTE CIP--> " + bean.getEticket()));
                            }
                        }
                        else {
                            EliminaVentasNoConfirmadas.log.info((Object)("ERROR AL ACTUALIZAR EN LA VENTA EXPIRADA  CIP--> " + bean.getEticket()));
                        }
                    }
                    else {
                        EliminaVentasNoConfirmadas.log.info((Object)("ERROR AL GENERAR CORRELATIVOS CIP--> " + bean.getEticket()));
                    }
                }
                List<B_VentaBean> ventaPagoEfectivo = new ArrayList<B_VentaBean>();
                ventaPagoEfectivo = (List<B_VentaBean>)this.venta.getVentaImprimirPagoEfectivo(CodigoCIP);
                for (final B_VentaBean item : ventaPagoEfectivo) {
                    final String empresaD = this.CodigoDeEmpresaCondicionado(item.getNroDestino(), new StringBuilder().append(item.getNroServicio()).toString());
                    item.setEmpresa(empresaD);
                    this.facturacionservice.USP_GENERARBOLETO(new StringBuilder(String.valueOf(item.getNro())).toString(), "4127", (item.getRuc() == null) ? 16 : 17, empresaD);
                    this.consumirservicio(new StringBuilder().append(item.getNro()).toString(), "B");
                }
                GenerarEmail.enviarCorreoAdjunto(GenerarEmail.parametros(Correo, ventaPagoEfectivo.get(0).getEticketVisa()), this.DescargaPDFTicket(ventaPagoEfectivo));
            }
        }
        catch (Exception e) {
            EliminaVentasNoConfirmadas.log.info((Object)"****************** ENTRO AL TRY CATCH DE  LAS VENTAS QUE EST\u00c1N FUERA DE HORA DE PAGOEFECTIVO ******************");
            EliminaVentasNoConfirmadas.log.info((Object)Utils.printStackTraceToString(e));
        }
    }
    
    public static Map<String, String> ConsultaPagoEfectivo(final String eticketPagoefectivo) {
        NodeList nlSolpago = null;
        final Map<String, String> resultados = new HashMap<String, String>();
        try {
            PagoEfectivo.CodServ = "PAL";
            PagoEfectivo.PublicPath = "/var/www/ventas/pagoefectivo/keys/produccion/SPE_PublicKey.1pz";
            PagoEfectivo.PrivatePath = "/var/www/ventas/pagoefectivo/keys/produccion/PAL_PrivateKey.1pz";
            PagoEfectivo.UrlPECriptography = "https://cip.pagoefectivo.pe/PagoEfectivoWSCrypto/WSCrypto.asmx";
            PagoEfectivo.UrlPEService = "https://cip.pagoefectivo.pe/PagoEfectivoWSGeneralv2/Service.asmx";
            EliminaVentasNoConfirmadas.log.info((Object)"********************************************************************************************");
            EliminaVentasNoConfirmadas.log.info((Object)("INGRESANDO AL PROCESO DE CONSULTA DE PAGOEFECTIVO -->" + PagoEfectivo.PrivatePath));
            final BEWSConsultarCIPRequestMod1 requestPagoEfectivo = new BEWSConsultarCIPRequestMod1();
            requestPagoEfectivo.setCodServ(PagoEfectivo.CodServ);
            EliminaVentasNoConfirmadas.log.info((Object)("TICKET" + eticketPagoefectivo));
            requestPagoEfectivo.setCIPS(eticketPagoefectivo);
            final BEWSConsultarCIPResponseMod1 responsePagoEfectivo = PagoEfectivo.ConsultarCIP(requestPagoEfectivo);
            final String XmlResponse = responsePagoEfectivo.getXML();
            EliminaVentasNoConfirmadas.log.info((Object)("RESPUESTA XML" + XmlResponse));
            final DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            final Document doc = db.parse(new ByteArrayInputStream(XmlResponse.getBytes("UTF-8")));
            nlSolpago = doc.getElementsByTagName("ConfirSolPago");
            if (nlSolpago.getLength() > 0) {
                
            	final NodeList nliDResSolPago = ((Element)nlSolpago.item(0)).getElementsByTagName("NumeroOrdenPago");
                final NodeList nliDResEstado = ((Element)nlSolpago.item(0)).getElementsByTagName("Estado");
                final NodeList nliDResTotal = ((Element)nlSolpago.item(0)).getElementsByTagName("Total");
                final NodeList nliDResEmitido = ((Element)nlSolpago.item(0)).getElementsByTagName("FechaEmision");
                final NodeList nliDResExpirado = ((Element)nlSolpago.item(0)).getElementsByTagName("FechaExpirado");
                final NodeList nliDResExtornado = ((Element)nlSolpago.item(0)).getElementsByTagName("FechaAnulado");
                final NodeList nliDResCancelado = ((Element)nlSolpago.item(0)).getElementsByTagName("FechaCancelado");
                final NodeList nliDResNombreUsuario = ((Element)nlSolpago.item(0)).getElementsByTagName("UsuarioNombre");
                final NodeList nliDResApellidoUsuario = ((Element)nlSolpago.item(0)).getElementsByTagName("UsuarioApellidos");
                final NodeList nliDResConceptoPago = ((Element)nlSolpago.item(0)).getElementsByTagName("ConceptoPago");
                final NodeList nliDResUsuarioEmail = ((Element)nlSolpago.item(0)).getElementsByTagName("UsuarioEmail");
                
                resultados.put("nroCIP", nliDResSolPago.item(0).getTextContent().trim());
                resultados.put("estado", nliDResEstado.item(0).getTextContent().trim());
                resultados.put("monto", nliDResTotal.item(0).getTextContent().trim());
                resultados.put("fechaEmision", nliDResEmitido.item(0).getTextContent().trim().equals("") ? "" : (String.valueOf(Utils.FormatoFechaReporte(nliDResEmitido.item(0).getTextContent().trim().substring(0, 10))) + " " + nliDResEmitido.item(0).getTextContent().trim().substring(10, 19)));
                resultados.put("fechaExpirado", nliDResExpirado.item(0).getTextContent().trim().equals("") ? "" : (String.valueOf(Utils.FormatoFechaReporte(nliDResExpirado.item(0).getTextContent().trim().substring(0, 10))) + " " + nliDResExpirado.item(0).getTextContent().trim().substring(10, 19)));
                resultados.put("fechaExtornado", nliDResExtornado.item(0).getTextContent().trim().equals("") ? "" : (String.valueOf(Utils.FormatoFechaReporte(nliDResExtornado.item(0).getTextContent().trim().substring(0, 10))) + " " + nliDResExtornado.item(0).getTextContent().trim().substring(10, 19)));
                resultados.put("fechaCancelado", nliDResCancelado.item(0).getTextContent().trim().equals("") ? "" : (String.valueOf(Utils.FormatoFechaReporte(nliDResCancelado.item(0).getTextContent().trim().substring(0, 10))) + " " + nliDResCancelado.item(0).getTextContent().trim().substring(10, 19)));
                resultados.put("usuario", String.valueOf(nliDResNombreUsuario.item(0).getTextContent().trim()) + " " + nliDResApellidoUsuario.item(0).getTextContent().trim());
                resultados.put("conceptoPago", nliDResConceptoPago.item(0).getTextContent().trim());
                resultados.put("usuarioEmail", nliDResUsuarioEmail.item(0).getTextContent().trim());
            }
        }
        catch (Exception e) {
            EliminaVentasNoConfirmadas.log.info((Object)Utils.printStackTraceToString(e));
        }
        return resultados;
    }
    
    public Map<String, Object> consultapagoefectivoVentaTelefonica(final String eticketPagoefectivo) {
    	
    		
        final Map<String, Object> rsp = new HashMap<String, Object>();
        try {
            final Map<String, String> rowsPagoefectivo = ConsultaPagoEfectivo(eticketPagoefectivo);
            if (rowsPagoefectivo.isEmpty()) {
                rsp.put("estado", "000");
                rsp.put("msgserver", "NO ENCONTRADO");
                rsp.put("mensaje", "Nro de CIP no Encontrado");
            }
            else {
                final String estadoCIP = rowsPagoefectivo.get("estado");
                if (estadoCIP.equals("593")) {
                    rsp.put("cip", rowsPagoefectivo.get("nroCIP"));
                    rsp.put("estado", estadoCIP);
                }
                else {
                    rsp.put("estado", estadoCIP);
                    rsp.put("msgserver", "SIN REGISTRO DE PAGO");
                    rsp.put("mensaje", "no podemos mostrarle los boletos relacionados");
                }
            }
        }
        catch (Exception e) {
            EliminaVentasNoConfirmadas.log.info((Object)Utils.printStackTraceToString(e));
        }
        return rsp;
    	 
    }
    
    public ByteArrayOutputStream DescargaPDFTicket(final List<B_VentaBean> ventaVisa) {
        final Rectangle tama\u00f1o = new Rectangle(250.0f, 800.0f);
        final com.itextpdf.text.Document documentoPDF = new com.itextpdf.text.Document();
        documentoPDF.setPageSize(tama\u00f1o);
        documentoPDF.setMargins(0.0f, 0.5f, 0.0f, 0.5f);
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            if (ventaVisa.size() > 0) {
                PdfWriter.getInstance(documentoPDF, (OutputStream)baos);
                documentoPDF.open();
            }
            int contador = 0;
            for (final B_VentaBean item : ventaVisa) {
                final String id = new StringBuilder().append(item.getNro()).toString();
                final String em = item.getEmpresa();
                final V_Varios_FacturacionBean facturacionEmpresa = this.variosservice.SQL_SELECT_LISTA_PARAMETROS_DESCARGA_FACTURADOR(em);
                InputStream rutaLogoPalomino;
                if (em.equals("002")) {
                    rutaLogoPalomino = new URL("https://ventas.grupopalomino.com.pe:8443/ventas/_lib/img/palomino.jpg").openStream();
                }
                else {
                    rutaLogoPalomino = new URL("https://ventas.grupopalomino.com.pe:8443/ventas/_lib/img/Transporte_Wari.png").openStream();
                }
                final Image logoPalomino = Image.getInstance(IOUtils.toByteArray(rutaLogoPalomino));
                logoPalomino.scalePercent(12.0f);
                logoPalomino.setAlignment(0);
                documentoPDF.add((com.itextpdf.text.Element)logoPalomino);
                PdfPTable table = new PdfPTable(4);
                table.setWidthPercentage(100.0f);
                table.setHorizontalAlignment(0);
                Map<String, Object> mapaVentas = new HashMap<String, Object>();
                V_Ventas_FacturacionBean ventaImages = new V_Ventas_FacturacionBean();
                mapaVentas = (Map<String, Object>)this.facturacionservice.SQL_SELECT_VENTA_DESCARGA_X_NRO_FACTURADOR(Integer.valueOf(Integer.parseInt(id)), "B");
                ventaImages = this.ventasfacturacionservice.SQL_SELECT_VENTAS_IMAGES_FACTURACION(Integer.valueOf(Integer.parseInt(mapaVentas.get("Nro").toString())), "B");
                table = FuncionesFacturacionPDF.F_Facturacion_Electronica_Pasajes_Ticket(table, facturacionEmpresa, (Map)mapaVentas, ventaImages);
                documentoPDF.add((com.itextpdf.text.Element)table);
                if (++contador != ventaVisa.size()) {
                    documentoPDF.newPage();
                }
            }
            documentoPDF.close();
            baos.flush();
            baos.close();
        }
        catch (Exception e) {
            EliminaVentasNoConfirmadas.log.info((Object)Utils.printStackTraceToString(e));
        }
        return baos;
    }
    
    public void consumirservicio(final String nro, final String tipo) {
        try {
            final URL url = new URL(String.valueOf(EliminaVentasNoConfirmadas.URL_BASE) + "?nro=" + nro + "&tipoOperacion=" + tipo);
            System.out.println(EliminaVentasNoConfirmadas.URL_BASE);
            System.out.println(String.valueOf(EliminaVentasNoConfirmadas.URL_BASE) + "?nro=" + nro + "&tipoOperacion=" + tipo);
            final HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
            final BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuilder respuesta = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                respuesta.append(output);
            }
            System.out.println(respuesta);
            conn.disconnect();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }
    
    private String CodigoDeEmpresaCondicionado(final String nroruta, final String servicioCodigo) {
        String respuesta = "002";
        if (servicioCodigo.equals("02")) {
            respuesta = "004";
        }
        else if (servicioCodigo.equals("09")) {
            respuesta = "002";
        }
        else {
            final List<String> rutaswari = new ArrayList<String>();
            rutaswari.add("4");
            rutaswari.add("10");
            rutaswari.add("5");
            rutaswari.add("15");
            rutaswari.add("20");
            rutaswari.add("21");
            for (final String nroRuta : rutaswari) {
                if (nroruta.equals(nroRuta)) {
                    respuesta = "004";
                    break;
                }
            }
        }
        return respuesta;
    }
    
    @Scheduled(fixedRate = 300000L)
    public void eliminaVentasNoConfirmadas() throws Exception {
    	log.info("[INICIO]======= ELIMINANDO VENTAS NO CONFIRMADAS =======");
    	if(Utils.isProduccion()) {
            List<B_VentaBean> ventasPagoEfectivo = new ArrayList<B_VentaBean>();
            try {
                ventasPagoEfectivo = (List<B_VentaBean>)this.venta.SelectListVentasPendientesPagoEfectivo();
                if (ventasPagoEfectivo.size() > 0) {
                    int operacion = -1;
                    for (final B_VentaBean pago : ventasPagoEfectivo) {
                        EliminaVentasNoConfirmadas.log.info((Object)("VENTAS EXPIRADAS POR VERIFICAR PAGOEFECTIVO CIP -->  " + pago.getEticket()));
                        Map<String, String> resultados = new HashMap<String, String>();
                        resultados = (Map<String, String>)ConsultasAction.ConsultaPagoEfectivo(pago.getEticket());
                        EliminaVentasNoConfirmadas.log.info((Object)("ESTADO SERVICIO PAGOEFECTIVO  --> ESTADO : " + resultados.get("estado") + " CIP : " + pago.getEticket()));
                        if (resultados.get("estado").trim().equals("593")) {
                            List<B_VentaBean> ventaPagada = new ArrayList<B_VentaBean>();
                            ventaPagada = (List<B_VentaBean>)this.venta.SelectListVentaPagoEfectivo(pago.getEticket());
                            final VentaInterface ventadatos = (VentaInterface)new VentaInterfaceI();
                            for (final B_VentaBean bean : ventaPagada) {
                                B_Correlativos correlativos = new B_Correlativos();
                                correlativos = this.servicecorrelativo.generaCorrelativo();
                                if (correlativos != null) {
                                    B_CuentaCorrienteBean cuentacorriente = new B_CuentaCorrienteBean();
                                    operacion = this.venta.updateVentaPagoEfectivoVerificaConfirmacion(bean.getNro(), bean.getSalida(), "C", 2, Integer.parseInt(resultados.get("estado").trim()));
                                    if (operacion != -1) {
                                        B_CuentaCorrienteBean beanverifica = new B_CuentaCorrienteBean();
                                        beanverifica = this.servicecuentacorriente.ListaVentaPagoEfectivoCuentaCorriente(bean.getNro());
                                        if (beanverifica == null) {
                                            cuentacorriente = ventadatos.DatosEstaticosCuentaCorriente(cuentacorriente);
                                            cuentacorriente = ventadatos.DatosDinamicosCuentaCorrientePagoEfectivo(bean, cuentacorriente, correlativos);
                                            operacion = this.servicecuentacorriente.insertCuentaCorriente(cuentacorriente);
                                        }
                                        else {
                                            EliminaVentasNoConfirmadas.log.info((Object)("VENTA EXPIRADA YA EXISTE EN LA CUENTACORRIENTE  CIP --> " + bean.getEticket()));
                                        }
                                        if (operacion != -1) {
                                            EliminaVentasNoConfirmadas.log.info((Object)("PROCESO DE TRANSACCION DE VENTA EXPIRADA TERMINADA CON EXITO  CIP --> " + bean.getEticket()));
                                        }
                                        else {
                                            EliminaVentasNoConfirmadas.log.info((Object)("ERROR AL ELIMINAR EN LA CUENTA CORRIENTE CIP--> " + bean.getEticket()));
                                        }
                                    }
                                    else {
                                        EliminaVentasNoConfirmadas.log.info((Object)("ERROR AL ACTUALIZAR EN LA VENTA EXPIRADA  CIP--> " + bean.getEticket()));
                                    }
                                }
                                else {
                                    EliminaVentasNoConfirmadas.log.info((Object)("ERROR AL GENERAR CORRELATIVOS CIP--> " + bean.getEticket()));
                                }
                            }
                            List<B_VentaBean> ventaPagoEfectivo = new ArrayList<B_VentaBean>();
                            ventaPagoEfectivo = (List<B_VentaBean>)this.venta.getVentaImprimirPagoEfectivo(pago.getEticket());
                            EliminaVentasNoConfirmadas.log.info((Object)("VENTA EXPIRADA Y CONFIRMADA PAGOEFECTIVO CIP --> " + pago.getEticket()));
                            GenerarEmail.enviarCorreoAdjunto(GenerarEmail.parametros(resultados.get("usuarioEmail").toString(), ventaPagoEfectivo.get(0).getEticketVisa()), Utils.BaosPagoEfectivo((List)ventaPagoEfectivo));
                            EliminaVentasNoConfirmadas.log.info((Object)("VOUCHER ENVIADO POR CORREO VENTA EXPIRADA Y CONFIRMADA PAGOEFECTIVO CIP --> " + pago.getEticket()));
                        }
                    }
                }
            }
            catch (Exception e) {
                EliminaVentasNoConfirmadas.log.info((Object)"****************** ENTRO AL TRY CATCH DE  LAS VENTAS QUE EST\u00c1N FUERA DE HORA DE PAGOEFECTIVO ******************");
                EliminaVentasNoConfirmadas.log.info((Object)Utils.printStackTraceToString(e));
            }
        }
    	log.info("[FIN]======= ELIMINANDO VENTAS NO CONFIRMADAS =======");
    }
    
    @Scheduled(fixedRate = 300000L)
    public void UpdateVentasNoConfirmadas() throws Exception {
    	log.info("[INICIO]======= UPDATE VENTAS NO CONFIRMADAS =======");
        if (Utils.isProduccion()) {
            this.venta.updateVentaNoConfirmada("E");            
        }
        log.info("[FIN]======= UPDATE VENTAS NO CONFIRMADAS =======");
    }
    
    @Scheduled(fixedRate = 1800000L)
    public void borrarVentasNoConfirmadas() throws Exception {
    	log.info("[INICIO]======= ELIMINANDO VENTAS CON ESTADO WEB [E] =======");
        if (Utils.isProduccion()) {            
            this.venta.deleteVentaNoConfirmada("E");
            EliminaVentasNoConfirmadas.log.info((Object)"****************** ELIMINANDO VENTAS QUE EST\u00c1N CON ESTADOWEB E ******************");
        }

    	log.info("[FIN]======= ELIMINANDO VENTAS CON ESTADO WEB [E] =======");
    }
    
    @Scheduled(cron = "0 27 0 * * ?")
    public void EliminaDocumentos() throws Exception {
    	log.info("[INICIO]======= ELIMINANDO ARCHIVOS TEMPORALES  =======");
        if (Utils.isProduccion()) {
            final File directorioEnvio = new File("/var/www/ventas/facturacion/enviotemporal/");
            EliminaVentasNoConfirmadas.log.info((Object)"INGRESANDO AL PROCESO DE ARCHIVOS .... ");
            FileUtils.cleanDirectory(directorioEnvio);
        }
        log.info("[FIN]======= ELIMINANDO ARCHIVOS TEMPORALES  =======");
    }
}