package pe.com.grupopalomino.sistema.boletaje.service;

import pe.com.grupopalomino.sistema.boletaje.bean.V_Promo_RetornoCBean;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;
import pe.com.grupopalomino.sistema.boletaje.dao.V_Promo_RetornoCDao;

public class Promo_RetornoCServiceI  implements Promo_RetornoCService{

	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	V_Promo_RetornoCDao dao = factoria.getTipoPromocionesRetorno();
	
	
	@Override
	public V_Promo_RetornoCBean SQL_SELECT_TIPO_PROMOCION_RETORNO() throws Exception {
		return dao.SQL_SELECT_TIPO_PROMOCION_RETORNO();
	}
	
}
