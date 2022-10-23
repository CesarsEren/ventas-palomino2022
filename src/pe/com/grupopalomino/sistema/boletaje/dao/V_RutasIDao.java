package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import pe.com.grupopalomino.sistema.boletaje.bean.V_RutasBean;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class V_RutasIDao extends MapperSQL implements V_RutasDao {
	
	private static final Log log = LogFactory.getLog(V_RutasIDao.class);
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<V_RutasBean> getRutasNro(int Nro) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		List lista = new ArrayList<V_RutasBean>();
		try {
			lista = session.selectList(ProjectStr+"SQL_ObtenerRutasNro",Nro);
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
	public List<V_RutasBean> ListaRutas() throws Exception {
		// TODO Auto-generated method stub
		
		SqlSession session = sqlMapper.openSession();
		List<V_RutasBean> lista = new ArrayList<V_RutasBean>();
		try {
			lista = session.selectList(ProjectStr+"SQL_SelectRutas_V_Rutas");
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
	public List<V_RutasBean> ListSQL_RutasXCupon(String codigocupon) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		List<V_RutasBean> lista = new ArrayList<V_RutasBean>();
		try {
			Map<String,Object>parametros = new HashMap<>();
			parametros.put("codigocupon",codigocupon);
			lista = session.selectList(ProjectStr+"SQL_RutasXCupon",parametros);
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return lista;
	}
	
	/*
	public static void main(String[] args) {
		try {
			List<V_RutasBean> rutas = new V_RutasIDao().ListSQL_RutasXCupon("65316295");
			for(V_RutasBean x : rutas)
			{
				System.out.println(x.getNro()+" - "+x.getNroDetalle());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}*/

}
