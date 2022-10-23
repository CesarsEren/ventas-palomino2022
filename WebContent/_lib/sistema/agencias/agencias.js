function responseHandler(res){
			return res.resultados;
}

function estadoAgencia(valor){
	var html = [];
	
	if(valor == "Y"){
		html.push("<label class='label label-primary'>");
		html.push ("ACTIVO");
	}else{
		html.push("<label class='label label-danger'>");
		html.push ("INACTIVO");
	}
	html.push("</label>");
	
	return html.join('');
}

function operacionesAgencia(id, estado){
	var html = [];
	
	var estado = estado['estado'];
	
	var btnVerUsuario = "<a href='veragencia?id="+id+"' class='btn btn-sm btn-default'>VER</a>&nbsp;";
	var btnModificar  = "<a href='actualizaagencia?id="+id+"' class='btn btn-sm btn-info'>MODIFICAR</a>&nbsp;";
	var btnEstado;
	
	if(estado == "Y"){
		btnEstado = "<btn onclick=modalSwal('"+id+"') class='btn btn-sm btn-danger'>DESACTIVAR</btn>&nbsp;";
	}
	else{
		btnEstado = "<btn onclick=modalSwal('"+id+"') class='btn btn-sm btn-primary'>ACTIVAR</btn>&nbsp;";
	}
	
	html.push(btnVerUsuario);
	html.push(btnModificar);
	html.push(btnEstado);
	
	return html.join('');
}

function modalSwal(valor){
	swal(
		{title: '¿Seguro desea cambiar el estado de la agencia?',   
		 text:  'Todos los usuarios de esta agencia se desactivaran.',   
		 type:  'warning',   
		 showCancelButton: true,   
		 confirmButtonColor: '#3085d6',   
		 cancelButtonColor: '#d33', 
		 cancelButtonText: 'NO',
		 confirmButtonText: 'SÍ',   
		 closeOnConfirm: false 
		}).then(function(){
			document.getElementById("valor").value =  valor;
			document.getElementById("frmAgencias").submit();
		},function(dismiss){
			if(dismiss == 'cancel'){
				return false;
			}
		});
}