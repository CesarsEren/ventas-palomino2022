$(function(){
	function acciones(value, row, index){
		var html = [];
		
		var nroAsiento 		= row['nroAsiento'];
		var id = row['id'];
		
		var a = "'"+nroAsiento+","+id+"'";
		
		var btnEliminarPasajero = "<button type='button' style='width:25%;' onclick=borrarPasajero("+a.toString()+") class='btn btn-sm btn-danger'><i class='fa fa-times'></i></button>"
		
		var b = document.createElement("button");
		b.innerHTML = "<i class='fa fa-times'></i>";
		b.className = "btn btn-sm btn-danger";
		b.onclick = borrarPasajero(id, nroAsiento);
		
		html.push(btnEliminarPasajero);
		
		return html.join('');
	}

	function a(id, nroAsiento){
		
		return "'"+id+","+nroAsiento+"'";
	}
	
	function borrarPasajero(numeroDocumento){
		console.log(numeroDocumento);
		swal({
			  title: '¿Desea quitar al pasajero?',
			  text: "Por favor, confirme su acción.",
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'SI',
			  cancelButtonText: 'NO',
			  confirmButtonClass: 'btn btn-success',
			  cancelButtonClass: 'btn btn-danger',
			  buttonsStyling: false
			}).then(function(isConfirm) {
			  if (isConfirm === true) {
			    $.ajax({
			    	type	: "get",
			    	url		: "quitarpasajerovuelta",
			    	data	: {numeroDocumento : numeroDocumento.split(",")[0]},
			    	success	: function(res){
			    		$("#tblPasajerosVuelta").bootstrapTable('removeByUniqueId', numeroDocumento.split(",")[0]);
			    		($("#AS-"+numeroDocumento.split(",")[1]).parent()).parent().removeClass("silla-seleccionada");
			    	}
			    });
			  } 
			})
	}
});
