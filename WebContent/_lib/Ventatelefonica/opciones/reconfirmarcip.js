 function responseHandlerPagoEfectivo(res) {
/*	console.log(res);
	var arreglo = [];
	arreglo.push(res.rows);*/
	return res.rows;
} 
$("#btnBusquedaPagoEfectivo").on("click",
				function() {
				var CIP = $("#query").val(); 
					$.ajax({
								url : 'consultapagoefectivoVentaTelefonica?eticketPagoefectivo='+ CIP + '',
								type : 'get',
								success : function(rsp) {
									// console.log(rsp);
									if (rsp.msgserver == "NO ENCONTRADO") {
										swal({
											title : rsp.msgserver,
											text : rsp.mensaje,// 'Espere 2
																// minutos,el
																// asiento será
																// liberado
																// automáticamente',
											type : "error",
										});
									} else if (rsp.msgserver == "SIN REGISTRO DE PAGO") {
										swal({
											title : rsp.msgserver,
											text : rsp.mensaje,// 'Espere 2
																// minutos,el
																// asiento será
																// liberado
																// automáticamente',
											type : "info",
										});
									} else if (rsp.msgserver == "PAGADO") {
										swal({
											title : rsp.msgserver,
											text : rsp.mensaje,// 'Espere 2
																// minutos,el
																// asiento será
																// liberado
																// automáticamente',
											type : "success",
										});

										$("#tablaventas").bootstrapTable(
												'refresh', {
													silent : true,
													url : 'listaPagoEfectivo',
													query : {
														Eticket : rsp.cip
													}
												});
										$("#codigoCIP").val(rsp.cip)
									}

								}
							});
				});


$("#btnreconfirmar").on("click", function(){   
	var codigocip = $("#codigoCIP").val();
	console.log(codigocip);
	if (codigocip.length > 0) { 
		$.ajax({
			url : "ReconfirmarPagoEfectivo?Eticket="+codigocip,
			dataType : 'json', 
			success : function(response) { 
				swal({
					title : rsp.msgserver,
					text : rsp.mensaje, 
					type : "success",
				});
			},
			error : function(error) { 
			} 
		});
	}
})
  