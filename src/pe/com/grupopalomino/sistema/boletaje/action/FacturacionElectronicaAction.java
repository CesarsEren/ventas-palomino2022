package pe.com.grupopalomino.sistema.boletaje.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.rpc.ServiceException;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.axis.utils.ByteArrayOutputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ResolverUtil.IsA;
import com.opensymphony.xwork2.util.ValueStack;
import com.sun.xml.internal.ws.fault.ServerSOAPFaultException;
import pe.com.grupopalomino.sistema.boletaje.bean.B_FacturacionBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Varios_FacturacionBean;
import pe.com.grupopalomino.sistema.boletaje.service.FacturacionService;
import pe.com.grupopalomino.sistema.boletaje.service.FacturacionServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.Varios_FacturacionService;
import pe.com.grupopalomino.sistema.boletaje.service.Varios_FacturacionServiceI;
import pe.com.grupopalomino.sistema.boletaje.util.GeneraDocumentoFe;
import pe.com.grupopalomino.sistema.boletaje.util.HeaderHandlerResolver;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

//agregamos beta para pruebas, debes quitar para produccion y poner los de produccion

/*import pe.com.grupopalomino.sunat.beta.service.BillService;
import pe.com.grupopalomino.sunat.beta.service.BillService_Service;*/

import pe.com.grupopalomino.sunat.produccion.service.BillService;
import pe.com.grupopalomino.sunat.produccion.service.BillService_Service;

import pe.gob.sunat.service.BillServicePort;
import pe.gob.sunat.service.BillServicePortProxy;
import pe.gob.sunat.service.BillServicePortService;
import pe.gob.sunat.service.BillServicePortServiceLocator;
import pe.gob.sunat.service.StatusCdrResponse;
import pe.gob.sunat.service.StatusResponse;

@ParentPackage(value = "BoletajePalomino03")
@PreAuthorize("hasAnyRole('1','F')")
public class FacturacionElectronicaAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String empresa, fechaemision;
	private Integer documentos;
	private int limit, offset, total;
	private Varios_FacturacionService variosservice = new Varios_FacturacionServiceI();
	private FacturacionService facturacionservice = new FacturacionServiceI();
	// private V_Ventas_FacturacionService ventasfacturacionservice = new
	// V_Ventas_FacturacionServiceI();
	private Map<String, Object> mapaResultados = new HashMap<>();
	private Map<String, Object> mapaDocumentos = new HashMap<>();

	private Map<String, Object> documento = new HashMap<>();
	private String nro, emp, tipo;

	public void setNro(String nro) {
		this.nro = nro;
	}

	public String getNro() {
		return nro;
	}

	private B_FacturacionBean bean = new B_FacturacionBean();
	private List<String> test = new ArrayList<>();
	private List<String> data = new ArrayList<String>();
	private Map<String, Object> mapaJSONResultado = new HashMap<>();
	private static final Log log = LogFactory.getLog(FacturacionElectronicaAction.class);
	// private ExecutorService executeService =
	// Executors.newCachedThreadPool();;
	private String ruc, razon, dni, nombre;

	@Action(value = "editdocumento", results = { @Result(name = SUCCESS, type = "json") })
	public String editdocumento() {
		ValueStack stack = ActionContext.getContext().getValueStack();
		documento = facturacionservice.SQL_UPDATE_DOCUMENTO(ruc, razon, dni, nombre, nro, tipo);
		Map<String, Object> rpta = new HashMap<>();
		rpta.put("msgserver", "SUCCESS");
		stack.push(rpta);
		return SUCCESS;
	}

	@Action(value = "documentosenvio", results = { @Result(name = SUCCESS, location = "sistema/facturacionelectronica/enviodocumentos.jsp") })
	public String accedeListaUsuarios() {
		return SUCCESS;
	}

	@Action(value = "Traerdocumento", results = { @Result(name = SUCCESS, type = "json") })
	public String TraerDocumento() {
		ValueStack stack = ActionContext.getContext().getValueStack();
		documento = facturacionservice.SQL_SELECT_DOCUMENTO_ELECTRONICO(emp, nro, tipo);
		stack.push(documento);
		return SUCCESS;
	}

	@Action(value = "cosultadocumentos", results = { @Result(name = SUCCESS, type = "json") })
	public String cosultadocumentos1() {

		List<Map<String, Object>> lstDatos = new ArrayList<Map<String, Object>>();
		try {
			if ((empresa == null || empresa.toString().isEmpty()) || (fechaemision == null || fechaemision.toString().trim().isEmpty())) {
				Map<String, Object> mapa = new HashMap<>();
				mapaDocumentos.put("respuesta", false);
				mapaDocumentos.put("mensaje", "por favor seleccione la fecha y/o la empresa.");
				lstDatos.add(mapa);
				return SUCCESS;
			}

			List<Map<String, Object>> ventas = facturacionservice.SQL_SELECT_VENTA_ENVIO_FACTURADOR(empresa.trim(), Utils.FormatoFecha(fechaemision.trim().toString()), documentos, limit, offset);
			List<Map<String, Object>> lstAuxiliar = new ArrayList<Map<String, Object>>();
			total = 0;

			lstAuxiliar.addAll(ventas);

			for (Map<String, Object> map : lstAuxiliar) {
				Map<String, Object> mapa = new HashMap<>();
				mapa.put("id", map.get("Nro").toString());
				mapa.put("fechaEmision", map.get("FechaEmisionD"));
				mapa.put("empresa", map.get("Empresa"));
				mapa.put("documentoElectronico", map.get("DocumentoElectronico").toString());
				mapa.put("agencia", map.get("AgenciaD"));
				mapa.put("servicio", map.get("Servicio"));
				mapa.put("servicioD", map.get("ServicioD"));
				mapa.put("estado", "S");
				mapa.put("respuesta_ose", map.get("RespuestaOse"));
				mapa.put("enviar", "N");
				mapa.put("todos", map.get("variosservice"));
				mapa.put("documentos", documentos);
				mapa.put("observaciones", "La consulta se ha generado correctamente");
				// agregado por JCHC map.get("NroOrdenRef")
				mapa.put("OrdenRef", map.get("NroOrdenRef"));
				// hasta aquí
				lstDatos.add(mapa);
			}
			if (ventas.size() > 0) {
				total = Integer.parseInt(ventas.get(0).get("Cantidad").toString());
			}
			mapaDocumentos.put("respuesta", true);
			mapaDocumentos.put("rows", lstDatos);
			mapaDocumentos.put("total", total);
			mapaDocumentos.put("mensaje", "la operación se realizó con éxito...");

		} catch (Exception e) {
			mapaDocumentos.put("respuesta", false);
			mapaDocumentos.put("mensaje", "Ocurrió un problema al realizar la consulta.");
			log.info(Utils.printStackTraceToString(e));
		}

		return SUCCESS;

	}

	@Action(value = "enviosunat", results = { @Result(name = SUCCESS, type = "json") })
	public String enviosunat() {
		log.info(" ====== INICIANDO PROCESO DE ENVIO DE DOCUMENTOS SELECCIONADOS ====== ");
		Map<String, Object> mapaEnvio = new HashMap<>();
		List<Map<String, Object>> lstResultado = new ArrayList<>();
		Map<String, Object> mapaFacturacion = new HashMap<>();
		V_Varios_FacturacionBean facturacion = null;

		try {

			List<Map<String, Object>> lstData = new ArrayList<>();
			Map<String, Object> mapaRespuesta = new HashMap<>();
			List<B_FacturacionBean> listaEnviar = new ArrayList<>();
			boolean todos = false;

			String parametro = null;

			for (String string : data) {

				parametro = string.toString();
			}

			JSONParser parser = new JSONParser();

			Object obj = parser.parse(parametro);
			JSONObject jsonObject = (JSONObject) obj;

			JSONArray msg = (JSONArray) jsonObject.get("bean");

			for (int i = 0; i < msg.size(); i++) {
				Map<String, Object> mapaData = new HashMap<>();

				JSONParser parser1 = new JSONParser();
				JSONObject json = (JSONObject) parser1.parse(msg.get(i).toString());

				mapaData.put("id", json.get("id"));
				mapaData.put("fechaEmision", json.get("fechaEmision"));
				mapaData.put("empresa", json.get("empresa"));
				mapaData.put("documentoElectronico", json.get("documentoElectronico"));
				mapaData.put("agencia", json.get("agencia"));
				mapaData.put("servicio", json.get("servicio"));
				mapaData.put("enviar", json.get("enviar"));
				mapaData.put("todos", json.get("todos"));
				mapaData.put("documentos", json.get("documentos"));
				mapaData.put("observaciones", json.get("observaciones"));
				mapaData.put("referencia", json.get("referencia"));
				lstData.add(mapaData);

			}
			log.info("MAPA DE DATOS" + lstData.toString());

			if (lstData.size() == 0) {

				mapaRespuesta.put("respuesta", false);
				mapaRespuesta.put("mensaje", "Por favor debe de seleccionar un elemento.");
				lstResultado.add(mapaRespuesta);
				mapaJSONResultado.put("rows", lstResultado);
				return SUCCESS;
			}

			todos = Boolean.parseBoolean(lstData.get(0).get("todos").toString());

			facturacion = variosservice.SQL_SELECT_LISTA_PARAMETROS_FACTURADOR(lstData.get(0).get("empresa").toString());
			if (todos) {
				List<Map<String, Object>> ventas = facturacionservice.SQL_SELECT_VENTA_ENVIO_FACTURADOR(facturacion.getEmpresa(), Utils.FormatoFecha(lstData.get(0).get("fechaEmision").toString()),
						Integer.parseInt(lstData.get(0).get("documentos").toString()), 0, 0);

				for (Map<String, Object> map : ventas) {

					B_FacturacionBean documentos = new B_FacturacionBean();
					documentos.setId(Integer.parseInt(map.get("Nro").toString()));
					documentos.setFechaEmision(map.get("FechaEmisionD").toString());
					documentos.setEmpresa(map.get("Empresa").toString());
					documentos.setDocumentoElectronico(map.get("DocumentoElectronico").toString());
					documentos.setAgencia(map.get("AgenciaD").toString());
					documentos.setServicio(map.get("Servicio").toString());
					documentos.setEstado("S");
					documentos.setEnviar("S");
					documentos.setObservaciones("");
					documentos.setReferencia(map.get("NroOrdenRef").toString());
					bean.setTodos(true);
					// documentos.setTodos(true);
					listaEnviar.add(documentos);
				}
			} else {

				for (Map<String, Object> data : lstData) {

					if (data.get("enviar").toString().equals("S")) {
						B_FacturacionBean bean = new B_FacturacionBean();
						bean.setId(Integer.parseInt(data.get("id").toString()));
						bean.setFechaEmision(data.get("fechaEmision").toString());
						bean.setEmpresa(data.get("empresa").toString());
						bean.setDocumentoElectronico(data.get("documentoElectronico").toString());
						bean.setAgencia(data.get("agencia").toString());
						bean.setServicio(data.get("servicio").toString());
						bean.setEnviar(data.get("enviar").toString());
						bean.setTodos(Boolean.parseBoolean(data.get("todos").toString()));
						bean.setDocumentos(Integer.parseInt(data.get("documentos").toString()));
						bean.setObservaciones(data.get("observaciones").toString());
						bean.setReferencia(data.get("referencia").toString());
						listaEnviar.add(bean);
					}
				}
			}
		 
			EnviarSunatUOse(listaEnviar, mapaFacturacion, facturacion, mapaEnvio, lstResultado);

		} catch (Exception ex) {
			System.out.println("PADRE DE LA EXCEPTION ERROR " + mapaFacturacion.get("DocumentoElectronico").toString() + " Servicio : " + mapaFacturacion.get("ServicioD").toString() + " "
					+ ex.getMessage().substring(0, ex.getMessage().length()));
			mapaEnvio.put("respuesta", false);
			mapaEnvio.put("documentoElectronico", mapaFacturacion.get("DocumentoElectronico").toString());
			mapaEnvio.put("mensaje",
					"Ocurrió un error al enviar los documentos: " + "\n" + "Documentos: " + mapaFacturacion.get("DocumentoElectronico").toString() + "\n" + "Mensaje : " + Utils.printStackTraceToString(ex));
			lstResultado.add(mapaEnvio);

			facturacionservice.SQL_UPDATE_VENTA_FACTURADOR(Integer.parseInt(mapaFacturacion.get("Nro").toString()), mapaFacturacion.get("Servicio").toString(), 2, "Error 5");
			mapaJSONResultado.put("rows", lstResultado);
		}
		return SUCCESS;
	}

	public void GenerarXMLPorTipoServicio(Map<String, Object> mapaFacturacion, V_Varios_FacturacionBean facturacion) {
		String mapaFacturacionServicio = mapaFacturacion.get("Servicio").toString();
		log.info("==== GENERANDO XML DEL DOCUMENTO ==== TIPO SERVICIO [" + mapaFacturacionServicio + "]");
		if (mapaFacturacionServicio.equals("N")) {
			log.info("==== GENERANDO XML DEL DOCUMENTO ==== NOTA DE CRÉDITO");
			GeneraDocumentoFe.GeneraDocumentoNotaCreditoXMLUBL21(mapaFacturacion, facturacion);

		} else if (mapaFacturacionServicio.equals("T")) {
			log.info("==== GENERANDO XML DEL DOCUMENTO ==== NOTA DE DÉBITO");
			GeneraDocumentoFe.GeneraDocumentoNotaDebitoXMLUBL21(mapaFacturacion, facturacion);

		} else if (mapaFacturacionServicio.equals("B") || mapaFacturacionServicio.equals("E") || mapaFacturacionServicio.equals("C")) {
			log.info("==== GENERANDO XML DEL DOCUMENTO ==== BOLETA , ENCOMIENDA , CANJE");
			if (mapaFacturacion.get("Bus_Carga") == null) {
				log.info("==== GENERANDO XML DEL DOCUMENTO ==== NO es Viaje Especial");
				GeneraDocumentoFe.GeneraDocumentoFacturaXMLPruebaUBL21(mapaFacturacion, facturacion);
			}

			if (mapaFacturacion.get("Bus_Carga").toString().equals("VI"))// Viaje Especial
			{
				log.info("==== GENERANDO XML DEL DOCUMENTO ==== Viaje Especial");
				// System.out.println("BUS CARGA : " +
				// mapaFacturacion.get("Bus_Carga").toString());
				GeneraDocumentoFe.GeneraDocumentoFacturaXMLPruebaUBL21ViajeEspecial(mapaFacturacion, facturacion);
			} else {
				log.info("==== GENERANDO XML DEL DOCUMENTO ==== NO es Viaje Especial");
				GeneraDocumentoFe.GeneraDocumentoFacturaXMLPruebaUBL21(mapaFacturacion, facturacion);
			}
		}
	}

	public byte[] SendBillBZLinks(String fileName, DataHandler dataHandler, V_Varios_FacturacionBean facturacion) throws RemoteException, IOException, ServiceException {

		Utils.USERNAME = facturacion.getUsernameSunat();
		Utils.PASSWORD = facturacion.getPasswordSunat();

		pe.gob.sunat.service.bzlinks.beta.util.HeaderHandlerResolver HeadersSecurity = new pe.gob.sunat.service.bzlinks.beta.util.HeaderHandlerResolver();
		HeadersSecurity.setVruc(facturacion.getRuc());
		byte[] rpta = null;
		if (Utils.isProduccion()) {
 

		} else {
			pe.gob.sunat.service.bzlinks.beta.BizlinksOSE service = new pe.gob.sunat.service.bzlinks.beta.BizlinksOSE();
			service.setHandlerResolver(HeadersSecurity);
			pe.gob.sunat.service.bzlinks.beta.BillServicePort port = service.getBillServicePortSoap11();
			rpta = port.sendBill(fileName, dataHandler, "");
		}
		return rpta;
	}

	public byte[] SendBillEscon(String fileName, DataHandler dataHandler, V_Varios_FacturacionBean facturacion) throws RemoteException, IOException, ServiceException {

		Utils.USERNAME = facturacion.getUsernameSunat();
		Utils.PASSWORD = facturacion.getPasswordSunat();
		byte[] rpta = null;

		if (Utils.isProduccion()) {// is PROD
			log.info("==== ENVIANDO XML A PRODUCCIÓN ESCON ==== ");
			BillServicePortService service = new BillServicePortServiceLocator();
			BillServicePort billService = service.getBillServicePortSoap11();
			rpta = billService.sendBill(fileName, toBytes(dataHandler), "");

		} else {
			log.info("==== ENVIANDO XML A CALIDAD ESCON==== ");
		}
		return rpta;
	}

	public void GetAndSaveCDR(V_Varios_FacturacionBean facturacion, Map<String, Object> mapaFacturacion, byte[] respuestaSunat) throws IOException, SAXException, ParserConfigurationException {

		FileOutputStream fos = new FileOutputStream(facturacion.getRutaRespuestaSunat() + "R-" + mapaFacturacion.get("DocumentoZip").toString());// FACTURA
		fos.write(respuestaSunat);
		fos.close();

		ZipInputStream zisEnvio = new ZipInputStream(new FileInputStream(facturacion.getRutaRespuestaSunat() + "R-" + mapaFacturacion.get("DocumentoZip").toString()));

		ZipEntry entrada;

		while (null != (entrada = zisEnvio.getNextEntry())) {

			byte[] buf = new byte[1024];
			int len;
			StringBuffer contentXml = new StringBuffer();

			if (!entrada.isDirectory()) {

				while ((len = zisEnvio.read(buf)) > 0) {
					contentXml.append(new String(buf, 0, len, "UTF-8"));
				}

				DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				InputSource inputSource = new InputSource();
				inputSource.setCharacterStream(new StringReader(contentXml.toString()));

				Document doc = documentBuilder.parse(inputSource);

				NodeList nodeListhash = doc.getElementsByTagName("cbc:ResponseCode");
				NodeList nodeListdescripcion = doc.getElementsByTagName("cbc:Description");
				Node nodohash = nodeListhash.item(0);
				Node nodohashDesc = nodeListdescripcion.item(0);
				log.info("El documento " + mapaFacturacion.get("DocumentoZip").toString() + "fue Enviado a SUNAT");
				if (nodohash instanceof Element && nodohashDesc instanceof Element) {
					if (nodohash.getTextContent().trim().equals("0")) {
						log.info("El documento " + mapaFacturacion.get("DocumentoZip").toString() + "fue ACEPTADO");
						facturacionservice.SQL_UPDATE_VENTA_FACTURADOR(Integer.parseInt(mapaFacturacion.get("Nro").toString()), mapaFacturacion.get("Servicio").toString(), 1, nodohashDesc.getTextContent().trim());
					} else {
						log.info("El documento " + mapaFacturacion.get("DocumentoZip").toString() + "tiene ERRORES");
						facturacionservice.SQL_UPDATE_VENTA_FACTURADOR(Integer.parseInt(mapaFacturacion.get("Nro").toString()), mapaFacturacion.get("Servicio").toString(), 2, nodohashDesc.getTextContent().trim());
					}
				}
			}
		}

		zisEnvio.close();
	}

	public void EnviarSunatUOse(List<B_FacturacionBean> listaEnviar, Map<String, Object> mapaFacturacion, V_Varios_FacturacionBean facturacion, Map<String, Object> mapaEnvio, List<Map<String, Object>> lstResultado)
			throws ServiceException {
		byte[] respuestaSunat = null;

		for (B_FacturacionBean data : listaEnviar) {

			mapaFacturacion = facturacionservice.SQL_SELECT_VENTA_FACTURADOR(data.getId(), data.getServicio());
			log.info("==== GENERANDO XML DEL DOCUMENTO ==== " + mapaFacturacion.get("DocumentoZip").toString());
			log.info("==== GENERANDO XML DEL DOCUMENTO ==== OSE :" + facturacion.getOSE());
			
			GenerarXMLPorTipoServicio(mapaFacturacion, facturacion);

			try {

				FileDataSource dataSource = new FileDataSource(facturacion.getRutaEnvioSunat() + mapaFacturacion.get("DocumentoZip").toString());
				DataHandler dataHandler = new DataHandler(dataSource);

				switch (facturacion.getOSE()) {
				case "ESCON":
					respuestaSunat = SendBillEscon(mapaFacturacion.get("DocumentoZip").toString(), dataHandler, facturacion);
					break;
				case "BZLINKS":
					break;
				default:
					log.info("NO TENEMOS INTEGRACIÓN CON ESA OSE , SOLO TENEMOS LA OPCIÓN DE ESCON Y BZLINKS");
					break;
				}

				if (respuestaSunat != null) {
					GetAndSaveCDR(facturacion, mapaFacturacion, respuestaSunat);
				} else {
					log.info("respuestaSunat is NULL");
				}

				mapaEnvio.put("respuesta", true);
				mapaEnvio.put("mensaje", "Los documentos fueron enviados correctamente");
				lstResultado.add(mapaEnvio);
				mapaJSONResultado.put("rows", lstResultado);

			} catch (IOException io) {

				System.out.println("EXCEPTION --- IOException " + mapaFacturacion.get("DocumentoElectronico").toString() + " Servicio : " + mapaFacturacion.get("ServicioD").toString() + " "
						+ io.getMessage().substring(0, io.getMessage().length()));
				String code = io.getMessage().substring(0, io.getMessage().length());
				System.out.println("EXCEPTION --- IOException2 " + io.getMessage().substring(0, io.getMessage().length()));
				System.out.println("EXCEPTION --- IOException3 " + code);
				mapaEnvio.put("respuesta", false);
				mapaEnvio.put("documentoElectronico", mapaFacturacion.get("DocumentoElectronico").toString());
				mapaEnvio.put("mensaje", "Ocurrió un error al enviar los documentos:" + "\n" + "Documentos: " + mapaFacturacion.get("DocumentoElectronico").toString() + "\n" + Utils.printStackTraceToString(io));
				lstResultado.add(mapaEnvio);
				if (code.equals("1033")) {
					facturacionservice.SQL_UPDATE_VENTA_FACTURADOR(Integer.parseInt(mapaFacturacion.get("Nro").toString()), mapaFacturacion.get("Servicio").toString(), 1, code);
				} else {
					facturacionservice.SQL_UPDATE_VENTA_FACTURADOR(Integer.parseInt(mapaFacturacion.get("Nro").toString()), mapaFacturacion.get("Servicio").toString(), 2, code);
				}

				mapaJSONResultado.put("rows", lstResultado);
			} catch (ParserConfigurationException pa) {
				System.out.println("EXCEPTION --- ParserConfigurationException " + mapaFacturacion.get("DocumentoElectronico").toString() + " Servicio : " + mapaFacturacion.get("ServicioD").toString() + " "
						+ pa.getMessage().substring(0, pa.getMessage().length()));

				mapaEnvio.put("respuesta", false);
				mapaEnvio.put("documentoElectronico", mapaFacturacion.get("DocumentoElectronico").toString());
				mapaEnvio.put("mensaje", "Ocurrió un error al enviar los documentos:" + "\n" + "Documentos: " + mapaFacturacion.get("DocumentoElectronico").toString() + "\n" + Utils.printStackTraceToString(pa));
				lstResultado.add(mapaEnvio);

				facturacionservice.SQL_UPDATE_VENTA_FACTURADOR(Integer.parseInt(mapaFacturacion.get("Nro").toString()), mapaFacturacion.get("Servicio").toString(), 2, "Error 1");
				mapaJSONResultado.put("rows", lstResultado);

			} catch (SAXException sa) {
				System.out.println("EXCEPTION --- SAXException" + mapaFacturacion.get("DocumentoElectronico").toString() + " Servicio : " + mapaFacturacion.get("ServicioD").toString() + " "
						+ sa.getMessage().substring(0, sa.getMessage().length()));

				mapaEnvio.put("respuesta", false);
				mapaEnvio.put("documentoElectronico", mapaFacturacion.get("DocumentoElectronico").toString());
				mapaEnvio.put("mensaje", "Ocurrió un error al enviar los documentos:" + "\n" + "Documentos: " + mapaFacturacion.get("DocumentoElectronico").toString() + "\n" + Utils.printStackTraceToString(sa));
				lstResultado.add(mapaEnvio);

				facturacionservice.SQL_UPDATE_VENTA_FACTURADOR(Integer.parseInt(mapaFacturacion.get("Nro").toString()), mapaFacturacion.get("Servicio").toString(), 2, "Error 2");
				mapaJSONResultado.put("rows", lstResultado);

			} catch (ServerSOAPFaultException se) {
				System.out.println("EXCEPTION --- ServerSOAPFaultException " + mapaFacturacion.get("DocumentoElectronico").toString() + " Servicio : " + mapaFacturacion.get("ServicioD").toString() + " "
						+ se.getMessage().substring(0, se.getMessage().length()));

				mapaEnvio.put("respuesta", false);
				mapaEnvio.put("documentoElectronico", mapaFacturacion.get("DocumentoElectronico").toString());
				mapaEnvio.put("mensaje",
						"Ocurrió un error al enviar los documentos: " + "\n" + "Documentos: " + mapaFacturacion.get("DocumentoElectronico").toString() + "\n" + "Mensaje : " + Utils.printStackTraceToString(se));
				lstResultado.add(mapaEnvio);

				facturacionservice.SQL_UPDATE_VENTA_FACTURADOR(Integer.parseInt(mapaFacturacion.get("Nro").toString()), mapaFacturacion.get("Servicio").toString(), 2, "Error 3");
				mapaJSONResultado.put("rows", lstResultado);
			}

			catch (SOAPFaultException so) {

				System.out.println("EXCEPTION --- SOAPFaultException " + mapaFacturacion.get("DocumentoElectronico").toString() + " Servicio : " + mapaFacturacion.get("ServicioD").toString() + " "
						+ so.getMessage().substring(0, so.getMessage().length()));

				mapaEnvio.put("respuesta", false);
				mapaEnvio.put("documentoElectronico", mapaFacturacion.get("DocumentoElectronico").toString());
				mapaEnvio.put("mensaje",
						"Ocurrió un error al enviar los documentos : " + "\n" + "Documentos: " + mapaFacturacion.get("DocumentoElectronico").toString() + "\n" + "Codigo Error: "
								+ (so.getMessage().substring(so.getMessage().lastIndexOf("INFO:") + 5, so.getMessage().lastIndexOf("INFO:") + 11)).trim() + "\n" + "Observaciones: "
								+ (so.getMessage().substring(so.getMessage().lastIndexOf("server:") + 7, so.getMessage().lastIndexOf("-"))).trim() + " \n" + "Mensaje : "
								+ (so.getMessage().substring(so.getMessage().lastIndexOf("INFO:") + 5, so.getMessage().lastIndexOf("INFO:") + 11)).trim());
				lstResultado.add(mapaEnvio);

				facturacionservice.SQL_UPDATE_VENTA_FACTURADOR(Integer.parseInt(mapaFacturacion.get("Nro").toString()), mapaFacturacion.get("Servicio").toString(), 2, "Error 4");
				mapaJSONResultado.put("rows", lstResultado);
			}
		}
	}

	public static byte[] toBytes(DataHandler handler) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		handler.writeTo(output);
		return output.toByteArray();
	}

	// Documentos Wari Cargo Canjes

	public static void main(String[] args) throws IOException, ServiceException {
		FacturacionService facturacionservice = new FacturacionServiceI();
		Map<String, Object> mapaFacturacion = new HashMap<>();
		Varios_FacturacionService variosservice = new Varios_FacturacionServiceI();
		// V_Varios_FacturacionBean facturacion =
		// variosservice.SQL_SELECT_LISTA_PARAMETROS_FACTURADOR("003");//WARI
		// CARGO
		V_Varios_FacturacionBean facturacion = variosservice.SQL_SELECT_LISTA_PARAMETROS_FACTURADOR("002");// TRANSPORTES
																											// PALOMINO
		// V_Varios_FacturacionBean facturacion =
		// variosservice.SQL_SELECT_LISTA_PARAMETROS_FACTURADOR("004");//TRANSPORTES
		// WARI

		Utils.USERNAME = facturacion.getUsernameSunat();
		Utils.PASSWORD = facturacion.getPasswordSunat();

		List<Integer> lista = new ArrayList<>();
		// lista.add(4188831);
		// facturacionservice.SQL_ALLDATA();

		int nro = 3705458;

		// for(int nro: lista){
		mapaFacturacion = facturacionservice.SQL_SELECT_VENTA_FACTURADOR(nro, "B");
		// GeneraDocumentoFe.GeneraDocumentoFacturaXMLPruebaUBL21(mapaFacturacion,
		// facturacion);
		// GeneraDocumentoFe.GeneraDocumentoNotaDebitoXMLUBL21(mapaFacturacion,
		// facturacion);

		// GeneraDocumentoFe.GeneraDocumentoFacturaXMLPruebaUBL21ViajeEspecial(mapaFacturacion,
		// facturacion);
		/*
		 * System.out.println("mapaFacturacion"+mapaFacturacion.toString());
		 * System.out.println("mapaFacturacion"+mapaFacturacion.toString());
		 * System.out.println("mapaFacturacion"+mapaFacturacion.toString());
		 * System.out.println("mapaFacturacion"+mapaFacturacion.toString());
		 * System.out.println("mapaFacturacion"+mapaFacturacion.toString());
		 * System.out.println("mapaFacturacion"+mapaFacturacion.toString());
		 */

		if (mapaFacturacion.get("Servicio").toString().equals("B") || mapaFacturacion.get("Servicio").toString().equals("E") || mapaFacturacion.get("Servicio").toString().equals("C")) {
			// System.out.println("BUS CARGA :
			// "+mapaFacturacion.get("Bus_Carga").toString());
			if (mapaFacturacion.get("Bus_Carga") == null) {
				GeneraDocumentoFe.GeneraDocumentoFacturaXMLPruebaUBL21(mapaFacturacion, facturacion);
			}
			if (mapaFacturacion.get("Bus_Carga").toString().equals("VI")) {
				System.out.println("BUS CARGA :  " + mapaFacturacion.get("Bus_Carga").toString());
				System.out.println("IGV_Activo:  " + mapaFacturacion.get("IGV_Activo").toString());

				GeneraDocumentoFe.GeneraDocumentoFacturaXMLPruebaUBL21ViajeEspecial(mapaFacturacion, facturacion);
			} else {
				GeneraDocumentoFe.GeneraDocumentoFacturaXMLPruebaUBL21(mapaFacturacion, facturacion);
			}
		}

		// GeneraDocumentoFe.GeneraDocumentoFacturaXML(mapaFacturacion,
		// facturacion);
		// GeneraDocumentoFe.GeneraDocumentoNotaCreditoXMLUBL21(mapaFacturacion,
		// facturacion);
		// log.info(""+mapaFacturacion.get("CodigoAfectacionIGV").toString());
		FileDataSource datasource = new FileDataSource(facturacion.getRutaEnvioSunat() + mapaFacturacion.get("DocumentoZip").toString().trim());
		DataHandler dataHandler = new DataHandler(datasource);
		byte[] DocumentoPorEnviar = toBytes(dataHandler);

		BillServicePortService service = new BillServicePortServiceLocator();
		BillServicePort billService = service.getBillServicePortSoap11();

		String RutaXML = facturacion.getRutaEnvioSunat() + facturacion.getRuc() + "-" + mapaFacturacion.get("TipoDocumento") + "-" + mapaFacturacion.get("DocumentoElectronico");
		System.out.println(RutaXML);

		System.out.println(mapaFacturacion.get("DocumentoZip").toString());
		billService.sendBill(mapaFacturacion.get("DocumentoZip").toString(), DocumentoPorEnviar, "");
		// LOG.info("Enviado "+nro);
		// }
	}

	public void main2(String[] args) throws IOException, ServiceException {
		String notarechazados = "";
		List<Map<String, Object>> lstResultado = new ArrayList<>();
		Map<String, Object> mapaEnvio = new HashMap<>();
		try {
			FacturacionService facturacionservice = new FacturacionServiceI();
			Map<String, Object> mapaFacturacion = new HashMap<>();
			Varios_FacturacionService variosservice = new Varios_FacturacionServiceI();
			V_Varios_FacturacionBean facturacion = variosservice.SQL_SELECT_LISTA_PARAMETROS_FACTURADOR("002");
			// List<Map<String, Object>> ventas =
			// facturacionservice.SQL_SELECT_VENTA_ENVIO_FACTURADOR(facturacion.getEmpresa(),Utils.FormatoFecha(fechaemision),documentos,0,0);
			// List<Integer>lista = facturacionservice.SQL_ALLDATA();
			// for (Integer nro : lista) {
			Integer nro = 3918395;
			mapaFacturacion = facturacionservice.SQL_SELECT_VENTA_FACTURADOR(nro, "B");

			Utils.USERNAME = facturacion.getUsernameSunat();
			Utils.PASSWORD = facturacion.getPasswordSunat();

			BillServicePortService service = new BillServicePortServiceLocator();
			BillServicePort billService = service.getBillServicePortSoap11();
			StatusCdrResponse response = billService.getStatusCdr(facturacion.getRuc(), mapaFacturacion.get("TipoDocumento").toString(), mapaFacturacion.get("SerieElectronica").toString(),
					mapaFacturacion.get("Numero").toString());

			if (response.getStatusCode().trim().equals("0") || response.getStatusCode().trim().equals("1033")) {
				/*
				 * FileOutputStream fos = new
				 * FileOutputStream(facturacion.getRutaRespuestaSunat()+ "R-" +
				 * mapaFacturacion.get("DocumentoZip").toString());
				 * fos.write(response.getContent()); fos.close();
				 */
				log.info("ENVIADO CORRECTAMENTE \n" + mapaFacturacion.get("DocumentoElectronico").toString() + " SERVICIO :" + mapaFacturacion.get("ServicioD").toString() + " ESTADO : " + response.getStatusCode()
						+ " MENSAJE : " + response.getStatusMessage());
				// facturacionservice.SQL_UPDATE_VENTA_FACTURADOR(Integer.parseInt(mapaFacturacion.get("Nro").toString()),
				// mapaFacturacion.get("Servicio").toString(),0);
			} else {

				log.info("DOCUMENTO RECHAZADO \n --> DOCUMENTO : " + mapaFacturacion.get("DocumentoElectronico").toString() + " SERVICIO :" + mapaFacturacion.get("ServicioD").toString() + " ESTADO : "
						+ response.getStatusCode() + " MENSAJE : " + response.getStatusMessage());
				notarechazados += "" + mapaFacturacion.get("DocumentoElectronico").toString() + ",";

				// facturacionservice.SQL_UPDATE_VENTA_FACTURADOR(Integer.parseInt(mapaFacturacion.get("Nro").toString()),
				// mapaFacturacion.get("Servicio").toString(),(/*response.getStatusCode().equals("0011")?0:*/
				// 0));

				// facturacionerrorervice.SQL_UPDATE_VENTAS_FACTURACION_ERROR(Integer.parseInt(mapaFacturacion.get("Nro").toString()),
				// mapaFacturacion.get("Servicio").toString(),
				// response.getStatusCode());
			}
			// }
			log.info(notarechazados);

			mapaEnvio.put("respuesta", true);
			mapaEnvio.put("mensaje", "Los Documentos fueron consultados");
			lstResultado.add(mapaEnvio);
			// mapaJSONResultado.put("rows", lstResultado);
		} catch (Exception e) {
			// log.info("ERROR EN EL SERVICIO DE CONSULTAS SUNAT(ESCON) -->
			// "+mapaFacturacion.get("DocumentoElectronico").toString()+"-"+mapaFacturacion.get("ServicioD").toString()+"
			// "+e.getMessage());
			mapaEnvio.put("respuesta", false);
			mapaEnvio.put("mensaje", "Ocurrio un problema al verificar los documentos, por favor vuelva a consultar los documentos.");
			lstResultado.add(mapaEnvio);
			// mapaJSONResultado.put("rows", lstResultado);
			// facturacionservice.SQL_UPDATE_VENTA_FACTURADOR(Integer.parseInt(mapaFacturacion.get("Nro").toString()),
			// mapaFacturacion.get("Servicio").toString(), 2);
		}
	}

	// DOCUMENTOS COMUNICACIÓN DE BAJA RA
	/*
	 * public static void main(String[]args) throws IOException, ServiceException {
	 * 
	 * FacturacionService facturacionservice = new FacturacionServiceI(); Map<String
	 * , Object> mapaFacturacion = new HashMap<>(); Varios_FacturacionService
	 * variosservice = new Varios_FacturacionServiceI(); //PARA PALOMINO
	 * V_Varios_FacturacionBean facturacion =
	 * variosservice.SQL_SELECT_LISTA_PARAMETROS_FACTURADOR("002");
	 * 
	 * Utils.USERNAME = facturacion.getUsernameSunat(); Utils.PASSWORD =
	 * facturacion.getPasswordSunat(); List<Integer>lista =
	 * facturacionservice.SQL_ALLDATA(); for(int nro: lista){ mapaFacturacion =
	 * facturacionservice.SQL_SELECT_VENTA_FACTURADOR(nro, "A");
	 * 
	 * GeneraDocumentoFe.GeneraDocumentoComunicacionBajaXML(mapaFacturacion,
	 * facturacion);
	 * 
	 * FileDataSource datasource = new
	 * FileDataSource(facturacion.getRutaEnvioSunat()+mapaFacturacion.get(
	 * "DocumentoZip").toString().trim()); DataHandler dataHandler = new
	 * DataHandler(datasource);
	 * 
	 * byte[] DocumentoPorEnviar=toBytes(dataHandler);
	 * 
	 * BillServicePortService service = new BillServicePortServiceLocator();
	 * 
	 * String RutaXML =
	 * facturacion.getRutaEnvioSunat()+facturacion.getRuc()+"-"+mapaFacturacion.
	 * get("TipoDocumento")+"-"+mapaFacturacion.get("DocumentoElectronico");
	 * System.out.println(RutaXML);
	 * 
	 * //ZipEntry dato = new ZipEntry("");
	 * 
	 * BillServicePort billService = service.getBillServicePortSoap11();
	 * System.out.println(mapaFacturacion.get("DocumentoZip").toString());
	 * billService.sendSummary(mapaFacturacion.get("DocumentoZip").toString(),
	 * DocumentoPorEnviar, ""); } //USP_SELECT_VENTA_X_NRO_FACTURADOR_SQL
	 * 
	 * }
	 */
	/*
	 * public void main1(String[] args) {
	 * 
	 * Map<String , Object> mapaFacturacion = new HashMap<>(); FacturacionService
	 * facturacionservice = new FacturacionServiceI(); Varios_FacturacionService
	 * variosservice = new Varios_FacturacionServiceI();
	 * 
	 * V_Varios_FacturacionBean facturacion =
	 * variosservice.SQL_SELECT_LISTA_PARAMETROS_FACTURADOR("002"); String
	 * respuestaSunat = null; try { mapaFacturacion =
	 * facturacionservice.SQL_SELECT_VENTA_FACTURADOR(154, "A");
	 * 
	 * GeneraDocumentoFe.GeneraDocumentoComunicacionBajaXML(mapaFacturacion,
	 * facturacion);
	 * 
	 * 
	 * FileDataSource dataSource = new
	 * FileDataSource(facturacion.getRutaEnvioSunat()+mapaFacturacion.get(
	 * "DocumentoZip").toString().trim()); DataHandler dataHandler = new
	 * DataHandler(dataSource);
	 * 
	 * BillService_Service serviceport = new BillService_Service();
	 * HeaderHandlerResolver headerResolver = new HeaderHandlerResolver();
	 * 
	 * 
	 * Utils.USERNAME = facturacion.getUsernameSunat(); Utils.PASSWORD =
	 * facturacion.getPasswordSunat();
	 * 
	 * serviceport.setHandlerResolver(headerResolver);
	 * 
	 * BillService billService = serviceport.getBillServicePort();
	 * 
	 * 
	 * 
	 * respuestaSunat =
	 * billService.sendSummary(mapaFacturacion.get("DocumentoZip").toString(),
	 * dataHandler, "");
	 * 
	 * System.out.println("TICKET DE ATENCION --> "+ respuestaSunat);
	 * 
	 * //StatusResponse response = new StatusResponse();
	 * 
	 * response = billService.getStatus(respuestaSunat);
	 * 
	 * System.out.println("RESPUESTA DEL STATUS --> "+ response.getStatusCode());
	 * 
	 * 
	 * if (response.getStatusCode().trim().equals("0")){
	 * 
	 * FileOutputStream fos = new
	 * FileOutputStream(facturacion.getRutaRespuestaSunat()+ "R-" +
	 * mapaFacturacion.get("DocumentoZip").toString().trim());// FACTURA
	 * fos.write(response.getContent()); fos.close();
	 * 
	 * ZipInputStream zisEnvio = new ZipInputStream(new
	 * FileInputStream(facturacion.getRutaRespuestaSunat()+"R-"+mapaFacturacion.
	 * get("DocumentoZip").toString().trim()));
	 * 
	 * ZipEntry entrada;
	 * 
	 * while (null != (entrada = zisEnvio.getNextEntry())){
	 * 
	 * byte[] buf = new byte[1024]; int len; StringBuffer contentXml = new
	 * StringBuffer();
	 * 
	 * if(!entrada.isDirectory()) {
	 * 
	 * while ((len = zisEnvio.read(buf)) > 0) { contentXml.append(new String(buf, 0,
	 * len, "UTF-8")); }
	 * 
	 * DocumentBuilder documentBuilder =
	 * DocumentBuilderFactory.newInstance().newDocumentBuilder(); InputSource
	 * inputSource = new InputSource(); inputSource.setCharacterStream(new
	 * StringReader(contentXml.toString()));
	 * 
	 * Document doc = documentBuilder.parse(inputSource); NodeList nodeListhash =
	 * doc.getElementsByTagName("cbc:ResponseCode"); Node nodohash =
	 * nodeListhash.item(0);
	 * 
	 * 
	 * if (nodohash instanceof Element){
	 * if(nodohash.getTextContent().trim().equals("0")) {
	 * 
	 * System.out.println("RESPUESTA DE LA SUNAT "
	 * +nodohash.getTextContent().trim());
	 * 
	 * //facturacionservice.SQL_UPDATE_VENTA_FACTURADOR(Integer.parseInt(
	 * mapaFacturacion.get("Nro").toString()),
	 * mapaFacturacion.get("Servicio").toString(),1);
	 * 
	 * }else {
	 * 
	 * //facturacionservice.SQL_UPDATE_VENTA_FACTURADOR(Integer.parseInt(
	 * mapaFacturacion.get("Nro").toString()),
	 * mapaFacturacion.get("Servicio").toString(),2);
	 * //facturacionerrorervice.SQL_UPDATE_VENTAS_FACTURACION_ERROR(Integer.
	 * parseInt(mapaFacturacion.get("Nro").toString()),
	 * mapaFacturacion.get("Servicio").toString(),
	 * nodohash.getTextContent().trim()); } } } }
	 * 
	 * zisEnvio.close();
	 * 
	 * 
	 * }
	 * 
	 * 
	 * } catch (Exception e) { log.info(Utils.printStackTraceToString(e)); }
	 * 
	 * }
	 */

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setFechaemision(String fechaemision) {
		this.fechaemision = fechaemision;
	}

	public String getFechaemision() {
		return fechaemision;
	}

	public void setDocumentos(Integer documentos) {
		this.documentos = documentos;
	}

	public Integer getDocumentos() {
		return documentos;
	}

	public Map<String, Object> getMapaDocumentos() {
		return mapaDocumentos;
	}

	public void setMapaDocumentos(Map<String, Object> mapaDocumentos) {
		this.mapaDocumentos = mapaDocumentos;
	}

	public void setMapaResultados(Map<String, Object> mapaResultados) {
		this.mapaResultados = mapaResultados;
	}

	public Map<String, Object> getMapaResultados() {
		return mapaResultados;
	}

	public void setBean(B_FacturacionBean bean) {
		this.bean = bean;
	}

	public B_FacturacionBean getBean() {
		return bean;
	}

	public void setTest(List<String> test) {
		this.test = test;
	}

	public List<String> getTest() {
		return test;
	}

	public void setData(List<String> data) {
		this.data = data;
	}

	public List<String> getData() {
		return data;
	}

	public void setMapaJSONResultado(Map<String, Object> mapaJSONResultado) {
		this.mapaJSONResultado = mapaJSONResultado;
	}

	public Map<String, Object> getMapaJSONResultado() {
		return mapaJSONResultado;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getLimit() {
		return limit;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getOffset() {
		return offset;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotal() {
		return total;
	}

	public Map<String, Object> getDocumento() {
		return documento;
	}

	public void setDocumento(Map<String, Object> documento) {
		this.documento = documento;
	}

	public void setEmp(String emp) {
		this.emp = emp;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEmp() {
		return emp;
	}

	public String getTipo() {
		return tipo;
	}

	public String getRuc() {
		return ruc;
	}

	public String getRazon() {
		return razon;
	}

	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setRazon(String razon) {
		this.razon = razon;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
}
