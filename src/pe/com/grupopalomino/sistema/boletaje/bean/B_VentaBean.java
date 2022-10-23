package pe.com.grupopalomino.sistema.boletaje.bean;

public class B_VentaBean {
	
	/**
	 * 
	 * LA VENTA PASA POR 3 ESTADOS
	 * R .- SIGNIFICA QUE EL CLIENTE VISA LO HA RESERVADO EN LA PAGINA WEB, ESTO OCURRE CUANDO SELECCIONA UN ASIENTO DEL CROQUIS
	 * C .- SIGNIFICA QUE EL CLIENTE/AGENCIA YA CONFIRMO LA VENTA DE LOS PASAJES. EN CASO SEA CLIENTE VISA, SIGNIFICA QUE YA SE REALIZO LA VENTA EN SI.
	 * E .- SIGNIFICA QUE EL CLIENTE CANCELO O ABANDONO LA TRANSACCION A MEDIO CAMINO. ESTE ESTADO AVISO A LOS SCHEDULERS QUE LA TRANSACCION ESTA LISTA PARA SER ELIMINADA.
	 * 
	 * */

	public static final String ESTADO_RESERVADO = "R";
	public static final String ESTADO_CONFIRMADO = "C";
	public static final String ESTADO_PENDIENTE = "P";
	public static final String MENSAJE_ERROR = "Ha ocurrido un error al intentar recuperar su ticket, para verificar el estado de su transaccion, por favor enviar un correo a ventatelefonica@grupopalomino.com.pe";
	public static final String MENSAJE_ERROR_INTERNO = "Ocurrio un problema Interno, se le enviara un correo dentro de 15 minutos, indicando el estado de su compra, de no presenciar ninguna respuesta en su cuenta, por favor"
			+ " enviar un mensaje a ventatelefonica@grupopalomino.com.pe indicando la incidencia. Gracias.";

	// atributos
	private int Nro;
	private int Salida;
	private int Destino;
	private String DestinoD;
	private String HoraViaje;
	private String HoraViajeIni;
	private String Empresa;
	private String EmpresaD;
	private String Serie;
	private String Numero;
	private String Origen;
	private String OrigenD;
	private String Destino1;
	private String Destino1D;
	private String Tipo;
	private String TipoD;
	private String Asiento;
	private String Retorno;
	private String Autorizo;
	private String Identidad;
	private String IdentidadD;
	private String DNI;
	private String Edad;
	private String Telefono;
	private String Ruc;
	private String Razon;
	private String Usuario;
	private String Terminal;
	private String Agencia;
	private String AgenciaD;
	private String Otro;
	private String Intermedio;
	private String Comida;
	private String Voucher;
	private String Nombre;
	private String agenciaembarque;
	private String agenciaembarqued;
	private String FechaViaje;
	private String FechaEmision;
	private double PrecioAct;
	private double Precio;
	private int Codigo;
	private int B_Identity;
	private String Comentario;
	private String EstadoWeb;
	private String FechaCaducidadWeb;
	private String EticketVisa;
	private String UsuarioVisa;
	private String Eticket;
	private String NumeroTarjeta;
	private String TarjetaHabiente;

	/// VARIABLE AUXILIAR USADA PARA REIMPRESION DE BOLETOS(NO USAR)
	private String Servicio;
	private String HoraCompra;

	// VARIABLE USADA PARA SABER LA FECHA DE EXPIRACION EN LA TRANSACCION DE
	// PAGOEFECTIVO
	private String FechaExpiracion;

	// VARIABLE USADA PARA IDENTIFICAR EL LOGO DEL VOUCHER POR EMPRESA (1 =
	// PALOMINO , 2 = POSEIDON)
	private Integer TipoEmpresa;

	// VARIABLE USADA PARA IDENTIFICAR SI HAY PROMOCIONES (SE INTEGRO EN LA
	// PROMOCION DE RETORNO)
	private String promocionWeb;

	private String token_movil_app;

	// VARIABLE AUXILIAR PARA IDENTIFICAR LA RUTA REAL DEL BUS
	private String DestinoRutaD;

	// ATRIBUTOS PARA VALIDACION DE EMISION DE FACTURA | BOLETA

	private String nroServicio;

	private String nroDestino;

	// ATRIBUTOS PARA VALIDACION DE EMISION DE FACTURA | BOLETA

	private String nroServiciocodigo;

	private String nroDestinocodigo;
	
	public String getNroServiciocodigo() {
		return nroServiciocodigo;
	}
	public void setNroServiciocodigo(String nroServiciocodigo) {
		this.nroServiciocodigo = nroServiciocodigo;
	}
	
	public String getNroDestinocodigo() {
		return nroDestinocodigo;
	}
	public void setNroDestinocodigo(String nroDestinocodigo) {
		this.nroDestinocodigo = nroDestinocodigo;
	}
	
	public String getNroServicio() {
		return nroServicio;
	}

	public void setNroServicio(String nroServicio) {
		this.nroServicio = nroServicio;
	}

	public String getNroDestino() {
		return nroDestino;
	}

	public void setNroDestino(String nroDestino) {
		this.nroDestino = nroDestino;
	}

	public int getNro() {
		return Nro;
	}

	public void setNro(int Nro) {
		this.Nro = Nro;
	}

	public int getSalida() {
		return Salida;
	}

	public void setSalida(int Salida) {
		this.Salida = Salida;
	}

	public int getDestino() {
		return Destino;
	}

	public void setDestino(int Destino) {
		this.Destino = Destino;
	}

	public String getDestinoD() {
		return DestinoD;
	}

	public void setDestinoD(String DestinoD) {
		this.DestinoD = DestinoD;
	}

	public String getHoraViaje() {
		return HoraViaje;
	}

	public void setHoraViaje(String HoraViaje) {
		this.HoraViaje = HoraViaje;
	}

	public String getHoraViajeIni() {
		return HoraViajeIni;
	}

	public void setHoraViajeIni(String HoraViajeIni) {
		this.HoraViajeIni = HoraViajeIni;
	}

	public String getEmpresa() {
		return Empresa;
	}

	public void setEmpresa(String Empresa) {
		this.Empresa = Empresa;
	}

	public String getEmpresaD() {
		return EmpresaD;
	}

	public void setEmpresaD(String EmpresaD) {
		this.EmpresaD = EmpresaD;
	}

	public String getSerie() {
		return Serie;
	}

	public void setSerie(String Serie) {
		this.Serie = Serie;
	}

	public String getNumero() {
		return Numero;
	}

	public void setNumero(String Numero) {
		this.Numero = Numero;
	}

	public String getOrigen() {
		return Origen;
	}

	public void setOrigen(String Origen) {
		this.Origen = Origen;
	}

	public String getOrigenD() {
		return OrigenD;
	}

	public void setOrigenD(String OrigenD) {
		this.OrigenD = OrigenD;
	}

	public String getDestino1() {
		return Destino1;
	}

	public void setDestino1(String Destino1) {
		this.Destino1 = Destino1;
	}

	public String getDestino1D() {
		return Destino1D;
	}

	public void setDestino1D(String Destino1D) {
		this.Destino1D = Destino1D;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String Tipo) {
		this.Tipo = Tipo;
	}

	public String getTipoD() {
		return TipoD;
	}

	public void setTipoD(String TipoD) {
		this.TipoD = TipoD;
	}

	public String getAsiento() {
		return Asiento;
	}

	public void setAsiento(String Asiento) {
		this.Asiento = Asiento;
	}

	public String getRetorno() {
		return Retorno;
	}

	public void setRetorno(String Retorno) {
		this.Retorno = Retorno;
	}

	public String getAutorizo() {
		return Autorizo;
	}

	public void setAutorizo(String Autorizo) {
		this.Autorizo = Autorizo;
	}

	public String getIdentidad() {
		return Identidad;
	}

	public void setIdentidad(String Identidad) {
		this.Identidad = Identidad;
	}

	public String getIdentidadD() {
		return IdentidadD;
	}

	public void setIdentidadD(String IdentidadD) {
		this.IdentidadD = IdentidadD;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String DNI) {
		this.DNI = DNI;
	}

	public String getEdad() {
		return Edad;
	}

	public void setEdad(String Edad) {
		this.Edad = Edad;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String Telefono) {
		this.Telefono = Telefono;
	}

	public String getRuc() {
		return Ruc;
	}

	public void setRuc(String Ruc) {
		this.Ruc = Ruc;
	}

	public String getRazon() {
		return Razon;
	}

	public void setRazon(String Razon) {
		this.Razon = Razon;
	}

	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String Usuario) {
		this.Usuario = Usuario;
	}

	public String getTerminal() {
		return Terminal;
	}

	public void setTerminal(String Terminal) {
		this.Terminal = Terminal;
	}

	public String getAgencia() {
		return Agencia;
	}

	public void setAgencia(String Agencia) {
		this.Agencia = Agencia;
	}

	public String getAgenciaD() {
		return AgenciaD;
	}

	public void setAgenciaD(String AgenciaD) {
		this.AgenciaD = AgenciaD;
	}

	public String getOtro() {
		return Otro;
	}

	public void setOtro(String Otro) {
		this.Otro = Otro;
	}

	public String getIntermedio() {
		return Intermedio;
	}

	public void setIntermedio(String Intermedio) {
		this.Intermedio = Intermedio;
	}

	public String getComida() {
		return Comida;
	}

	public void setComida(String Comida) {
		this.Comida = Comida;
	}

	public String getVoucher() {
		return Voucher;
	}

	public void setVoucher(String Voucher) {
		this.Voucher = Voucher;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String Nombre) {
		this.Nombre = Nombre;
	}

	public String getAgenciaembarque() {
		return agenciaembarque;
	}

	public void setAgenciaembarque(String agenciaembarque) {
		this.agenciaembarque = agenciaembarque;
	}

	public String getAgenciaembarqued() {
		return agenciaembarqued;
	}

	public void setAgenciaembarqued(String agenciaembarqued) {
		this.agenciaembarqued = agenciaembarqued;
	}

	public double getPrecioAct() {
		return PrecioAct;
	}

	public void setPrecioAct(double PrecioAct) {
		this.PrecioAct = PrecioAct;
	}

	public double getPrecio() {
		return Precio;
	}

	public void setPrecio(double Precio) {
		this.Precio = Precio;
	}

	public int getCodigo() {
		return Codigo;
	}

	public void setCodigo(int Codigo) {
		this.Codigo = Codigo;
	}

	public int getB_Identity() {
		return B_Identity;
	}

	public void setB_Identity(int B_Identity) {
		this.B_Identity = B_Identity;
	}

	public String getComentario() {
		return Comentario;
	}

	public void setComentario(String Comentario) {
		this.Comentario = Comentario;
	}

	public void setFechaViaje(String fechaViaje) {
		FechaViaje = fechaViaje;
	}

	public String getFechaEmision() {
		return FechaEmision.substring(0,10);
	}

	public void setFechaEmision(String fechaEmision) {
		FechaEmision = fechaEmision;
	}

	public String getFechaViaje() {
		return FechaViaje.substring(0,10);
	}

	public String getDestinoConcatDestino1D() {
		return getDestino1() + "," + getDestino1D();
	}

	public String getServicio() {
		return Servicio;
	}

	public void setServicio(String servicio) {
		Servicio = servicio;
	}

	public void setEstadoWeb(String estadoWeb) {
		EstadoWeb = estadoWeb;
	}

	public String getEstadoWeb() {
		return EstadoWeb;
	}

	public void setFechaCaducidadWeb(String fechaCaducidadWeb) {
		FechaCaducidadWeb = fechaCaducidadWeb;
	}

	public String getFechaCaducidadWeb() {
		return FechaCaducidadWeb;
	}

	public void setHoraCompra(String horaCompra) {
		HoraCompra = horaCompra;
	}

	public String getHoraCompra() {
		return HoraCompra;
	}

	public void setEticketVisa(String eticketVisa) {
		EticketVisa = eticketVisa;
	}

	public String getEticketVisa() {
		return EticketVisa;
	}

	public void setUsuarioVisa(String usuarioVisa) {
		UsuarioVisa = usuarioVisa;
	}

	public String getUsuarioVisa() {
		return UsuarioVisa;
	}

	public String getEticket() {
		return Eticket;
	}

	public void setEticket(String eticket) {
		Eticket = eticket;
	}

	public String getNumeroTarjeta() {
		return NumeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		NumeroTarjeta = numeroTarjeta;
	}

	public String getTarjetaHabiente() {
		return TarjetaHabiente;
	}

	public void setTarjetaHabiente(String tarjetaHabiente) {
		TarjetaHabiente = tarjetaHabiente;
	}

	public String getFechaExpiracion() {
		return FechaExpiracion;
	}

	public void setFechaExpiracion(String fechaExpiracion) {
		FechaExpiracion = fechaExpiracion;
	}

	public void setTipoEmpresa(Integer tipoEmpresa) {
		TipoEmpresa = tipoEmpresa;
	}

	public Integer getTipoEmpresa() {
		return TipoEmpresa;
	}

	public void setPromocionWeb(String promocionWeb) {
		this.promocionWeb = promocionWeb;
	}

	public String getPromocionWeb() {
		return promocionWeb;
	}

	/*
	 * public Integer getPromocionWeb() { return promocionWeb; }
	 * 
	 * public void setPromocionWeb(Integer promocionWeb) { this.promocionWeb =
	 * promocionWeb; }
	 */

	public void setToken_movil_app(String token_movil_app) {
		this.token_movil_app = token_movil_app;
	}

	public String getToken_movil_app() {
		return token_movil_app;
	}

	public void setDestinoRutaD(String destinoRutaD) {
		DestinoRutaD = destinoRutaD;
	}

	public String getDestinoRutaD() {
		return DestinoRutaD;
	}

	@Override
	public String toString() {
		return "B_VentaBean [Nro=" + Nro + ", Salida=" + Salida + ", Destino=" + Destino + ", DestinoD=" + DestinoD
				+ ", HoraViaje=" + HoraViaje + ", HoraViajeIni=" + HoraViajeIni + ", Empresa=" + Empresa + ", EmpresaD="
				+ EmpresaD + ", Serie=" + Serie + ", Numero=" + Numero + ", Origen=" + Origen + ", OrigenD=" + OrigenD
				+ ", Destino1=" + Destino1 + ", Destino1D=" + Destino1D + ", Tipo=" + Tipo + ", TipoD=" + TipoD
				+ ", Asiento=" + Asiento + ", Retorno=" + Retorno + ", Autorizo=" + Autorizo + ", Identidad="
				+ Identidad + ", IdentidadD=" + IdentidadD + ", DNI=" + DNI + ", Edad=" + Edad + ", Telefono="
				+ Telefono + ", Ruc=" + Ruc + ", Razon=" + Razon + ", Usuario=" + Usuario + ", Terminal=" + Terminal
				+ ", Agencia=" + Agencia + ", AgenciaD=" + AgenciaD + ", Otro=" + Otro + ", Intermedio=" + Intermedio
				+ ", Comida=" + Comida + ", Voucher=" + Voucher + ", Nombre=" + Nombre + ", agenciaembarque="
				+ agenciaembarque + ", agenciaembarqued=" + agenciaembarqued + ", FechaViaje=" + FechaViaje
				+ ", FechaEmision=" + FechaEmision + ", PrecioAct=" + PrecioAct + ", Precio=" + Precio + ", Codigo="
				+ Codigo + ", B_Identity=" + B_Identity + ", Comentario=" + Comentario + ", EstadoWeb=" + EstadoWeb
				+ ", FechaCaducidadWeb=" + FechaCaducidadWeb + ", EticketVisa=" + EticketVisa + ", UsuarioVisa="
				+ UsuarioVisa + ", Eticket=" + Eticket + ", NumeroTarjeta=" + NumeroTarjeta + ", TarjetaHabiente="
				+ TarjetaHabiente + ", Servicio=" + Servicio + ", HoraCompra=" + HoraCompra + ", FechaExpiracion="
				+ FechaExpiracion + ", TipoEmpresa=" + TipoEmpresa + ", promocionWeb=" + promocionWeb
				+ ", token_movil_app=" + token_movil_app + ", DestinoRutaD=" + DestinoRutaD + "]";
	}

}
