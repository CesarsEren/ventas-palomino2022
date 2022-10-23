package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import pe.com.grupopalomino.sistema.boletaje.bean.V_CategoriaFalla;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class V_CategoriaFallasIDao extends MapperSQL implements V_CategoriaFallasDao {
	private static final Log log = LogFactory.getLog(V_CategoriaFallasIDao.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<V_CategoriaFalla> ListarCategoriaFallas(int activo) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		List<V_CategoriaFalla> lista = new ArrayList<V_CategoriaFalla>();
		try {
			lista = session.selectList(ProjectStr + "SQL_GET_Categorias_Bitacora", activo);
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public int InsertCategoriaFalla(String detalle) throws Exception {

		int rpta = -1;
		SqlSession session = sqlMapper.openSession();
		V_CategoriaFalla bean = new V_CategoriaFalla();
		bean.setDetalle(detalle);
		try {
			rpta = session.insert(ProjectStr + "SQL_INSERT_V_CategoriaFalla_Bitacora", bean);
			session.commit();

		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			// TODO: handle exception
		} finally {
			session.close();
		}

		return rpta;
	}
	/*
	 * public static void main(String[] args) { try { int x = new
	 * V_TipofallasIDao().UpdateTipoFalla(2,true); System.out.println(x); }
	 * catch (Exception e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * }
	 */

	@Override
	public int UpdateCategoriaFalla(int id, boolean estado) throws Exception {
		// TODO Auto-generated method stub
		int rpta = -1;
		SqlSession session = sqlMapper.openSession();
		V_CategoriaFalla bean = new V_CategoriaFalla();
		bean.setIdcategoria(id);
		bean.setEstado(estado);
		try {
			rpta = session.update(ProjectStr + "SQL_UPDATE_V_CategoriaFalla_Bitacora", bean);
			session.commit();
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			// TODO: handle exception
		} finally {
			session.close();
		}
		return rpta;
	}
}
