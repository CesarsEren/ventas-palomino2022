package pe.com.grupopalomino.sistema.boletaje.bean;

public class V_PasajerosBean {
	
	private String Codigo,DNI,Nombre,Edad,Telefono;
	private double Kilometraje,KilometrajeAct;
	
	
	public String getCodigo() {
		return Codigo;
	}
	public void setCodigo(String codigo) {
		Codigo = codigo;
	}
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getEdad() {
		return Edad;
	}
	public void setEdad(String edad) {
		Edad = edad;
	}
	public String getTelefono() {
		return Telefono;
	}
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	public double getKilometraje() {
		return Kilometraje;
	}
	public void setKilometraje(double kilometraje) {
		Kilometraje = kilometraje;
	}
	public double getKilometrajeAct() {
		return KilometrajeAct;
	}
	public void setKilometrajeAct(double kilometrajeAct) {
		KilometrajeAct = kilometrajeAct;
	}
	
	
	

}
