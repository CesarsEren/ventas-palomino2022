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

									<hr />


									<div class="row">

										<div class="col-xs-12 col-md-6">
											<label>Asunto</label> <input name="asunto" id="asunto"
												type="text" placeholder="Ingrese Asunto"
												class="form-control" data-toggle="tooltip"
												data-original-title="Ingrese El Asunto" />

										</div>

										<div class="col-xs-12 col-md-3">
											<label>Categoria Falla</label> <select
												class="form-control fallas" name="listacategoria"
												id="listacategoria">
												<option value="-1">-- Seleccione Categoria --</option>
											</select>
										</div>


										<div class="col-xs-12 col-md-3">
											<label>SubCategoria Falla</label> <select
												class="form-control fallas" name="listasubcategoria"
												id="listasubcategoria">
												<option value="-1">-- Seleccione SubCategoria --</option>
											</select>
										</div>


									</div>

									<div class="row">


										<div class="col-xs-12 col-md-6">
											<label>Nro Bus</label> <input name="nrobus" id="nrobus"
												type="text" placeholder="Ingrese Nro de Bus"
												class="form-control" data-toggle="tooltip"
												data-original-title="Ingrese El Nro del Bus">
										</div>
										<div class="col-xs-12 col-md-6">

											<label>Ruta</label> <select class="form-control ruta"
												name="listarutas" id="listarutas">
												<option value="-1">-- Seleccione Ruta --</option>
											</select>
										</div>
									</div>

									<div class="row">

										<div class="col-xs-12 col-md-6">
											<div class="form-group purple-border">
												<label for="descripcion">Descripción de la Falla</label>
												<textarea class="form-control" id="descripcion" rows="3"
													maxlength='300'></textarea>
											</div>
										</div>
										<div class="col-xs-12 col-md-6">
											<!-- <form id="uploadForm" method='post'
												enctype="multipart/form-data">-->
											<label>Cargar Foto</label> <br /> <input type='file'
												name="upFile" id="upFile" required capture="camera" /> <input
												type="hidden" id="key1" value="AKIAJFB26PUAUYAFQ43A" /> <input
												type="hidden" id="key2"
												value="LfNWu9T5xE9Em5s0PnObPfsMU2kF+76OzBubqbrk" />
											<!-- </form>-->
										</div>
									</div>

									<hr />
									<div style="display: none">
										<sec:authorize access="isAuthenticated()">
											<span id="registro"><sec:authentication
													property="principal.nombreCompleto" /></span>
										</sec:authorize>
									</div>
									<div class="row">
										<div class="col-xs-12 col-md-6 center">
											<input type='button' id='btnenviar' class="btn btn-primary"
												value='REGISTRAR FALLA' />
										</div>
									</div>
									<!--
										<div class="row">
											<div class="col-xs-12 col-md-6 center">
												<button id="registrarfalla" name="registrarfalla"
													class="btn btn-primary">REGISTRAR FALLA</button>
											</div>
										</div>
										-->


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
		src="${pageContext.request.contextPath}/_lib/sistema/bitacora/bitacora.js"
		charset="UTF-8"></script>
	<!-- <script src="${pageContext.request.contextPath}/_lib/usuarios/usuarios.js" charset="UTF-8"></script>-->


</body>
</html>
