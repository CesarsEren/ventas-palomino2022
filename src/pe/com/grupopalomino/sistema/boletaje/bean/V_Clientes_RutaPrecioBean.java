package pe.com.grupopalomino.sistema.boletaje.bean;

public class V_Clientes_RutaPrecioBean {
	
	private int Id;
	private String Ruc;
	private int NroRuta;
	private String DescripcionRuta;
	private String NroServicio;
	private String DescripcionServicio;
	private double Precio1;
	private double Precio2;
	
	public void setId(int id) {
		Id = id;
	}
	public int getId() {
		return Id;
	}
	
	public void setRuc(String ruc) {
		Ruc = ruc;
	}
	public String getRuc() {
		return Ruc;
	}
	public void setNroRuta(int nroRuta) {
		NroRuta = nroRuta;
	}
	public int getNroRuta() {
		return NroRuta;
	}
	public void setDescripcionRuta(String descripcionRuta) {
		DescripcionRuta = descripcionRuta;
	}
	public String getDescripcionRuta() {
		return DescripcionRuta;
	}
	public void setNroServicio(String nroServicio) {
		NroServicio = nroServicio;
	}
	public String getNroServicio() {
		return NroServicio;
	}
	public void setDescripcionServicio(String descripcionServicio) {
		DescripcionServicio = descripcionServicio;
	}
	public String getDescripcionServicio() {
		return DescripcionServicio;
	}
	public void setPrecio1(double precio1) {
		Precio1 = precio1;
	}
	public double getPrecio1() {
		return Precio1;
	}
	public void setPrecio2(double precio2) {
		Precio2 = precio2;
	}
	public double getPrecio2() {
		return Precio2;
	}
	
}
