package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import pe.com.grupopalomino.sistema.boletaje.bean.V_TipoDocumentosBean;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class V_TipoDocumentoslDao extends MapperSQL implements V_TipoDocumentosDao{

	private static final Log log = LogFactory.getLog(V_TipoDocumentoslDao.class);

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<V_TipoDocumentosBean> MuestraTipoDocumentos(String TipoDocumento) throws Exception {
		// TODO Auto-generated method stub
		
		SqlSession session = sqlMapper.openSession();
		List bean = new ArrayList<V_TipoDocumentosBean>();
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("TipoDocumento", TipoDocumento);
		
		
		try {
			bean =   session.selectList(ProjectStr+"SQL_MuestraTipoDocumentos", parametros);
			
		}catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		}finally{
			session.close();
		}
		return bean;
	}	
	
}
