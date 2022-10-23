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
			<jsp:include page="../template/menucabecera.jsp"></jsp:include>
			<div class="right_col" role="main">
				<div class="">
					<div class="clearfix" style="border-color: #E9F513;">
						<div class="col-md-12 col-sm-12 col-xs-12"
							style="font-size: 20px; font-weight: bold; text-align: center; background-color: #FEEE00; color: #009045; background: linear-gradient(#FEEE00, #ccc97e), #D5DC93;">
							ATENCIÓN RECLAMOS - GRUPO PALOMINO</div>
					</div>
					<div class="row">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<jsp:include page="reclamosreporte.jsp"></jsp:include>
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
