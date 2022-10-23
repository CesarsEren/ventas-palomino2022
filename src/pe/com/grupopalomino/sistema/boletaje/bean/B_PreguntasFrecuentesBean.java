package pe.com.grupopalomino.sistema.boletaje.bean;

public class B_PreguntasFrecuentesBean {
	
	private Integer Nro;
	private String Fecha;
	private String Nombre;
	private String Email;
	private String Telefono;
	private String Pregunta;
	private String Mensaje;
	
	public void setNro(Integer nro) {
		Nro = nro;
	}
	public Integer getNro() {
		return Nro;
	}
	public void setFecha(String fecha) {
		Fecha = fecha;
	}
	public String getFecha() {
		return Fecha;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getEmail() {
		return Email;
	}
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	public String getTelefono() {
		return Telefono;
	}
	public void setPregunta(String pregunta) {
		Pregunta = pregunta;
	}
	public String getPregunta() {
		return Pregunta;
	}
	public void setMensaje(String mensaje) {
		Mensaje = mensaje;
	}
	public String getMensaje() {
		return Mensaje;
	}
	@Override
	public String toString() {
		return "B_PreguntasFrecuentesBean [Nro=" + Nro + ", Fecha=" + Fecha + ", Nombre=" + Nombre + ", Email=" + Email
				+ ", Telefono=" + Telefono + ", Pregunta=" + Pregunta + ", Mensaje=" + Mensaje + "]";
	}
	
	
	
}
