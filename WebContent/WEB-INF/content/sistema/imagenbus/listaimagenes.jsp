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
										<form id="frmUsuario" action="cambiaestadousuario"
											method="post">
											<table id="tablaUsuario" data-toggle="table"
												data-locale="es-ES" data-url="listausuarios"
												data-side-pagination="server" data-pagination="true"
												data-response-handler="responseHandler">
												<thead>
													<tr>
														<th data-field="NomUsuario">USUARIO</th>
														<th data-field="Usuario">NOMBRE COMPLETO</th>
														<th data-field="Nivel">Nivel</th>
														<th data-field="correo">Correo</th>
														<th data-field="estado" data-formatter="estadoUsuario">ESTADO
															USUARIO</th>
														<th data-field="ruc">Ruc</th>
														<th data-field="limitecredito">Limite Credito</th>
														<th data-field="id" data-formatter="operacionesUsuario">OPERACIONES</th>
													</tr>
												</thead>
											</table>
											<input type="hidden" id="valor" name="usuario.id">
										</form>
									</div>
									<hr />
									<div class="row">
										<a href="nuevousuario" class="btn btn-primary">NUEVO
											USUARIO</a>
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
		src="${pageContext.request.contextPath}/_lib/usuarios/usuarios.js"
		charset="UTF-8"></script>


</body>
</html>
