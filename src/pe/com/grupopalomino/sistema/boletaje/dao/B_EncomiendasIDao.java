package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import pe.com.grupopalomino.sistema.boletaje.bean.B_EncomiendasBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_Encomiendas_Detalles;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class B_EncomiendasIDao extends MapperSQL  implements B_EncomiendasDao {
	
	private static final Log log = LogFactory.getLog(B_EncomiendasIDao.class);

	@Override
	public B_EncomiendasBean getEncomiendaSerieNumero(String serie, String numero,String documento,String empresa) throws Exception {
		// TODO Auto-generated method stub
		
		SqlSession session = sqlMapper.openSession();
		B_EncomiendasBean bean = null;
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("serie", serie);
		parametros.put("numero", numero);
		parametros.put("documento", documento);
		parametros.put("empresa", empresa);
		try {
			bean = (B_EncomiendasBean) session.selectOne(ProjectStr+"SQL_Obtiene_Encomienda", parametros);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		return bean;
	}

	@Override
	public List<B_Encomiendas_Detalles> SQL_Obtiene_DetalleEncomienda(int nro) {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		List<B_Encomiendas_Detalles> bean = null;
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("nro", nro); 
		try {
			bean =  (List<B_Encomiendas_Detalles>)session.selectList(ProjectStr+"SQL_Obtiene_DetalleEncomienda", parametros);
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally{
			session.close();
		}
		return bean;
	}
	/*
	public static void main(String ... args)
	{
		B_EncomiendasDao dao = new B_EncomiendasIDao();
		List<B_Encomiendas_Detalles> listaencomiendas= dao.SQL_Obtiene_DetalleEncomienda(2527461);
		for(B_Encomiendas_Detalles x: listaencomiendas){
			System.out.println(x);
		};
	}*/
}
