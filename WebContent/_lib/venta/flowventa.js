$(function(){
	
	$("#ventawizard").bootstrapWizard({
		onTabClick: function(tab, navigation, index) {
			return false;
		}
	});
	
	$("#btnAPaso2").on("click", function(){
		$.ajax({
			url			: "testmapa",
			success		: function(res){
				$("#modalPaso1").modal("hide");
				$(".next").click();
				   
				$("#mapaIda").html(res);
				
			}
		});
	});
	
	$("#tblPasajeros").on("click", ".actualizarPasajero", function(){
		
		var numeroDocumento = $(this).attr("data-nroDocumento");
		var id = $(this).attr("data-id");
		
		$.ajax({
			type	: 'get',
			url		: 'obtenerpasajero',
			data	: {numeroDocumento : numeroDocumento},
			success : function(res){
				var json = res.paso3Form
				for(key in json){
					$("input[name='paso3Form."+key+"']").val(json[key]);
				}
				
				$("#paso3Form_comboDestinoBajada option:contains("+json["comboDestinoBajada"].split(",")[1]+")").prop("selected", true);
				$("#paso3Form_comboEmbarque").val(json["comboEmbarque"]);
				
				$("#btnActualizarPasajero").show();
				$("#btnAgregarPasajero").hide();
				$("#modalPaso2").modal("show");
				
				$(".pasajeroSeleccionado").val(id);
			}
		});
		
	});
	
	$("#tblPasajeros").on("click", ".eliminarPasajero", function(){
		
		var nroAsiento = $(this).attr("data-nroAsiento");
		var numeroDocumento = $(this).attr("data-nroDocumento");
	//	var precio = $(this).attr("data-nroPrecio");
		var id = $(this).attr("data-id");
	//	var montoActual = $('#totalIda').val();
	//	var monto = (parseInt(montoActual) - parseInt(precio));
		swal({
			  title: '¿Desea quitar al pasajero?',
			  text: "Por favor, confirme su acción.",
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'SI',
			  cancelButtonText: 'NO',
			  confirmButtonClass: 'btn btn-success',
			  cancelButtonClass: 'btn btn-danger',
			  buttonsStyling: false
			}).then(function(isConfirm) {
			  if (isConfirm === true) {
				  $('#hidContador').val($('#hidContador').val()-1);
				$.ajax({
			    	type	: "get",
			    	url		: "quitarpasajero",
			    	data	: {numeroDocumento : numeroDocumento},
			    	success	: function(res){
			    		$("#tblPasajeros").bootstrapTable('removeByUniqueId', id);
			    		$("#AS-"+nroAsiento).prop("checked", false);
			    		($("#AS-"+nroAsiento).parent()).parent().removeClass("silla-seleccionada");
			    		//$('#totalIda').val(monto+ '.0');
			    	}
			    });
			  } 
			})
	});
	
});

function acciones(value, row, index){
	var html = [];
	
	var nroDocumento 	= row['nroDocumento'];
	var nroAsiento 		= row['nroAsiento'];
	var nroPrecio 		= row['precio'];
	var id = row['id'];
	
	var btnVerPasajero = "<button type='button'  onclick=verPasajero('"+nroDocumento+"') class='btn btn-sm btn-primary'><i class='fa fa-search'></i></button>&nbsp;";
	var btnEliminarPasajero = "<button type='button' data-nroPrecio="+nroPrecio+"  data-nroDocumento="+nroDocumento+" data-nroAsiento="+nroAsiento+" data-id="+id+" id='data-"+nroDocumento+"' class='eliminarPasajero btn btn-sm btn-danger'><i class='fa fa-times'></i></button>"
	var btnActualizarPasajero = "<button type='button' data-nroDocumento="+nroDocumento+" data-id="+id+" class='actualizarPasajero btn btn-sm btn-info'><i class='fa fa-pencil-square-o'></i></button>"
	
	html.push(btnVerPasajero);
	//html.push(btnActualizarPasajero);
	html.push(btnEliminarPasajero);
	
	return html.join('');
}

function verPasajero(numeroDocumento){
	$.ajax({
		type	: 'get',
		url		: 'obtenerpasajero',
		data	: {numeroDocumento : numeroDocumento},
		success : function(res){
			var json = res.paso3Form;
			for(key in json){
				$("input[name='paso3Form."+key+"']").val(json[key]);
			}
			
			$("#paso3Form_comboDestinoBajada option:contains("+json["comboDestinoBajada"].split(",")[1]+")").prop("selected", true);
			$("#paso3Form_comboEmbarque").val(json["comboEmbarque"]);
			
			$("#modalPaso2").modal("show");
			$("#btnAgregarPasajero").hide();
		}
	});
}

function actualizaPasajero(numeroDocumento){
	
}

