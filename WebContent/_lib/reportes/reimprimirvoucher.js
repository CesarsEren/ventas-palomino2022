$(function(){
	
	$("#tblReimprimirVoucher").bootstrapTable('refresh',
		{silent: true,
		 url :  'ListaBoletoReservadoGrid', 
		 query : { FechaInicial : $('#fechaInicial').val(), 
			 	   FechaFinal : $('#fechaFinal').val(),
			 	   Tipo : $('#rbtConsulta').val()
			 	  }
		}
	);
	
	$("#tblReimprimirVoucher").on('check.bs.table',
		function(row, element){
			$('#hidenselect').val('1');
			$('#txtNrovoucher').val( element['nro']);
			$('#txtSalida').val( element['salida']);
			$('#txtUsuario').val( element['Usuario']);

		}
	);
	$("#tblReimprimirVoucher").on('uncheck.bs.table',
			function(row, element){
			$("#hidenselect").val('-1')
			}
		);
	
});

function responseHandler(res){
	 return res.mapaJSONResultadoBoletos;
}

function Imprimir(){
	
	if($("#hidenselect").val()=='-1'){
		BootstrapDialog.show({
			  	title :'Mensaje de Advertencia!',
			  	type : BootstrapDialog.TYPE_DANGER,
	            message: 'Por favor Seleccione un Documento',
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
		
	}else{
		document.getElementById("formImprimir").action = 'reimprimirvoucher';
		document.getElementById("formImprimir").submit();
		
		return true;
	} 
}