
$(function() {

	$('#hidTiporeclamoTexto').val($("#lstReclamo option:selected").text());
	$('#hidTiporeclamo').val($("#lstReclamo option:selected").val());

	$('#lstReclamo').change(function() {
		$('#hidTiporeclamo').val($(this).val());

		$("#lstReclamo option:selected").each(function() {
			$('#hidTiporeclamoTexto').val($(this).text());
		});
	});
	$("#crearexcel").css("display", "inline-block");
	$("#loader").css("display", "none");
	$("#descargarexcel").css("display", "none");
	$("#crearexcel").on("click", function() {

		$.ajax({
			url : 'generarexcel',
			type : 'get',
			beforeSend: function () {
				$("#loader").css("display", "inline-block");
				$("#crearexcel").css("display", "none");
				$("#responseexcel").text("Cargando Información a Excel");
				$("#responseexcel").css("color","blue");
				
			},
			success : function(response) {
				$("#responseexcel").text(response.msgserver);
				$("#crearexcel").css("display", "none");
				$("#descargarexcel").css("display", "inline-block");
				$("#responseexcel").text("Excel Actualizado");
				$("#loader").css("display", "none");
				window.open("descargarreclamos");
			}
		});

	});

});

function responseHandler(res) {
	console.log(res);
	return res.reclamoinformes;
}

function FormatterReporte(valor) {
	var html = [];

	if (valor == "S") {
		// html.push("<span class='span'
		// style='color:#5cb85c;'>Atendido</span>");
		html
				.push("<span style='color:#009740;font-weight: bold;'>ATENDIDO</span>");
	} else if (valor == "N") {
		html
				.push("<span style='color:#E30613;font-weight: bold;'>NO ATENDIDO</span>");
		// html.push("<span class='span'
		// style='color:#FF0000;'>Pendiente</span>");
	} else {
		html
				.push("<span style='color:#005C89;font-weight: bold;'>PENDIENTE</span>");
	}

	return html.join('');
}

/*
 * function operacionesReclamos(Id,atendido){ var html = [];
 * 
 * var atendido = atendido['atendido'];
 * 
 * var btnReclamo = "<btn onclick=modalSwal('"+Id+"') class='btn btn-sm
 * btn-primary'>Confirmar Atención</btn>";
 * 
 * if(atendido == "N"){
 * 
 * html.push(btnReclamo); }
 * 
 * 
 * return html.join(''); }
 * 
 * function modalSwal(valor){
 * 
 * 
 * $('#hidId').val(valor);
 * 
 * 
 * swal( {title: '¿Seguro desea cambiar el estado del reclamo?', text: 'Por
 * favor confirme su operación.', type: 'warning', showCancelButton: true,
 * confirmButtonColor: '#3085d6', cancelButtonColor: '#d33', cancelButtonText:
 * 'NO', confirmButtonText: 'SÍ', closeOnConfirm: false }).then(function(){
 * 
 * document.getElementById("frmEstadoReclamos").submit(); },function(dismiss){
 * if(dismiss == 'cancel'){ return false; } }); }
 */
/*
 * $('#btnReenviarCorreo').on('click',function(){
 * 
 * if($('#txtReenvioCorreo').val()==''){ $('#mensajecorreo').removeClass();
 * $('#mensajecorreo').addClass("alert alert-danger");
 * $('#mensajecorreo').html("Por favor ingrese su correo."); return; }else{
 * $('#mensajecorreo').removeClass(); $('#mensajecorreo').html(''); }
 * 
 * 
 * 
 * 
 * $.ajax({ url : 'reenviocorreoatencionreclamos', type : 'GET', data : {correo :
 * $('#txtReenvioCorreo').val(),periodo : $('#hidPeriodo').val(),nro :
 * $('#hidNroR').val(),empresa : $('#hidEmpresa').val()}, beforeSend: function
 * (response) { $('#CargaReCorreo').css({display:'block'});}, success :
 * function(response){ $('#CargaReCorreo').css('display','none');
 * $('#txtReenvioCorreo').val(''); if(response.respuestaServer){
 * $('#mensajecorreo').addClass("alert alert-success");
 * $('#mensajecorreo').html(response.mensajeServer) }else{
 * $('#mensajecorreo').addClass("alert alert-danger");
 * $('#mensajecorreo').html(response.mensajeServer) }
 *  } });
 * 
 * 
 * 
 * });
 */
