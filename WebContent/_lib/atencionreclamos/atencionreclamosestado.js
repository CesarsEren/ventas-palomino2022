function responseHandler(res) {
	return res.reclamoinformes;
}

function FormatterEstado(valor) {
	var html = [];

	if (valor == "S") {

		html
				.push("<span style='color:#009740;font-weight: bold;'>ATENDIDO</span>");
	} else if (valor == "N") {
		html
				.push("<span style='color:#E30613;font-weight: bold;'>NO ATENDIDO</span>");

	} else {
		html
				.push("<span style='color:#005C89;font-weight: bold;'>PENDIENTE</span>");
	}

	return html.join('');
}

function operacionesReclamos(Id, row) {
	var html = [];

	var atendido = row.atendido;
	var btnReclamo = "<btn onclick=modalSwal('" + Id
			+ "') class='btn btn-sm btn-primary'>Confirmar Atención</btn>";
	var btnPdf = "<a href='imprimiratencionreclamosestado?reclamos.empresa="
			+ row.empresa + "&reclamos.nro=" + row.nro + "&reclamos.periodo="
			+ row.periodo + "' class='btn btn-sm btn-warning'>PDF</a>&nbsp;";

	if (atendido == "N" || atendido == "P") {
		html.push(btnReclamo);
	}

	html.push(btnPdf);

	return html.join('');
}

function generaEvento(event) {
	if ($('#txtDetalle').val().trim() != '') {
		$('#hidDetalle').val($('#txtDetalle').val().trim().toUpperCase());
	}

}

function modalSwal(valor) {

	$('#hidId').val(valor);

	swal(
			{
				title : '¿Seguro desea cambiar el estado del reclamo?',
				text : 'Por favor confirme su operación.',
				// input : 'textarea',
				html : $("<textarea id='txtDetalle' name='detalle' maxlength='1500' onkeyup='generaEvento(this);' placeholder='Ingrese detalle de la atención. (Max. 1500 caracteres)' class='swal2-textarea' style='display: block;text-transform:uppercase;'></textarea>"),
				type : 'warning',
				showCancelButton : true,
				confirmButtonColor : '#3085d6',
				cancelButtonColor : '#d33',
				cancelButtonText : 'NO',
				confirmButtonText : 'SÍ',
				closeOnConfirm : false,
				preConfirm : function() {
					var text = $('#txtDetalle').val().trim();
					$('#hidDetalle').val(text);
					return new Promise(
							function(resolve, reject) {
								if (text === '') {
									reject('Por favor ingresar el detalle de la atención.')
								} else {
									resolve($('#txtDetalle').val())
								}
							})
				},
			}).then(function(text) {
		document.getElementById("frmEstadoReclamos").submit();
	}, function(dismiss) {
		if (dismiss == 'cancel') {
			return false;
		}
	});
}

$('#cboTipoConsulta').change(function() {

	$("#tblReclamosEstado").bootstrapTable('destroy').bootstrapTable({
		pagination : true,
		pageSize : 10,
		height : 400,// 400,
		showColumns : true,
		locale : "es-ES",

		url : "listareclamosinformes",
		queryParams : function(params) {
			params['tipo'] = $("#cboTipoConsulta").val();
			params['agencia'] = $("#cboAgencia").val();
			params['servicio'] = $("#cboServicio").val();
			return params;

		},
		responseHandler : function(res) {

			if (res.respuestaServer) {

				BootstrapDialog.show({
					title : 'Mensaje de Advertencia!',
					type : BootstrapDialog.TYPE_DANGER,
					message : res.mensajeServer,
					closable : false,
					buttons : [ {
						label : 'OK',
						cssClass : 'btn btn-danger',
						action : function(event) {
							event.close();
						}
					} ]
				});
				return true;

			}

			return res.reclamoinformes;
		}

	});

});

$('#cboAgencia').change(function() {

	$("#tblReclamosEstado").bootstrapTable('destroy').bootstrapTable({
		pagination : true,
		pageSize : 10,
		height : 400,// 400,
		showColumns : true,
		locale : "es-ES",

		url : "listareclamosinformes",
		queryParams : function(params) {
			params['tipo'] = $("#cboTipoConsulta").val();
			params['agencia'] = $("#cboAgencia").val();
			params['servicio'] = $("#cboServicio").val();
			return params;

		},
		responseHandler : function(res) {

			if (res.respuestaServer) {

				BootstrapDialog.show({
					title : 'Mensaje de Advertencia!',
					type : BootstrapDialog.TYPE_DANGER,
					message : res.mensajeServer,
					closable : false,
					buttons : [ {
						label : 'OK',
						cssClass : 'btn btn-danger',
						action : function(event) {
							event.close();
						}
					} ]
				});
				return true;

			}
			return res.reclamoinformes;
		}
	});

});

$('#txtdni').keyup(function() {
	var valormayorOcho = $('#txtdni').val();
	
	if (valormayorOcho.length> 7) {
		$("#tblReclamosEstado").bootstrapTable('destroy').bootstrapTable({
			pagination : true,
			pageSize : 10,
			height : 400,// 400,
			showColumns : true,
			locale : "es-ES", 
			url : "listareclamosinformes",
			queryParams : function(params) {
				params['tipo'] = $("#cboTipoConsulta").val();
				params['agencia'] = $("#cboAgencia").val();
				params['servicio'] = $("#cboServicio").val();
				params['dni'] = $("#txtdni").val();
				return params; 
			},
			responseHandler : function(res) { 
				if (res.respuestaServer) { 
					BootstrapDialog.show({
						title : 'Mensaje de Advertencia!',
						type : BootstrapDialog.TYPE_DANGER,
						message : res.mensajeServer,
						closable : false,
						buttons : [ {
							label : 'OK',
							cssClass : 'btn btn-danger',
							action : function(event) {
								event.close();
							}
						} ]
					});
					return true;

				}
				return res.reclamoinformes;
			}
		});
	}

});
$('#cboServicio').change(function() {

	$("#tblReclamosEstado").bootstrapTable('destroy').bootstrapTable({
		pagination : true,
		pageSize : 10,
		height : 400,// 400,
		showColumns : true,
		locale : "es-ES",

		url : "listareclamosinformes",
		queryParams : function(params) {
			params['tipo'] = $("#cboTipoConsulta").val();
			params['agencia'] = $("#cboAgencia").val();
			params['servicio'] = $("#cboServicio").val();
			return params;

		},
		responseHandler : function(res) {

			if (res.respuestaServer) {

				BootstrapDialog.show({
					title : 'Mensaje de Advertencia!',
					type : BootstrapDialog.TYPE_DANGER,
					message : res.mensajeServer,
					closable : false,
					buttons : [ {
						label : 'OK',
						cssClass : 'btn btn-danger',
						action : function(event) {
							event.close();
						}
					} ]
				});
				return true;

			}
			return res.reclamoinformes;
		}
	});

});

function queryParams(params) {
	params['tipo'] = $("#cboTipoConsulta").val();
	params['agencia'] = $("#cboAgencia").val();
	params['servicio'] = $("#cboServicio").val();
	return params;
}
