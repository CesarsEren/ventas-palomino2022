package pe.com.grupopalomino.sistema.boletaje.service;

import pe.com.grupopalomino.sistema.boletaje.bean.V_PasajerosBean;


public interface PasajerosService {
	
	public V_PasajerosBean  listPasajerosDNI(String DNI,String Codigo) throws Exception;
	public int  InsertPasajeros(V_PasajerosBean bean) throws Exception;

}
