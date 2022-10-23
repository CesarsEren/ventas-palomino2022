package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.TransactionIsolationLevel;
import pe.com.grupopalomino.sistema.boletaje.bean.V_UsuarioHorariosBean;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class V_UsuarioHorariosIDao extends MapperSQL implements V_UsuarioHorariosDao {
	
	private static final Log log = LogFactory.getLog(V_UsuarioHorariosIDao.class);

	@Override
	public int insertUsuariosHorarios(V_UsuarioHorariosBean bean) throws Exception {
		// TODO Auto-generated method stub
		

		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);
		
		try {
			resultado = session.insert(ProjectStr+"SQL_Insert_UsuariosHorarios_SQL", bean);
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
	public int deleteUsuariosHorarios(int Id, String Usuario) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateUsuariosHorarios(V_UsuarioHorariosBean bean) throws Exception {
		// TODO Auto-generated method stub
		
		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);
		
	
		try {
			resultado = session.update(ProjectStr+"SQL_Update_UsuarioHorarios_SQL",bean);
			
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
	public List<V_UsuarioHorariosBean> listaUsuarios(String query, Integer limit, Integer offset) throws Exception {
		// TODO Auto-generated method stub
		
		SqlSession session = sqlMapper.openSession();
		List<V_UsuarioHorariosBean> listaAgencias = new ArrayList<V_UsuarioHorariosBean>();
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("limit", limit);
		parametros.put("offset", offset);
		
		try {
			listaAgencias = session.selectList(ProjectStr+"SQL_Select_List_UsuarioHorarios_SQL", parametros);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return listaAgencias;
	}

	@Override
	public V_UsuarioHorariosBean listaUsuarioHorarioXid(int Id) throws Exception {
		
		// TODO Auto-generated method stub
				SqlSession session = sqlMapper.openSession();
				V_UsuarioHorariosBean listaAgencias = new V_UsuarioHorariosBean();
				
				Map<String, Object> parametros = new HashMap<String, Object>();
				parametros.put("Id", Id);
				
				try {
					listaAgencias = (V_UsuarioHorariosBean) session.selectOne(ProjectStr+"SQL_SelectIdUsuarioHorarios_SQL", parametros);
				} catch (Exception e) {
					log.info(Utils.printStackTraceToString(e));
				} finally{
					session.close();
				}
				
				return listaAgencias;
	}

	@Override
	public V_UsuarioHorariosBean listaUsuarioHorario(String Usuario) throws Exception {
		
		
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		V_UsuarioHorariosBean listaAgencias = new V_UsuarioHorariosBean();
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Usuario", Usuario);
		
		try {
			listaAgencias = (V_UsuarioHorariosBean) session.selectOne(ProjectStr+"SQL_SelectUsuarioHorarios_SQL", parametros);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return listaAgencias;
	}

	@Override
	public int UsuariosHorariosCount() throws Exception {
		// TODO Auto-generated method stub
		
		SqlSession session = sqlMapper.openSession();
		int clienterutasTotales = 0;
		
		try {
			clienterutasTotales = (int) session.selectOne(ProjectStr+"SQL_SelectUsuarioHorariosCount");
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return clienterutasTotales;
	}

	@Override
	public V_UsuarioHorariosBean VerificaUsuarioHorario(String Usuario) throws Exception {
		// TODO Auto-generated method stub
		
			SqlSession session = sqlMapper.openSession();
			V_UsuarioHorariosBean listaAgencias = new V_UsuarioHorariosBean();
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("Usuario", Usuario);
			
			try {
				listaAgencias = (V_UsuarioHorariosBean) session.selectOne(ProjectStr+"SQL_SelectVerificaUsuarioHorarios_SQL", parametros);
			} catch (Exception e) {
				log.info(Utils.printStackTraceToString(e));
			} finally{
				session.close();
			}
			
			return listaAgencias;
		
	}

}
