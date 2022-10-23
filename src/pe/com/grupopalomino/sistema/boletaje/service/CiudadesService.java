package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;
import pe.com.grupopalomino.sistema.boletaje.bean.V_CiudadesBean;

public interface CiudadesService {

	public List<V_CiudadesBean> SelectCiudades(String Tipo)throws Exception;
	public List<V_CiudadesBean> SelectCiudades()throws Exception;
	public List<V_CiudadesBean> SelectCiudadesTransporteDePersonal(String query, Integer limit, Integer offset)throws Exception;
	public int CountCiudades()throws Exception;
	public int CambiarEstado(String codigo);
	public int InsertCiudad(String detalle);
	
}
