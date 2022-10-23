<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<sj:head locale="%{locale.language}" jquerytheme="start" />
<jsp:include page="../template/header.jsp"></jsp:include>
<!-- Google Tag Manager -->

<script>
	/*$("#tblPasajeros").bootstrapTable('refresh', {
		url : 'DetallePasajerosVisa?offset=0&limit=10'
	});
	 */

	function responseHandler(res) {
		return res.resultados;
	}

	(function(w, d, s, l, i) {
		w[l] = w[l] || [];
		w[l].push({
			'gtm.start' : new Date().getTime(),
			event : 'gtm.js'
		});
		var f = d.getElementsByTagName(s)[0], j = d.createElement(s), dl = l != 'dataLayer'
				? '&l=' + l
				: '';
		j.async = true;
		j.src = 'https://www.googletagmanager.com/gtm.js?id=' + i + dl;
		f.parentNode.insertBefore(j, f);
	})(window, document, 'script', 'dataLayer', 'GTM-KBVFZPH');
</script>
<!--<script src="http://static-content.vnforapps.com/v2/js/checkout.js"></script>-->
<!-- End Google Tag Manager -->
<style type="text/css">
#loader-wrapper {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	z-index: 1000;
}

#loader {
	display: block;
	position: relative;
	left: 50%;
	top: 50%;
	width: 150px;
	height: 150px;
	margin: -75px 0 0 -75px;
	border-radius: 50%;
	border: 3px solid transparent;
	border-top-color: #3498db;
	-webkit-animation: spin 2s linear infinite;
	/* Chrome, Opera 15+, Safari 5+ */
	animation: spin 2s linear infinite;
	/* Chrome, Firefox 16+, IE 10+, Opera */
}

#loader:before {
	content: "";
	position: absolute;
	top: 5px;
	left: 5px;
	right: 5px;
	bottom: 5px;
	border-radius: 50%;
	border: 3px solid transparent;
	border-top-color: #e74c3c;
	-webkit-animation: spin 3s linear infinite;
	/* Chrome, Opera 15+, Safari 5+ */
	animation: spin 3s linear infinite;
	/* Chrome, Firefox 16+, IE 10+, Opera */
}

#loader:after {
	content: "";
	position: absolute;
	top: 15px;
	left: 15px;
	right: 15px;
	bottom: 15px;
	border-radius: 50%;
	border: 3px solid transparent;
	border-top-color: #f9c922;
	-webkit-animation: spin 1.5s linear infinite;
	/* Chrome, Opera 15+, Safari 5+ */
	animation: spin 1.5s linear infinite;
	/* Chrome, Firefox 16+, IE 10+, Opera */
}

@
-webkit-keyframes spin { 0% {
	-webkit-transform: rotate(0deg); /* Chrome, Opera 15+, Safari 3.1+ */
	-ms-transform: rotate(0deg); /* IE 9 */
	transform: rotate(0deg); /* Firefox 16+, IE 10+, Opera */
}

100%
{
-webkit-transform


:

 

rotate


(360
deg
); /* Chrome, Opera 15+, Safari 3.1+ */


            

-ms-transform


:

 

rotate


(360
deg
); /* IE 9 */


            

transform


:

 

rotate


(360
deg
); /* Firefox 16+, IE 10+, Opera */


        

}
}
@
keyframes spin { 0% {
	-webkit-transform: rotate(0deg); /* Chrome, Opera 15+, Safari 3.1+ */
	-ms-transform: rotate(0deg); /* IE 9 */
	transform: rotate(0deg); /* Firefox 16+, IE 10+, Opera */
}
100%
{
-webkit-transform


:

 

rotate


(360
deg
); /* Chrome, Opera 15+, Safari 3.1+ */


            

-ms-transform


:

 

rotate


(360
deg
); /* IE 9 */


            

transform


:

 

rotate


(360
deg
); /* Firefox 16+, IE 10+, Opera */


        

}
}
</style>
</head>

<body class="nav-md">
	<div class="container body">
		<div class="main_container">

			<jsp:include page="../template/menuizquierda.jsp"></jsp:include>

			<!-- top navigation -->
			<jsp:include page="../template/menucabecera.jsp"></jsp:include>
			<!-- /top navigation -->

			<!-- page content -->
			<div id="demo-content">

				<!--<div id="loader-wrapper" style="display: none">
					<div id="loader"></div>
					<p>Estamos Generando su Boletos y Enviadolos a su correo espere por favor.</p>
				</div>-->

			</div>
			<div id="formprincipal" class="right_col" role="main">
				<div class="">
					<div class="clearfix" style="border-color: #E9F513;">
						<div class="col-md-12 col-sm-12 col-xs-12 venta-pasajes">
							VENTA DE PASAJES</div>
					</div>
					<div class="row">
						<div class="col-md-12 col-sm-12 col-xs-12" style="margin: auto">
							<div class="x_panel">
								<div class="x_content">
									<jsp:include page="../ventaida/mensajeserver.jsp"></jsp:include>
									<div id="ventawizard">
										<div class="navbar">
											<div class="navbar-inner">
												<div class="container">
													<ul>
														<li><a href="#tab1" data-toggle="tab"><span
																class="badge">Transacción de Pago Visa</span>&nbsp;</a></li>
													</ul>
												</div>
											</div>
											<div class="row">
												<div class="col-md-12 col-sm-12 col-xs-12"
													style="text-align: right;">
													<img alt=""
														src="${pageContext.request.contextPath}/_lib/img/timer.png"
														height="35px" width="35px"> Tiempo restante:
												</div>
											</div>
											<div class="row">
												<jsp:include page="../template/timer.jsp"></jsp:include>
											</div>
										</div>
										<div class="tab-content">
											<div class="tab-pane" id="tab1">
												<!--  
									<div class="row">
										<sec:authorize access="hasAnyRole('SMS')">
							            	<div class="pull-right">
							            		<div class="your-clock"></div>
							            	</div>
							            </sec:authorize>
									</div>
								-->
												<!-- <div id="mensajevisa"></div>-->
												<!--<jsp:include page="visa.jsp"></jsp:include>-->
												<div class="row container">
													<div class="col-xs-2"></div>
													<div class="col-xs-12 col-md-8 col-xs-offset-2"
														style="margin: auto">
														<div class="x_panel">
															<div class="card-header">
																<div class="row"
																	style="margin-bottom: 50px; margin-top: 15px; margin-right: 25px; margin-left: 25px">
																	<div class="col-md-6">
																		<figure>
																			<img src="/ventas/_lib/img/logoMenu.png" width="70px"
																				height="64px" />
																		</figure>
																	</div>
																	<div class="col-md-6">
																		<p class="text-right">
																			Vouchers que serán emitidos </br> Parar Canjear
																		</p>
																	</div>
																</div>
															</div>
															<hr style="size: 2px" />
															<div class="card-body">
																<div class="row"
																	style="margin-bottom: 35px; margin-top: 15px; margin-right: 25px; margin-left: 25px">
																	<div class="col-md-6">
																		<h2>Información del Cliente</h2>

																		<br /> <label>Se Enviarán los vouchers al ,
																			Email :</label>
																		<p>
																			<s:property value="email" />
																		</p>
																		<br />
																	</div>
																	<div class="col-md-6">

																		<h4 class="text-right">Detalle de Pago</h4>
																		<p class="text-right" id="SubTotalPagar">
																			SubTotal :S /
																			<s:property value="importe" />
																		</p>
																		<p class="text-right" id="IgvPagar">IGV : S / 0.00</p>
																		<p class="text-right" id="TotalPagar">
																			Total : S /
																			<s:property value="importe" />
																		</p>

																		<br />
																	</div>
																</div>
																<div class="bootstrap-table">
																	<div class="fixed-table-container">
																		<div class="row">
																			<table data-toggle="table" id="tablaitinerarios"
																				data-locale="es-ES" data-side-pagination="server"
																				data-pagination="true"
																				data-url="DetallePasajerosVisa"
																				data-response-handler="responseHandler">
																				<thead>
																					<tr>
																						<!--<th data-field="id" data-visible="false"></th>-->
																						<th data-field="nroCC">Codigo único</th>
																						<th data-field="nombre">Nombre</th>
																						<th data-field="asiento">Asiento</th>
																						<th data-field="destinoD">Destino</th>
																						<th data-field="precioAct">Precio</th>
																					</tr>
																				</thead>
																			</table>

																		</div>
																	</div>
																</div>
															</div>

															<hr style="size: 2px">
															<div class="card-footer">
																<div class="text-right">
																	<!-- action="http://service.grupopalomino.com.pe:8090/ventas/PagarVisa?importe=<s:property value="importe"/>&purchaseNumber=<s:property value="purchase"/>&merchantId=<s:property value="merchantId"/>&currency=<s:property value="currency"/>" -->


																	<!-- al.36 
																	<form action="http://service.grupopalomino.com.pe:8090/ventas/PagarVisa?importe=<s:property value="importe"/>&purchaseNumber=<s:property value="purchase"/>&merchantId=<s:property value="merchantId"/>&currency=<s:property value="currency"/>&sessionvisa=<s:property value="sessionvisa"/>"
																		method='post'> 
																		<script
																			src="https://static-content.vnforapps.com/v2/js/checkout.js"
																			type="text/javascript"
																			data-sessiontoken="<s:property value="sessionvisa"/>"
																			data-merchantid="<s:property value="merchantId"/>"
																			data-channel="web" data-merchantname="Grupo Palomino"
																			data-purchasenumber="<s:property value="purchase"/>"
																			data-cardholdername="<s:property value="nombre"/>"
																			data-cardholderlastname="<s:property value="apellido"/>"
																			data-cardholderemail="<s:property value="email"/>"
																			data-amount="<s:property value="importe"/>"
																			data-timeouturl="http://service.grupopalomino.com.pe:8090/ventas/">
																			
																		</script>
																	</form> -->
																	<!-- PRODUCCION-->
																	<form
																		action="https://ventas.grupopalomino.com.pe:8443/ventas/PagarVisa?importe=<s:property value="importe"/>&purchaseNumber=<s:property value="purchase"/>&merchantId=<s:property value="merchantId"/>&currency=<s:property value="currency"/>&sessionvisa=<s:property value="sessionvisa"/>"
																		method='post'>
																		<script
																			src="https://static-content.vnforapps.com/v2/js/checkout.js"
																			type="text/javascript"
																			data-sessiontoken="<s:property value="sessionvisa"/>"
																			data-merchantid="<s:property value="merchantId"/>"
																			data-channel="web" data-merchantname="Grupo Palomino"
																			data-purchasenumber="<s:property value="purchase"/>"
																			data-cardholdername="<s:property value="nombre"/>"
																			data-cardholderlastname="<s:property value="apellido"/>"
																			data-cardholderemail="<s:property value="email"/>"
																			data-amount="<s:property value="importe"/>"
																			data-timeouturl="https://ventas.grupopalomino.com.pe:8443/ventas/">
																		</script>
																	</form>

																	<!--  ambiente de Test en local 
																	  <form 
																		action="PagarVisa?importe=<s:property value="importe"/>&purchaseNumber=<s:property value="purchase"/>&merchantId=<s:property value="merchantId"/>&currency=<s:property value="currency"/>&sessionvisa=<s:property value="sessionvisa"/>"
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
																			data-timeouturl="http://127.0.0.1:8080/ventas/">
																			
																		</script>
																	</form> 
	 aqui de pone el cierre de test local -->
																</div>

															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<jsp:include page="../template/footer-visa.jsp"></jsp:include>
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

	<jsp:include page="footer.jsp"></jsp:include>

	<!-- Google Tag Manager (noscript) -->
	<noscript>
		<iframe src="https://www.googletagmanager.com/ns.html?id=GTM-KBVFZPH"
			height="0" width="0" style="display: none; visibility: hidden"></iframe>
	</noscript>
	<!-- End Google Tag Manager (noscript) -->

</body>
</html>
