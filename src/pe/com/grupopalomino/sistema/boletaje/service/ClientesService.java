package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;
import java.util.Map;

import pe.com.grupopalomino.sistema.boletaje.bean.V_ClientesBean;

public interface ClientesService {
	
	public V_ClientesBean listClientesRUC (String RUC)throws Exception;
	public int InertClientes (V_ClientesBean bean)throws Exception;
	public List<Map<String, String>> listaHistorialVentas(String username);
}
