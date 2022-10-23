package pe.com.grupopalomino.sistema.boletaje.bean;

public class V_CiudadesBean {

	private String codigo;
	private String detalle;
	private String sistema;
	private String codLiq;
	private String depositoAgencia;
	private boolean TDP;

	public boolean isTDP() {
		return TDP;
	}

	public void setTDP(boolean tDP) {
		TDP = tDP;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public String getSistema() {
		return sistema;
	}

	public void setCodLiq(String codLiq) {
		this.codLiq = codLiq;
	}

	public String getCodLiq() {
		return codLiq;
	}

	public void setDepositoAgencia(String depositoAgencia) {
		this.depositoAgencia = depositoAgencia;
	}

	public String getDepositoAgencia() {
		return depositoAgencia;
	}

	@Override
	public String toString() {
		return "V_CiudadesBean [codigo=" + codigo + ", detalle=" + detalle + ", sistema=" + sistema + ", codLiq="
				+ codLiq + ", depositoAgencia=" + depositoAgencia + "]";
	}

}
