package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.B_MotivoBean;

public interface MotivoService {
	
	public List<B_MotivoBean> MuestraMotivos(String MotivoReclamo) throws Exception;

}
