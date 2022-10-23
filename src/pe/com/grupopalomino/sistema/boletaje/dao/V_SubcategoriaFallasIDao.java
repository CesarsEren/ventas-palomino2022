package pe.com.grupopalomino.sistema.boletaje.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.helpers.UtilLoggingLevel;

import pe.com.grupopalomino.sistema.boletaje.bean.V_CategoriaFalla;
import pe.com.grupopalomino.sistema.boletaje.bean.V_SubCategoriaFalla;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class V_SubcategoriaFallasIDao extends MapperSQL implements V_SubcategoriaFallasDao {
	private static final Log log = LogFactory.getLog(V_SubcategoriaFallasIDao.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<V_SubCategoriaFalla> ListarSubCategoriaFallas(int idcategoria, int estado) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("idcategoria", idcategoria);
		parametros.put("estado", estado);
		List<V_SubCategoriaFalla> lista = new ArrayList<V_SubCategoriaFalla>();
		try {
			lista = (List<V_SubCategoriaFalla>) session.selectList(ProjectStr + "SQL_GET_Subcategoria_Bitacora",
					parametros);
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public int InsertSubCategoriaFalla(int idcategoria, String detalle) throws Exception {
		int rpta = -1;
		SqlSession session = sqlMapper.openSession();
		V_SubCategoriaFalla bean = new V_SubCategoriaFalla();
		bean.setIdcategoria(idcategoria);
		bean.setDetalle(detalle);
		try {
			rpta = session.insert(ProjectStr + "SQL_INSERT_V_Subcategoria_Bitacora", bean);
			session.commit();

		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
			// TODO: handle exception
		} finally {
			session.close();
		}

		return rpta;
	}

	@Override
	public int UpdateSubCategoriaFalla(int idsubcategoria, boolean estado) throws Exception {
		// TODO Auto-generated method stub
		int rpta = -1;
		SqlSession session = sqlMapper.openSession();
		V_SubCategoriaFalla bean = new V_SubCategoriaFalla();
		bean.setIdsubcategoria(idsubcategoria);
		bean.setEstado(estado);
		try {
			rpta = session.update(ProjectStr + "SQL_UPDATE_V_Subcategoria_Bitacora", bean);
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
	 * public static void main(String[] args) throws IOException {
	 * 
	 * try { //int x1 = new
	 * V_SubcategoriaFallasIDao().InsertSubCategoriaFalla(1, "Vasos"); int x2 =
	 * new V_SubcategoriaFallasIDao().UpdateSubCategoriaFalla(3, true); } catch
	 * (Exception e) { // TODO: handle exception
	 * log.info(Utils.printStackTraceToString(e)); }
	 * 
	 * try { List<V_SubCategoriaFalla> subcategorias = new
	 * V_SubcategoriaFallasIDao().ListarSubCategoriaFallas(1, -1); for
	 * (V_SubCategoriaFalla temp : subcategorias) { log.info("" +
	 * temp.getDetalle() + " " + temp.getIdcategoria() + " " + temp.isEstado() +
	 * " " + temp.getIdsubcategoria()); } } catch (Exception e) { // TODO:
	 * handle exception log.info(Utils.printStackTraceToString(e)); }
	 * 
	 * }
	 */

}
