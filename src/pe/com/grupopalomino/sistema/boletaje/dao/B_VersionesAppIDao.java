package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.TransactionIsolationLevel;
import pe.com.grupopalomino.sistema.boletaje.bean.B_VersionesAppBean;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class B_VersionesAppIDao extends MapperSQL implements B_VersionesAppDao{
	
	private static final Log log = LogFactory.getLog(B_VersionesAppIDao.class);

	@Override
	public int insertVerionesApp(B_VersionesAppBean bean) throws Exception {
		// TODO Auto-generated method stub
		
		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);
		
		try {
			resultado = session.insert(ProjectStr+"SQL_RegistroVersionesApp", bean);
			
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
	public List<B_VersionesAppBean> listaVersionesApp(int offset,int limit) throws Exception {
		// TODO Auto-generated method stub
		
		SqlSession session = sqlMapper.openSession();
		List<B_VersionesAppBean> bean = new ArrayList<B_VersionesAppBean>();
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("offset", offset);
		parametros.put("limit", limit);
		
		try {
			bean =  ((List<B_VersionesAppBean>) session.selectList(ProjectStr+"SQL_MuestraVersionesApp",parametros));
			session.commit();
		}catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		}finally{
			session.close();
		}
		return bean;
	}

	@Override
	public B_VersionesAppBean selectVersionesApp(int id) throws Exception {
		// TODO Auto-generated method stub
		
		SqlSession session = sqlMapper.openSession();
		B_VersionesAppBean bean = null;
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		
		try {
			bean = (B_VersionesAppBean) session.selectOne(ProjectStr+"SQL_SelectVersionesApp", parametros);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return bean;
	}

	@Override
	public int listaVersionesAppTotal() throws Exception {
		// TODO Auto-generated method stub
		
		SqlSession session = sqlMapper.openSession();
		Integer total = 0;
		
		try {
			total = (Integer) session.selectOne(ProjectStr+"SQL_count_B_VersionesApp");
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		
		return total;
	}

	@Override
	public int ActualizaVersionesApp(B_VersionesAppBean bean) throws Exception {
		// TODO Auto-generated method stub
		
		int resultado = -1;
		
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", bean.getId());
		parametros.put("Plataforma", bean.getPlataforma());
		parametros.put("Fecha", bean.getFecha());
		parametros.put("Version_App", bean.getVersion_App());
		parametros.put("Critico", bean.getCritico());
		
		try {
			resultado = session.update(ProjectStr+"SQL_update_versionesapp_web", parametros);
			session.commit();
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		} finally{
			session.close();
		}
		
		return resultado;
	}

	@Override
	public int EliminarVersionesApp(int id) throws Exception {
		// TODO Auto-generated method stub
		
		int resultado = -1;
		
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		
		try {
			resultado = session.delete(ProjectStr+"SQL_delete_versionesapp_web", parametros);
			session.commit();
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		} finally{
			session.close();
		}
		
		return resultado;
	}

}
