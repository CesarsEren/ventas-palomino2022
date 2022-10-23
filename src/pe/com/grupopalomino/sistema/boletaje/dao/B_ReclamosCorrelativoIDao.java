package pe.com.grupopalomino.sistema.boletaje.dao;


import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import pe.com.grupopalomino.sistema.boletaje.bean.B_ReclamosCorrelativoBean;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;


public class B_ReclamosCorrelativoIDao extends MapperSQL implements B_ReclamosCorrelativoDao{
	
	private static final Log log = LogFactory.getLog(B_ReclamosCorrelativoIDao.class);
	
	@Override
	public B_ReclamosCorrelativoBean selecReclamosCorrelativo(String Empresa,String atencionreclamos) throws Exception {
		
			SqlSession session = sqlMapper.openSession();
			B_ReclamosCorrelativoBean bean = null;
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("Empresa", Empresa);
			parametros.put("atencionreclamos", atencionreclamos);
			
			try {
				bean =  (B_ReclamosCorrelativoBean) session.selectOne(ProjectStr+"SQL_ObtenerReclamosCorrelativo",parametros);
				return bean;
			} catch (Exception e) {
				log.info(Utils.printStackTraceToString(e));
			} finally {
				session.close();
			}
			return null;
	}
	

	
	

}
