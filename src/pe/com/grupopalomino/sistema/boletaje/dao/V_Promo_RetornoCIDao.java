package pe.com.grupopalomino.sistema.boletaje.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Promo_RetornoCBean;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class V_Promo_RetornoCIDao extends MapperSQL implements V_Promo_RetornoCDao {
	
	private static final Log log = LogFactory.getLog(V_Promo_RetornoCIDao.class);
	

	@Override
	public V_Promo_RetornoCBean SQL_SELECT_TIPO_PROMOCION_RETORNO() throws Exception {
		
		
		SqlSession session = sqlMapper.openSession();
		V_Promo_RetornoCBean bean = null;
		
		try {
			bean = (V_Promo_RetornoCBean) session.selectOne(ProjectStr+"SQL_SELECT_TIPO_PROMOCION_RETORNO");
		} catch (Exception e) {
			session.rollback();
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		
		return bean;
	}

}

