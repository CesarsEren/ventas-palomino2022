package pe.com.grupopalomino.sistema.boletaje.bean;

public class B_ReclamosCorrelativoBean {
	
	private int Periodo;
	private int CorrelativoRec;
	private int CorrelativoAteRec;
	   
	
	public void setPeriodo(int periodo) {
		Periodo = periodo;
	}
	public int getPeriodo() {
		return Periodo;
	}
	
	public void setCorrelativoRec(int correlativoRec) {
		CorrelativoRec = correlativoRec;
	}
	public int getCorrelativoRec() {
		return CorrelativoRec;
	}
	public void setCorrelativoAteRec(int correlativoAteRec) {
		CorrelativoAteRec = correlativoAteRec;
	}
	public int getCorrelativoAteRec() {
		return CorrelativoAteRec;
	}

}
