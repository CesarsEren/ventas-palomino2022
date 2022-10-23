package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import pe.com.grupopalomino.sistema.boletaje.bean.V_Fallas;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class B_BitacoraIDAO extends MapperSQL implements B_BitacoraDAO {

	private static final Log log = LogFactory.getLog(B_BitacoraIDAO.class);

	@SuppressWarnings("unchecked")
	public List<V_Fallas> LisSQL_Fallas(String estado, int offset, int limit) throws Exception {
		// TODO Auto-generated method stub
		V_Fallas bean = new V_Fallas();
		bean.setEstado(estado);
		Map<String, Object> params = new HashMap<>();
		params.put("estado", estado);
		params.put("offset", offset);
		params.put("limit", limit);
		SqlSession session = sqlMapper.openSession();
		List<V_Fallas> lista = new ArrayList<V_Fallas>();
		try {
			// lista = session.selectList(ProjectStr +
			// "SQL_GET_V_ListBitacoraFallas_Bitacora", bean);
			lista = session.selectList(ProjectStr + "SQL_GET_V_ListBitacoraFallas_Bitacora", params);
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public int InsertFalla(V_Fallas bean) throws Exception {
		// TODO Auto-generated method stub
		int rpta = -1;
		SqlSession session = sqlMapper.openSession();
		try {
			session.insert(ProjectStr + "SQL_INSERT_V_Falla_Bitacora", bean);
			// session.insert(ProjectStr + "USP_InsertFalla", bean);
			session.commit();
			rpta = 1;
		} catch (Exception e) {
			session.rollback();
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return rpta;
	}
	/*
	 * public static void main(String[] args) { try {
	 * 
	 * new B_BitacoraIDAO().InsertFalla(new V_Fallas(2, "777", 2, "LimaCusco",
	 * "problemas de motor", "" +
	 * + "", "pendiente", "Nadie")); } catch (Exception e) {
	 * System.err.println(e.getMessage()); // TODO: handle exception } try {
	 * List<V_Fallas> templs = new B_BitacoraIDAO().LisSQL_Fallas(); for
	 * (V_Fallas x : templs) { System.out.println(x.getId());
	 * System.out.println(x.getFoto()); System.out.println(x.getDetalle()); } }
	 * catch (Exception e) { // TODO: handle exception
	 * System.err.println(e.getMessage()); }
	 * 
	 * }
	 */

	@Override
	public int UpdateEstado(Map<String, Object> parametros) throws Exception {
		// TODO Auto-generated method stub

		int rpta = -1;
		SqlSession session = sqlMapper.openSession();
		try {
			session.insert(ProjectStr + "SQL_UPDATE_V_BitacoraFalla_Estado_Bitacora", parametros);
			// session.insert(ProjectStr + "USP_InsertFalla", bean);
			session.commit();
			rpta = 1;
		} catch (Exception e) {
			session.rollback();
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return rpta;
	}

	@Override
	public V_Fallas GetBitacora(int id) throws Exception {
		// TODO Auto-generated method stub
		V_Fallas bean = new V_Fallas();

		SqlSession session = sqlMapper.openSession();
		try {
			bean = (V_Fallas) session.selectOne(ProjectStr + "SQL_GET_V_BitacoraFalla_BitacoraX_ID", id);
		} catch (Exception e) {
			session.rollback();
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return bean;

	}
	/*
	 * public static void main(String[] args) { try { List<V_Fallas> temp = new
	 * B_BitacoraIDAO().LisSQL_FallasPorBusYestado("768","Solucionado");
	 * for(V_Fallas x : temp) { System.out.println(x.getId());
	 * System.out.println(x.getAsunto()); } } catch (Exception e) { // TODO:
	 * handle exception }
	 * 
	 * } }
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<V_Fallas> LisSQL_FallasPorBusYestado(String estado, String nrobus, int offset, int limit)
			throws Exception {
		// TODO Auto-generated method stub

		Map<String,Object> bean = new HashMap<>();
		bean.put("estado",estado);
		bean.put("nrobus",nrobus);
		bean.put("limit",limit);
		bean.put("offset",offset);
		SqlSession session = sqlMapper.openSession();
		List<V_Fallas> lista = new ArrayList<V_Fallas>();
		try {
			lista = session.selectList(ProjectStr + "SQL_ConsultaFallasXnroBus_Estado_Bitacora", bean);
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public int LisSQL_FallasTodos(String estado) throws Exception {
		// TODO Auto-generated method stub
		int bean = 0;
		SqlSession session = sqlMapper.openSession();
		// List<V_Fallas> lista = new ArrayList<V_Fallas>();
		try {
			bean = (int) session.selectOne(ProjectStr + "SQL_GET_V_ListBitacoraFallas_BitacoraTodos", estado);

		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return bean;
	}
}