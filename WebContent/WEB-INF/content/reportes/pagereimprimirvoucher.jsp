<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
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
								<div class="x_title">
									<h2>Imprimir Voucher</h2>
									<ul class="nav navbar-right panel_toolbox">
									</ul>
									<div class="col-md-12 col-sm-6 col-xs-12"
										style="text-align: right;">
										<img alt=""
											src="${pageContext.request.contextPath}/_lib/img/timer.png"
											height="35px" width="35px"> Tiempo restante:
									</div>
									<div class="row">
										<jsp:include page="../template/timer.jsp"></jsp:include>
									</div>
									<div class="clearfix"></div>
								</div>
								<div class="x_content">
									<jsp:include page="reimprimirvoucher.jsp"></jsp:include>
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
</body>
</html>
