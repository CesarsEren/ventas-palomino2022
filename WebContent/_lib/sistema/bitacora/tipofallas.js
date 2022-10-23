$(function() {

	// tipofallabean
	cargarfallas();
	$('#btnregistrotipo').on('click', function() {

		$.ajax({
			url : 'registrartipofalla',
			type : 'post',
			data : {
				detalletipofalla : $('#detalle').val()
			},
			success : function(rsp) {
				cargarfallas();
			}
		});
		$('#detalle').val('');

	});

});

function cargarfallas() {
	$.ajax({
		url : 'gettiposfallas',
		type : 'get',
		success : function(rsp) {
			$('#tipofalla tbody').html('');
			var Modelo = rsp.listatipofalla;
			for ( var i in Modelo) {
				var mdl = Modelo[i];
				$('#tipofalla tbody').append(
						"<tr>" + "<td>" + mdl.id + "</td>" + "<td>"
								+ mdl.detalle + "</td>"
								+ "<td> <button class='btn btn-"
								+ (mdl.estado ? "success" : "danger")
								+ "' onclick='changeestado(" + mdl.id + ","
								+ mdl.estado + ")'>" + mdl.estado
								+ "</button></td>" + +"</tr>")
			}
		}
	});
}

function changeestado(id, estado) {
	// alert(id+" : "+estado)
	$.ajax({
		url : 'cambiarestadoVfalla',
		type : 'get',
		data : {
			id : id,
			estadoVtipo : estado
		},
		success : function(rsp) {
			// location.reload(true);
			cargarfallas();
		}
	})
}


