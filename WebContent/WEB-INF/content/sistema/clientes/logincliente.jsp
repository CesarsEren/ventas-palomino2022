<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="es">
<head>
</head>
<body>

	<div class="row">
		<a href="#"><b style="font-size: 19px;">YA SOY CLIENTE</b></a> <br />
		<br />
		<p>Ingrese su Correo Electr�nico y su Clave secreta.</p>
	</div>

	<div class="row">
		<div class="form-group col-xs-12">
			<label>Usuario</label> <input name="username" type="text"
				class="form-control" placeholder="Usuario" required="required" />
		</div>
	</div>
	<div class="row">
		<div class="form-group col-xs-12">
			<label>Contrase�a</label> <input name="password" type="password"
				class="form-control" placeholder="Contrase�a" required="required" />
		</div>
	</div>
	<div class="row" style="text-align: left; padding: 15px;">
		<a href="#" id="recupera"><u><b>Olvido su contrase�a?</b> </u></a>
	</div>
</body>
</html>