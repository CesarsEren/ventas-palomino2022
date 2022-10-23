
$(function(){
	
	$('#dateRangePickerVersion').on('click',function(){
		$("#fechaVersion").datepicker('show');
		
	});
	
	$("#fechaVersion").datepicker({
		language : 'es',
		autoclose: true,
		todayHighlight: true
	});
	
	
});


function responseHandler(res){
			return res.resultados;
}


function operacionesVersiones(id){
	var html = [];
	
	//var estado = estado['estado'];
	
	//var btnVerUsuario = "<a href='veragencia?id="+id+"' class='btn btn-sm btn-default'>VER</a>&nbsp;";
	var btnModificar  = "<a href='actualizaversiones?id="+id+"' class='btn btn-sm btn-info'>MODIFICAR</a>&nbsp;";
	var btnEliminar  = "<btn onclick=modalSwal('"+id+"') class='btn btn-sm btn-danger'>ELIMINAR</btn>&nbsp;";
	//var btnEstado;
	
	/*if(estado == "Y"){
		btnEstado = "<btn onclick=modalSwal('"+id+"') class='btn btn-sm btn-danger'>DESACTIVAR</btn>&nbsp;";
	}
	else{
		btnEstado = "<btn onclick=modalSwal('"+id+"') class='btn btn-sm btn-primary'>ACTIVAR</btn>&nbsp;";
	}*/
	
	//html.push(btnVerUsuario);
	html.push(btnModificar);
	html.push(btnEliminar);
	//html.push(btnEstado);
	
	return html.join('');
}

function modalSwal(valor){
	swal(
		{title: '¿Seguro desea eliminar esta Versión?',   
		 text:  'Por favor confirme su operación.',   
		 type:  'warning',   
		 showCancelButton: true,   
		 confirmButtonColor: '#3085d6',   
		 cancelButtonColor: '#d33', 
		 cancelButtonText: 'NO',
		 confirmButtonText: 'SÍ',   
		 closeOnConfirm: false 
		}).then(function(){
			document.getElementById("valor").value =  valor;
			document.getElementById("frmVersion").submit();
		},function(dismiss){
			if(dismiss == 'cancel'){
				return false;
			}
		});
}

