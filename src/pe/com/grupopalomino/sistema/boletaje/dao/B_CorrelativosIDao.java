package pe.com.grupopalomino.sistema.boletaje.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import pe.com.grupopalomino.sistema.boletaje.bean.B_Correlativos;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class B_CorrelativosIDao extends MapperSQL implements B_CorrelativosDao {

	private static final Log log = LogFactory.getLog(B_CorrelativosIDao.class);
	@Override
	public B_Correlativos generaCorrelativo() throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		B_Correlativos bean= new B_Correlativos();
		try {
			bean = (B_Correlativos) session.selectOne(ProjectStr+"SQL_GeneraCorrelativo");
			return bean;
		}catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		}finally{
			session.close();
		}
		return null;
	
	}

}
