package pe.com.grupopalomino.sistema.boletaje.dao;

import pe.com.grupopalomino.sistema.boletaje.bean.V_PasajerosBean;

public interface V_PasajerosDao {
	
	public V_PasajerosBean  listPasajerosDNI(String DNI,String Codigo) throws Exception;
	public int  InsertPasajeros(V_PasajerosBean bean) throws Exception;
	
}
