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
<script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
	new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
	j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
	'https://www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
	})(window,document,'script','dataLayer','GTM-KBVFZPH');</script>
<!-- End Google Tag Manager -->
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
												<div id="mensajevisa"></div>
												<jsp:include page="visa.jsp"></jsp:include>
											</div>
											<div class="tab-pane" id="tab2"></div>
											<div class="tab-pane" id="tab3"></div>
											<div class="tab-pane" id="tab4"></div>
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
	<jsp:include page="footer.jsp"></jsp:include>
	<!-- Google Tag Manager (noscript) -->
	<noscript>
		<iframe src="https://www.googletagmanager.com/ns.html?id=GTM-KBVFZPH"
			height="0" width="0" style="display: none; visibility: hidden"></iframe>
	</noscript>
	<!-- End Google Tag Manager (noscript) -->
</body>
</html>
