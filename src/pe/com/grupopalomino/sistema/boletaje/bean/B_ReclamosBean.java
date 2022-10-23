package pe.com.grupopalomino.sistema.boletaje.bean;

import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class B_ReclamosBean {
	
	private int Nro;
	private String Empresa;
	private String EmpresaD;
	private String FechaReclamo;
	private String Identidad;
	private String IdentidadD;
	private String DNI;
	private String Nombres;
	private String ApePaterno;
	private String ApeMaterno;
	private String Telefono;
	private String Email;
	private String Domicilio;
	private String PadreMenor;
	private String FechaIncidente;
	private String Servicio;
	private String ServicioD;
	private String MotivoReclamo;
	private String MotivoReclamoD;
	private String Documento;
	private String Serie;
	private String Numero;
	private String TipoReclamo;
	private String Detalle;
	private String Pedido;
	
	
	/*****************************************/
	private String Hora;
	private String Mes;
	private String Anio;
	private String FechaCorreo;
	private int Periodo;
	
	/*****************************************/
	private String FechaEmision;
	private String DocumentoD;
	private String DestinoD;
	private String AgenciaD;
	private String TipoReclamoD;
	
	public int getNro() {
		return Nro;
	}
	public void setNro(int nro) {
		Nro = nro;
	}
	public String getEmpresa() {
		return Empresa;
	}
	public void setEmpresa(String empresa) {
		Empresa = empresa;
	}
	public void setEmpresaD(String empresaD) {
		EmpresaD = empresaD;
	}
	public String getEmpresaD() {
		return EmpresaD;
	}
	public String getFechaReclamo() {
		return FechaReclamo;
	}
	public void setFechaReclamo(String fechaReclamo) {
		FechaReclamo = fechaReclamo;
	}
	public String getIdentidad() {
		return Identidad;
	}
	public void setIdentidad(String identidad) {
		Identidad = identidad;
	}
	public String getIdentidadD() {
		return IdentidadD;
	}
	public void setIdentidadD(String identidadD) {
		IdentidadD = identidadD;
	}
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public String getNombres() {
		return Nombres;
	}
	public void setNombres(String nombres) {
		Nombres = nombres;
	}
	public String getApePaterno() {
		return ApePaterno;
	}
	public void setApePaterno(String apePaterno) {
		ApePaterno = apePaterno;
	}
	public String getApeMaterno() {
		return ApeMaterno;
	}
	public void setApeMaterno(String apeMaterno) {
		ApeMaterno = apeMaterno;
	}
	public String getTelefono() {
		return Telefono;
	}
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getDomicilio() {
		return Domicilio;
	}
	public void setDomicilio(String domicilio) {
		Domicilio = domicilio;
	}
	public String getPadreMenor() {
		return PadreMenor;
	}
	public void setPadreMenor(String padreMenor) {
		PadreMenor = padreMenor;
	}
	public String getFechaIncidente() {
		return FechaIncidente;
	}
	public void setFechaIncidente(String fechaIncidente) {
		FechaIncidente = fechaIncidente;
	}
	public String getServicio() {
		return Servicio;
	}
	public void setServicio(String servicio) {
		Servicio = servicio;
	}
	public void setServicioD(String servicioD) {
		ServicioD = servicioD;
	}
	public String getServicioD() {
		return ServicioD;
	}
	public void setMotivoReclamo(String motivoReclamo) {
		MotivoReclamo = motivoReclamo;
	}
	public String getMotivoReclamo() {
		return MotivoReclamo;
	}
	public void setMotivoReclamoD(String motivoReclamoD) {
		MotivoReclamoD = motivoReclamoD;
	}
	public String getMotivoReclamoD() {
		return MotivoReclamoD;
	}
	public String getDocumento() {
		return Documento;
	}
	public void setDocumento(String documento) {
		Documento = documento;
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
	public String getTipoReclamo() {
		return TipoReclamo;
	}
	public void setTipoReclamo(String tipoReclamo) {
		TipoReclamo = tipoReclamo;
	}
	public String getDetalle() {
		return Detalle;
	}
	public void setDetalle(String detalle) {
		Detalle = detalle;
	}
	public String getPedido() {
		return Pedido;
	}
	public void setPedido(String pedido) {
		Pedido = pedido;
	}
	public void setHora(String hora) {
		Hora = hora;
	}
	public String getHora() {
		return Hora;
	}
	public void setMes(String mes) {
		Mes = mes;
	}
	public String getMes() {
		return Mes;
	}
	public void setAnio(String anio) {
		Anio = anio;
	}
	public String getAnio() {
		return Anio;
	}
	public void setFechaCorreo(String fechaCorreo) {
		FechaCorreo = fechaCorreo;
	}
	public String getFechaCorreo() {
		return FechaCorreo;
	}
	
	public void setPeriodo(int periodo) {
		Periodo = periodo;
	}
	public int getPeriodo() {
		return Periodo;
	}
	
	public void setFechaEmision(String fechaEmision) {
		FechaEmision = fechaEmision;
	}
	public String getFechaEmision() {
		return FechaEmision;
	}
	public void setDocumentoD(String documentoD) {
		DocumentoD = documentoD;
	}
	public String getDocumentoD() {
		return DocumentoD;
	}
	
	public void setDestinoD(String destinoD) {
		DestinoD = destinoD;
	}
	
	public String getDestinoD() {
		return DestinoD;
	}
	
	public void setAgenciaD(String agenciaD) {
		AgenciaD = agenciaD;
	}
	
	public String getAgenciaD() {
		return AgenciaD;
	}
	
	public void setTipoReclamoD(String tipoReclamoD) {
		TipoReclamoD = tipoReclamoD;
	}
	public String getTipoReclamoD() {
		return TipoReclamoD;
	}
	
	public void setValuesReclamos(B_ReclamosBean bean){
		
		this.setIdentidad(bean.getIdentidad());
		if(bean.getIdentidad().trim().equals("D")){
			this.setIdentidadD("D.N.I.");
		}else if (bean.getIdentidad().trim().equals("M")){
			this.setIdentidadD("Menor Edad");
		}else if (bean.getIdentidad().trim().equals("P")){
			this.setIdentidadD("Pasaporte");
		}
		this.setEmpresa(bean.getEmpresa());
		this.setDNI(bean.getDNI());
		this.setNombres(bean.getNombres().toUpperCase());
		this.setApePaterno(bean.getApePaterno().toUpperCase());
		this.setApeMaterno(bean.getApeMaterno().toUpperCase());
		this.setTelefono(bean.getTelefono());
		this.setEmail(bean.getEmail());
		this.setDomicilio(bean.getDomicilio().toUpperCase());
		this.setPadreMenor((bean.getPadreMenor()!= null ? bean.getPadreMenor().toUpperCase() : "")); 
		this.setFechaIncidente(Utils.FormatoFecha(bean.getFechaIncidente()));
		this.setServicio(bean.getServicio());
		this.setMotivoReclamo(bean.getMotivoReclamo().toUpperCase());
		this.setDocumento(bean.getDocumento());
		this.setSerie(bean.getSerie());
		this.setNumero(bean.getNumero());
		this.setTipoReclamo(bean.getTipoReclamo().toUpperCase());
		this.setDetalle(bean.getDetalle().toUpperCase());
		this.setPedido(bean.getPedido().toUpperCase());
		
		
		
		
		
	}
	
	
	
	
}
