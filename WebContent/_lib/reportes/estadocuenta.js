$(function(){
	
	
	$("#fechaInicial").datepicker({
		language : 'es',
		autoclose: true,
		//startDate: '0d',
		//todayHighlight: true
	});
	
	$("#fechaInicial").on("changeDate", function(){
		
		var ToEndDate = new Date();
		$("#fechaFinal").val("");
		$("#fechaFinal").prop("disabled", false);
		$("#fechaFinal").datepicker("destroy");
		
		$("#fechaFinal").datepicker({
			language 	: 'es',
			autoclose: true,
			startDate	: $("#fechaInicial").val(),
			
			});
		
	})
	
	$('#dateRangePickerEstadoIni').on('click',function(){
		$("#fechaInicial").datepicker('show');
		
	});
	$('#dateRangePickerEstadoFin').on('click',function(){
		$("#fechaFinal").datepicker('show');
		
	});
	
	$("input[name='estadocuenta']").on("change", function(){
		if($(this).val() == "true"){
			$('#rbtConsulta').val('H')
			$("#DivfechaEstadoCuenta").css('display','block');
		}
		else{
			$('#rbtConsulta').val('P')
			$("#fechaInicial").val("");
			$("#fechaFinal").val("");
			$('#tblEstadoCuenta').bootstrapTable('removeAll');
			$("#DivfechaEstadoCuenta").css('display','none');
		}
	});
});


$('#btnBuscarEstadoCuenta').on('click',function(){
	$('#tblEstadoCuenta').bootstrapTable('removeAll');
	$("#tblEstadoCuenta").bootstrapTable('refresh',
		{silent: true,
		 url :  'ListaEstadoCuentaCorriente', 
		 query : { FechaInicial : $('#fechaInicial').val(), 
			 	   FechaFinal : $('#fechaFinal').val(),
			 	   Tipo : $('#rbtConsulta').val(),
			 	   Usuario : $('#Usuario').val()
			 	  }
		}
	);
	
	

	
});

function queryParams(params){
	params['FechaInicial'] = $('#fechaInicial').val();
	params['FechaFinal'] = $('#fechaFinal').val();
	params['Tipo'] = $('#rbtConsulta').val();
	params['Usuario'] = $('#Usuario').val();
	return params;
}

function responseHandler(res){
	 
	 if(res.respuestaServer){
		 
		 BootstrapDialog.show({
			  	title :'Mensaje de Advertencia!',
			  	type : BootstrapDialog.TYPE_DANGER,
	            message: res.mensajeServer,
	            closable: false,
	            buttons: [{
	                label: 'OK',
	                cssClass: 'btn btn-danger',
	                action: function(event){
	                    event.close();
	                }
	            }]
	        });
		 return false;
		 
	 }
	 
	 console.log(res);
	 console.log(res.mapaJSONResultadoEstadoCuenta.rows);
	 if(res.mapaJSONResultadoEstadoCuenta.total>0){
		 $('#btnEstadoCuenta').prop('disabled',false);
		 
	}else{
		 $('#btnEstadoCuenta').prop('disabled',true);
		 
	 }
	 
	 return res.mapaJSONResultadoEstadoCuenta;
}