package pe.com.grupopalomino.sistema.boletaje.bean;

import pe.com.grupopalomino.sistema.boletaje.util.Utils;

public class B_AtencionReclamosBean {
	
	public int Nro;
	public String Empresa;
	public String EmpresaD;
	public String FechaReclamo;
	public String Identidad;
	public String IdentidadD;
	public String DNI;
	public String Nombres;
	public String ApePaterno;
	public String ApeMaterno;
	public String Telefono;
	public String Email;
	public String Domicilio;
	public String PadreMenor;
	public String FechaIncidente;
	public String Servicio;
	public String ServicioD;
	public String MotivoReclamo;
	public String MotivoReclamoD;
	public String Documento;
	public String Serie;
	public String Numero;
	public String TipoReclamo;
	public String Detalle;
	public String Pedido;
	public String Usuario;
	public String Agencia;
	
	
	
	/*****************************************/
	public String Hora;
	public String Mes;
	public String Anio;
	public String FechaCorreo;
	public int Periodo;
	
	/*****************************************/
	public String FechaEmision;
	public String DocumentoD;
	public String DestinoD;
	public String AgenciaD;
	public String TipoReclamoD;
	
	public Integer Id;
	public String AtencionReclamos;
	public String Atendido;
	public String FechaCaducidad;
	public String FechaAtencion;
	public String ReenvioCorreo;
	public String DetalleAtencion;
	public String Responsable;
	
	//*************************
	public Integer Cantidad;
	
	//*************************
	// variable para la decarga del PDF de seguimiento de reclamos
	public String Operacion;
	
 
	
	@Override
	public String toString() {
		return "B_AtencionReclamosBean [Nro=" + Nro + ", Empresa=" + Empresa + ", EmpresaD=" + EmpresaD
				+ ", FechaReclamo=" + FechaReclamo + ", Identidad=" + Identidad + ", IdentidadD=" + IdentidadD
				+ ", DNI=" + DNI + ", Nombres=" + Nombres + ", ApePaterno=" + ApePaterno + ", ApeMaterno=" + ApeMaterno
				+ ", Telefono=" + Telefono + ", Email=" + Email + ", Domicilio=" + Domicilio + ", PadreMenor="
				+ PadreMenor + ", FechaIncidente=" + FechaIncidente + ", Servicio=" + Servicio + ", ServicioD="
				+ ServicioD + ", MotivoReclamo=" + MotivoReclamo + ", MotivoReclamoD=" + MotivoReclamoD + ", Documento="
				+ Documento + ", Serie=" + Serie + ", Numero=" + Numero + ", TipoReclamo=" + TipoReclamo + ", Detalle="
				+ Detalle + ", Pedido=" + Pedido + ", Usuario=" + Usuario + ", Agencia=" + Agencia + ", Hora=" + Hora
				+ ", Mes=" + Mes + ", Anio=" + Anio + ", FechaCorreo=" + FechaCorreo + ", Periodo=" + Periodo
				+ ", FechaEmision=" + FechaEmision + ", DocumentoD=" + DocumentoD + ", DestinoD=" + DestinoD
				+ ", AgenciaD=" + AgenciaD + ", TipoReclamoD=" + TipoReclamoD + ", Id=" + Id + ", AtencionReclamos="
				+ AtencionReclamos + ", Atendido=" + Atendido + ", FechaCaducidad=" + FechaCaducidad
				+ ", FechaAtencion=" + FechaAtencion + ", ReenvioCorreo=" + ReenvioCorreo + ", DetalleAtencion="
				+ DetalleAtencion + ", Responsable=" + Responsable + ", Cantidad=" + Cantidad + ", Operacion="
				+ Operacion + "]";
	}
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
	public void setAtencionReclamos(String atencionReclamos) {
		AtencionReclamos = atencionReclamos;
	}
	public String getAtencionReclamos() {
		return AtencionReclamos;
	}
	public void setAtendido(String atendido) {
		Atendido = atendido;
	}
	public String getAtendido() {
		return Atendido;
	}
	public void setFechaCaducidad(String fechaCaducidad) {
		FechaCaducidad = fechaCaducidad;
	}
	public String getFechaCaducidad() {
		return FechaCaducidad;
	}
	public void setFechaAtencion(String fechaAtencion) {
		FechaAtencion = fechaAtencion;
	}
	public String getFechaAtencion() {
		return FechaAtencion;
	}
	public void setReenvioCorreo(String reenvioCorreo) {
		ReenvioCorreo = reenvioCorreo;
	}
	public String getReenvioCorreo() {
		return ReenvioCorreo;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public Integer getId() {
		return Id;
	}
	
	public void setResponsable(String responsable) {
		Responsable = responsable;
	}
	public String getResponsable() {
		return Responsable;
	}
	public void setDetalleAtencion(String detalleAtencion) {
		DetalleAtencion = detalleAtencion;
	}
	public String getDetalleAtencion() {
		return DetalleAtencion;
	}
	public void setUsuario(String usuario) {
		Usuario = usuario;
	}
	public String getUsuario() {
		return Usuario;
	}
	public void setAgencia(String agencia) {
		Agencia = agencia;
	}
	public String getAgencia() {
		return Agencia;
	}
	public void setCantidad(Integer cantidad) {
		Cantidad = cantidad;
	}
	public Integer getCantidad() {
		return Cantidad;
	}
	
	public void setOperacion(String operacion) {
		Operacion = operacion;
	}
	
	public String getOperacion() {
		return Operacion;
	}
	

	
	public void setValuesReclamos(B_AtencionReclamosBean bean){
		
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
		this.setUsuario(bean.getUsuario());
		this.setAgencia(bean.getAgencia());
		this.setAgenciaD(bean.getAgenciaD());
		
	}
	
	
	
	
}
