package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.TransactionIsolationLevel;
import pe.com.grupopalomino.sistema.boletaje.bean.B_PreguntasFrecuentesBean;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class B_PreguntasFrecuentesIDao extends MapperSQL implements B_PreguntasFrecuentesDao {
	
	private static final Log log = LogFactory.getLog(B_PreguntasFrecuentesIDao.class);
	
	@Override
	public int insertPreguntasFrecuentes(B_PreguntasFrecuentesBean bean) throws Exception {
		// TODO Auto-generated method stub
		
		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);
		
		try {
			resultado = session.insert(ProjectStr+"SQL_RegistroPreguntasFrecuentes", bean);
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
	public List<B_PreguntasFrecuentesBean> ListaPreguntasFrecuentes(String email) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		List<B_PreguntasFrecuentesBean> lista = new ArrayList<>();
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("email", email);
		
		try {
			lista = session.selectList(ProjectStr+"SQL_ListaPreguntasFrecuentes",parametros);
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return lista;
	}

	@Override
	public B_PreguntasFrecuentesBean VerificaPreguntas(String Email) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		B_PreguntasFrecuentesBean bean = null ;
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("Email", Email);
		
		try {
			bean = (B_PreguntasFrecuentesBean) session.selectOne(ProjectStr+"SQL_VerificaPreguntas",parametros);
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return bean;
	}

}
