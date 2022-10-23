package pe.com.grupopalomino.sistema.boletaje.bean;

public class B_BoletosRedBusEliminados {

	int Id;
	String Nro;
	String CodigoMesaAyuda;
	String Motivo;
	String Usuario;
	String Fecha;

	public B_BoletosRedBusEliminados() {
		// TODO Auto-generated constructor stub
	}

	public B_BoletosRedBusEliminados(int id, String nro, String codigoMesaAyuda, String motivo, String usuario,
			String fecha) {
		super();
		Id = id;
		Nro = nro;
		CodigoMesaAyuda = codigoMesaAyuda;
		Motivo = motivo;
		Usuario = usuario;
		Fecha = fecha;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getNro() {
		return Nro;
	}

	public void setNro(String nro) {
		Nro = nro;
	}

	public String getCodigoMesaAyuda() {
		return CodigoMesaAyuda;
	}

	public void setCodigoMesaAyuda(String codigoMesaAyuda) {
		CodigoMesaAyuda = codigoMesaAyuda;
	}

	public String getMotivo() {
		return Motivo;
	}

	public void setMotivo(String motivo) {
		Motivo = motivo;
	}

	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public String getFecha() {
		return Fecha;
	}

	public void setFecha(String fecha) {
		Fecha = fecha;
	}

}
