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

	<div class="row">
		<div class="form-group col-xs-12">
			<label>Direccion</label>
			<s:textfield cssClass="form-control" name="agencia.direccion"
				maxlength="150">
			</s:textfield>
		</div>
	</div>

	<div class="row">
		<div class="form-group col-xs-12">
			<label>Nombre del responsable de la agencia</label>
			<s:textfield cssClass="form-control" name="agencia.personaContacto"
				maxlength="200">
			</s:textfield>
		</div>
	</div>
	<div class="row">
		<div class="form-group col-xs-6">
			<label>Limite credito</label>
			<s:textfield data-toggle="tooltip"
				title="El limite de credito debe contener un punto(.) y dos decimales mínimo."
				cssClass="form-control" name="agencia.limiteCredito" maxlength="8"></s:textfield>
		</div>
		<div class="form-group col-xs-6">
			<label>Correo</label>
			<s:textfield cssClass="form-control" name="agencia.correo"
				maxlength="200"></s:textfield>
		</div>

	</div>
	<div class="row">
		<div class="form-group col-xs-6">
			<label>Ruc</label>
			<s:textfield cssClass="form-control" name="agencia.ruc"
				maxlength="11">
			</s:textfield>
		</div>
		<div class="form-group col-xs-6">
			<label>Razon social</label>
			<s:textfield cssClass="form-control" name="agencia.razonSocial"
				maxlength="150"></s:textfield>
		</div>
	</div>

	<div class="row">
		<div class="form-group col-xs-6">
			<label>Telefono</label>
			<s:textfield cssClass="form-control" name="agencia.telefono"
				maxlength="15"></s:textfield>
		</div>
		<div class="form-group col-xs-6">
			<label>Estado</label> <select class="form-control"
				name="agencia.estado">
				<option value="Y">ACTIVA</option>
				<option value="N">INACTIVA</option>
			</select>
		</div>
	</div>
	<s:textfield type="hidden" name="agencia.id"></s:textfield>
</body>

</html>