package pe.com.grupopalomino.sistema.boletaje.service;

public interface FuncionesGeneralesService {
	
	public String FechaServidor() throws Exception; 
	public String ValidaRangoFecha(String fechaInicial,String fechaFinal) throws Exception;

}
