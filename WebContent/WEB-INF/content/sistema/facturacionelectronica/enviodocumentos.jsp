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
<style>
fieldset {
	border: 1px groove #ddd !important;
	padding: 0 1.4em 1.4em 1.4em !important;
	margin: 0 0 1.5em 0 !important;
	-webkit-box-shadow: 0px 0px 0px 0px #000;
	box-shadow: 0px 0px 0px 0px #000;
}

legend {
	font-size: 1.2em !important;
	font-weight: bold !important;
	text-align: left !important;
}
</style>
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

					<div class="clearfix">
						<h4>ENVÍO DE DOCUMENTOS ELECTRÓNICOS A LA SUNAT</h4>
					</div>

					<div class="row">

						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_content">
									<div class="row">
										<div class="row">
											<div class="col-md-3 col-sm-6 col-xs-12">
												<label>Documentos</label> <select class="form-control"
													id="documentos" name="documentos">
													<option value="0">DOCUMENTOS PENDIENTES</option>
													<option value="2">DOCUMENTOS RECHAZADOS</option>
												</select>
											</div>
											<div class="col-md-3 col-sm-6 col-xs-12">
												<label>Fecha</label>
												<div class="input-group input-append date"
													id="dateRangePickerDocumentos">
													<input id="fechaDocumentos" type="text"
														class="form-control" name="fechaDocumentos" /> <span
														class="input-group-addon add-on"><span
														class="glyphicon glyphicon-calendar"></span></span>
												</div>
											</div>
											<div class="col-md-3 col-sm-6 col-xs-12">
												<label>Empresa</label> <select class="form-control"
													id="empresa" name="empresa">
													<sec:authorize access="isAuthenticated()">
														<sec:authorize access="hasAnyRole('1')">
															<option value="002">EXPRESO INTERNACIONAL
																PALOMINO SAC</option>
															<option value="003">WARI EXPRESS SAC</option>
															<option value="004">TRANSPORTE WARI SAC</option>
														
														<option value="005">TURISMO PALOMINO</option>
														</sec:authorize>
														<sec:authorize access="hasAnyRole('F')">
															<option
																value="<sec:authentication property="principal.empresa"/>"><sec:authentication
																	property="principal.empresaD" /></option>
														</sec:authorize>
													</sec:authorize>
												</select>
											</div>
											<div class="col-md-3 col-sm-6 col-xs-12"
												style="padding-top: 23px;">
												<input id="btnConsultaDocumentos" type="button"
													class="btn btn-success" value="CONSULTAR DOCUMENTOS">
											</div>
										</div>
										<div class="row">
											<div class="col-xs-12">
												<table id="tblDocumentos" data-click-to-select="true"
													class="table table-condensed table-sm" data-toggle="table"
													data-locale="es-ES" data-pagination="true"
													data-search="server" data-height="380" data-unique-id="id"
													data-side-pagination="server"
													data-response-handler="responseHandler"
													data-query-params="queryParams">
													<thead>
														<tr>
															<th data-field="state" data-checkbox="true"></th>
															<th data-field="id" data-visible="false">Nro</th>
															<th data-field="fechaEmision">Fecha Emision</th>
															<th data-field="empresa">Empresa</th>
															<th data-field="documentoElectronico">Documento</th>
															<th data-field="agencia">Agencia</th>
															<th data-field="servicio" data-visible="false">Servicio</th>
															<th data-field="enviar" data-visible="false">Enviar</th>
															<th data-field="servicioD">Servicio</th>
															<th data-field="respuesta_ose">Respuesta Ose</th>
															<th data-field="estado" data-formatter="FormatterEstado"
																align="center">Estado</th>
															<th data-field="observaciones">Observaciones</th>
															<th data-field="OrdenRef">NroOrdenRef</th>
															<th data-field="acciones"
																data-formatter="FormatterAction" align="center">Acciones</th>
														</tr>
													</thead>
												</table>
											</div>
										</div>


										<div class="clearfix">
											<div class="row"
												style="padding-top: 20px; text-align: center;">
												<div id="divEnvio">
													<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
														<input id="seleccionado" type="radio" name="enviartodos"
															checked="checked" value="false" /> <label
															for="seleccionado">Enviar seleccionados</label> <input
															id="todos" type="radio" name="enviartodos" value="true" />
														<label for="todos">Enviar todos</label> <input
															type="hidden" id="hidTodos" value="false">
													</div>
													<div class="col-lg-2 col-md-3 col-sm-6 col-xs-6">
														<input type="button" id="btnCargar"
															class="btn btn-success" value="GENERAR Y ENVIAR A SUNAT">
													</div>
												</div>
												<div id="divVerifica" style="display: none;">
													<div class="col-lg-2 col-md-3 col-sm-6 col-xs-6">
														<input type="button" id="btnVerificar"
															class="btn btn-success" value="VERIFICAR RECHAZADOS"
															disabled="disabled">
													</div>
												</div>

											</div>
										</div>
									</div>

								</div>
							</div>
							<div class="row">
								<jsp:include page="../../template/footer-visa.jsp"></jsp:include>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../../template/footer.jsp"></jsp:include>


	<div class="modal fade" id="modalEdit" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">
						Modificación de documento :<span id="nrofactura"></span>
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form>
						<fieldset id="seccliente">
							<legend>Datos Cliente:</legend>
							<div class="form-group">
								<label for="dni" class="col-form-label">DNI:</label> <input
									type="text" class="form-control" id="dni">
							</div>
							<div class="form-group">
								<label for="nombre" class="col-form-label">Nombre
									Completo:</label> <input type="text" class="form-control" id="nombre" />
							</div>
						</fieldset>
						<fieldset id="secempresa">
							<legend>Datos Empresa:</legend>
							<div class="form-group">
								<label for="ruc" class="col-form-label">RUC:</label> <input
									class="form-control" type="text" id="ruc">

							</div>
							<div class="form-group">
								<label for="razon" class="col-form-label">Razón Social:</label>
								<input class="form-control" type="text" id="razon">
							</div>
						</fieldset>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Cerrar</button>
					<button type="button" class="btn btn-primary" id="btneditar">Actualizar</button>
				</div>
			</div>
		</div>
	</div>


	<script
		src="${pageContext.request.contextPath}/_lib/sistema/facturacionelectronica/facturacion.js"
		charset="UTF-8"></script>
	<!--<script src="${pageContext.request.contextPath}/_lib/sistema/facturacionelectronica/enviodocumentos.js" charset="UTF-8"></script>-->

	<script type="text/javascript">
		
	</script>
</body>
</html>
