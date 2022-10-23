$(function(){
	
	$('#datelunesdesde').datetimepicker({
	    format: 'hh:ii',
	    autoclose:true,
	    startView : 0,
	    language : 'en'
	});
	
	$('#dateluneshasta').datetimepicker({
	    format: 'hh:ii',
	    autoclose:true,
	    startView : 0,
	    language : 'en'
	});
	
	$('#datemartesdesde').datetimepicker({
	    format: 'hh:ii',
	    autoclose:true,
	    startView : 0,
	    language : 'en'
	});
	
	$('#datemarteshasta').datetimepicker({
	    format: 'hh:ii',
	    autoclose:true,
	    startView : 0,
	    language : 'en'
	});
	
	$('#datemiercolesdesde').datetimepicker({
	    format: 'hh:ii',
	    autoclose:true,
	    startView : 0,
	    language : 'en'
	});
	
	$('#datemiercoleshasta').datetimepicker({
	    format: 'hh:ii',
	    autoclose:true,
	    startView : 0,
	    language : 'en'
	});
	
	$('#datejuevesdesde').datetimepicker({
	    format: 'hh:ii',
	    autoclose:true,
	    startView : 0,
	    language : 'en'
	});
	
	$('#datejueveshasta').datetimepicker({
	    format: 'hh:ii',
	    autoclose:true,
	    startView : 0,
	    language : 'en'
	});
	
	$('#dateviernesdesde').datetimepicker({
	    format: 'hh:ii',
	    autoclose:true,
	    startView : 0,
	    language : 'en'
	});
	
	$('#datevierneshasta').datetimepicker({
	    format: 'hh:ii',
	    autoclose:true,
	    startView : 0,
	    language : 'en'
	});
	
	$('#datesabadodesde').datetimepicker({
	    format: 'hh:ii',
	    autoclose:true,
	    startView : 0,
	    language : 'en'
	});
	
	$('#datesabadohasta').datetimepicker({
	    format: 'hh:ii',
	    autoclose:true,
	    startView : 0,
	    language : 'en'
	});
	
	$('#datedomingodesde').datetimepicker({
	    format: 'hh:ii',
	    autoclose:true,
	    startView : 0,
	    language : 'en'
	});
	
	$('#datedomingohasta').datetimepicker({
	    format: 'hh:ii',
	    autoclose:true,
	    startView : 0,
	    language : 'en'
	});
	
	
	
});

function responseHandler(res){
	return res.resultados;
}

function operacionesUsuarioHorarios(id){
	var html = [];
	
	var btnModificar  = "<a href='actualizausuariohorarios?Id="+id+"' class='btn btn-sm btn-info'>MODIFICAR</a>&nbsp;";
	var btnEstado;

	html.push(btnModificar);
	
	return html.join('');
}
