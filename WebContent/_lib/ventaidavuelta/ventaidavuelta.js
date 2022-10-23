$(function(){
	$("#divRegresar").hide();
});

$('#btnPagarVuelta').on('click',function(){

	if($('#rbTerminosVuelta').val() == 'false'){
		
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
	if($('#correoVuelta').val()==''){
		
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
	
	$('#CorreoPaso4').val($('#correoVuelta').val())
	$("#frmAccedePagoVuelta").submit();
	
});

$('#btnGrabarIdaVuelta').on('click',function(){
	
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
	if($('#correoVuelta').val()==''){
		
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
			        				url  : 'registroventaidavuelta',
			        				type : 'get',
			        				data : {EnvioCorreo : $('#correoVuelta').val()},
			        				beforeSend: function (response) {
		        										$('#CargaGrabarVuelta').css({display:'block'});},
		        					success   : function(response){
		        										$('#CargaGrabarVuelta').css('display','none');
					        						if(response.respuestaServer){
					        							$("#btnGrabarIdaVuelta").hide();
					        							$("#divRegresar").show();
				        								ImprimirVoucherVuelta();
				        								
				        							}else{
				        								$('#CargaGrabarVuelta').css('display','none');
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


function ImprimirVoucherVuelta(){
	
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
              $('#tblPasajerosVuelta').bootstrapTable('removeAll');
          	  $('#hidenviocorreoVuelta').val($('#correoVuelta').val())
          	  $("#correoVuelta").val('');
          	  $('#formGenerarVoucherIdaVuelta').submit();
              return true;
          }
      },
      {
	      label: 'Cancelar',
	      action: function(event){
	         $('#hidContador').val('0'); 
	 		 $('#tblPasajerosVuelta').bootstrapTable('removeAll');
	 		 $("#correoVuelta").val('');
	 		 $('#formCancelarVoucherIdaVuelta').submit();
	 		 event.close();
	            }
      
	 	}]
  });
}



