package pe.com.grupopalomino.sistema.boletaje.service;


import java.util.List;
import java.util.Map;

import pe.com.grupopalomino.sistema.boletaje.bean.B_AtencionReclamosBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_AtencionReclamosBeanDetalle;


public interface AtencionReclamosService {

	public int registraReclamos(B_AtencionReclamosBean bean) throws Exception;
	public B_AtencionReclamosBean selecReclamos (int Nro, int Periodo,String Empresa,String AtencionReclamos)throws Exception;
	public B_AtencionReclamosBeanDetalle selecReclamosDetalle (String Serie, String Numero, String Documento,String Empresa)throws Exception;
	public List<B_AtencionReclamosBean> selecReclamos (String query, Integer limit, Integer offset,String tipo,String agencia,String servicio)throws Exception;
	public List<B_AtencionReclamosBean> selecReclamosReporte (String query, Integer limit, Integer offset)throws Exception;
	public List<B_AtencionReclamosBean> selecReclamosReporte (String tiporeclamo)throws Exception;
	public int selectReclamosTotales(String tipo) throws Exception;
	public int updateReclamos (Integer Id ,String detalle)throws Exception;
	public List<B_AtencionReclamosBean> selecReclamosDni(String query, Integer limit, Integer offset,String tipo,String agencia,String servicio,String dni)throws Exception;
	//public B_AtencionReclamosBean selecReclamosDni (int Nro, int Periodo,String Empresa,String AtencionReclamos,String dni)throws Exception;
	//public List<B_AtencionReclamosBean> ObtenerAtencionListaReclamosRpte();
	public List<Map<String,Object>> ObtenerAtencionListaReclamosRpte();
	
	
}
