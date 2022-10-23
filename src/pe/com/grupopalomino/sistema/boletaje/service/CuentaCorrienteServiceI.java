package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.B_CuentaCorrienteBean;
import pe.com.grupopalomino.sistema.boletaje.dao.B_CuentaCorrienteDao;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;

public class CuentaCorrienteServiceI implements CuentaCorrienteService {

	
	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	B_CuentaCorrienteDao dao = factoria.getCuentaCorriente();
	
	
	@Override
	public int insertCuentaCorriente(B_CuentaCorrienteBean bean) throws Exception {
		// TODO Auto-generated method stub
		return dao.insertCuentaCorriente(bean);
	}

	@Override
	public List<B_CuentaCorrienteBean> ListaCuentaCorriente(B_CuentaCorrienteBean bean) throws Exception {
		// TODO Auto-generated method stub
		return dao.ListaCuentaCorriente(bean);
	}

	@Override
	public List<B_CuentaCorrienteBean> EstadoCuentaCorriente(String RUC,String Tipo,String FechaInicial,String FechaFinal,int offset, int limit,String Usuario) throws Exception {
		// TODO Auto-generated method stub
		return dao.EstadoCuentaCorriente(RUC,Tipo,FechaInicial,FechaFinal, offset,limit,Usuario);
	}

	@Override
	public int deleteCuentaCorriente(int Nro,int NroBol) throws Exception {
		// TODO Auto-generated method stub
		return dao.deleteCuentaCorriente(Nro,NroBol); 
	}

	@Override
	public int updateCuentaCorrienteConfirmada(int Nro, int NroBol,String estado) throws Exception {
		// TODO Auto-generated method stub
		return dao.updateCuentaCorrienteConfirmada(Nro, NroBol,estado); 
	}

	@Override
	public int EstadoCountCuentaCorriente(String RUC, String Tipo, String FechaInicial,
			String FechaFinal,String Usuario) throws Exception {
		// TODO Auto-generated method stub
		return dao.EstadoCountCuentaCorriente(RUC, Tipo, FechaInicial, FechaFinal,Usuario); 
	}

	@Override
	public double VentasRealizadasCuentaCorriente(String RUC,String Usuario) throws Exception {
		// TODO Auto-generated method stub
		return dao.VentasRealizadasCuentaCorriente(RUC,Usuario); 
	}

	@Override
	public int deleteCuentaCorrientePagoEfectivo(int NroBol) throws Exception {
		// TODO Auto-generated method stub
		return dao.deleteCuentaCorrientePagoEfectivo(NroBol); 
	}

	@Override
	public B_CuentaCorrienteBean ListaVentaPagoEfectivoCuentaCorriente(int NroBol) throws Exception {
		// TODO Auto-generated method stub
		return dao.ListaVentaPagoEfectivoCuentaCorriente(NroBol); 
	} 

}
