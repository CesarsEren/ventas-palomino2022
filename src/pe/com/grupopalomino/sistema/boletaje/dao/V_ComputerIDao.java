package pe.com.grupopalomino.sistema.boletaje.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import pe.com.grupopalomino.sistema.boletaje.bean.V_ComputerBean;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class  V_ComputerIDao extends MapperSQL implements V_ComputerDao {

	private static final Log log = LogFactory.getLog(V_ComputerIDao.class);
	
	@Override
	public V_ComputerBean listComputer(String Serie) throws Exception {
		// TODO Auto-generated method stub
		
		SqlSession session = sqlMapper.openSession(); /*Inicia comunicación MyBatis*/
		V_ComputerBean bean = null;
		try {
			bean =  (V_ComputerBean) session.selectOne(ProjectStr+"SQL_ObtenerComputer",Serie);
			return bean;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e)); 
		} finally {
			session.close();
		}
		return null;
	}

}
