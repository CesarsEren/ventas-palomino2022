package pe.com.grupopalomino.sistema.boletaje.formviews;

import java.io.Serializable;

@SuppressWarnings("serial")
public class VentaPaso1Form implements Serializable{

	private boolean idaVuelta;
	private String origenIda;
	private String fechaIda;
	private String destinoIda;
	private String fechaVuelta;
	private String origenDescripcion;
	private String destinoDescripcion;
	private String rolUser;
	private String cantidadMaximaPasajeros;
	private int medioPago;
	
	private int nroDestinoIDA;
	private int nroDestinoVUELTA;
	
	public boolean isIdaVuelta() {
		return idaVuelta;
	}
	public void setIdaVuelta(boolean idaVuelta) {
		this.idaVuelta = idaVuelta;
	}
	public String getOrigenIda() {
		return origenIda;
	}
	public void setOrigenIda(String origenIda) {
		this.origenIda = origenIda;
	}
	public String getFechaIda() {
		return fechaIda;
	}
	public void setFechaIda(String fechaIda) {
		this.fechaIda = fechaIda;
	}
	public String getDestinoIda() {
		return destinoIda;
	}
	public void setDestinoIda(String destinoIda) {
		this.destinoIda = destinoIda;
	}
	public String getFechaVuelta() {
		return fechaVuelta;
	}
	public void setFechaVuelta(String fechaVuelta) {
		this.fechaVuelta = fechaVuelta;
	}
	public int getNroDestinoIDA() {
		return nroDestinoIDA;
	}
	
	public void setNroDestinoIDA(int nroDestinoIDA) {
		this.nroDestinoIDA = nroDestinoIDA;
	}
	
	public int getNroDestinoVUELTA() {
		return nroDestinoVUELTA;
	}
	
	public void setNroDestinoVUELTA(int nroDestinoVUELTA) {
		this.nroDestinoVUELTA = nroDestinoVUELTA;
	}
	
	public void setCantidadMaximaPasajeros(String cantidadMaximaPasajeros) {
		this.cantidadMaximaPasajeros = cantidadMaximaPasajeros;
	}
	public String getCantidadMaximaPasajeros() {
		return cantidadMaximaPasajeros;
	}
	public void setOrigenDescripcion(String origenDescripcion) {
		this.origenDescripcion = origenDescripcion;
	}
	public String getOrigenDescripcion() {
		return origenDescripcion;
	}
	public void setDestinoDescripcion(String destinoDescripcion) {
		this.destinoDescripcion = destinoDescripcion;
	}
	public String getDestinoDescripcion() {
		return destinoDescripcion;
	}
	public void setRolUser(String rolUser) {
		this.rolUser = rolUser;
	}
	public String getRolUser() {
		return rolUser;
	}
	public void setMedioPago(int medioPago) {
		this.medioPago = medioPago;
	}
	public int getMedioPago() {
		return medioPago;
	}
	
	
	/**
	 * METODOS USADOS EN CASO SE SELECCIONE IDA Y VUELTA
	 * */
	public String getOrigenVuelta() {
		return getDestinoIda();
	}
	/**
	 * METODOS USADOS EN CASO SE SELECCIONE IDA Y VUELTA
	 * */
	public String getDestinoVuelta() {
		return getOrigenIda();
	}
	
	@Override
	public String toString() {
		return "VentaPaso1Form [idaVuelta=" + idaVuelta + ", origenIda=" + origenIda + ", fechaIda=" + fechaIda
				+ ", destinoIda=" + destinoIda + ", fechaVuelta=" + fechaVuelta + ", origenDescripcion="
				+ origenDescripcion + ", destinoDescripcion=" + destinoDescripcion + ", rolUser=" + rolUser
				+ ", cantidadMaximaPasajeros=" + cantidadMaximaPasajeros + ", medioPago=" + medioPago
				+ ", nroDestinoIDA=" + nroDestinoIDA + ", nroDestinoVUELTA=" + nroDestinoVUELTA + "]";
	}
	

}
