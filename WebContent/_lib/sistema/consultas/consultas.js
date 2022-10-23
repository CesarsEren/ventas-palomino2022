$("#btnBusqueda").on("click", function(){
		$("#tablaConsulta").bootstrapTable('refresh',
								{	silent: true,
								 	url :  'consultavisa', 
									query : { eticket : $('#query').val()}
								
								}
		);
	})

function responseHandler(res){
	console.log(res);
	var arreglo = [];
	arreglo.push(res.rows);
	return arreglo;
}

$("#btnBusquedaPagoEfectivo").on("click", function(){
	$("#tablaConsultaPagoEfectivo").bootstrapTable('refresh',
							{	silent: true,
							 	url :  'consultapagoefectivo', 
								query : { eticketPagoefectivo : $('#query').val()}
							
							}
	);
})

function responseHandlerPagoEfectivo(res){
	console.log(res);
	var arreglo = [];
	arreglo.push(res.rowsPagoefectivo);
	return arreglo;
}