package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.V_Varios_FacturacionBean;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;
import pe.com.grupopalomino.sistema.boletaje.dao.V_Varios_FacturacionDao;

public class Varios_FacturacionServiceI implements Varios_FacturacionService {
	
	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	V_Varios_FacturacionDao dao = factoria.getVariosFacturacion();

	@Override
	public V_Varios_FacturacionBean SQL_SELECT_LISTA_PARAMETROS_FACTURADOR(String empresa) {
		return dao.SQL_SELECT_LISTA_PARAMETROS_FACTURADOR(empresa);
	}

	@Override
	public V_Varios_FacturacionBean SQL_SELECT_LISTA_PARAMETROS_DESCARGA_FACTURADOR(String empresa) {
		return dao.SQL_SELECT_LISTA_PARAMETROS_DESCARGA_FACTURADOR(empresa);
	}

	@Override
	public List<V_Varios_FacturacionBean> SQL_SELECT_TODOS_PARAMETROS_FACTURADOR() {
		return dao.SQL_SELECT_TODOS_PARAMETROS_FACTURADOR();
	}

}
