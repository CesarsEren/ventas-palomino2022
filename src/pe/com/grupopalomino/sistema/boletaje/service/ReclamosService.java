package pe.com.grupopalomino.sistema.boletaje.service;


import pe.com.grupopalomino.sistema.boletaje.bean.B_ReclamosBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_ReclamosBeanDetalle;

public interface ReclamosService {


	public int registraReclamos(B_ReclamosBean bean) throws Exception;
	public B_ReclamosBean selecReclamos (int Nro, int Periodo,String Empresa)throws Exception;
	public B_ReclamosBeanDetalle selecReclamosDetalle (String Serie, String Numero, String Documento,String Empresa)throws Exception;
	
}
