package pe.com.grupopalomino.sistema.boletaje.formviews;

public class ComboEmbarque {

	private String codigo;
	private String agencia;
	private String hora;
	private int id;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "ComboEmbarque [codigo=" + codigo + ", agencia=" + agencia + ", hora=" + hora + ", id=" + id + "]";
	}
	
}
