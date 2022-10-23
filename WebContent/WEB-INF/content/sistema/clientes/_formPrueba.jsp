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
	</s:if>
	<div class="row">
		<h2>Registro de cliente</h2>
		<br />
		<p>Para poder continuar con su compra en linea, debe registrarse
			como cliente del sistema. Este registro solo se hace una vez.</p>
		<p>Se enviara una cuenta de acceso del Sistema a su cuenta de
			correo.</p>
	</div>
	<div class="row">
		<div class="form-group col-xs-12">
			<label>Nombres</label>
			<s:textfield name="nombres" cssClass="form-control" maxlength="60"></s:textfield>
		</div>
		<div class="form-group col-xs-12">
			<label>Apellidos</label>
			<s:textfield name="apellidos" cssClass="form-control" maxlength="60"></s:textfield>
		</div>
		<div class="form-group col-xs-12">
			<label>Telefono</label>
			<s:textfield name="telefono" cssClass="form-control" maxlength="15"></s:textfield>
		</div>
		<div class="form-group col-xs-12">
			<label>Identidad</label> <select id="identidad" name="identidad"
				class="form-control">
				<option value="0">D.N.I</option>
				<option value="1">Pasaporte</option>
				<option value="2">Carnet Extranjeria</option>
			</select>
		</div>
		<div class="form-group col-xs-12">
			<label>Nro. Documento</label>
			<s:textfield name="numerodocumento" cssClass="form-control"
				maxlength="15"></s:textfield>
		</div>
	</div>
	<div class="row">
		<div class="form-group col-xs-12">
			<label>E-mail (Nombre de usuario)</label>
			<s:textfield name="email" cssClass="form-control" maxlength="100"></s:textfield>
		</div>
		<div class="form-group col-xs-12">
			<label>Confirme su E-mail</label>
			<s:textfield name="confirm_email" cssClass="form-control"
				maxlength="100"></s:textfield>
		</div>
	</div>
	<div class="row">
		<div class="form-group col-xs-12">
			<label>Contraseña</label>
			<s:textfield type="password" name="password" cssClass="form-control"
				maxlength="10"></s:textfield>
		</div>
	</div>
	<div class="row">
		<div class="form-group col-xs-12">
			<label>Confirmar contraseña</label>
			<s:textfield type="password" name="confirm_password"
				cssClass="form-control" maxlength="10"></s:textfield>
		</div>
	</div>

</body>
</html>