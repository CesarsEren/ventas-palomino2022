package pe.com.grupopalomino.sistema.boletaje.util;

import pe.com.grupopalomino.sistema.boletaje.formviews.VentaPaso2Form;

public class ValidaDatosPaso2Form {

	private VentaPaso2Form ventaPaso2Ida;
	private VentaPaso2Form ventaPaso2IdaVuelta;
	
	
	public ValidaDatosPaso2Form(VentaPaso2Form ventaPaso2Ida) {
		this.ventaPaso2Ida = ventaPaso2Ida;
	}
	
	public ValidaDatosPaso2Form(VentaPaso2Form ventaPaso2Ida, VentaPaso2Form ventaPaso2Vuelta){
		this.ventaPaso2Ida = ventaPaso2Ida;
		this.ventaPaso2IdaVuelta = ventaPaso2Vuelta;
	}
	
	public ErrorValidacion validaPaso2FormIda(){
		
		ErrorValidacion error = new ErrorValidacion();
		
			if(ventaPaso2Ida.getNroProgramacion() == null){
				error.addMensaje("Seleccione una programacion de Salida.");
				error.setError(true);
			
		}
		
		return error;
	}
	
	public ErrorValidacion validaPaso2FormIdaVuelta(){
		
		ErrorValidacion error = new ErrorValidacion();
		
		if(ventaPaso2Ida.getNroProgramacion() == null){
				error.addMensaje("Seleccione una programacion de Salida de Ida.");
				error.setError(true);
			
		}
		if(ventaPaso2IdaVuelta.getNroProgramacion() == null){
				error.addMensaje("Seleccione una programacion de Salida de Regreso.");
				error.setError(true);
		}
		
		return error;
	}
	
}
