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
								<h4 align="center">
									<s:label value="CONSULTA DE WEBSERVICES - PAGOEFECTIVO">
									</s:label>
								</h4>
								<div class="x_content">
									<div class="row" id="idpanelRegistro">
										<div class="col-md-12 col-sm-12 col-xs-12">
											<div class="x_panel">
												<div class="x_content">
													<div class="row">
														<div class="input-group">
															<span class="input-group-btn">
																<button id="btnBusquedaPagoEfectivo"
																	class="btn btn-default" type="button">BUSCAR</button>
															</span> <input id="query" name="query" type="text"
																class="form-control"
																placeholder="BUSCAR NUMERO DE TICKET" />
														</div>
													</div>
													<hr />
													<div class="row">
														<table id="tablaConsultaPagoEfectivo" data-toggle="table"
															data-locale="es-ES" data-side-pagination="client"
															data-pagination="true"
															data-response-handler="responseHandlerPagoEfectivo">
															<thead>
																<tr>
																	<th data-field="nroCIP">Ticket</th>
																	<th data-field="estado">Estado</th>
																	<th data-field="monto">Monto</th>
																	<th data-field="fechaEmision">Fecha Emision</th>
																	<th data-field="fechaExpirado">Fecha Expiracion</th>
																	<th data-field="fechaExtornado">Fecha Extornado</th>
																	<th data-field="fechaCancelado">Fecha Cancelado</th>
																	<th data-field="usuario">Usuario</th>
																	<th data-field="conceptoPago">Concepto de Pago</th>
																</tr>
															</thead>
														</table>
													</div>
													<hr />
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
	<script
		src="${pageContext.request.contextPath}/_lib/sistema/consultas/consultas.js"
		charset="UTF-8"></script>

	<script type="text/javascript">
	</script>
</body>
</html>
