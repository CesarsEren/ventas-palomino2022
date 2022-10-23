$(function() {

	$("#tblPasajeros").bootstrapTable('refresh', {
		url : 'DetallePasajerosVisa?offset=0&limit=10'
	});
 
});


