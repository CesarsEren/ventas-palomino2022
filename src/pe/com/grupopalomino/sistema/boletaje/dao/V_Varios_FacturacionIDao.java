package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Varios_FacturacionBean;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class V_Varios_FacturacionIDao extends MapperSQL implements V_Varios_FacturacionDao {

	private static final Log log = LogFactory.getLog(V_Varios_FacturacionIDao.class);
	
	@Override
	public V_Varios_FacturacionBean SQL_SELECT_LISTA_PARAMETROS_FACTURADOR(String empresa) {
		
		
		SqlSession session = sqlMapper.openSession();
		
		V_Varios_FacturacionBean bean = new V_Varios_FacturacionBean();
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("empresa", empresa);
		
		try {
			
			bean = (V_Varios_FacturacionBean) session.selectOne(ProjectStr+"SQL_SELECT_LISTA_PARAMETROS_FACTURADOR",parametros);
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return bean;
	}
	@Override
	public V_Varios_FacturacionBean SQL_SELECT_LISTA_PARAMETROS_DESCARGA_FACTURADOR(String empresa) {
		SqlSession session = sqlMapper.openSession();
		V_Varios_FacturacionBean bean = null;
		
		try {
			bean = (V_Varios_FacturacionBean) session.selectOne(ProjectStr+"SQL_VARIOS_DESCARGA_FACTURACION_WEB", empresa);
		} catch (Exception e) {
			session.rollback();
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		
		return bean;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<V_Varios_FacturacionBean> SQL_SELECT_TODOS_PARAMETROS_FACTURADOR() {
		
		SqlSession session = sqlMapper.openSession();
		
		List<V_Varios_FacturacionBean> bean = new ArrayList<>();
		
		try {
			
			bean = (List<V_Varios_FacturacionBean>) session.selectList(ProjectStr+"SQL_SELECT_TODOS_PARAMETROS_FACTURADOR");
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return bean;
	}
	public static void main(String []args)
	{
		V_Varios_FacturacionBean bn = new V_Varios_FacturacionIDao().SQL_SELECT_LISTA_PARAMETROS_FACTURADOR("003");
	}




}
