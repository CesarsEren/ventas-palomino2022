$(function(){
	
		$("#tblPasajeros").bootstrapTable('refresh').bootstrapTable({
			pagination: false,
			pageSize: 15,
			height: 250,
			showColumns: true,
			locale: "es-ES",
			silent: true
		});
		$("[data-disponible = false]").parent().addClass("silla-separada");
		$("[data-info = B]").empty();
		$("[data-info = E]").empty();
		$("[data-info = B]").parent().removeClass("silla")
		$("[data-info = B]").parent().removeClass("silla-separada").addClass("silla-bar");
		$("[data-info = E]").parent().removeClass("silla")
		$("[data-info = E]").parent().removeClass("silla-separada").addClass("silla-escalera");
		
		if(($("#piso2").children()).length == 0 ){
				$.each($("#piso1").children(), function(key, value){
				var top = value.style.top;
				value.style.top = (top.split("px")[0]-127)+"px";
			});

		};
		
		var sillaChecked = "";
		
		$(".silla-numero").on("click", function(){
			
			if($(this).parent().hasClass("silla-separada")){
				return;
			}
			if($(this).parent().hasClass("silla-televisor")){
				return;
			}
			if($(this).parent().hasClass("silla-bar")){
				return;
			}
			if($(this).parent().hasClass("silla-escalera")){
				return;
			}
			
			LimpiarDatos();
			
			$(".numeroAsiento").val($(this).attr("data-info"));
			$(".precioAsiento").val($(this).attr("data-precio"));
			$(this).parent().addClass("silla-seleccionada");
			
			sillaChecked = $(this).find("input");
			
			$("#mensajeServerModal").empty().removeClass();
			$("#btnActualizarPasajero").hide();
			$("#btnAgregarPasajero").show();
			
			$("#modalPaso2").modal("show");
		});
		
				
		$("#btnAplicarCuppon").on("click",function(){
			
			//alert($("#cuppon").val());
			$.ajax({
				url : 'aplicarcupon',
				type : 'post',
				//cuppon : $('#cuppon').val(),
				data: $("#frmPasajero").serialize(),
				dataType : 'json',
				success : function(res)
				{
						//alert(res.Cuponbean['codigocuppon']);
						//alert(res.cuponbean['montodescuento']);
						//console.log(res.cuponbean.montodescuento);
						//console.log(res.listaPasajeros)
					
						if(res.error)
						{
							$("#cuppon").val("");
							alert(res.mensajeServer);
						}
						//console.log(res.cuponbean);
						else{
							$("#PrecioTotal").text(res.total);
							$("#PrecioConCupon").text(res.totalcupon);
							$("#cuppon").val("");
	            		$("#codigocuppon1").text("");
		            	$("#cuppondescuento1").text("");
		            	$("#tipocuppon1").text("");
		            	$("#PrecioTotal").text()
		            	
						$("#modalValidarCuppon").modal("hide")
						
						}
				}, 
				error : function(xhr, status) {
			        	alert('Disculpe, existió un problema con la peticion al Servidor ');
			    },
			});
			
		});
		
		
		
		$("#btnAgregarPasajero").on("click", function(){
			HabilitarDatos();
			
			//agregado por JCHC
			$('body').append('<div id="modaledad" class="modal" tabindex="-1" role="dialog">'+
					  '<div class="modal-dialog modal-lg" role="document">'+
					    '<div class="modal-content">'+
					      '<div class="modal-header">'+							        
					        '<button type="button" class="close" data-dismiss="modal" aria-label="Close">'+
					          '<span aria-hidden="true">&times;</span>'+
					        '</button>'+
					      '</div>'+
					      '<div class="modal-body">'+
					        '<img src="https://domiliadosbucket.s3.amazonaws.com/fotos/anuncio45.jpeg" style="width: 100%; height: auto;">'+
					      '</div>'+							      
					    '</div>'+
					  '</div>'+
					'</div>');
			 $("#modaledad").modal("show");					 
			//finalizado por JCHC
			
			$.ajax({
				url 	: "agregarpasajero", 
				type	: "get",
				data	: $("#frmPasajero").serialize(),
				beforeSend: function (response) {
					$('#CargaAgregaIda').css({display:'block'});
					$("#mensajeServerModal").html("Mayores a 45 años, Obligatorio presentar carnet con las 2 Dosis de la Vacuna!!").addClass("alert alert-danger");
				},
				success	: function(res){
					if(res.error){
						$('#CargaAgregaIda').css('display','none');
						$("#mensajeServerModal").html(res.mensajeServer).addClass("alert alert-danger");
						
					}
					else{
						var cant = parseInt($('#hidContador').val());
						$('#hidContador').val(cant + 1);
						$('#CargaAgregaIda').css('display','none');
						var auxiliar = $(".destinobajada").val();
						var  destinoseparado  = [];
						destinoseparado =  auxiliar.split(',');
						
						
						sillaChecked.prop("checked", true);
						$("#modalPaso2").modal("hide");
						$("#PrecioTotal").val($(".precioAsiento").val());
						$("#tblPasajeros").bootstrapTable('insertRow', 
							{index : 1, row : { id:  res.paso3Form.id, nroDocumento : $(".nroDocumento").val(),nroAsiento : $(".numeroAsiento").val(),precio : $(".precioAsiento").val(),
											    nombre: $(".nombreCompleto").val(),ruc:$(".ruc").val(),razon:$(".razon").val(),identidad : $(".identidad").val(),telefono:$(".telefono").val(),
											    embarque :$(".embarque option:selected").text(),destinobajada :destinoseparado[1]}});
						
					//Mirar el panel de Cuppon	
					if($("#tblPasajeros").length!=0)
						{
						$("#panelcuppon").css("display","block");
						}
					}
				}
			});
		});
		
		$("#btnActualizarPasajero").on("click", function(){
			
			$.ajax({
				url 	: "actualizarpasajero",
				type	: "post",
				data	: $("#frmPasajero").serialize(),
				success	: function(res){
					if(res.error){
						$("#mensajeServerModal").html(res.mensajeServer).addClass("alert alert-danger");
					}
					else{
						
						var pasajero = res.paso3Form;
						
						$("#modalPaso2").modal("hide");
						$("#tblPasajeros").bootstrapTable('updateByUniqueId', 
							{id : $(".pasajeroSeleccionado").val(), 
							 row : { nroDocumento : pasajero.numeroDocumentoIdentidad, nombre: pasajero.nombrePasajero, 
									 nroAsiento : pasajero.numeroAsiento, precio : pasajero.precio,
									 embarque : pasajero.comboEmbarque, destinobajada : pasajero.comboDestinoBajada.split(",")[1]}});
					}
				}
			});
		});
		
		$("#modalPaso2").on('hidden.bs.modal', function(){
			$("#mensajeServerModal").empty().removeClass();
			$("#btnActualizarPasajero").hide();
			$("#btnAgregarPasajero").show();
			
			if(!sillaChecked.is(":checked")){
				(sillaChecked.parent()).parent().removeClass("silla-seleccionada");
			}
		});
		
	});

$('#rbFacturaIda').click(function() {
    if ($(this).is(':checked')) {
    	$('.ruc').prop('disabled',false)
		$('.razon').prop('disabled',false)
    }else{
    	$('.ruc').prop('disabled',true)
		$('.razon').prop('disabled',true)
    }
});


$('#rbTerminosIda').click(function() {
    if ($(this).is(':checked')) {
    	$('#rbTerminosIda').val('true');
    }else{
    	$('#rbTerminosIda').val('false');
    }
});

$("input[name='medioPago']").on("change", function(){
	if($(this).val() == "1"){
		$('.hidmediopago').val('1');
		console.log($('.hidmediopago').val());
	}
	else if($(this).val() == "2"){
		$('.hidmediopago').val('2');
		console.log($('.hidmediopago').val());
	} else {
		$('.hidmediopago').val('3');		
		console.log($('.hidmediopago').val());
	}
});

function validarNumerosReales(e) { // 1
    tecla = (document.all) ? e.keyCode : e.which; // 2
    if (tecla==8) return true; // 3
    patron = /[0123456789.]/; // Solo acepta números
    te = String.fromCharCode(tecla); // 5
    return patron.test(te); // 6
}
	
	function LimpiarDatos(){
		$(".nroDocumento").val('');
		$(".nombreCompleto").val('');
		$(".edad").val('');
		$(".telefono").val('');
		$(".ruc").val('');
		$(".razon").val('');
		$('#rbFacturaIda').prop("checked", false);
		$('.ruc').prop("disabled", true);
		$('.embarque').val('-1')
	}
	function HabilitarDatos(){
		$(".nombreCompleto").prop('disabled',false);
		$(".edad").prop('disabled',false);
		$(".telefono").prop('disabled',false);
		$(".razon").prop('disabled',false);
	}
	function CerosDecimales(event){
		
		var numero = parseInt(event.value);
		var tamanio = (6-(numero.toString().length));
		var ceros = '000.00';
		var resultado = '';
		
		if(parseInt(numero)<0){
			resultado=ceros;
			$('.precioAsiento').val(resultado);
			return;
		}
		
		if(isNaN(numero)){
			resultado = ceros;
			$('.precioAsiento').val(resultado);
			return;
			
		}
		
		if (tamanio == 6){
			resultado = numero;
		}
		
		else if (tamanio > String(ceros).length){
			 resultado = numero;
		}
		else if(numero.toString().includes('.')){
			
			if(tamanio == 4){
				if(isNaN(numero)){
					resultado = String(numero).substring(0,2)+'00';
				}else{
					resultado = ceros;
					
				}
			}
			
		}else{
			
			resultado = String(numero).substring(0,3)+'.00';
		}
		
		$('.precioAsiento').val(resultado);
		
	}

	$("#validacuppon").on("click",function(){

		var cuppon = $("#cuppon").val();
		if($('#hidContador').val()==0)
			{
			$("#cuppon").val("");
			$("#panelcuppon").css("display","none");
			alert("Debe añadir al menos un pasajero");
			}
		else{
		if(cuppon=="")
			{
			$("#cupponerror").text("Ingrese Texto");
			}
		else
			{
			$("#codigocuppon1").text("");
        	$("#cuppondescuento1").text("");
        	$("#tipocuppon1").text("");
			$.ajax({
				url:"comprobarcuppon",
				type:"post",
				data : { cuppon : $('#cuppon').val()},	
	            success: function (jsonrpta) {
	            	if(jsonrpta.error){
	            		$('#cuppon').val('');
	            		alert(jsonrpta.mensajeServer);
	            	}else
	            	{
	            		$("#modalValidarCuppon").modal("show");
	            		$("#codigocuppon1").text(jsonrpta.cuponbean['detalle']);
		            	$("#cuppondescuento1").text(jsonrpta.cuponbean['descuento']*100+" %");
		            	$("#tipocuppon1").text(jsonrpta.cuponbean['paracupon']);
		            	$("#cuppon").val("");
	            	}
	            },
	            error: function (xhr, status) {   
	                alert('Disculpe, existió un problema'); 
	            }
			});
			}}
	});
	