package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.ArrayList; 
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import pe.com.grupopalomino.sistema.boletaje.bean.V_EmpresasBean;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class V_EmpresaIDao extends MapperSQL implements V_EmpresaDao {

	private static final Log log = LogFactory.getLog(V_EmpresaIDao.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<V_EmpresasBean> listaEmpresas() throws Exception {
		
		
		SqlSession session = sqlMapper.openSession();
		List<V_EmpresasBean> lista = new ArrayList<V_EmpresasBean>();
		try {
			lista = session.selectList(ProjectStr+"SQL_Select_Empresas");
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}
	

}
