package pe.com.grupopalomino.sistema.boletaje.formviews;

import java.io.Serializable;


@SuppressWarnings("serial")
public class VentaPaso4Form implements Serializable{

	private String id ;
	private String numeroAsiento;
	private String precio;
	private String nombrePasajero;
	private String edad;
	private String telefono;
	private String ruc;
	private String razonSocial;
	private String numeroDocumentoIdentidad;
	private String comboEmbarque;
	private String comboIdentidad;
	private String comboDestinoBajada;
	private boolean asientoasignado;
	private double precioMaximo;
	private double montoVentaActual;
	
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	
	public String getNumeroAsiento() {
		return numeroAsiento;
	}
	public void setNumeroAsiento(String numeroAsiento) {
		this.numeroAsiento = numeroAsiento;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	public String getNombrePasajero() {
		return nombrePasajero;
	}
	public void setNombrePasajero(String nombrePasajero) {
		this.nombrePasajero = nombrePasajero;
	}
	public String getEdad() {
		return edad;
	}
	public void setEdad(String edad) {
		this.edad = edad;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getComboEmbarque() {
		return comboEmbarque;
	}
	public void setComboEmbarque(String comboEmbarque) {
		this.comboEmbarque = comboEmbarque;
	}
	public String getComboIdentidad() {
		return comboIdentidad;
	}
	public void setComboIdentidad(String comboIdentidad) {
		this.comboIdentidad = comboIdentidad;
	}
	public String getComboDestinoBajada() {
		return comboDestinoBajada;
	}
	public void setComboDestinoBajada(String comboDestinoBajada) {
		this.comboDestinoBajada = comboDestinoBajada;
	}
	public void setNumeroDocumentoIdentidad(String numeroDocumentoIdentidad) {
		this.numeroDocumentoIdentidad = numeroDocumentoIdentidad;
	}
	public String getNumeroDocumentoIdentidad() {
		return numeroDocumentoIdentidad;
	}
	public void setAsientoasignado(boolean asientoasignado) {
		this.asientoasignado = asientoasignado;
	}
	public boolean isAsientoasignado() {
		return asientoasignado;
	}
	public void setIdFromView(VentaPaso4Form ventaPaso3Form){
		this.setId(ventaPaso3Form.getNumeroDocumentoIdentidad().trim()+ventaPaso3Form.getEdad().trim());
		ventaPaso3Form.setId(this.getId().trim());
	}
	public void setPrecioMaximo(double precioMaximo) {
		this.precioMaximo = precioMaximo;
	}
	public double getPrecioMaximo() {
		return precioMaximo;
	}
	public void setMontoVentaActual(double montoVentaActual) {
		this.montoVentaActual = montoVentaActual;
	}
	public double getMontoVentaActual() {
		return montoVentaActual;
	}
	
	public void setValuesFromView(VentaPaso4Form ventaPaso4Form){
		this.setNumeroAsiento(ventaPaso4Form.getNumeroAsiento());
		this.setPrecio(ventaPaso4Form.getPrecio()); 
		this.setNumeroDocumentoIdentidad(ventaPaso4Form.getNumeroDocumentoIdentidad());
		this.setNumeroDocumentoIdentidad(ventaPaso4Form.getNumeroDocumentoIdentidad());
		this.setNombrePasajero(ventaPaso4Form.getNombrePasajero());
		this.setComboEmbarque(ventaPaso4Form.getComboEmbarque());
		this.setComboIdentidad(ventaPaso4Form.getComboIdentidad());
		this.setComboDestinoBajada(ventaPaso4Form.getComboDestinoBajada());
		this.setEdad(ventaPaso4Form.getEdad());
		this.setTelefono(ventaPaso4Form.getTelefono()); 
		this.setRuc(ventaPaso4Form.getRuc());
		this.setRazonSocial(ventaPaso4Form.getRazonSocial());
		this.setMontoVentaActual(ventaPaso4Form.getMontoVentaActual()); 
	}
	@Override
	public String toString() {
		return "VentaPaso4Form [id=" + id + ", numeroAsiento=" + numeroAsiento + ", precio=" + precio
				+ ", nombrePasajero=" + nombrePasajero + ", edad=" + edad + ", telefono=" + telefono + ", ruc=" + ruc
				+ ", razonSocial=" + razonSocial + ", numeroDocumentoIdentidad=" + numeroDocumentoIdentidad
				+ ", comboEmbarque=" + comboEmbarque + ", comboIdentidad=" + comboIdentidad + ", comboDestinoBajada="
				+ comboDestinoBajada + ", asientoasignado=" + asientoasignado + ", precioMaximo=" + precioMaximo
				+ ", montoVentaActual=" + montoVentaActual + "]";
	}
	
	
	
	
}
