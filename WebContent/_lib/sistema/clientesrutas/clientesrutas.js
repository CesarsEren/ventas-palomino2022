$(function(){
	

	$('#hidDescripcionRuta').val($("select[id=idRuta] option:selected").text());
	$('#hidDescripcionServicio').val($("select[id=idServicio] option:selected").text());	
});


$(document).on("change", "#idRuta", function() {
	$('#hidDescripcionRuta').val($(this).find("option:selected").text());
});

$(document).on("change", "#idServicio", function() {
    $('#hidDescripcionServicio').val($(this).find("option:selected").text());
});

/*****************************************************************************************************************/
function CerosDecimalesRutas(event,tipo){
	
	var numero = parseInt(event.value);
	var tamanio = (7-(numero.toString().length));
	var ceros = '0000.00';
	var resultado = '';
	
	if(parseInt(numero)<0){
		resultado=ceros;
		if (tipo == '1'){
			$('.precio1').val(resultado);
		}else{
			$('.precio2').val(resultado);
		}
		
		
		return;
	}
	
	if(isNaN(numero)){
		resultado = ceros;
		
		if (tipo == '1'){
			$('.precio1').val(resultado);
		}else{
			$('.precio2').val(resultado);
		}
		
		return;
		
	}
	
	if (tamanio == 7){
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
		
		resultado = String(numero).substring(0,4)+'.00';
	}
	
	if (tipo == '1'){
		$('.precio1').val(resultado);
	}else{
		$('.precio2').val(resultado);
	}
	
	
}


function responseHandler(res){
	return res.resultados;
}

function operacionesClienteRuta(id){
	var html = [];
	
	var btnModificar  = "<a href='actualizaclienteruta?Id="+id+"' class='btn btn-sm btn-info'>MODIFICAR</a>&nbsp;";
	var btnEstado;
	
	html.push(btnModificar);
	
	return html.join('');
}


$("#select2Clientes").on("select2:select", function (e) {
	$('#hidCliente').val($('#select2Clientes').val())
	
}); 

$("#select2Rutas").on("select2:select", function (e) {
	$('#hidRutas').val($('#select2Rutas').val())
	$('#hidRutasD').val($('#select2Rutas option:selected').text())
	
}); 


$("#select2Servicio").on("select2:select", function (e) {
	$('#hidServicio').val($('#select2Servicio').val())
	$('#hidServicioD').val($('#select2Servicio option:selected').text())
	
}); 