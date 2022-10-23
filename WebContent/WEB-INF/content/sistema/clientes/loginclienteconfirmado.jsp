<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="es">
<head>
</head>
<body>

	<s:if test="hasActionErrors()">
		<div class="alert alert-danger">
			<s:actionerror />
		</div>
	</s:if>
	<s:if test="hasActionMessages()">
		<div class="alert alert-success">
			<s:actionmessage />
		</div>
		<form method="post" action="login" autocomplete="off">
			<div class="row">
				<h2>Cliente registrado</h2>
				<br />
				<p>ingresar aqui su correo y contrase�a para culminar su compra.</p>
			</div>

			<div class="row">
				<div class="form-group col-xs-12">
					<label>Usuario(correo electronico)</label> <input name="username"
						type="text" class="form-control" placeholder="Usuario"
						required="required" />
				</div>
			</div>
			<div class="row">
				<div class="form-group col-xs-12">
					<label>Contrase�a</label> <input name="password" type="password"
						class="form-control" placeholder="Contrase�a" required="required" />
				</div>
			</div>
			<div class="row">
				<input type="submit" class="btn btn-success" value="Entrar" />
			</div>
		</form>
	</s:if>
</body>
</html>