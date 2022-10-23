package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.B_MotivoBean;
import pe.com.grupopalomino.sistema.boletaje.dao.B_MotivoDao;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;

public class MotivoServiceI implements MotivoService{
	
	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	B_MotivoDao dao = factoria.getMotivos();
	
	@Override
	public List<B_MotivoBean> MuestraMotivos(String MotivoReclamo) throws Exception {
		// TODO Auto-generated method stub
		return dao.MuestraMotivos(MotivoReclamo); 
	}
	
	
}
