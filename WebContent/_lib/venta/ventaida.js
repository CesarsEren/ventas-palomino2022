$("#btnPagar").on('click', function(){
	if($('#rbTerminosIda').val() == 'false'){
		
		 BootstrapDialog.show({
			  	title :'Mensaje de Advertencia!',
			  	type : BootstrapDialog.TYPE_DANGER,
	            message: 'Por favor para continuar con la compra acepte los Términos y condiciones',
	            closable: false,
	            buttons: [{
	                label: 'OK',
	                cssClass: 'btn btn-danger',
	                action: function(event){
	                    event.close();
	                }
	            }]
	        });
		return false;
		
	}
	
	if($('#hidContador').val()=='0'){
		
		 BootstrapDialog.show({
			  	title :'Mensaje de Advertencia!',
			  	type : BootstrapDialog.TYPE_DANGER,
	            message: 'Debe Ingresar un Pasajero',
	            closable: false,
	            buttons: [{
	                label: 'OK',
	                cssClass: 'btn btn-danger',
	                action: function(event){
	                    event.close();
	                }
	            }]
	        });
		return false;
	}
	
	if($('#correoIda').val()==''){
		
		 BootstrapDialog.show({
			  	title :'Mensaje de Advertencia!',
			  	type : BootstrapDialog.TYPE_DANGER,
	            message: 'Debe Ingresar un Correo Electronico.',
	            closable: false,
	            buttons: [{
	                label: 'OK',
	                cssClass: 'btn btn-danger',
	                action: function(event){
	                    event.close();
	                }
	            }]
	        });
		return false;
	}
	
	$('#CorreoPaso3').val($('#correoIda').val())
	$("#frmAccedePago").submit();
	
});

$('#btnGrabar').on('click',function(){
	
		if($('#hidContador').val()=='0'){
			
			 BootstrapDialog.show({
				  	title :'Mensaje de Advertencia!',
				  	type : BootstrapDialog.TYPE_DANGER,
		            message: 'Debe Ingresar un Pasajero',
		            closable: false,
		            buttons: [{
		                label: 'OK',
		                cssClass: 'btn btn-danger',
		                action: function(event){
		                    event.close();
		                }
		            }]
		        });
			return false;
		}
		
		if($('#correoIda').val()==''){
			
			 BootstrapDialog.show({
				  	title :'Mensaje de Advertencia!',
				  	type : BootstrapDialog.TYPE_DANGER,
		            message: 'Debe Ingresar un Correo Electronico.',
		            closable: false,
		            buttons: [{
		                label: 'OK',
		                cssClass: 'btn btn-danger',
		                action: function(event){
		                    event.close();
		                }
		            }]
		        });
			return false;
		}
	
		BootstrapDialog.show({
	        title: 'Confirmación',
	        message: 'Está seguro de realizar la operación? ',
	        align: 'center',
	        type : BootstrapDialog.TYPE_SUCCESS,
	        closable: false,
	        buttons: [{
	        	 label: 'OK',
	                cssClass: 'btn btn-success',
	                action: function(event){
	                	 event.close();

		        		$.ajax({
			        				url  : 'registraventaida',
			        				type : 'get',
			        				data : {EnvioCorreo : $('#correoIda').val()},
		        			beforeSend: function (response) {
		        							$('#CargaGrabarIda').css({display:'block'});},
		        			success   : function(response){
		        						
				        						if(response.respuestaServer){
			        								$('#CargaGrabarIda').css('display','none');
			        								//$("#modal-voucherIda").modal('show');
			        								ImprimirVoucher();
			        								
			        								
			        							}else{
			        								$('#CargaGrabarIda').css('display','none');
			        								 BootstrapDialog.show({
			        									  	title :'Mensaje de Alerta!',
			        									  	type : BootstrapDialog.TYPE_DANGER,
			        							            message: response.mensajeServer,
			        							            closable: false,
			        							            buttons: [{
			        							                label: 'OK',
			        							                cssClass: 'btn-danger',
			        							                action: function(event){
			        							                    event.close();
			        							                }
			        							            }]
			        							        });
			        								
			        							}
		        					}
		        		});
	                }
	           
	        }, {
	        	label: 'Cancelar',
	        	  action: function(event){
	            	event.close();
	            }
	        }]
	    });
	});

function ImprimirVoucher(){
	
	 BootstrapDialog.show({
		  	title :'Impresion de Voucher',
		  	type : BootstrapDialog.TYPE_SUCCESS,
         message: 'Desea Imprimir el Voucher?',
         closable: false,
         buttons: [{
             label: 'Aceptar',
             cssClass: 'btn btn-success',
             action: function(event){
                 event.close();
                 $('#hidContador').val('0'); 
                 $('#tblPasajeros').bootstrapTable('removeAll');
             	 $('#hidenviocorreoIda').val($('#correoIda').val())
             	 $("#correoIda").val('');
                 $('#formGenerarVoucherIda').submit();
                 return true;
             }
         },
         {
        	label: 'Cancelar',
        	action: function(event){
        	    $('#hidContador').val('0'); 
    		    $('#tblPasajeros').bootstrapTable('removeAll');
    		    $("#correoIda").val('');
    		    $('#formCancelarVoucherIda').submit();
    		    event.close();
	        }
         
	 	}]
     });
	
}

