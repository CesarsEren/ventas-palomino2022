package pe.com.grupopalomino.sistema.boletaje.service;


import java.util.List;
import java.util.Map;

import pe.com.grupopalomino.sistema.boletaje.bean.B_AtencionReclamosBean;
import pe.com.grupopalomino.sistema.boletaje.bean.B_AtencionReclamosBeanDetalle;
import pe.com.grupopalomino.sistema.boletaje.dao.B_AtencionReclamosDao;
import pe.com.grupopalomino.sistema.boletaje.dao.DAOFactory;

public class AtencionReclamosServiceI implements AtencionReclamosService {

	DAOFactory factoria = DAOFactory.getFactorty(DAOFactory.MSSQL);
	B_AtencionReclamosDao dao = factoria.getAtencionReclamos();
	
	
	@Override
	public int registraReclamos(B_AtencionReclamosBean bean) throws Exception {
		// TODO Auto-generated method stub
		return dao.registraReclamos(bean); 
	}


	@Override
	public B_AtencionReclamosBean selecReclamos(int Nro,int Periodo,String Empresa,String AtencionReclamos) throws Exception {
		// TODO Auto-generated method stub
		return dao.selecReclamos(Nro,Periodo,Empresa,AtencionReclamos); 
	}


	@Override
	public B_AtencionReclamosBeanDetalle selecReclamosDetalle(String Serie, String Numero, String Documento,String Empresa) throws Exception {
		// TODO Auto-generated method stub
		return dao.selecReclamosDetalle(Serie, Numero, Documento,Empresa); 
	}


	@Override
	public List<B_AtencionReclamosBean> selecReclamos(String query, Integer limit, Integer offset,String tipo,String agencia,String servicio) throws Exception {
		// TODO Auto-generated method stub
		return dao.selecReclamos(query,limit,offset,tipo,agencia,servicio);
	}


	@Override
	public int selectReclamosTotales(String tipo) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectReclamosTotales(tipo);
	}


	@Override
	public int updateReclamos(Integer Id,String detalle) throws Exception {
		// TODO Auto-generated method stub
		return dao.updateReclamos(Id,detalle);
	}
	
	@Override
	public List<B_AtencionReclamosBean> selecReclamosReporte(String tiporeclamo) throws Exception {
		// TODO Auto-generated method stub
		return dao.selecReclamosReporte(tiporeclamo);
	}


	@Override
	public List<B_AtencionReclamosBean> selecReclamosReporte(String query, Integer limit, Integer offset)
			throws Exception {
		// TODO Auto-generated method stub
		return dao.selecReclamosReporte(query, limit, offset);
	}


	@Override
	public List<B_AtencionReclamosBean> selecReclamosDni(String query, Integer limit, Integer offset, String tipo,
			
			String agencia, String servicio, String dni) throws Exception {
		// TODO Auto-generated method stub
		
		return dao.selecReclamosDni(query,limit, offset,tipo,agencia,servicio,dni);
	}


	@Override
	public List<Map<String,Object>> ObtenerAtencionListaReclamosRpte() {
		// TODO Auto-generated method stub
		return dao.ObtenerAtencionListaReclamosRpte();
	} 
}
