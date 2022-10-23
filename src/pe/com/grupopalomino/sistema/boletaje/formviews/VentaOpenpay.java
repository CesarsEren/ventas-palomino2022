package pe.com.grupopalomino.sistema.boletaje.formviews;

import java.io.Serializable;

public class VentaOpenpay implements Serializable {
	
	private String tarjeta;
	private String anio;
	private String mes;
	private String ccv;
	private String nombre;
	private String apellidos;
	private String telefono;
	private String correo;
	private float monto;
	
	public String getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getCcv() {
		return ccv;
	}
	public void setCcv(String ccv) {
		this.ccv = ccv;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}
		// TODO Auto-generated constructor stub
		@Override
		public String toString() {
			return "VentaOpenpayForm [tarjeta=" + tarjeta + ", anio=" + anio + ", mes=" + mes
					+ ", ccv =" + ccv + ", nombre=" + nombre + ", apellidos="
					+ apellidos + ", telefono=" + telefono + ", correo=" + correo
					+ ", monto =" + monto + "]";
		}	
		
}


