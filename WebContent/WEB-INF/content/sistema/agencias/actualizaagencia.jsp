<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<html lang="es">
<head>
<sj:head locale="%{locale.language}" jquerytheme="start" />
<jsp:include page="../../template/header.jsp"></jsp:include>
</head>

<body class="nav-md">
	<div class="container body">
		<div class="main_container">

			<jsp:include page="../../template/menuizquierda.jsp"></jsp:include>

			<!-- top navigation -->
			<div class="top_nav">

				<div class="nav_menu">
					<nav class="" role="navigation">
						<div class="nav toggle">
							<a id="menu_toggle"><i class="fa fa-bars"></i></a>
						</div>
					</nav>
				</div>

			</div>
			<!-- /top navigation -->

			<!-- page content -->
			<div class="right_col" role="main">
				<div class="">

					<div class="clearfix"></div>

					<div class="row">

						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_content">
									<fieldset>
										<legend>ACTUALIZA AGENCIA</legend>
										<form action="actualizaagencia" method="post"
											autocomplete="off" accept-charset="UTF-8">
											<jsp:include page="_form.jsp"></jsp:include>
											<input type="submit" class="btn btn-success"
												value="ACTUALIZAR">
										</form>
									</fieldset>
								</div>
							</div>
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
	<jsp:include page="../../template/footer.jsp"></jsp:include>
</body>
</html>
