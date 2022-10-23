package pe.com.grupopalomino.sistema.boletaje.dao;



public class DAOFactoryRD extends DAOFactory{

	@Override
	public V_Usuarios_WebDao getUsuarioWeb() {
		// TODO Auto-generated method stub
		return new V_Usuarios_WebIDao();
	}

	@Override
	public B_VentaBoletajeDao getVenta() {
		// TODO Auto-generated method stub
		return new B_VentaBoletajeIDao();
	}

	@Override
	public V_DestinosDao getDestino() {
		// TODO Auto-generated method stub
		return new V_DestinosIDao();
	}

	@Override
	public B_ProgramacionSalidaDao getProgamacionSalidas() {
		// TODO Auto-generated method stub
		return new B_ProgramacionSalidaIDao();
	}

	@Override
	public B_CorrelativosDao getCorrelativos() {
		// TODO Auto-generated method stub
		return new B_CorrelativosIDao();
	}

	@Override
	public V_CroquisBusDao getCroquisBusDao() {
		// TODO Auto-generated method stub
		return new V_CroquisBusIDao();
	}

	@Override
	public FuncionesGeneralesDao getFunciones() {
		// TODO Auto-generated method stub
		return new FuncionesGeneralesIDao();
	}

	@Override
	public V_PasajerosDao getPasajeros() {
		// TODO Auto-generated method stub
		return new V_PasajerosIDao();
	}

	@Override
	public V_ClientesDao getClientes() {
		// TODO Auto-generated method stub
		return new V_ClientesIDao();
	}

	@Override
	public B_CuentaCorrienteDao getCuentaCorriente() {
		// TODO Auto-generated method stub
		return new B_CuentaCorrienteIDao();
	}

	@Override
	public V_ComputerDao getTerminal() {
		// TODO Auto-generated method stub
		return new V_ComputerIDao();
	}

	@Override
	public V_AgenciasDao getAgencias() {
		// TODO Auto-generated method stub
		return new V_AgenciasIDao();
	}

	@Override
	public V_ServiciosDao getServicios() {
		// TODO Auto-generated method stub
		return new V_ServiciosIDao();
	}

	@Override
	public V_RutasDao getRutas() {
		// TODO Auto-generated method stub
		return new V_RutasIDao();
	}

	@Override
	public V_VariosDao getVarios() {
		// TODO Auto-generated method stub
		return new V_VariosIDao();
	}

	@Override
	public V_AgenciasWebDao getAgenciasWeb() {
		// TODO Auto-generated method stub
		return new V_AgenciasWebIDao();
	}

	@Override
	public V_Clientes_RutaPrecioDao getClienteRutaPrecio() {
		// TODO Auto-generated method stub
		return new V_Clientes_RutaPrecioIDao();
	}
	
	@Override
	public V_UsuarioHorariosDao getUsuariosHorarios() {
		// TODO Auto-generated method stub
		return new V_UsuarioHorariosIDao();
	}

	@Override
	public V_ProgramacionPagoDao getProgramacionpagos() {
		// TODO Auto-generated method stub
		return new V_ProgramacionPagoIDao();
	}
	
	@Override
	public B_ReclamosDao getReclamos() {
		// TODO Auto-generated method stub
		return new B_ReclamosIDao();
	}

	@Override
	public V_EmpresaDao getEmpresas() {
		// TODO Auto-generated method stub
		return new V_EmpresaIDao();
	}

	@Override
	public B_MotivoDao getMotivos() {
		// TODO Auto-generated method stub
		return new B_MotivoIDao();
	}

	@Override
	public V_TipoDocumentosDao getTipoDocumentos() {
		// TODO Auto-generated method stub
		return new V_TipoDocumentoslDao();
	}

	@Override
	public B_EncomiendasDao getEncomiendas() {
		// TODO Auto-generated method stub
		return new B_EncomiendasIDao();
	}

	@Override
	public B_ReclamosCorrelativoDao getReclamosCorrelativos() {
		// TODO Auto-generated method stub
		return new B_ReclamosCorrelativoIDao();
	}

	@Override
	public B_VersionesAppDao getVersionesApp() {
		// TODO Auto-generated method stub
		return new B_VersionesAppIDao();
	}
	@Override
	public V_DestinosClienteWebDao getDestinosClientesWeb() {
		// TODO Auto-generated method stub
		return new V_DestinosClienteWebIDao();
	}
	
	@Override
	public V_CiudadesDao getCiudades() {
		// TODO Auto-generated method stub
		return new V_CiudadesIDao();
	}

	@Override
	public V_DestinosMapaDao getDestinosMapa() {
		// TODO Auto-generated method stub
		return new V_DestinosMapaIDao();
	}

	@Override
	public V_DestinosAgenciasWebDao getDestinosAgenciasWeb() {
		// TODO Auto-generated method stub
		return new V_DestinosAgenciasWebIDao();
	}
	
	@Override
	public B_PreguntasFrecuentesDao getPreguntasFrecuentesWeb() {
		// TODO Auto-generated method stub
		return new B_PreguntasFrecuentesIDao();
	}
	
	@Override
	public B_AtencionReclamosDao getAtencionReclamos() {
		// TODO Auto-generated method stub
		return new B_AtencionReclamosIDao();
	}

	@Override
	public V_Varios_FacturacionDao getVariosFacturacion() {
		// TODO Auto-generated method stub
		return new V_Varios_FacturacionIDao();
	}

	@Override
	public B_FacturacionDao getVentaFacturacion() {
		// TODO Auto-generated method stub
		return new B_FacturacionIDao();
	}

	@Override
	public V_Ventas_FacturacionDao getVentasFacturacion() {
		// TODO Auto-generated method stub
		return new V_Ventas_FacturacionIDao();
	}
	
	@Override
	public V_Promo_RetornoCDao getTipoPromocionesRetorno() {
		return new V_Promo_RetornoCIDao();
	}

	
}
