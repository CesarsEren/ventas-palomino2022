<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

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
						<div class="row">

							<div class="x_panel" id="paso2">
								<div class="x_content">
									<h3>Detalle de Venta</h3>
									<form
										action="http://localhost:8080/ventas/PagarVisa?importe=<s:property value="importe"/>&purchaseNumber=<s:property value="purchase"/>&merchantId=<s:property value="merchantId"/>&currency=<s:property value="currency"/>"
										method='post'>
										<script
											src="https://static-content-qas.vnforapps.com/v2/js/checkout.js?qa=true"
											type="text/javascript"
											data-sessiontoken="<s:property value="sessionvisa"/>"
											data-merchantid="<s:property value="merchantId"/>"
											data-channel="web" data-merchantname="Grupo Palomino"
											data-purchasenumber="<s:property value="purchase"/>"
											data-cardholdername="<s:property value="nombre"/>"
											data-cardholderlastname="<s:property value="apellido"/>"
											data-cardholderemail="<s:property value="email"/>"
											data-amount="<s:property value="importe"/>"
											data-timeouturl="http://localhost:8080/ventas/">
											</script>
									</form>
								</div>
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

<!-- <script src="${pageContext.request.contextPath}/_lib/usuarios/usuarios.js" charset="UTF-8"></script>-->

