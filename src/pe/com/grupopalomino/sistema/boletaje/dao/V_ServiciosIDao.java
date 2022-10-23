package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import pe.com.grupopalomino.sistema.boletaje.bean.V_ServiciosBean;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class V_ServiciosIDao extends MapperSQL implements V_ServiciosDao {

	private static final Log log = LogFactory.getLog(V_ServiciosIDao.class);

	@Override
	public V_ServiciosBean getServicioCodigo(String Codigo) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		V_ServiciosBean bean = new V_ServiciosBean();
		try {
			bean = (V_ServiciosBean) session.selectOne(ProjectStr + "SQL_ObtenerServicioCodigo", Codigo);
			return bean;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			session.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<V_ServiciosBean> ListarServicios() throws Exception {
		// TODO Auto-generated method stub

		SqlSession session = sqlMapper.openSession();
		List<V_ServiciosBean> lista = new ArrayList<V_ServiciosBean>();
		try {
			lista = session.selectList(ProjectStr + "SQL_ListarServicio");
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<V_ServiciosBean> ListarDetalledeServiciosXCupon(String detalle) throws Exception {
		// TODO Auto-generated method stub

		SqlSession session = sqlMapper.openSession();
		List<V_ServiciosBean> lista = new ArrayList<V_ServiciosBean>();
		try {
			Map<String, Object> parametros = new HashMap<>();
			parametros.put("detalle", detalle);
			lista = session.selectList(ProjectStr + "SQL_ServiciosXCupon", parametros);
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}

	/*
	 * public static void main(String[] args) { try { List<V_ServiciosBean> temp
	 * = new V_ServiciosIDao().ListarDetalledeServiciosXCupon("IDA15"); for
	 * (V_ServiciosBean x : temp) { System.out.println("" + x.getCodigo());
	 * System.out.println("" + x.getDetalle().trim()); } } catch (Exception e) {
	 * // TODO: handle exception System.err.println(e.getMessage()); } }
	 */
}
