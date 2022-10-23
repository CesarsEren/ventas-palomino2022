package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.V_TipoDocumentosBean;

public interface TipoDocumentoService {

	public List<V_TipoDocumentosBean> MuestraTipoDocumentos(String TipoDocumento) throws Exception;
	
}
