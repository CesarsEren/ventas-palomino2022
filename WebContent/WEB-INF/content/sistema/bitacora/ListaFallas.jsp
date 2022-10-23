<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<sj:head locale="%{locale.language}" jquerytheme="start" />
<jsp:include page="../../template/header.jsp"></jsp:include>

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
								<div class="col-xs-12 col-md-3">
									<label>Estado</label> <select class="form-control"
										id="listaestado">
										<option value="Todos">Todos</option>
										<option value="Pendiente">Pendiente</option>
										<option value="En Proceso">En Proceso</option>
										<option value="Solucionado">Solucionado</option>
									</select>
								</div>


								<div class="x_content">
									<div class="bootstrap-table">
										<div class="fixed-table-container">

											<div class="row">
												<table data-toggle="table" id="tablafallas2"
													data-locale="es-ES" data-side-pagination="server"
													data-url="listafallas?estado=Todos&porestado=true"
													data-pagination="true"
													data-response-handler="responseHandler">
													<thead>
														<tr>
															<th data-field="nrobus" class="th-inner">Nro Bus</th>
															<th data-field="categoriaD" class="th-inner">Categoria
																Falla</th>
															<th data-field="subcategoriaD" class="th-inner">SubCategoria
																Falla</th>
															<th data-field="rutaD" class="th-inner">Ruta</th>
															<th data-field="foto" data-formatter="FotoVideo"
																class="th-inner">Foto</th>
															<th data-field="asunto" class="th-inner">Asunto</th>
															<th data-field="fechacreacion" class="th-inner">Fecha
																Creación</th>
															<th data-field="registro" class="th-inner">Registro</th>
															<th data-field="resolvio" class="th-inner">Resolvió</th>
															<th data-field="estado"
																data-formatter="AccionesProgramacion" class="th-inner">Estado</th>
														</tr>
													</thead>
													</tbody>
												</table>
											</div>
										</div>
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

			<div class="modal fade" id="modalEnProceso" tabindex="-1"
				role="dialog">
				<div class="modal-dialog modal-lg" role="document">
					<div class="modal-content">
						<div class="modal-header" style="background: #f0ad4e;">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="modalLabelPaso2"
								style="color: white;">Requerimientos para estar En Proceso
							</h4>
						</div>
						<div class="modal-body">

							<form>
								<div class="col-md-12 col-lg-12">
									<div class="row">

										<div class="col-md-12">
											<div class="col-md-6 col-xs-12">
												<label>Usuario</label>
											</div>
											<div class="col-md-6 col-xs-12">
												<span id="mrresolvera"> <sec:authentication
														property="principal.nombreCompleto" /></span>
											</div>
											<span id="mrresolvera2" style="display: none"> <sec:authentication
													property="principal.nombreCompleto" /></span>
										</div>
									</div>
									<hr
										style="background-color: #5cb85c; color: #5cb85c; height: 1px;" />

									<div class="form-group purple-border">
										<label for="contenidoproceso">Requerimientos</label>
										<textarea class="form-control" id="contenidoproceso" rows="10"
											maxlength='300'></textarea>
									</div>
								</div>
							</form>
						</div>
					</div>
					<div class="modal-footer">

						<div class="row">
							<div class="col-xs-12">
								<div class="col-md-4 col-xs-6 ">
									<button type="button" id="btnproceso" class="btn btn-warning"
										data-dismiss="modal">En Proceso</button>
								</div>
								<div class="col-md-4 col-xs- 6">
									<button type="button" class="btn btn-danger"
										data-dismiss="modal">Cerrar</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>


			<div class="modal fade" id="modalSolucionado" tabindex="-1"
				role="dialog">
				<div class="modal-dialog modal-lg" role="document">
					<div class="modal-content">
						<div class="modal-header" style="background: #218838">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" style="color: white;"
								id="modalLabelPaso2">Registro de la Solución</h4>
						</div>
						<div class="modal-body">

							<form>
								<div class="col-md-12 col-lg-12">
									<div class="row">

										<div class="col-md-12">
											<div class="col-md-6 col-xs-12">
												<label>Usuario</label>
											</div>
											<div class="col-md-6 col-xs-12">
												<span id="mrresolvera"> <sec:authentication
														property="principal.nombreCompleto" /></span>
											</div>
										</div>


									</div>
									<hr
										style="background-color: #5cb85c; color: #5cb85c; height: 1px;" />

									<div class="form-group purple-border">
										<label for="contenidosolucion">Solución</label>
										<textarea class="form-control" id="contenidosolucion"
											rows="10" maxlength='300'></textarea>
									</div>
								</div>
							</form>
						</div>
					</div>
					<div class="modal-footer">

						<div class="row">
							<div class="col-xs-12">
								<div class="col-md-4 col-xs-6 ">
									<button type="button" id=btnsolucionado class="btn btn-success"
										data-dismiss="modal">En Proceso</button>
								</div>
								<div class="col-md-4 col-xs- 6">
									<button type="button" class="btn btn-danger"
										data-dismiss="modal">Cerrar</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>


			<div class="modal fade" id="modalbitacora" tabindex="-1"
				role="dialog">
				<div class="modal-dialog modal-lg" role="document">
					<div class="modal-content">
						<div class="modal-header" style="background: #138496">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" style="color: white;"
								id="modalLabelPaso2">
								Ver Detalle de Falla :<span id="idfalla"></span>
							</h4>
						</div>
						<div class="modal-body">

							<form>
								<div class="col-md-12 col-lg-12">
									<div class="row">
										<div class="col-md-12">
											<div class="col-md-6">
												<label>NroBus :</label>
											</div>
											<div class="col-md-6">
												<span id="mnrobus"> </span>
											</div>
										</div>
										<div class="col-md-12">
											<div class="col-md-6">
												<label>Categoria Falla :</label>
											</div>
											<div class="col-md-6">
												<span id="mcategoriafalla"> </span>
											</div>
										</div>
										<div class="col-md-12">
											<div class="col-md-6">
												<label>Subcategoria Falla :</label>
											</div>
											<div class="col-md-6">
												<span id="msubcategoriafalla"> </span>
											</div>
										</div>

										<div class="col-md-12">
											<div class="col-md-6">
												<label>Ruta :</label>
											</div>
											<div class="col-md-6">
												<span id="mrutaD"> </span>
											</div>
										</div>
										<div class="col-md-12">
											<div class="col-md-6">
												<label>Asunto :</label>
											</div>
											<div class="col-md-6">
												<span id="masunto"> </span>
											</div>
										</div>
										<div class="col-md-12">
											<div class="col-md-6">
												<label>Fecha Creación :</label>
											</div>
											<div class="col-md-6">
												<span id="mfechacreacion"> </span>
											</div>
										</div>
										<div class="col-md-12" style="border: 1px solid #C82333">
											<div class="col-md-12 text-center">
												<label>Descripción de falla :</label>
											</div>
											<div class="col-md-12 text-center">
												<span id="mdetalle"> </span>
											</div>
										</div>
										<div class="col-md-12">
											<div class="col-md-6">
												<label>Estado :</label>
											</div>
											<div class="col-md-6">
												<span id="mestado"> </span>
											</div>
										</div>

										<div class="col-md-12">
											<div class="col-md-6">
												<label>Registro :</label>
											</div>
											<div class="col-md-6">
												<span id="mregistro"> </span>
											</div>
										</div>

										<div class="col-md-12">
											<div class="col-md-6">
												<label>Resolvio :</label>
											</div>
											<div class="col-md-6">
												<span id="mresolvio"> </span>
											</div>
										</div>

									</div>
									<hr
										style="background-color: #5cb85c; color: #5cb85c; height: 1px;">
									<div class="row">
										<div class="col-md-12">
											<img id="mimagen" class="img-fluid" />
										</div>
									</div>


									<div class="col-md-12" style="border: 1px solid #E0A800">
										<div class="col-md-12 text-center">
											<label>Detalle En Proceso :</label>
										</div>
										<div class="col-md-12 text-center">
											<span id="dtproceso"> </span>
										</div>
									</div>


									<div class="col-md-12" style="border: 1px solid #218838">
										<div class="col-md-12 text-center">
											<label>Detalle de Solución :</label>
										</div>
										<div class="col-md-12 text-center">
											<span id="dtsolucionado"> </span>
										</div>
									</div>

								</div>
								<div class="col-md-12 text-center ">
									<span id="mfechasolucion" style="color: green; font-size: 20px"></span>
								</div>

							</form>
						</div>
					</div>
					<div class="modal-footer">

						<div class="row">
							<div class="col-xs-12">

								<button type="button" class="btn btn-danger"
									data-dismiss="modal">Cerrar</button>
							</div>
						</div>
					</div>
				</div>
			</div>


		</div>

		<!-- /page content -->

		<!-- footer content -->

		<!-- /footer content -->
	</div>
	</div>

	<jsp:include page="../../template/footer.jsp"></jsp:include>

	<script
		src="${pageContext.request.contextPath}/_lib/sistema/bitacora/verbitacora.js"
		charset="UTF-8"></script>
	<!-- <script src="${pageContext.request.contextPath}/_lib/usuarios/usuarios.js" charset="UTF-8"></script>-->


</body>
</html>
