$(function(){
	
	$("#enero").datepicker({
		language : 'es',
		autoclose: true,
		startView: 'months',
		multidate: true,
		todayHighlight: true
	});
	
	$("#febrero").datepicker({
		language : 'es',
		autoclose: true,
		startView: 'months',
		multidate: true,
		todayHighlight: true
	});
	$("#marzo").datepicker({
		language : 'es',
		autoclose: true,
		startView: 'months',
		multidate: true,
		todayHighlight: true
	});
	
	$("#abril").datepicker({
		language : 'es',
		autoclose: true,
		startView: 'months',
		multidate: true,
		todayHighlight: true
	});
	
	$("#mayo").datepicker({
		language : 'es',
		autoclose: true,
		startView: 'months',
		multidate: true,
		todayHighlight: true
	});
	
	$("#junio").datepicker({
		language : 'es',
		autoclose: true,
		startView: 'months',
		multidate: true,
		todayHighlight: true
	});
	
	$("#julio").datepicker({
		language : 'es',
		autoclose: true,
		startView: 'months',
		multidate: true,
		todayHighlight: true
	});
	
	$("#agosto").datepicker({
		language : 'es',
		autoclose: true,
		startView: 'months',
		multidate: true,
		todayHighlight: true
	});
	
	$("#septiembre").datepicker({
		language : 'es',
		autoclose: true,
		startView: 'months',
		multidate: true,
		todayHighlight: true
	});
	
	$("#octubre").datepicker({
		language : 'es',
		autoclose: true,
		startView: 'months',
		multidate: true,
		todayHighlight: true
	});
	
	$("#noviembre").datepicker({
		language : 'es',
		autoclose: true,
		startView: 'months',
		multidate: true,
		todayHighlight: true
	});
	
	$("#diciembre").datepicker({
		language : 'es',
		autoclose: true,
		startView: 'months',
		multidate: true,
		todayHighlight: true
	});
	
	
	
});

function responseHandler(res){
	return res.resultados;
}

function operacionesProgramacionPagos(id){
	var html = [];
	
	var btnModificar  = "<a href='actualizaprogramacionpagos?Id="+id+"' class='btn btn-sm btn-info'>MODIFICAR</a>&nbsp;";
	var btnEstado;

	html.push(btnModificar);
	
	return html.join('');
}
