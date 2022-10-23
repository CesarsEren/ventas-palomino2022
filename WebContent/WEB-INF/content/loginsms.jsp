<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>

<sj:head locale="%{locale.language}" jquerytheme="start" />
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

<link href="_lib/animate/animate.css" rel="stylesheet">
<style>
ul {
	list-style-type: none;
	text-align: left;
}
</style>
</head>

<body style="background: #F7F7F7;">
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

					<form id="form1" autocomplete="off">
						<h1>Ingrese su numero de celular</h1>
						<div id="mensajeServer"></div>
						<div>
							<input name="telefono" type="text" class="form-control"
								maxlength="15" placeholder="Numero celular" required="required" />
						</div>
						<div>
							<input id="btnEnviarNumero" type="button"
								class="btn btn-default submit" value="Enviar numero" />
						</div>
						<div class="clearfix"></div>
						<div class="separator">
							<div class="clearfix"></div>
							<br />
							<div>
								<h1>
									<i class="fa fa-paw" style="font-size: 26px;"></i> Transporte
									Palomino S.A.C.
								</h1>

								<p>El placer de viajar en familia</p>
							</div>
						</div>
					</form>

					<form id="form2" action="login" method="post" autocomplete="off">
						<h1>Por favor ingrese el codigo enviado a su celular</h1>
						<div>
							<input name="username" type="text" class="form-control"
								placeholder="Codigo" required="required" /> <input
								type="hidden" name="password" value="123" />
						</div>
						<input id="btnEnviarCodigo" type="button"
							class="btn btn-default submit" value="Iniciar sesión" />
						<div class="clearfix"></div>
						<div class="separator">
							<div class="clearfix"></div>
							<br />
							<div>
								<h1>
									<i class="fa fa-paw" style="font-size: 26px;"></i> Transporte
									Palomino S.A.C.
								</h1>

								<p>El placer de viajar en familia</p>
							</div>
						</div>
					</form>

				</section>
			</div>
		</div>
	</div>

	<script>
		$(function(){
			
			$("#form2").hide();
			
			$("#btnEnviarNumero").on("click", function(){
				
				$.ajax({
					type 	: "post",
					url		: "enviosms",
					data	: $("#form1").serialize(),
					success	:function(res){
						if(res.error == true){
							$("#mensajeServer").addClass("alert alert-danger").html(res.mensajeServer);
						}
						else{
							$("#mensajeServer").removeClass().html("");
							$("#form1").addClass('animated ' + 'bounceOutLeft');
							$("#form1").hide();
							$("#form2").show().addClass('animated ' + 'bounceInLeft');
						}
					}
				});
				
			});
			
			$("#btnEnviarCodigo").on("click", function(){
				$("#form2").submit();
			});
		});
	</script>
</body>
</html>