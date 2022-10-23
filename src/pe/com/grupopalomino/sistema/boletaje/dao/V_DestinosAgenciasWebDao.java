package pe.com.grupopalomino.sistema.boletaje.dao;


import java.util.List;
import pe.com.grupopalomino.sistema.boletaje.bean.V_DestinosAgenciasWebBean;

public interface V_DestinosAgenciasWebDao {
	
	public V_DestinosAgenciasWebBean obtieneDestinosAgencias(String origen,String destino) throws Exception;
	public List<V_DestinosAgenciasWebBean> ListaDestinosAgencias() throws Exception;
	

}
