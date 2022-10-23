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
									<h4>Programación Salida Transporte de Personal</h4>
									<hr />
									<div style="display: none">
										<sec:authorize access="isAuthenticated()">
											<span id="registro"><sec:authentication
													property="principal.nombreCompleto" /></span>
										</sec:authorize>
									</div>
									<div class="row">
										<div class="col-xs-12 col-md-6 center">
											<a href="registroprogramacion" class="btn btn-primary">Nueva
												Programación</a>
										</div>
									</div>
									<div class="bootstrap-table">
										<div class="fixed-table-container">
											<form id="frmCiudad" action="CambiarEstadoCiudadTDP"
												method="post">
												<div class="row">
													<table data-toggle="table" data-locale="es-ES"
														data-url="listarciudades" data-side-pagination="server"
														data-pagination="true"
														data-response-handler="responseHandler">
														<thead>
															<tr>
																<th class="th-inner">Nro</th>
																<th class="th-inner">Fecha</th>
																<th class="th-inner">Origen-Destino</th>
																<th class="th-inner">Servicio</th>
																<th class="th-inner">Bus</th>
																<th class="th-inner">Salida</th>
																<th class="th-inner">Hora</th>
															</tr>
														</thead>
														</tbody>
													</table>
												</div>
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

			<!-- /page content -->



		</div>
	</div>
	<!-- footer content -->
	<jsp:include page="../../../template/footer.jsp"></jsp:include>
	<!-- /footer content -->
	<script
		src="${pageContext.request.contextPath}/_lib/sistema/bitacora/bitacora.js"
		charset="UTF-8"></script>
	<!-- <script src="${pageContext.request.contextPath}/_lib/usuarios/usuarios.js" charset="UTF-8"></script>-->

</body>
</html>
