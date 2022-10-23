<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
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
								<div class="x_content">
									<div class="row">
										<div class="input-group">
											<span class="input-group-btn">
												<button id="btnBusqueda" class="btn btn-default"
													type="button">BUSCAR</button>
											</span> <input id="query" name="query" type="text"
												class="form-control"
												placeholder="BUSCAR POR NUMERO DE DOCUMENTO" />
										</div>
									</div>
									<hr />
									<div class="row">
										<form id="frmClienteRuta" action="cambiaestadoClienteruta"
											method="post">
											<table id="tablaClienteRutas" data-toggle="table"
												data-locale="es-ES" data-url="listaclientesruta"
												data-side-pagination="server" data-pagination="true"
												data-response-handler="responseHandler">
												<thead>
													<tr>
														<th data-field="Cliente">Cliente</th>
														<th data-field="Ruta">Ruta</th>
														<th data-field="Servicio">Servicio</th>
														<th data-field="Precio1">Precio 1</th>
														<th data-field="Precio2">Precio 2</th>
														<th data-field="Id"
															data-formatter="operacionesClienteRuta">OPERACIONES</th>
													</tr>
												</thead>
											</table>
											<input type="hidden" id="valor" name="clienteruta.Id">
										</form>
									</div>
									<hr />
									<div class="row">
										<a href="nuevoclienteruta" class="btn btn-primary">NUEVO
											CLIENTE RUTAS</a>
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
		src="${pageContext.request.contextPath}/_lib/sistema/clientesrutas/clientesrutas.js"
		charset="UTF-8"></script>


</body>
</html>
