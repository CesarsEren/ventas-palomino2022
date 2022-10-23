package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.List;
import java.util.Map;

import pe.com.grupopalomino.sistema.boletaje.bean.V_Fallas;

public interface B_BitacoraDAO {
	public abstract List<V_Fallas> LisSQL_Fallas(String estado,int offset,int limit) throws Exception;
	public abstract int LisSQL_FallasTodos(String estado) throws Exception;
	//SQL_GET_V_ListBitacoraFallas_BitacoraTodos
	public abstract V_Fallas GetBitacora(int id) throws Exception;
	public abstract int InsertFalla(V_Fallas bean) throws Exception;
	public abstract int UpdateEstado(Map<String,Object> parametros)throws Exception;
	public abstract List<V_Fallas> LisSQL_FallasPorBusYestado(String estado , String nrobus,int offset ,int limit)throws Exception;
}
