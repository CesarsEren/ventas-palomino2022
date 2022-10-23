$(function() {

	$.ajax({

		url : 'getlistaruta',
		type : 'get',
		success : function(response) {
			$.each(response.listarutas, function() {
				var option = $('<option />');
				option.attr('value', this['nro']).text(this['nroDetalle']);
				$('#listarutas').append(option);
			});
		}
	});D

	nroruta: $('#listarutas  option:selected').val()

	$.ajax({

		url : 'cbocategoria',
		type : 'get',
		success : function(response) {
			$.each(response.listacategoriafallas,
					function() {
						var option = $('<option />');
						option.attr('value', this['idcategoria']).text(
								this['detalle']);
						$('#listacategoria').append(option);
					});
		}
	});

	$('#listacategoria').change(function() {
		// alert($('#listacategoria option:selected').val()+" :
		// "+$('#listacategoria option:selected').text());
		$('#listasubcategoria').empty();
		listarsubcategoria($('#listacategoria  option:selected').val());
	});

	function listarsubcategoria(id) {
		$
				.ajax({
					url : 'cbosubcategorias',
					type : 'get',
					data : {
						idcategoria : id
					},
					success : function(response) {
						$('#listasubcategoria')
								.append(
										'<option value="-1">-- Seleccione SubCategoria --</option>')
						$.each(response.listasubcategoriafallas, function() {
							var option = $('<option />');
							option.attr('value', this['idsubcategoria']).text(
									this['detalle']);
							$('#listasubcategoria').append(option);
						});
					}
				});
	}

	function registrar(data) {
		//alert(data);
		$.ajax({
			url : 'registrarfalla',
			type : 'post',
			data : {
				asunto : $('#asunto').val(),
				// nrofalla : $('#listacategoria option:selected').val(),
				nrosubfalla : $('#listasubcategoria  option:selected').val(),
				nrobus : $('#nrobus').val(),
				nroruta : $('#listarutas  option:selected').val(),
				descripcion : $('#descripcion').val(),
				registro : $('#registro').text(),
				archivo : data
			},
			suceess : function(rsp) {
				alert(rsp.msgserver);
			}
		});

		$('#nrobus').val('');
		$('#asunto').val('');
		$('#descripcion').val('');
	}

	AWS.config.update({
		accessKeyId : $('#key1').val(),
		secretAccessKey : $('#key2').val()
	});
	AWS.config.region = 'us-east-1';

	$("#btnenviar")
			.on(
					"click",
					function() {
						if ($('#asunto').val().trim() != ''
								&& $('#descripcion').val().trim() != ''
								&& $.isNumeric($('#nrobus').val())
								&& $('#listarutas option:selected').val() != -1
								&& $('#listasubcategoria option:selected')
										.val() != -1
								&& $('#listacategoria option:selected').val() != -1) {

							if ($('#upFile').val() == "") {
								 registrar("https://ventas.grupopalomino.com.pe:8443/ventas/_lib/img/noimage.jpg");
								//registrar("http://localhost:8080/ventas/_lib/img/noimage.jpg");
							} else {
								var bucket = new AWS.S3({
									params : {
										Bucket : 'grupopalomino/BitacoraFallas'
									}
								});

								var uploadFiles = $('#upFile')[0];
								var upFile = uploadFiles.files[0];
								if (upFile) {
									var uploadParams = {
										Key : upFile.name,
										ContentType : upFile.type,
										Body : upFile,
										ACL : "public-read"
									};

									bucket.upload(uploadParams).on(
											'httpUploadProgress',
											function(evt) {
											}).send(function(err, data) {
										// console.log(data.Location);
										registrar(data.Location);
										console.log(err);
										$('#upFile').val(null);
										$("#showMessage").show("");
									});
								}
							}
						} else {
							alert('Rellene los campos Correctamente');
						}
					});

});