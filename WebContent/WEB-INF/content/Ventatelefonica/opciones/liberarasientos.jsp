<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<html lang="es">
<head>
<sj:head locale="%{locale.language}" jquerytheme="start" />
<jsp:include page="../../template/header.jsp"></jsp:include>

</head>

<body class="nav-md">
	<div class="container body">
		<div class="main_container">

			<jsp:include page="../../template/menuizquierda.jsp"></jsp:include>

			<!-- top navigation -->
			<div class="top_nav">

				<div class="nav_menu">
					<nav class="" role="navigation">
						<div class="nav toggle">
							<a id="menu_toggle"><i class="fa fa-bars"></i></a>
						</div>
					</nav>
				</div>

			</div>
			<!-- /top navigation -->

			<!-- page content -->
			<div class="right_col" role="main">
				<div class="">
					<div class="clearfix">
						<div class="col-md-12 col-sm-12 col-xs-12 venta-pasajes">
							Liberación de Ventas Telefónicas</div>
					</div>
					<div class="row">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_content">

									<div class="row">
										<div class="col-md-4 col-md-xs-12">
											<label>Usuario de Venta Telefonica :</label>
											<div class="input-group">
												<select class="form-control" id="selectUsuario">
													<option id="Todos">Todos</option>
												</select>
											</div>
										</div>
										<div class="col-md-5 col-md-xs-12"></div>
										<div class="col-md-1 col-md-xs-12">
											<label>Liberar</label>
											<div class="input-group">
												<button class="form-control btn btn-primary" type="button">NetBus</button>
											</div>
										</div>

										<div class="col-md-1 col-md-xs-12">
											<label>Liberar</label>
											<div class="input-group">
												<button id="btnRedBus" class="form-control btn btn-primary"
													type="button">RedBus</button>
											</div>
										</div>
										<div class="col-md-1 col-md-xs-12">
											<label>Liberar</label>
											<div class="input-group">
												<button class="form-control btn btn-primary" type="button"
													id="btnWeb">Clientes</button>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-12">

											<div class="bootstrap-table">
												<div class="fixed-table-container">
													<div class="row">
														<table data-toggle="table" id="tablaventas"
															data-locale="es-ES" data-side-pagination="server"
															data-pagination="true"
															data-url="listventasventelefonicas?todos=true"
															data-response-handler="responseHandler">
															<thead>
																<tr>
																	<!--<th data-field="id" data-visible="false"></th>-->
																	<th data-field="nro">Codigo Único</th>
																	<th data-field="destinoD">Destino</th>
																	<th data-field="asiento">Asiento</th>
																	<th data-field="fechaViaje">Fecha de Viaje</th>
																	<th data-field="fechaEmision">Fecha de Emisión</th>
																	<th data-field="identidadD">Tipo Documento</th>
																	<th data-field="DNI">Documento</th>
																	<th data-field="nombre">Nombre</th>
																	<th data-field="fechaCaducidadWeb">Fecha de
																		Vencimiento</th>
																	<th data-field="eticket">Código CIP</th>
																	<th data-field="usuarioVisa">Usuario</th>
																	<th data-formatter="acciones">Acción</th>
																</tr>
															</thead>
														</table>

													</div>
												</div>
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
			<!-- /page content -->

			<!-- footer content -->




			<!-- /footer content -->
		</div>
	</div>
	<jsp:include page="../../template/footer.jsp"></jsp:include>
	<script
		src="${pageContext.request.contextPath}/_lib/Ventatelefonica/opciones/liberarasientos.js"
		charset="UTF-8"></script>
</body>
</html>
