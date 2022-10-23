$(function(){
	
	$('#dateRangePickerDocumentos').on('click',function(){
		$("#fechaDocumentos").datepicker('show');
		
	});
	
	$("#fechaDocumentos").datepicker({
		language : 'es',
		autoclose: true,
		todayHighlight: true
		
	});
	
});

$('#btnConsultaDocumentos').on('click',function(){
	
	$('#tblDocumentosElectronicos').bootstrapTable('removeAll');
	$("#tblDocumentosElectronicos").bootstrapTable('refresh',
		{silent: false,
		 height: 350,
		 url :  'cosultadocumentosenviados', 
		 query : {
				 	empresa: $('#empresa').val(),
					fechaemision: $('#fechaDocumentos').val(),
					serie: $('#serie').val(),
					numero: $('#numero').val(),
					tipodocumento:  $('#tipodocumento').val(),
					ruc : $('#ruc').val()
			 	  }
		}
	);
});

$('#tipodocumento').on('change', function() {
	if(this.value=='01' || this.value=='03' || this.value=='05' || this.value=='06'){
		$('#divFactura').css({"display":"block"});
		$('#serie').attr("placeholder", "000");
	}else{
		$('#divFactura').css({"display":"none"});	
		$('#serie').attr("placeholder", "000");
	}
})

function queryParams(params){
	params['empresa'] = $('#empresa').val();
	params['fechaemision'] = $('#fechaDocumentos').val();
	params['serie'] = $('#serie').val();
	params['numero'] = $('#numero').val();
	params['tipodocumento'] = $('#tipodocumento').val();
	params['ruc'] = $('#ruc').val();
	return params;
}

function responseHandlerDocumentos(res){
	if(!res.respuestaServer){
		
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
return res.mapaJSONResultadoIDA;
}


function FormatterEstado(valor){
	var html = [];
	if(valor == "S"){
		html.push("<span class='glyphicon glyphicon-ok' style='color:#5cb85c;'></span>");
	}else{
		html.push("<span class='glyphicon glyphicon-remove' style='color:#FF0000;'></span>");
	}
	html.push("</label>");
	
	return html.join('');
}

function RucExistente(event){
	
	var ruc=event.value.trim();
	var razon='';
	
	if(ruc!=''){
		
			$.ajax({
					url : 'ClientesRUC',
				 	data : { RUC : ruc },
				 	type : 'GET',
				 	dataType : 'json',
			success : function(json) {
				 		if(json.respuestaAjaxCliente){
					 		razon =json.beancliente['razon'];
							Habilitar_Componentes_Cliente(true,razon);
					 	}else{
					 		Habilitar_Componentes_Cliente(false,razon);
					 	}
				    },
			error : function(xhr, status) {
			        	alert('Disculpe, existió un problema al realizar la petición al servidor.');
			    },
		});
	}else{
		Habilitar_Componentes_Cliente(false,'');
	}
}

function Habilitar_Componentes_Cliente(enabled,razon){
	$('#razon').val(razon);
}
function validarSoloNumerosEnteros(e) {
    return tecla = document.all ? e.keyCode : e.which, 8 == tecla || (patron = /[0123456789]/, te = String.fromCharCode(tecla), patron.test(te))
}
function AumentarCerosNumero(e) {
    var r = e.value,
        t = 7 - r.length,
        o = "0000000",
        n = "";
    if (parseInt(r) < 0) return n = o, void $("#numero").val(n);
    n = 7 == t ? r : String(o).substring(0, t) + r, $("#numero").val(n)
}

function AumentarCerosSerie(e) {
    var r = e.value,
        t = 3 - r.length,
        o = "";
    if (parseInt(r) < 0) return o = "000", void $("#serie").val(o);
    o = 3 == t ? r : String("000").substring(0, t)+ r, $("#serie").val(o)
}

/*function AumentarCerosNumero(event){
	
	var numero= event.value;
	var tamanio = 7-(numero.length);
	var ceros = '0000000';
	var resultado='';
	
	
	if(numero==''){
		resultado=ceros;
		$('#numero').val(resultado);
		return;
	}
	
	if(parseInt(numero)<0){
		resultado=ceros;
		$('#numero').val(resultado);
		return;
	}
	
	if (tamanio == 7){
		resultado = numero;
	}
	else{
		resultado = String(ceros).substring(0,tamanio)+numero;
	}
	
	$('#numero').val(resultado);
	
}
function AumentarCerosSerie(event){
	
	var numero= event.value;
	var tamanio = 3-(numero.length);
	var ceros = '000';
	var resultado='';
	
	
	if(numero==''){
		resultado=ceros;
		$('#serie').val(resultado);
		return;
	}
	
	if(parseInt(numero)<0){
		resultado=ceros;
		$('#serie').val(resultado);
		return;
	}
	
	if (tamanio == 3){
		resultado = numero;
	}
	else{
		resultado = String(ceros).substring(0,tamanio)+numero;
	}
	
	$('#serie').val(resultado);
	
}*/

function FormatterDescargar(value,row){
	
	var html = [];
	
	if(row.enviado == 'S'){
		var btnXML = "<a href='457e5f18b6ae40ca920770214f?empresa="+row.empresa+"&nro="+row.id+"&tipoOperacion="+row.servicio+"' class='btn btn-sm btn-warning'>XML</a>&nbsp;<a href='457e5f18b6ae40ca920770214c?empresa="+row.empresa+"&nro="+row.id+"&tipoOperacion="+row.servicio+"' class='btn btn-sm btn-warning'>CDR</a>&nbsp;";
		html.push(btnXML);
		
	}
	
	if(row.servicio == 'C' || row.servicio == 'N' || row.servicio == 'T'){
		
		var btnPDFNormal = "<a href='ccdf8a28d6554fe3af3cc0aa84?empresa="+row.empresa+"&nro="+row.id+"&tipoOperacion="+row.servicio+ "' class='btn btn-sm btn-primary'>PDF A-4</a>&nbsp;";
		html.push(btnPDFNormal);
		
	}else if (row.servicio == 'B' || row.servicio == 'E'){
		
		var btnPDFTicket = "<a href='80ddc3cbfe7d45aa8ae95e87ed?empresa="+row.empresa+"&nro="+row.id+"&tipoOperacion="+row.servicio+"' class='btn btn-sm btn-danger'>PDF TICKET</a>&nbsp;";
		html.push(btnPDFTicket);	
	}

	return html.join('');
}






