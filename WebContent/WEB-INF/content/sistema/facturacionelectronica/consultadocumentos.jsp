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

					<div class="clearfix">
						<h4>CONSULTA DE TICKET VISANET</h4>
					</div>

					<div class="row">

						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_content">
									<div class="row" id="idpanelRegistro">
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
																placeholder="BUSCAR NUMERO DE TICKET" />
														</div>
													</div>
													<hr />
													<div class="row">
														<table id="tablaConsulta" data-toggle="table"
															data-locale="es-ES" data-side-pagination="client"
															data-pagination="true"
															data-response-handler="responseHandler">
															<thead>
																<tr>
																	<th data-field="eticket">Ticket</th>
																	<th data-field="dsc_cod_accion">Fecha</th>
																	<th data-field="dato_comercio">Monto</th>
																	<th data-field="nombre_th">Nombre</th>
																	<th data-field="pedidoID">Pedido</th>
																	<th data-field="nom_emisor">Emisor</th>
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
