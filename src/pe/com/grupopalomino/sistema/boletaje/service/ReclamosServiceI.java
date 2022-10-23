package pe.com.grupopalomino.sistema.boletaje.service;


import pe.com.grupopalomino.sistema.boletaje.bean.B_ReclamosBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_ReclamosBeanDetalle;
import pe.com.grupopalomino.sistema.boletaje.dao.B_ReclamosDao;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;

public class ReclamosServiceI implements ReclamosService {

	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	B_ReclamosDao dao = factoria.getReclamos();
	
	
	@Override
	public int registraReclamos(B_ReclamosBean bean) throws Exception {
		// TODO Auto-generated method stub
		return dao.registraReclamos(bean); 
	}


	@Override
	public B_ReclamosBean selecReclamos(int Nro,int Periodo,String Empresa) throws Exception {
		// TODO Auto-generated method stub
		return dao.selecReclamos(Nro,Periodo,Empresa); 
	}


	@Override
	public B_ReclamosBeanDetalle selecReclamosDetalle(String Serie, String Numero, String Documento,String Empresa) throws Exception {
		// TODO Auto-generated method stub
		return dao.selecReclamosDetalle(Serie, Numero, Documento,Empresa); 
	}


}
