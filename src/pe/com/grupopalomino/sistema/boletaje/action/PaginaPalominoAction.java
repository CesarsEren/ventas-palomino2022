package pe.com.grupopalomino.sistema.boletaje.action;

import java.io.InputStream;   
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import pe.com.grupopalomino.sistema.boletaje.bean.B_PreguntasFrecuentesBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_ProgramacionSalidaBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_AgenciasBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_CiudadesBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_DestinosAgenciasWebBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_DestinosMapaBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Varios;
import pe.com.grupopalomino.sistema.boletaje.formviews.VentaPaso1Form;
import pe.com.grupopalomino.sistema.boletaje.formviews.VentaPaso2Form;
import pe.com.grupopalomino.sistema.boletaje.service.AgenciasService;
import pe.com.grupopalomino.sistema.boletaje.service.AgenciasServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.CiudadesService;
import pe.com.grupopalomino.sistema.boletaje.service.CiudadesServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.DestinosAgenciasWebService;
import pe.com.grupopalomino.sistema.boletaje.service.DestinosAgenciasWebServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.DestinosMapaService;
import pe.com.grupopalomino.sistema.boletaje.service.DestinosMapaServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.PreguntasFrecuentesService;
import pe.com.grupopalomino.sistema.boletaje.service.PreguntasFrecuentesServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.ProgramacionSalidaService;
import pe.com.grupopalomino.sistema.boletaje.service.ProgramacionSalidaServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.UsuariosWebService;
import pe.com.grupopalomino.sistema.boletaje.service.UsuariosWebServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.VariosService;
import pe.com.grupopalomino.sistema.boletaje.service.VariosServiceI;
import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityUser;
import pe.com.grupopalomino.sistema.boletaje.util.GenerarEmailPreguntas;
import pe.com.grupopalomino.sistema.boletaje.util.Regex;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

@SuppressWarnings("serial")
@ParentPackage(value = "BoletajePalomino03")
public class PaginaPalominoAction extends ActionSupport implements SessionAware,ServletResponseAware/*,ServletRequestAware*/ {
	
	private String origenIda,origenCiudad,destinoCiudad,destinoIda, fechaIda, fechaVuelta, mensajeServer, cantidadMaximaPasajeros;
	private String origenIdaDescripcion, destinoIdaDescripcion,ciudad,tipo,nombre,email,coment,telefono;
	private int nroProgramacion;
	private String hora;
	private boolean error, idaVuelta;
	private HttpServletResponse response;
	private Map<String, Object> session;
	//private HttpServletRequest request;
	private CiudadesService ciudades = new CiudadesServiceI();
	private AgenciasService agencias = new AgenciasServiceI();
	private UsuariosWebService usuarioService = new UsuariosWebServiceI();
	private DestinosMapaService destinosmapaservice = new DestinosMapaServiceI();
	private PreguntasFrecuentesService preguntas = new PreguntasFrecuentesServiceI();
	private DestinosAgenciasWebService destinoAgenciasService = new DestinosAgenciasWebServiceI();
	private ProgramacionSalidaService programacionSalidaService = new ProgramacionSalidaServiceI();
	private List<Map<String, Object>> listaCiudades = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> listaAgencias = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> listaAgentesCiudades = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> listaAgentes = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> listaOrigenCombo = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> listaDestinoCombo = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> destinosmapa = new ArrayList<Map<String, Object>>();
	private List<V_Varios> listacantidadPasajeros = new ArrayList<V_Varios>();
	private Map<String, Object> salidasmapaIda  = new HashMap<>();
	private Map<String, Object> salidasmapaVuelta  = new HashMap<>();
	private VentaPaso1Form paso1Form = new VentaPaso1Form();
	private String url= "";
	private static final Log log = LogFactory.getLog(PaginaPalominoAction.class);
	private String recaptcharesponse;
	
	@Action(value = "accedemantenimiento", results = {@Result(name = SUCCESS, location = "sistema/clientes/paginamantenimiento.jsp")})
	public String accedePaginaMantenimiento(){
		
		//TODO: CAMBIAR A http://www.grupopalomino.com.pe CUANDO SE PASE A PRODUCCION
		//response.addHeader("Access-Control-Allow-Origin", "http://www.grupopalomino.com.pe");
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST");
		return SUCCESS;
	}
	
	
	@Action(value = "preguntasfrecuentes", results = {@Result(name = SUCCESS, type = "json")})
	public String preguntasfrecuentes(){
		
		//response.addHeader("Access-Control-Allow-Origin", "http://www.grupopalomino.com.pe");
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST");
		
		try {
			
			int resultado = -1;
			
			if((nombre == null || nombre.trim().isEmpty()) ||  (email == null || email.trim().isEmpty()) || (coment == null || coment.trim().isEmpty())|| (telefono == null || telefono.trim().isEmpty())){
				mensajeServer = "Todos los campos (*) son obligatorios.";
				error = true;
				return SUCCESS;
			}
			if(!nombre.trim().matches(Regex.NOMBRES)){
				mensajeServer = "El nombre no tiene el formato correcto.";
				error = true;
				return SUCCESS;
			}
			if(!telefono.trim().matches(Regex.TELEFONO)){
				mensajeServer = "El telefono solo puede contener numeros.";
				error = true;
				return SUCCESS;
			}
			if(!email.trim().matches(Regex.EMAIL)){
				mensajeServer = "El correo no tiene el formato correcto.";
				error = true;
				return SUCCESS;
			}
			if(coment.trim().length()> 290){
				mensajeServer = "La cantidad maxima para la pregunta es de 290 caracteres.";
				error = true;
				return SUCCESS;
			}
			if(!(VerificaCaptcha(recaptcharesponse))){
				error = true;
				return SUCCESS;
			}
			
			B_PreguntasFrecuentesBean bean = new B_PreguntasFrecuentesBean();
			
			bean.setNombre(nombre);
			bean.setEmail(email);
			bean.setTelefono(telefono);
			bean.setPregunta(coment);
			
			B_PreguntasFrecuentesBean verificaenvios = null;
			
			verificaenvios = preguntas.VerificaPreguntas(email);
			
			if(verificaenvios != null){
				error = true;
				mensajeServer = verificaenvios.getMensaje();
				return SUCCESS;
			}
			
			resultado = preguntas.insertPreguntasFrecuentes(bean);
			
			if(resultado == -1){
				error = true;
				mensajeServer = "Ocurrio un problema al enviar sus preguntas, por favor inténtelo más tarde.";
				return SUCCESS;
			}
			
			VariosService servicevarios = new VariosServiceI(); 
			V_Varios beanvarios = new  V_Varios();
			
			beanvarios = servicevarios.Select_Varios();
			
			Map<String, String> parametros = new HashMap<String, String>();
			parametros.put("subject","PREGUNTAS FRECUENTES - GRUPOPALOMINO");
			parametros.put("to",beanvarios.getCorreoPreguntasFrecuentes());
			parametros.put("cc",beanvarios.getCorreoPreguntasFrecuentesCC());
			parametros.put("header", "¿Alguna pregunta adicional?");
			
			StringBuilder sb = new StringBuilder();
			sb.append("<b>Nombre:</b>"+ nombre +"<br></br><br></br>");
			sb.append("<b>Correo Electronico:</b>"+ email+"<br></br><br></br>");
			sb.append("<b>Telefono:</b>"+ telefono+"<br></br><br></br>");
			sb.append("<b>Pregunta:</b>"+ coment);
			parametros.put("body", sb.toString());
			
			GenerarEmailPreguntas.enviarCorreoPreguntasFrecuentes(parametros, null);
			mensajeServer = "Su mensaje ha sido enviado, Gracias te responderemos a la brevedad.";
			error = false;
			
		} catch (Exception e) {
			error = true;
			mensajeServer = "Ocurrio un problema con el servicio, por favor inténtelo más tarde.";
			log.info(Utils.printStackTraceToString(e));
		}
		
		
		return SUCCESS;
	}
	
	private boolean VerificaCaptcha(String recaptcharesponse){
		
		boolean respuesta = false;
		
		if(recaptcharesponse == null || recaptcharesponse.trim().isEmpty()){
			mensajeServer = "Por favor comprobar que no es un robot.";
			respuesta = false;
			return respuesta;
		}
		
		try {
			
			URL obj = new URL(Utils.SITE_VERIFY_URL);
			
			HttpsURLConnection conn = (HttpsURLConnection) obj.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0");
	        conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			
	        // Data will be sent to the server.
            String postParams = "secret=" +Utils.SECRET_KEY + "&response=" + recaptcharesponse;
 
            // Send Request
            conn.setDoOutput(true);
 
            // Get the output stream of Connection.
            // Write data in this stream, which means to send data to Server.
            OutputStream outStream = conn.getOutputStream();
            outStream.write(postParams.getBytes());
 
            outStream.flush();
            outStream.close();
 
            // Response code return from Server.
            int responseCode = conn.getResponseCode();
            
            log.info("STATUS DE GOOGLE "+responseCode);
            
            if(responseCode == HttpsURLConnection.HTTP_OK){
            	
                // Get the Input Stream of Connection to read data sent from the Server.
            	 InputStream is = conn.getInputStream();
            	 ObjectMapper mapper = new ObjectMapper();
                 JsonNode json = mapper.readTree(is);
                 
                 log.info("LOS DATOS RECIBIDOS DE GOOGLE STATUS : "+ HttpsURLConnection.HTTP_OK+ " JSON :" +json);
                 
                 if(json.get("success").getBooleanValue()){
                 	respuesta =  true;
                 }
                 else{
                	 mensajeServer = "La verificación ha caducado, por favor vuelva a marcar la casilla de captcha.";
                	 respuesta =  false;
                 }
            	
            }else{
            	log.info("RESPUESTA DE GOOGLE STATUS : "+HttpsURLConnection.HTTP_OK+" JSON: "+responseCode);
            	mensajeServer = "No se pudo establecer la conexión con los servicios,por favor intentelo más tarde.";
            	return false;
            }
            
		} catch (Exception e) {
			respuesta = false;
			mensajeServer = "Ocurrio un problema con el servicio, por favor inténtelo más tarde.";
			log.info(Utils.printStackTraceToString(e));
		}
		return respuesta;
	}
	
	
	
	@Action(value = "destinosciudades", results = {@Result(name = SUCCESS, type = "json")})
	public String destinos(){
		
		try {
			
			List<V_DestinosAgenciasWebBean> dataOrigen = destinoAgenciasService.ListaDestinosAgencias();
					
					for (V_DestinosAgenciasWebBean bean : dataOrigen) {
							if(bean.getOrigen().trim().substring(0, 3).equals(origenCiudad)){
								Map<String, Object> mapaJSONResultadoOrigen  = new HashMap<>();
								mapaJSONResultadoOrigen.put("id", bean.getOrigen());
								mapaJSONResultadoOrigen.put("text", bean.getOrigenD());
								listaOrigenCombo.add(mapaJSONResultadoOrigen);
							}
						}
					
					for (V_DestinosAgenciasWebBean bean : dataOrigen) {
						if(!bean.getOrigen().trim().substring(0, 3).equals(origenCiudad)){
							Map<String, Object> mapaJSONResultadoOrigen  = new HashMap<>();
							mapaJSONResultadoOrigen.put("id", bean.getOrigen());
							mapaJSONResultadoOrigen.put("text", bean.getOrigenD());
							listaOrigenCombo.add(mapaJSONResultadoOrigen);
						}
					}
					
					//********************************************************************************************
					
					for (V_DestinosAgenciasWebBean bean : dataOrigen){
						if(bean.getOrigen().length()== 3 && bean.getOrigen().trim().equals(destinoCiudad)){
								Map<String, Object> mapaJSONResultadoDestino  = new HashMap<>();
								mapaJSONResultadoDestino.put("id", bean.getOrigen());
								mapaJSONResultadoDestino.put("text", bean.getCiudadD());
								listaDestinoCombo.add(mapaJSONResultadoDestino);
								break;
						}
					}
					for (V_DestinosAgenciasWebBean bean : dataOrigen){
						if(bean.getOrigen().length()== 3 && !bean.getOrigen().trim().equals(destinoCiudad)){
							Map<String, Object> mapaJSONResultadoDestino  = new HashMap<>();
							mapaJSONResultadoDestino.put("id", bean.getOrigen());
							mapaJSONResultadoDestino.put("text", bean.getCiudadD());
							listaDestinoCombo.add(mapaJSONResultadoDestino);
						}
					}
					listacantidadPasajeros = Utils.getListaCantidadPasajeros();
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		
		//response.addHeader("Access-Control-Allow-Origin", "http://www.grupopalomino.com.pe");
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET");
		
		return SUCCESS;
		
	}

	@Action(value = "verificadisponibilidad", results = {@Result(name = SUCCESS, type = "json")})
	public String verificaDisponibilidad1(){
		
		error = false;
		V_DestinosAgenciasWebBean destinoBeanIDA = new V_DestinosAgenciasWebBean();
		
		//response.addHeader("Access-Control-Allow-Origin", "http://www.grupopalomino.com.pe");
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST");
		
		//log.info("PARAMETROS origenIda = "+ origenIda +" - destinoIda="+destinoIda+" - fechaida ="+fechaIda+" - idavuelta="+idaVuelta+" - fechavuelta="+fechaVuelta);
		
		if(origenIda == null || origenIda.trim().isEmpty()){
			error = true;
			mensajeServer = "Seleccione un Origen de Viaje.";
			return SUCCESS;
		} 
		
		if(destinoIda == null || destinoIda.trim().isEmpty()){
			error = true;
			mensajeServer = "Seleccione un Destino de Viaje.";
			return SUCCESS;
		}
		
		if(fechaIda == null || fechaIda.trim().isEmpty()){
			error = true;
			mensajeServer = "Seleccione una fecha de ida.";
			return SUCCESS;
		}
		
		else if(!fechaIda.replace("/", "").matches(Regex.FECHA)){
			error = true;
			mensajeServer = "La fecha de ida seleccionada no tiene el formato correcto.";
			return SUCCESS;
		}else if(!Utils.esFechaPosteriorActual(fechaIda)){
			error = true;
			mensajeServer = "La fecha de ida no puede ser anterior a la actual.";
			return SUCCESS;
			
		}
		if(idaVuelta){
			if(fechaVuelta == null || fechaVuelta.trim().isEmpty()){
				error = true;
				mensajeServer = "Seleccione una fecha de regreso.";
				return SUCCESS;
			}
			if(!fechaVuelta.replace("/", "").matches(Regex.FECHA)){
				error = true;
				mensajeServer = "La fecha de vuelta seleccionada no tiene el formato correcto.";
				return SUCCESS;
			}
			else if(!Utils.esFechaIdaMenorFechaVuelta(fechaIda, fechaVuelta)){
				error = true;
				mensajeServer = "La fecha de salida no puede ser mayor a la fecha de retorno.";
				return SUCCESS;
			}
		}
		
		if(!error){
			try {
				 //destinoBeanIDA = destinoService.getDestinoXIdaYVuelta(origenIda.substring(0, 3), destinoIda);
				 destinoBeanIDA = destinoAgenciasService.obtieneDestinosAgencias(origenIda.substring(0,3), destinoIda);
				
				if(destinoBeanIDA != null){
					
					if(programacionSalidaService.getSalidas(Utils.FormatoFecha(fechaIda),"X",origenIda,destinoIda,0).size() == 0){
						error = true;
						mensajeServer = "No existe Programaciones de Destino en la fecha indicada.";
						return SUCCESS;
					}
				}
				else{
					error = true;
					mensajeServer = "No existen viajes disponibles para el destino seleccionado.";
					return SUCCESS;
				}
				
				if(idaVuelta){
					
					V_DestinosAgenciasWebBean destinoVueltaBean = null;
					
					destinoVueltaBean = destinoAgenciasService.obtieneDestinosAgencias(destinoIda, origenIda.substring(0,3));
					
					if(destinoVueltaBean == null){
						error = true;
						mensajeServer="El destino seleccionado no tiene un viaje de regreso.";
						return SUCCESS;
					}
					
					if(programacionSalidaService.getSalidas(Utils.FormatoFecha(fechaVuelta),"X",destinoIda,origenIda.substring(0,3),0).size() == 0){
						error = true;
						mensajeServer = "No existe Programaciones de Destino en la fecha de regreso.";
						return SUCCESS;
					}
				}
			} 
			catch (Exception e) {
				error = true;
				mensajeServer = "Ocurrio un problema en el servicio, por favor inténtelo más tarde.";
				log.info(Utils.printStackTraceToString(e));
				return SUCCESS;
				
			}
		}
		
		//log.info("Todo ok, generando ventapaso1form de la pagina principal");
		
		{
			VentaPaso1Form paso1Form = new VentaPaso1Form();
			paso1Form.setCantidadMaximaPasajeros(cantidadMaximaPasajeros);
			paso1Form.setOrigenIda(origenIda);
			paso1Form.setFechaIda(fechaIda);
			paso1Form.setDestinoIda(destinoIda);
			paso1Form.setIdaVuelta(idaVuelta);
			paso1Form.setOrigenDescripcion(destinoBeanIDA.getOrigenD());
			paso1Form.setDestinoDescripcion(destinoBeanIDA.getDestinoD());
			
			if(idaVuelta){
				paso1Form.setFechaVuelta(fechaVuelta);
			}
			else{
				paso1Form.setFechaVuelta("");
			}
			

			url = "paso1Form.cantidadMaximaPasajeros="+paso1Form.getCantidadMaximaPasajeros()+
					 "&paso1Form.origenIda="+paso1Form.getOrigenIda()+
					 "&paso1Form.fechaIda="+paso1Form.getFechaIda()+
					 "&paso1Form.destinoIda="+paso1Form.getDestinoIda()+
					 "&paso1Form.idaVuelta="+String.valueOf(paso1Form.isIdaVuelta())+
					 "&paso1Form.origenDescripcion="+paso1Form.getOrigenDescripcion()+
					 "&paso1Form.destinoDescripcion="+paso1Form.getDestinoDescripcion();

					if(idaVuelta){
					paso1Form.setFechaVuelta(fechaVuelta);
					url = url + "&paso1Form.fechaVuelta="+paso1Form.getFechaVuelta();
					}
					else{
					paso1Form.setFechaVuelta("");
					url = url + "&paso1Form.fechaVuelta=";
					}

					url.trim();
					session.put("paso1Form", paso1Form);
			//log.info("URL : "+url);
			//log.info("CANTIDAD DE PASAJEROS  "+paso1Form.getCantidadMaximaPasajeros());
		}
		
		return SUCCESS;
	}
	
	@Action(value = "redireccionadisponibilidad", results = {@Result(name = SUCCESS, type = "redirectAction", location = "paso2"),
															 @Result(name = ERROR, location = "inicio.jsp")})
	public String redireccionaDisponibilidad(){
		
	
		try {
			//response.addHeader("Access-Control-Allow-Origin", "http://www.grupopalomino.com.pe");
			response.addHeader("Access-Control-Allow-Origin", "172.16.10.235");
			response.addHeader("Access-Control-Allow-Origin", "*");
			response.addHeader("Access-Control-Allow-Methods", "POST");
			
			limpiaDatosVenta();
			
			
		} catch (Exception e) {
			log.info("ENTRANDO AL CATCH REDIRECCIONAMIENTO DEL PASO 2");
			log.info(Utils.printStackTraceToString(e));
			return ERROR;
		}



		session.put("paso1Form", paso1Form);
		
		//log.info("Se esta redireccionando ....");
		
		return SUCCESS;
	}
	@Action(value = "/ListaProgramacionCiudadesIda", results = { @Result(name = "success", type = "json") })
	public String ListaProgramacionCiudadesIda() {
		
		try {
					//List<String> parameterNames = new ArrayList<String>(request.getParameterMap().keySet());
			
					//log.info("PARAMETROS EN EL METODO LISTA DE SALIDAS IDA --> "+parameterNames.toString());
					//log.info("ORIGENCIUDAD .."+origenCiudad);
					//log.info("DESTINOCIUDAD .."+destinoCiudad);
					//log.info("FECHA .."+fechaIda);
					
					V_DestinosAgenciasWebBean destinos = destinoAgenciasService.obtieneDestinosAgencias(origenCiudad, destinoCiudad);
					
					List<Map<String, Object>> lstAuxiliar = new ArrayList<>();
					List<B_ProgramacionSalidaBean>  data = new ArrayList<B_ProgramacionSalidaBean>();
					
					if(destinos != null){
						
						data = programacionSalidaService.getSalidasCiudades(Utils.FormatoFecha(fechaIda),origenCiudad,destinoCiudad);
						
						for (B_ProgramacionSalidaBean bean : data) {
							Map<String, Object> mapaAuxiliar = new HashMap<>();
							mapaAuxiliar.put("nro", bean.getNro());
							mapaAuxiliar.put("destino", bean.getDestino());
							mapaAuxiliar.put("servicio", bean.getServicio());
							mapaAuxiliar.put("servicioD", bean.getServicioD());
							mapaAuxiliar.put("agenciaD", bean.getAgenciaD());
							mapaAuxiliar.put("hora", bean.getHora());
							mapaAuxiliar.put("precio1", bean.getPrecio1());
							mapaAuxiliar.put("precio2", bean.getPrecio2());
							mapaAuxiliar.put("bus", bean.getBus());
							mapaAuxiliar.put("fechapartida", bean.getFechaPartida());
							mapaAuxiliar.put("fechallegada", bean.getFechaLlegada());
							mapaAuxiliar.put("id", bean.getNro());
							mapaAuxiliar.put("descuento1", bean.getDescuento1());
							mapaAuxiliar.put("descuento2", bean.getDescuento2());
							lstAuxiliar.add(mapaAuxiliar);
						}
						
					}else{
						log.info("NO EXISTE EL DESTINO (IDA) !!! VERIFICAR ORIGENCIUDAD : "+origenCiudad+" DESTINOCIUDAD :"+destinoCiudad);
					}
						
					salidasmapaIda.put("rows", lstAuxiliar);
					salidasmapaIda.put("total", data.size());
					//response.addHeader("Access-Control-Allow-Origin", "http://www.grupopalomino.com.pe");
					response.addHeader("Access-Control-Allow-Origin", "*");
					response.addHeader("Access-Control-Allow-Methods", "GET");
					response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
				
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		return SUCCESS;
	}
	@Action(value = "/ListaProgramacionCiudadesVuelta", results = { @Result(name = "success", type = "json") })
	public String ListaProgramacionCiudadesVuelta() {
		
		try {
					
					//log.info("PARAMETROS EN LA LISTA DE REGRESO ....");
					//log.info("ORIGENCIUDAD .."+origenCiudad);
					//log.info("DESTINOCIUDAD .."+destinoCiudad);
					//log.info("FECHA .."+fechaVuelta);
					
					V_DestinosAgenciasWebBean destinos = destinoAgenciasService.obtieneDestinosAgencias(origenCiudad, destinoCiudad);
					
					List<Map<String, Object>> lstAuxiliar = new ArrayList<>();
					List<B_ProgramacionSalidaBean>  data = new ArrayList<>();
					
					if(destinos != null){
						
						data = programacionSalidaService.getSalidasCiudades(Utils.FormatoFecha(fechaVuelta),origenCiudad,destinoCiudad);
						
						for (B_ProgramacionSalidaBean bean : data) {
							Map<String, Object> mapaAuxiliar = new HashMap<>();
							mapaAuxiliar.put("nro", bean.getNro());
							mapaAuxiliar.put("destino", bean.getDestino());
							mapaAuxiliar.put("servicio", bean.getServicio());
							mapaAuxiliar.put("servicioD", bean.getServicioD());
							mapaAuxiliar.put("agenciaD", bean.getAgenciaD());
							mapaAuxiliar.put("hora", bean.getHora());
							mapaAuxiliar.put("precio1", bean.getPrecio1());
							mapaAuxiliar.put("precio2", bean.getPrecio2());
							mapaAuxiliar.put("bus", bean.getBus());
							mapaAuxiliar.put("fechapartida", bean.getFechaPartida());
							mapaAuxiliar.put("fechallegada", bean.getFechaLlegada());
							mapaAuxiliar.put("id", bean.getNro());
							mapaAuxiliar.put("descuento1", bean.getDescuento1());
							mapaAuxiliar.put("descuento2", bean.getDescuento2());
							lstAuxiliar.add(mapaAuxiliar);
						}
						
					}else{
						log.info("NO EXISTE EL DESTINO(RETORNO) !!! VERIFICAR ORIGENCIUDAD : "+origenCiudad+" DESTINOCIUDAD :"+destinoCiudad);
					}
					
					salidasmapaVuelta.put("rows", lstAuxiliar);
					salidasmapaVuelta.put("total", data.size());
					//response.addHeader("Access-Control-Allow-Origin", "http://www.grupopalomino.com.pe");
					response.addHeader("Access-Control-Allow-Origin", "*");
					response.addHeader("Access-Control-Allow-Methods", "GET");
					response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
					
				
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		return SUCCESS;
	}
	@Action(value = "redireccionacroquis", results = {@Result(name = SUCCESS, type = "redirectAction", location = "paso3"),
			 @Result(name = ERROR, location = "inicio.jsp")})
	public String redireccionacroquis(){
						
						//List<String> parameterNames = new ArrayList<String>(request.getParameterMap().keySet());
				
						//response.addHeader("Access-Control-Allow-Origin", "http://www.grupopalomino.com.pe");
						response.addHeader("Access-Control-Allow-Origin", "*");
						response.addHeader("Access-Control-Allow-Methods", "POST");
						
						//log.info("PARAMETROS DESDE LA PAGINA --> "+parameterNames.toString());
						//log.info("PROGRAMACION ..."+nroProgramacion);
						//log.info("ORIGENCIUDAD ..."+origenCiudad);
						//log.info("DESTINOCIUDAD ..."+destinoCiudad);
						//log.info("HORA ..."+hora);
						//log.info("FECHA ..."+fechaIda);
					
						
						
					try {
						
						B_ProgramacionSalidaBean salidas =  programacionSalidaService.getSalidasPrecioPromocion(nroProgramacion,destinoCiudad,0);
						
						VentaPaso1Form paso1 = new VentaPaso1Form();
						VentaPaso2Form paso2FormIDA = new VentaPaso2Form();
						
						
						if(salidas!= null){
							
							//V_DestinosBean destinos  = new V_DestinosBean();
							V_DestinosAgenciasWebBean destinos = new V_DestinosAgenciasWebBean();
							//destinos = destinosservice.getDestinoNro(salidas.getDestino());
							
							destinos = destinoAgenciasService.obtieneDestinosAgencias(origenCiudad, destinoCiudad);
							
							paso1.setIdaVuelta(false);
							paso1.setCantidadMaximaPasajeros(cantidadMaximaPasajeros);
							paso1.setOrigenIda(destinos.getOrigen());
							paso1.setFechaIda(fechaIda);
							paso1.setDestinoIda(destinos.getDestino());
							paso1.setRolUser(""); 
							paso1.setNroDestinoIDA(salidas.getDestino());
							paso1.setOrigenDescripcion(destinos.getOrigenD());
							paso1.setDestinoDescripcion(destinos.getDestinoD());
							paso2FormIDA.setNroProgramacion(salidas.getNro());
							paso2FormIDA.setNroServicio(salidas.getServicio());
							paso2FormIDA.setNroDestino(String.valueOf(salidas.getDestino())); 
							paso2FormIDA.setDescripcionServicioIda(salidas.getServicioD());
							paso2FormIDA.setNroBus(salidas.getBus());
							paso2FormIDA.setHora1Ida(hora);
						}else{
							log.info("EL NRO DE PROGRAMACION NO EXISTE !!! NRO : "+ nroProgramacion);
							return ERROR;
							
						}
						//log.info("ENVIANDO DATOS paso2FormIDA ..."+paso2FormIDA);
						//log.info("ENVIANDO DATOS paso1        ..."+paso1);
						session.put("paso1Form", paso1);
						session.put("paso2Form", paso2FormIDA);
						
						
					}catch(Exception e){
						log.info("ENTRANDO AL CATHC NRO PROGRAMACION CROQUIS : "+nroProgramacion);
						log.info(Utils.printStackTraceToString(e));
						return ERROR;
					}
					
					return SUCCESS;
	}
	
	@Action(value = "agenciasviajes",results={@Result(name = SUCCESS,type="json")})
	public String agenciasviajes(){
		
		
		try {
			
			List<V_AgenciasBean> data = new ArrayList<>();
			//System.out.println("VALOR DEL TIPO DE AGENCIAS "+tipo);
				
				if(tipo.trim().equals("A")){
					
					List<SpringSecurityUser> data2 = new ArrayList<>();
					
					data2 = usuarioService.listaUsuariosAgentesCiudad(ciudad);
					
					for (SpringSecurityUser bean : data2) {
						Map<String, Object> mapaAuxiliar = new HashMap<>();
						mapaAuxiliar.put("id", bean.getId());
						mapaAuxiliar.put("text", bean.getNombreCompleto());
						mapaAuxiliar.put("lat", bean.getLatitud());
						mapaAuxiliar.put("lon", bean.getLongitud());
						mapaAuxiliar.put("dir", bean.getDireccion());
						mapaAuxiliar.put("tel", bean.getTelefono());
						listaAgentes.add(mapaAuxiliar);
					}
					
				
				}else{
					
					data = agencias.getListaAgenciasDisponibles(ciudad,(tipo == null || tipo.trim().isEmpty() ? "T": tipo));
					
					for (V_AgenciasBean bean : data) {
						Map<String, Object> mapaAuxiliar = new HashMap<>();
						mapaAuxiliar.put("id", bean.getCodigo());
						mapaAuxiliar.put("text", bean.getDetalle());
						mapaAuxiliar.put("lat", bean.getLatitud());
						mapaAuxiliar.put("lon", bean.getLongitud());
						mapaAuxiliar.put("dir", bean.getDireccion());
						mapaAuxiliar.put("tel", bean.getTelefono());
						listaAgencias.add(mapaAuxiliar);
					}
				}
		
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		//response.addHeader("Access-Control-Allow-Origin", "http://www.grupopalomino.com.pe");
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET");
		
		return SUCCESS;
		
	}
	
	@Action(value = "ciudadesviajes",results = {@Result(name = SUCCESS,type = "json")})
	public String ciudadesviajes(){
		
		try {
			
			List<V_CiudadesBean> data= new ArrayList<>();
			
			if(tipo!= null && !tipo.trim().isEmpty() && tipo.trim().equals("A")){
				
					if(ciudad == null || ciudad.trim().isEmpty()){
						
						List<SpringSecurityUser> agenteciudad = new ArrayList<>();
						
						agenteciudad = usuarioService.listaUsuariosAgentes();
						
						for (SpringSecurityUser bean : agenteciudad) {
							Map<String, Object> mapaAuxiliar = new HashMap<>();
							mapaAuxiliar.put("id", bean.getCiudad());
							mapaAuxiliar.put("text", bean.getCiudadd());
							listaAgentesCiudades.add(mapaAuxiliar);
						}
						
						List<SpringSecurityUser> data2 = new ArrayList<>();
						
						data2 = usuarioService.listaUsuariosAgentesCiudad(agenteciudad.get(0).getCiudad());
						
						for (SpringSecurityUser bean : data2) {
							Map<String, Object> mapaAuxiliar = new HashMap<>();
							mapaAuxiliar.put("id", bean.getId());
							mapaAuxiliar.put("text", bean.getNombreCompleto());
							mapaAuxiliar.put("lat", bean.getLatitud());
							mapaAuxiliar.put("lon", bean.getLongitud());
							mapaAuxiliar.put("dir", bean.getDireccion());
							mapaAuxiliar.put("tel", bean.getTelefono());
							listaAgentes.add(mapaAuxiliar);
						}
					}
				
			}else{
				
					data = ciudades.SelectCiudades((tipo == null || tipo.trim().isEmpty() ? "T": tipo));
					
					for (V_CiudadesBean bean : data) {
						Map<String, Object> mapaAuxiliar = new HashMap<>();
						mapaAuxiliar.put("id", bean.getCodigo());
						mapaAuxiliar.put("text", bean.getDetalle());
						listaCiudades.add(mapaAuxiliar);
					}
					
					List<V_AgenciasBean> data2 = new ArrayList<>();
					
					data2 = agencias.getListaAgenciasDisponibles(data.get(0).getCodigo(),(tipo == null || tipo.trim().isEmpty() ? "T": tipo));
					
					for (V_AgenciasBean bean : data2) {
						Map<String, Object> mapaAuxiliar = new HashMap<>();
						mapaAuxiliar.put("id", bean.getCodigo());
						mapaAuxiliar.put("text", bean.getDetalle());
						mapaAuxiliar.put("lat", bean.getLatitud());
						mapaAuxiliar.put("lon", bean.getLongitud());
						mapaAuxiliar.put("dir", bean.getDireccion());
						mapaAuxiliar.put("tel", bean.getTelefono());
						listaAgencias.add(mapaAuxiliar);
					}
				
			}
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		//response.addHeader("Access-Control-Allow-Origin", "http://www.grupopalomino.com.pe");
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET");
		return SUCCESS;
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Action(value = "destinosmapa",results = {@Result(name = SUCCESS,type="json")})
	public String destinosmapa(){
		
		try {
			
			List<V_DestinosMapaBean> lista = new ArrayList<>();
			lista = destinosmapaservice.destinosmapa();
			
			for (V_DestinosMapaBean bean : lista) {
				
				Map<String, Object> mapa = new HashMap<>();
				JSONObject properties = new JSONObject();
				
				properties.put("hc-middle-x", 0.49);
				properties.put("hc-middle-y", 0.52);
				properties.put("hc-key", bean.getHc_key());
				properties.put("class", bean.getHc_key());
				properties.put("name", bean.getDestinoD());
				properties.put("href", bean.getHref());
				
				
				JSONObject geometry = new JSONObject();
				
				String data[] = bean.getCoordenadas().split(",");
				List<int [][]> coordinates  =  new ArrayList<int [][]>();
				
				int [][] auxi = {{Integer.parseInt(data[0]),Integer.parseInt(data[1])}};
				 
				coordinates.add(auxi);
				mapa.put("id", "PE."+bean.getHc_key().substring(3));
				mapa.put("type", "Feature");
				mapa.put("properties", properties);
				geometry.put("type", "Polygon");
				geometry.put("coordinates", coordinates);
				
				mapa.put("geometry", geometry);
				
				destinosmapa.add(mapa);
				
			}
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		//response.addHeader("Access-Control-Allow-Origin", "http://www.grupopalomino.com.pe");
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET");
		return SUCCESS;
	}

	public String getOrigenIda() {
		return origenIda;
	}
	public void setOrigenIda(String origenIda) {
		this.origenIda = origenIda;
	}
	public String getDestinoIda() {
		return destinoIda;
	}
	public void setDestinoIda(String destinoIda) {
		this.destinoIda = destinoIda;
	}
	public String getFechaIda() {
		return fechaIda;
	}
	public void setFechaIda(String fechaIda) {
		this.fechaIda = fechaIda;
	}
	public String getFechaVuelta() {
		return fechaVuelta;
	}
	public void setFechaVuelta(String fechaVuelta) {
		this.fechaVuelta = fechaVuelta;
	}
	public String getCantidadMaximaPasajeros() {
		return cantidadMaximaPasajeros;
	}
	public void setCantidadMaximaPasajeros(String cantidadMaximaPasajeros) {
		this.cantidadMaximaPasajeros = cantidadMaximaPasajeros;
	}
	public String getMensajeServer() {
		return mensajeServer;
	}
	public void setMensajeServer(String mensajeServer) {
		this.mensajeServer = mensajeServer;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public boolean isIdaVuelta() {
		return idaVuelta;
	}
	public void setIdaVuelta(boolean idaVuelta) {
		this.idaVuelta = idaVuelta;
	}
	public String getDestinoIdaDescripcion() {
		return destinoIdaDescripcion;
	}
	public void setDestinoIdaDescripcion(String destinoIdaDescripcion) {
		this.destinoIdaDescripcion = destinoIdaDescripcion;
	}
	public String getOrigenIdaDescripcion() {
		return origenIdaDescripcion;
	}
	public void setOrigenIdaDescripcion(String origenIdaDescripcion) {
		this.origenIdaDescripcion = origenIdaDescripcion;
	}
	
	public void setPaso1Form(VentaPaso1Form paso1Form) {
		this.paso1Form = paso1Form;
	}
	public VentaPaso1Form getPaso1Form() {
		return paso1Form;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrl() {
		return url;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getTipo() {
		return tipo;
	}
	
	public void setListaAgentes(List<Map<String, Object>> listaAgentes) {
		this.listaAgentes = listaAgentes;
	}
	public List<Map<String, Object>> getListaAgentes() {
		return listaAgentes;
	}
	public void setListaAgentesCiudades(List<Map<String, Object>> listaAgentesCiudades) {
		this.listaAgentesCiudades = listaAgentesCiudades;
	}
	public List<Map<String, Object>> getListaAgentesCiudades() {
		return listaAgentesCiudades;
	}
	public void setListaCiudades(List<Map<String, Object>> listaCiudades) {
		this.listaCiudades = listaCiudades;
	}
	public List<Map<String, Object>> getListaCiudades() {
		return listaCiudades;
	}
	
	public void setListaAgencias(List<Map<String, Object>> listaAgencias) {
		this.listaAgencias = listaAgencias;
	}
	public List<Map<String, Object>> getListaAgencias() {
		return listaAgencias;
	}
	
	public void setDestinosmapa(List<Map<String, Object>> destinosmapa) {
		this.destinosmapa = destinosmapa;
	}
	public List<Map<String, Object>> getDestinosmapa() {
		return destinosmapa;
	}
	public void setSalidasmapaIda(Map<String, Object> salidasmapaIda) {
		this.salidasmapaIda = salidasmapaIda;
	}
	public Map<String, Object> getSalidasmapaIda() {
		return salidasmapaIda;
	}
	public void setSalidasmapaVuelta(Map<String, Object> salidasmapaVuelta) {
		this.salidasmapaVuelta = salidasmapaVuelta;
	}
	public Map<String, Object> getSalidasmapaVuelta() {
		return salidasmapaVuelta;
	}
	public void setNroProgramacion(int nroProgramacion) {
		this.nroProgramacion = nroProgramacion;
	}
	public int getNroProgramacion() {
		return nroProgramacion;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getHora() {
		return hora;
	}
	
	public void setOrigenCiudad(String origenCiudad) {
		this.origenCiudad = origenCiudad;
	}
	public String getOrigenCiudad() {
		return origenCiudad;
	}
	
	public void setDestinoCiudad(String destinoCiudad) {
		this.destinoCiudad = destinoCiudad;
	}
	public String getDestinoCiudad() {
		return destinoCiudad;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setComent(String coment) {
		this.coment = coment;
	}
	public String getComent() {
		return coment;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getTelefono() {
		return telefono;
	}
	
	public void setRecaptcharesponse(String recaptcharesponse) {
		this.recaptcharesponse = recaptcharesponse;
	}
	public String getRecaptcharesponse() {
		return recaptcharesponse;
	}
	
	public void setListaOrigenCombo(List<Map<String, Object>> listaOrigenCombo) {
		this.listaOrigenCombo = listaOrigenCombo;
	}
	public List<Map<String, Object>> getListaOrigenCombo() {
		return listaOrigenCombo;
	}
	public List<Map<String, Object>> getListaDestinoCombo() {
		return listaDestinoCombo;
	}
	public void setListaDestinoCombo(List<Map<String, Object>> listaDestinoCombo) {
		this.listaDestinoCombo = listaDestinoCombo;
	}
	
	public void setListacantidadPasajeros(List<V_Varios> listacantidadPasajeros) {
		this.listacantidadPasajeros = listacantidadPasajeros;
	}
	public List<V_Varios> getListacantidadPasajeros() {
		return listacantidadPasajeros;
	}
	private void limpiaDatosVenta(){
		
		session.remove("paso1Form");
		session.remove("paso2FormIDA");
		session.remove("paso2FormVUELTA");
		session.remove("listaCroquisBusIda");
		session.remove("listaCroquisBusVuelta");
		session.remove("minValorIda");
		session.remove("minValorVuelta");
		session.remove("listaPasajeros");
		session.remove("listaPasajerosTabla");
		session.remove("listaPasajerosIdaVuelta");
		session.remove("ListaAuxiliar");
		session.remove("ListaPreventaIda");
		session.remove("ListaPreventaIdaVuelta");
		
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}
	/*@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}*/
	
}
