$(function() {

});

$("#btnnuevaprogramacion").on("click", function() {
	$("#modalregistrarprogramacion").modal("show")
});

function responseHandler(res) {
	return res.resultados;
}
function estadoCiudades(valor) {
	var html = [];

	if (valor) {
		html.push("<label class='label label-primary'>");
		html.push("ACTIVO");
	} else {
		html.push("<label class='label label-danger'>");
		html.push("INACTIVO");
	}
	html.push("</label>");

	return html.join('');
}
function operacionesCiudades(id, estado) {
	var html = [];

	var estado = estado['Estado']; 
	
	var btnEstado;
	
	if (estado) {
		btnEstado = "<btn onclick=modalSwaltdp('" + id
				+ "') class='btn btn-sm btn-danger'>DESACTIVAR</btn>&nbsp;";
	} else {
		btnEstado = "<btn onclick=modalSwaltdp('" + id
				+ "') class='btn btn-sm btn-primary'>ACTIVAR</btn>&nbsp;";
	}
 
	html.push(btnEstado);

	return html.join('');
}

function modalSwaltdp(valor,estado) {
	swal({
		title : '¿Seguro que quiere cambiar el estado de la Programación?',
		text : 'Por favor confirme su operación.',
		type : 'warning',
		showCancelButton : true,
		confirmButtonColor : '#3085d6',
		cancelButtonColor : '#d33',
		cancelButtonText : 'NO',
		confirmButtonText : 'SÍ',
		closeOnConfirm : false
	}).then(function() {
		document.getElementById("valor").value = valor; 
		document.getElementById("frmprogramacion").submit();
	}, function(dismiss) {
		if (dismiss == 'cancel') {
			return false;
		}
	});
}