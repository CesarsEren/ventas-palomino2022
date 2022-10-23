$(function() {

	$("#selectUsuario").select2({
		theme : "bootstrap",
		placeholder : "Por favor espere...",
		language : "es"
	})
	$.ajax({
		url : "cbousventelefonica",
		dataType : 'json',
		success : function(response) {

			$("#selectUsuario").select2({
				theme : "bootstrap",
				placeholder : "Seleccione Destino",
				language : "es",
				data : response.usventatelefonica
			})
		},
		error : function(error) {
			$("#selectUsuario").select2({
				theme : "bootstrap",
				placeholder : {
					id : '-1',
					text : 'No se encontraron resultados'
				},
				language : "es"
			});
		}
	});
});
function responseHandler(res) {
	return res.usventatelefonica;
}
// listventasventelefonicas?todos=true

$('#selectUsuario').on('select2:select', function(e) {
	var data = e.params.data;
	// console.log(""+data);
	// console.log(""+data.value);
	if (data.text == "Todos") { 
		$("#tablaventas").bootstrapTable('refresh', {
			url : 'listventasventelefonicas?todos=true'
		});
	} else {
		$("#tablaventas").bootstrapTable('refresh', {
			url : 'listventasventelefonicas?todos=false&usuario=' + data.text
		});
	} 
	// var destino = $('#selectUsuario').select2('data');
});
$("#btnWeb").on("click",function(){
	
	swal({
		title : 'Liberar Asiento Palomino Web', 
		html: 
		  '<input id="nro" class="swal2-input" placeholder="Código Único de Asiento">' +
	      '<input id="dni" class="swal2-input" placeholder="Documento ">' + 
	      '<input id="codemesaayuda" class="swal2-input" placeholder="Codigo de Mesa de Ayuda">'+
	      '<textarea id="motivo" class="swal2-input" placeholder="Motivo de Anulación max(300)"></textarea>'+
	      '<p>Para liberar un asiento de <strong>Palomino web </strong>'+
		  ' necesitamos la información del cliente '+
		  'y el código devuelto luego de enviar el correo a '+
		  '<strong>mesadeayuda@grupopalomino.com.pe</strong></p>',
			showCancelButton : true,
			confirmButtonColor : '#3085d6',
			cancelButtonColor : '#d33',
			cancelButtonText : 'Cancelar',
			confirmButtonText : 'Liberar Asiento',
			closeOnConfirm : false
	}).then(function() {
		var codemesayuda = $("#codemesaayuda").val()
		var nro = $("#nro").val()
		var motivo = $("#motivo").val()
		var dni= $("#dni").val()
		$.ajax({ 
			dangerMode: true,
			url : 'liberarasientoWeb',//?nro=' + nro + '',
			type : 'get',
			data:
				{
				nro:nro,
				motivo:motivo,
				codemesayuda:codemesayuda,
				DNI:dni
				},
			success : function(rsp) {
				if (rsp.msgserver == "Liberado") { 
					swal({
						title : rsp.mensaje,
						text : 'Espere 2 minutos,el asiento será liberado automáticamente',
						type: "success",
					});
					$("#tablaventas").bootstrapTable('refresh', {url : 'listventasventelefonicas?todos=true'});					
				}else
				{
					swal({
					title : rsp.mensaje,
					type: "error",
				}); 
				}
			}
		});
	}, function(dismiss) {
		if (dismiss == 'cancel') {
			return false;
		}
	}); 
});

$("#btnRedBus").on("click",function(){ 
	swal({
		title : 'Liberar Asiento de Red Bus', 
		html: 
		  '<input id="nro" class="swal2-input" placeholder="Codigo Único de Asiento">' +
	      //'<input id="dni" class="swal2-input" placeholder="Documento Nacional de Identidad">' + 
	      '<input id="codemesaayuda" class="swal2-input" placeholder="Codigo de Mesa de Ayuda">'+
	      '<textarea id="motivo" class="swal2-input" placeholder="Motivo de Anulación max(300)"></textarea>'+
	      '<p>Para liberar un asiento de <strong>RedBus</strong>'+
		  ' necesitamos la información del cliente '+
		  'y el código devuelto luego de enviar el correo a '+
		  '<strong>mesadeayuda@grupopalomino.com.pe</strong></p>',
			showCancelButton : true,
			confirmButtonColor : '#3085d6',
			cancelButtonColor : '#d33',
			cancelButtonText : 'Cancelar',
			confirmButtonText : 'Liberar Asiento',
			closeOnConfirm : false
	}).then(function() {
		var codemesayuda = $("#codemesaayuda").val()
		var nro = $("#nro").val()
		var motivo = $("#motivo").val()
		
		$.ajax({ 
			dangerMode: true,
			url : 'liberarasientoRB',//?nro=' + nro + '',
			type : 'get',
			data:
				{
				nro:nro,
				motivo:motivo,
				codemesayuda:codemesayuda
				},
			success : function(rsp) {
				if (rsp.msgserver == "Liberado") { 
					swal({
						title : rsp.mensaje,
						text : 'Espere 2 minutos,el asiento será liberado automáticamente',
						type: "success",
					});
					$("#tablaventas").bootstrapTable('refresh', {url : 'listventasventelefonicas?todos=true'});					
				}else
				{
					swal({
					title : rsp.mensaje,
					type: "error",
				}); 
				}
			}
		});
	}, function(dismiss) {
		if (dismiss == 'cancel') {
			return false;
		}
	});
	
	
});

$("#tablaventas").on("click",".liberarasiento",function() {
	var nro= $(this).data("nro");
	var usuario = $(this).data("usuario");
	var destinoD= $(this).data("destinod");	
	var asiento= $(this).data("asiento");
	
	console.log(nro);	
	swal({
		title : '¿Seguro que quiere liberar este Asiento?',
		text : 	'Destino '+destinoD+'<br>'+
				'Asiento :'+asiento+'<br>'+
				'del Usuario : '+usuario+
				'<br> Por favor confirme su operación .',
		type : 'warning',
		showCancelButton : true,
		confirmButtonColor : '#3085d6',
		cancelButtonColor : '#d33',
		cancelButtonText : 'NO',
		confirmButtonText : 'SI',
		closeOnConfirm : false
	}).then(function() {
		$.ajax({ dangerMode: true,
			url : 'liberarasiento?nro=' + nro + '',
			type : 'get',
			success : function(rsp) {
				if (rsp.msgserver == "Liberado") {
					
					swal({
						title : rsp.mensaje,
						text : 'Espere de 2 minutos,el asiento será liberado automáticamente',
						type: "success",
					}); 
					$("#tablaventas").bootstrapTable('refresh', {url : 'listventasventelefonicas?todos=true'});					
				}else
				{
					swal({
					title : rsp.mensaje,
					type: "error",
				}); 
				}
			}
		});
	}, function(dismiss) {
		if (dismiss == 'cancel') {
			return false;
		}
	});
});
function acciones(value, row, index) {
	var html = [];
	html.push("<button data-nro="+row.nro+" data-usuario="+row.usuarioVisa+" data-destinoD="+row.destinoD+" data-asiento="+row.asiento+" " +
			"class='liberarasiento btn btn-danger'>");
	html.push("Liberar Asiento");
	html.push("</button>");
	return html.join('');
}
