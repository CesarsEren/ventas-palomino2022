package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import pe.com.grupopalomino.sistema.boletaje.bean.V_DestinosAgenciasWebBean;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class V_DestinosAgenciasWebIDao extends MapperSQL implements V_DestinosAgenciasWebDao{
	
	private static final Log log = LogFactory.getLog(V_DestinosAgenciasWebIDao.class);

	@Override
	public V_DestinosAgenciasWebBean obtieneDestinosAgencias(String origen, String destino) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		V_DestinosAgenciasWebBean bean = new V_DestinosAgenciasWebBean();
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("origen", origen);
		parametros.put("destino", destino);
		
		try {
			bean =  (V_DestinosAgenciasWebBean) session.selectOne(ProjectStr+"SQL_DestinosAgenciasWeb",parametros);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return bean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<V_DestinosAgenciasWebBean> ListaDestinosAgencias() throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		List<V_DestinosAgenciasWebBean> bean = new ArrayList<>();
		
		try {
			bean =  (List<V_DestinosAgenciasWebBean>) session.selectList(ProjectStr+"SQL_ListaDestinosAgenciasWeb");
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return bean;
	}

}
