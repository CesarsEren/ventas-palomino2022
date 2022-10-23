$(function(){
	
	$('.your-clock').FlipClock(900, {
		countdown: true,
		language: 'es',
		clockFace: 'MinuteCounter'
	});
	
	
	$("#ventawizard").bootstrapWizard({
		onTabClick: function(tab, navigation, index) {
			return false;
		}
	});
	
	$("#select2Origen").select2({theme: "bootstrap",placeholder:"Por favor espere...",language: "es"})
	$("#select2Destino").select2({theme: "bootstrap",placeholder:"Por favor espere...",language: "es"})
	
	$.ajax({
		url: "destinoscombo",
	    dataType: 'json',
	    delay: 500, 
	    data:{origenCiudad:"001",destinoCiudad:"003"},
	    success: function(response){
	    		$('#idorigendescripcion').val(response.listaOrigenCombo[0].text);
	    		$('#iddestinodescripcion').val(response.listaDestinoCombo[0].text);
	    	$("#select2Origen").select2({
	    		theme: "bootstrap",
	    		placeholder:"Seleccione Origen",
	    		language: "es",
	    		data:response.listaOrigenCombo
	    		
	    	})
	    	$("#select2Destino").select2({
	    		theme: "bootstrap",
	    		placeholder:"Seleccione Destino",
	    		language: "es",
	    		data:response.listaDestinoCombo
	    		
	    	})
	    	
	    },error: function (error) {
            $("#select2Origen").select2({theme: "bootstrap",placeholder:{id:'-1',text:'No se encontraron resultados'},language: "es"});
            $("#select2Destino").select2({theme: "bootstrap",placeholder:{id:'-1',text:'No se encontraron resultados'},language: "es"});
        }
		
	});
	
	$('#dateRangePickerIda').on('click',function(){
		$("#fechaIda").datepicker('show');
		
	});
	$('#dateRangePickerVuelta').on('click',function(){
		$("#fechaVuelta").datepicker('show');
		
	});
	
	$("#fechaIda").datepicker({
		language : 'es',
		autoclose: true,
		startDate: '0d',
		todayHighlight: true
		
	}); 
	$("#fechaIda").datepicker('setDate', 'now');
	
	$("#fechaIda").on("changeDate", function(){
		
		$("#fechaVuelta").val("");
		$("#fechaVuelta").prop("disabled", false);
		$("#fechaVuelta").datepicker("destroy");
		
		$("#fechaVuelta").datepicker({
			language 	: 'es',
			autoclose: true,
			startDate	: $("#fechaIda").val()
		});
	})
	
	$("input[name='paso1Form.idaVuelta']").on("change", function(){
		if($(this).val() == "true"){
			if(!$("#fechaIda").val()== ''){
				$("#fechaVuelta").prop("disabled", false);
				$("#fechaVuelta").datepicker({
					language 	: 'es',
					autoclose: true,
					startDate	: $("#fechaIda").val()
				});
			}else{
				$("#fechaVuelta").prop("disabled", true);
			}
			$(".fechaVueltaDiv").show();
			$('#liPaso4').css({display:'block'});
			
		}
		else{
			$(".fechaVueltaDiv").hide();
			$('#liPaso4').css({display:'none'});
		}
	});
});

$("#select2Origen").on("select2:select", function (e) {
	var origen = $('#select2Origen option:selected').text().split("-");
	$('#idorigendescripcion').val(origen[0]);
}); 

$("#select2Destino").on("select2:select", function (e) {
	var destino = $('#select2Destino option:selected').text().split("-");
	$('#iddestinodescripcion').val(destino[0]);
}); 
