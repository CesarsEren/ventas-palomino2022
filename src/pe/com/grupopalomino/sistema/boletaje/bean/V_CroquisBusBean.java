package pe.com.grupopalomino.sistema.boletaje.bean;

import java.io.Serializable;


@SuppressWarnings("serial")
public class V_CroquisBusBean implements Serializable {
private String Bus;
private String Asiento;
private int LLeft;
private int TTop;
private String Visible;

//SOLO PARA INFO DE LA VISTA
private double precio;
private boolean promocion;



public boolean isPromocion() {
	return promocion;
}
public void setPromocion(boolean promocion) {
	this.promocion = promocion;
}
public String getBus() {
	return Bus;
}
public void setBus(String bus) {
	Bus = bus;
}
public String getAsiento() {
	return Asiento;
}
public void setAsiento(String asiento) {
	Asiento = asiento.trim();
}
public int getLLeft() {
	return LLeft;
}
public void setLLeft(int lLeft) {
	LLeft = lLeft;
}
public int getTTop() {
	return TTop;
}
public void setTTop(int tTop) {
	TTop = tTop;
}
public String getVisible() {
	return Visible;
}
public void setVisible(String visible) {
	Visible = visible;
}
public double getPrecio() {
	return precio;
}
public void setPrecio(double precio) {
	this.precio = precio;
}


}
