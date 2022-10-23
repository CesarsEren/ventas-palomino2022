<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<sj:head locale="%{locale.language}" jquerytheme="start" />
<jsp:include page="../../../template/header.jsp"></jsp:include>
</head>

<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<jsp:include page="../../../template/menuizquierda.jsp"></jsp:include>
			<!-- top navigation -->
			<jsp:include page="../../../template/menucabecera.jsp"></jsp:include>
			<!-- /top navigation -->
			<!-- page content -->
			<div class="right_col" role="main">
				<div class="">

					<div class="clearfix"></div>

					<div class="row">

						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_content">

									<hr />
									<h4>Ciudades Transporte de Personal</h4>
									<hr />
									<div style="display: none">
										<sec:authorize access="isAuthenticated()">
											<span id="registro"><sec:authentication
													property="principal.nombreCompleto" /></span>
										</sec:authorize>
									</div>
									<div class="row">
										<div class="col-xs-12 col-md-6 center">
											<input type='button' id='btnnuevaciudad'
												class="btn btn-primary" value='Nueva Ciudad | Ubicación' />
										</div>
									</div>
									<div class="bootstrap-table">
										<div class="fixed-table-container">
											<form id="frmCiudad" action="CambiarEstadoCiudadTDP"
												method="post">
												<div class="row">
													<table id="tablaciudades" data-toggle="table"
														data-locale="es-ES" data-url="listarciudades"
														data-side-pagination="server" data-pagination="true"
														data-response-handler="responseHandler">
														<thead>
															<tr>
																<th class="th-inner" data-field="Codigo">Codigo</th>
																<th class="th-inner" data-field="Detalle">Detalle</th>
																<th class="th-inner" data-field="Estado"
																	data-formatter="estadoCiudades">Estado</th>
																<th class="th-inner" data-field="Codigo"
																	data-formatter="operacionesCiudades">Operaciones</th>
															</tr>
														</thead>
													</table>
												</div>
												<input type="hidden" id="valor" name="ciudad.codigo">
												<!-- <input type="hidden" id="estado" name="ciudad.estado">-->
											</form>
										</div>
									</div>
								</div>
							</div>
							<div class="x_panel">
								<jsp:include page="../../../template/footer-visa.jsp"></jsp:include>
							</div>

						</div>
					</div>

				</div>

			</div>
			<div class="modal fade" id="modalregistrar" tabindex="-1"
				role="dialog">
				<div class="modal-dialog modal-lg" role="document">
					<div class="modal-content">
						<form method="post" action="RegistrarCiudadTDP">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title">Registro de Ciudad | Ubicación</h4>
							</div>
							<div class="modal-body">
								<div class="row">
									<div class="col-xs-12">
										<label>Nombre de Ubicación</label>
									</div>
									<div class="col-xs-12">
										<div class="col-md-12 col-sm-12 col-xs-12">
											<input name="ciudad.detalle" type="text" class="form-control"
												placeholder="Ubicación" required="required">
										</div>
									</div>
								</div>
							</div>
							<div class="modal-footer">

								<div class="row">
									<div class="col-xs-12">
										<button type="submit" id="btnregistrarciudad"
											class="btn btn-success">
											<i class="fa fa-plus"></i>&nbsp;Registrar
										</button>
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Cerrar</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>

			<!-- /page content -->

			<!-- footer content -->

			<!-- /footer content -->
		</div>
	</div>
	<jsp:include page="../../../template/footer.jsp"></jsp:include>

	<script
		src="${pageContext.request.contextPath}/_lib/TransporteDePersonal/administrador/ciudades.js"
		charset="UTF-8"></script>

</body>
</html>
