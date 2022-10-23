

$(function(){
	
	
	$("#select2Pasajeros").select2({
		theme: "bootstrap",
		placeholder:"Seleccione un Pasajero",
		language: "es",
		data: {id:null, text: null},
			ajax: {
			    url: "pasajeroidavuelta",
			    dataType: 'json',
			    delay: 500, 
			    data: function (params) {
			    	return {
			    	searchPasajero: params.term, 
			        page: params.page
			      };
		    },
		    processResults: function (data, params) {
		      params.page = params.page || 1;
		      var miArray = [];
		      $.each(data.listaPasajerosIdaVueltaMapa, function(k,v){
		    	  if(v['visible'] == false){
		    		  miArray.push(v);
		    		}
		      })
		      return {
		        results: miArray,
		      };
		    },
		    cache: true
		},
	});
	
	/***************************************************************************************/
	
$("#select2Pasajeros").on("select2:select", function (e) {
		$('#select2Pasajeros option:selected').val();
		
	}); 
	
		
	
});