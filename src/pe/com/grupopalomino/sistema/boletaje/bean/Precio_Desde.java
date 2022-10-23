package pe.com.grupopalomino.sistema.boletaje.bean;

import java.io.Serializable;

@SuppressWarnings("serial")

public class Precio_Desde implements Serializable {
	
	
	private int Nro;	
	private int Asiento;		
	private double Precio;
	
	
	
	public int getNro() {
		return Nro;
	}

	public void setNro(int nro) {
		Nro = nro;
	}

	public int getAsiento() {
		return Asiento;
	}

	public void setAsiento(int asiento) {
		Asiento = asiento;
	}

	public double getPrecio() {
		return Precio;
	}

	public void setPrecio(double precio) {
		Precio = precio;
	}

}
