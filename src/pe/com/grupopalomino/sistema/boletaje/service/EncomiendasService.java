package pe.com.grupopalomino.sistema.boletaje.service;

import pe.com.grupopalomino.sistema.boletaje.bean.B_EncomiendasBean;

public interface EncomiendasService {

	public B_EncomiendasBean  getEncomiendaSerieNumero (String serie,String numero,String documento,String empresa) throws Exception;
}
