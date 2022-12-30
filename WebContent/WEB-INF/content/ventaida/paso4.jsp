<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12" style="text-align: right;">
		<img alt=""
			src="${pageContext.request.contextPath}/_lib/img/timer.png"
			height="35px" width="35px"> Tiempo restante:
	</div>
</div>
<div class="row">
	<jsp:include page="../template/timer.jsp"></jsp:include>
</div>
<div class="row">
	<div class="col-lg-8 col-md-8 col-xs-11">
		<br /> <label>LISTA DE PASAJEROS AGREGADOS</label>
		<div class="row">
			<div class="col-md-5 col-sm-5 col-xs-7"></div>
			<div class="col-md-5 col-sm-4 col-xs-12">
				<s:label id="lblInfo"
					value="%{#session.paso1Form.destinodescripcion} - "
					cssStyle="color: #009045; font-size:20px;"></s:label>
				<s:label id="lblInfo1"
					value="%{#session.paso1Form.origendescripcion}"
					cssStyle="color: #009045; font-size:20px;"></s:label>
			</div>
		</div>
		<table id="tblPasajerosVuelta" data-toggle="table" data-locale="es-ES"
			data-unique-id="id" class="table table-fixed">
			<thead>
				<tr>
					<th data-field="id" data-visible="false"></th>
					<th data-field="precio">Precio</th>
					<th data-field="nroAsiento">Asi.</th>
					<th data-field="nombre">Nombre</th>
					<th data-field="embarque">Embarque</th>
					<th data-field="destinobajada">Destino</th>
					<th data-formatter="acciones">Operaciones</th>
				</tr>
			</thead>
		</table>

		<s:if test="%{#session.paso1Form.idaVuelta == true}">

			<div id="panelcuppon" class=" row" style="display: none">

				<div class="row">
					<div class="col-md-12">
						<div class="col-md-2">
							<label style="font-size: 18px"><bold>Total
								Retorno:</bold></label>
						</div>
						<div class="col-md-1">
							<span style="font-size: 20px; font-family: MyriadPro-SemiCnIt;"
								id="PrecioTotal"></span>
						</div>

						<div class="col-md-2">
							<label style="font-size: 18px"><bold>Total Cupon
								Retorno:</bold></label>
						</div>
						<div class="col-md-1">
							<span style="font-size: 20px; font-family: MyriadPro-SemiCnIt;"
								id="PrecioConCupon"></span>
						</div>

						<div class="col-md-3">
							<input id="cuppon" type="text" class="form-control cuppon"
								style="background-color: #F8EA0F; color: #339641" maxlength="15" />
							<label id="cupponerror" style="color: Red;"></label>
						</div>
						<div class="col-md-3">
							<input id="validacupponret" type="button" value="Validar Cuppon"
								class="btn btn-success">
						</div>
					</div>
				</div>


			</div>

		</s:if>



		<!--  MODAL DE VALIDACIÓN DE CUPPON -->
		<div class="modal fade" id="modalValidarCuppon" tabindex="-1"
			role="dialog">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Su cuppon es de Descuento</h4>
					</div>
					<div class="modal-body">
						<form accept-charset="UTF-8">
							<div class="row">
								<div class="col-xs-12">
									<div class="col-xs-6">
										<label>Nro Cuppon</label>
									</div>
									<div class="col-xs-6">
										<label id="codigocuppon1"> <!-- NRO DE CUPON POR LLENAR -->
										</label>
									</div>
								</div>

							</div>
							<h2>¿Que Ofrece?</h2>
							<hr
								style="background-color: #5cb85c; color: #5cb85c; height: 1px;">



							<div class="col-xs-12">
								<div class="col-xs-6">
									<label>Tipo de Cupon</label>
								</div>
								<div class="col-xs-6">
									<label id="tipocuppon1"> <!-- Se llenará de datos -->
									</label>
								</div>
							</div>

							<div class="col-xs-12">
								<div class="col-xs-6">
									<label>Descuento</label>
								</div>
								<div class="col-xs-6">
									<label id="cuppondescuento1"> <!-- Se llenará de datos -->
									</label>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">

						<div class="row">
							<div class="col-xs-12">
								<button id="btnAplicarCuppon" type="button"
									class="btn btn-success">
									<i class="fa fa-plus"></i>&nbsp;Aplicar Cuppon
								</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Cerrar</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--  ////MODAL DE VALIDACIÓN DE CUPPON -->


		<br />
		<div class="row">
			<sec:authorize access="hasAnyRole('1','3','2')">
				<div class="col-lg-4 col-md-3 col-xs-12">
					<label>Ingresar Correo Electronico</label>
				</div>
			</sec:authorize>

			<sec:authorize access="isAnonymous() or hasAnyRole('SMS')">
				<div class="col-lg-4 col-md-3 col-xs-12">
					<label for="ida">Medio de Pago</label>
				</div>
			</sec:authorize>
		</div>
		<div class="row">
			<sec:authorize access="hasAnyRole('1','3','2')">
				<div class="col-lg-8 col-md-3 col-xs-12">
					<div class="input-group">
						<div class="input-group-addon">@</div>
						<input type="text" class="form-control" id="correoVuelta"
							placeholder="Enviar Correo">
					</div>
				</div>
			</sec:authorize>
			<sec:authorize access="isAnonymous() or hasAnyRole('SMS')">
				<div class="row" style="padding-left: 20px">
					<div class="row">
						<div class="col-lg-11 col-md-11 col-xs-12">
							<div class="panel panel-warning">
								<div class="panel-body">
									<div class="row">
										<div class="col-lg-3 col-md-3 col-xs-12">
											<input id="visa" type="radio" name="medioPago"
												checked="checked" value="1" /> <label for="visa"></label> <img
												alt=""
												src="${pageContext.request.contextPath}/_lib/img/visa_pos_fc.png"
												height="40px" width="70px">
										</div>
										<div class="col-lg-9 col-md-9 col-xs-10"
											style="font-size: 12px">
											<b>Visa</b> <br> Paga con tu tarjeta de crédito o débito<br>
										</div>
									</div>
									<hr style="width: 100%" align="left">
									<div class="row">
										<div class="col-lg-3 col-md-3 col-xs-12">
											<input id="pagoefectivo" type="radio" name="medioPago"
												value="2" /> <label for="pagoefectivo"></label> <img alt=""
												src="${pageContext.request.contextPath}/_lib/img/pago_banca_por_internet_64x30.png">
										</div>
										<div class="col-lg-9 col-md-9 col-xs-12"
											style="font-size: 12px">
											<b>Banca por Internet</b> <br> Paga a traves de tu banca
											por internet en BBVA,BCP,INTERBANK,SCOTIABANK <br> y
											BANBIF.Debítalo de tu cuenta o cárgalo a tu tarjeta de
											crédito asociada.
										</div>
									</div>
									<hr style="width: 100%" align="left">
									<div class="row">
										<div class="col-lg-3 col-md-3 col-xs-10">
											<input id="pagoefectivo" type="radio" name="medioPago"
												value="2" /> <label for="pagoefectivo"></label> <img alt=""
												src="${pageContext.request.contextPath}/_lib/img/pago_agentes_agencias_64x30.png">
										</div>
										<div class="col-lg-9 col-md-9 col-xs-12"
											style="font-size: 12px">
											<b>Agentes y Agencias</b> <br> Acércate cualquier punto
											del BBVA,BCP,INTERBANK,SCOTIABANK y BANBIF.<br> Agentes
											corresponsales KASNET,WESTER UNION-Pago de Servicios y <br>
											FULLCARGA.
										</div>
									</div>
									<!-- hr style="width: 100%" align="left">
									<div class="row">
										<div class="col-lg-3 col-md-3 col-xs-10">
											<input id="openpay" type="radio" name="medioPago" value="3" />
											<label for="openpay"></label> <img alt=""
												src="https://public.openpay.mx/images/logos/openpay/login_logo.png">
										</div>
										<div class="col-lg-9 col-md-9 col-xs-12"
											style="font-size: 12px">
											<b>Paga con tu tarjeta de crédito o débito</b><br>
										</div>
									</div-->
								</div>
							</div>
						</div>
					</div>
				</div>
			</sec:authorize>
		</div>
		<sec:authorize access="isAnonymous() or hasAnyRole('SMS')">
			<br>
			<br>
			<br>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
					style="text-align: left;">
					<input id="rbTerminosVuelta" type="checkbox" value="false" /> <a
						id="terminos" style="color: black;" href="#"> <u><b>Acepto Los Términos y condiciones</b></u>
					</a>
				</div>
				<div class="col-lg-4 col-md-3 col-xs-12">
					<jsp:include page="../template/terminoscondiciones.jsp"></jsp:include>
				</div>
			</div>
		</sec:authorize>
	</div>
	<div class="col-lg-3 col-md-3 col-xs-11" id="divBus">
		<div id="divInfo1" style="text-align: center;">
			<s:label
				value="%{#session.paso2FormVUELTA.descripcionServicioVuelta}-"
				cssStyle="color: #35ac19; font-size:15px;"></s:label>
			<s:label value="%{#session.paso2FormVUELTA.hora1Vuelta}"
				cssStyle="color: #35ac19; font-size:15px;"></s:label>
			<s:label value="%{#session.paso1Form.fechaVuelta}"
				cssStyle="color: #35ac19; font-size:15px;"></s:label>
			<s:label value="Seleccione Asiento de Vuelta"
				cssStyle="color: #35ac19; font-size:15px;"></s:label>
		</div>
		<div id="mapa" class="center-block">
			<div id="piso2">
				<s:iterator value="listaCroquisBusVuelta" var="asiento">
					<s:if test="#asiento.TTOP lt 1600">
						<s:if
							test="#asiento.Asiento == 'T1' || #asiento.Asiento == 'T2' || #asiento.Asiento == 'T3' || #asiento.Asiento == 'T4'">
							<div class="silla-televisor"
								style="top: <s:property value="TTop/12"/>px; left: <s:property value="LLeft/15"/>px;"></div>
						</s:if>
						<s:else>
							<div class="silla"
								style="top: <s:property value="TTop/12"/>px; left: <s:property value="LLeft/15"/>px;">
								<div class="silla-numero"
									data-info="<s:property value="Asiento" />"
									data-disponible="<s:property value="Visible" />"
									data-precio="<s:property value="Precio" />">
									<s:property value="Asiento" />
									<input type="checkbox" name="asientoBus"
										id="AS-<s:property value="Asiento" />">
								</div>
							</div>
						</s:else>
					</s:if>
				</s:iterator>
			</div>
			<s:iterator value="listaCroquisBusVuelta" status="status" begin="0"
				end="0">
				<s:if test="listaCroquisBusVuelta[0].TTOP < 1920">
					<hr id="Linea"
						style="top: 120px; width: 100%; position: absolute; border-top: 1px solid #337ab7;" />
				</s:if>
			</s:iterator>
			<div id="piso1">
				<s:iterator value="listaCroquisBusVuelta" var="asiento">
					<s:if test="#asiento.TTOP lt 7600 && #asiento.TTOP gt 1600">
						<s:if
							test="#asiento.Asiento == 'T1' || #asiento.Asiento == 'T2' || #asiento.Asiento == 'T3' || #asiento.Asiento == 'T4'">
							<div class="silla-televisor"
								style="top: <s:property value="TTop/12"/>px; left: <s:property value="LLeft/15"/>px;"></div>
						</s:if>
						<s:else>
							<div class="silla"
								style="top: <s:property value="TTop/12"/>px; left: <s:property value="LLeft/15"/>px;">
								<div class="silla-numero"
									data-info="<s:property value="Asiento" />"
									data-disponible="<s:property value="Visible" />"
									data-precio="<s:property value="Precio" />">
									<s:property value="Asiento" />
									<input type="checkbox" name="asientoBus"
										id="AS-<s:property value="Asiento" />">
								</div>
							</div>
						</s:else>
					</s:if>
				</s:iterator>
			</div>
		</div>
	</div>
	<div class="col-lg-1 col-md-1 col-xs-1">
		<div class="row">
			<div class="TextoVertical1Piso">1er Piso</div>
		</div>
		<br>
		<div class="row">
			<s:iterator value="listaCroquisBusVuelta" status="status" begin="0"
				end="0">
				<s:if test="listaCroquisBusVuelta[0].TTOP < 1920">
					<div class="TextoVertical2Piso">2do Piso</div>
				</s:if>
			</s:iterator>
		</div>
	</div>
	<br>
	<div class="row">
		<div class="col-md-8"></div>
		<div class="col-md-4">
			<table>
				<tr>
					<td>
						<div class="col-xs-12">
							<ul class="legend">
								<li><span class="libre"></span>LIBRE</li>
								<li><span class="ocupado"></span>OCUPADO</li>
								<li><span class="seleccionado"></span>SELECCIONADO</li>
							</ul>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<div class="form-group-inline">
				<div class="row">
					<div class="col-md-4"></div>
					<sec:authorize access="isAuthenticated()">
						<sec:authorize access="hasAnyRole('1','3','2')">
							<div class="col-md-2">
								<input type="submit" value="GRABAR" id="btnGrabarIdaVuelta"
									class="btn btn-success">
							</div>
							<div id="divRegresar" class="col-md-2">
								<a href="ventapaso1" class="btn btn-primary">Generar nueva
									venta</a>
							</div>
						</sec:authorize>
					</sec:authorize>
					<sec:authorize access="isAnonymous() or hasAnyRole('SMS')">
						<div class="col-md-2">
							<form id="frmAccedePagoVuelta" action="accedepago" method="post">
								<input type="hidden" name="EnvioCorreo" id="CorreoPaso4">
								<sec:authorize access="isAnonymous() or hasAnyRole('SMS')">
									<input type="hidden" class="hidmediopagoVuelta"
										name="medioPago" value="1" />
								</sec:authorize>
								<input type="button" value="PAGAR" id="btnPagarVuelta"
									class="btn btn-success">
							</form>
						</div>
					</sec:authorize>
					<div class="col-md-2">
						<div id="CargaGrabarVuelta" style="display: none">
							<img src="_lib/gif/ajax-loader-grabar-vuelta.gif" />
						</div>
					</div>
				</div>


			</div>

		</div>
	</div>
</div>

<br>

<div class="modal fade" id="modalPaso4" role="dialog"
	aria-labelledby="modalLabelPaso2">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="modalLabelPaso2">Lista de Pasajeros
					de Viaje</h4>
			</div>
			<div class="modal-body">
				<form id="frmPasajeroIdaVuelta">
					<div class="row">
						<div id="mensajeServerModalIdaVuelta"></div>
					</div>
					<div class="row">
						<div class="col-md-6 col-sm-6 col-xs-12">
							<label>Número asiento</label> <input
								name="paso4Form.numeroAsiento" type="text"
								class="form-control numeroAsientoVuelta" readonly="readonly" />
						</div>
						<sec:authorize access="hasAnyRole('1','3','2')">
							<div class="col-md-6 col-sm-6 col-xs-12">
								<label>Precio</label> <input name="paso4Form.precio" type="text"
									class="form-control precioAsientoVuelta"
									onkeypress="return validarNumerosReales(event);"
									onblur="javascript:CerosDecimales(this);" />
							</div>
						</sec:authorize>
						<sec:authorize access="!hasAnyRole('1','3','2')">
							<div class="col-md-6 col-sm-6 col-xs-12">
								<label>Precio</label> <input name="paso4Form.precio" type="text"
									class="form-control precioAsientoVuelta"
									onkeypress="return validarNumerosReales(event);"
									onblur="javascript:CerosDecimales(this);" readonly="readonly"
									maxlength="6" />
							</div>
						</sec:authorize>
					</div>
					<div class="row">
						<div class="col-md-6 col-sm-6 col-xs-12">
							<label>Embarque</label>
							<s:select list="listaComoEmbarqueVuelta"
								cssClass="form-control embarqueVuelta" headerKey="-1"
								headerValue="-- Seleccione Embarque --"
								listKey="%{codigo+'-'+agencia}" listValue="agencia"></s:select>
						</div>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<label>Destino</label>
							<s:select list="listaComboDestinoBajadaVuelta"
								cssClass="form-control destinobajadaVuelta" listKey="Ciudad"
								listValue="CiudadD">
							</s:select>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8 col-sm-8 col-xs-10">
							<label>Pasajeros</label> <select id="select2Pasajeros"
								class="js-example-responsive" style="width: 74%">
							</select>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<div class="col-md 2">
					<div>
						<div id="CargaAgregaIdaVuelta" style="display: none">
							<img src="_lib/gif/ajax-loader-agregar-vuelta.gif" />
						</div>
					</div>
				</div>
				<button id="btnAsignarAsientoPasajero" type="button"
					class="btn btn-success">
					<i class="fa fa-plus"></i>&nbsp;Asignar Pasajero al Asiento
				</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
			</div>
		</div>
	</div>
</div>
<input type="hidden" id="hidContador" value="0">
<form action="generarvoucherIdaVuelta" id="formGenerarVoucherIdaVuelta">
	<input type="hidden" id="hidenviocorreoVuelta" name="EnvioCorreo">
</form>
<form action="cancelarvouchervuelta" id="formCancelarVoucherIdaVuelta">
</form>

<script
	src="${pageContext.request.contextPath}/_lib/ventaidavuelta/pasajerosidavuelta.js"
	charset="UTF-8"></script>
<script
	src="${pageContext.request.contextPath}/_lib/ventaidavuelta/flowventaidavuelta.js"
	charset="UTF-8"></script>
<script
	src="${pageContext.request.contextPath}/_lib/ventaidavuelta/ventaidavuelta.js"
	charset="UTF-8"></script>
<script
	src="${pageContext.request.contextPath}/_lib/ventaidavuelta/paso4.js"></script>
