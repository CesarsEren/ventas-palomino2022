<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div class="x_panel">
	<div class="row">
		<form method="post" class="form-horizontal" id="frmReclamos">
			<div class="row">
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>Empresa</label>
					<s:select id="cboEmpresa" list="listaComboEmpresa"
						style="font-weight:bold;" cssClass="form-control input-sm empresa"
						listKey="Codigo" listValue="Razon" name="reclamos.Empresa" />

				</div>
				<div class="col-md-1 col-sm-3 col-xs-12">
					<label>Ruc</label> <input type="text" id="rucEmpresa"
						style="color: #009045; font-weight: bold;" class="form-control"
						readonly="readonly"
						value="<s:property  value='listaComboEmpresa[0].Ruc'/>">
				</div>
				<div class="col-md-4 col-sm-6 col-xs-12">
					<label>Direccion Legal</label> <input type="text" id="dirEmpresa"
						style="color: #009045; font-weight: bold;" class="form-control"
						readonly="readonly"
						value="<s:property  value='listaComboEmpresa[0].Direccion'/>">
				</div>
				<div class="col-md-4 col-sm-6 col-xs-12" align="center">
					<img style="width: 110px; text-align: right;"
						src="${pageContext.request.contextPath}/_lib/img/logoMenu.png">
				</div>
			</div>
			<div class="row">
				<div class="col-md-4 col-sm-6 col-xs-12">
					<label>Fecha Reclamo</label>
					<div class="input-group input-append date"
						id="dateRangePickerReclamo">
						<input type="text" class="form-control input-sm fechaReclamo"
							disabled="disabled" name="reclamos.FechaReclamo" />
					</div>
				</div>
				<br>
				<br>
			</div>
			<div class="">
				<div class="clearfix" style="border-color: #E9F513;">
					<div class="col-md-12 col-sm-12 col-xs-12"
						style="font-size: 15px; font-weight: bold; text-align: left; background-color: #FEEE00; color: #009045; background: linear-gradient(#FEEE00, #ccc97e), #D5DC93;">
						1.- IDENTIFICACIÓN DEL CONSUMIDOR DEL RECLAMANTE</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-2 col-sm-6 col-xs-12">
					<label>Identidad:</label> <select id="cboDocumentoIdentidad"
						class="form-control input-sm identidad" name="reclamos.Identidad">
						<option value="D">D.N.I</option>
						<option value="M">Menor Edad</option>
						<option value="P">Pasaporte</option>
					</select>
				</div>
				<div class="col-md-2 col-sm-6 col-xs-12">
					<label>Nro. Documento:</label> <input type="text"
						class="form-control input-sm dni"
						placeholder="Número de Documento" name="reclamos.DNI"
						maxlength="8" required="required"
						onkeypress="return validarNumerosReclamo(event);" /><br>
				</div>
			</div>
			<div class="row">
				<div class="col-md-2 col-sm-6 col-xs-12">
					<label>Nombres:</label> <input type="text"
						class="form-control input-sm nombres" name="reclamos.Nombres"
						placeholder="Nombres" maxlength="80" required="required"
						style="text-transform: uppercase;" /><br>
				</div>
				<div class="col-md-2 col-sm-6 col-xs-12">
					<label>Apellido Paterno:</label> <input type="text"
						class="form-control input-sm apepaterno"
						name="reclamos.ApePaterno" placeholder="Apellido Paterno"
						maxlength="45" required="required"
						style="text-transform: uppercase;" /><br>
				</div>
				<div class="col-md-2 col-sm-6 col-xs-12">
					<label>Apellido Materno:</label> <input type="text"
						class="form-control input-sm apematerno"
						name="reclamos.ApeMaterno" placeholder="Apellido Materno"
						maxlength="45" required="required"
						style="text-transform: uppercase;" /><br>
				</div>
				<div class="col-md-1 col-sm-6 col-xs-12">
					<label>Telefono:</label> <input type="text"
						class="form-control input-sm telefono" name="reclamos.Telefono"
						placeholder="Teléfono" maxlength="15" required="required" /><br>
				</div>
				<div class="col-md-2 col-sm-6 col-xs-12">
					<label>Email:</label> <input type="text"
						class="form-control input-sm email" name="reclamos.Email"
						placeholder="Email" maxlength="80" required="required"
						style="text-transform: uppercase;" /><br>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4 col-sm-6 col-xs-12">
					<label>Domicilio:</label> <input type="text"
						class="form-control input-sm domicilio" name="reclamos.Domicilio"
						placeholder="Domicilio" maxlength="150" required="required"
						style="text-transform: uppercase;" /><br>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>Padre/Madre(caso sea menor de Edad)</label> <input
						type="text" class="form-control input-sm padreomadre"
						name="reclamos.PadreMenor" disabled="disabled"
						placeholder="Nombre del Padre o Madre" maxlength="80"
						style="text-transform: uppercase;" /><br>
				</div>
			</div>
			<div class="">
				<div class="clearfix" style="border-color: #E9F513;">
					<div class="col-md-12 col-sm-12 col-xs-12"
						style="font-size: 15px; font-weight: bold; text-align: left; background-color: #FEEE00; color: #009045; background: linear-gradient(#FEEE00, #ccc97e), #D5DC93;">
						2.- IDENTIFICACIÓN DEL BIEN CONTRATADO</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-2 col-sm-6 col-xs-12">
					<label>Fecha Incidente</label>
					<div class="input-group input-append date dateRangePickerIncidente">
						<input type="text" class="form-control input-sm fechaIncidente"
							name="reclamos.FechaIncidente" /> <span
							class="input-group-addon add-on"><span
							class="glyphicon glyphicon-calendar"></span></span>
					</div>
				</div>
				<div class="col-md-2 col-sm-6 col-xs-12">
					<label>Servicio</label> <select id="cboServicio"
						class="form-control input-sm" name="reclamos.Servicio">
						<option value="B">Pasajes</option>
						<option value="E">Encomiendas/Carga/Giro</option>
					</select>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4 col-sm-12 col-xs-12">
					<label>Motivo</label> <select id="cboMotivo"
						class="form-control input-sm" name="reclamos.MotivoReclamo">
					</select>
				</div>
				<div class="col-md-3 col-sm-12 col-xs-12">
					<label>Empresa</label> <input type="text" id="razonEmpresa"
						style="color: #009045; font-weight: bold;" class="form-control"
						readonly="readonly"
						value="<s:property  value='listaComboEmpresa[0].Razon'/>">
				</div>
				<div class="col-md-2 col-sm-6 col-xs-12">
					<label>Documento</label> <select id="cboTipoDocumento"
						class="form-control input-sm" name="reclamos.Documento">
					</select>
				</div>
				<div class="col-md-1 col-sm-6 col-xs-12">
					<label>Serie</label> <input type="text"
						class="form-control input-sm serie" name="reclamos.Serie"
						placeholder="000" maxlength="3"
						onblur="javascript:AumentarCerosSerie(this);" required="required" /><br>
				</div>
				<div class="col-md-2 col-sm-6 col-xs-12">
					<label>Numero</label> <input type="text"
						class="form-control input-sm numero" name="reclamos.Numero"
						placeholder="0000000" maxlength="7"
						onblur="javascript:VerificaDocumento(this);" required="required"><br>
				</div>
			</div>
			<div class="">
				<div class="clearfix" style="border-color: #E9F513;">
					<div class="col-md-12 col-sm-12 col-xs-12"
						style="font-size: 15px; font-weight: bold; text-align: left; background-color: #FEEE00; color: #009045; background: linear-gradient(#FEEE00, #ccc97e), #D5DC93;">
						3.- DETALLE DE RECLAMACIÓN</div>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-6 col-sm-6 col-xs-12">
					<input id="reclamo" type="radio" name="reclamos.TipoReclamo"
						checked="checked" value="R" /> <label for="reclamo">Reclamo</label>
					<p>Disconformidad relacionada a los productos o servicios</p>
				</div>
				<div class="col-md-6 col-sm-6 col-xs-12">
					<input id="queja" type="radio" name="reclamos.TipoReclamo"
						value="Q" /> <label for="queja">Queja</label>
					<p>Disconformidad no relacionada a los productos o servicios;
						o, malestar o descontento respecto a la atención del cliente al
						público.</p>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-6 col-sm-6 col-xs-12">
					<label>Detalle</label>
					<textarea class="form-control input-sm detalle" rows="5" cols="5"
						name="reclamos.Detalle"
						placeholder="Detalle de la ocurrencia: descripcion en cortas palabras de la ocurrencia."
						required="required" style="text-transform: uppercase;"></textarea>
				</div>
				<div class="col-md-6 col-sm-6 col-xs-12">
					<label>Pedido</label>
					<textarea class="form-control input-sm pedido" rows="5" cols="5"
						name="reclamos.Pedido"
						placeholder="¿Qué espera obtener del reclamo ó queja?"
						required="required" style="text-transform: uppercase;"></textarea>
				</div>
			</div>
			<br> <br>
			<div class="row">
				<div class="col-xs-2">
					<input type="button" value="ENVIAR RECLAMO" id="btnReclamo"
						class="btn btn-primary">
				</div>
			</div>
		</form>
	</div>
	<div class="row">
		<!-- <div id="modalReclamos"></div>-->

		<div class="modal fade" id="modalReclamoConfirmado" tabindex="-1"
			role="dialog" aria-labelledby="modalR">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header"
						style="background: linear-gradient(#33AD45, #327d3d), #D5DC93; color: white;">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="modalLabelPaso2">Su reclamo ha
							sido enviado satisfactoriamente</h4>
					</div>
					<div class="modal-body"
						style="max-height: calc(100vh - 210px); overflow-y: auto;">
						<div class="row" style="padding-left: 5px;">
							<form method="post" id="frmReclamoImprimir"
								action="imprimirreclamos" autocomplete="off"
								accept-charset="UTF-8">
								<input type="hidden" id="hidPeriodo" name="reclamos.Periodo">
								<input type="hidden" id="hidNroR" name="reclamos.Nro"> <input
									type="hidden" id="hidEmpresa" name="reclamos.Empresa">
								<div class="col-md-12 col-sm-12 col-xs-12">
									<div class="row">
										<div class="form-group col-xs-4">
											<label>Su Numero de Reclamo es:</label>

										</div>
										<div class="form-group col-xs-1">
											<div
												style="font-weight: bold; text-align: center; font-size: 20px; background: #009045; color: white;">
												<s:label id="hidNro"></s:label>
											</div>
										</div>
										<div class="form-group col-xs-1"></div>
										<div class="form-group col-xs-6"
											style="font-size: 15px; font-weight: bold;">
											<s:label id="hidEmpresaD"></s:label>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-4">
											<label>Fecha de Reclamo:</label>

										</div>
										<div class="form-group col-xs-3" style="font-weight: normal;">
											<s:label id="hidFechaReclamo"></s:label>
										</div>

									</div>
									<div class="row">
										<div class="">
											<div class="clearfix" style="border-color: #E9F513;">
												<div class="col-md-12 col-sm-12 col-xs-12"
													style="font-size: 15px; font-weight: bold; text-align: left; background-color: #FEEE00; color: #009045; background: linear-gradient(#FEEE00, #ccc97e), #D5DC93;">
													1.- IDENTIFICACIÓN DEL CONSUMIDOR DEL RECLAMANTE</div>
											</div>
										</div>
										<div class="form-group col-xs-4">
											<label>Documento de Identidad:</label>

										</div>
										<div class="form-group col-xs-3">
											<s:label id="hidIdentidadD"></s:label>
											-
											<s:label id="hidDNI"></s:label>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-4">
											<label>Nombres:</label>
										</div>
										<div class="form-group col-xs-8">
											<s:label id="hidNombres"></s:label>

										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-4">
											<label>Ape. Paterno:</label>
										</div>
										<div class="form-group col-xs-8">
											<s:label id="hidApePaterno"></s:label>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-4">
											<label>Ape. Materno:</label>
										</div>
										<div class="form-group col-xs-8">
											<s:label name="reclamos.ApeMaterno" id="hidApeMaterno"></s:label>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-4">
											<label>Telefono:</label>

										</div>
										<div class="form-group col-xs-3">
											<s:label id="hidTelefono"></s:label>

										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-4">
											<label>E-mail:</label>
										</div>
										<div class="form-group col-xs-8">
											<s:label id="hidEmail"></s:label>

										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-4">
											<label>Domicilio:</label>
										</div>
										<div class="form-group col-xs-8">
											<s:label name="reclamos.Domicilio" id="hidDomicilio"></s:label>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-5">
											<label>Padre/Madre(caso sea menor de Edad):</label>
										</div>
										<div class="form-group col-xs-7">
											<s:label name="reclamos.PadreMenor" id="hidPadreMenor"></s:label>
										</div>
									</div>
									<div class="row">
										<div class="">
											<div class="clearfix" style="border-color: #E9F513;">
												<div class="col-md-12 col-sm-12 col-xs-12"
													style="font-size: 15px; font-weight: bold; text-align: left; background-color: #FEEE00; color: #009045; background: linear-gradient(#FEEE00, #ccc97e), #D5DC93;">
													2.- IDENTIFICACIÓN DEL BIEN CONTRATADO</div>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="form-group col-xs-4">
											<label>Servicio:</label>
										</div>
										<div class="form-group col-xs-8">
											<s:label name="reclamos.ServicioD" id="hidServicioD"></s:label>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-4">
											<label>Motivo:</label>
										</div>
										<div class="form-group col-xs-8">
											<s:label name="reclamos.ServicioD" id="hidMotivoReclamoD"></s:label>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-4">
											<label>Documento:</label>
										</div>
										<div class="form-group col-xs-8">
											<s:label id="hidDocumentoD"></s:label>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-4">
											<label>N°:</label>
										</div>
										<div class="form-group col-xs-8">
											<s:label name="reclamos.Serie" id="hidSerie"></s:label>
											-
											<s:label name="reclamos.Numero" id="hidNumero"></s:label>
										</div>
									</div>
									<div class="row">
										<div class="">
											<div class="clearfix" style="border-color: #E9F513;">
												<div class="col-md-12 col-sm-12 col-xs-12"
													style="font-size: 15px; font-weight: bold; text-align: left; background-color: #FEEE00; color: #009045; background: linear-gradient(#FEEE00, #ccc97e), #D5DC93;">
													3.- DETALLE DE RECLAMACIÓN</div>
											</div>
										</div>
										<div class="form-group col-xs-4">
											<label>Tipo</label>
										</div>
										<div class="form-group col-xs-8">
											<s:label id="hidTipoD"></s:label>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-4">
											<label>Destino</label>
										</div>
										<div class="form-group col-xs-8">
											<s:label id="hidDestinoD"></s:label>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-4">
											<label>Agencia</label>
										</div>
										<div class="form-group col-xs-8">
											<s:label id="hidAgenciaD"></s:label>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-4">
											<label>Detalle</label>
										</div>
										<div class="form-group col-xs-8">
											<s:label id="hidDetalle"></s:label>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-4">
											<label>Pedido</label>
										</div>
										<div class="form-group col-xs-8">
											<s:label id="hidPedido"></s:label>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
					<div class="modal-footer">
						<div class="row">
							<div class="col-xs-12">
								<div class="row">
									<div class="form-group col-xs-2" style="text-align: left;">
										<label>Ingrese su Correo</label>
									</div>
									<div class="form-group col-xs-8">
										<div class="input-group">
											<div class="input-group-addon">@</div>
											<input type="text" id="txtReenvioCorreo" class="form-control"
												placeholder="Enviar Correo">
										</div>
									</div>
									<div class="form-group col-xs-1">
										<div id="CargaReCorreo" style="display: none">
											<img src="_lib/gif/ajax-loader-agregar-ida.gif" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-6">
										<div id="mensajecorreo" style="text-align: left;"></div>
									</div>
									<div class="col-xs-2">
										<button id="btnReenviarCorreo" type="button"
											class="btn btn-success">REENVIAR CORREO</button>
									</div>
									<div class="col-xs-2">
										<button id="btnImprimirReclamo" type="button"
											class="btn btn-success">IMPRIMIR</button>
									</div>
									<div class="col-xs-1">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Cerrar</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<br>
<script
	src="${pageContext.request.contextPath}/_lib/reclamos/reclamos.js"
	charset="UTF-8"></script>
