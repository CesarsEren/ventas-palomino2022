<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<sj:head locale="%{locale.language}" jquerytheme="start" />
<jsp:include page="../../template/header.jsp"></jsp:include>
<!-- Facebook Pixel Code -->
<script>
		  !function(f,b,e,v,n,t,s)
		  {if(f.fbq)return;n=f.fbq=function(){n.callMethod?
		  n.callMethod.apply(n,arguments):n.queue.push(arguments)};
		  if(!f._fbq)f._fbq=n;n.push=n;n.loaded=!0;n.version='2.0';
		  n.queue=[];t=b.createElement(e);t.async=!0;
		  t.src=v;s=b.getElementsByTagName(e)[0];
		  s.parentNode.insertBefore(t,s)}(window, document,'script',
		  'https://connect.facebook.net/en_US/fbevents.js');
		  fbq('init', '372758356524832');
		  fbq('track', 'PageView');
	</script>
<noscript>
	<img height="1" width="1" style="display: none"
		src="https://www.facebook.com/tr?id=372758356524832&ev=PageView&noscript=1" />
</noscript>
<!-- End Facebook Pixel Code -->
<!-- Global site tag (gtag.js) - Google Analytics -->
<script async
	src="https://www.googletagmanager.com/gtag/js?id=UA-107487118-1"></script>
<script>
		 window.dataLayer = window.dataLayer || [];
		 function gtag(){dataLayer.push(arguments);}
		 gtag('js', new Date());
		 gtag('config', 'UA-107487118-1');
	</script>
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
									<div class="row">
										<div class="col-xs-2"></div>
										<div class="col-xs-5" id="idpanelAcceso">
											<s:if test="%{#session.cambiopagina == false}">
												<s:if test="hasActionErrors()">
													<div class="alert alert-danger">
														<s:actionerror />
													</div>
												</s:if>
											</s:if>
											<s:if test="%{#session.cambiopagina == true}">
												<s:if test="hasActionErrors()">
													<div class="alert alert-danger">
														<s:actionerror />
													</div>
												</s:if>
												<s:if test="hasActionMessages()">
													<div class="alert alert-success">
														<s:actionmessage />
													</div>

												</s:if>
												<form method="post" action="verificacuenta"
													autocomplete="off">
													<div class="row">
														<h2>Olvidaste tu Contraseña</h2>
														<br />
													</div>
													<div class="row">
														<p>
															ingresa una nueva contraseña para tu cuenta de
															<s:label value="%{#session.correo}"></s:label>
														</p>
													</div>

													<div class="row">
														<div class="form-group col-xs-12">
															<label>contraseña</label> <input name="password"
																type="password" class="form-control"
																placeholder="Usuario" required="required" /> <input
																type="hidden" name="username"
																value="<s:property value="%{#session.correo}"/>">
														</div>
													</div>
													<div class="row">
														<div class="form-group col-xs-12">
															<label>confirmar contraseña</label> <input
																name="confirmpassword" type="password"
																class="form-control" placeholder="Contraseña"
																required="required" />
														</div>
													</div>
													<div class="row">
														<input type="submit" class="btn btn-success"
															value="Entrar" />
													</div>
												</form>
											</s:if>
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
			<!-- /page content -->
			<!-- footer content -->
			<!-- /footer content -->
		</div>
	</div>
	<jsp:include page="../../template/footer.jsp"></jsp:include>
</body>
</html>
