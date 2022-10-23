$(function() {

	$("#tblPasajerosVuelta").bootstrapTable('refresh').bootstrapTable({
		url : "listapasajerosagregadosvuelta",
		pagination : false,
		pageSize : 15,
		height : 250,
		showColumns : true,
		locale : "es-ES",
		silent : true,
		responseHandler : function(res) {
			$("#hidContador").val(res.mapaJSONResultado.rows.length);
			return res.mapaJSONResultado.rows;
		}
	});

	$("[data-disponible = false]").parent().addClass("silla-separada");
	$("[data-info = B]").empty();
	$("[data-info = E]").empty();
	$("[data-info = B]").parent().removeClass("silla")
	$("[data-info = B]").parent().removeClass("silla-separada").addClass(
			"silla-bar");
	$("[data-info = E]").parent().removeClass("silla")
	$("[data-info = E]").parent().removeClass("silla-separada").addClass(
			"silla-escalera");

	if (($("#piso2").children()).length == 0) {
		$.each($("#piso1").children(), function(key, value) {
			var top = value.style.top;
			value.style.top = (top.split("px")[0] - 127) + "px";
		});

	}

	var sillaChecked = "";

	$(".silla-numero").on("click", function() {

		if ($(this).parent().hasClass("silla-separada")) {
			return;
		}
		if ($(this).parent().hasClass("silla-televisor")) {
			return;
		}
		if ($(this).parent().hasClass("silla-bar")) {
			return;
		}
		if ($(this).parent().hasClass("silla-escalera")) {
			return;
		}

		$(".numeroAsientoVuelta").val($(this).attr("data-info"));
		$(".precioAsientoVuelta").val($(this).attr("data-precio"));
		$(this).parent().addClass("silla-seleccionada");
		$('.embarqueVuelta').val('-1')
		sillaChecked = $(this).find("input");

		$("#mensajeServerModalIdaVuelta").empty().removeClass();
		$("#btnAsignarAsientoPasajero").show();
		$("#modalPaso4").modal("show");
	});

	$("#btnAsignarAsientoPasajero")
			.on(
					"click",
					function() {

						$("#panelcuppon").css("display", "block");
						$
								.ajax({
									url : "asientopasajero",
									type : "get",
									data : {
										idPasajero : $('#select2Pasajeros')
												.val(),
										numeroAsiento : $(
												".numeroAsientoVuelta").val(),
										destinobajadaVuelta : $(
												".destinobajadaVuelta").val(),
										embarqueVuelta : $(".embarqueVuelta")
												.val(),
										precio : $('.precioAsientoVuelta')
												.val()
									},
									beforeSend : function(response) {
										$('#CargaAgregaIdaVuelta').css({
											display : 'block'
										});
									},
									success : function(res) {
										if (res.errorserver) {
											$('#CargaAgregaIdaVuelta').css(
													'display', 'none');
											$("#mensajeServerModalIdaVuelta")
													.html(res.mensajeServer)
													.addClass(
															"alert alert-danger");
										} else {
											var cant = parseInt($(
													'#hidContador').val());
											$('#hidContador').val(cant + 1);
											$('#CargaAgregaIdaVuelta').css(
													'display', 'none');
											$(this).parent().addClass(
													"silla-seleccionada");
											var auxiliar = $(
													".destinobajadaVuelta")
													.val();
											var destinoseparadoVuelta = [];
											destinoseparadoVuelta = auxiliar
													.split(',');
											sillaChecked = $(this)
													.find("input");
											$("#modalPaso4").modal("hide");
											$("#tblPasajerosVuelta")
													.bootstrapTable(
															'insertRow',
															{
																index : 1,
																row : {
																	id : res.idTabla,
																	nroAsiento : $(
																			".numeroAsientoVuelta")
																			.val(),
																	precio : $(
																			".precioAsientoVuelta")
																			.val(),
																	nombre : $(
																			'#select2Pasajeros option:selected')
																			.text(),
																	embarque : $(
																			".embarqueVuelta option:selected")
																			.text(),
																	destinobajada : destinoseparadoVuelta[1]
																}
															});

											$("#select2Pasajeros").select2(
													"val", "");
											/*
											 * if($('#hidContador').val()=='1'){
											 * $('#totalVuelta').val('');
											 * $('#totalVuelta').val($(".precioAsientoVuelta").val());
											 * 
											 * }else{
											 * $('#totalVuelta').val(parseFloat($('#totalVuelta').val())+
											 * parseFloat($(".precioAsientoVuelta").val())+'.0') }
											 */
										}
									}
								});

					});

	$("#modalPaso4").on('hidden.bs.modal', function() {
		$("#mensajeServerModalIdaVuelta").empty().removeClass();
		$("#btnActualizarPasajero").hide();
		$("#btnAsignarAsientoPasajero").show();

		if (!sillaChecked.is(":checked")) {
			(sillaChecked.parent()).parent().removeClass("silla-seleccionada");
		}
	});

	$('#rbTerminosVuelta').click(function() {
		if ($(this).is(':checked')) {
			$('#rbTerminosVuelta').val('true');
		} else {
			$('#rbTerminosVuelta').val('false');
		}
	});

	$("input[name='medioPago']").on("change", function() {
		if ($(this).val() == "1") {
			$('.hidmediopagoVuelta').val('1');
		} else if ($(this).val() == "2") {
			$('.hidmediopagoVuelta').val('2');
		} else {
			$('.hidmediopagoVuelta').val('3');
		}
	});

	$("#validacupponret").on(
			"click",
			function() {

				var cuppon = $("#cuppon").val();
				if ($('#hidContador').val() == 0) {
					$("#cuppon").val("");
					$("#panelcuppon").css("display", "none");
					alert("Debe añadir al menos un pasajero");
				} else {
					if (cuppon == "") {
						$("#cupponerror").text("Ingrese Texto");
					} else {
						$("#codigocuppon1").text("");
						$("#cuppondescuento1").text("");
						$("#tipocuppon1").text("");
						$.ajax({
							url : "comprobarcupponret",
							type : "post",
							data : {
								cuppon : $('#cuppon').val()
							},
							success : function(jsonrpta) {
								if (jsonrpta.errorserver) {
									$('#cuppon').val('');
									alert(jsonrpta.mensajeServer);
								} else {
									$("#modalValidarCuppon").modal("show");
									$("#codigocuppon1").text(
											jsonrpta.cuponbean['detalle']);
									$("#cuppondescuento1").text(
											jsonrpta.cuponbean['descuento']
													* 100 + " %");
									$("#tipocuppon1").text(
											jsonrpta.cuponbean['paracupon']);
									$("#cuppon").val("");
								}
							},
							error : function(xhr, status) {
								alert('Disculpe, existió un problema');
							}
						});
					}
				}
			});

	$("#btnAplicarCuppon")
			.on(
					"click",
					function() {

						// alert($("#cuppon").val());
						$
								.ajax({
									url : 'aplicarcuponret',
									type : 'post',
									// cuppon : $('#cuppon').val(),
									data : $("#frmPasajeroIdaVuelta")
											.serialize(),
									dataType : 'json',
									success : function(res) {
										// alert(res.Cuponbean['codigocuppon']);
										// alert(res.cuponbean['montodescuento']);
										// console.log(res.cuponbean.montodescuento);
										// console.log(res.listaPasajeros)

										if (res.error) {
											$("#cuppon").val("");
											alert(res.mensajeServer);
										}
										// console.log(res.cuponbean);
										else {
											$("#PrecioTotal").text(
													'S/' + res.total);
											$("#PrecioConCupon").text(
													'S/' + res.totalcupon);
											$("#cuppon").val("");
											$("#codigocuppon1").text("");
											$("#cuppondescuento1").text("");
											$("#tipocuppon1").text("");
											$("#PrecioTotal").text()

											$("#modalValidarCuppon").modal(
													"hide")

										}
									},
									error : function(xhr, status) {
										alert('Disculpe, existió un problema con la peticion al Servidor ');
									},
								});

					});
});

function CerosDecimales(event) {

	var numero = parseInt(event.value);
	var tamanio = (6 - (numero.toString().length));
	var ceros = '000.00';
	var resultado = '';

	if (parseInt(numero) < 0) {
		resultado = ceros;
		$('.precioAsientoVuelta').val(resultado);
		return;
	}

	if (isNaN(numero)) {
		resultado = ceros;
		$('.precioAsientoVuelta').val(resultado);
		return;

	}

	if (tamanio == 6) {
		resultado = numero;
	}

	else if (tamanio > String(ceros).length) {
		resultado = numero;
	} else if (numero.toString().includes('.')) {

		if (tamanio == 4) {

			if (isNaN(numero)) {
				resultado = String(numero).substring(0, 2) + '00';
			} else {
				resultado = ceros;

			}

		}

	} else {
		resultado = String(numero).substring(0, 3) + '.00';
	}

	$('.precioAsientoVuelta').val(resultado);

}