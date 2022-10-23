package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.List;
import pe.com.grupopalomino.sistema.boletaje.bean.V_DestinosClienteWebBean;

public interface V_DestinosClienteWebDao {
	
	
	public List<V_DestinosClienteWebBean> obtieneOrigenClientes(String Ruc) throws Exception;
	public List<V_DestinosClienteWebBean> obtieneDestinoClientes(String Origen,String Ruc) throws Exception;
	public V_DestinosClienteWebBean verificaDestinoCliente(String Ruc) throws Exception;

}
