$(function() {

	$("#tablafallas2").bootstrapTable('refresh', {
		url : 'listafallas?estado=Todos&porestado=true'
	});

	// cargartabla("Todos");
	llenarcbobuses();
	// $('#back').hide();

});

function responseHandler(res) {
	return res.listfallas;
}

function FotoVideo(value, row, index) {
	var html = [];
	var formato = row.foto.substring(row.foto.lastIndexOf('.') + 1,
			row.foto.length);
	var img;
	if (formato == 'jpg' || formato == 'png' || formato == 'jpeg') {
		img = '<img   src="'
				+ row.foto
				+ '" alt="Imagen cambiada" class="rounded" width="85px" heigth="85px"  />';
	} else {
		img = '<video   src="' + row.foto
				+ '" alt="Video" width="85px" heigth="85px"/>';
	}
	html.push(img);
	return html.join('');
}
function AccionesProgramacion(value, row, index) {
	var estado = row.estado.trim();
	var prim = "primary";
	var dang = "danger";
	var warn = "warning";
	var boton = estado == ("Pendiente") ? dang : estado == ("En Proceso")
			? warn
			: estado == ("Solucionado") ? prim : "";

	var html = [];
	html.push("<button class='changeestado btn btn-" + boton
			+ "' data-estado='" + row.estado + "' data-id=" + row.id + ">"
			+ row.estado.trim());
	html.push("</button>");
	html.push("<button class='verfalla btn btn-info' data-id=" + row.id
			+ ">  VER ");
	html.push("</button>");
	return html.join('');
}

var limit;
var datostemporales;
var nroporpagina = 5;
var inicio = 0;
var ultimo = inicio + nroporpagina;
var data;

$('#next').click(function() {
	$('#back').show();
	inicio = inicio + 1;
	ultimo = inicio + nroporpagina;
	llenartable(inicio, ultimo)
});
$('#back').click(function() {
	$('#next').show();
	ultimo = inicio - 2;
	inicio = ultimo - nroporpagina;
	llenartable(inicio, ultimo);
});

 

/*$('#listabuses').change(function() {
	inicio = 0;
	ultimo = inicio + nroporpagina;

	consultartabla();
});*/

 
/*function consultartabla() {
	if ($('#listabuses option:selected').val() == -1) {
		cargartabla($('#listaestado option:selected').val());
	} else {
		llenartablas($('#listabuses option:selected').val(), $(
				'#listaestado option:selected').val());
	}
};*/ 
$('#listaestado').change(function() {
	var data = $('#listaestado option:selected').val();
	var codigo = data;
	$("#tablafallas2").bootstrapTable('refresh', {
		url : 'listafallas?estado='+codigo+'&porestado=true'
	}); 
});

/*
 * function llenartablas(estado, nrobus) { $.ajax({ url : 'listafallas', type :
 * 'get', data : { estado : estado, nrobus : nrobus }, success : function(rsp) {
 * $("#tablafallas tbody").html(""); var Modelo = rsp.listafallas; limit =
 * rsp.listafallas.length; datostemporales = rsp.listafallas;
 * llenartable(inicio, ultimo);
 *  } });
 *  }
 */

function llenartable(ini, fin) {

	if (fin > limit) {
		fin = limit;
		$('#next').hide();
		$('#back').show();
	}
	if (ini == 0) {
		$('#back').hide();
	}
	if (ini < 0) {
		ini = 0;
		fin = nroporpagina;
		$('#next').show();
		$('#back').hide();
	}
	// console.log("inicia en " + ini)
	for (var ini; ini < fin; ini++) {
		i = ini;
		var formato = datostemporales[i].foto.substring(datostemporales[i].foto
				.lastIndexOf('.') + 1, datostemporales[i].foto.length);
		var img;
		if (formato == 'jpg' || formato == 'png' || formato == 'jpeg') {

			img = '<img   src="'
					+ datostemporales[i].foto
					+ '" alt="Imagen cambiada" class="rounded" width="85px" heigth="85px"  />';
		} else {

			img = '<video   src="' + datostemporales[i].foto
					+ '" alt="Video" width="85px" heigth="85px"/>';
		}

		data = data
				+ "<tr>"
				+ "<td>"
				+ datostemporales[i].nrobus
				+ "</td>"
				+ "<td>"
				+ datostemporales[i].categoriaD
				+ "</td>"
				+ "<td>"
				+ datostemporales[i].subcategoriaD
				+ "</td>"
				+ "<td>"
				+ datostemporales[i].rutaD
				+ "</td>"
				+ "<td>"
				+ img
				+ "</td>"
				+ "<td>"
				+ datostemporales[i].asunto
				+ "</td>"
				+ "<td>"
				+ datostemporales[i].fechacreacion
				+ "</td>"
				+ "<td>"
				+ "<input type='button' class='btn btn-"
				+ (datostemporales[i].estado == "Pendiente"
						? "danger"
						: datostemporales[i].estado == "En Proceso"
								? "warning"
								: "success") + "' onclick='actualizar("
				+ datostemporales[i].id + ",\"\ " + datostemporales[i].estado
				+ " \"\)' style='color:white' value='"
				+ datostemporales[i].estado + "'></input>"
				+ "<button class='btn btn-succes'"
				+ " style='color:black' onclick='ver(" + datostemporales[i].id
				+ ")' >VER</button>" + "</td>" + "<td>"
				+ datostemporales[i].registro + "</td>" + "<td>"
				+ datostemporales[i].resolvio + "</td>" + "</tr>";

		inicio = ini;
	}
	// console.log("termina en " + inicio)

	$('#tablafallas tbody').html("");
	$('#tablafallas tbody').append(data);
	data = "";
	// console.log("final " + fin);
	if (datostemporales.length > nroporpagina) {
		$("#next").show();
	}
};

function llenartablas(estado, nrobus) {
	$.ajax({
		url : 'listafallas',
		type : 'get',
		data : {
			estado : estado,
			nrobus : nrobus
		},
		success : function(rsp) {
			$("#tablafallas tbody").html("");
			var Modelo = rsp.listafallas;
			limit = rsp.listafallas.length;
			datostemporales = rsp.listafallas;
			llenartable(inicio, ultimo);

		}
	});

}

var temp1, temp2;
function actualizar(id, estado) {
	temp1 = id;
	temp2 = estado.trim();
	$("#contenidoproceso").val('');
	$("#contenidosolucion").val('');
	estado.trim() == 'Pendiente' ? $('#modalEnProceso').modal("show") : estado
			.trim() == 'En Proceso' ? $('#modalSolucionado').modal("show") : "";

}

function llenarcbobuses() {
	$.ajax({

		url : 'getnrobuses',
		type : 'get',
		success : function(response) {
			// console.log(response.listanrobuses);

			for (var i = 0; i < response.listanrobuses.length; i++) {
				$('#listabuses').append(
						'<option value=' + response.listanrobuses[i] + '> '
								+ response.listanrobuses[i] + ' </option>')
			}
		}
	});
}
$('#btnsolucionado').on('click', function() {

	$.ajax({
		url : 'actualizarfalla',
		type : 'post',
		data : {
			id : temp1,
			user : $('#user').text(),
			estado : temp2,
			contenido : $('#contenidosolucion').val()
		},
		success : function(rsp) {
			//cargartabla("Todos");
			$("#tablafallas2").bootstrapTable('refresh', {
				url : 'listafallas?estado=Todos&porestado=true'
			});
		}
	})

});

$('#btnproceso').on('click', function() {

	$.ajax({
		url : 'actualizarfalla',
		type : 'post',
		data : {
			id : temp1,
			user : $('#user').text(),
			estado : temp2,
			contenido : $('#contenidoproceso').val()
		},
		success : function(rsp) {
			// cargartabla("Todos");
			$("#tablafallas2").bootstrapTable('refresh', {
				url : 'listafallas?estado=Todos&porestado=true'
			});
		}
	})

});

// var temp1, temp2;

$("#tablafallas2").on(
		"click",
		".changeestado",
		function() {

			var id = $(this).data("id");
			var estado = $(this).data("estado");
			estado = estado.replace("\'", "");
			console.log(estado)
			temp1 = id;
			temp2 = estado.trim();
			$("#contenidoproceso").val('');
			$("#contenidosolucion").val('');

			estado.trim() == 'Pendiente'
					? $('#modalEnProceso').modal("show")
					: estado.trim() == 'En Proceso' ? $('#modalSolucionado')
							.modal("show") : "";

		});

$("#tablafallas2")
		.on(
				"click",
				".verfalla",
				function() {

					var x = $(this).data("id");
					// console.log(x.replace('\'',''));

					$('#idfalla').text('');
					$('#mnrobus').text('');
					$('#mcategoriafalla').text('');
					$('#msubcategoriafalla').text('');
					$('#mrutaD').text('');
					$('#masunto').text('');
					$('#mdetalle').text('');
					$('#dtproceso').text('');
					$('#dtsolucionado').text('');
					$('#mestado').text('');
					$('#mresolvio').text('');
					$('#mfechacreacion').text('');
					$('#mfechasolucion').text('');
					$('#modalbitacora').modal("show");
					$
							.ajax({
								url : 'fallaid',
								type : 'get',
								data : {
									id : x
								},
								success : function(rsp) {

									var mdl = rsp.encontrado;

									var formato = mdl.foto.substring(mdl.foto
											.lastIndexOf('.') + 1,
											mdl.foto.length);

									$('#idfalla').text(mdl.id);
									$('#mnrobus').text(mdl.nrobus);
									$('#mcategoriafalla').text(mdl.categoriaD);
									$('#msubcategoriafalla').text(
											mdl.subcategoriaD);
									$('#mrutaD').text(mdl.rutaD);
									$('#masunto').text(mdl.asunto);
									$('#mfechacreacion')
											.text(mdl.fechacreacion);
									$('#mfechasolucion').text(
											mdl.fecharesolucion);
									$('#mdetalle').text(mdl.detalle);
									$('#dtproceso').text(mdl.detalleenproceso);
									$('#dtsolucionado').text(
											mdl.detallesolucionado);
									$('#mestado').text(mdl.estado);
									$('#mregistro').text(mdl.registro);
									$('#mresolvio').text(mdl.resolvio);
									if (formato == 'jpg' || formato == 'png'
											|| formato == 'jpeg') {

										$('#mimagen')
												.replaceWith(
														'<img id="mimagen" src="'
																+ mdl.foto
																+ '" alt="imagen cambiada" class="img-responsive" />');
									} else {
										$('#mimagen')
												.replaceWith(
														'<video autoplay controls id="mimagen" src="'
																+ mdl.foto
																+ '" alt="imagen cambiada" class="img-responsive" />');
									}

								}
							})

				});

// verfalla
function ver(x) {
	$('#idfalla').text('');
	$('#mnrobus').text('');
	$('#mcategoriafalla').text('');
	$('#msubcategoriafalla').text('');
	$('#mrutaD').text('');
	$('#masunto').text('');
	$('#mdetalle').text('');
	$('#dtproceso').text('');
	$('#dtsolucionado').text('');
	$('#mestado').text('');
	$('#mresolvio').text('');
	$('#mfechacreacion').text('');
	$('#mfechasolucion').text('');
	$('#modalbitacora').modal("show");
	$
			.ajax({
				url : 'fallaid',
				type : 'get',
				data : {
					id : x
				},
				success : function(rsp) {

					var mdl = rsp.encontrado;

					var formato = mdl.foto.substring(
							mdl.foto.lastIndexOf('.') + 1, mdl.foto.length);

					$('#idfalla').text(mdl.id);
					$('#mnrobus').text(mdl.nrobus);
					$('#mcategoriafalla').text(mdl.categoriaD);
					$('#msubcategoriafalla').text(mdl.subcategoriaD);
					$('#mrutaD').text(mdl.rutaD);
					$('#masunto').text(mdl.asunto);
					$('#mfechacreacion').text(mdl.fechacreacion);
					$('#mfechasolucion').text(mdl.fecharesolucion);
					$('#mdetalle').text(mdl.detalle);
					$('#dtproceso').text(mdl.detalleenproceso);
					$('#dtsolucionado').text(mdl.detallesolucionado);
					$('#mestado').text(mdl.estado);
					$('#mregistro').text(mdl.registro);
					$('#mresolvio').text(mdl.resolvio);
					if (formato == 'jpg' || formato == 'png'
							|| formato == 'jpeg') {

						$('#mimagen')
								.replaceWith(
										'<img id="mimagen" src="'
												+ mdl.foto
												+ '" alt="imagen cambiada" class="img-responsive" />');
					} else {
						$('#mimagen')
								.replaceWith(
										'<video autoplay controls id="mimagen" src="'
												+ mdl.foto
												+ '" alt="imagen cambiada" class="img-responsive" />');
					}

				}
			})
}

/*function cargartabla(estado) {

	$.ajax({
		url : 'listafallas',
		type : 'get',
		data : {
			estado : estado
		},
		success : function(rsp) {
			limit = rsp.listafallas.length;
			datostemporales = rsp.listafallas;
			llenartable(inicio, ultimo);
		}

	});

}*/