

$(function(){
	
	
	 $("#frm-envio").submit(function (event) {

	        //stop submit the form, we will post it manually.
	        event.preventDefault();

	       fire_ajax_submit();
	        
	       
	 
	
});
	 
});


function FormatterEstadoEnvio(valor){
	var html = [];
	if(valor == "S"){
		html.push("<span class='glyphicon glyphicon-ok' style='color:#5cb85c;'></span>");
	}else{
		html.push("<span class='glyphicon glyphicon-remove' style='color:#FF0000;'></span>");
	}
	html.push("</label>");
	
	return html.join('');
}


function getDataTable (){
	
	
	$('#btnCargar').on('click',function(){
		
		
		console.log($("#tblDocumentos").bootstrapTable('getData'));
		
		
	});
	
	
}



function fire_ajax_submit() {

  

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/documentos/enviosunat",
        //data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (res) {

            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(res, null, 4) + "</pre>";
           

            console.log("SUCCESS : "+ res.rows);
            
            $("#tblDocumentos").bootstrapTable('removeAll');
            
            $.each(res.rows, function (id, value) {
            	  console.log(value.tipodocumento);
            	  
            	  // Will stop running after "three"
            	  //return (value !== 'three');
            	  
            	  $("#tblDocumentos").bootstrapTable('insertRow', 
      					{index :id , row : { tipodocumento:  value.tipodocumento, documento : value.documento,
      										fechaemision : value.fechaemision,
      										servicio : value.servicio,
      										estado :value.estado}});
            	  
            	  
            	  
            });
            
            
           /* $("#tblDocumentos").bootstrapTable('insertRow', 
					{index : 1, row : { tipodocumento:  res.rows.tipodocumento, documento : res.rows.documento,
										fechaemision : res.rows.fechaemision,
										servicio : res.rows.servicio,
										estado :res.rows.estado}}); */

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);

        }
    });
	
/*	$("#tblDocumentos").bootstrapTable('refresh').bootstrapTable({
		pageSize: 10,
		showColumns: true,
		height: 260,
		locale: "es-ES",
			url: "/documentos/generadocumentos",
			queryParams: function(params){
				params['Fecha'] = '';
				params['destino'] = '';
				return params;
			},
			onCheck: function(row, element){
				
					$('#txthiden').val('1');
					
					var nroDestino			= $('#select2Destino').val();
					var nroProgramacion 	= row['nro'];
					var nroBus 				= row['bus'];
					var nroServicio         = row['servicio'];
					
					$('#hidnroProgramacionIda').val(row['nro']);
					$('#hidnroBusIda').val(row['bus']);
					$('#hidnroServicioIda').val(row['servicio']);
					$('#hidDescServicioIda').val(row['servicioD'])
					$('#hidHora1Ida').val(row['hora1'])
					//$('#lblDetalle').val(row['servicioD']);
					//console.log($('#lblDetalle').val());
			},
			onClickRow: function(row, element, field){
				//element.addClass("alert-success").siblings().removeClass('alert-success');
				$('#title-bus').val(row['servicioD']);
				console.log($('#title-bus').val());
			},
			onUncheck: function(row,element){
				$('#txthiden').val("");
				$('#hidnroProgramacionIda').val("");
				$('#hidnroBusIda').val("");
				$('#hidnroServicioIda').val("");
			},
			responseHandler: function(res){
				console.log(res);
				return res['rows'];
			}
	});*/

}

