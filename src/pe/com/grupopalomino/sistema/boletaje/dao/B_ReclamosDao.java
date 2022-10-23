package pe.com.grupopalomino.sistema.boletaje.dao;


import pe.com.grupopalomino.sistema.boletaje.bean.*;

public interface B_ReclamosDao {
	
	public int registraReclamos(B_ReclamosBean bean) throws Exception;
	public B_ReclamosBean selecReclamos (int Nro,int Periodo,String Empresa)throws Exception;
	public B_ReclamosBeanDetalle selecReclamosDetalle (String Serie, String Numero, String Documento,String Empresa)throws Exception;
	
	
	

}
