package pe.com.grupopalomino.sistema.boletaje.bean;

import java.util.Date;

public class V_Fallas {
	 
	private int id;
	private String nrobus;
	private int idruta;

	private String asunto;
	private int idsubcategoria;

	private String detalle;
	private String detalleenproceso;
	private String detallesolucionado;
	private Date fechacreacion;
	private Date fechaenproceso;
	private Date fecharesolucion;
	private String foto;
	private String estado;
	private String registro;
	
	// para inner join
	private String rutaD;
	private String categoriaD;
	private String SubcategoriaD;
	// end
	

	private String resolvio;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNrobus() {
		return nrobus;
	}


	public void setNrobus(String nrobus) {
		this.nrobus = nrobus;
	}


	public int getIdruta() {
		return idruta;
	}


	public void setIdruta(int idruta) {
		this.idruta = idruta;
	}


	public String getAsunto() {
		return asunto;
	}


	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}


	public int getIdsubcategoria() {
		return idsubcategoria;
	}


	public void setIdsubcategoria(int idsubcategoria) {
		this.idsubcategoria = idsubcategoria;
	}


	public String getDetalle() {
		return detalle;
	}


	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}


	public String getDetalleenproceso() {
		return detalleenproceso;
	}


	public void setDetalleenproceso(String detalleenproceso) {
		this.detalleenproceso = detalleenproceso;
	}


	public String getDetallesolucionado() {
		return detallesolucionado;
	}


	public void setDetallesolucionado(String detallesolucionado) {
		this.detallesolucionado = detallesolucionado;
	}


	public Date getFechacreacion() {
		return fechacreacion;
	}


	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}


	public Date getFechaenproceso() {
		return fechaenproceso;
	}


	public void setFechaenproceso(Date fechaenproceso) {
		this.fechaenproceso = fechaenproceso;
	}


	public Date getFecharesolucion() {
		return fecharesolucion;
	}


	public void setFecharesolucion(Date fecharesolucion) {
		this.fecharesolucion = fecharesolucion;
	}


	public String getFoto() {
		return foto;
	}


	public void setFoto(String foto) {
		this.foto = foto;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getRegistro() {
		return registro;
	}


	public void setRegistro(String registro) {
		this.registro = registro;
	}


	public String getRutaD() {
		return rutaD;
	}


	public void setRutaD(String rutaD) {
		this.rutaD = rutaD;
	}


	public String getCategoriaD() {
		return categoriaD;
	}


	public void setCategoriaD(String categoriaD) {
		this.categoriaD = categoriaD;
	}


	public String getSubcategoriaD() {
		return SubcategoriaD;
	}


	public void setSubcategoriaD(String subcategoriaD) {
		SubcategoriaD = subcategoriaD;
	}


	public String getResolvio() {
		return resolvio;
	}


	public void setResolvio(String resolvio) {
		this.resolvio = resolvio;
	}
	
	
	
}
