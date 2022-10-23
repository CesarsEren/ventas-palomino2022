$(function(){
	

	$("#tblProgramacionSalidaIda").bootstrapTable('refresh').bootstrapTable({
		pageSize: 10,
		showColumns: true,
		height: 260,
		locale: "es-ES",
			url: "ListaProgramacionSalidaGridIda",
			queryParams: function(params){
				params['Fecha'] = $('#idMantFecha').val();
				params['destino'] = $('#select2Destino').val();
				return params;
			},
			onCheck: function(row, element){
				
					$('#txthiden').val('1');
					
					var nroDestino			= $('#select2Destino').val();
					var nroProgramacion 	= row['nro'];
					var nroBus 				= row['bus'];
					var nroServicio         = row['servicio'];
					
					$('#hidnroProgramacionIda').val(row['nro']);
					$('#hidnroDestinoIda').val(row['destino']);
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
                                //add for Cesars
                                $('#infoinkaida').css("display", "none");
                                $('#infochasquiida').css("display", "none");
                                $('#infoplusida').css("display", "none");
                                //arriba primero Eliminar Luego observar
                                switch($('#title-bus').val())
                                {case 'INKA CLASS':
                                $('#infoinkaida').css("display", "block");
                                break;
                                case 'CHASQUI BUS':
                                $('#infochasquiida').css("display", "block");
                                break;
                                case 'INTI PLUS':
                                $('#infoplusida').css("display", "block");
                                break;
                        }

				//console.log($('#title-bus').val());
			},
			onUncheck: function(row,element){
				$('#txthiden').val("");
				$('#hidnroProgramacionIda').val("");
				$('#hidnroDestinoIda').val("");
				$('#hidnroBusIda').val("");
				$('#hidnroServicioIda').val("");
                                //add for Cesars
                                $('#infoinkaida').css("display", "none");
                                $('#infochasquiida').css("display", "none");
                                $('#infoplusida').css("display", "none");
			},
                        
                     
			responseHandler: function(res){
				return res.mapaJSONResultadoIDA.rows;
			}
	});

	$("#tblProgramacionSalidaIdaVuelta").bootstrapTable('refresh').bootstrapTable({
		
		pageSize: 10,
		showColumns: true,
		height: 250,
		locale: "es-ES",
		silent: true,
		onCheck: function(row, element){
			
			$('#txthidenVuelta').val('1');
			
			element.addClass("alert-success").siblings().removeClass('alert-success');
			
			var nroDestino			= $('#idSelectDestino').val();
			var nroProgramacion 	= row['nro'];
			var nroBus 				= row['bus'];
			var nroServicio         = row['servicio'];
			
			
			$('#hidnroProgramacionIdaVuelta').val(row['nro']);
			$('#hidnroDestinoIdaVuelta').val(row['destino']);
			$('#hidnroBusIdaVuelta').val(row['bus']);
			$('#hidnroServicioIdaVuelta').val(row['servicio']);
			$('#hidDescServicioVuelta').val(row['servicioD']);
			$('#hidHora1Vuelta').val(row['hora1']);
		},
		onClickRow: function(row, element, field){
			$('#txthidenVuelta').val('1');
			$('#hidDescServicioVuelta').val(row['servicioD']);
                        //Primero Eliminar
                                $('#infoinkavuelta').css("display", "none");
                                $('#infochasquivuelta').css("display", "none");
                                $('#infoplusvuelta').css("display", "none");
                        //
                        //console.log($('#hidDescServicioVuelta').val());
                         switch($('#hidDescServicioVuelta').val())
                                {case 'INKA CLASS':
                                $('#infoinkavuelta').css("display", "block");
                                break;
                                case 'CHASQUI BUS':
                                $('#infochasquivuelta').css("display", "block");
                                break;
                                case 'INTI PLUS':
                                $('#infoplusvuelta').css("display", "block");
                                break;}
		},
		onUncheck: function(row,element){
                    $('#hidDescServicioVuelta').val(row['servicioD']);
			$('#txthidenVuelta').val("");
			$('#hidnroProgramacionIdaVuelta').val("");
			$('#hidnroDestinoIdaVuelta').val("");
			$('#hidnroBusIdaVuelta').val("");
			$('#hidnroServicioIdaVuelta').val("");
                        //add for Cesars
                          $('#infoinkavuelta').css("display", "none");
                          $('#infochasquivuelta').css("display", "none");
                          $('#infoplusvuelta').css("display", "none");
		},     
		url: "ListaProgramacionSalidaGridIdaVuelta",
		queryParams: function(params){
			params['Fecha'] = $('#idMantFechaVuelta').val();
			params['destino'] = $('#idSelectDestino').val();
			return params;
		},
		responseHandler: function(res){
			return res.mapaJSONResultadoVUELTA.rows;
		}
	});
	
	
});

function FormatterDescuento1(value,row) {
	if(row['descuento1']=='S'){
		return '<label class="oferta-compra"> '+value+'</label>';
	}else{
		return value;
	}
	
}

function FormatterDescuento2(value,row) {
	if(row['descuento2']=='S'){
		return '<label class="oferta-compra"> '+value+'</label>';
	}else{
		return value;
	}
}

function mostrarDetalle (servicioD,servicio){
	
	var html = [];
	
	//console.log(servicioD);
	//console.log(servicio['servicio']);
	
	Detalle = '<a onclick="MostrarFotos('+(servicio['servicio'])+');" href="#" title=" Ver Fotos">'+servicioD+
				'<span class="glyphicon glyphicon-picture"></span>'+
			 '</a>';
			
	html.push(Detalle);
	return html.join('');
	
}

function MostrarFotos(servicio){
	
	//alert(z);
	
	//$('#lblDetalle').val($('#hidDescServicioIda').val());
	//alert($('#hidnroServicioIda').val());
	//alert(servicio);
	console.log(servicio);
	//console.log(String(servicio).length);
	
//	if(servicio == '02'){
		
		var src5 = [
		    		'_lib/img/002.jpg'
		    	/*	'./g05/011.jpg','./g05/002.jpg',
		    		'./g05/004.jpg','./g05/007.jpg',
		    		'./g05/005.jpg','./g05/012.jpg',
		    		'./g05/003.jpg' */
		    		];				
		    		
		    /*	var src6 = [
		    		['./g06/001.jpg','Photo 2014'],		
		    		['./g06/003.jpg','Photo 1'],['./g06/009.jpg','Photo 2'],
		    		['./g06/006.jpg','Photo 3'],['./g06/002.jpg','Photo 4'],
		    		['./g06/008.jpg','Photo 5'],['./g06/010.jpg','Photo 6'],
		    		['./g06/007.jpg','Photo 7'],['./g06/011.jpg','Photo 8'],
		    		['./g06/005.jpg','Photo 9']
		    		]; */				
		    	
		    	
		    	$('#divCuerpoFotos').onebook(src5);
		    		
		    /*	$('.links a:eq(0)').click(function(){
		    		 $.onebook(src5,{startPage:3, pageColor:'#888888', skin:'light', cesh:false});
		    		 return false;
		    	});
		    	
		    	$('.links a:eq(1)').click(function(){
		    		 $.onebook(src5,{slope:1, bgDark:'#222222 url("./img/002.jpg");background-size:cover;', cesh:false});
		    		 return false;
		    	});  */	
		    	
		    /*  	$('.links a:eq(2)').click(function(){
		    		 $.onebook(src6,{border:100,bgDark:'#777777', language:'ru', cesh:false});
		    		 return false;
		    	}); */	
		      

		//alert('ok')
		
//	}
	$("#modalImagenes").modal("show");
}







