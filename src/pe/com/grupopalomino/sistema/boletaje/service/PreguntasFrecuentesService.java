package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;
import pe.com.grupopalomino.sistema.boletaje.bean.B_PreguntasFrecuentesBean;


public interface PreguntasFrecuentesService {
	public int insertPreguntasFrecuentes(B_PreguntasFrecuentesBean bean )throws Exception;
	public List<B_PreguntasFrecuentesBean> ListaPreguntasFrecuentes(String Email)throws Exception;
	public B_PreguntasFrecuentesBean VerificaPreguntas(String Email)throws Exception;
}
