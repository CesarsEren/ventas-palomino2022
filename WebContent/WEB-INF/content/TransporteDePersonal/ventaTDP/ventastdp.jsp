<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<sj:head locale="%{locale.language}" jquerytheme="start" />
<jsp:include page="../../template/header.jsp"></jsp:include>
<script src="https://sdk.amazonaws.com/js/aws-sdk-2.1.24.min.js"></script>
</head>

<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<jsp:include page="../../template/menuizquierda.jsp"></jsp:include>

			<!-- top navigation -->
			<jsp:include page="../../template/menucabecera.jsp"></jsp:include>
			<!-- /top navigation -->

			<!-- page content -->
			<div class="right_col" role="main">
				<div class="">

					<div class="clearfix"></div>

					<div class="row">

						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_content">

									<div class="col-md-12 col-sm-12 col-xs-12 venta-pasajes">
										Transporte de Personal</div>
								</div>
							</div>
							<div class="x_panel">
								<div class="x_content">

									<h3>
										Paso 1
										</h4>
										<h4>Elige tu Origen y Destino
									</h3>
									<hr>
									<div class="row">

										<div class="col-xs-12 col-md-3">
											<label>Origen</label> <select class="form-control fallas"
												name="listacategoria" id="listacategoria">
												<option value="-1">-- Seleccione Origen --</option>
												<option value="1">AUDIO Y VIDEO</option>
												<option value="2">EQUIPOS</option>
												<option value="3">EQUIPOS DE CLIMATIZACION</option>
												<option value="4">ELEMENTOS DE SALON</option>
												<option value="5">BAÑOS</option>
												<option value="6">LIMPIEZA</option>
											</select>
										</div>



									</div>


									<div class="row">
										<div class="col-xs-12 col-md-3">
											<label>Destino</label> <select class="form-control fallas"
												name="listasubcategoria" id="listasubcategoria">
												<option value="-1">-- Seleccione Destino --</option>
											</select>
										</div>
									</div>
									<div class="row">
										<div class="col-md-3 col-sm-6 col-xs-12">
											<label>Fecha de Ida</label>
											<div class="input-group input-append date"
												id="dateRangePickerIda">
												<input id="fechaIda" type="text" class="form-control"
													name="paso1Form.fechaIda"> <span
													class="input-group-addon add-on"><span
													class="glyphicon glyphicon-calendar"></span></span>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-3 col-sm-6 col-xs-12">
											<div
												class="d-flex justify-content-between align-items-center">

												<button class="btn btn btn-success ">BUSCAR
													ITINERARIOS</button>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="x_panel">
								<div class="x_content">
									<h3>
										Paso 2
										</h4>
										<h4>Elige un Intinerario
									</h3>
									<hr>
									<div class="row" style="text-align: center;">
										<div class="col-md-12 col-sm-12 col-xs-12">
											<label style="color: #009045; font-size: 20px;">27/11/2019</label>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <label id="lblInfo"
												style="color: #009045; font-size: 20px;">LIMA - </label> <label
												id="lblInfo1" style="color: #009045; font-size: 20px;">CUSCO</label>
										</div>
									</div>
									<div class="bootstrap-table">
										<div class="fixed-table-container">

											<div class="row">
												<table class="table table-hover" data-toggle="table">
													<thead>
														<tr>
															<th class="th-inner"></th>
															<th class="th-inner">Servicio</th>
															<th class="th-inner">Origen</th>
															<th class="th-inner">Destino</th>
															<th class="th-inner">Hora de Salida</th>
															<th class="th-inner">Precio</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>

							<div class="x_panel">
								<div class="x_content">
									<h3>
										Paso 3
										</h4>
										<h4>Elige tus Asientos
									</h3>
									<hr>

									<div class="row">



										<div class="col-lg-8 col-md-8 col-xs-11">
											<br> <label>LISTA DE PASAJEROS AGREGADOS</label>
											<div class="row">
												<div class="col-md-5 col-sm-5 col-xs-7"></div>
												<div class="col-md-5 col-sm-4 col-xs-12">


													<label id="lblInfo"
														style="color: #009045; font-size: 20px;">LIMA - </label> <label
														id="lblInfo1" style="color: #009045; font-size: 20px;">CUSCO</label>



												</div>
											</div>
											<div class="bootstrap-table">
												<div class="fixed-table-toolbar">
													<div class="columns columns-right btn-group pull-right">
														<div class="keep-open btn-group" title="Columnas">
															<button type="button"
																class="btn btn-default dropdown-toggle"
																data-toggle="dropdown">
																<i class="glyphicon glyphicon-th icon-th"></i> <span
																	class="caret"></span>
															</button>
															<ul class="dropdown-menu" role="menu">
																<li><label><input type="checkbox"
																		data-field="id" value="0"> </label></li>
																<li><label><input type="checkbox"
																		data-field="precio" value="1" checked="checked">
																		Precio</label></li>
																<li><label><input type="checkbox"
																		data-field="nroAsiento" value="2" checked="checked">
																		Asi.</label></li>
																<li><label><input type="checkbox"
																		data-field="nroDocumento" value="3" checked="checked">
																		Documento</label></li>
																<li><label><input type="checkbox"
																		data-field="nombre" value="4" checked="checked">
																		Nombre</label></li>
																<li><label><input type="checkbox"
																		data-field="embarque" value="5" checked="checked">
																		Embarque</label></li>
																<li><label><input type="checkbox"
																		data-field="telefono" value="6"> Telefono</label></li>
																<li><label><input type="checkbox"
																		data-field="identidad" value="7"> Identidad</label></li>
																<li><label><input type="checkbox"
																		data-field="ruc" value="8"> Ruc</label></li>
																<li><label><input type="checkbox"
																		data-field="destinobajada" value="9" checked="checked">
																		Destino</label></li>
																<li><label><input type="checkbox"
																		data-field="10" value="10" checked="checked">
																		Operaciones</label></li>
															</ul>
														</div>
													</div>
												</div>
												<div class="fixed-table-container"
													style="height: 230px; padding-bottom: 26px;">
													<div class="fixed-table-header" style="margin-right: 0px;">
														<table class="table table-fixed table-hover"
															style="width: 1049px;">
															<thead>
																<tr>
																	<th style="" data-field="precio" tabindex="0"><div
																			class="th-inner ">Precio</div>
																		<div class="fht-cell"></div></th>
																	<th style="" data-field="nroAsiento" tabindex="0"><div
																			class="th-inner ">Asi.</div>
																		<div class="fht-cell"></div></th>
																	<th style="" data-field="nroDocumento" tabindex="0"><div
																			class="th-inner ">Documento</div>
																		<div class="fht-cell"></div></th>
																	<th style="" data-field="nombre" tabindex="0"><div
																			class="th-inner ">Nombre</div>
																		<div class="fht-cell"></div></th>
																	<th style="" data-field="embarque" tabindex="0"><div
																			class="th-inner ">Embarque</div>
																		<div class="fht-cell"></div></th>
																	<th style="" data-field="destinobajada" tabindex="0"><div
																			class="th-inner ">Destino</div>
																		<div class="fht-cell"></div></th>
																	<th style="" data-field="10" tabindex="0"><div
																			class="th-inner ">Operaciones</div>
																		<div class="fht-cell"></div></th>
																</tr>
															</thead>
														</table>
													</div>
													<div class="fixed-table-body">
														<div class="fixed-table-loading" style="top: 27px;">Por
															favor espere...</div>
														<table id="tblPasajeros" data-toggle="table"
															data-locale="es-ES" data-unique-id="id"
															class="table table-fixed table-hover"
															style="margin-top: -26px;">
															<thead>
																<tr>
																	<th style="" data-field="precio" tabindex="0"><div
																			class="th-inner ">Precio</div>
																		<div class="fht-cell"></div></th>
																	<th style="" data-field="nroAsiento" tabindex="0"><div
																			class="th-inner ">Asi.</div>
																		<div class="fht-cell"></div></th>
																	<th style="" data-field="nroDocumento" tabindex="0"><div
																			class="th-inner ">Documento</div>
																		<div class="fht-cell"></div></th>
																	<th style="" data-field="nombre" tabindex="0"><div
																			class="th-inner ">Nombre</div>
																		<div class="fht-cell"></div></th>
																	<th style="" data-field="embarque" tabindex="0"><div
																			class="th-inner ">Embarque</div>
																		<div class="fht-cell"></div></th>
																	<th style="" data-field="destinobajada" tabindex="0"><div
																			class="th-inner ">Destino</div>
																		<div class="fht-cell"></div></th>
																	<th style="" data-field="10" tabindex="0"><div
																			class="th-inner ">Operaciones</div>
																		<div class="fht-cell"></div></th>
																</tr>
															</thead>
															<tbody>
																<tr class="no-records-found">
																	<td colspan="7">No se encontraron resultados</td>
																</tr>
															</tbody>
														</table>
													</div>
													<div class="fixed-table-footer" style="display: none;">
														<table>
															<tbody>
																<tr></tr>
															</tbody>
														</table>
													</div>
													<div class="fixed-table-pagination" style="display: none;"></div>
												</div>
											</div>
											<div class="clearfix"></div>



											<div id="panelcuppon" class=" row" style="display: none">

												<div class="row">
													<div class="col-md-12">
														<div class="col-md-2">
															<label style="font-size: 18px"><bold>Total
																Ida:</bold></label>
														</div>
														<div class="col-md-1">
															<span
																style="font-size: 20px; font-family: MyriadPro-SemiCnIt;"
																id="PrecioTotal"></span>
														</div>

														<div class="col-md-2">
															<label style="font-size: 18px"><bold>Total
																Cupon Ida:</bold></label>
														</div>
														<div class="col-md-1">
															<span
																style="font-size: 20px; font-family: MyriadPro-SemiCnIt;"
																id="PrecioConCupon"></span>
														</div>

														<div class="col-md-3">
															<input id="cuppon" type="text"
																class="form-control cuppon"
																style="background-color: #F8EA0F; color: #339641"
																maxlength="15"> <label id="cupponerror"
																style="color: Red;"></label>
														</div>
														<div class="col-md-3">
															<input id="validacuppon" type="button"
																value="Validar Cuppon" class="btn btn-success">
														</div>
													</div>
												</div>


											</div>




											<!--  MODAL DE VALIDACIÓN DE CUPPON -->
											<div class="modal fade" id="modalValidarCuppon" tabindex="-1"
												role="dialog">
												<div class="modal-dialog modal-lg" role="document">
													<div class="modal-content">
														<div class="modal-header">
															<button type="button" class="close" data-dismiss="modal"
																aria-label="Close">
																<span aria-hidden="true">×</span>
															</button>
															<h4 class="modal-title" id="modalLabelPaso2">Su
																cuppon es de Descuento</h4>
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
											<br>
											<div class="row">

												<div class="col-xs-12 col-md-6">
													<label>Cargar Plantilla Excel</label> <br> <input
														type="file" name="upFile" id="upFile">
												</div>
												<div class="col-xs-12 col-md-6">
													<label>Descargar Plantilla Excel</label> <br> <input
														type="button" value="Descargar" name="downFile"
														id="upFile">
												</div>

											</div>


										</div>

										<div class="col-lg-3 col-md-3 col-xs-11">
											<div id="divInfo" style="text-align: center;">


												<label style="color: #35ac19; font-size: 15px;">CHASQUI
													BUS-</label> <label style="color: #35ac19; font-size: 15px;">08:00
												</label> <label style="color: #35ac19; font-size: 15px;">30/11/2019</label>





												<label style="color: #35ac19; font-size: 15px;">Seleccione
													Asiento de Ida</label>



											</div>
											<div id="mapa">
												<div class="row">
													<div id="piso2"></div>
												</div>




												<div id="piso1">




													<div class="silla" style="top: 33px; left: 24px;">
														<div class="silla-numero" data-info="1"
															data-disponible="True" data-precio="70.0">
															1 <input type="checkbox" name="asientoBus" id="AS-1">
														</div>
													</div>






													<div class="silla" style="top: 33px; left: 56px;">
														<div class="silla-numero" data-info="2"
															data-disponible="True" data-precio="70.0">
															2 <input type="checkbox" name="asientoBus" id="AS-2">
														</div>
													</div>






													<div class="silla" style="top: 33px; left: 120px;">
														<div class="silla-numero" data-info="3"
															data-disponible="True" data-precio="70.0">
															3 <input type="checkbox" name="asientoBus" id="AS-3">
														</div>
													</div>






													<div class="silla" style="top: 33px; left: 151px;">
														<div class="silla-numero" data-info="4"
															data-disponible="True" data-precio="70.0">
															4 <input type="checkbox" name="asientoBus" id="AS-4">
														</div>
													</div>





													<div class="silla-televisor" style="top: 33px; left: 88px;"></div>







													<div class="silla" style="top: 63px; left: 24px;">
														<div class="silla-numero" data-info="5"
															data-disponible="True" data-precio="70.0">
															5 <input type="checkbox" name="asientoBus" id="AS-5">
														</div>
													</div>






													<div class="silla" style="top: 63px; left: 56px;">
														<div class="silla-numero" data-info="6"
															data-disponible="True" data-precio="70.0">
															6 <input type="checkbox" name="asientoBus" id="AS-6">
														</div>
													</div>






													<div class="silla" style="top: 63px; left: 120px;">
														<div class="silla-numero" data-info="7"
															data-disponible="True" data-precio="70.0">
															7 <input type="checkbox" name="asientoBus" id="AS-7">
														</div>
													</div>






													<div class="silla" style="top: 63px; left: 152px;">
														<div class="silla-numero" data-info="8"
															data-disponible="True" data-precio="70.0">
															8 <input type="checkbox" name="asientoBus" id="AS-8">
														</div>
													</div>






													<div class="silla-bar" style="top: 91px; left: 120px;">
														<div class="silla-numero" data-info="B"
															data-disponible="false" data-precio="70.0"></div>
													</div>
													<div class="silla" style="top: 93px; left: 24px;">
														<div class="silla-numero" data-info="9"
															data-disponible="True" data-precio="70.0">
															9 <input type="checkbox" name="asientoBus" id="AS-9">
														</div>
													</div>
													<div class="silla" style="top: 93px; left: 56px;">
														<div class="silla-numero" data-info="10"
															data-disponible="True" data-precio="70.0">
															10 <input type="checkbox" name="asientoBus" id="AS-10">
														</div>
													</div>
													<div class="silla-escalera"
														style="top: 121px; left: 120px;">
														<div class="silla-numero" data-info="E"
															data-disponible="false" data-precio="70.0"></div>
													</div>
													<div class="silla" style="top: 123px; left: 24px;">
														<div class="silla-numero" data-info="11"
															data-disponible="True" data-precio="70.0">
															11 <input type="checkbox" name="asientoBus" id="AS-11">
														</div>
													</div>
													<div class="silla" style="top: 123px; left: 56px;">
														<div class="silla-numero" data-info="12"
															data-disponible="True" data-precio="70.0">
															12 <input type="checkbox" name="asientoBus" id="AS-12">
														</div>
													</div>
													<div class="silla-televisor"
														style="top: 151px; left: 88px;"></div>
													<div class="silla" style="top: 153px; left: 24px;">
														<div class="silla-numero" data-info="13"
															data-disponible="True" data-precio="70.0">
															13 <input type="checkbox" name="asientoBus" id="AS-13">
														</div>
													</div>

													<div class="silla" style="top: 153px; left: 56px;">
														<div class="silla-numero" data-info="14"
															data-disponible="True" data-precio="70.0">
															14 <input type="checkbox" name="asientoBus" id="AS-14">
														</div>
													</div>
													<div class="silla" style="top: 153px; left: 120px;">
														<div class="silla-numero" data-info="15"
															data-disponible="True" data-precio="70.0">
															15 <input type="checkbox" name="asientoBus" id="AS-15">
														</div>
													</div>
													<div class="silla" style="top: 153px; left: 152px;">
														<div class="silla-numero" data-info="16"
															data-disponible="True" data-precio="70.0">
															16 <input type="checkbox" name="asientoBus" id="AS-16">
														</div>
													</div>


													<div class="silla" style="top: 183px; left: 24px;">
														<div class="silla-numero" data-info="17"
															data-disponible="True" data-precio="70.0">
															17 <input type="checkbox" name="asientoBus" id="AS-17">
														</div>
													</div>

													<div class="silla" style="top: 183px; left: 56px;">
														<div class="silla-numero" data-info="18"
															data-disponible="True" data-precio="70.0">
															18 <input type="checkbox" name="asientoBus" id="AS-18">
														</div>
													</div>
													<div class="silla" style="top: 183px; left: 120px;">
														<div class="silla-numero" data-info="19"
															data-disponible="True" data-precio="70.0">
															19 <input type="checkbox" name="asientoBus" id="AS-19">
														</div>
													</div>
													<div class="silla" style="top: 183px; left: 152px;">
														<div class="silla-numero" data-info="20"
															data-disponible="True" data-precio="70.0">
															20 <input type="checkbox" name="asientoBus" id="AS-20">
														</div>
													</div>
													<div class="silla" style="top: 213px; left: 24px;">
														<div class="silla-numero" data-info="21"
															data-disponible="True" data-precio="70.0">
															21 <input type="checkbox" name="asientoBus" id="AS-21">
														</div>
													</div>
													<div class="silla" style="top: 213px; left: 56px;">
														<div class="silla-numero" data-info="22"
															data-disponible="True" data-precio="70.0">
															22 <input type="checkbox" name="asientoBus" id="AS-22">
														</div>
													</div>
													<div class="silla" style="top: 213px; left: 120px;">
														<div class="silla-numero" data-info="23"
															data-disponible="True" data-precio="70.0">
															23 <input type="checkbox" name="asientoBus" id="AS-23">
														</div>
													</div>
													<div class="silla" style="top: 213px; left: 152px;">
														<div class="silla-numero" data-info="24"
															data-disponible="True" data-precio="70.0">
															24 <input type="checkbox" name="asientoBus" id="AS-24">
														</div>
													</div>
													<div class="silla" style="top: 243px; left: 24px;">
														<div class="silla-numero" data-info="25"
															data-disponible="True" data-precio="70.0">
															25 <input type="checkbox" name="asientoBus" id="AS-25">
														</div>
													</div>
													<div class="silla" style="top: 243px; left: 56px;">
														<div class="silla-numero" data-info="26"
															data-disponible="True" data-precio="70.0">
															26 <input type="checkbox" name="asientoBus" id="AS-26">
														</div>
													</div>
													<div class="silla" style="top: 243px; left: 120px;">
														<div class="silla-numero" data-info="27"
															data-disponible="True" data-precio="70.0">
															27 <input type="checkbox" name="asientoBus" id="AS-27">
														</div>
													</div>
													<div class="silla" style="top: 243px; left: 152px;">
														<div class="silla-numero" data-info="28"
															data-disponible="True" data-precio="70.0">
															28 <input type="checkbox" name="asientoBus" id="AS-28">
														</div>
													</div>

													<div class="silla-televisor"
														style="top: 243px; left: 88px;"></div>

													<div class="silla" style="top: 273px; left: 24px;">
														<div class="silla-numero" data-info="29"
															data-disponible="True" data-precio="70.0">
															29 <input type="checkbox" name="asientoBus" id="AS-29">
														</div>
													</div>

													<div class="silla" style="top: 273px; left: 56px;">
														<div class="silla-numero" data-info="30"
															data-disponible="True" data-precio="70.0">
															30 <input type="checkbox" name="asientoBus" id="AS-30">
														</div>
													</div>
													<div class="silla" style="top: 273px; left: 120px;">
														<div class="silla-numero" data-info="31"
															data-disponible="True" data-precio="70.0">
															31 <input type="checkbox" name="asientoBus" id="AS-31">
														</div>
													</div>
													<div class="silla" style="top: 273px; left: 152px;">
														<div class="silla-numero" data-info="32"
															data-disponible="True" data-precio="70.0">
															32 <input type="checkbox" name="asientoBus" id="AS-32">
														</div>
													</div>
													<div class="silla" style="top: 303px; left: 24px;">
														<div class="silla-numero" data-info="33"
															data-disponible="True" data-precio="70.0">
															33 <input type="checkbox" name="asientoBus" id="AS-33">
														</div>
													</div>
													<div class="silla" style="top: 303px; left: 56px;">
														<div class="silla-numero" data-info="34"
															data-disponible="True" data-precio="70.0">
															34 <input type="checkbox" name="asientoBus" id="AS-34">
														</div>
													</div>
													<div class="silla" style="top: 303px; left: 120px;">
														<div class="silla-numero" data-info="35"
															data-disponible="True" data-precio="70.0">
															35 <input type="checkbox" name="asientoBus" id="AS-35">
														</div>
													</div>
													<div class="silla" style="top: 303px; left: 152px;">
														<div class="silla-numero" data-info="36"
															data-disponible="True" data-precio="70.0">
															36 <input type="checkbox" name="asientoBus" id="AS-36">
														</div>
													</div>
													<div class="silla" style="top: 333px; left: 24px;">
														<div class="silla-numero" data-info="37"
															data-disponible="True" data-precio="70.0">
															37 <input type="checkbox" name="asientoBus" id="AS-37">
														</div>
													</div>
													<div class="silla" style="top: 333px; left: 56px;">
														<div class="silla-numero" data-info="38"
															data-disponible="True" data-precio="70.0">
															38 <input type="checkbox" name="asientoBus" id="AS-38">
														</div>
													</div>
													<div class="silla" style="top: 333px; left: 120px;">
														<div class="silla-numero" data-info="39"
															data-disponible="True" data-precio="70.0">
															39 <input type="checkbox" name="asientoBus" id="AS-39">
														</div>
													</div>
													<div class="silla" style="top: 333px; left: 152px;">
														<div class="silla-numero" data-info="40"
															data-disponible="True" data-precio="70.0">
															40 <input type="checkbox" name="asientoBus" id="AS-40">
														</div>
													</div>
													<div class="silla" style="top: 363px; left: 24px;">
														<div class="silla-numero" data-info="41"
															data-disponible="True" data-precio="70.0">
															41 <input type="checkbox" name="asientoBus" id="AS-41">
														</div>
													</div>
													<div class="silla" style="top: 363px; left: 56px;">
														<div class="silla-numero" data-info="42"
															data-disponible="True" data-precio="70.0">
															42 <input type="checkbox" name="asientoBus" id="AS-42">
														</div>
													</div>
													<div class="silla" style="top: 363px; left: 120px;">
														<div class="silla-numero" data-info="43"
															data-disponible="True" data-precio="70.0">
															43 <input type="checkbox" name="asientoBus" id="AS-43">
														</div>
													</div>

													<div class="silla" style="top: 363px; left: 152px;">
														<div class="silla-numero" data-info="44"
															data-disponible="True" data-precio="70.0">
															44 <input type="checkbox" name="asientoBus" id="AS-44">
														</div>
													</div>






													<div class="silla" style="top: 393px; left: 24px;">
														<div class="silla-numero" data-info="45"
															data-disponible="True" data-precio="70.0">
															45 <input type="checkbox" name="asientoBus" id="AS-45">
														</div>
													</div>






													<div class="silla" style="top: 393px; left: 56px;">
														<div class="silla-numero" data-info="46"
															data-disponible="True" data-precio="70.0">
															46 <input type="checkbox" name="asientoBus" id="AS-46">
														</div>
													</div>






													<div class="silla" style="top: 393px; left: 120px;">
														<div class="silla-numero" data-info="47"
															data-disponible="True" data-precio="70.0">
															47 <input type="checkbox" name="asientoBus" id="AS-47">
														</div>
													</div>






													<div class="silla" style="top: 393px; left: 152px;">
														<div class="silla-numero" data-info="48"
															data-disponible="True" data-precio="70.0">
															48 <input type="checkbox" name="asientoBus" id="AS-48">
														</div>
													</div>






													<div class="silla" style="top: 423px; left: 24px;">
														<div class="silla-numero" data-info="49"
															data-disponible="True" data-precio="70.0">
															49 <input type="checkbox" name="asientoBus" id="AS-49">
														</div>
													</div>
												</div>
											</div>
										</div>


										<br>

									</div>
								</div>
							</div>

							<div class="x_panel">
								<jsp:include page="../../template/footer-visa.jsp"></jsp:include>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../../template/footer.jsp"></jsp:include>
	<script
		src="${pageContext.request.contextPath}/_lib/sistema/bitacora/bitacora.js"
		charset="UTF-8"></script>
	<!-- <script src="${pageContext.request.contextPath}/_lib/usuarios/usuarios.js" charset="UTF-8"></script>-->

</body>
</html>
