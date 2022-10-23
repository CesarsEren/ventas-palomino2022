package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.TransactionIsolationLevel;
import pe.com.grupopalomino.sistema.boletaje.bean.V_ProgramacionPagosBean;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class V_ProgramacionPagoIDao extends MapperSQL implements V_ProgramacionPagoDao{

	private static final Log log = LogFactory.getLog(V_ProgramacionPagoIDao.class);
	
	@Override
	public int insertProgramacionPago(V_ProgramacionPagosBean bean) throws Exception {
		// TODO Auto-generated method stub
		
		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);
		
		try {
			resultado = session.insert(ProjectStr+"SQL_Insert_ProgramacionPagos_SQL", bean);
			session.commit();
		}catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		}finally{
			session.close();
		}
		return resultado;
	}

	@Override
	public int deleteProgramacionPago(int Id, String Usuario) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateProgramacionPago(V_ProgramacionPagosBean bean) throws Exception {
		// TODO Auto-generated method stub
		
		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);
		
	
		try {
			resultado = session.update(ProjectStr+"SQL_Update_ProgramacionPagos_SQL",bean);
			
			session.commit();
		}catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		}finally{
			session.close();
		}
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<V_ProgramacionPagosBean> listaProgramacionPagos(String query, Integer limit, Integer offset)
			throws Exception {
		// TODO Auto-generated method stub
		
		SqlSession session = sqlMapper.openSession();
		List<V_ProgramacionPagosBean> lista = new ArrayList<V_ProgramacionPagosBean>();
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("limit", limit);
		parametros.put("offset", offset);
		
		try {
			lista = session.selectList(ProjectStr+"SQL_Select_List_ProgramacionPagos_SQL", parametros);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return lista;
	}

	@Override
	public V_ProgramacionPagosBean listaProgramacionPagos(String Usuario,int Periodo) throws Exception {
		// TODO Auto-generated method stub
		
		SqlSession session = sqlMapper.openSession();
		V_ProgramacionPagosBean bean = new V_ProgramacionPagosBean();
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Usuario", Usuario);
		parametros.put("Periodo", Periodo);
		
		try {
			bean = (V_ProgramacionPagosBean) session.selectOne(ProjectStr+"SQL_SelectProgramacionPagos_SQL", parametros);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return bean;
		
		
	}

	@Override
	public V_ProgramacionPagosBean listaProgramacionPagosXid(int Id) throws Exception {
		// TODO Auto-generated method stub
		
		SqlSession session = sqlMapper.openSession();
		V_ProgramacionPagosBean bean = new V_ProgramacionPagosBean();
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Id", Id);
		
		try {
			bean = (V_ProgramacionPagosBean) session.selectOne(ProjectStr+"SQL_SelectIdProgramacionPagos_SQL", parametros);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return bean;
	}

	@Override
	public int V_ProgramacionPagosCount() throws Exception {
		// TODO Auto-generated method stub
		
		SqlSession session = sqlMapper.openSession();
		int cantidad = 0;
		
		try {
			cantidad = (int) session.selectOne(ProjectStr+"SQL_SelectProgramacionPagosCount");
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return cantidad;
	}

	@Override
	public V_ProgramacionPagosBean VerificaProgramacionPago(String Usuario) throws Exception {
		// TODO Auto-generated method stub
		
		SqlSession session = sqlMapper.openSession();
		V_ProgramacionPagosBean bean = new V_ProgramacionPagosBean();
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Usuario", Usuario);
		
		try {
			bean = (V_ProgramacionPagosBean) session.selectOne(ProjectStr+"SQL_VerificaProgramacionPagos_SQL", parametros);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return bean;
	}

}
