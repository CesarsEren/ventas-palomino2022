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
			<!-- page content -->
			<div class="col-md-12">
				<div class="col-middle">
					<div class="text-center text-center">
						<h1 class="error-number" style="color: white;">403</h1>
						<h2 style="color: white;">Aceeso denegado</h2>
						<p style="color: white;">Disculpe, no tiene permisos para
							acceder a este recurso.</p>
					</div>
				</div>
			</div>
			<!-- /page content -->
		</div>
	</div>

	<jsp:include page="../template/footer.jsp"></jsp:include>
</body>
</html>