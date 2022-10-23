package pe.com.grupopalomino.sistema.boletaje.formviews;

import java.io.Serializable;


@SuppressWarnings("serial")
public class VentaPaso2Form implements Serializable {

	private Integer nroProgramacion;
	private String nroDestino;
	private String nroBus;
	private String nroServicio;
	private String descripcionServicioIda;
	private String descripcionServicioVuelta;
	private String hora1Ida;
	private String hora1Vuelta;
	private Integer capacidadBus;
	private boolean mostrarComida;
	private Integer cantidadAsientosOcupados;
	private Integer cantidadAsientosReservados;
	
	public Integer getNroProgramacion() {
		return nroProgramacion;
	}
	public void setNroProgramacion(Integer nroProgramacion) {
		this.nroProgramacion = nroProgramacion;
	}
	public String getNroDestino() {
		return nroDestino;
	}
	public void setNroDestino(String nroDestino) {
		this.nroDestino = nroDestino;
	}
	public String getNroBus() {
		return nroBus;
	}
	public void setNroBus(String nroBus) {
		this.nroBus = nroBus;
	}
	public String getNroServicio() {
		return nroServicio;
	}
	public void setNroServicio(String nroServicio) {
		this.nroServicio = nroServicio;
	}
	public void setDescripcionServicioIda(String descripcionServicioIda) {
		this.descripcionServicioIda = descripcionServicioIda;
	}
	public String getDescripcionServicioIda() {
		return descripcionServicioIda;
	}
	public void setDescripcionServicioVuelta(String descripcionServicioVuelta) {
		this.descripcionServicioVuelta = descripcionServicioVuelta;
	}
	public String getDescripcionServicioVuelta() {
		return descripcionServicioVuelta;
	}
	public void setHora1Ida(String hora1Ida) {
		this.hora1Ida = hora1Ida;
	}
	public String getHora1Ida() {
		return hora1Ida;
	}
	public void setHora1Vuelta(String hora1Vuelta) {
		this.hora1Vuelta = hora1Vuelta;
	}
	public String getHora1Vuelta() {
		return hora1Vuelta;
	}
	
	public Integer getCapacidadBus() {
		return capacidadBus;
	}
	public void setCapacidadBus(Integer capacidadBus) {
		this.capacidadBus = capacidadBus;
	}
	
	public boolean isMostrarComida() {
		return mostrarComida;
	}
	public void setMostrarComida(boolean mostrarComida) {
		this.mostrarComida = mostrarComida;
	}
	
	public void setCantidadAsientosOcupados(Integer cantidadAsientosOcupados) {
		this.cantidadAsientosOcupados = cantidadAsientosOcupados;
	}
	public Integer getCantidadAsientosOcupados() {
		return cantidadAsientosOcupados;
	}
	public void setCantidadAsientosReservados(Integer cantidadAsientosReservados) {
		this.cantidadAsientosReservados = cantidadAsientosReservados;
	}
	public Integer getCantidadAsientosReservados() {
		return cantidadAsientosReservados;
	}
	@Override
	public String toString() {
		return "VentaPaso2Form [nroProgramacion=" + nroProgramacion + ", nroDestino=" + nroDestino + ", nroBus="
				+ nroBus + ", nroServicio=" + nroServicio + ", descripcionServicioIda=" + descripcionServicioIda
				+ ", descripcionServicioVuelta=" + descripcionServicioVuelta + ", hora1Ida=" + hora1Ida
				+ ", hora1Vuelta=" + hora1Vuelta + ", capacidadBus=" + capacidadBus + ", mostrarComida=" + mostrarComida
				+ ", cantidadAsientosOcupados=" + cantidadAsientosOcupados + ", cantidadAsientosReservados="
				+ cantidadAsientosReservados + "]";
	}
	
}
