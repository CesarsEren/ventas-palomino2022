package pe.com.grupopalomino.sistema.boletaje.bean;

public class V_ServiciosBean {
	
	private String Codigo;
	private String Detalle;
	private String Tipo;
	private String Comida;
	private String BusPlantilla;
	private double Tarifa ;
	
	
	public String getCodigo() {
		return Codigo;
	}
	public void setCodigo(String codigo) {
		Codigo = codigo;
	}
	public String getDetalle() {
		return Detalle;
	}
	public void setDetalle(String detalle) {
		Detalle = detalle;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	public String getComida() {
		return Comida;
	}
	public void setComida(String comida) {
		Comida = comida;
	}
	public String getBusPlantilla() {
		return BusPlantilla;
	}
	public void setBusPlantilla(String busPlantilla) {
		BusPlantilla = busPlantilla;
	}
	public double getTarifa() {
		return Tarifa;
	}
	public void setTarifa(double tarifa) {
		Tarifa = tarifa;
	}

}
