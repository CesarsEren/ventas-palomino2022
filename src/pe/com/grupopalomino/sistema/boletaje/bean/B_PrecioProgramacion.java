package pe.com.grupopalomino.sistema.boletaje.bean;

public class B_PrecioProgramacion {
	
	Integer Nro;
	Integer Asiento;
	Double Precio;
	Integer B_Identity;

	public B_PrecioProgramacion() {
	};

	public B_PrecioProgramacion(Integer nro, Integer asiento, Double precio, Integer b_Identity) {
		super();
		Nro = nro;
		Asiento = asiento;
		Precio = precio;
		B_Identity = b_Identity;
	}

	public Integer getNro() {
		return Nro;
	}

	public void setNro(Integer nro) {
		Nro = nro;
	}

	public Integer getAsiento() {
		return Asiento;
	}

	public void setAsiento(Integer asiento) {
		Asiento = asiento;
	}

	public Double getPrecio() {
		return Precio;
	}

	public void setPrecio(Double precio) {
		Precio = precio;
	}

	public Integer getB_Identity() {
		return B_Identity;
	}

	public void setB_Identity(Integer b_Identity) {
		B_Identity = b_Identity;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
