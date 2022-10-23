package pe.com.grupopalomino.sistema.boletaje.util;


import pe.com.grupopalomino.sistema.boletaje.bean.V_DestinosAgenciasWebBean;
import pe.com.grupopalomino.sistema.boletaje.formviews.VentaPaso1Form;
import pe.com.grupopalomino.sistema.boletaje.service.DestinosAgenciasWebService;
import pe.com.grupopalomino.sistema.boletaje.service.DestinosAgenciasWebServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.ProgramacionSalidaService;
import pe.com.grupopalomino.sistema.boletaje.service.ProgramacionSalidaServiceI;
import pe.com.grupopalomino.sistema.boletaje.service.VariosService;
import pe.com.grupopalomino.sistema.boletaje.service.VariosServiceI;

public class ValidaDatosPaso1Form {
	
	//private DestinosService destinoService = new DestinoServiceI();
	private DestinosAgenciasWebService destinoAgenciasService = new DestinosAgenciasWebServiceI();
	private VariosService variosservice = new VariosServiceI();
	private ProgramacionSalidaService programacionSalidaService = new ProgramacionSalidaServiceI();
	private VentaPaso1Form ventaPaso1;
	
	public ValidaDatosPaso1Form(VentaPaso1Form ventaPaso1) {
		this.ventaPaso1 = ventaPaso1;
	}
	
	public ErrorValidacion validaPaso1Form() throws Exception{
		
		ErrorValidacion error = new ErrorValidacion();
		
		if(ventaPaso1.getOrigenIda() == null || ventaPaso1.getOrigenIda().trim().isEmpty()){
			error.addMensaje("Seleccione un Origen de Viaje");
			error.setError(true);
			
		}
		
		if(ventaPaso1.getFechaIda() == null || ventaPaso1.getFechaIda().trim().isEmpty()){
			error.addMensaje("Seleccione una fecha de ida.");
			error.setError(true);
		}
		
		if(!ventaPaso1.getFechaIda().replace("/", "").matches(Regex.FECHA)){
			error.addMensaje("La fecha de ida no tiene el formato correcto.");
			error.setError(true);
		}
		else{
			if(!Utils.esFechaPosteriorActual(ventaPaso1.getFechaIda())){
				error.addMensaje("La fecha de ida no puede ser anterior a la actual.");
				error.setError(true);
			}
		}
		
		if(ventaPaso1.getDestinoIda() == null || ventaPaso1.getDestinoIda().trim().isEmpty()){
			error.addMensaje("Seleccione un Destino de Viaje");
			error.setError(true);
		}
		
		if(ventaPaso1.isIdaVuelta()){
			if(ventaPaso1.getFechaVuelta() == null || ventaPaso1.getFechaVuelta().trim().isEmpty()){
				error.addMensaje("Seleccione una fecha de regreso");
				error.setError(true);
				return error;
			}
			if(!ventaPaso1.getFechaVuelta().replace("/", "").matches(Regex.FECHA)){
				error.addMensaje("La fecha de regreso no tiene el formato correcto");
				error.setError(true);
				return error;
			}
			else{
				if(!Utils.esFechaIdaMenorFechaVuelta(ventaPaso1.getFechaIda(), ventaPaso1.getFechaVuelta())){
					error.addMensaje("La fecha de ida debe ser menor o igual a la fecha de vuelta.");
					error.setError(true);
					
					return error;
				}
			}
		}
		
		if(!ventaPaso1.getRolUser().trim().equals("C")){
			if(ventaPaso1.getCantidadMaximaPasajeros() == null || ventaPaso1.getCantidadMaximaPasajeros().trim().isEmpty()){
				error.addMensaje("Debe seleccionar una cantidad de pasajeros.");
				error.setError(true);
				return error;
			}
			if(!ventaPaso1.getCantidadMaximaPasajeros().trim().matches(Regex.NUMEROS)){
				error.addMensaje("Cantidad de pasajeros no permitida.");
				error.setError(true);
				return error;
			}
			
			int cantidadMaximaPasajeros = variosservice.Get_Cantidad_Maximo_Pasajeros().getCantidadMaximaPasajeros();
			if(ventaPaso1.getCantidadMaximaPasajeros().trim().matches(Regex.NUMEROS)){
				if(Integer.parseInt(ventaPaso1.getCantidadMaximaPasajeros())<= 0){
					error.addMensaje("Cantidad de pasajeros no permitida.");
					error.setError(true);
				}
				if(Integer.parseInt(ventaPaso1.getCantidadMaximaPasajeros())>cantidadMaximaPasajeros){
					error.addMensaje("Cantidad de pasajeros no permitida.");
					error.setError(true);
				}
			}
		}
		
		if(!(error.getError())){
			
			/**************************************  VALIDACIONES DE LA VENTA DE IDA    ******************************/
			
				//V_DestinosBean destinoBeanIDA = destinoService.getDestinoXIdaYVuelta(ventaPaso1.getOrigenIda(), ventaPaso1.getDestinoIda());
				V_DestinosAgenciasWebBean destinoBeanIDA  = destinoAgenciasService.obtieneDestinosAgencias(ventaPaso1.getOrigenIda().substring(0, 3), ventaPaso1.getDestinoIda());
				if(destinoBeanIDA != null){
					//ventaPaso1.setNroDestinoIDA(destinoBeanIDA.getNro());
					
					if(programacionSalidaService.getSalidas(Utils.FormatoFecha(ventaPaso1.getFechaIda()),(ventaPaso1.getRolUser()== null? "X":ventaPaso1.getRolUser()),ventaPaso1.getOrigenIda(),ventaPaso1.getDestinoIda(),0).size() == 0){
						error.addMensaje("No existe Programaciones de Destino en la fecha indicada.");
						error.setError(true);
					}
				}
				else{
					error.addMensaje("No existen viajes disponibles para el destino seleccionado.");
					error.setError(true);
					
					return error;
				}
			
			/**************************************  VALIDACIONES DE LA VENTA DE IDA Y VUELTA    ******************************/
			if(ventaPaso1.isIdaVuelta()){
				//V_DestinosBean destinoVueltaBean = null;
				//destinoVueltaBean = destinoService.getDestinoXIdaYVuelta(ventaPaso1.getDestinoIda(), ventaPaso1.getOrigenIda());
				
				V_DestinosAgenciasWebBean destinoVueltaBean = null;
				
				destinoVueltaBean = destinoAgenciasService.obtieneDestinosAgencias(ventaPaso1.getDestinoIda(), ventaPaso1.getOrigenIda().substring(0, 3));
				
				if(destinoVueltaBean == null){
					error.addMensaje("El destino seleccionado no tiene un viaje de regreso.");
					error.setError(true);
				}
				
				if(!error.getError()){
					//V_DestinosBean destinoBeanIDA = destinoService.getDestinoXIdaYVuelta(ventaPaso1.getOrigenIda(), ventaPaso1.getDestinoIda());
					//ventaPaso1.setNroDestinoIDA(destinoBeanIDA.getNro());
					//ventaPaso1.setNroDestinoVUELTA(destinoVueltaBean.getNro());
					
					if(programacionSalidaService.getSalidas(Utils.FormatoFecha(ventaPaso1.getFechaVuelta()),(ventaPaso1.getRolUser()== null? "X":ventaPaso1.getRolUser()),ventaPaso1.getDestinoIda(),ventaPaso1.getOrigenIda().substring(0,3),0).size() == 0){
						error.addMensaje("No existe Programaciones de Destino en la fecha de regreso.");
						error.setError(true);
					}
					
				}else{
					
					return error;
					
				}
				
				
				//else{
					//ventaPaso1.setNroDestinoVUELTA(destinoVueltaBean.getNro());
				//}
			}
			
		}

		
		
		return error;
		
	}
	
}
