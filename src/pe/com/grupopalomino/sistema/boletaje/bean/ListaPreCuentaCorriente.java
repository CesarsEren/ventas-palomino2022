package pe.com.grupopalomino.sistema.boletaje.bean;

import java.io.Serializable;


@SuppressWarnings("serial")
public class ListaPreCuentaCorriente implements Serializable{
	
	private int NroBol;
	private int Nro;
	private int NroDoc;
	
	public void setNro(int nro) {
		Nro = nro;
	}
	public int getNro() {
		return Nro;
	}
	public void setNroBol(int nroBol) {
		NroBol = nroBol;
	}
	public int getNroBol() {
		return NroBol;
	}
	public void setNroDoc(int nroDoc) {
		NroDoc = nroDoc;
	}
	public int getNroDoc() {
		return NroDoc;
	}
	
	public void SetValuesPreCuentaCorriente(int NroBol,int Nro,int NroDoc){
		this.setNroBol(NroBol);
		this.setNro(Nro);
		this.setNroDoc(NroDoc);
	}

}
