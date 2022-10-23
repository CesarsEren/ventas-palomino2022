<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div class="modal fade" id="modalReclamoConfirmado" tabindex="-1"
	role="dialog" aria-labelledby="modalLabelPaso2">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header"
				style="background: linear-gradient(#33AD45, #327d3d), #D5DC93; color: white;">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="modalLabelPaso2">Su reclamo ha sido
					enviado satisfactoriamente</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<!--  <input type="hidden" id="hidrefresh" value="0"> -->

				</div>
				<div class="row">
					<div id="mensajeserver"></div>
				</div>
				<div class="row" style="padding-left: 5px;">
					<form method="post" id="frmReclamoImprimir" autocomplete="off"
						accept-charset="UTF-8">
						<input type="hidden" id="hidPeriodo" name="reclamos.Periodo">
						<div class="col-md-11">
							<div class="row">
								<div class="form-group col-xs-4">
									<label>Numero de Reclamo:</label>

								</div>
								<div class="form-group col-xs-1">
									<div
										style="font-weight: bold; text-align: center; font-size: 20px; background: #009045; color: white;">
										<s:label name="reclamos.Nro"></s:label>

									</div>

								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-4">
									<label>Fecha de Reclamo:</label>

								</div>
								<div class="form-group col-xs-3">
									<s:label name="reclamos.Nro" value="%{reclamos.FechaReclamo}"></s:label>
								</div>

							</div>
							<div class="row">
								<div class="form-group col-xs-4">
									<label>Documento de Identidad:</label>

								</div>
								<div class="form-group col-xs-3">
									<s:property value="reclamos.IdentidadD" />
									-
									<s:property value="reclamos.DNI" />
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-4">
									<label>Telefono:</label>

								</div>
								<div class="form-group col-xs-3">
									<s:property value="reclamos.Telefono" />
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-4">
									<label>E-mail:</label>
								</div>
								<div class="form-group col-xs-8">
									<s:property value="reclamos.Email" />
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-4">
									<label>Nombres:</label>
								</div>
								<div class="form-group col-xs-8">
									<s:property value="reclamos.Nombres" />
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-4">
									<label>Ape. Paterno:</label>
								</div>
								<div class="form-group col-xs-8">
									<s:property value="reclamos.ApePaterno" />
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-4">
									<label>Ape. Materno:</label>
								</div>
								<div class="form-group col-xs-8">
									<s:property value="reclamos.ApeMaterno" />
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-4">
									<label>Padre/Madre(caso sea menor de Edad):</label>
								</div>
								<div class="form-group col-xs-8">
									<s:property value="reclamos.PadreMenor" />
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-4">
									<label>Domicilio:</label>
								</div>
								<div class="form-group col-xs-8">
									<s:property value="reclamos.Domicilio" />
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-4">
									<label>Servicio:</label>
								</div>
								<div class="form-group col-xs-8">
									<s:property value="reclamos.ServicioD" />
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-4">
									<label>Motivo:</label>
								</div>
								<div class="form-group col-xs-8">
									<s:property value="reclamos.MotivoReclamoD" />
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-4">
									<label>Documento:</label>
								</div>
								<div class="form-group col-xs-8">
									<s:property value="reclamos.DocumentoD" />
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-4">
									<label>N°:</label>
								</div>
								<div class="form-group col-xs-8">
									<s:property value="reclamos.Serie" />
									-
									<s:property value="reclamos.Numero" />
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-4">
									<label>Fecha Emision</label>
								</div>
								<div class="form-group col-xs-8">
									<!-- 	<s:property value="detalle.FechaEmision"/> -->
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-4">
									<label>Destino</label>
								</div>
								<div class="form-group col-xs-8">
									<!-- 	<s:property value="detalle.FechaEmision"/> -->
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-4">
									<label>Agencia</label>
								</div>
								<div class="form-group col-xs-8">
									<!-- 	<s:property value="detalle.FechaEmision"/> -->
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-4">
									<label>Detalle</label>
								</div>
								<div class="form-group col-xs-8">
									<!-- 	<s:property value="detalle.FechaEmision"/> -->
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-4">
									<label>Pedido</label>
								</div>
								<div class="form-group col-xs-8">
									<!-- 	<s:property value="detalle.FechaEmision"/> -->
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="modal-footer">
				<div class="row">
					<div class="col-xs-12">
						<button id="btnImprimirReclamo" type="submit"
							class="btn btn-success">IMPRIMIR</button>
						<button id="btnCerrarCliente" type="button"
							class="btn btn-default" data-dismiss="modal">Cerrar</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script
	src="${pageContext.request.contextPath}/_lib/reclamos/reclamosImprimir.js"
	charset="UTF-8"></script>