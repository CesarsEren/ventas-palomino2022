package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.TransactionIsolationLevel;
import pe.com.grupopalomino.sistema.boletaje.bean.V_PasajerosBean;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;


public class V_PasajerosIDao extends MapperSQL implements V_PasajerosDao {

	private static final Log log = LogFactory.getLog(V_PasajerosIDao.class);
	
	@Override
	public V_PasajerosBean listPasajerosDNI(String DNI,String Codigo) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession(); /*Inicia comunicación MyBatis*/
		V_PasajerosBean bean = null;
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("DNI", DNI);
		parametros.put("Codigo", Codigo);
		
		try {
			bean =  (V_PasajerosBean) session.selectOne(ProjectStr+"SQL_ObtenerPasajeroDNI",parametros);
			return bean;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public int InsertPasajeros(V_PasajerosBean bean) throws Exception {
		// TODO Auto-generated method stub
		
		int resultado = -1;
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);
		
		try {
			resultado = session.insert(ProjectStr+"SQL_RegistroPasajero", bean);
			session.commit();
		}catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		}finally{
			session.close();
		}
		return resultado;
	}

	
	

}
