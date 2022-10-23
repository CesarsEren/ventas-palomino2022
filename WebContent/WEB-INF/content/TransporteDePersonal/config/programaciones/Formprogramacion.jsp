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
										<div class="col-xs-12 col-md-12 center">
											<h5>Nueva Programación</h5>

											<div class="row">
												<div class="col-xs-12 col-md-2">
													<label>Del</label> <input class="form-control" type="date" />
												</div>
												<div class="col-xs-12 col-md-2">
													<label>Al</label> <input class="form-control" type="date" />
												</div>

												<div class="col-xs-12 col-md-3">
													<label>Destino</label> <select class="form-control"></select>
												</div>
												<div class="col-xs-12 col-md-3">
													<label>Servicio</label> <select class="form-control"></select>
												</div>
												<div class="col-xs-12 col-md-2">
													<label>Bus</label> <select class="form-control"></select>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-xs-12 col-md-6">
											<div class="col-xs-12 col-md-3">
												<label>Terr/Ayuda</label> <input class="form-control"
													type="date" />
											</div>
											<div class="col-xs-12 col-md-3">
												<label>Codigo </label> <input class="form-control"
													type="date" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-xs-12 col-md-6">
											<div class="col-xs-12 col-md-3">
												<label>Piloto</label> <input class="form-control"
													type="date" />
											</div>
											<div class="col-xs-12 col-md-3">
												<label>Codigo </label> <input class="form-control"
													type="date" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-xs-12 col-md-6">
											<div class="col-xs-12 col-md-3">
												<label>Co-Piloto</label> <input class="form-control"
													type="date" />
											</div>
											<div class="col-xs-12 col-md-3">
												<label>Codigo </label> <input class="form-control"
													type="date" />
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

				<!-- footer content -->

				<!-- /footer content -->
			</div>
		</div>
		<jsp:include page="../../../template/footer.jsp"></jsp:include>
		<script
			src="${pageContext.request.contextPath}/_lib/sistema/bitacora/bitacora.js"
			charset="UTF-8"></script>
		<!-- <script src="${pageContext.request.contextPath}/_lib/usuarios/usuarios.js" charset="UTF-8"></script>-->
</body>
</html>
