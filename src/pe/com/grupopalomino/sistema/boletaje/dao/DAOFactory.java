package pe.com.grupopalomino.sistema.boletaje.dao;



public abstract class DAOFactory {
	
	public static final int MYSQL = 1;
	public static final int MSSQL = 2;
	public static final int ORACLE = 3;
	

	//objetos daos
	public abstract V_Usuarios_WebDao getUsuarioWeb();
	public abstract B_VentaBoletajeDao getVenta();
	public abstract V_DestinosDao getDestino();
	public abstract B_ProgramacionSalidaDao getProgamacionSalidas();
	public abstract B_CorrelativosDao getCorrelativos();
	public abstract V_CroquisBusDao getCroquisBusDao();
	public abstract FuncionesGeneralesDao getFunciones();
	public abstract V_PasajerosDao getPasajeros();
	public abstract V_ClientesDao getClientes();
	public abstract B_CuentaCorrienteDao getCuentaCorriente();
	public abstract V_ComputerDao getTerminal();
	public abstract V_AgenciasDao getAgencias();
	public abstract V_ServiciosDao getServicios();
	public abstract V_RutasDao getRutas();
	public abstract V_VariosDao getVarios();
	public abstract V_AgenciasWebDao getAgenciasWeb();
	public abstract V_Clientes_RutaPrecioDao getClienteRutaPrecio();
	public abstract V_UsuarioHorariosDao getUsuariosHorarios();
	public abstract V_ProgramacionPagoDao getProgramacionpagos();
	public abstract B_ReclamosDao getReclamos();
	public abstract V_EmpresaDao getEmpresas();
	public abstract B_MotivoDao getMotivos();
	public abstract V_TipoDocumentosDao getTipoDocumentos();
	public abstract B_EncomiendasDao getEncomiendas();
	public abstract B_ReclamosCorrelativoDao getReclamosCorrelativos();
	public abstract B_VersionesAppDao getVersionesApp();
	public abstract V_DestinosClienteWebDao getDestinosClientesWeb();
	public abstract V_CiudadesDao getCiudades();
	public abstract V_DestinosMapaDao getDestinosMapa();
	public abstract V_DestinosAgenciasWebDao getDestinosAgenciasWeb();
	public abstract B_PreguntasFrecuentesDao getPreguntasFrecuentesWeb();
	public abstract B_AtencionReclamosDao getAtencionReclamos();
	public abstract V_Varios_FacturacionDao getVariosFacturacion();
	public abstract B_FacturacionDao getVentaFacturacion();
	public abstract V_Ventas_FacturacionDao getVentasFacturacion();
	public abstract V_Promo_RetornoCDao getTipoPromocionesRetorno();
	
	// Se aplica polimorfismo
	public static DAOFactory getFactorty(int bd) {
		switch (bd) {
			case MSSQL:
				return new DAOFactoryRD();	
		}
		return null;
	}

}
