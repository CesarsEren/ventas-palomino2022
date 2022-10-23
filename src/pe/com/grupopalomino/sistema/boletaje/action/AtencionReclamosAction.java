package pe.com.grupopalomino.sistema.boletaje.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionSupport;
import pe.com.grupopalomino.sistema.boletaje.bean.B_AtencionReclamosBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_AtencionReclamosBeanDetalle;
import pe.com.grupopalomino.sistema.boletaje.bean.B_EncomiendasBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_MotivoBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_ReclamosCorrelativoBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_EmpresasBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_TipoDocumentosBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Varios;
import pe.com.grupopalomino.sistema.boletaje.service.AtencionReclamosService;
import pe.com.grupopalomino.sistema.boletaje.service.AtencionReclamosServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.EmpresaService;
import pe.com.grupopalomino.sistema.boletaje.service.EmpresaServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.EncomiendasService;
import pe.com.grupopalomino.sistema.boletaje.service.EncomiendasServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.ReclamosCorrelativoService;
import pe.com.grupopalomino.sistema.boletaje.service.ReclamosCorrelativoServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.UsuariosWebService;
import pe.com.grupopalomino.sistema.boletaje.service.UsuariosWebServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.VariosService;
import pe.com.grupopalomino.sistema.boletaje.service.VariosServiceI;
import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityUser;
import pe.com.grupopalomino.sistema.boletaje.util.ErrorValidacion;
import pe.com.grupopalomino.sistema.boletaje.util.FuncionesPDF;
import pe.com.grupopalomino.sistema.boletaje.util.GeneraExcel;
import pe.com.grupopalomino.sistema.boletaje.util.GenerarEmailReclamos;
import pe.com.grupopalomino.sistema.boletaje.util.Regex;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;
import pe.com.grupopalomino.sistema.boletaje.util.ValidaAtencionDatosReclamos;

@SuppressWarnings("serial")
@ParentPackage(value = "BoletajePalomino03")
@PreAuthorize("hasAnyRole('1','R','E','K','H')")
public class AtencionReclamosAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	B_AtencionReclamosBean reclamos = new B_AtencionReclamosBean();
	AtencionReclamosService service = new AtencionReclamosServiceI();
	ReclamosCorrelativoService servicecorrelativo = new ReclamosCorrelativoServiceI();
	String mensajeServer = "";
	private String query, detalle, tipo, agencia, servicio;
	private int limit, offset, total, Id;
	boolean respuestaServer = false;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private static final Logger log = Logger.getLogger(AtencionReclamosAction.class);
	private EncomiendasService serviceencomiendas = new EncomiendasServiceI();
	private UsuariosWebService usuarioService = new UsuariosWebServiceI();
	private List<V_EmpresasBean> listaComboEmpresa = new ArrayList<V_EmpresasBean>();
	private String RUC;
	private String nro, periodo, correo, empresa;
	private Map<String, Object> reclamoinformes;
	private List<B_MotivoBean> listaMotivos = new ArrayList<B_MotivoBean>();
	private List<V_TipoDocumentosBean> listaTipoDocumentos = new ArrayList<V_TipoDocumentosBean>();
	private List<Map<String, Object>> listaAgencias = new ArrayList<Map<String, Object>>();
	private EmpresaService serviceempresa = new EmpresaServiceI();

	private String dni = "";

	public SpringSecurityUser ObtenerPrincipal() {
		SpringSecurityUser usuario = (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		return usuario;
	}

	@Action(value = "/listareclamos", results = {
			@Result(name = SUCCESS, location = "reclamosinformes/listareclamosagencia.jsp") })
	public String listareclamosadministrador() {
		SpringSecurityUser USER = ObtenerPrincipal();
		try {

			SpringSecurityUser usuario = null;

			if (SecurityContextHolder.getContext().getAuthentication().getName() != null) {
				if (!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)) {
					if (SecurityContextHolder.getContext().getAuthentication()
							.getPrincipal() instanceof SpringSecurityUser) {

						usuario = (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication()
								.getPrincipal();

					} else {
						mensajeServer = "su tiempo ha expirado, por favor vuelva a ingresar.";
						return SUCCESS;
					}
				}
			}
			List<Map<String, Object>> Agencias = new ArrayList<Map<String, Object>>();
			Map<String,Object> agencia = new HashMap<>();
			agencia.put("Agencia", usuario.getAgencia());
			agencia.put("AgenciaD", usuario.getAgenciaD());
			Agencias.add(agencia);
			listaAgencias.clear();
			listaAgencias.addAll(Agencias);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	} 

	@Action(value = "/atencionreclamos", results = {
			@Result(name = SUCCESS, location = "reclamosinformes/pagereclamos.jsp") })
	public String reclamos() {

		try {
			listaComboEmpresa = serviceempresa.listaEmpresas();

		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}

		return SUCCESS;
	}

	@Action(value = "/atencionreclamosreporte", results = {
			@Result(name = SUCCESS, location = "reclamosinformes/pagereclamosreporte.jsp") })
	public String atencionreclamosreporte() {

		try {
			// listaComboEmpresa = serviceempresa.listaEmpresas();

		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}

		return SUCCESS;
	}

	@Action(value = "/reclamosestado", results = {
			@Result(name = SUCCESS, location = "reclamosinformes/pagereclamosestado.jsp") })
	public String reclamosestado() {

		try {
			// listaComboEmpresa = serviceempresa.listaEmpresas();
			listaAgencias = usuarioService.SQL_SELECT_AGENCIAS_USUARIOS();

		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}

		return SUCCESS;
	}

	@Action(value = "/listareclamosinformes", results = { @Result(name = SUCCESS, type = "json") })
	public String listareclamosinformes() {

		try {
			String recedni = "";
			recedni = this.dni;
			List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
			reclamoinformes = new HashMap<String, Object>();
			if ("".equals(recedni)) {
				List<B_AtencionReclamosBean> listaReclamos = service.selecReclamos(query, limit, offset, tipo, agencia,
						servicio);

				for (B_AtencionReclamosBean reclamo : listaReclamos) {
					Map<String, Object> parametros = new HashMap<String, Object>();
					parametros.put("nro", reclamo.getNro());
					parametros.put("empresa", reclamo.getEmpresa());
					parametros.put("periodo", reclamo.getPeriodo());
					parametros.put("empresaD", reclamo.getEmpresaD());
					parametros.put("fechaReclamo", reclamo.getFechaReclamo());
					parametros.put("tipoReclamo", reclamo.getTipoReclamo());
					parametros.put("atendido", reclamo.getAtendido());
					parametros.put("fechaAtencion", reclamo.getFechaAtencion());
					parametros.put("fechaCaducidad", reclamo.getFechaCaducidad());
					parametros.put("usuario", reclamo.getUsuario());
					parametros.put("agenciad", reclamo.getAgenciaD());
					parametros.put("Id", reclamo.getId());
					rows.add(parametros);
				}

				if (listaReclamos.size() > 0) {
					total = listaReclamos.get(0).getCantidad(); // service.selectReclamosTotales(tipo);
				} else {
					total = 0;
				}
			} else {

				List<B_AtencionReclamosBean> listaReclamos = service.selecReclamosDni(query, limit, offset, tipo,
						agencia, servicio, dni);

				for (B_AtencionReclamosBean reclamo : listaReclamos) {
					Map<String, Object> parametros = new HashMap<String, Object>();
					parametros.put("nro", reclamo.getNro());
					parametros.put("empresa", reclamo.getEmpresa());
					parametros.put("periodo", reclamo.getPeriodo());
					parametros.put("empresaD", reclamo.getEmpresaD());
					parametros.put("fechaReclamo", reclamo.getFechaReclamo());
					parametros.put("tipoReclamo", reclamo.getTipoReclamo());
					parametros.put("atendido", reclamo.getAtendido());
					parametros.put("fechaAtencion", reclamo.getFechaAtencion());
					parametros.put("fechaCaducidad", reclamo.getFechaCaducidad());
					parametros.put("usuario", reclamo.getUsuario());
					parametros.put("agenciad", reclamo.getAgenciaD());
					parametros.put("Id", reclamo.getId());
					rows.add(parametros);
				}

				if (listaReclamos.size() > 0) {

					total = listaReclamos.get(0).getCantidad(); // service.selectReclamosTotales(tipo);
				} else {

					total = 0;
				}
			}

			reclamoinformes.put("rows", rows);
			reclamoinformes.put("total", total);

		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}

		return SUCCESS;
	}

	@Action(value = "/listareclamosreporte", results = { @Result(name = SUCCESS, type = "json") })
	public String listareclamosreporte() {

		try {

			reclamoinformes = new HashMap<String, Object>();
			List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
			List<B_AtencionReclamosBean> listaReclamos = service.selecReclamosReporte(query, limit, offset);

			for (B_AtencionReclamosBean reclamo : listaReclamos) {
				Map<String, Object> parametros = new HashMap<String, Object>();
				parametros.put("nro", reclamo.getNro());
				parametros.put("empresa", reclamo.getEmpresaD());
				parametros.put("periodo", reclamo.getPeriodo());
				parametros.put("fechaReclamo", reclamo.getFechaReclamo());
				parametros.put("reclamante", reclamo.getNombres() + " " + reclamo.getApePaterno());
				parametros.put("fechaIncidente", reclamo.getFechaIncidente());
				parametros.put("servicio", reclamo.getServicioD());
				parametros.put("motivoReclamo", reclamo.getMotivoReclamoD());
				parametros.put("tipoReclamo", reclamo.getTipoReclamoD());
				parametros.put("documento", reclamo.getSerie() + "-" + reclamo.getNumero());
				parametros.put("atendido", reclamo.getAtendido());
				parametros.put("fechaAtencion", reclamo.getFechaAtencion());
				parametros.put("detalleAtencion", reclamo.getDetalleAtencion());
				parametros.put("Id", reclamo.getId());
				rows.add(parametros);
			}

			total = service.selectReclamosTotales(null);

			reclamoinformes.put("rows", rows);
			reclamoinformes.put("total", total);

		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}

		return SUCCESS;
	}

	@Action(value = "cambiaestadoreclamos", results = {
			@Result(name = SUCCESS, location = "reclamosinformes/pagereclamosestado.jsp") })
	public String cambiaestadoreclamos() {

		try {

			int resultado = -1;

			resultado = service.updateReclamos(Id, detalle);

			if (resultado == -1) {

				log.info("ERROR AL GENERAR EL UPDATE DE RECLAMOS");
			}

		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}

		return SUCCESS;
	}

	@Action(value = "/test", results = { @Result(name = SUCCESS, location = "reclamosinformes/_form.jsp") })
	public String test() {

		try {
			// listaComboEmpresa = serviceempresa.listaEmpresas();

		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}

		return SUCCESS;
	}

	@Action(value = "/generarexcel", results = { @Result(name = SUCCESS, type = "json") })
	public String generarexcel() {
		try {
			GeneraExcel.CrearExcelReporteReclamos();
			this.setMensajeServer("Excel Generado Correctamente");
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Action(value = "descargarreclamos", results = {
			@Result(name = SUCCESS, type = "stream", params = { "contentType", "application/vnd.ms-excel", "inputName",
					"inputStream", "contentDisposition", "filename=\"ReporteReclamos.xls\"", "bufferSize", "1024" }) })
	public String descargarreclamos() throws NumberFormatException, Exception {

		response.setHeader("Content-Disposition", "attachment;filename=ReporteReclamos.xls");
		response.setHeader("Content-Type", "application/vnd.ms-excel");
		// String sSistemaOperativo = System.getProperty("os.name");
		File file;
		if (Utils.isDesarrollo()) {
			file = new File("/home/equipo1/reclamos/ReporteReclamos.xls");
		} else {
			file = new File("C:\\Users\\Sistemas01\\Desktop\\ReporteReclamos.xls");
		}
		FileInputStream inStream = new FileInputStream(file);
		ServletOutputStream outStream = response.getOutputStream();
		byte[] buffer = new byte[4096];
		int bytesRead = -1;
		while ((bytesRead = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}
		inStream.close();
		outStream.close();

		return SUCCESS;
	}

	@Action(value = "/imprimiratencionreclamos", results = @Result(name = SUCCESS, type = "stream", params = {
			"contentType", "application/pdf", "inputName", "inputStream", "contentDisposition", "filename=\"test.pdf\"",
			"bufferSize", "1024" }))
	public String imprimirreclamos() {

		try {

			reclamos = service.selecReclamos(reclamos.getNro(), reclamos.getPeriodo(), reclamos.getEmpresa(), "S");

			B_AtencionReclamosBeanDetalle detalle = null;
			detalle = service.selecReclamosDetalle(reclamos.getSerie(), reclamos.getNumero(), reclamos.getDocumento(),
					reclamos.getEmpresa());

			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment;filename=Reclamo-N°-" + reclamos.getNro() + ".pdf");

			ServletOutputStream outputStream = response.getOutputStream();
			ByteArrayOutputStream baos = obtieneBAOS(reclamos, detalle);
			baos.writeTo(outputStream);

			response.setHeader("Content-Disposition", "attachment; filename=\"stuReport.pdf\"");
			response.setContentType("application/pdf");
			outputStream.flush();
			outputStream.close();
			baos.flush();
			baos.close();

		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}

		return SUCCESS;
	}

	@Action(value = "/imprimiratencionreclamosestado", results = @Result(name = SUCCESS, type = "stream", params = {
			"contentType", "application/pdf", "inputName", "inputStream", "contentDisposition", "filename=\"test.pdf\"",
			"bufferSize", "1024" }))
	public String imprimirreclamosestado() {

		try {

			reclamos = service.selecReclamos(reclamos.getNro(), reclamos.getPeriodo(), reclamos.getEmpresa(), "S");

			B_AtencionReclamosBeanDetalle detalle = null;
			detalle = service.selecReclamosDetalle(reclamos.getSerie(), reclamos.getNumero(), reclamos.getDocumento(),
					reclamos.getEmpresa());

			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment;filename=Reclamo-N°-" + reclamos.getNro() + ".pdf");

			reclamos.setOperacion("1");

			ServletOutputStream outputStream = response.getOutputStream();
			ByteArrayOutputStream baos = obtieneBAOS(reclamos, detalle);
			baos.writeTo(outputStream);

			response.setHeader("Content-Disposition", "attachment; filename=\"stuReport.pdf\"");
			response.setContentType("application/pdf");
			outputStream.flush();
			outputStream.close();
			baos.flush();
			baos.close();

		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}

		return SUCCESS;
	}

	@Action(value = "/reenviocorreoatencionreclamos", results = { @Result(name = SUCCESS, type = "json") })
	public String reclamoconfirmado() {

		try {

			if (correo.trim().isEmpty()) {
				respuestaServer = false;
				mensajeServer = "Por favor ingrese su correo.";
				return SUCCESS;

			}
			if (!correo.trim().matches(Regex.EMAIL)) {
				respuestaServer = false;
				mensajeServer = "El email ingresado no tiene el formato correcto.";
				return SUCCESS;
			}

			reclamos = service.selecReclamos(Integer.parseInt(nro), Integer.parseInt(periodo), empresa, "S");
			// GenerarEmail.enviarCorreoAdjuntoReclamos(GenerarEmail.parametrosReclamoCLiente(reclamos,correo,null),null);
			// GenerarEmail.enviarCorreoAtencionReclamos(parametros, stream);
			GenerarEmailReclamos.enviarCorreoAtencionReclamos(
					GenerarEmailReclamos.parametrosAtencionReclamos(reclamos, reclamos.getEmail(), null), null);
			respuestaServer = true;
			mensajeServer = "se ha enviado la solicitud a su correo, por favor verificar.";
		} catch (Exception e) {
			respuestaServer = false;
			mensajeServer = "Ocurrio un problema al enviar la solicitud a su correo, por favor intente más tarde.";
			log.info(Utils.printStackTraceToString(e));
		}

		return SUCCESS;
	}

	@Action(value = "/enviaratencionreclamos", results = { @Result(name = SUCCESS, type = "json") })
	public String enviarreclamo() throws Exception {

		try {

			SpringSecurityUser usuario = null;

			if (SecurityContextHolder.getContext().getAuthentication().getName() != null) {
				if (!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)) {
					if (SecurityContextHolder.getContext().getAuthentication()
							.getPrincipal() instanceof SpringSecurityUser) {

						usuario = (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication()
								.getPrincipal();

					} else {
						mensajeServer = "su tiempo ha expirado, por favor vuelva a ingresar.";
						return SUCCESS;
					}
				}
			}

			if (usuario == null) {
				mensajeServer = "su tiempo ha expirado, por favor vuelva a ingresar.";
				return SUCCESS;
			}

			reclamos.setUsuario(usuario.getUsername());
			reclamos.setAgencia(usuario.getAgencia());
			reclamos.setAgenciaD(usuario.getAgenciaD());

			int operacion = -1;

			reclamos.setValuesReclamos(reclamos);
			ValidaAtencionDatosReclamos validador = new ValidaAtencionDatosReclamos(reclamos);
			ErrorValidacion error = validador.ValidaReclamos();

			if (error.getError()) {
				for (String mensajeError : error.getMensajeError()) {
					mensajeServer = mensajeError;
				}
				respuestaServer = false;
				return SUCCESS;
			}

			B_EncomiendasBean encomiendas = new B_EncomiendasBean();

			encomiendas = serviceencomiendas.getEncomiendaSerieNumero(reclamos.getSerie(), reclamos.getNumero(),
					reclamos.getDocumento(), reclamos.getEmpresa());
			if (encomiendas == null) {
				mensajeServer = "El documento ingresado, no existe";
				return SUCCESS;
			}

			List<V_EmpresasBean> ListaEmpresa = new ArrayList<V_EmpresasBean>();
			ListaEmpresa = serviceempresa.listaEmpresas();

			for (V_EmpresasBean bean : ListaEmpresa) {
				if (bean.getCodigo().trim().equals(reclamos.getEmpresa())) {
					reclamos.setEmpresaD(bean.getRazon());
				}
			}

			/* GENERAMOS EL CORRELATIVO DEL LIBRO DE RECLAMACIONES */

			B_ReclamosCorrelativoBean bean = new B_ReclamosCorrelativoBean();

			bean = servicecorrelativo.selecReclamosCorrelativo(reclamos.getEmpresa(), "S");

			if (bean == null) {

				respuestaServer = false;
				mensajeServer = "Ocurrio un problema interno,su solicitud no ha sido procesada, por favor enviar un correo a ventatelefonica@grupopalomino.com.pe";
				return SUCCESS;
			}

			reclamos.setNro(bean.getCorrelativoAteRec());
			reclamos.setPeriodo(bean.getPeriodo());
			reclamos.setAtencionReclamos("S");
			if (reclamos.getAtendido() == null) {
				reclamos.setAtendido("P");
				reclamos.setDetalleAtencion("");
			} else if (reclamos.getAtendido().equals("on")) {
				reclamos.setAtendido("S");
			} else {
				reclamos.setAtendido("P");
				reclamos.setDetalleAtencion("");
			} 
			operacion = service.registraReclamos(reclamos);

			if (operacion == -1) {
				respuestaServer = false;
				mensajeServer = "Ocurrio un problema al enviar su reclamo, por favor intentelo más tarde.";
				return SUCCESS;
			}

			/* CONSULTAMOS LA TRANSACCION PARA ENVIARLA POR CORREO */

			reclamos = service.selecReclamos(reclamos.getNro(), bean.getPeriodo(), reclamos.getEmpresa(), "S");

			B_AtencionReclamosBeanDetalle detalle = null;

			detalle = service.selecReclamosDetalle(reclamos.getSerie(), reclamos.getNumero(), reclamos.getDocumento(),
					reclamos.getEmpresa());

			reclamos.setFechaEmision(detalle.getFechaEmision());
			reclamos.setDocumentoD(detalle.getDocumentoD());
			reclamos.setDestinoD(detalle.getDestinoD());
			reclamos.setAgenciaD(detalle.getAgenciaD());
			reclamos.setTipoReclamoD((reclamos.getTipoReclamo().trim().equals("R") ? "RECLAMO" : "QUEJA"));

			VariosService servicevarios = new VariosServiceI();
			V_Varios beanvarios = new V_Varios();

			beanvarios = servicevarios.Select_Varios();

			String CorreoReclamoPasajesCC = null;
			String CorreoReclamoEncomiendasCC = null;

			if (beanvarios.getCorreoReclamoPasajesCC() != null) {
				CorreoReclamoPasajesCC = beanvarios.getCorreoReclamoPasajesCC();
			}
			if (beanvarios.getCorreoReclamoEncomiendasCC() != null) {
				CorreoReclamoEncomiendasCC = beanvarios.getCorreoReclamoEncomiendasCC();
			}

			reclamos.setResponsable(beanvarios.getResponsableAtencionReclamos());
			// GenerarEmail.enviarCorreoAdjuntoReclamos(GenerarEmail.parametrosReclamoCLiente(reclamos,reclamos.getEmail(),null),null);
			// GenerarEmail.enviarCorreoAdjuntoReclamos(GenerarEmail.parametrosReclamoCLiente(reclamos,beanvarios.getCorreoReclamos(),beanvarios.getCorreoReclamosCC()),obtieneBAOS(reclamos,detalle));

			GenerarEmailReclamos.enviarCorreoAtencionReclamos(
					GenerarEmailReclamos.parametrosAtencionReclamos(reclamos, reclamos.getEmail(), null), null);
			GenerarEmailReclamos.enviarCorreoAtencionReclamos(GenerarEmailReclamos.parametrosAtencionReclamos(reclamos,
					(reclamos.getServicio().trim().equals("B") ? beanvarios.getCorreoReclamoPasajes()
							: beanvarios.getCorreoReclamoEncomiendas()),
					(reclamos.getServicio().trim().equals("B") ? CorreoReclamoPasajesCC : CorreoReclamoEncomiendasCC)),
					obtieneBAOS(reclamos, detalle));

			respuestaServer = true;
			mensajeServer = "Su reclamo ha sido enviado satisfactoriamente";

		} catch (Exception e) {
			respuestaServer = false;
			mensajeServer = "Ocurrio un problema al enviar su reclamo, por favor intentelo más tarde.";
			log.info(Utils.printStackTraceToString(e));
		}

		return SUCCESS;

	}

	private ByteArrayOutputStream obtieneBAOS(B_AtencionReclamosBean reclamos, B_AtencionReclamosBeanDetalle detalle) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {

			BaseFont BebasNeueRegular = BaseFont.createFont(
					FuncionesPDF.class.getResource("BebasNeueRegular.ttf").toString(), BaseFont.IDENTITY_H,
					BaseFont.EMBEDDED);
			String rutaLogo = "";
			float absoluteY = 0, absoluteX = 0, scalePercent = 0;
			Font Fuente = new Font(BebasNeueRegular, 19f, Font.NORMAL, BaseColor.BLACK);
			Font FuenteCabecera = new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
			Font FuenteCabeceraEmpresa = new Font(FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.WHITE);
			Font FuenteDetalle = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);

			Document documento = new Document();
			PdfWriter.getInstance(documento, baos);
			documento.open();

			if (reclamos.getEmpresa().trim().equals("002")) {

				rutaLogo = "Expreso_Internacional_Palomino_2.png";
				scalePercent = 18f;
				absoluteX = 415f;
				absoluteY = 760f;

			} else if (reclamos.getEmpresa().trim().equals("004")) {

				rutaLogo = "Transporte_Wari.png";
				scalePercent = 18f;
				absoluteX = 427f;
				absoluteY = 760f;

			} else if (reclamos.getEmpresa().trim().equals("003")) {

				rutaLogo = "Wari-Cargo-logo.png";
				scalePercent = 13f;
				absoluteX = 445f;
				absoluteY = 745f;
			}

			InputStream rutapalomino = request.getServletContext()
					.getResourceAsStream("_lib/img" + File.separator + rutaLogo);

			Image logopalomino = Image.getInstance(IOUtils.toByteArray(rutapalomino));
			logopalomino.scalePercent(scalePercent);
			logopalomino.setAbsolutePosition(absoluteX, absoluteY); // 770
			logopalomino.setAlignment(Element.ALIGN_LEFT);

			documento.add(logopalomino);
			documento.add(Chunk.NEWLINE);

			Paragraph titulo = new Paragraph("ATENCIÓN DE RECLAMOS N° " + reclamos.getNro(), new Font(Fuente));
			titulo.setAlignment(Element.ALIGN_CENTER);

			documento.add(titulo);
			documento.add(Chunk.NEWLINE);

			PdfPTable table = new PdfPTable(12);
			float[] medidaCeldas = { 0.75f, 0.75f, 0.55f, 1.8f, 0.70f, 0.70f, 0.90f, 0.55f, 0.70f, 0.70f, 0.90f,
					0.55f };
			table.setWidths(medidaCeldas);

			table = FuncionesPDF.ReporteAtencionReclamos(table, reclamos, FuenteCabeceraEmpresa, FuenteCabecera,
					FuenteDetalle, detalle);

			documento.add(table);

			documento.close();
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));

		}

		return baos;
	}

	public void setReclamos(B_AtencionReclamosBean reclamos) {
		this.reclamos = reclamos;
	}

	public B_AtencionReclamosBean getReclamos() {
		return reclamos;
	}

	public void setRespuestaServer(boolean respuestaServer) {
		this.respuestaServer = respuestaServer;
	}

	public boolean isRespuestaServer() {
		return respuestaServer;
	}

	public void setMensajeServer(String mensajeServer) {
		this.mensajeServer = mensajeServer;
	}

	public String getMensajeServer() {
		return mensajeServer;
	}

	public void setListaComboEmpresa(List<V_EmpresasBean> listaComboEmpresa) {
		this.listaComboEmpresa = listaComboEmpresa;
	}

	public List<V_EmpresasBean> getListaComboEmpresa() {
		return listaComboEmpresa;
	}

	public void setListaMotivos(List<B_MotivoBean> listaMotivos) {
		this.listaMotivos = listaMotivos;
	}

	public List<B_MotivoBean> getListaMotivos() {
		return listaMotivos;
	}

	public void setRUC(String rUC) {
		RUC = rUC;
	}

	public String getRUC() {
		return RUC;
	}

	public void setNro(String nro) {
		this.nro = nro;
	}

	public String getNro() {
		return nro;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getPeriodo() {
		return periodo;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getCorreo() {
		return correo;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getEmpresa() {
		return empresa;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

	public void setReclamoinformes(Map<String, Object> reclamoinformes) {
		this.reclamoinformes = reclamoinformes;
	}

	public Map<String, Object> getReclamoinformes() {
		return reclamoinformes;
	}

	public List<V_TipoDocumentosBean> getListaTipoDocumentos() {
		return listaTipoDocumentos;
	}

	public void setListaTipoDocumentos(List<V_TipoDocumentosBean> listaTipoDocumentos) {
		this.listaTipoDocumentos = listaTipoDocumentos;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getQuery() {
		return query;
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

	public void setId(int id) {
		Id = id;
	}

	public int getId() {
		return Id;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public String getServicio() {
		return servicio;
	}

	public void setListaAgencias(List<Map<String, Object>> listaAgencias) {
		this.listaAgencias = listaAgencias;
	}

	public List<Map<String, Object>> getListaAgencias() {
		return listaAgencias;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getAgencia() {
		return agencia;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
}
