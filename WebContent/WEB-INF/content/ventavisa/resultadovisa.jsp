<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<sj:head locale="%{locale.language}" jquerytheme="start" />
<jsp:include page="../template/header.jsp"></jsp:include>
</head>

<body class="nav-md">
	<div class="container body">
		<div class="main_container">

			<jsp:include page="../template/menuizquierda.jsp"></jsp:include>

			<!-- top navigation -->
			<jsp:include page="../template/menucabecera.jsp"></jsp:include>
			<!-- /top navigation -->

			<!-- page content -->
			<div class="right_col" role="main">
				<div class="">

					<div class="clearfix"></div>

					<div class="row">

						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_content">
									<s:if test="%{rptaVisa.respuestaServer == true}">
										<div class="row">
											<div class="col-xs-8 col-xs-offset-2">
												<table class="table table-condensed">
													<tbody>
														<tr>

															<td align="center"><img alt=""
																src="${pageContext.request.contextPath}/_lib/img/logo.png"
																height="80px" width="120px"></td>

														</tr>
														<tr>
															<td>Tienda :</td>
															<td>EMP. TRANS. EXPR. INTERNACIONAL PALOMINO S.A.C</td>
														</tr>
														<tr>
															<td>Telefono :</td>
															<td>620-2333</td>
														</tr>
														<tr>
															<td>Direccion Comercial :</td>
															<td>AV. NICOLAS ARRIOLA |Nro ° 910</td>
														</tr>
														<tr>
															<td>Dominio :</td>
															<td>www.grupopalomino.com.pe</td>
														</tr>
														<tr>
															<td>ID. del Pedido :</td>
															<td><s:property value="%{rptaVisa.pedidoID}" /></td>
														</tr>
														<tr>
															<td>Fecha y hora del Pedido :</td>
															<td>Fecha:<s:property value="%{rptaVisa.Fecha}" />
																- Hora:<s:property value="%{rptaVisa.Hora}" /></td>
														</tr>
														<tr>
															<td>Numero de Tarjeta :</td>
															<td><s:property value="%{rptaVisa.Card}" /></td>
														</tr>
														<tr>
															<td>Importe :</td>
															<td><s:property value="%{rptaVisa.Importe}" /> <s:property
																	value="%{rptaVisa.Moneda}" /></td>
														</tr>
														<tr>
															<td>Moneda :</td>
															<td>SOL</td>
														</tr>
														<tr>
															<td>Producto :</td>
															<td>Pasajes de Viaje</td>
														</tr>
														<tr>
															<td>Mensaje</td>
															<td>Si no te llego el Correo, Puedes descargar tu
																Boleto a la izquierda en Mis Compras / Compras /
																Imprimir</td>
														</tr>
														<tr>
															<td>Estimado cliente, se le ha enviado a su correo
																el voucher de la compra, por favor imprimirla para poder
																realizar el canje del pasaje.</td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
										<div class="row">

											<div class="alert alert-success">
												<s:property value="%{rptaVisa.mensajeServer}" />
											</div>

										</div>
									</s:if>
									<s:else>

										<div class="row">


											<div class="col-xs-8 col-xs-offset-2">
												<table class="table table-condensed">
													<tbody>
														<tr>

															<td align="center"><img alt=""
																src="${pageContext.request.contextPath}/_lib/img/logo.png"
																height="80px" width="120px"></td>

														</tr>
														<tr>
															<td>Tienda :</td>
															<td>EMP. TRANS. EXPR. INTERNACIONAL PALOMINO S.A.C</td>
														</tr>
														<tr>
															<td>Número de pedido:</td>
															<td><s:property value="%{rptaVisa.pedidoID}" /></td>
														</tr>
														<tr>
															<td>Fecha y Hora de Pedido:</td>

															<td>Fecha:<s:property value="%{rptaVisa.Fecha}" />
																- Hora:<s:property value="%{rptaVisa.Hora}" /></td>

														</tr>
													</tbody>
												</table>
											</div>
										</div>
										<div class="row">
											<div class="alert alert-danger">
												<s:property value="%{rptaVisa.mensajeServer}" />
											</div>
										</div>
									</s:else>
								</div>
							</div>
							<div class="x_panel">
								<jsp:include page="../template/footer-visa.jsp"></jsp:include>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../template/footer.jsp"></jsp:include>
</body>
</html>
