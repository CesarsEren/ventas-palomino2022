package pe.com.grupopalomino.sistema.boletaje.dao;

public interface FuncionesGeneralesDao {
	
	public String FechaServidor() throws Exception;
	public String ValidaRangoFecha(String fechaInicial,String fechaFinal) throws Exception;
	

}
