<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Grupo Palomino</title>

<!-- Bootstrap -->
<link href="_lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Font Awesome -->
<link href="../vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">

<!-- Custom Theme Style -->
<link href="_lib/gentella/custom.css" rel="stylesheet">

<link href="_lib/img/favicon1.ico" rel="shortcut icon" />

<style>
ul {
	list-style-type: none;
	text-align: left;
}
</style>
</head>
<!-- 	 <body style="background-image: url(${pageContext.request.contextPath}/_lib/img/inicio.jpg);background-repeat: no-repeat;
	 background-attachment: fixed;background-size: cover;background-position: center center;
	 center center cover no-repeat fixed;">  -->
<body style="background: #fff;">
	<!--  	 <body class="inicio" > -->
	<div class="">
		<a class="hiddenanchor" id="toregister"></a> <a class="hiddenanchor"
			id="tologin"></a>

		<div id="wrapper">
			<div id="login" class=" form">
				<section class="login_content">
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
					<form action="login" method="post" autocomplete="off">
						<h1>Iniciar sesión</h1>
						<div class="row">
							<div class="col-md-12 col-sm-12 col-xs-12">
								<input name="username" type="text" class="form-control"
									placeholder="Usuario" required="required" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-sm-12 col-xs-12">
								<input name="password" type="password" class="form-control"
									placeholder="Contraseña" required="required" />
							</div>
						</div>
						<div style="text-align: center;"></div>
						<input type="submit" class="btn btn-success  submit"
							value="Iniciar sesión" />

						<div class="clearfix"></div>
						<div class="separator">
							<div class="clearfix"></div>
							<br />
							<div>
								<!-- <h1><i class="fa fa-paw" style="font-size: 26px;"></i> Transporte Palomino S.A.C.</h1> -->
							</div>
							<div>
								<img alt=""
									src="${pageContext.request.contextPath}/_lib/img/logo.png"
									align="middle">
							</div>
							<div>
								<!-- 	<p>El placer de viajar en familia</p> -->
							</div>
						</div>
						<s:token></s:token>
					</form>
				</section>

			</div>
		</div>
	</div>
</body>

<div class="footer_login">
	<div class="col-md-12 col-sm-12 col-xs-12"
		style="text-align: center; background-color: #FEEE00; color: #009045; height: 20px;">
		<!-- 	ABANCAY - ANDAHUAYLAS - AREQUIPA - AYACUCHO - CUSCO - ICA - NASCA - LIMA - PUERTO MALDONADO -->
	</div>
	<div class="col-md-12 col-sm-12 col-xs-12" style="text-align: center;">
		620-2333 Anexo 100 opcion 1/RPC :997 769 498
		ventatelefonica@grupopalomino.com.pe<br> www.grupopalomino.com.pe
	</div>
</div>
</html>