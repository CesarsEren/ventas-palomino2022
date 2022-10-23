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

			<!-- top navigation -->

			<!-- /top navigation -->

			<!-- page content -->
			<div class="right_col" role="main">
				<div class="">

					<div class="clearfix"></div>
					<div class="row">
						<s:if test="hasActionErrors()">
							<div class="alert alert-danger">
								<s:actionerror />
							</div>
						</s:if>

						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<h3>Ver Mis Compras</h3>
								<br />
								<br />
								<div class="x_content">
									<div class="row" id="idpanelIngreso">
										<div class="col-xs-8">
											<p>Ingrese su Correo Electrónico y su Clave secreta.</p>
											<br />
											<form method="post" action="miscompras" autocomplete="off">
												<div class="row">
													<div class="form-group col-xs-12">
														<label>Usuario (Correo Electrónico)</label> <input
															name="username" type="text" class="form-control"
															placeholder="Usuario" required="required" />
													</div>
												</div>
												<div class="row">
													<div class="form-group col-xs-12">
														<label>Contraseña</label> <input name="password"
															type="password" class="form-control"
															placeholder="Contraseña" required="required" />
													</div>
												</div>
												<input type="submit" class="btn btn-success"
													value="INGRESAR" />
											</form>
										</div>

									</div>
								</div>
							</div>
							<div class="row">
								<jsp:include page="../../template/footer-visa.jsp"></jsp:include>
							</div>
						</div>
					</div>
					<div id="modalPagina"></div>
				</div>
			</div>
			<!-- /page content -->
			<!-- footer content -->
			<!-- /footer content -->
		</div>
	</div>
	<jsp:include page="../../template/footer.jsp"></jsp:include>
	<script
		src="${pageContext.request.contextPath}/_lib/usuarios/usuarios.js"
		charset="UTF-8"></script>
	<script
		src="${pageContext.request.contextPath}/_lib/clientes/clientes.js"
		charset="UTF-8"></script>

	<script type="text/javascript">
	</script>
</body>
</html>
