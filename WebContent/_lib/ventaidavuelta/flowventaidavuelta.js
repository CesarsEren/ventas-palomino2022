$(function(){
	
	$("#ventawizard").bootstrapWizard({
		onTabClick: function(tab, navigation, index) {
			return false;
		}
	});
	
	$("#tblPasajerosVuelta").on("click", ".eliminar-pasajero", function(){
		
		var nroAsiento = $(this).attr("data-nroAsiento");
		var id = $(this).attr("data-id");
		
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
				  $('#hidContador').val($('#hidContador').val()-1);
			    $.ajax({
			    	type	: "get",
			    	url		: "quitarpasajerovuelta",
			    	data	: {numeroDocumento : id},
			    	success	: function(res){
			    		$("#tblPasajerosVuelta").bootstrapTable('removeByUniqueId', id);
			    		$("#AS-"+nroAsiento).prop("checked", false);
			    		($("#AS-"+nroAsiento).parent()).parent().removeClass("silla-seleccionada");
			    	}
			    });
			  } 
			})
	});
	
});



function acciones(value, row, index){
	var html = [];
	
	var id 			= row['id'];
	var nroAsiento 	= row['nroAsiento'];
	var nroPrecio 	= row['precio'];
	
	var btnEliminarPasajero = "<button type='button' data-nroPrecio="+nroPrecio+" data-nroAsiento="+nroAsiento+" data-id="+id+"  class='eliminar-pasajero btn btn-sm btn-danger'><i class='fa fa-times'></i></button>"
	
	html.push(btnEliminarPasajero);
	
	return html.join('');
}