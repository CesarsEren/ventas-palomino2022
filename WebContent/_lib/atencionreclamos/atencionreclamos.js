$(function(){
	
	var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; 

    var yyyy = today.getFullYear();
    if(dd<10){
        dd='0'+dd;
    } 
    if(mm<10){
        mm='0'+mm;
    } 
    var today = dd+'/'+mm+'/'+yyyy;
    $('.fechaReclamo').val(today);
	
    $(".fechaIncidente").datepicker({
		language : 'es',
		autoclose: true,
		endDate:'0d',
		todayHighlight: true
	});
    
    $('.dateRangePickerIncidente').on('click',function(){
		$(".fechaIncidente").datepicker('show');
		
	});
  $.ajax({
		url  : 'listareclamospasaje',
		type : 'get',
		success   : function(response){
				$.each(response.listaMotivos, function () {
					var option = $('<option />');
				     option.attr('value',this['motivoReclamo']).text(this['motivoReclamoD']);
				     $('#cboMotivo').append(option);
				});
				$.each(response.listaTipoDocumentos, function () {
					var option = $('<option />');
				     option.attr('value',this['codigo']).text(this['detalle']);
				     $('#cboTipoDocumento').append(option);
				});
			}
		});
  
 
});

function validarNumerosReclamo(e) { // 1
    tecla = (document.all) ? e.keyCode : e.which; // 2
    if (tecla==8) return true; // 3
    patron = /[0123456789]/; // Solo acepta números
    te = String.fromCharCode(tecla); // 5
    return patron.test(te); // 6
}


function VerificaDocumento(event){
	
	AumentarCerosNumero(event);
	
	if($('.serie').val()!= '' && $('.numero').val()!= ''){
		$.ajax({
			url  : 'verificadocumento',
			type : 'GET',
			data : {serie : $('.serie').val() , numero : $('.numero').val(), documento : $('#cboTipoDocumento').val(),empresa : $('#cboEmpresa').val()},
			success   : function(response){
				if(!response.respuestaServer){
					$('.serie').val('');
					$('.numero').val('');
					BootstrapDialog.show({
					  	title :'Mensaje de Advertencia!',
					  	type : BootstrapDialog.TYPE_DANGER,
			            message: response.mensajeServer,
			            closable: false,
			            buttons: [{
			                label: 'OK',
			                cssClass: 'btn btn-danger',
			                action: function(event){
			                    event.close();
			                }
			            }]
			        });
				  }
				}
			});
	  }
	
}

function AumentarCerosSerie(event){

		var numero= event.value;
		var tamanio = 3-(numero.length);
		var ceros = '000';
		var resultado='';

		if(numero==''){
			$('.serie').val(resultado);
			return;
		}
		if(parseInt(numero)<0){
			$('.serie').val(resultado);
			return;
		}
		if (tamanio == 3){
			resultado = numero;
		}
		else{
			resultado = String(ceros).substring(0,tamanio)+numero;
		}
		$('.serie').val(resultado);

}
	
function AumentarCerosNumero(event){
	
			var numero= event.value;
			var tamanio = 7-(numero.length);
			var ceros = '0000000';
			var resultado='';
	
			if(numero==''){
				$('.numero').val(resultado);
				return;
			}
	
			if(parseInt(numero)<0){
				resultado=ceros;
				$('.numero').val(resultado);
				return;
			}
	
			if (tamanio == 7){
				resultado = numero;
			}
			else{
				resultado = String(ceros).substring(0,tamanio)+numero;
			}
	
			$('.numero').val(resultado);

}
	
$('#cboDocumentoIdentidad').change(function(){
	$(".dni").val('')
	
	if($(this).val()=='P'){
		$(".dni").attr('maxlength','16');
	}else{
		
		$(".dni").attr('maxlength','8');
	}
	
	if($(this).val()=='M'){
		$('.padreomadre').prop('disabled',false)
	}else{
		$('.padreomadre').prop('disabled',true)
	}
	
});

$('#cboEmpresa').change(function(){
	
	$.ajax({
		url  : 'listaempresa',
		type : 'GET',
		data : {Codigo : $(this).val()},
		success   : function(response){
					$('#rucEmpresa').val(response.rucEmpresa);
					$('#dirEmpresa').val(response.dirEmpresa);
					$('#razonEmpresa').val(response.razonEmpresa);
			}
		});
});


$('#cboServicio').change(function(){
	
	$.ajax({
		url  : 'listamotivodocumento',
		type : 'get',
		data : {Tipo : $(this).val()},
		success   : function(response){
						$("#cboMotivo").empty();
						$.each(response.listaMotivos, function () {
							var option = $('<option />');
						     option.attr('value',this['motivoReclamo']).text(this['motivoReclamoD']);
						     $('#cboMotivo').append(option);
						});
				
						$("#cboTipoDocumento").empty();
						$.each(response.listaTipoDocumentos, function () {
							var option = $('<option />');
						     option.attr('value',this['codigo']).text(this['detalle']);
						     $('#cboTipoDocumento').append(option);
						}); 
			}
	});
	
});

$('#btnReclamo').on('click',function(){
	console.log($("#frmReclamos").serialize());
	var dialog
	var button; 	$.ajax({
				url  : 'enviaratencionreclamos',
				type : 'post',
				data : $("#frmReclamos").serialize(),
				beforeSend: function (response) {
					$('#mensajecorreo').removeClass();
					$('#mensajecorreo').html(''); 
					dialog =  new BootstrapDialog({
									title :'Mensaje de Alerta!',
									closable: false,
									animate : true,
						            message: $('<div id="idReclamo" class="row">'+
						            					'<div class="col-xs-8">'+
						            						'Por favor espere su solicitud se esta procesando...'+
						            					'</div>'+
						            					'<div class="col-xs-2">'+
						            						'<img src="_lib/gif/ajax-loader-agregar-ida.gif"/>'+
					            						'</div>'+
						            					
						            			'</div>'),
						            onshow: function(dialog) {
						            	button =  dialog.getButton('btn-reclamos-1');
						            	button.disable();
						            },
						            buttons: [{
						                id: 'btn-reclamos-1',
						                label: 'OK',
						                cssClass: 'btn-default'
						            }]
			        });
					dialog.open();
				},
				
				success   : function(response){
								if(response.respuestaServer){
												dialog.close();
												LimpiarControles();
												$('#hidEmpresaD').html(response.reclamos.empresaD);
											 	$('#hidNro').html(response.reclamos.nro);
												$('#hidNroR').val(response.reclamos.nro);
												$('#hidPeriodo').val(response.reclamos.periodo);
												$('#hidEmpresa').val(response.reclamos.empresa);
												$('#hidFechaReclamo').html(response.reclamos.fechaReclamo);
												$('#hidIdentidadD').html(response.reclamos.identidadD);
												$('#hidDNI').html(response.reclamos.DNI);
												$('#hidTelefono').html(response.reclamos.telefono);
												$('#hidEmail').html(response.reclamos.email);
												$('#hidNombres').html(response.reclamos.nombres);
												$('#hidApePaterno').html(response.reclamos.apePaterno);
												$('#hidApeMaterno').html(response.reclamos.apeMaterno);
												$('#hidPadreMenor').html(response.reclamos.padreMenor);
												$('#hidDomicilio').html(response.reclamos.domicilio);
												$('#hidServicioD').html(response.reclamos.servicioD);
												$('#hidMotivoReclamoD').html(response.reclamos.motivoReclamoD);
												$('#hidDocumentoD').html(response.reclamos.documentoD);
												$('#hidSerie').html(response.reclamos.serie);
												$('#hidNumero').html(response.reclamos.numero);
												$('#hidTipoD').html(response.reclamos.tipoReclamoD);
												$('#hidDestinoD').html(response.reclamos.destinoD);
												$('#hidAgenciaD').html(response.reclamos.agenciaD);
												$('#hidDetalle').html(response.reclamos.detalle);
												$('#hidPedido').html(response.reclamos.pedido);
												
												$('#modalReclamoConfirmado').modal('show');
											 	
									
								}else{
									
									dialog.setMessage('<div class="row">'+
											'<div class="col-xs-7">'+
												response.mensajeServer+
											'</div>'+
											'<div class="col-xs-2">'+
											'<img src="_lib/img/error-reclamos.png"/>'+  
		            						'</div>'+
	            					  '</div>')
										dialog.setType(BootstrapDialog.TYPE_DANGER)
										button.enable();
										$(button).on('click',function(event){
											dialog.close();
											
										});
									
								}
							
							}
				}); 
});



$('#btnImprimirAtencionReclamo').on('click',function(){
$('#frmAtencionReclamo').submit();
});

$('#btnReenviarCorreo').on('click',function(){
	
	if($('#txtReenvioCorreo').val()==''){
		$('#mensajecorreo').removeClass();
		$('#mensajecorreo').addClass("alert alert-danger");
		$('#mensajecorreo').html("Por favor ingrese su correo.");
		return;
	}else{
		$('#mensajecorreo').removeClass();
		$('#mensajecorreo').html('');
	}
	
	
	
	
	$.ajax({
		url  : 'reenviocorreoatencionreclamos',
		type : 'GET',
		data : {correo : $('#txtReenvioCorreo').val(),periodo : $('#hidPeriodo').val(),nro : $('#hidNroR').val(),empresa : $('#hidEmpresa').val()},
		beforeSend: function (response) {
					$('#CargaReCorreo').css({display:'block'});},
		success   : function(response){
					$('#CargaReCorreo').css('display','none');
					$('#txtReenvioCorreo').val('');
					if(response.respuestaServer){
						$('#mensajecorreo').addClass("alert alert-success");
						$('#mensajecorreo').html(response.mensajeServer)
					}else{
						$('#mensajecorreo').addClass("alert alert-danger");
						$('#mensajecorreo').html(response.mensajeServer)
					}
			
			}
	});
	
	
	
});

function LimpiarControles(){
		
		$(".dni").val('')
		$(".nombres").val('')
		$(".apepaterno").val('')
		$(".apematerno").val('')
		$(".telefono").val('')
		$(".email").val('')
		$(".domicilio").val('')
		$(".padreomadre").val('')
		$(".fechaIncidente").val('')
		$(".serie").val('')
		$(".numero").val('')
		$(".detalle").val('')
		$(".detalleatencion").val('')		
		$(".pedido").val('')
		$("#checksolucionado").prop("checked", false);
		
			
			
}

