package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.B_ProgramacionSalidaBean;
import pe.com.grupopalomino.sistema.boletaje.bean.Precio_Desde;
import pe.com.grupopalomino.sistema.boletaje.dao.B_ProgramacionSalidaDao;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;


public class ProgramacionSalidaServiceI implements ProgramacionSalidaService  {

	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	B_ProgramacionSalidaDao dao = factoria.getProgamacionSalidas();
	
	@Override
	public List<B_ProgramacionSalidaBean> getSalidas(String fecha,String rol,String origenAgencia,String destino,Integer promocionWeb) throws Exception {
		// TODO Auto-generated method stub
		return dao.getSalidas(fecha,rol,origenAgencia,destino,promocionWeb);
	}
	
	@Override
	public List<Precio_Desde> getPrecioDesde(Integer programacion) throws Exception {
		// TODO Auto-generated method stub
		return dao.getPrecioDesde(programacion);
	}

	@Override
	public B_ProgramacionSalidaBean getSalidasNro(int Nro) throws Exception {
		// TODO Auto-generated method stub
		return dao.getSalidasNro(Nro);
	}

	@Override
	public List<B_ProgramacionSalidaBean> getSalidasAgencias(int Nro,String origenAgencia) throws Exception {
		// TODO Auto-generated method stub
		return dao.getSalidasAgencias(Nro,origenAgencia);
	}
	@Override
	public List<B_ProgramacionSalidaBean> getSalidasCiudades(String fecha, String origenCiudad,String destinoCiudad)
			throws Exception {
		// TODO Auto-generated method stub
		return dao.getSalidasCiudades(fecha, origenCiudad,destinoCiudad); 
		
	}
	@Override
	public B_ProgramacionSalidaBean getSalidasPrecioPromocion(int Nro,String destino,Integer promocionWeb) throws Exception {
		// TODO Auto-generated method stub
		return dao.getSalidasPrecioPromocion(Nro,destino,promocionWeb);
	}

}
