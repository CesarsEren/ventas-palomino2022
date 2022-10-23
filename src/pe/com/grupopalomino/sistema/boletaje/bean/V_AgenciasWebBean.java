package pe.com.grupopalomino.sistema.boletaje.bean;

public class V_AgenciasWebBean {
	
	private int id;
	private String codigo;
	private String direccion;
	private String razonSocial;
	private String ruc;
	private String correo;
	private String telefono;
	private String telefono2;
	private String documentoIdentidad;
	private String estado;
	private String personaContacto;
	private double limiteCredito;
	private String Detalle;
	private double montoVentaActual;
	private double montoVentaConfirmada;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getPersonaContacto() {
		return personaContacto;
	}
	public void setPersonaContacto(String personaContacto) {
		this.personaContacto = personaContacto;
	}
	public void setLimiteCredito(double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}
	public double getLimiteCredito() {
		return limiteCredito;
	}
	public String getDetalle() {
		return Detalle;
	}
	public void setDetalle(String detalle) {
		Detalle = detalle;
	}
	public void setMontoVentaActual(double montoVentaActual) {
		this.montoVentaActual = montoVentaActual;
	}
	public double getMontoVentaActual() {
		return montoVentaActual;
	}
	
	public void setMontoVentaConfirmada(double montoVentaConfirmada) {
		this.montoVentaConfirmada = montoVentaConfirmada;
	}
	public double getMontoVentaConfirmada() {
		return montoVentaConfirmada;
	}
	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}
	public String getTelefono2() {
		return telefono2;
	}
	public void setDocumentoIdentidad(String documentoIdentidad) {
		this.documentoIdentidad = documentoIdentidad;
	}
	public String getDocumentoIdentidad() {
		return documentoIdentidad;
	}
	
	public void setValuesAgencias(V_AgenciasWebBean bean){
		this.setDireccion(bean.getDireccion().toUpperCase());
		this.setPersonaContacto(bean.getPersonaContacto().toUpperCase());
		this.setRazonSocial(bean.getRazonSocial().toUpperCase());
	}
	
	@Override
	public String toString() {
		return "V_AgenciasWebBean [id=" + id + ", codigo=" + codigo + ", direccion=" + direccion + ", razonSocial="
				+ razonSocial + ", ruc=" + ruc + ", correo=" + correo + ", telefono=" + telefono + ", estado=" + estado
				+ ", personaContacto=" + personaContacto + ", limiteCredito=" + limiteCredito + ", Detalle=" + Detalle
				+ ", montoVentaActual=" + montoVentaActual + ", montoVentaConfirmada=" + montoVentaConfirmada + "]";
	}
	
	
}
