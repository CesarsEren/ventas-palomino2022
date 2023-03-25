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

	<sec:authorize access="!hasAnyRole('C')">

		<div class="col-lg-8 col-md-8 col-xs-11">
			<br /> <label>LISTA DE PASAJEROS AGREGADOS</label>
			<div class="row">
				<div class="col-md-5 col-sm-5 col-xs-7"></div>
				<div class="col-md-5 col-sm-4 col-xs-12">
					<s:label id="lblInfo"
						value="%{#session.paso1Form.origendescripcion} - "
						cssStyle="color: #009045; font-size:20px;"></s:label>
					<s:label id="lblInfo1"
						value="%{#session.paso1Form.destinodescripcion}"
						cssStyle="color: #009045; font-size:20px;"></s:label>
				</div>
			</div>
			<table id="tblPasajeros" data-toggle="table" data-locale="es-ES"
				data-unique-id="id" class="table table-fixed">
				<thead>
					<tr>
						<th data-field="id" data-visible="false"></th>
						<th data-field="precio">Precio</th>
						<th data-field="nroAsiento">Asi.</th>
						<th data-field="nroDocumento">Documento</th>
						<th data-field="nombre">Nombre</th>
						<th data-field="embarque">Embarque</th>
						<th data-field="telefono" data-visible="false">Telefono</th>
						<th data-field="identidad" data-visible="false">Identidad</th>
						<th data-field="ruc" data-visible="false">Ruc</th>
						<!-- 	<th data-field="numeroEmbarque" data-width="2%" data-visible="false">NroEmbarque</th>  -->
						<th data-field="destinobajada">Destino</th>
						<th data-formatter="acciones">Operaciones</th>
					</tr>
				</thead>
			</table>

			<s:if test="%{#session.paso1Form.idaVuelta == false}">

				<div id="panelcuppon" class=" row" style="display: none">

					<div class="row">
						<div class="col-md-12">
							<div class="col-md-2">
								<label style="font-size: 18px"><bold>Total Ida:</bold></label>
							</div>
							<div class="col-md-1">
								<span style="font-size: 20px; font-family: MyriadPro-SemiCnIt;"
									id="PrecioTotal"></span>
							</div>

							<div class="col-md-2">
								<label style="font-size: 18px"><bold>Total Cupon
									Ida:</bold></label>
							</div>
							<div class="col-md-1">
								<span style="font-size: 20px; font-family: MyriadPro-SemiCnIt;"
									id="PrecioConCupon"></span>
							</div>

							<div class="col-md-3">
								<input id="cuppon" type="text" class="form-control cuppon"
									style="background-color: #F8EA0F; color: #339641"
									maxlength="15" /> <label id="cupponerror" style="color: Red;"></label>
							</div>
							<div class="col-md-3">
								<input id="validacuppon" type="button" value="Validar Cuppon"
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
							<h4 class="modal-title" id="modalLabelPaso2">Su cuppon es de
								Descuento</h4>
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
			<s:if test="%{#session.paso1Form.idaVuelta == false}">
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
								<input type="text" class="form-control" id="correoIda"
									placeholder="Enviar Correo">
							</div>
						</div>
					</sec:authorize>
					<sec:authorize access="isAnonymous() or hasAnyRole('SMS')">
						<div class="row" style="padding-left: 20px">
							<div class="row">

								<div class="col-md-11 col-lg-11 col-xs-12">
									<div class="panel panel-warning">
										<div class="panel-body">
											<div class="row">
												<div class="col-lg-3 col-md-3 col-xs-12">
													<input id="visa" type="radio" name="medioPago"
														checked="checked" value="1" /> <label for="visa"></label>
													<img alt=""
														src="${pageContext.request.contextPath}/_lib/img/visa_pos_fc.png"
														height="40px" width="70px">
												</div>
												<div class="col-lg-9 col-md-9 col-xs-10"
													style="font-size: 12px">
													<b>Visa</b> <br> Paga con tu tarjeta de crédito o
													débito<br>
												</div>
											</div>
											<hr style="width: 100%" align="left">
											<div class="row">
												<div class="col-lg-3 col-md-3 col-xs-12">
													<input id="pagoefectivo" type="radio" name="medioPago"
														value="2" /> <label for="pagoefectivo"></label> <img
														alt=""
														src="${pageContext.request.contextPath}/_lib/img/pago_banca_por_internet_64x30.png">
												</div>
												<div class="col-lg-9 col-md-9 col-xs-12"
													style="font-size: 12px">
													<b>Banca por Internet</b> <br> Paga a traves de tu
													banca por internet en BBVA,BCP,INTERBANK,SCOTIABANK <br>
													y BANBIF.Debítalo de tu cuenta o cárgalo a tu tarjeta de
													crédito asociada.
												</div>
											</div>
											<hr style="width: 100%" align="left">
											<div class="row">
												<div class="col-lg-3 col-md-3 col-xs-12">
													<input id="pagoefectivo" type="radio" name="medioPago"
														value="2" /> <label for="pagoefectivo"></label> <img
														alt=""
														src="${pageContext.request.contextPath}/_lib/img/pago_agentes_agencias_64x30.png">
												</div>
												<div class="col-lg-9 col-md-9 col-xs-12"
													style="font-size: 12px">
													<b>Agentes y Agencias</b> <br> Acércate cualquier
													punto del BBVA,BCP,INTERBANK,SCOTIABANK y BANBIF.<br>
													Agentes corresponsales KASNET,WESTER UNION-Pago de
													Servicios y <br> FULLCARGA.
												</div>
											</div>
											<hr style="width: 100%" align="left">
											<!-- OpenPay 
											<div class="row">
												<div class="col-lg-3 col-md-3 col-xs-12">
													<input id="openpay" type="radio" name="medioPago" value="3" />
													<label for="openPay"></label> <img alt=""
														src="${pageContext.request.contextPath}/_lib/img/openpay.png"
														height="22px" width="70px">
												</div>
												<div class="col-lg-9 col-md-9 col-xs-10"
													style="font-size: 12px">
													<b>openPay</b> <br> Paga con cualquier tarjeta de
													crédito o débito<br>
												</div>
											</div> 
											Fin OpenPay -->
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
							<input id="rbTerminosIda" type="checkbox" value="false" /> <a
								id="terminos" style="color: black;" href="#"> <strong>Acepto
									Los Términos y condiciones</strong>
							</a>
						</div>
						<div class="col-lg-4 col-md-3 col-xs-12">
							<jsp:include page="../template/terminoscondiciones.jsp"></jsp:include>
						</div>
					</div>
				</sec:authorize>
			</s:if>
		</div>

		<div class="col-lg-3 col-md-3 col-xs-11">
			<div id="divInfo" style="text-align: center;">
				<s:label value="%{#session.paso2FormIDA.descripcionServicioIda}-"
					cssStyle="color: #35ac19; font-size:15px;"></s:label>
				<s:label value="%{#session.paso2FormIDA.hora1Ida} "
					cssStyle="color: #35ac19; font-size:15px;"></s:label>
				<s:label value="%{#session.paso1Form.fechaIda}"
					cssStyle="color: #35ac19; font-size:15px;"></s:label>
				<s:label value="Seleccione Asiento de Ida"
					cssStyle="color: #35ac19; font-size:15px;"></s:label>
			</div>
			<div id="mapa">
				<div class="row">
					<div id="piso2">
						<s:iterator value="listaCroquisBusIda" var="asiento">
							<s:if test="#asiento.TTOP lt 1600">
								<s:if
									test="#asiento.Asiento == 'T1' || #asiento.Asiento == 'T2' || #asiento.Asiento == 'T3' || #asiento.Asiento == 'T4' || #asiento.Asiento == 'T5' || #asiento.Asiento == 'T6' || #asiento.Asiento == 'T7'">
									<div class="silla-televisor"
										style="top: <s:property value="TTop/12"/>px; left: <s:property value="LLeft/15"/>px;">
									</div>
								</s:if>
								<s:else>
									<s:if test="#asiento.promocion==true">
										<div class="silla parpadea"
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

								</s:else>
							</s:if>
						</s:iterator>
					</div>
				</div>

				<s:iterator value="listaCroquisBusIda" status="status" begin="0"
					end="0">

					<s:if
						test="#session.paso2FormIDA.descripcionServicioIda!='INKA PLUS' && listaCroquisBusIda[0].TTOP < 1920">
						<hr id="Linea"
							style="top: 120px; width: 100%; position: absolute; border-top: 1px solid #337ab7;" />
					</s:if>
				</s:iterator>
				<div id="piso1">
					<s:iterator value="listaCroquisBusIda" var="asiento">
						<s:if test="#asiento.TTOP lt 7600 && #asiento.TTOP gt 1600">
							<s:if
								test="%{#asiento.Asiento == 'T1' || #asiento.Asiento == 'T2' || #asiento.Asiento == 'T3' || #asiento.Asiento == 'T4' || #asiento.Asiento == 'T5' || #asiento.Asiento == 'T6' || #asiento.Asiento == 'T7'}">
								<div class="silla-televisor"
									style="top: <s:property value="TTop/12"/>px; left: <s:property value="LLeft/15"/>px;"></div>
							</s:if>
							<s:else>
								<s:if test="#asiento.promocion==true">
									<div class="silla parpadea"
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
								</s:if>
								<s:else>
									<div class="silla "
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
							</s:else>
						</s:if>
					</s:iterator>
				</div>
			</div>
		</div>
		<div class="col-lg-1 col-md-1 col-xs-1">
			<div class="row">
				<s:if
					test="#session.paso2FormIDA.descripcionServicioIda!='INKA PLUS' && listaCroquisBusIda[0].TTOP < 1920">
					<div class="TextoVertical1Piso">1er Piso</div>
				</s:if>
				<s:else>
					<div class="TextoVertical1PisoAlt">Piso 1</div>
				</s:else>

			</div>

			<br>
			<div class="row">
				<s:iterator value="listaCroquisBusIda" status="status" begin="0"
					end="0">
					<s:if
						test="#session.paso2FormIDA.descripcionServicioIda!='INKA PLUS' && listaCroquisBusIda[0].TTOP < 1920">
						<div class="TextoVertical2Piso">2do Piso</div>
					</s:if>
				</s:iterator>
			</div>
		</div>
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
	</sec:authorize>
	<br>
	<div class="row" style="">
		<div class="col-xs-12">
			<div class="row">
				<div class="form-group-inline">
					<sec:authorize access="!hasAnyRole('C')">
						<div class="col-md-4">
							<form action="regresapaso2" method="post">
								<div class="pull-left">
									<input type="submit" value="REGRESAR" class="btn btn-success">
								</div>
							</form>
						</div>
					</sec:authorize>

					<div class="col-md-5">
						<s:if test="%{#session.paso1Form.idaVuelta == false}">
							<sec:authorize access="isAuthenticated()">
								<sec:authorize access="hasAnyRole('1','3','2')">
									<div class="col-md-3">
										<input type="submit" value="GRABAR" id="btnGrabar"
											class="btn btn-success">
									</div>
								</sec:authorize>
							</sec:authorize>
							<sec:authorize access="isAnonymous() or hasAnyRole('SMS')">
								<div class="row">
									<div class="col-md-3">
										<form id="frmAccedePago" action="accedepago" method="post">
											<input type="hidden" name="EnvioCorreo" id="CorreoPaso3">
											<sec:authorize access="isAnonymous() or hasAnyRole('SMS')">
												<input type="hidden" class="hidmediopago" name="medioPago"
													value="1" />
											</sec:authorize>
											<input type="button" value="PAGAR" id="btnPagar"
												class="btn btn-success">
										</form>
									</div>
								</div>
							</sec:authorize>
							<div class="col-md-3">
								<div id="CargaGrabarIda" style="display: none">
									<img src="_lib/gif/ajax-loader-grabar-ida.gif" />
								</div>
							</div>
						</s:if>
					</div>
					<s:if test="%{#session.paso1Form.idaVuelta == true}">
						<div class="col-md-2">
							<form action="paso4" method="post">
								<div class="pull-right">
									<input type="submit" value="CONTINUAR" class="btn btn-success" />
								</div>
							</form>
						</div>
					</s:if>

				</div>
			</div>
		</div>
	</div>
	<sec:authorize access="hasAnyRole('C')">
		<br>
		<div class="panel panel-success">
			<div class="panel-heading">
				<div class="row">
					<div class="col-lg-8 col-md-7 col-xs-12">
						<s:label value="%{#session.paso2FormIDA.descripcionServicioIda}-"
							cssStyle=" font-size:15px;"></s:label>
						<s:label value="%{#session.paso2FormIDA.hora1Ida} -"
							cssStyle=" font-size:15px;"></s:label>
						<s:label value="%{#session.paso1Form.fechaIda} -"
							cssStyle=" font-size:15px;"></s:label>
						<s:label value="%{#session.paso1Form.origenDescripcion} - "
							cssStyle=" font-size:15px;"></s:label>
						<s:label value="%{#session.paso1Form.destinoDescripcion}"
							cssStyle=" font-size:15px;"></s:label>
					</div>
				</div>

			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-5 col-md-6 col-xs-12">
						<hr>
						<h4>ASIENTOS OCUPADOS</h4>
						<label style="color: #35ac19; font-size: 20px;"><s:property
								value="%{paso2FormIDA.cantidadAsientosOcupados}" /></label>
						<div class="row">
							<div class="col-lg-6 col-md-6 col-xs-12">
								<ul class="list-group">
									<s:iterator value="listaAsientoOcupadoAgencia">
										<li class="list-group-item"><span> <s:property
													value="detalle" />
										</span></li>
									</s:iterator>
								</ul>
							</div>
						</div>
					</div>
					<div class="col-lg-6 col-md-6 col-xs-12">
						<hr>
						<h4>ASIENTOS RESERVADOS</h4>
						<label Style="color: #35ac19; font-size: 20px;"><s:property
								value="%{paso2FormIDA.cantidadAsientosReservados}" /></label>
						<div class="row">
							<div class="col-lg-6 col-md-6 col-xs-12">
								<ul class="list-group">
									<s:iterator value="listaAsientoReservadoAgencia">
										<li class="list-group-item"><span> <s:property
													value="detalle" />
										</span></li>
									</s:iterator>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="panel-footer">
				<div class="row">
					<div class="row">
						<div class="col-lg-3 col-md-3 col-xs-12">
							<div class="col-lg-4 col-md-4 col-xs-7">
								<h4>BUS:</h4>
							</div>
							<div class="col-lg-3 col-md-4 col-xs-6">
								<label Style="color: #35ac19; font-size: 20px;"><s:property
										value="%{paso2FormIDA.nroBus}" /></label>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-6 col-md-6 col-xs-12">
							<div class="col-lg-2 col-md-4 col-xs-7">
								<h4>CAPACIDAD:</h4>
							</div>
							<div class="col-lg-6 col-md-6 col-xs-6">
								<label Style="color: #35ac19; font-size: 20px;"><s:property
										value="%{paso2FormIDA.capacidadBus}" /></label>
							</div>
						</div>
					</div>
					<s:if test="%{paso2FormIDA.mostrarComida == true}">
						<div class="row">
							<div class="col-lg-6 col-md-6 col-xs-12">
								<div class="col-lg-2 col-md-4 col-xs-7">
									<h4>COMIDAS:</h4>
								</div>
								<div class="row">
									<div class="col-lg-3 col-md-6 col-xs-6">
										<ul class="list-group">
											<s:iterator value="listaComidas">
												<li class="list-group-item"><span> <s:property
															value="detalle" />
												</span></li>
											</s:iterator>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</s:if>
				</div>
			</div>
		</div>
	</sec:authorize>
</div>
<br>

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
							<label>Asiento</label> <input name="paso3Form.numeroAsiento"
								type="text" class="form-control numeroAsiento"
								readonly="readonly" />
						</div>
						<sec:authorize access="hasAnyRole('1','3','2')">
							<div class="col-xs-6">
								<label>Precio</label> <input name="paso3Form.precio" type="text"
									class="form-control precioAsiento"
									onkeypress="return validarNumerosReales(event);" maxlength="6"
									onblur="javascript:CerosDecimales(this);" readonly="readonly" />
							</div>
						</sec:authorize>
						<sec:authorize access="!hasAnyRole('1','3','2')">
							<div class="col-xs-6">
								<label>Precio</label> <input name="paso3Form.precio" type="text"
									class="form-control precioAsiento"
									onkeypress="return validarNumerosReales(event);"
									onblur="javascript:CerosDecimales(this);" readonly="readonly"
									maxlength="6" />
							</div>
						</sec:authorize>

					</div>
					<div class="row">
						<div class="col-xs-6">
							<label>Identidad</label> <select class="form-control identidad"
								name="paso3Form.comboIdentidad">
								<s:iterator value="listaComboIdentidad">
									<option
										value="<s:property value="%{tipoDocumento+'-'+descripcionDocumento}"/>"><s:property
											value="%{tipoDocumento+' '+descripcionDocumento}" /></option>
								</s:iterator>
							</select>
						</div>
						<div class="col-xs-6">
							<label>Nro. Documento</label> <input
								name="paso3Form.numeroDocumentoIdentidad" type="text"
								class="form-control nroDocumento" maxlength="15"
								onblur="javascript:DniExistente(this);">
						</div>
					</div>
					<div class="row">
						<div class="col-xs-6">
							<label>Edad</label> <input name="paso3Form.edad" type="text"
								class="form-control edad" maxlength="2" />
						</div>
						<div class="col-xs-6">
							<label>Telefono</label> <input name="paso3Form.telefono"
								type="text" class="form-control telefono" maxlength="15" />
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<label>Nombre del pasajero</label> <input
								name="paso3Form.nombrePasajero" type="text"
								class="form-control nombreCompleto" maxlength="50" />
						</div>
					</div>
					<hr style="background-color: #5cb85c; color: #5cb85c; height: 1px;">
					<div class="row">
						<div class="col-xs-6">
							<label>Factura.. ?</label> <input id="rbFacturaIda"
								type="checkbox" value="false" />
						</div>
					</div>
					<div class="row">
						<div class="col-xs-6">
							<label>Ruc</label> <input name="paso3Form.ruc" type="text"
								class="form-control ruc" disabled="disabled" maxlength="11"
								onblur="javascript:RucExistente(this);" />
						</div>
						<div class="col-xs-6">
							<label>Razon social</label> <input name="paso3Form.razonSocial"
								type="text" disabled="disabled" class="form-control razon"
								maxlength="50" />
						</div>
					</div>
					<div class="row">
						<div class="col-xs-6">
							<label>Embarque</label> <select class="form-control embarque"
								name="paso3Form.comboEmbarque">
								<option value="-1">-- Seleccione Embarque --</option>
								<s:iterator value="listaComoEmbarque">
									<option value="<s:property value="%{codigo+'-'+agencia}"/>"><s:property
											value="%{agencia}" /></option>
								</s:iterator>
							</select>
						</div>
						<div class="col-xs-6">
							<label>Destino</label>
							<s:select list="listaComboDestinoBajada"
								cssClass="form-control destinobajada" listKey="Ciudad"
								listValue="CiudadD" name="paso3Form.comboDestinoBajada" />

						</div>
					</div>
					<input type="hidden" class="pasajeroSeleccionado"
						name="paso3Form.id" />
				</form>
			</div>
			<div class="modal-footer">
				<div class="row">
					<div class="col-xs-12">
						<div>
							<div id="CargaAgregaIda" style="display: none">
								<img src="_lib/gif/ajax-loader-agregar-ida.gif" />
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-xs-12">
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
	</div>
</div>
<input type="hidden" id="hidContador" value="0">
<form action="generarvoucherIda" id="formGenerarVoucherIda">
	<input type="hidden" id="hidenviocorreoIda" name="EnvioCorreo">
</form>
<form action="cancelarvoucherida" id="formCancelarVoucherIda"></form>
<script
	src="${pageContext.request.contextPath}/_lib/venta/onkeypress.js"
	charset="UTF-8"></script>
<script src="${pageContext.request.contextPath}/_lib/venta/ventaida.js"
	charset="UTF-8"></script>
<script src="${pageContext.request.contextPath}/_lib/venta/flowventa.js"
	charset="UTF-8"></script>
<script src="${pageContext.request.contextPath}/_lib/venta/paso3.js"
	charset="UTF-8"></script>
