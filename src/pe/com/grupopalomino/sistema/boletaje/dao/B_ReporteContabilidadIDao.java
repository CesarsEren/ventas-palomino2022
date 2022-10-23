package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import pe.com.grupopalomino.sistema.boletaje.bean.ReporteContabilidad;
import pe.com.grupopalomino.sistema.boletaje.bean.V_Fallas;
import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class B_ReporteContabilidadIDao extends MapperSQL implements B_ReporteContabilidadDao {

	private static final Log log = LogFactory.getLog(B_ReporteContabilidadIDao.class);

	@Override
	public List<ReporteContabilidad> GetDocumentosPorEnviar() {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		List<ReporteContabilidad> lista = new ArrayList<ReporteContabilidad>();
		try {
			lista = session.selectList(ProjectStr + "SQL_getReporteDocumentosPorEnviar");
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<ReporteContabilidad> GetDocumentosRechazados() {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		List<ReporteContabilidad> lista = new ArrayList<ReporteContabilidad>();
		try {
			lista = session.selectList(ProjectStr + "SQL_getReporteDocumentosRechazados");
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<String> GetDocumentosSinPDF417() {
		// TODO Auto-generated method stub
		SqlSession session = sqlMapper.openSession();
		List<String> lista = new ArrayList<String>();
		try {
			lista = session.selectList(ProjectStr + "SQL_View_documentosSinPDF417");
			return lista;
		} catch (Exception e) {
			log.info(Utils.printStackTraceToString(e));
		} finally {
			session.close();
		}
		return null;
	}
	/*public static void main(String[]args)
	{
		B_ReporteContabilidadDao dao = new B_ReporteContabilidadIDao();
		for(String x:dao.GetDocumentosSinPDF417())
		{
			System.out.println(x);
		}
	}*/

}
