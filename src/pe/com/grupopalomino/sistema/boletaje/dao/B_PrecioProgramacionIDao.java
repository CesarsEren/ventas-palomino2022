package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import pe.com.grupopalomino.sistema.boletaje.bean.B_PrecioProgramacion;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Fallas;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class B_PrecioProgramacionIDao extends MapperSQL implements B_PrecioProgramacionDao {


	private static final Log log = LogFactory.getLog(B_PrecioProgramacionIDao.class);
	@Override
	public List<B_PrecioProgramacion> SQL_ObtenerAsientosPrecioPromocionConNroProgramacion(int Nro) {

		// TODO Auto-generated method stub
		//B_PrecioProgramacion bean = new B_PrecioProgramacion(); 
		Map<String, Object> params = new HashMap<>();
		params.put("Nro", Nro); 
		SqlSession session = sqlMapper.openSession();
		List<B_PrecioProgramacion> lista = new ArrayList<B_PrecioProgramacion>();
		try { 
			lista = session.selectList(ProjectStr + "SQL_ObtenerAsientosPrecioPromocionConNroProgramacion", params);
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}


}
