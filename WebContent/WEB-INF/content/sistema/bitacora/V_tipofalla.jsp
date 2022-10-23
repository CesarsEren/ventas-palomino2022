<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
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
								<!--  <h2><bold>TIPO FALLA</bold></h2>-->
								<div class="x_content">

									<hr />

									<div class="row">

										<div class="col-xs-12 col-md-4">
											<label>Detalle</label> <input type="text" id="detalle"
												name="detalle" class="form-control" />
										</div>

									</div>


									<hr />

									<div class="row">
										<div class="col-xs-12 col-md-6 center">
											<input type='button' id='btnregistrotipo'
												class="btn btn-primary" value='REGISTRAR TIPO FALLA' />
										</div>

									</div>
								</div>
								<div class="x_content">
									<table class="table" id="tipofalla">
										<thead style="background: #27ae60">
											<tr>
												<th style="color: white">ID</th>
												<th style="color: white">DESCRIPCION</th>
												<th style="color: white">ESTADO</th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
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
		src="${pageContext.request.contextPath}/_lib/sistema/bitacora/tipofallas.js"
		charset="UTF-8"></script>
	<!-- <script src="${pageContext.request.contextPath}/_lib/usuarios/usuarios.js" charset="UTF-8"></script>-->


</body>
</html>
