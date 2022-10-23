package pe.com.grupopalomino.sistema.boletaje.bean;

import java.io.File;
import java.io.Serializable;

@SuppressWarnings("serial")
public class B_ImagenesBus implements Serializable {
	
	private String servicio;
	private String servicioD;
	private File imagen;
	private byte [] bytes_image;
	private String nombrearchivo;
	private String tipoarchivo;
	private boolean estado;

	
	public void setImagen(File imagen) {
		this.imagen = imagen;
	}
	public File getImagen() {
		return imagen;
	}
	public void setBytes_image(byte[] bytes_image) {
		this.bytes_image = bytes_image;
	}
	public byte[] getBytes_image() {
		return bytes_image;
	}
	public void setNombrearchivo(String nombrearchivo) {
		this.nombrearchivo = nombrearchivo;
	}
	public String getNombrearchivo() {
		return nombrearchivo;
	
	} public void setTipoarchivo(String tipoarchivo) {
		this.tipoarchivo = tipoarchivo;
	}
	public String getTipoarchivo() {
		return tipoarchivo;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public String getServicio() {
		return servicio;
	}
	public void setServicioD(String servicioD) {
		this.servicioD = servicioD;
	}
	public String getServicioD() {
		return servicioD;
	}
	

}
