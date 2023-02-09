package pe.com.grupopalomino.sistema.boletaje.util;

import java.io.BufferedReader;
//import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
//import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

//import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import pe.com.grupopalomino.sistema.boletaje.bean.B_ProgramacionSalidaBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_VentaBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_AgenciasBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_RutasBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Varios;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Varios_FacturacionBean;
import pe.com.grupopalomino.sistema.boletaje.formviews.ComboIdentidad;
import pe.com.grupopalomino.sistema.boletaje.service.AgenciasService;
import pe.com.grupopalomino.sistema.boletaje.service.AgenciasServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.FacturacionService;
import pe.com.grupopalomino.sistema.boletaje.service.FacturacionServiceI;
//import pe.com.grupopalomino.sistema.boletaje.service.FacturacionService;
//import pe.com.grupopalomino.sistema.boletaje.service.FacturacionServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.ProgramacionSalidaService;
import pe.com.grupopalomino.sistema.boletaje.service.ProgramacionSalidaServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.V_Ventas_FacturacionService;
import pe.com.grupopalomino.sistema.boletaje.service.V_Ventas_FacturacionServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.VariosService;
import pe.com.grupopalomino.sistema.boletaje.service.VariosServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.Varios_FacturacionService;
import pe.com.grupopalomino.sistema.boletaje.service.Varios_FacturacionServiceI;
/*import pe.com.grupopalomino.sunat.consultas.produccion.service.BillConsultService;
import pe.com.grupopalomino.sunat.consultas.produccion.service.BillService;
import pe.com.grupopalomino.sunat.consultas.produccion.service.StatusResponse;*/
//import pe.com.grupopalomino.sunat.consultas.produccion.service.BillConsultService;
//import pe.com.grupopalomino.sunat.consultas.produccion.service.BillService;
//import pe.com.grupopalomino.sunat.consultas.produccion.service.StatusResponse;
//import pe.com.grupopalomino.sunat.beta.service.BillService;
//import pe.com.grupopalomino.sunat.beta.service.BillService_Service;
//import pe.com.grupopalomino.sunat.beta.service.StatusResponse;

public class Utils {

	public static final String WEBCONTENT = "/WEB-INF/content/";
	public static final String RESERVABOLETO = "/WEB-INF/content/ReservaBoleto/";
	public static final String BOLETAJE = "/WEB-INF/content/Boletaje/";
	public static final String VENTAS = "/WEB-INF/content/Consultas/";
	public static final String REUTILIZABLES = "/WEB-INF/content/Reutilizables/";
	public static final String SITE_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
	public static final String SITE_KEY = "6LeBNDEUAAAAABr9VEM1kxGSqDJrxBa0cpBgOMAs";
	public static final String SECRET_KEY = "6LeBNDEUAAAAAOq1S-5wPDR63P-ubeoMg-v31BTy";

	// PROPIEDADES PARA LA FACTURACIÓN ELECTRONICA
	public static String USERNAME = "";
	public static String PASSWORD = "";
	public static String rutazip = "";// temporal
	public static final String KEYSTORE_TYPE = "JKS";
	
	/**		CODIGO DE MONTO EN LETRAS (CATALOGO 15 - sac:AdditionalProperty/cbc:ID)	 **/
	
	//PASAJES
	public static final String CODIGO_MONTO_LETRAS = "1000";
	/** CODIGO TIPO DE TRIBUTO (CATALOGO 5 - cac:TaxScheme/cbc:ID) **/
	public static final String CODIGO_TIPO_TRIBUTO_PASAJES = "9997";//para factura y para boleta
	/** CODIGO TIPO DE TRIBUTO (CATALOGO 5 - cac:TaxScheme/cbc:Name) **/
	public static final String NOMBRE_TRIBUTO_PASAJES = "EXO";//para factura y para boleta
	/** CODIGO TIPO DE TRIBUTO (CATALOGO 5 - cac:TaxScheme/cbc:TaxTypeCode) **/
	public static final String CODIGO_INTERNACIONAL_TRIBUTO_PASAJES = "VAT";//para factura y para boleta
	
	//ENCOMIENDA
	public static final String CODIGO_TIPO_TRIBUTO_ENCOMIENDA = "1000";//para factura y para boleta
	/** CODIGO TIPO DE TRIBUTO (CATALOGO 5 - cac:TaxScheme/cbc:Name) **/
	public static final String NOMBRE_TRIBUTO_ENCOMIENDA = "IGV";//para factura y para boleta
	/** CODIGO TIPO DE TRIBUTO (CATALOGO 5 - cac:TaxScheme/cbc:TaxTypeCode) **/
	public static final String CODIGO_INTERNACIONAL_TRIBUTO_ENCOMIENDA = "VAT";//para factura y para boleta 

	public static final String URL_PDF_TICKET_CLIENTES = "d3sc4rg4d3d0cum3t0s3l3ctr0n1c0st1ck3tcl13nt3s";
	public static final String URL_XML_CLIENTES = "d3sc4rg4d3d0cum3t0s3l3ctr0n1c0sxmlcl13nt3s";
	public static final String URL_PDF_NROMAL_CLIENTES = "d3sc4rg4d3d0cum3t0s3l3ctr0n1c0sn0rm4lcl13nt3s";
	
	//AGREGADO POR JCHC
	public static final String NROORDENREFERENCIA= "99"; //Leyenda: Este documento sustenta el traslado de bienes
	public static final String NROORDENREFERENCIA1= "1000"; //Leyenda: Este documento sustenta el traslado de bienes
	// HASTA AQUI
	
	
	//PASAJES Y/O ENCOMIENDAS POR CARIDAD
	
	public static final String CODIGO_TIPO_TRIBUTO_EXCENTO = "9996";
	public static final String NOMBRE_TRIBUTO_EXCENTO = "GRA";
	public static final String CODIGO_INTERNACIONAL_EXCENTO = "FRE";

	private static final Log log = LogFactory.getLog(Utils.class);
 	
	//Url de producción para ventas generar código de barras pdf417 y code128 además de codehash
	
	
	
	//public static String URL_BASE = "http://172.16.10.230:8080/wspalomino/palomino/fe/generadocumento";
	public static String URL_BASE = "https://ventas.grupopalomino.com.pe:8443/wspalomino/palomino/fe/generadocumento";
	
	//public static String URL_BASE = "http://172.16.10.3:8080/wspalominotest/palomino/fe/generadocumento";//EmpresaBkp3	
	
	public static String printStackTraceToString(Exception e) {
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors)); 
		return errors.toString();
	}
	// DEV | PROD
	public static String STATUS_SYSTEM = "PROD";
	public static boolean isProduccion()
	{
		return Utils.STATUS_SYSTEM.equals("PROD");
	}

	public static boolean esFechaPosteriorActual(String fecha) {

		boolean esFechaPosterior = true;

		DateTimeFormatter fechaFormat = DateTimeFormat.forPattern("dd/MM/yyyy");
		DateTime fechaJoda = fechaFormat.withZoneUTC().parseDateTime(fecha);

		esFechaPosterior = fechaJoda.toLocalDate().isAfter(new DateTime().toLocalDate())
				|| fechaJoda.toLocalDate().equals(new DateTime().toLocalDate());

		return esFechaPosterior;
	}

	public static boolean esFechaIdaMenorFechaVuelta(String fechaIda, String fechaVuelta) {

		boolean esMayor = false;

		DateTimeFormatter fechaFormat = DateTimeFormat.forPattern("dd/MM/yyyy");

		DateTime fechaIdaJodaA = fechaFormat.withZoneUTC().parseDateTime(fechaIda);
		DateTime fechaVueltaJodaB = fechaFormat.withZoneUTC().parseDateTime(fechaVuelta);

		esMayor = fechaIdaJodaA.toLocalDate().isBefore(fechaVueltaJodaB.toLocalDate())
				|| fechaIdaJodaA.toLocalDate().equals(fechaVueltaJodaB.toLocalDate());

		return esMayor;
	}

	public static String FormatoFecha(String strFecha) {

		String resultado = "";

		try {

			if ((strFecha != null) && (!(strFecha.trim().equals("")))) {

				SimpleDateFormat to = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat from = new SimpleDateFormat("dd/MM/yyyy");
				resultado = to.format(from.parse(strFecha));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultado;
	}

	public static String FormatoFechaPagosIso(String strFecha) {

		String resultado = "";

		try {

			if ((strFecha != null) && (!(strFecha.trim().equals("")))) {

				String[] valorSeparado = strFecha.split(",");

				for (int i = 0; i < valorSeparado.length; i++) {

					SimpleDateFormat to = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat from = new SimpleDateFormat("dd/MM/yyyy");
					resultado = resultado + to.format(from.parse(valorSeparado[i]))
							+ (i < (valorSeparado.length - 1) ? "," : "");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultado;
	}

	public static String FormatoFechaPagosEstandar(String strFecha) {

		String resultado = "";

		try {

			if ((strFecha != null) && (!(strFecha.trim().equals("")))) {

				String[] valorSeparado = strFecha.split(",");

				for (int i = 0; i < valorSeparado.length; i++) {

					SimpleDateFormat to = new SimpleDateFormat("dd/MM/yyyy");
					SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd");
					resultado = resultado + to.format(from.parse(valorSeparado[i]))
							+ (i < (valorSeparado.length - 1) ? "," : "");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultado;
	}

	/**
	 * 
	 * Estable un formato de fecha DD/MM/YYYY a partir de una fecha en formato
	 * ISO.
	 **/
	public static String FormatoFechaReporteISO(String strFechaISO) {

		String anio = "";
		String mes = "";
		String dia = "";
		String resultado = "";

		if ((strFechaISO != null) && (!(strFechaISO.trim().equals("")))) {
			anio = strFechaISO.substring(0, 4);
			mes = strFechaISO.substring(4, 6);
			dia = strFechaISO.substring(6, 8);
			resultado = dia + "/" + mes + "/" + anio;

		} else {
			resultado = "";
		}

		return resultado;
	}

	public static String FormatoFechaReporte(String strFecha) {

		String anio = "";
		String mes = "";
		String dia = "";
		String resultado = "";

		if ((strFecha != null) && (!(strFecha.trim().equals("")))) {
			if (strFecha.length() == 10) {
				anio = strFecha.substring(0, 4);
				mes = strFecha.substring(5, 7);
				dia = strFecha.substring(8, 10);
				resultado = dia + "/" + mes + "/" + anio;
			} else {
				resultado = "";
			}
		} else {
			resultado = "";
		}

		return resultado;
	}

	public static String FormatoFechaVoucher(String strFecha, String reimprimir) throws ParseException {

		SimpleDateFormat formatoresultado = new SimpleDateFormat("d MMMM',' yyyy", new Locale("ES"));
		SimpleDateFormat formatoInicial = new SimpleDateFormat("yyyy-MM-dd");
		String separadorespacio = " ";
		String[] valorfecha;
		String dia = "";
		String mes = "";
		String anio = "";
		String Resultado = "";
		if ((strFecha != null) && (!(strFecha.trim().equals("")))) {
			if (strFecha.length() == 10) {
				if (reimprimir == "Y") {
					dia = strFecha.substring(0, 2);
					mes = strFecha.substring(3, 5);
					anio = strFecha.substring(6, 10);
					strFecha = anio + "-" + mes + "-" + dia;
					dia = "";
					mes = "";
					anio = "";
				}
				Date fechaProgramacion = formatoInicial.parse(strFecha);
				String fechasaliente = formatoresultado.format(fechaProgramacion);
				valorfecha = fechasaliente.split(separadorespacio);
				dia = valorfecha[0];
				mes = valorfecha[1];
				anio = valorfecha[2];
				Resultado = dia + " " + mes.toUpperCase() + anio;
			}
		}

		return Resultado;
	}

	public static String FormatoFechaReporteEstadouenta(String strFecha) throws ParseException {
		String dia = "";
		String mes = "";
		String anio = "";
		String Resultado = "";
		if ((strFecha != null) && (!(strFecha.trim().equals("")))) {
			if (strFecha.length() == 10) {
				dia = strFecha.substring(0, 2);
				mes = strFecha.substring(3, 5);
				anio = strFecha.substring(6, 10);
				strFecha = anio + mes + dia;
				Resultado = strFecha;
			}
		}

		return Resultado;
	}

	public static String[] F_Separador_Reporte(String cadena, String separador) {

		try {
			String[] resultado = null;

			if (cadena != null && !cadena.trim().equals("")) {

				resultado = cadena.split(separador);
			}
			return resultado;
		} catch (Exception ex) {

			throw ex;
		}

	}

	public static List<ComboIdentidad> getListaComboIdentidad() {

		List<ComboIdentidad> listaComboIdentidad = new ArrayList<ComboIdentidad>();

		ComboIdentidad cb1 = new ComboIdentidad();
		cb1.setId(1);
		cb1.setTipoDocumento("D");
		cb1.setDescripcionDocumento("D.N.I.");

		ComboIdentidad cb2 = new ComboIdentidad();
		cb2.setId(2);
		cb2.setTipoDocumento("P");
		cb2.setDescripcionDocumento("Pasaporte");

		ComboIdentidad cb3 = new ComboIdentidad();
		cb3.setId(3);
		cb3.setTipoDocumento("M");
		cb3.setDescripcionDocumento("Menor Edad");

		listaComboIdentidad.add(cb1);
		listaComboIdentidad.add(cb2);
		listaComboIdentidad.add(cb3);

		return listaComboIdentidad;
	}

	public static List<V_RutasBean> getListaComboDestinoBajada(String destino, String destinoDescripcion) {

		// RutasService serviceRutas = new RutasServiceI();

		List<V_RutasBean> lstRutasBajada = new ArrayList<V_RutasBean>();

		try {

			V_RutasBean comboDestinoBajada = new V_RutasBean();

			comboDestinoBajada.setCiudad(String.valueOf(destino + "," + destinoDescripcion));
			comboDestinoBajada.setCiudadD(destinoDescripcion);

			List<V_RutasBean> listaRutasBeans = new ArrayList<>();
			listaRutasBeans.add(comboDestinoBajada);

			lstRutasBajada.addAll(listaRutasBeans);

			/*
			 * List<V_RutasBean> listaRutasBeans =
			 * serviceRutas.getRutasNro(nroDestino);
			 * 
			 * String detalle = listaRutasBeans.get(0).getNroDetalle(); String[]
			 * valor = detalle.split("-");
			 * 
			 * for (int i = 0; i < listaRutasBeans.size(); i++) {
			 * 
			 * if
			 * (listaRutasBeans.get(i).getCiudadD().trim().equals(valor[1].trim(
			 * ))) { V_RutasBean comboDestinoBajada = new V_RutasBean();
			 * comboDestinoBajada .setCiudad(listaRutasBeans.get(i).getCiudad()
			 * + "," + listaRutasBeans.get(i).getCiudadD());
			 * comboDestinoBajada.setCiudadD(listaRutasBeans.get(i).getCiudadD()
			 * .trim());
			 * 
			 * lstRutasBajada.add(comboDestinoBajada);
			 * listaRutasBeans.remove(i); } }
			 * 
			 * for (int i = 0; i < listaRutasBeans.size(); i++) { V_RutasBean
			 * rutas = listaRutasBeans.get(i); V_RutasBean beanauxiliar = new
			 * V_RutasBean();
			 * 
			 * beanauxiliar.setCiudad(String.valueOf(rutas.getCiudad() + "," +
			 * rutas.getCiudadD()));
			 * beanauxiliar.setCiudadD(rutas.getCiudadD().trim());
			 * 
			 * listaRutasBeans.set(i, beanauxiliar); }
			 * 
			 * lstRutasBajada.addAll(listaRutasBeans);
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lstRutasBajada;
	}

	public static List<B_ProgramacionSalidaBean> getListaComboEmbarque(int nroProgramacion, String origenAgencia) {

		List<B_ProgramacionSalidaBean> listaEmbarque = new ArrayList<B_ProgramacionSalidaBean>();

		try {

			ProgramacionSalidaService service = new ProgramacionSalidaServiceI();
			listaEmbarque = service.getSalidasAgencias(nroProgramacion, origenAgencia);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaEmbarque;

	}

	public static List<V_Varios> getListaCantidadPasajeros() {

		List<V_Varios> listacantidadPasajeros = new ArrayList<V_Varios>();
		VariosService servicevarios = new VariosServiceI();

		V_Varios bean = new V_Varios();
		try {
			bean = servicevarios.Get_Cantidad_Maximo_Pasajeros();

			if (bean != null) {
				for (int i = 1; i < bean.getCantidadMaximaPasajeros() + 1; i++) {

					V_Varios beanAuxi = new V_Varios();
					beanAuxi.setCantidadMaximaPasajeros(i);
					listacantidadPasajeros.add(beanAuxi);
				}
			}

			return listacantidadPasajeros;
		} catch (Exception e) {

			e.printStackTrace();
		}

		return listacantidadPasajeros;
	}

	public static List<V_AgenciasBean> getListaVentasOcupadoXAgencias(int nroProgramacion) {

		List<V_AgenciasBean> listaAgencia = new ArrayList<>();
		AgenciasService service = new AgenciasServiceI();

		try {
			listaAgencia = service.getListaAsientosOcupadoXAgencia(nroProgramacion);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaAgencia;

	}

	public static List<V_AgenciasBean> getListaVentasReservadoXAgencias(int nroProgramacion) {

		List<V_AgenciasBean> listaAgencia = new ArrayList<>();
		AgenciasService service = new AgenciasServiceI();

		try {
			listaAgencia = service.getListaAsientosReservadoXAgencia(nroProgramacion);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaAgencia;

	}

	public static List<V_AgenciasBean> getListaComidas(int nroProgramacion) {

		List<V_AgenciasBean> listaAgencia = new ArrayList<>();
		AgenciasService service = new AgenciasServiceI();

		try {
			listaAgencia = service.getListaComida(nroProgramacion);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaAgencia;

	}

	public static ByteArrayOutputStream BaosPagoEfectivo(List<B_VentaBean> venta) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {

			Document documento = new Document();

			if (venta.size() > 0) {
				PdfWriter.getInstance(documento, baos);
				documento.open();

			}

			int contador = 0;

			for (B_VentaBean bean : venta) {

				// InputStream rutaIda =
				// request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Palomino.jpg");

				InputStream rutaIda = null;

				if (bean.getTipoEmpresa() == 1) {

					// rutaIda =
					// request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Palomino.jpg");
					rutaIda = FuncionesPDF.class.getResourceAsStream("Embarque_Palomino.jpg");
					// FuncionesPDF.class.getResourceAsStream("Embarque_Palomino.jpg");
					// rutaIda = new FileInputStream("_lib/img/");

				} else if (bean.getTipoEmpresa() == 2) {

					// rutaIda =
					// request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Poseidon.jpg");
					rutaIda = FuncionesPDF.class.getResourceAsStream("Embarque_Poseidon.jpg");

				}

				// GENERAMOS EL VOUCHER DE IDA
				// ___________________________________________________________________________________________________________________________________
				Image logoIda = Image.getInstance(IOUtils.toByteArray(rutaIda));
				logoIda.scalePercent(22f);
				logoIda.setAbsolutePosition(20f, 610f);
				logoIda.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);

				documento.add(logoIda);

				PdfPTable table = new PdfPTable(35);

				table = FuncionesPDF.F_Reporte_Ventas(table, bean.getDestinoD().trim(), bean.getOrigen().trim(),
						bean.getServicio().trim(), bean.getNombre().trim(), bean.getDNI(), bean.getNro(),
						bean.getAsiento().trim(), bean.getOrigenD().trim(), bean.getFechaViaje().substring(0, 10),
						bean.getHoraViaje(), "N", bean.getFechaEmision(), bean.getPrecioAct(), bean.getRuc(),
						bean.getRazon(), bean.getHoraCompra(), "Tarjeta", "Pago Efectivo", bean.getTarjetaHabiente(),
						"", bean.getEticketVisa(), null, bean.getDestinoRutaD());

				documento.add(table);

				// InputStream rutaplantillafooterIda =
				// request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Plantilla_Base_Tarjeta.jpg");

				InputStream rutaplantillafooterIda = null;

				if (bean.getTipoEmpresa() == 1) {

					// rutaplantillafooterIda =
					// request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Plantilla_Base_Tarjeta.jpg");
					rutaplantillafooterIda = FuncionesPDF.class.getResourceAsStream("Plantilla_Base_Tarjeta.jpg");

				} else if (bean.getTipoEmpresa() == 2) {

					// rutaplantillafooterIda =
					// request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Plantilla_Base_Tarjeta_Poseidon.jpg");
					rutaplantillafooterIda = FuncionesPDF.class
							.getResourceAsStream("Plantilla_Base_Tarjeta_Poseidon.jpg");
				}

				Image logofooterIda = Image.getInstance(IOUtils.toByteArray(rutaplantillafooterIda));
				logofooterIda.scalePercent(23f);
				logofooterIda.setAbsolutePosition(5f, 75f);
				logofooterIda.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);

				documento.add(logofooterIda);
				contador = contador + 1;

				if (contador != venta.size()) {
					documento.newPage();
				}
			}
			documento.close();

		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		return baos;

	}

	public static ByteArrayOutputStream obtieneBAOS(List<B_VentaBean> ventaVisa) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			Document documento = new Document();
			if (ventaVisa.size() > 0) {
				PdfWriter.getInstance(documento, baos);
				documento.open();

			}

			int contador = 0;

			for (B_VentaBean bean : ventaVisa) {

				InputStream rutaIda = FuncionesPDF.class.getResourceAsStream("Embarque_Palomino.jpg");// request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Embarque_Palomino.jpg");

				// GENERAMOS EL VOUCHER DE IDA
				// ___________________________________________________________________________________________________________________________________
				Image logoIda = Image.getInstance(IOUtils.toByteArray(rutaIda));
				logoIda.scalePercent(22f);
				logoIda.setAbsolutePosition(20f, 610f);
				logoIda.setAlignment(Element.ALIGN_LEFT);

				documento.add(logoIda);

				PdfPTable table = new PdfPTable(35);

				table = FuncionesPDF.F_Reporte_Ventas(table, bean.getDestinoD().trim(), bean.getOrigen().trim(),
						bean.getServicio().trim(), bean.getNombre().trim(), bean.getDNI(), bean.getNro(),
						bean.getAsiento().trim(), bean.getOrigenD().trim(), bean.getFechaViaje().substring(0, 10),
						bean.getHoraViaje(), "N", bean.getFechaEmision(), bean.getPrecioAct(), bean.getRuc(),
						bean.getRazon(), bean.getHoraCompra(), "Tarjeta", "VISA", bean.getTarjetaHabiente(), "",
						bean.getEticketVisa(), null, bean.getDestinoRutaD());

				documento.add(table);

				InputStream rutaplantillafooterIda = FuncionesPDF.class
						.getResourceAsStream("Plantilla_Base_Tarjeta.jpg"); // request.getServletContext().getResourceAsStream("_lib/img"+File.separator+"Plantilla_Base_Tarjeta.jpg");

				Image logofooterIda = Image.getInstance(IOUtils.toByteArray(rutaplantillafooterIda));
				logofooterIda.scalePercent(23f);
				logofooterIda.setAbsolutePosition(5f, 75f);
				logofooterIda.setAlignment(Element.ALIGN_LEFT);

				documento.add(logofooterIda);
				contador = contador + 1;

				if (contador != ventaVisa.size()) {
					documento.newPage();
				}
			}
			documento.close();
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}

		return baos;
	}
	
	public static void consumirservicio(String nro, String tipo) {
		try {
			log.info("Generando CODIGOHASH Y IMAGENPDF417 A Registro -> "+nro);
			URL url = new URL(URL_BASE + "?nro=" + nro + "&tipoOperacion=" + tipo); 
			/*System.out.println(URL_BASE);
			System.out.println(URL_BASE + "?nro=" + nro + "&tipoOperacion=" + tipo);*/
			// ?nro=3337445&tipoOperacion=B
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type",
					"application/json");/*
										 * conn.setRequestProperty("apikey",
										 * ""); conn.setRequestProperty(
										 * "codigocomercio", "");
										 */
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			StringBuilder respuesta = new StringBuilder();
			String output;
			while ((output = br.readLine()) != null) {
				respuesta.append(output);
			}
			//System.out.println(respuesta);
			log.info("CODIGOHASH GENERADO : -> "+respuesta);
			conn.disconnect();
		} catch (MalformedURLException e) { 
			log.info(Utils.printStackTraceToString(e));
		} catch (IOException e) {
			log.info(Utils.printStackTraceToString(e));
		}
	}
	public   void main4(String[] args) {

		PasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("F142030"));

		Map<String, Object> mapaFacturacion = new HashMap<>();
		// Map<String, Object> mapa = new HashMap<>();

		try { 

			FacturacionService facturacionservice = new FacturacionServiceI();
			Varios_FacturacionService variosservice = new Varios_FacturacionServiceI();

			mapaFacturacion = facturacionservice.SQL_SELECT_VENTA_FACTURADOR(2642034, "B");

			V_Varios_FacturacionBean facturacion = variosservice.SQL_SELECT_LISTA_PARAMETROS_FACTURADOR("002");

			V_Ventas_FacturacionService ventasfacturacionservice = new V_Ventas_FacturacionServiceI();

			int resultado = -1;

			Map<String, Object> respuestaXML = new HashMap<>();

			respuestaXML.put("codehash", "6wj/zVbwQ+aKsuRgIS8xhFbC6dw=");

			// resultado =
			// ventasfacturacionservice.SQL_UPDATE_VENTAS_FACTURACION_IMAGES(2637941,"B","6wj/zVbwQ+aKsuRgIS8xhFbC6dw=",null,null);

			byte[] inputStreamBarra = GeneraDocumentoFe.CodigoBarras(facturacion, mapaFacturacion, respuestaXML);
			byte[] inputStreamQR = GeneraDocumentoFe.CodigoQR(facturacion, mapaFacturacion, respuestaXML);

			System.out.println("BYTES --> " + inputStreamBarra);

			resultado = ventasfacturacionservice.SQL_UPDATE_VENTAS_FACTURACION_IMAGES(
					Integer.parseInt(mapaFacturacion.get("Nro").toString()), mapaFacturacion.get("Servicio").toString(),
					respuestaXML.get("codehash").toString(), null, inputStreamQR);

			System.out.println("RESULTADO DEL UPDATE " + resultado);

			// System.out.println(mapaFacturacion);

			/*
			 * BillService_Service serviceport = new BillService_Service();
			 * BillService billService = serviceport.getBillServicePort();
			 * 
			 * StatusResponse response = new StatusResponse();
			 * 
			 * response = billService.getStatus("1524258704564");
			 * System.out.println("RESPUESTA -->" + response.getStatusCode());
			 */

		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
