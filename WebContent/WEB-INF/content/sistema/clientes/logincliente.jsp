<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="es">
<head>
</head>
<body>

	<div class="row">
		<a href="#"><b style="font-size: 19px;">YA SOY CLIENTE</b></a> <br />
		<br />
		<p>Ingrese su Correo Electrónico y su Clave secreta.</p>
	</div>

	<div class="row">
		<div class="form-group col-xs-12">
			<label>Usuario</label> <input name="username" type="text"
				class="form-control" placeholder="Usuario" required="required" />
		</div>
	</div>
	<div class="row">
		<div class="form-group col-xs-12">
			<label>Contraseña</label> <input name="password" type="password"
				class="form-control" placeholder="Contraseña" required="required" />
		</div>
	</div>
	<div class="row" style="text-align: left; padding: 15px;">
		<a href="#" id="recupera"><u><b>Olvido su contraseña?</b> </u></a>
	</div>
</body>
</html>