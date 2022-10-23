package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.TransactionIsolationLevel;
import pe.com.grupopalomino.sistema.boletaje.bean.B_MotivoBean;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class B_MotivoIDao extends MapperSQL implements B_MotivoDao {
	
	
	private static final Log log = LogFactory.getLog(B_MotivoIDao.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<B_MotivoBean> MuestraMotivos(String MotivoReclamo) throws Exception {
		// TODO Auto-generated method stub
		
		SqlSession session = sqlMapper.openSession(TransactionIsolationLevel.SERIALIZABLE);
		List<B_MotivoBean> bean = new ArrayList<B_MotivoBean>();
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("MotivoReclamo", MotivoReclamo);
		
		
		try {
			bean =  ((List<B_MotivoBean>) session.selectList(ProjectStr+"SQL_MuestraMotivos", parametros));
			session.commit();
		}catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		}finally{
			session.close();
		}
		return bean;
	}

}
