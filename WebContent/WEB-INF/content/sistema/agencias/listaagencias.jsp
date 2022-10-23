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
										<form id="frmAgencias" action="cambiaestadoagencia"
											method="post">
											<table id="tablaUsuario" data-toggle="table"
												data-locale="es-ES" data-url="listaagencias"
												data-side-pagination="server" data-pagination="true"
												data-response-handler="responseHandler">
												<thead>
													<tr>
														<th data-field="razonSocial">Razón social</th>
														<th data-field="ruc">Ruc</th>
														<th data-field="correo">Correo</th>
														<th data-field="telefono">Teléfono</th>
														<th data-field="limiteCredito">Limite Credito</th>
														<th data-field="estado" data-formatter="estadoAgencia">Estado
															Agencia</th>
														<th data-field="id" data-formatter="operacionesAgencia">OPERACIONES</th>
													</tr>
												</thead>
											</table>
											<input type="hidden" id="valor" name="agencia.id">
										</form>
									</div>
									<hr />
									<div class="row">
										<a href="nuevaagencia" class="btn btn-primary">NUEVA
											AGENCIA</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /page content -->

			<!-- footer content -->
			<footer>
				<div class="pull-right">Transporte Palomino S.A.C.</div>
				<div class="clearfix"></div>
			</footer>
			<!-- /footer content -->
		</div>
	</div>
	<jsp:include page="../../template/footer.jsp"></jsp:include>
	<script
		src="${pageContext.request.contextPath}/_lib/sistema/agencias/agencias.js"
		charset="UTF-8"></script>


</body>
</html>
