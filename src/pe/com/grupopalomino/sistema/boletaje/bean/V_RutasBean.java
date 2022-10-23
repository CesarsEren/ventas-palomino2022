package pe.com.grupopalomino.sistema.boletaje.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class V_RutasBean implements Serializable {
	
	
	private int NroRuta,Nro;
	private double Precio;
	private String NroDetalle,Ciudad,CiudadD;
	
	
	public int getNroRuta() {
		return NroRuta;
	}
	public void setNroRuta(int nroRuta) {
		NroRuta = nroRuta;
	}
	public int getNro() {
		return Nro;
	}
	public void setNro(int nro) {
		Nro = nro;
	}
	public double getPrecio() {
		return Precio;
	}
	public void setPrecio(double precio) {
		Precio = precio;
	}
	public String getNroDetalle() {
		return NroDetalle;
	}
	public void setNroDetalle(String nroDetalle) {
		NroDetalle = nroDetalle;
	}
	public String getCiudad() {
		return Ciudad;
	}
	public void setCiudad(String ciudad) {
		Ciudad = ciudad;
	}
	public String getCiudadD() {
		return CiudadD;
	}
	public void setCiudadD(String ciudadD) {
		CiudadD = ciudadD;
	}
	
	public String getNroConcatCiudad(){
		return (getNro() + "," + getCiudadD()).trim();
	}
	
	

}
