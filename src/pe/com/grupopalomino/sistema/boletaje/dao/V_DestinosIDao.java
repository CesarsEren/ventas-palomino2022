package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import pe.com.grupopalomino.sistema.boletaje.bean.V_DestinosBean;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;


public class V_DestinosIDao extends MapperSQL implements V_DestinosDao {

	private static final Log log = LogFactory.getLog(V_DestinosIDao.class);
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<V_DestinosBean> getDestinos() throws Exception {
		SqlSession session = sqlMapper.openSession();
		List lista = new ArrayList<V_DestinosBean>();
		try {
			lista = session.selectList(ProjectStr+"SQL_getDestinosView");
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<V_DestinosBean> getDestinos(String ciudad) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		List lista = new ArrayList<V_DestinosBean>();
		try {
			lista = session.selectList(ProjectStr+"SQL_getDestinosViewSelect",ciudad);
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public V_DestinosBean getDestinoNro(int Nro) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		V_DestinosBean bean= new V_DestinosBean();
		try {
			bean = (V_DestinosBean)session.selectOne(ProjectStr+"SQL_getDestinosViewNro",Nro);
			return bean;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public V_DestinosBean getDestinoVuelta(String destino, String origen) throws Exception {
		SqlSession session = sqlMapper.openSession();
		V_DestinosBean bean= new V_DestinosBean();
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("DESTINO", origen);
		parametros.put("ORIGEN", destino);
		
		try {
			bean = (V_DestinosBean)session.selectOne(ProjectStr+"SQL_getListaDestinosVuelta",parametros);
			return bean;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<V_DestinosBean> getDestinosID(String origen) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		List lista = new ArrayList<V_DestinosBean>();
		try {
			lista = session.selectList(ProjectStr+"SQL_getDestinosViewSelectId",origen);
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<V_DestinosBean> getDestinosXId(String ids) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		List lista = new ArrayList<V_DestinosBean>();
		try {
			lista = session.selectList(ProjectStr+"SQL_getDestinosViewSelectXId",ids);
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<V_DestinosBean> getDestinosTodos() throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		
		List lista = new ArrayList<V_DestinosBean>();
		try {
			lista = session.selectList(ProjectStr+"SQL_getDestinosViewTodosSelect");
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<V_DestinosBean> getDestinosXorigen(String origen) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		List lista = new ArrayList<V_DestinosBean>();
		try {
			lista = session.selectList(ProjectStr+"SQL_getDestinosViewSelectXorigen",origen);
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public V_DestinosBean getDestinoXIdaYVuelta(String origen, String destino) throws Exception {
		SqlSession session = sqlMapper.openSession();
		V_DestinosBean destinoBean = new V_DestinosBean();
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("origen", origen);
		parametros.put("destino", destino);
		
		try {
			destinoBean = (V_DestinosBean) session.selectOne(ProjectStr+"SQL_getDestinosViewXIdaYVuelta",parametros);
			return destinoBean;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}
	
	public static void main(String[] args) {
		V_DestinosDao a = new V_DestinosIDao();
		try {
			System.out.println(a.getDestinoXIdaYVuelta("001", "003").getNro());
		} catch (Exception e) {e.printStackTrace();
		}
	}
}
