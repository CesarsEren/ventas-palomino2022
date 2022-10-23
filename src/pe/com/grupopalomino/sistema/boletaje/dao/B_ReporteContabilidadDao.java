package pe.com.grupopalomino.sistema.boletaje.dao;

import java.util.List;

import pe.com.grupopalomino.sistema.boletaje.bean.ReporteContabilidad;

public interface B_ReporteContabilidadDao {

	public List<ReporteContabilidad> GetDocumentosPorEnviar();
	public List<ReporteContabilidad> GetDocumentosRechazados();
	public List<String> GetDocumentosSinPDF417();
}
