package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;
import pe.com.grupopalomino.sistema.boletaje.bean.V_DestinosBean;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;
import pe.com.grupopalomino.sistema.boletaje.dao.V_DestinosDao;

public class DestinoServiceI implements DestinosService{

	
	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	V_DestinosDao dao = factoria.getDestino();
	
	@Override
	public List<V_DestinosBean> getDestinos() throws Exception {
		// TODO Auto-generated method stub
		return dao.getDestinos();
	}

	@Override
	public List<V_DestinosBean> getDestinos(String ciudad) throws Exception {
		// TODO Auto-generated method stub
		return dao.getDestinos(ciudad);
	}

	@Override
	public V_DestinosBean getDestinoNro(int Nro) throws Exception {
		// TODO Auto-generated method stub
		return dao.getDestinoNro(Nro);
	}

	@Override
	public V_DestinosBean getDestinoVuelta(String destino, String origen) throws Exception {
		// TODO Auto-generated method stub
		return dao.getDestinoVuelta(destino, origen);
	}

	@Override
	public List<V_DestinosBean> getDestinosID(String origen) throws Exception {
		// TODO Auto-generated method stub
		return dao.getDestinosID(origen);
	}

	@Override
	public List<V_DestinosBean> getDestinosXId(String ids) throws Exception {
		// TODO Auto-generated method stub
		return dao.getDestinosXId(ids);
	}

	@Override
	public List<V_DestinosBean> getDestinosTodos() throws Exception {
		// TODO Auto-generated method stub
		return dao.getDestinosTodos();
	}

	@Override
	public List<V_DestinosBean> getDestinosXorigen(String origen) throws Exception {
		// TODO Auto-generated method stub
		return dao.getDestinosXorigen(origen); 
	}

	@Override
	public V_DestinosBean getDestinoXIdaYVuelta(String origen, String destino) throws Exception {
		return dao.getDestinoXIdaYVuelta(origen, destino);
	}

/*	@Override
	public List<B_ProgramacionSalidaBean> getSalidas(B_ProgramacionSalidaBean bean) throws Exception {
		// TODO Auto-generated method stub
		return dao.getSalidas(bean);
	}
*/
}
