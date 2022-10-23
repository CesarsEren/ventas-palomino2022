package pe.com.grupopalomino.sistema.boletaje.action;

 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import com.opensymphony.xwork2.ActionSupport;
import pe.com.grupopalomino.sistema.boletaje.bean.B_CuentaCorrienteBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_VentaBean;
import pe.com.grupopalomino.sistema.boletaje.dao.FuncionesGeneralesDao;
import pe.com.grupopalomino.sistema.boletaje.dao.FuncionesGeneralesIDao;
import pe.com.grupopalomino.sistema.boletaje.service.CuentaCorrienteService;
import pe.com.grupopalomino.sistema.boletaje.service.CuentaCorrienteServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.UsuariosWebService;
import pe.com.grupopalomino.sistema.boletaje.service.UsuariosWebServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.VentaBoletaService;
import pe.com.grupopalomino.sistema.boletaje.service.VentaBoletaServiceI;
import pe.com.grupopalomino.sistema.boletaje.spring.security.SpringSecurityUser;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;


@SuppressWarnings("serial")
@ParentPackage(value = "BoletajePalomino03")
@PreAuthorize("hasAnyRole('1','3','2')")
public class ListaReportesAction extends ActionSupport{

	private Map<String, Object> mapaJSONResultadoBoletos  = new HashMap<>();
	private Map<String, Object> mapaJSONResultadoEstadoCuenta  = new HashMap<>();
	private CuentaCorrienteService servicecuentacorriente = new CuentaCorrienteServiceI();
	private VentaBoletaService serviceventa = new VentaBoletaServiceI();
	private String FechaInicial,FechaFinal,Tipo,Usuario;
	private boolean respuestaServer = false;
	private String mensajeServer = "";
	private String query;
	private int limit, offset, total;
	private FuncionesGeneralesDao servicefunciones = new FuncionesGeneralesIDao();
	private List<SpringSecurityUser> listaUsuarios = new ArrayList<SpringSecurityUser>();
	private UsuariosWebService usuarioService = new UsuariosWebServiceI();
	private static final Log log = LogFactory.getLog(ListaReportesAction.class);
	
	@Action(value="/voucher",results = {@Result(name=SUCCESS, location="reportes/pagereimprimirvoucher.jsp")})   
	public String PageReimprimirVoucher(){
		
		return SUCCESS;
	}
	@Action(value="/estadocuenta",results = {@Result(name=SUCCESS, location="reportes/pageestadocuenta.jsp")})   
	public String PageEstadoCuenta(){
		
		SpringSecurityUser usuario = (SecurityContextHolder.getContext().getAuthentication().getName())== null? null : (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		try {
			listaUsuarios = usuarioService.listaUsuariosRuc(usuario.getRuc());
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		
		return SUCCESS;
	}
	
	@Action(value = "/ListaEstadoCuentaCorriente",results ={@Result(name = SUCCESS ,type= "json")})
	public String ListaEstadoCuenta(){
		
		try {
			
			SpringSecurityUser usuario = null;
			List<B_CuentaCorrienteBean>  data = null;
			usuario = (SecurityContextHolder.getContext().getAuthentication().getName())== null? null : (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			if(usuario!=null){
				
				
				if(Tipo.trim().equals("H")){
					
					if(FechaInicial == null || FechaInicial.trim().equals("")){
						
						respuestaServer = true;
						mensajeServer= "Por favor seleccione una fecha Inicial";
						return SUCCESS;
						
					}
					
					if(FechaFinal == null || FechaFinal.trim().equals("")){
						
						respuestaServer = true;
						mensajeServer= "Por favor seleccione una fecha Final";
						return SUCCESS;
						
					}
					
					FechaInicial = Utils.FormatoFechaReporteEstadouenta(FechaInicial);
					FechaFinal   = Utils.FormatoFechaReporteEstadouenta(FechaFinal);
					
					
					String ValidaRangoFecha = servicefunciones.ValidaRangoFecha(FechaInicial, FechaFinal); 
					if(!ValidaRangoFecha.trim().equals("")){
						
						respuestaServer= true;
						mensajeServer= ValidaRangoFecha;
						return SUCCESS;
					}	
				}
				
				if(Usuario!=null &&!Usuario.trim().equals("-1")){
					
					usuario = 	usuarioService.obtieneUsuarioXid(Integer.parseInt(Usuario.trim()));
					
					 data = servicecuentacorriente.EstadoCuentaCorriente(usuario.getRuc(),Tipo,FechaInicial,FechaFinal,offset,limit,usuario.getUsername());
					 total = servicecuentacorriente.EstadoCountCuentaCorriente(usuario.getRuc(), Tipo, FechaInicial, FechaFinal,usuario.getUsername()); 
					
				}else{
					
					if(usuario.getNivel().trim().equals("3")){
						
						 data =servicecuentacorriente.EstadoCuentaCorriente(usuario.getRuc(),Tipo,FechaInicial,FechaFinal,offset,limit,usuario.getUsername());
						 total = servicecuentacorriente.EstadoCountCuentaCorriente(usuario.getRuc(), Tipo, FechaInicial, FechaFinal,usuario.getUsername());
						
					}else{
						
						data =servicecuentacorriente.EstadoCuentaCorriente(usuario.getRuc(),Tipo,FechaInicial,FechaFinal,offset,limit,"N");
						total = servicecuentacorriente.EstadoCountCuentaCorriente(usuario.getRuc(), Tipo, FechaInicial, FechaFinal,"N");
					}
					
				}
				
				//List<B_CuentaCorrienteBean>  data =servicecuentacorriente.EstadoCuentaCorriente(usuario.getRuc(),Tipo,FechaInicial,FechaFinal,offset,limit);
				List<Map<String, Object>> lstAuxiliar = new ArrayList<>();
				
				for (B_CuentaCorrienteBean bean : data) {
					Map<String, Object> mapaAuxiliar = new HashMap<>();
					mapaAuxiliar.put("voucher", bean.getVoucher());
					mapaAuxiliar.put("fechaemision", bean.getFechaEmision());
					mapaAuxiliar.put("destino", bean.getDestinoD());
					mapaAuxiliar.put("total", bean.getTotal());
					mapaAuxiliar.put("pago", bean.getPagos());
					mapaAuxiliar.put("saldo", bean.getSaldo());
					
				lstAuxiliar.add(mapaAuxiliar);
				}
				
				//total = servicecuentacorriente.EstadoCountCuentaCorriente(usuario.getRuc(), Tipo, FechaInicial, FechaFinal); 
				
				
				mapaJSONResultadoEstadoCuenta.put("rows", lstAuxiliar);
				mapaJSONResultadoEstadoCuenta.put("total", total);
			}
		  } catch (Exception e) {
			  log.info(Utils.printStackTraceToString(e));
		}
		
		return SUCCESS;
		
	}
	

	@Action(value = "/ListaBoletoReservadoGrid",results = {@Result(name = SUCCESS,type ="json")})
	public String ListaBoletoReservadoGrid(){
		
		try  {
			
			SpringSecurityUser usuario = SecurityContextHolder.getContext().getAuthentication().getName()== null ? null : (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			List<B_VentaBean>  data = new  ArrayList<B_VentaBean>();
			
			if(usuario!=null){
				
				if(usuario.getNivel().trim().equals("2")){
					
					 data =serviceventa.SelectVentaAgenteAdmin(usuario.getRuc(), offset, limit);	
					
				}else{
					
					data =serviceventa.getVentaRealizada(usuario.getUsername(),offset,limit);
					
				}
				
				
				//data =serviceventa.getVentaRealizada(usuario.getUsername(),offset,limit);
				List<Map<String, Object>> lstAuxiliar = new ArrayList<>();
				
				for (B_VentaBean bean : data) {
					Map<String, Object> mapaAuxiliar = new HashMap<>();
					mapaAuxiliar.put("nro", bean.getNro());
					mapaAuxiliar.put("salida", bean.getSalida());
					mapaAuxiliar.put("destino", bean.getDestinoD());
					mapaAuxiliar.put("fechaemision", bean.getFechaEmision());
					mapaAuxiliar.put("hora", bean.getHoraViaje());
					mapaAuxiliar.put("nombre", bean.getNombre());
					mapaAuxiliar.put("dni", bean.getDNI());
					mapaAuxiliar.put("precio", bean.getPrecio());
					mapaAuxiliar.put("asiento", bean.getAsiento());
					mapaAuxiliar.put("fechavoucher", bean.getFechaViaje());
					mapaAuxiliar.put("nroembarque", bean.getOrigen());
					mapaAuxiliar.put("embarque", bean.getOrigenD());
					mapaAuxiliar.put("servicio", bean.getServicio());
					mapaAuxiliar.put("monto", bean.getPrecioAct());
					mapaAuxiliar.put("Usuario", bean.getUsuario());
					lstAuxiliar.add(mapaAuxiliar);
				}
				
				if(usuario.getNivel().trim().equals("2")){
					
					total = serviceventa.SelectCountVentaAgenteAdmin(usuario.getRuc());
					
				}else{
					
					total = serviceventa.ListaCountVentasRealizadas(usuario.getUsername().trim());
				}
				
				
				mapaJSONResultadoBoletos.put("rows", lstAuxiliar);
				mapaJSONResultadoBoletos.put("total", total);
			}
		}catch(Exception e){
			log.info(Utils.printStackTraceToString(e));
		}
		return SUCCESS;
	}
	
	public void setMapaJSONResultadoBoletos(Map<String, Object> mapaJSONResultadoBoletos) {
		this.mapaJSONResultadoBoletos = mapaJSONResultadoBoletos;
	}
	public void setMapaJSONResultadoEstadoCuenta(Map<String, Object> mapaJSONResultadoEstadoCuenta) {
		this.mapaJSONResultadoEstadoCuenta = mapaJSONResultadoEstadoCuenta;
	}
	
	public Map<String, Object> getMapaJSONResultadoBoletos() {
		return mapaJSONResultadoBoletos;
	}
	public Map<String, Object> getMapaJSONResultadoEstadoCuenta() {
		return mapaJSONResultadoEstadoCuenta;
	}
	public void setFechaFinal(String fechaFinal) {
		FechaFinal = fechaFinal;
	}
	public String getFechaInicial() {
		return FechaInicial;
	}
	public void setFechaInicial(String fechaInicial) {
		FechaInicial = fechaInicial;
	}
	public String getFechaFinal() {
		return FechaFinal;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	public String getTipo() {
		return Tipo;
	}
	
	public void setMensajeServer(String mensajeServer) {
		this.mensajeServer = mensajeServer;
	}
	public String getMensajeServer() {
		return mensajeServer;
	}
	public void setRespuestaServer(boolean respuestaServer) {
		this.respuestaServer = respuestaServer;
	}
	
	public boolean isRespuestaServer() {
		return respuestaServer;
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
	public void setTotal(int total) {
		this.total = total;
	}
	public int getTotal() {
		return total;
	}
	
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getOffset() {
		return offset;
	}
	
	public void setListaUsuarios(List<SpringSecurityUser> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	public List<SpringSecurityUser> getListaUsuarios() {
		return listaUsuarios;
	}
	public void setUsuario(String usuario) {
		Usuario = usuario;
	}
	public String getUsuario() {
		return Usuario;
	}
	
}
