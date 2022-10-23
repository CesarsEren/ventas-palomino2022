function responseHandler(res){
	
	return res.rows;
}

function Formatter(value,row){
	
var html = [];
	if(row['Canjeado'] == "S"){
		
	}else{
		html.push("<label class='label label-danger' >");
		html.push("<a href='canjeavoucher?NroVoucher="+row['Nro']+"-"+row['Salida']+"' style='color:white;'>IMPRIMIR");
		html.push("</a>");
		html.push("</label>");
	}
	return html.join('');

}

$('#recupera').on('click',function(){
	
	 BootstrapDialog.show({
		 type : BootstrapDialog.TYPE_SUCCESS,
        title: 'Olvidaste tu Contraseña!',
        message: $('<div id="mensaje"></div>' +
       		 	'<p>Ingresa tu dirección de correo para restaurarla.</p>' +
       		 	'<br><input id="txtEnviar" type="text" required="required" class="form-control" placeholder="ingrese su correo" autofocus/>' +
       		    '<br><div class="row">'+
       		    	'<div class="col-md-3">'+
       		    		'<div id="cargaCorreo" style="display:none">'+
       		    			'<img src="_lib/gif/ajax-loader-recupera-correo.gif"/>'+
       		    		'</div>'+
       		    	'</div>'+
       		 	'</div>'),
       buttons: [{
            label: 'Enviar',
            cssClass: 'btn-success',
            hotkey: 13, // Enter.
            action: function(dialog) {
           	 
           	 $.ajax({
					url  : 'recuperaclave',
					type : 'get',
					data : {correo : $('#txtEnviar').val()},
					beforeSend: function (response) {
								$('#cargaCorreo').css({display:'block'});},
					success   : function(response){
								$('#cargaCorreo').css('display','none');
							if (response.resultado){
	 								$("#mensaje").empty().removeClass();
									$("#mensaje").html(response.mensaje).addClass("alert alert-success");
									setTimeout(function() {
										$("#mensaje").hide();
										dialog.close();
									},4000);
									
							}else{
								$('#cargaCorreo').css('display','none');
								$("#mensaje").empty().removeClass();
								$("#mensaje").html(response.mensaje).addClass("alert alert-danger");
								
							}
				    	}
				});
           	 
            }
        }]
    });
	
	
});

$('#registrarse').on('click',function(){
	$.ajax({
			url  : 'paginaregistrocliente',
			type : 'get',
			success   : function(response){
						$('#modalPagina').html(response);
						$('#modalRegistro').modal('show');
				}
		});
	
});