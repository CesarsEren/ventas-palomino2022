package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.List;
import pe.com.grupopalomino.sistema.boletaje.bean.V_TipoDocumentosBean;

public interface V_TipoDocumentosDao {

	public List<V_TipoDocumentosBean> MuestraTipoDocumentos(String TipoDocumento) throws Exception;
	
}
