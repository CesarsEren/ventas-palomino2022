package pe.com.grupopalomino.sistema.boletaje.bean;

public class ReporteContabilidad {

	private String FechaEmision;
	private String EmpresaD;
	private int documentos;

	public String getFechaEmision() {
		return FechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		FechaEmision = fechaEmision;
	}

	public String getEmpresaD() {
		return EmpresaD;
	}

	public void setEmpresaD(String empresaD) {
		EmpresaD = empresaD;
	}

	public int getDocumentos() {
		return documentos;
	}

	public void setDocumentos(int documentos) {
		this.documentos = documentos;
	}

	public ReporteContabilidad() {
		// TODO Auto-generated constructor stub
	}

	public ReporteContabilidad(String fechaEmision, String empresaD, int documentos) {
		super();
		FechaEmision = fechaEmision;
		EmpresaD = empresaD;
		this.documentos = documentos;
	}

}
