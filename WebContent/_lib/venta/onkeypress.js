function RucExistente(event){
	
	var ruc=event.value.trim();
	var razon='';
	
	if(ruc!=''){
		
			$.ajax({
					url : 'ClientesRUC',
				 	data : { RUC : ruc },
				 	type : 'GET',
				 	dataType : 'json',
			success : function(json) {
				 		if(json.respuestaAjaxCliente){
					 		razon =json.beancliente['razon'];
							Habilitar_Componentes_Cliente(true,razon);
					 	}else{
					 		Habilitar_Componentes_Cliente(false,razon);
					 	}
				    },
			error : function(xhr, status) {
			        	alert('Disculpe, existió un problema al realizar la petición al servidor.');
			    },
		});
	}else{
		Habilitar_Componentes_Cliente(false,'');
	}
} 
function RucExistenteTDP(event){
	
	var ruc=event.value.trim();
	var razon='';
	
	if(ruc!=''){
		
			$.ajax({
					url : 'ClientesRUC',
				 	data : { RUC : ruc },
				 	type : 'GET',
				 	dataType : 'json',
			success : function(json) {
				 		if(json.respuestaAjaxCliente){
					 		razon =json.beancliente['razon'];
					 		Habilitar_Componentes_ClienteTDP(true,razon);
					 	}else{
					 		Habilitar_Componentes_ClienteTDP(false,razon);
					 	}
				    },
			error : function(xhr, status) {
			        	alert('Disculpe, existió un problema al realizar la petición al servidor.');
			    },
		});
	}else{
		Habilitar_Componentes_Cliente(false,'');
	}
}
/************************************************************************************************************************/
function Habilitar_Componentes_Cliente(enabled,razon){
	$('.razon').prop('disabled',enabled);
	$('.razon').val(razon);
	
}
function Habilitar_Componentes_ClienteTDP(enabled,razon){
	$('#razon').prop('disabled',enabled);
	$('#razon').val(razon);
	
}

/*************************************************************************************************************************/

function DniExistente(event){
	
	var dni=event.value.trim();
	var nombre='';
	var edad ='';
	var identidad = $('.identidad').val();
	console.log($('.identidad').val().split('-')[0]);
	var telefono='';
	
	if(dni!=''){
		
		    $.ajax({
				    url : 'PasajerosDNI',
				 	data : { DNI : dni,Codigo : $('.identidad').val().split('-')[0]},
				 	type : 'GET',
				 	dataType : 'json',
			success : function(json) {
						if(json.respuestaAjaxPasajero){
							nombre =json.beanpasajero['nombre'];
					 		edad =json.beanpasajero['edad'];
					 		telefono=json.beanpasajero['telefono'];
					 		Habilitar_Componentes_Pasajero(true,nombre,edad,telefono);
					   }else{
					 		Habilitar_Componentes_Pasajero(false,'','','');
					 	  }
				     },
			 error : function(xhr, status) {
			        	alert('Disculpe, existió un problema con la peticion al Servidor ');
			    },
			});
	}else{
		
		Habilitar_Componentes_Pasajero(false,$('.nombreCompleto').val(),$('.edad').val(),$('.telefono').val());
	}
}
/************************************************************************************************************************/

function Habilitar_Componentes_Pasajero(enabled,nombre,edad,telefono){
	$('.nombreCompleto').val(nombre);
	$('.edad').val(edad);
	$('.telefono').val(telefono);
	$('.nombreCompleto').prop('disabled',enabled)
	$('.edad').prop('disabled',enabled);
	$('.telefono').prop('disabled',enabled);
}

/************************************************************************************************************************/
