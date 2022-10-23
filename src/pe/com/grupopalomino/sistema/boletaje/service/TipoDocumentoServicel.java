package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.V_TipoDocumentosBean;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;
import pe.com.grupopalomino.sistema.boletaje.dao.V_TipoDocumentosDao;

public class TipoDocumentoServicel implements TipoDocumentoService{

	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	V_TipoDocumentosDao dao = factoria.getTipoDocumentos();

	@Override
	public List<V_TipoDocumentosBean> MuestraTipoDocumentos(String TipoDocumento) throws Exception {
		
		return dao.MuestraTipoDocumentos(TipoDocumento); 
	}
	
}
