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
		<div class="form-group col-xs-6">
			<label>Plataforma</label> <select class="form-control"
				name="version.Plataforma">
				<option value="Android">Android</option>
				<option value="I.O.S">I.O.S</option>
			</select>
		</div>
		<div class="form-group col-xs-6">
			<label>Fecha Actualizacion</label>
			<div class="input-group input-append date"
				id="dateRangePickerVersion">
				<s:textfield id="fechaVersion" type="text" class="form-control"
					name="version.Fecha" />
				<span class="input-group-addon add-on"><span
					class="glyphicon glyphicon-calendar"></span></span>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group col-xs-6">
			<label>Version</label>
			<s:textfield cssClass="form-control" name="version.Version_App"
				maxlength="30"></s:textfield>
		</div>
		<div class="form-group col-xs-6">
			<label>Critico</label> <select class="form-control"
				name="version.Critico">
				<option value="S">SI</option>
				<option value="N">NO</option>
			</select>
		</div>

	</div>
	<s:textfield type="hidden" name="version.id"></s:textfield>
	<script
		src="${pageContext.request.contextPath}/_lib/versionesapp/versionapp.js"
		charset="UTF-8"></script>
</body>
</html>