<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="mapa">
	<div id="piso2"></div>
	<hr />
	<div id="piso1">
		<s:iterator value="lstMapa">
			<div class="silla"
				style="top: <s:property value="TTop/12"/>px; left: <s:property value="LLeft/15"/>px;">
				<div class="silla-numero" data-info="<s:property value="Asiento" />"
					data-precio="<s:property value="Precio" />">
					<s:property value="Asiento" />
					<input type="checkbox" name="asientoBus"
						id="AS-<s:property value="Asiento" />">
				</div>
			</div>
		</s:iterator>
	</div>
</div>

<div class="modal fade" id="modalPaso2" tabindex="-1" role="dialog"
	aria-labelledby="modalLabelPaso2">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="modalLabelPaso2">Ingrese los datos
					del pasajero</h4>
			</div>
			<div class="modal-body">
				<form id="frmPasajero">
					<div class="row">
						<div id="mensajeServerModal"></div>
					</div>
					<div class="row">
						<div class="col-xs-6">
							<label>Número asiento</label> <input
								name="ventaPaso2Form.asiento" type="text"
								class="form-control numeroAsiento" readonly="readonly" />
						</div>
						<div class="col-xs-6">
							<label>Precio</label> <input name="ventaPaso2Form.precioAsiento"
								type="text" class="form-control precioAsiento"
								readonly="readonly" />
						</div>
					</div>
					<div class="row">
						<div class="col-xs-6">
							<label>Identidad</label> <select
								name="ventaPaso2Form.documentoIdentidad"
								class="form-control documentoIdentidad">
								<option value="-1">[Seleccione]</option>
							</select>
						</div>
						<div class="col-xs-6">
							<label>Nro. Documento</label> <input
								name="ventaPaso2Form.numeroDocumentoIdentidad" type="text"
								class="form-control nroDocumento" maxlength="15">
						</div>
					</div>
					<div class="row">
						<div class="col-xs-6">
							<label>Nombre completo del pasajero</label> <input
								name="ventaPaso2Form.nombreCompleto" type="text"
								class="form-control nombreCompleto" />
						</div>
						<div class="col-xs-6">
							<label>Edad</label> <input name="ventaPaso2Form.edad" type="text"
								class="form-control edad" maxlength="2" />
						</div>
					</div>
					<input type="hidden" class="pasajeroSeleccionado"
						name="ventaPaso2Form.id" />
				</form>
			</div>
			<div class="modal-footer">
				<button id="btnActualizarPasajero" type="button"
					class="btn btn-primary">
					<i class="fa fa-check"></i>&nbsp;Actualizar pasajero
				</button>
				<button id="btnAgregarPasajero" type="button"
					class="btn btn-success">
					<i class="fa fa-plus"></i>&nbsp;Agregar pasajero
				</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
			</div>
		</div>
	</div>

</div>

<script>
	$(function(){

		var sillaChecked = "";
		
		$(".silla-numero").on("click", function(){
			$(".numeroAsiento").val($(this).attr("data-info"));
			$(".precioAsiento").val($(this).attr("data-precio"));
			$(this).parent().addClass("silla-seleccionada");
			
			sillaChecked = $(this).find("input");
			
			$("#mensajeServerModal").empty().removeClass();
			$("#btnActualizarPasajero").hide();
			$("#btnAgregarPasajero").show();
			
			$("#modalPaso2").modal("show");
		});
		
		$("#btnAgregarPasajero").on("click", function(){
			$.ajax({
				url 	: "agregarpasajero",
				type	: "post",
				data	: $("#frmPasajero").serialize(),
				success	: function(res){
					if(res.error){
						$("#mensajeServerModal").html(res.mensajeServer).addClass("alert alert-danger");
					}
					else{

						sillaChecked.prop("checked", true);
						$("#modalPaso2").modal("hide");
						$("#tblPasajeros").bootstrapTable('insertRow', 
							{index : 1, row : { id:  res.ventaPaso2Form.id, nroDocumento : $(".nroDocumento").val(), nombre: $(".nombreCompleto").val(), 
												nroAsiento : $(".numeroAsiento").val(), precio : $(".precioAsiento").val()}});
					}
				}
			});
		});
		
		$("#btnActualizarPasajero").on("click", function(){
			
			$.ajax({
				url 	: "actualizarpasajero",
				type	: "post",
				data	: $("#frmPasajero").serialize(),
				success	: function(res){
					if(res.error){
						$("#mensajeServerModal").html(res.mensajeServer).addClass("alert alert-danger");
					}
					else{
						$("#modalPaso2").modal("hide");
						$("#tblPasajeros").bootstrapTable('updateByUniqueId', 
							{id : $(".pasajeroSeleccionado").val(), 
							 row : { nroDocumento : $(".nroDocumento").val(), nombre: $(".nombreCompleto").val(), 
									 nroAsiento : $(".numeroAsiento").val(), precio : $(".precioAsiento").val()}});
					}
				}
			});
		});
		
		$("#modalPaso2").on('hidden.bs.modal', function(){
			$("#mensajeServerModal").empty().removeClass();
			$("#btnActualizarPasajero").hide();
			$("#btnAgregarPasajero").show();
			
			if(!sillaChecked.is(":checked")){
				(sillaChecked.parent()).parent().removeClass("silla-seleccionada");
			}
		});
	});
</script>