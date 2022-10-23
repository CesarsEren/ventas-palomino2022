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
			<label>Servicio</label> <select class="form-control"
				name="bean.servicio">
				<s:iterator value="%{#session.listaServicios}">
					<option value="<s:property value="servicio"/>"><s:property
							value="servicioD" /></option>
				</s:iterator>
			</select>
		</div>
	</div>
	<div class="row">
		<div class="form-group col-xs-6">
			<label class="control-label">Select File</label> <input id="input-6"
				name="input6[]" type="file" multiple class="file-loading">
		</div>
	</div>
	<div class="row">
		<div class="form-group col-xs-6">
			<label>Estado</label> <select class="form-control"
				name="usuario.estado">
				<option value="Y">ACTIVO</option>
				<option value="N">INACTIVO</option>
			</select>
		</div>
	</div>
	<s:textfield type="hidden" name="usuario.id"></s:textfield>
	<script
		src="${pageContext.request.contextPath}/_lib/usuarios/Decimales.js"
		charset="UTF-8"></script>
	<script
		src="${pageContext.request.contextPath}/_lib/venta/onkeypress.js"
		charset="UTF-8"></script>
</body>
</html>