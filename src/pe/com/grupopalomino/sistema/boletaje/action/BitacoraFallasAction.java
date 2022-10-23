package pe.com.grupopalomino.sistema.boletaje.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.security.access.prepost.PreAuthorize;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

import pe.com.grupopalomino.sistema.boletaje.bean.V_Fallas;
import pe.com.grupopalomino.sistema.boletaje.bean.V_RutasBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_SubCategoriaFalla;
import pe.com.grupopalomino.sistema.boletaje.bean.V_CategoriaFalla;
import pe.com.grupopalomino.sistema.boletaje.service.RutasService;
import pe.com.grupopalomino.sistema.boletaje.service.RutasServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.VariosService;
import pe.com.grupopalomino.sistema.boletaje.service.VariosServiceI;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

import pe.com.grupopalomino.sistema.boletaje.dao.B_BitacoraDAO;
import pe.com.grupopalomino.sistema.boletaje.dao.B_BitacoraIDAO;
import pe.com.grupopalomino.sistema.boletaje.dao.V_CategoriaFallasDao;
import pe.com.grupopalomino.sistema.boletaje.dao.V_CategoriaFallasIDao;
import pe.com.grupopalomino.sistema.boletaje.dao.V_SubcategoriaFallasDao;
import pe.com.grupopalomino.sistema.boletaje.dao.V_SubcategoriaFallasIDao;

@SuppressWarnings("serial")
@ParentPackage(value = "BoletajePalomino03")
@PreAuthorize("hasAnyRole('1','B','K','H')")
public class BitacoraFallasAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	private HttpServletRequest request;
	private HttpServletResponse response;

	private static final Logger log = Logger.getLogger(BitacoraFallasAction.class);
	private V_CategoriaFallasDao servicecategoria = new V_CategoriaFallasIDao();
	private V_SubcategoriaFallasDao servicesubcategoria = new V_SubcategoriaFallasIDao();

	private int id;

	private VariosService servicevarios = new VariosServiceI();

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	// ***////*****//BEANS
	private List<V_RutasBean> listarutas = new ArrayList<V_RutasBean>();
	private List<V_Fallas> listafallas = new ArrayList<V_Fallas>();
	private List<V_CategoriaFalla> listacategoriafallas = new ArrayList<V_CategoriaFalla>();
	private List<V_SubCategoriaFalla> listasubcategoriafallas = new ArrayList<V_SubCategoriaFalla>();
	// ***////*****//SERVICES
	private RutasService servicerutas = new RutasServiceI();
	// #{nrobus},#{idruta},#{asunto},#{detalle},#{idsubcategoria},#{foto},#{registro}
	// ***///***//**//**/SERVICE DAO
	private B_BitacoraDAO daoservbitacora = new B_BitacoraIDAO();
	private String nrobus;
	private String nroruta;
	private String asunto;
	private String descripcion;
	private String nrosubfalla;
	private String archivo;
	private String registro;

	@Action(value = "/accederegistrofalla", results = {
			@Result(name = SUCCESS, location = "sistema/bitacora/registrofalla.jsp") })
	public String accederegistrofalla() {
		return SUCCESS;
	}
	/*
	 * @Action(value = "/accederTiposFalla", results = {
	 * 
	 * @Result(name = SUCCESS, location = "sistema/bitacora/V_tipofalla.jsp") })
	 * public String accederTiposFalla() { return SUCCESS; }
	 */

	private String detalletipofalla;

	public String getDetalletipofalla() {
		return detalletipofalla;
	}

	public void setDetalletipofalla(String detalletipofalla) {
		this.detalletipofalla = detalletipofalla;
	}

	@Action(value = "/registrartipofalla", results = { @Result(name = SUCCESS, type = "json") })
	public String registrartipofalla() {

		try {
			// servicetipofallas.InsertTipoFalla(detalletipofalla);

		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		return SUCCESS;
	}

	private List<String> listanrobuses = new ArrayList<>();

	public List<String> getListanrobuses() {
		return listanrobuses;
	}

	public void setListanrobuses(List<String> listanrobuses) {
		this.listanrobuses = listanrobuses;
	}

	@Action(value = "/getnrobuses", results = { @Result(name = SUCCESS, type = "json") })
	public String Listabusesconfallas() {

		try {
			listanrobuses = servicevarios.Select_NroBusesconFallas();
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		return SUCCESS;
	}

	/*
	 * @Action(value = "/gettiposfallas", results = { @Result(name = SUCCESS,
	 * type = "json") }) public String gettiposfallas() { try { //
	 * listatipofalla = servicetipofallas.listarfallas(-1); } catch (Exception
	 * e) { log.info(Utils.printStackTraceToString(e));
	 * 
	 * } return SUCCESS; }
	 */

	@Action(value = "/cbocategoria", results = { @Result(name = SUCCESS, type = "json") })
	public String cbocategoria() {
		try {
			// listatipofalla = servicetipofallas.listarfallas(1);

			listacategoriafallas = servicecategoria.ListarCategoriaFallas(1);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));

		}
		return SUCCESS;
	}

	private int idcategoria;

	public int getIdcategoria() {
		return idcategoria;
	}

	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
	}

	@Action(value = "/cbosubcategorias", results = { @Result(name = SUCCESS, type = "json") })
	public String cbosubcategorias() {
		try {
			// listatipofalla = servicetipofallas.listarfallas(1);
			listasubcategoriafallas = servicesubcategoria.ListarSubCategoriaFallas(idcategoria, 1);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		return SUCCESS;
	}

	@Action(value = "/getlistaruta", results = { @Result(name = SUCCESS, type = "json") })
	public String getlistaruta() {
		try {
			listarutas = servicerutas.ListaRutas();
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		return SUCCESS;
	}

	private boolean estadoVtipo;

	public void setEstadoVtipo(boolean estadoVtipo) {
		this.estadoVtipo = estadoVtipo;
	}

	public boolean getEstadoVtipo() {
		return estadoVtipo;
	}

	@Action(value = "/cambiarestadoVfalla", results = { @Result(name = SUCCESS, type = "json") })
	public String cambiarestadoVfalla() {
		try {
			// servicetipofallas.UpdateTipoFalla(id, !estadoVtipo);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		return SUCCESS;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	@Action(value = "/registrarfalla", results = {
			@Result(name = SUCCESS, location = "sistema/bitacora/registrofalla.jsp") })
	public String registrarfalla() {
		try {
			/*
			 * log.info("Nro Ruta  : " + nroruta); log.info("Nro Bus : " +
			 * nrobus); log.info("Descripcion : " + descripcion); log.info(
			 * "Asunto : " + asunto); log.info(": nrofalla " + nrofalla);
			 * log.info("Archivo : " + archivo); log.info("Archivo : " +
			 * archivo.length());
			 */
			// nroruta;
			// nrosubfalla;

			V_Fallas falla = new V_Fallas();
			falla.setNrobus(nrobus);
			falla.setFoto(archivo);
			falla.setDetalle(descripcion);
			falla.setAsunto(asunto);
			falla.setIdsubcategoria(Integer.parseInt(nrosubfalla));
			falla.setIdruta(Integer.parseInt(nroruta));
			falla.setRegistro(registro);
			daoservbitacora.InsertFalla(falla);
			/*
			 * log.info("nro bus"+nrobus); log.info("Foto"+archivo);
			 * log.info("Descripcion"+descripcion); log.info("Asunto"+asunto);
			 * log.info("Id Ruta"+nroruta); log.info("Id SubFalla"+nrosubfalla);
			 */

		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		return SUCCESS;
	}

	@Action(value = "/verfallas", results = { @Result(name = SUCCESS, location = "sistema/bitacora/ListaFallas.jsp") })
	public String verfallas() {
		return SUCCESS;
	}

	private String contenido;

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	@Action(value = "/actualizarfalla", results = {
			@Result(name = SUCCESS, location = "sistema/bitacora/ListaFallas.jsp") })
	public String actualizarfalla() {
		// session/
		try {
			Map<String, Object> parametros = new HashMap<>();
			parametros.put("id", id);
			parametros.put("estado", estado);
			parametros.put("contenido", contenido);
			parametros.put("resolvio", user);
			daoservbitacora.UpdateEstado(parametros);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info(Utils.printStackTraceToString(e));
		}
		return SUCCESS;
	}

	int offset;
	int limit;
	boolean porestado;
	boolean porambos;

	@Action(value = "/listafallas", results = { @Result(name = SUCCESS, type = "json") })
	public String listafallas() {
		ValueStack stack = ActionContext.getContext().getValueStack();
		Map<String, Object> data = new HashMap<>();
		Map<String, Object> res = new HashMap<>();
		if(porestado){		
		try {
			// listafallas = daoservbitacora.LisSQL_Fallas(estado);
		
			res.put("rows", daoservbitacora.LisSQL_Fallas(estado, offset, limit));
			res.put("total", daoservbitacora.LisSQL_FallasTodos(estado));
			data.put("listfallas", res);
			stack.push(data);
			return SUCCESS;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		}else if(porambos)
		{
			try {
				// listafallas = daoservbitacora.LisSQL_Fallas(estado);
			
				res.put("rows", daoservbitacora.LisSQL_FallasPorBusYestado(nrobus,estado, offset, limit));
				res.put("total", daoservbitacora.LisSQL_FallasTodos(estado));
				data.put("listfallas", res);
				stack.push(data);
				return SUCCESS;
			} catch (Exception e) {
				log.info(Utils.printStackTraceToString(e));
			}
		}
		
		return SUCCESS;
	}

	@Action(value = "/consultafallas", results = { @Result(name = SUCCESS, type = "json") })
	public String listafallas2() {
		try {

			// listafallas = daoservbitacora.LisSQL_Fallas("Todos");
			listafallas = daoservbitacora.LisSQL_FallasPorBusYestado(estado, nrobus,offset,limit);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info(Utils.printStackTraceToString(e)); 
		}
		return SUCCESS;
	}

	private V_Fallas encontrado = new V_Fallas();

	public V_Fallas getEncontrado() {
		return encontrado;
	}

	public void setEncontrado(V_Fallas encontrado) {
		this.encontrado = encontrado;
	}

	@Action(value = "/fallaid", results = { @Result(name = SUCCESS, type = "json") })
	public String fallaid() {
		try {
			encontrado = daoservbitacora.GetBitacora(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info(Utils.printStackTraceToString(e));
		}
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

	public List<V_RutasBean> getListarutas() {
		return listarutas;
	}

	public void setListarutas(List<V_RutasBean> listarutas) {
		this.listarutas = listarutas;
	}

	public String getNroruta() {
		return nroruta;
	}

	public void setNroruta(String nroruta) {
		this.nroruta = nroruta;
	}

	public String getNrobus() {
		return nrobus;
	}

	public void setNrobus(String nrobus) {
		this.nrobus = nrobus;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public List<V_CategoriaFalla> getListacategoriafallas() {
		return listacategoriafallas;
	}

	public void setListacategoriafallas(List<V_CategoriaFalla> listacategoriafallas) {
		this.listacategoriafallas = listacategoriafallas;
	}

	public void setListasubcategoriafallas(List<V_SubCategoriaFalla> listasubcategoriafallas) {
		this.listasubcategoriafallas = listasubcategoriafallas;
	}

	public List<V_SubCategoriaFalla> getListasubcategoriafallas() {
		return listasubcategoriafallas;
	}

	public List<V_Fallas> getListafallas() {
		return listafallas;
	}

	public void setListafallas(List<V_Fallas> listafallas) {
		this.listafallas = listafallas;
	}

	private String user;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	private String estado;

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNrosubfalla() {
		return nrosubfalla;
	}

	public void setNrosubfalla(String nrosubfalla) {
		this.nrosubfalla = nrosubfalla;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getLimit() {
		return limit;
	}

	public boolean isPorambos() {
		return porambos;
	}

	public boolean isPorestado() {
		return porestado;
	}

	public void setPorestado(boolean porestado) {
		this.porestado = porestado;
	}

	public void setPorambos(boolean porambos) {
		this.porambos = porambos;
	}
}
