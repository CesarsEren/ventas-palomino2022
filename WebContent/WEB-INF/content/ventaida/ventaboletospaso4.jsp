<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
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

<style>

.parpadea {
  background-color:yellow;
  border-radius:10px;
  animation-name: parpadeo;
  animation-duration: 0.7s;
  animation-timing-function: linear;
  animation-iteration-count: infinite;

  -webkit-animation-name:parpadeo;
  -webkit-animation-duration: 0.7s;
  -webkit-animation-timing-function: linear;
  -webkit-animation-iteration-count: infinite;
}

@-moz-keyframes parpadeo{  
  0% { opacity: 1.0; }
  50% { opacity: 0.0; }
  100% { opacity: 1.0; }
}

@-webkit-keyframes parpadeo {  
  0% { opacity: 1.0; }
  50% { opacity: 0.0; }
   100% { opacity: 1.0; }
}

@keyframes parpadeo {  
  0% { opacity: 1.0; }
   50% { opacity: 0.0; }
  100% { opacity: 1.0; }
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
			<div class="right_col" role="main">
				<div class="">

					<div class="clearfix" style="border-color: #E9F513;">
						<jsp:include page="../template/headerText.jsp"></jsp:include>
					</div>

					<div class="row">

						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_content">
									<jsp:include page="mensajeserver.jsp"></jsp:include>
									<div id="ventawizard">
										<div class="navbar">
											<div class="navbar-inner">
												<div class="container">
													<ul>
														<li><a href="#tab1" data-toggle="tab"><span
																class="badge">1</span>&nbsp;Destino y fechas</a></li>
														<li><a href="#tab2" data-toggle="tab"><span
																class="badge">2</span>&nbsp;Programación</a></li>
														<li><a href="#tab3" data-toggle="tab"><span
																class="badge">3</span>&nbsp;Seleccion de asiento ida</a></li>
														<s:if test="%{#session.paso1Form.idaVuelta}">
															<li class="active"><a href="#tab4"
																aria-expanded="true" data-toggle="tab"><span
																	class="badge">4</span>&nbsp;Seleccion de asiento vuelta</a></li>
														</s:if>
													</ul>
												</div>
											</div>
										</div>
										<div class="tab-content">
											<div class="tab-pane" id="tab1"></div>
											<div class="tab-pane" id="tab2"></div>
											<div class="tab-pane" id="tab3"></div>
											<div class="tab-pane active" id="tab4">
												<jsp:include page="paso4.jsp"></jsp:include>
											</div>
										</div>
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
			<!-- /page content -->

			<!-- footer content -->
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
