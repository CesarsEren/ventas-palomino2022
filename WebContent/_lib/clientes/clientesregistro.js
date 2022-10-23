
$('#btnRegistraCliente').on('click',function(){
	$.ajax({
		url  : 'accederegistroconfirmacion',
		type : 'get',
		data : $('#formRegistraCliente').serialize(),
		beforeSend: function (response) {
					$('#cargaRegistroCliente').css({display:'block'});},
		success   : function(response){
				if(response.errorserver){
					$('#cargaRegistroCliente').css({display:'none'});
					$("#mensajeserver").empty().removeClass();
					$("#mensajeserver").html(response.mensajeServer).addClass("alert alert-danger");
				}else{
					$('.nombres').val('');
		      		$('.apellidos').val('');
		      		$('.telefono').val('');
		      		$('.documento').val('');
		      		$('.email').val('');
		      		$('.confirmemail').val('');
		      		$('.pass').val('');
		      		$('.confirmpass').val('');
					$('#cargaRegistroCliente').css({display:'none'});
					$("#mensajeserver").empty().removeClass();
					$("#mensajeserver").html(response.mensajeServer).addClass("alert alert-success");
					
				}
					
			}
	});
	
});
