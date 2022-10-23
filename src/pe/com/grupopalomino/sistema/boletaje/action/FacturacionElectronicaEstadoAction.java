package pe.com.grupopalomino.sistema.boletaje.action;

import java.io.FileOutputStream;
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
import com.opensymphony.xwork2.ActionSupport;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Varios_FacturacionBean;
import pe.com.grupopalomino.sistema.boletaje.service.FacturacionService;
import pe.com.grupopalomino.sistema.boletaje.service.FacturacionServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.V_Ventas_FacturacionService;
import pe.com.grupopalomino.sistema.boletaje.service.V_Ventas_FacturacionServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.Varios_FacturacionService;
import pe.com.grupopalomino.sistema.boletaje.service.Varios_FacturacionServiceI;
//import pe.com.grupopalomino.sistema.boletaje.util.HeaderHandlerResolver;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;
//import pe.com.grupopalomino.sunat.consultas.produccion.service.BillConsultService;
//import pe.com.grupopalomino.sunat.consultas.produccion.service.BillService;
//import pe.com.grupopalomino.sunat.consultas.produccion.service.StatusResponse;
import pe.gob.sunat.service.BillServicePort;
import pe.gob.sunat.service.BillServicePortService;
import pe.gob.sunat.service.BillServicePortServiceLocator;
import pe.gob.sunat.service.StatusCdrResponse;


@ParentPackage(value = "BoletajePalomino03")
@PreAuthorize("hasAnyRole('1','F')")
public class FacturacionElectronicaEstadoAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String empresa,fechaemision;
	private Integer documentos;
	private int limit, offset, total;
	private Varios_FacturacionService variosservice = new Varios_FacturacionServiceI();
	private FacturacionService facturacionservice = new FacturacionServiceI();
	private V_Ventas_FacturacionService facturacionerrorervice = new V_Ventas_FacturacionServiceI();
	private Map<String, Object> mapaJSONResultado  = new HashMap<>();
	
	private static final Log log = LogFactory.getLog(FacturacionElectronicaEstadoAction.class);
	
	@Action(value = "estadodocumentossunat", results = {@Result(name = SUCCESS, type = "json")})
	public String consultaestadodocumentos(){
		
		List<Map<String, Object>> lstResultado =  new ArrayList<>();
		Map<String, Object> mapaEnvio  = new HashMap<>();
		Map<String, Object> mapaFacturacion = new HashMap<>(); 
		try {	
			V_Varios_FacturacionBean facturacion = variosservice.SQL_SELECT_LISTA_PARAMETROS_FACTURADOR(empresa);
			List<Map<String, Object>> ventas =  facturacionservice.SQL_SELECT_VENTA_ENVIO_FACTURADOR(facturacion.getEmpresa(),Utils.FormatoFecha(fechaemision),documentos,0,0);
			for (Map<String, Object> map : ventas) {
			mapaFacturacion = facturacionservice.SQL_SELECT_VENTA_FACTURADOR(Integer.parseInt(map.get("Nro").toString()), map.get("Servicio").toString());
 
			Utils.USERNAME = facturacion.getUsernameSunat(); 
  			Utils.PASSWORD = facturacion.getPasswordSunat();  
  			BillServicePortService service = new BillServicePortServiceLocator(); 
  			BillServicePort billService = service.getBillServicePortSoap11(); 
  			StatusCdrResponse response = billService.getStatusCdr(facturacion.getRuc(), mapaFacturacion.get("TipoDocumento").toString() ,mapaFacturacion.get("SerieElectronica").toString(),mapaFacturacion.get("Numero").toString());
  			
  			if(response.getStatusCode().trim().equals("0") || response.getStatusCode().trim().equals("1033")){
  				//StatusResponse responseCDR =  billService.getStatusCdr(facturacion.getRuc(), mapaFacturacion.get("TipoDocumento").toString() ,mapaFacturacion.get("SerieElectronica").toString(),Integer.parseInt(mapaFacturacion.get("Numero").toString()));
  				FileOutputStream fos = new FileOutputStream(facturacion.getRutaRespuestaSunat()+ "R-" + mapaFacturacion.get("DocumentoZip").toString());
	            fos.write(response.getContent());
	            fos.close();
	            facturacionservice.SQL_UPDATE_VENTA_FACTURADOR(Integer.parseInt(mapaFacturacion.get("Nro").toString()), mapaFacturacion.get("Servicio").toString(),0,response.getStatusMessage());
  			}else{
  				
  				log.info("DOCUMENTO RECHAZADO --> DOCUMENTO : "+mapaFacturacion.get("DocumentoElectronico").toString()+" SERVICIO :"+mapaFacturacion.get("ServicioD").toString()+ " ESTADO : "+response.getStatusCode()+" MENSAJE : " +response.getStatusMessage());
  				
  				facturacionservice.SQL_UPDATE_VENTA_FACTURADOR(Integer.parseInt(mapaFacturacion.get("Nro").toString()), mapaFacturacion.get("Servicio").toString(),(/*response.getStatusCode().equals("0011")?0:*/ 0),response.getStatusMessage());
  				
  				facturacionerrorervice.SQL_UPDATE_VENTAS_FACTURACION_ERROR(Integer.parseInt(mapaFacturacion.get("Nro").toString()), mapaFacturacion.get("Servicio").toString(), response.getStatusCode());		  				
  			}
		}
			mapaEnvio.put("respuesta",true);
			mapaEnvio.put("mensaje","Los Documentos fueron consultados");
			lstResultado.add(mapaEnvio);
			mapaJSONResultado.put("rows", lstResultado);
		} catch (Exception e) {
			log.info("ERROR EN EL SERVICIO DE CONSULTAS SUNAT(ESCON) --> "+mapaFacturacion.get("DocumentoElectronico").toString()+"-"+mapaFacturacion.get("ServicioD").toString()+" "+e.getMessage());
			mapaEnvio.put("respuesta",false);
			mapaEnvio.put("mensaje","Ocurrio un problema al verificar los documentos, por favor vuelva a consultar los documentos.");
			lstResultado.add(mapaEnvio);
			mapaJSONResultado.put("rows", lstResultado);			
			facturacionservice.SQL_UPDATE_VENTA_FACTURADOR(Integer.parseInt(mapaFacturacion.get("Nro").toString()), mapaFacturacion.get("Servicio").toString(), 2,e.getMessage().substring(0, 200));
		}
		return SUCCESS;
	}
	
	
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

}
