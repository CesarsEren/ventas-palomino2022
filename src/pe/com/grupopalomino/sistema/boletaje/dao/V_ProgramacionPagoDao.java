package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.List;
import pe.com.grupopalomino.sistema.boletaje.bean.V_ProgramacionPagosBean;

public interface V_ProgramacionPagoDao {
	
	public int insertProgramacionPago(V_ProgramacionPagosBean bean)throws Exception;
	public int deleteProgramacionPago(int Id, String Usuario)throws Exception;
	public int updateProgramacionPago(V_ProgramacionPagosBean bean)throws Exception;
	public List<V_ProgramacionPagosBean> listaProgramacionPagos(String query, Integer limit, Integer offset) throws Exception;
	public V_ProgramacionPagosBean listaProgramacionPagos(String Usuario,int Periodo)throws Exception;
	public V_ProgramacionPagosBean listaProgramacionPagosXid(int Id)throws Exception;
	public V_ProgramacionPagosBean VerificaProgramacionPago(String Usuario)throws Exception;
	public int V_ProgramacionPagosCount()throws Exception;
	

}
