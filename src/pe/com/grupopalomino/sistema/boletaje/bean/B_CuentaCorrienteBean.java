package pe.com.grupopalomino.sistema.boletaje.bean;

public class B_CuentaCorrienteBean {

	private int Nro,NroAplic,NroDoc,Pago,NroBol;
	private double Monto;
	private String FechaEmision,Empresa,EmpresaD,
	Documento,DocumentoD,Serie,Numero,Ruc,Razon,
	IdDoc,Referencia,Usuario,Terminal,Agencia,AgenciaD,ChLeTr,
	Destino,DestinoD,Servicio,ServicioD,Entregado,FechaEntrega,
	Voucher,Flag;
	private String EstadoWeb;
	private String EticketVisa;
	private String UsuarioVisa;
	private String Eticket;
	public static final String ESTADO_RESERVADO = "R";
	public static final String ESTADO_CONFIRMADO = "C";
	
	// VARIABLES EXCLUSIVAS PARA EL REPORTE DE ESTADO DE CUENTA 
	private double total,pagos,saldo;
	private String FechaViaje,Nombre,OrigenD;
	
	public int getNro() {
		return Nro;
	}
	public void setNro(int nro) {
		Nro = nro;
	}
	public int getNroAplic() {
		return NroAplic;
	}
	public void setNroAplic(int nroAplic) {
		NroAplic = nroAplic;
	}
	
	public double getMonto() {
		return Monto;
	}
	public void setMonto(double monto) {
		Monto = monto;
	}
	public int getPago() {
		return Pago;
	}
	public void setPago(int pago) {
		Pago = pago;
	}
	public int getNroBol() {
		return NroBol;
	}
	public void setNroBol(int nroBol) {
		NroBol = nroBol;
	}
	public String getFechaEmision() {
		return FechaEmision;
	}
	public void setFechaEmision(String fechaEmision) {
		FechaEmision = fechaEmision;
	}
	public String getEmpresa() {
		return Empresa;
	}
	public void setEmpresa(String empresa) {
		Empresa = empresa;
	}
	public String getEmpresaD() {
		return EmpresaD;
	}
	public void setEmpresaD(String empresaD) {
		EmpresaD = empresaD;
	}
	public String getDocumento() {
		return Documento;
	}
	public void setDocumento(String documento) {
		Documento = documento;
	}
	public String getDocumentoD() {
		return DocumentoD;
	}
	public void setDocumentoD(String documentoD) {
		DocumentoD = documentoD;
	}
	public String getSerie() {
		return Serie;
	}
	public void setSerie(String serie) {
		Serie = serie;
	}
	public String getNumero() {
		return Numero;
	}
	public void setNumero(String numero) {
		Numero = numero;
	}
	public String getRuc() {
		return Ruc;
	}
	public void setRuc(String ruc) {
		Ruc = ruc;
	}
	public String getRazon() {
		return Razon;
	}
	public void setRazon(String razon) {
		Razon = razon;
	}
	public String getIdDoc() {
		return IdDoc;
	}
	public void setIdDoc(String idDoc) {
		IdDoc = idDoc;
	}
	public String getReferencia() {
		return Referencia;
	}
	public void setReferencia(String referencia) {
		Referencia = referencia;
	}
	public String getUsuario() {
		return Usuario;
	}
	public void setUsuario(String usuario) {
		Usuario = usuario;
	}
	public String getTerminal() {
		return Terminal;
	}
	public void setTerminal(String terminal) {
		Terminal = terminal;
	}
	public String getAgencia() {
		return Agencia;
	}
	public void setAgencia(String agencia) {
		Agencia = agencia;
	}
	public String getAgenciaD() {
		return AgenciaD;
	}
	public void setAgenciaD(String agenciaD) {
		AgenciaD = agenciaD;
	}
	public String getChLeTr() {
		return ChLeTr;
	}
	public void setChLeTr(String chLeTr) {
		ChLeTr = chLeTr;
	}
	public String getDestino() {
		return Destino;
	}
	public void setDestino(String destino) {
		Destino = destino;
	}
	public String getDestinoD() {
		return DestinoD;
	}
	public void setDestinoD(String destinoD) {
		DestinoD = destinoD;
	}
	public String getServicio() {
		return Servicio;
	}
	public void setServicio(String servicio) {
		Servicio = servicio;
	}
	public String getServicioD() {
		return ServicioD;
	}
	public void setServicioD(String servicioD) {
		ServicioD = servicioD;
	}
	public String getEntregado() {
		return Entregado;
	}
	public void setEntregado(String entregado) {
		Entregado = entregado;
	}
	public String getFechaEntrega() {
		return FechaEntrega;
	}
	public void setFechaEntrega(String fechaEntrega) {
		FechaEntrega = fechaEntrega;
	}
	public String getVoucher() {
		return Voucher;
	}
	public void setVoucher(String voucher) {
		Voucher = voucher;
	}
	public String getFlag() {
		return Flag;
	}
	public void setFlag(String flag) {
		Flag = flag;
	}
	public int getNroDoc() {
		return NroDoc;
	}
	public void setNroDoc(int nroDoc) {
		NroDoc = nroDoc;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	public double getTotal() {
		return total;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public void setPagos(double pagos) {
		this.pagos = pagos;
	}
	public double getPagos() {
		return pagos;
	}
	public void setEstadoWeb(String estadoWeb) {
		EstadoWeb = estadoWeb;
	}
	public String getEstadoWeb() {
		return EstadoWeb;
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
	public void setFechaViaje(String fechaViaje) {
		FechaViaje = fechaViaje;
	}
	public String getFechaViaje() {
		return FechaViaje;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setOrigenD(String origenD) {
		OrigenD = origenD;
	}
	public String getOrigenD() {
		return OrigenD;
	}
	public void setEticket(String eticket) {
		Eticket = eticket;
	}
	public String getEticket() {
		return Eticket;
	}
}
