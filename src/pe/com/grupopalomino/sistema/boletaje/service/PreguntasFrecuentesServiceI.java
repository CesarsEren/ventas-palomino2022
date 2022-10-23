package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;
import pe.com.grupopalomino.sistema.boletaje.bean.B_PreguntasFrecuentesBean;
import pe.com.grupopalomino.sistema.boletaje.dao.B_PreguntasFrecuentesDao;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;

public class PreguntasFrecuentesServiceI implements PreguntasFrecuentesService{

	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	B_PreguntasFrecuentesDao dao = factoria.getPreguntasFrecuentesWeb();
	@Override
	public int insertPreguntasFrecuentes(B_PreguntasFrecuentesBean bean) throws Exception {
		// TODO Auto-generated method stub
		return dao.insertPreguntasFrecuentes(bean);
	}
	@Override
	public List<B_PreguntasFrecuentesBean> ListaPreguntasFrecuentes(String email) throws Exception {
		// TODO Auto-generated method stub
		return dao.ListaPreguntasFrecuentes(email);
	}
	@Override
	public B_PreguntasFrecuentesBean VerificaPreguntas(String email) throws Exception {
		// TODO Auto-generated method stub
		return dao.VerificaPreguntas(email);
	}
}
