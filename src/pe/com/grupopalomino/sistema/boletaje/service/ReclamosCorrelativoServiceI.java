package pe.com.grupopalomino.sistema.boletaje.service;

import pe.com.grupopalomino.sistema.boletaje.bean.B_ReclamosCorrelativoBean;
import pe.com.grupopalomino.sistema.boletaje.dao.B_ReclamosCorrelativoDao;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;

public class ReclamosCorrelativoServiceI implements ReclamosCorrelativoService {

	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	B_ReclamosCorrelativoDao dao = factoria.getReclamosCorrelativos();
	
	
	@Override
	public B_ReclamosCorrelativoBean selecReclamosCorrelativo(String Empresa,String atencionreclamos) throws Exception {
		// TODO Auto-generated method stub
		return dao.selecReclamosCorrelativo(Empresa,atencionreclamos);
	}
	



}
