package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.V_Varios;

public interface V_VariosDao {
	
	public V_Varios Get_Precio_Maximo_Asiento() throws Exception;
	public V_Varios Get_Cantidad_Maximo_Pasajeros() throws Exception;
	public V_Varios Select_Varios() throws Exception;
	public List<String> Select_NroBusesconFallas() throws Exception;
	

}
