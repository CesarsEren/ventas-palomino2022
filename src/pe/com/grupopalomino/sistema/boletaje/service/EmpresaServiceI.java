package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.V_EmpresasBean;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;
import pe.com.grupopalomino.sistema.boletaje.dao.V_EmpresaDao;

public class EmpresaServiceI implements EmpresaService {
	
	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	
	V_EmpresaDao empresaDao = factoria.getEmpresas();

	@Override
	public List<V_EmpresasBean> listaEmpresas() throws Exception {
		
		return empresaDao.listaEmpresas();
	}
	
	

}
