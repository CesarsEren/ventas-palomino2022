package pe.com.grupopalomino.sistema.boletaje.service;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.B_PrecioProgramacion;
import pe.com.grupopalomino.sistema.boletaje.dao.B_PrecioProgramacionDao;
import pe.com.grupopalomino.sistema.boletaje.dao.B_PrecioProgramacionIDao;

public class B_PrecioProgramacionServiceI implements B_PrecioProgramacionService{

	B_PrecioProgramacionDao b_precioProgramacion = new B_PrecioProgramacionIDao();
	
	@Override
	public List<B_PrecioProgramacion> SQL_ObtenerAsientosPrecioPromocionConNroProgramacion(int Nro) {
		// TODO Auto-generated method stub
		return b_precioProgramacion.SQL_ObtenerAsientosPrecioPromocionConNroProgramacion(Nro);
	}

}
