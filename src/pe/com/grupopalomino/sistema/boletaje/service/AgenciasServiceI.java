package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.V_AgenciasBean;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;
import pe.com.grupopalomino.sistema.boletaje.dao.V_AgenciasDao;


public class AgenciasServiceI implements AgenciasService{

	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	V_AgenciasDao dao = factoria.getAgencias();
	
	@Override
	public V_AgenciasBean listAgencias(String Codigo) throws Exception {
		// TODO Auto-generated method stub
		return dao.listAgencias(Codigo);
	}

	@Override
	public List<V_AgenciasBean> getListaAsientosOcupadoXAgencia(int nroProgramacion) throws Exception {
		// TODO Auto-generated method stub
		return dao.getListaAsientosOcupadoXAgencia(nroProgramacion);
	}

	@Override
	public List<V_AgenciasBean> getListaAsientosReservadoXAgencia(int nroProgramacion) throws Exception {
		// TODO Auto-generated method stub
		return dao.getListaAsientosReservadoXAgencia(nroProgramacion); 
	}

	@Override
	public List<V_AgenciasBean> getListaComida(int nroProgramacion) throws Exception {
		// TODO Auto-generated method stub
		return dao.getListaComida(nroProgramacion); 
	}
	@Override
	public List<V_AgenciasBean> getListaAgenciasDisponibles(String ciudad,String tipo) throws Exception {
		// TODO Auto-generated method stub
		return dao.getListaAgenciasDisponibles(ciudad,tipo); 
	}

	@Override
	public List<V_AgenciasBean> SQL_SELECT_AGENCIAS() throws Exception {
		// TODO Auto-generated method stub
		return dao.SQL_SELECT_AGENCIAS();
	}

}
