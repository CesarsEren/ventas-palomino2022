package pe.com.grupopalomino.sistema.boletaje.action;

import java.util.ArrayList;    
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import com.opensymphony.xwork2.ActionSupport;
import pe.com.grupopalomino.sistema.boletaje.bean.*;
import pe.com.grupopalomino.sistema.boletaje.service.EmpresaService;
import pe.com.grupopalomino.sistema.boletaje.service.EmpresaServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.EncomiendasService;
import pe.com.grupopalomino.sistema.boletaje.service.EncomiendasServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.MotivoService;
import pe.com.grupopalomino.sistema.boletaje.service.MotivoServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.TipoDocumentoService;
import pe.com.grupopalomino.sistema.boletaje.service.TipoDocumentoServicel;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

@SuppressWarnings("serial")
@ParentPackage(value = "BoletajePalomino03")
public class ListaReclamosAction extends ActionSupport{
	
	private static final Log log = LogFactory.getLog(ListaReclamosAction.class);
	private List<V_EmpresasBean> listaEmpresa = new ArrayList<V_EmpresasBean>();
	private List<B_MotivoBean> listaMotivos = new ArrayList<B_MotivoBean>();
	private List<V_TipoDocumentosBean> listaTipoDocumentos = new ArrayList<V_TipoDocumentosBean>();
	private TipoDocumentoService servicetipodocumentos = new TipoDocumentoServicel();
	private EncomiendasService serviceencomiendas = new EncomiendasServiceI();
	private boolean respuestaServer = false;
	private String mensajeServer ;
	private String serie,numero,documento,empresa,rucEmpresa,dirEmpresa,razonEmpresa,Codigo;
	private EmpresaService serviceempresa = new EmpresaServiceI();
	private MotivoService service = new MotivoServiceI();
	private MotivoService servicemotivos = new MotivoServiceI();
	private String Tipo;
	
	
	@Action(value = "/listareclamospasaje",  results = { @Result(name = SUCCESS, type = "json")})
	public String listareclamos(){
	
	try {
		listaMotivos = servicemotivos.MuestraMotivos("B");
		listaTipoDocumentos = servicetipodocumentos.MuestraTipoDocumentos("B");
		
		
	} catch (Exception e) {
		
		log.info(Utils.printStackTraceToString(e));
	}
	
	return SUCCESS;
	}
	
	@Action(value = "listamotivodocumento", results = { @Result(name = SUCCESS, type = "json")})
	public String listaprueba(){
		
	
		try {
			
			listaMotivos = service.MuestraMotivos(Tipo);
			listaTipoDocumentos = servicetipodocumentos.MuestraTipoDocumentos(Tipo);
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		
		return SUCCESS;
		
	}
	@Action(value = "listaempresa", results = { @Result(name = SUCCESS, type = "json")})
	public String listaempresa(){
		
	
		try {
			List<V_EmpresasBean> listaComboEmpresa = new ArrayList<V_EmpresasBean>();
			listaComboEmpresa = serviceempresa.listaEmpresas();
			
			if(!Codigo.trim().equals("")){
				for (V_EmpresasBean bean : listaComboEmpresa) {
					
					if(bean.getCodigo().trim().equals(Codigo)){
						rucEmpresa = bean.getRuc().trim();
						dirEmpresa = bean.getDireccion().trim();
						razonEmpresa = bean.getRazon().trim();
					}
				}
			}
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		
		return SUCCESS;
		
	}
	
	
	@Action(value = "verificadocumento", results = {@Result(name = SUCCESS , type = "json")})
	public String  verificadocumento(){
		
		
		try {
			
			
			
			B_EncomiendasBean encomiendas = new B_EncomiendasBean();
			
			encomiendas = serviceencomiendas.getEncomiendaSerieNumero(serie, numero,documento,empresa);
			
			if(encomiendas == null){
				mensajeServer = "El documento ingresado, no existe";
				return SUCCESS;
				
			}
			respuestaServer = true;
			
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}
		return SUCCESS;
		
	}
	
	public void setCodigo(String codigo) {
		Codigo = codigo;
	}
	public String getCodigo() {
		return Codigo;
	}
	public void setRucEmpresa(String rucEmpresa) {
		this.rucEmpresa = rucEmpresa;
	}
	public String getRucEmpresa() {
		return rucEmpresa;
	}
	public void setDirEmpresa(String dirEmpresa) {
		this.dirEmpresa = dirEmpresa;
	}
	public String getDirEmpresa() {
		return dirEmpresa;
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
	
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getSerie() {
		return serie;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getNumero() {
		return numero;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getDocumento() {
		return documento;
	}
	
	public void setListaEmpresa(List<V_EmpresasBean> listaEmpresa) {
		this.listaEmpresa = listaEmpresa;
	}
	public List<V_EmpresasBean> getListaEmpresa() {
		return listaEmpresa;
	}
	
	public void setListaMotivos(List<B_MotivoBean> listaMotivos) {
		this.listaMotivos = listaMotivos;
	}
	public List<B_MotivoBean> getListaMotivos() {
		return listaMotivos;
	}
	
	public void setListaTipoDocumentos(List<V_TipoDocumentosBean> listaTipoDocumentos) {
		this.listaTipoDocumentos = listaTipoDocumentos;
	}
	public List<V_TipoDocumentosBean> getListaTipoDocumentos() {
		return listaTipoDocumentos;
	}
	
	public void setRazonEmpresa(String razonEmpresa) {
		this.razonEmpresa = razonEmpresa;
	}
	public String getRazonEmpresa() {
		return razonEmpresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getEmpresa() {
		return empresa;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	public String getTipo() {
		return Tipo;
	}
	
	
	
}
