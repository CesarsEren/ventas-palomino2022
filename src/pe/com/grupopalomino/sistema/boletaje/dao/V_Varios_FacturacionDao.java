package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.V_Varios_FacturacionBean;

public interface V_Varios_FacturacionDao {

	public V_Varios_FacturacionBean SQL_SELECT_LISTA_PARAMETROS_FACTURADOR(String empresa);
	public List<V_Varios_FacturacionBean> SQL_SELECT_TODOS_PARAMETROS_FACTURADOR();
	public V_Varios_FacturacionBean SQL_SELECT_LISTA_PARAMETROS_DESCARGA_FACTURADOR(String empresa);
}
