package pe.com.grupopalomino.sistema.boletaje.bean;

public class B_Encomiendas_Detalles {

	int Nro;
	int NroEnvio;
	String Empresa;
	String EmpresaD;
	String Serie;
	String Numero;
	int Item;
	String Descripcion;
	double Cantidad;
	double PU;
	double SubTotal;
	int Anulado;
	
	
	
	@Override
	public String toString() {
		return "B_Encomiendas_Detalles [Nro=" + Nro + ", NroEnvio=" + NroEnvio + ", Empresa=" + Empresa + ", EmpresaD="
				+ EmpresaD + ", Serie=" + Serie + ", Numero=" + Numero + ", Item=" + Item + ", Descripcion="
				+ Descripcion + ", Cantidad=" + Cantidad + ", PU=" + PU + ", SubTotal=" + SubTotal + ", Anulado="
				+ Anulado + "]";
	}
	public int getNro() {
		return Nro;
	}
	public void setNro(int nro) {
		Nro = nro;
	}
	public int getNroEnvio() {
		return NroEnvio;
	}
	public void setNroEnvio(int nroEnvio) {
		NroEnvio = nroEnvio;
	}
	public String getEmpresa() {
		return Empresa;
	}
	public void setEmpresa(String empresa) {
		Empresa = empresa;
	}
	public String getEmpresaD() {
		return EmpresaD;
	}
	public void setEmpresaD(String empresaD) {
		EmpresaD = empresaD;
	}
	public String getSerie() {
		return Serie;
	}
	public void setSerie(String serie) {
		Serie = serie;
	}
	public String getNumero() {
		return Numero;
	}
	public void setNumero(String numero) {
		Numero = numero;
	}
	public int getItem() {
		return Item;
	}
	public void setItem(int item) {
		Item = item;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public double getCantidad() {
		return Cantidad;
	}
	public void setCantidad(double cantidad) {
		Cantidad = cantidad;
	}
	public double getPU() {
		return PU;
	}
	public void setPU(double pU) {
		PU = pU;
	}
	public double getSubTotal() {
		return SubTotal;
	}
	public void setSubTotal(double subTotal) {
		SubTotal = subTotal;
	}
	public int getAnulado() {
		return Anulado;
	}
	public void setAnulado(int anulado) {
		Anulado = anulado;
	}
	
	
}
