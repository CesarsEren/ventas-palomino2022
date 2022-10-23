package pe.com.grupopalomino.sistema.boletaje.dao;


import java.util.List;
import java.util.Map;

import pe.com.grupopalomino.sistema.boletaje.bean.*;

public interface B_AtencionReclamosDao {
	
	public int registraReclamos(B_AtencionReclamosBean bean) throws Exception;
	public B_AtencionReclamosBean selecReclamos (int Nro,int Periodo,String Empresa,String AtencionReclamos)throws Exception;
	public B_AtencionReclamosBeanDetalle selecReclamosDetalle (String Serie, String Numero, String Documento,String Empresa)throws Exception;
	public List<B_AtencionReclamosBean> selecReclamos (String query, Integer limit, Integer offset,String tipo,String agencia,String servicio)throws Exception;
	public List<B_AtencionReclamosBean> selecReclamosReporte (String query, Integer limit, Integer offset)throws Exception;
	public List<B_AtencionReclamosBean> selecReclamosReporte (String tiporeclamo)throws Exception;
	public int selectReclamosTotales(String tipo) throws Exception;
	public int updateReclamos (Integer Id ,String detalle)throws Exception;
	/*public B_AtencionReclamosBean selecReclamosDni(int nro, int periodo, String empresa, String atencionReclamos,String dni);*/
	public List<B_AtencionReclamosBean> selecReclamosDni(String query, Integer limit, Integer offset, String tipo,
			String agencia, String servicio, String dni);
	//public List<B_AtencionReclamosBean> ObtenerAtencionListaReclamosRpte();
	public List<Map<String,Object>> ObtenerAtencionListaReclamosRpte();

}
