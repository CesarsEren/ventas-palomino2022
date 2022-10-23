package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import pe.com.grupopalomino.sistema.boletaje.bean.V_AgenciasBean;
import pe.com.grupopalomino.sistema.boletaje.bean.V_DestinosMapaBean;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class V_DestinosMapaIDao extends MapperSQL  implements V_DestinosMapaDao{
	
	private static final Log log = LogFactory.getLog(V_DestinosMapaIDao.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<V_DestinosMapaBean> destinosmapa() throws Exception {
		
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		List <V_DestinosMapaBean> lista = new ArrayList<V_DestinosMapaBean>();
		try {
			lista = session.selectList(ProjectStr+"SQL_LIST_DESTINOS_MAPA");
			
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return lista;
	}

}
