package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;
import pe.com.grupopalomino.sistema.boletaje.bean.V_ProgramacionPagosBean;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;
import pe.com.grupopalomino.sistema.boletaje.dao.V_ProgramacionPagoDao;


public class ProgramacionPagoServiceI implements ProgramacionPagoService {
	
	
	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	V_ProgramacionPagoDao dao = factoria.getProgramacionpagos();

	@Override
	public int insertProgramacionPago(V_ProgramacionPagosBean bean) throws Exception {
		// TODO Auto-generated method stub
		return dao.insertProgramacionPago(bean);
	}

	@Override
	public int deleteProgramacionPago(int Id, String Usuario) throws Exception {
		// TODO Auto-generated method stub
		return dao.deleteProgramacionPago(Id, Usuario);
		
	}

	@Override
	public int updateProgramacionPago(V_ProgramacionPagosBean bean) throws Exception {
		// TODO Auto-generated method stub
		return dao.updateProgramacionPago(bean); 
	}

	@Override
	public List<V_ProgramacionPagosBean> listaProgramacionPagos(String query, Integer limit, Integer offset)
			throws Exception {
		// TODO Auto-generated method stub
		return dao.listaProgramacionPagos(query, limit, offset); 
	}

	@Override
	public V_ProgramacionPagosBean listaProgramacionPagos(String Usuario,int Periodo) throws Exception {
		// TODO Auto-generated method stub
		return dao.listaProgramacionPagos(Usuario,Periodo); 
	}

	@Override
	public V_ProgramacionPagosBean listaProgramacionPagosXid(int Id) throws Exception {
		// TODO Auto-generated method stub
		return dao.listaProgramacionPagosXid(Id);
	}

	@Override
	public int V_ProgramacionPagosCount() throws Exception {
		// TODO Auto-generated method stub
		return dao.V_ProgramacionPagosCount();
	}

	@Override
	public V_ProgramacionPagosBean VerificaProgramacionPago(String Usuario) throws Exception {
		// TODO Auto-generated method stub
		return dao.VerificaProgramacionPago(Usuario); 
	}

}
