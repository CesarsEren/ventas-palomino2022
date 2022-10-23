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
<script type='text/javascript' src="https://openpay.s3.amazonaws.com/openpay.v1.js"></script>
<script type='text/javascript' src="https://openpay.s3.amazonaws.com/openpay-data.v1.js"></script>
<!-- End Google Tag Manager -->
<script>
	/*$("#tblPasajeros").bootstrapTable('refresh', {
		url : 'DetallePasajerosVisa?offset=0&limit=10'
	});
	 */
	 $(document).ready(function(){
			OpenPay.setSandboxMode(true);

			// Setup Form after calling setSandboxMode
			var deviceDataId = OpenPay.deviceData.setup("formpago", "deviceIdField");
			console.log(deviceDataId);
			//var deviceDataId = OpenPay.deviceData.setup();
			
			//$('#deviceId').text(deviceDataId);
			$('#deviceSessionId').val($('#deviceIdField').val());
	});
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

	(function() {
		validaTarjeta();
		validames();
		validaanio();
		validaccv();
	})();

	function detectCardType() {
		validaTarjeta();
		var analiza = $("#tarjeta").val();
		if (analiza.length >= 3) {

			var re = {
				electron : /^(4026|417500|4405|4508|4844|4913|4917)\d+$/,
				maestro : /^(5018|5020|5038|5612|5893|6304|6759|6761|6762|6763|0604|6390)\d+$/,
				dankort : /^(5019)\d+$/,
				interpayment : /^(636)\d+$/,
				unionpay : /^(62|88)\d+$/,
				visa : /^4[0-9]{12}(?:[0-9]{3})?$/,
				mastercard : /^5[1-5][0-9]{14}$/,
				amex : /^3[47][0-9]{13}$/,
				diners : /^3(?:0[0-5]|[68][0-9])[0-9]{11}$/,
				discover : /^6(?:011|5[0-9]{2})[0-9]{12}$/,
				jcb : /^(?:2131|1800|35\d{3})\d{11}$/
			}

			for ( var key in re) {
				console.log(key);
				if (re[key].test(analiza)) {
					//return key
					switch (key) {
						case 'visa' :
							document.getElementById('logotj').src = "https://api.grupopalomino.com.pe/openpay/img/Visa.png";
							break;
						case 'mastercard' :
							document.getElementById('logotj').src = "https://api.grupopalomino.com.pe/openpay/img/mastercard.png";
							break;
						default :
							document.getElementById('logotj').src = "https://api.grupopalomino.com.pe/openpay/img/sntj.png";
							break;
					}
				}
			}
		}

	}

	function validaTarjeta() {
		var a = document.getElementById('tarjeta');
		if (a.value.length > 16) {
			a.value = a.value.slice(0, 16);
		}
	}

	function validames() {
		var a = document.getElementById('mes');
		if (a.value.length > 2) {
			a.value = a.value.slice(0, 2);
		}
	}

	function validaanio() {
		var a = document.getElementById('anio');
		if (a.value.length > 2) {
			a.value = a.value.slice(0, 2);
		}
	}

	function validaccv() {
		var a = document.getElementById('ccv');
		if (a.value.length > 3) {
			a.value = a.value.slice(0, 3);
		}
	}

	const
	btnGrabar = document.querySelector("#pagaopenpay");
	btnGrabar.onclick = function() { 
		pagarReserva(); 
	}
	

	function pagarReserva() {

		//alert($("#cuppon").val());
		
				$.ajax({
					url : 'Pagaropenpay.action',
					type : 'post',
					//cuppon : $('#cuppon').val(),
					data : $("#formpago").serialize(),
					dataType : 'json',
					success : function(res) {
						alert(res);
						console.log(res);
						$("#respuestas").html(res);
						//alert(res.Cuponbean['codigocuppon']);
						//alert(res.cuponbean['montodescuento']);
						//console.log(res.cuponbean.montodescuento);
						//console.log(res.listaPasajeros)

					},
					error : function(xhr, status) {
						alert('Disculpe, existió un problema con la peticion al Servidor ');
					},
				});
	}
</script>
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
					<div class="clearfix" style="border-color: #E9F513;">
						<div class="col-md-12 col-sm-12 col-xs-12 venta-pasajes">
							VENTA DE PASAJES</div>
					</div>
					<div class="row">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_content">
									<jsp:include page="../ventaida/mensajeserver.jsp"></jsp:include>
									<div id="ventawizard">
										<div class="navbar">
											<div class="navbar-inner">
												<div class="container">
													<ul class="nav nav-pills">
														<li class="active"><a href="#tab1" data-toggle="tab" aria-expanded="true"><span class="badge">Openpay</span></a></li>
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
											<div class="tab-pane active" id="tab1">
												<!-- <Iframe src="https://api.grupopalomino.com.pe/openpay/" style="width:70vw; height: 40vh;" scrolling="auto"></Iframe> -->
											</div>
											<div class="tab-pane" id="tab2"></div>
											<div class="tab-pane" id="tab3"></div>
											<div class="tab-pane" id="tab4"></div>
										</div>
										<div class="row container">
											<div class="col-xs-2"></div>
											<div class="col-xs-12 col-md-8 col-xs-offset-2"
												style="margin: auto">
												<div class="x_panel">
													<div class="card-header">
														<div class="row" style="margin-bottom: 50px; margin-top: 15px; margin-right: 25px; margin-left: 25px">
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
																	<%=session.getAttribute("emailopenpay")%>
																	<s:property value="email" />
																</p>
																<br />
															</div>
															<div class="col-md-6">

																<h4 class="text-right">Detalle de Pago</h4>
																<p class="text-right" id="SubTotalPagar">
																	SubTotal :S /
																	<%=session.getAttribute("subtotalopenpay")%>
																	<s:property value="importe" />
																</p>
																<p class="text-right" id="IgvPagar">
																	IGV : S /
																	<%=session.getAttribute("igvopenpay")%></p>
																<p class="text-right" id="TotalPagar">
																	Total : S /
																	<%=session.getAttribute("totalopenpay")%>
																	<s:property value="totalopenpay" />
																</p>

																<br />
															</div>
														</div>
														<div class="bootstrap-table">
															<div class="fixed-table-container">
																<div class="row">
																	<table data-toggle="table" id="tablaitinerarios"
																		data-locale="es-ES" data-side-pagination="server"
																		data-pagination="true" data-url="DetallePasajerosVisa"
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
														
														<form autocomplete="off" id="formpago" method="post" action="pagaropenpay" accept-charset="UTF-8" class="credit-card-div">
															<div class="panel panel-default">
																<div class="panel-heading">

																	<div class="row ">
																		<div class="col-md-12">		
																			<input id="tarjeta" name="tarjeta" type="number" onkeyup="detectCardType();" class="form-control"
																				placeholder="Enter Card Number"/>
																		</div>
																	</div>
																	<div class="row ">
																		<div class="col-md-3 col-sm-3 col-xs-3">
																			<span class="help-block text-muted small-font">
																				Expiry Month</span>
																			<input type="number" class="form-control" id="mes" name="mes"
																				onkeyup="validames()" name="mes"
																				placeholder="MM" />
																		</div>
																		<div class="col-md-3 col-sm-3 col-xs-3">
																			<span class="help-block text-muted small-font">
																				Expiry Year</span>
																			<input type="number" class="form-control" id="anio" name="anio"
																				onkeyup="validaanio()" name="anio"
																				placeholder="YY" />
																		</div>
																		<div class="col-md-3 col-sm-3 col-xs-3">
																			<span class="help-block text-muted small-font">
																				CCV</span>
																			<input type="number" class="form-control" id="ccv" name="ccv" onkeyup="validaccv()" placeholder="CCV" />
																		</div>
																		<div class="col-md-3 col-sm-3 col-xs-3">
																			<img id="logotj" src="/ventas/_lib/img/sntj.png"
																				style="width: 5vw; height: auto;"
																				class="img-rounded" />
																		</div>
																	</div>
																	<div class="row ">
																		<div class="col-md-6 pad-adjust">
																			<input type="text" name="nombre" id="nombre" name="nombre" class="form-control" placeholder="Nombre" />
																		</div>
																		<div class="col-md-6 pad-adjust">
																			<input type="text" name="apellidos" id="apellidos" class="form-control" placeholder="Apellidos" />
																		</div>
																		<div class="col-md-6 pad-adjust">
																			<input type="number" name="telefono"
																				id="telefono" class="form-control"
																				placeholder="Telefono" />
																		</div>
																		<div class="col-md-6 pad-adjust">
																			<input type="email" name="correo"
																				id="correo" class="form-control" placeholder="Email" />
																		</div>
																	</div>
																	<div class="row">
																		<div class="col-md-12 pad-adjust">
																			<input type="hidden" name="monto"
																				id="monto" class="form-control"
																				value="<%=session.getAttribute("totalopenpay")%>" />
																				
																			<input type="hidden" name="deviceSessionId" id="deviceSessionId" value="">
																				
																		</div>
																	</div>
																	<div class="row">
																		<div class="col-md-12 pad-adjust">
																			<div class="checkbox">
																				<div id="respuestas"></div>
																			</div>
																		</div>
																	</div>
																	
																	<div class="row ">
																		<div class="col-md-12 col-sm-12 col-xs-12 pad-adjust">
																			<p>Tus pagos se realizan de forma segura con encriptación de 256 bits</p>
																			<img id="logotj" src="/ventas/_lib/img/openpay.png"
																				style="height: auto;"
																				class="img-rounded" />
																			<img id="logotj" src="/ventas/_lib/img/tarjetasopenpay.png"
																				style="height: auto;"
																				class="img-rounded" />
																			<!-- <input type="button"
																				class="btn btn-warning btn-block" onclick="pagarReserva()"  id="pagaopenpay"
																				value="PAGAR AHORA" />-->
																		</div>
																	</div>
																	
																	<div class="row">
																		<div class="col-md-12 col-sm-12 col-xs-12 pad-adjust">
																			<input type="submit" class="btn btn-success  submit" value="PAGAR AHORA" />
																			<!-- <input type="button"
																				class="btn btn-warning btn-block" onclick="pagarReserva()"  id="pagaopenpay"
																				value="PAGAR AHORA" />-->
																		</div>
																	</div>
																	
																	
																	

																</div>
															</div>	
														</form>													
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
														<!-- PRODUCCION
																 	<form action="https://ventas.grupopalomino.com.pe:8443/ventas/PagarVisa?importe=<s:property value="importe"/>&purchaseNumber=<s:property value="purchase"/>&merchantId=<s:property value="merchantId"/>&currency=<s:property value="currency"/>&sessionvisa=<s:property value="sessionvisa"/>"
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
																	</form>  -->

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
	</div>
	<jsp:include page="../template/footer.jsp"></jsp:include>
	<!-- Google Tag Manager (noscript) -->
	<noscript>
		<iframe src="https://www.googletagmanager.com/ns.html?id=GTM-KBVFZPH"
			height="0" width="0" style="display: none; visibility: hidden"></iframe>
	</noscript>
	<!-- End Google Tag Manager (noscript) -->
</body>
</html>
