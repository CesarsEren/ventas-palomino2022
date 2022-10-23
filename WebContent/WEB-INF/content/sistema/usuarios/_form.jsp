<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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
			<label>Usuario</label>
			<s:textfield data-toggle="tooltip" cssClass="form-control"
				title="Máximo 12 Caracteres" name="usuario.username" maxlength="12"></s:textfield>
		</div>
		<div class="form-group col-xs-6">
			<label>Nombres</label>
			<s:textfield data-toggle="tooltip" cssClass="form-control"
				name="usuario.nombres" title="Máximo 50 Caracteres" maxlength="50"></s:textfield>
		</div>
	</div>
	<div class="row">
		<div class="form-group col-xs-6">
			<label>Apellido Paterno</label>
			<s:textfield data-toggle="tooltip" cssClass="form-control"
				name="usuario.apellidoPaterno" title="Máximo 50 Caracteres"
				maxlength="50"></s:textfield>
		</div>
		<div class="form-group col-xs-6">
			<label>Apellido Materno</label>
			<s:textfield data-toggle="tooltip" cssClass="form-control"
				name="usuario.apellidoMaterno" title="Máximo 50 Caracteres"
				maxlength="50"></s:textfield>
		</div>
	</div>
	<div class="row">
		<div class="form-group col-xs-6">
			<label>Ruc</label>
			<s:textfield data-toggle="tooltip" cssClass="form-control"
				name="usuario.ruc" title="Obligatorio 11 Caracteres" maxlength="11"
				onblur="javascript:RucExistenteTDP(this);"></s:textfield>
		</div>
		<div class="form-group col-xs-6">
			<label>Razon Social</label>
			<s:textfield data-toggle="tooltip" cssClass="form-control "
				id="razon" title="Máximo 125 Caracteres" name="usuario.razonPrueba"
				maxlength="150"
				value="EMP. TRANS. EXPR. INTERNACIONAL PALOMINO S.A.C"></s:textfield>
		</div>
	</div>
	<div class="row">
		<div class="form-group col-xs-6">
			<label>Telefono</label>
			<s:textfield data-toggle="tooltip" cssClass="form-control"
				title="Solo 9 Caracteres" name="usuario.telefono" maxlength="9"></s:textfield>
		</div>
		<div class="form-group col-xs-6">
			<label>Telefono 2</label>
			<s:textfield data-toggle="tooltip" cssClass="form-control"
				title="Solo 9 Caracteres" name="usuario.telefono2" maxlength="9"></s:textfield>
		</div>
		<div class="form-group col-xs-6">
			<label>Correo</label>
			<s:textfield data-toggle="tooltip" type="email"
				cssClass="form-control" title="Máximo 100 caracteres"
				name="usuario.correo" maxlength="100"></s:textfield>
		</div>
		<sec:authorize access="isAuthenticated()">
			<sec:authorize access="hasAnyRole('1')">
				<div class="form-group col-xs-6">
					<label>Representante</label>
					<s:textfield cssClass="form-control" name="usuario.representante"
						maxlength="100" value="WILMAN"></s:textfield>
				</div>
			</sec:authorize>
		</sec:authorize>
	</div>
	<div class="row">
		<div class="form-group col-xs-6">
			<label>Nivel</label> <select class="form-control"
				name="usuario.nivel">
				<sec:authorize access="isAuthenticated()">
					<sec:authorize access="hasAnyRole('1')">
						<option value="3">USUARIO</option>
						<option value="1">ADMINISTRADOR</option>
						<option value="2">ADMINISTRADOR AGENTE</option>
						<option value="V">ADMINISTRADOR VENTATELEFONICA</option>
						<option value="B">BITACORA FALLAS</option>
						<option value="K">BITACORA Y REGISTRO RECLAMOS</option>
						<option value="H">BITACORA Y ATENCION RECLAMOS</option>
						<option value="C">SERVICIO COMIDA</option>
						<option value="E">ATENCION RECLAMOS</option>
						<option value="R">ATENCION RECLAMOS ADMIN</option>
						<option value="F">FACTURACION (ENVIO Y CONSULTA)</option>
						<option value="D">FACTURACION (CONSULTA)</option>
					</sec:authorize>
				</sec:authorize>
				<option value="4">CLIENTE TRANSPORTE DE PERSONAL</option>
				<option value="T">ADMINISTRADOR TRANSPORTE DE PERSONAL</option>
			</select>
		</div>
		<div class="form-group col-xs-6">
			<label>Estado</label> <select class="form-control"
				name="usuario.estado">
				<option value="Y">ACTIVO</option>
				<option value="N">INACTIVO</option>
			</select>
		</div>
	</div>
	<div class="row">
		<sec:authorize access="isAuthenticated()">
			<sec:authorize access="hasAnyRole('1')">
				<div class="form-group col-xs-6">
					<label>Limite Credito</label>
					<s:textfield cssClass="form-control credito"
						name="usuario.limiteCredito" maxlength="7" autocomplete="off"
						onblur="javascript:CerosDecimales(this);" value="1.0"></s:textfield>
				</div>
			</sec:authorize>
		</sec:authorize>
		<div class="form-group col-xs-6">
			<label>Direccion</label>
			<s:textfield data-toggle="tooltip" cssClass="form-control direccion"
				title="Solo 200 Caracteres" name="usuario.direccion" maxlength="200"></s:textfield>
		</div>
		<div class="form-group col-xs-6">
			<label>Contraseña</label>
			<s:textfield data-toggle="tooltip" type="password"
				cssClass="form-control" title="Solo 10 Caracteres"
				name="usuario.Password" maxlength="10"></s:textfield>
		</div>
		<sec:authorize access="isAuthenticated()">
			<sec:authorize access="hasAnyRole('1')">
				<div class="form-group col-xs-6">
					<label>Localidad</label>
					<s:textfield cssClass="form-control" name="usuario.localidad"
						maxlength="100"></s:textfield>
				</div>
				<div class="form-group col-xs-6">
					<label>Centro de Costo</label>
					<s:textfield cssClass="form-control" name="usuario.centroCosto"
						maxlength="200"></s:textfield>
				</div>


				<div class="form-group col-xs-6">
					<label>Medio Pago (valor por defecto 0 cuando es solo
						Agente Autorizado)</label>
					<s:textfield cssClass="form-control" name="usuario.medio_Pago"
						maxlength="10" value="0"></s:textfield>
				</div>
				<div class="form-group col-xs-6">
					<label>Comision %</label>
					<s:textfield cssClass="form-control"
						name="usuario.porcentajeComision" maxlength="10"></s:textfield>
				</div>
				<div class="form-group col-xs-6">
					<label>Ciudad</label> <select class="form-control"
						name="usuario.ciudad">
						<s:iterator value="ciudades">
							<option value="<s:property value="%{codigo+'-'+detalle}"/>"><s:property
									value="detalle" /></option>
						</s:iterator>
					</select>
				</div>
				<div class="form-group col-xs-6">
					<label>Latitud</label>
					<s:textfield cssClass="form-control" name="usuario.latitud"></s:textfield>
				</div>
				<div class="form-group col-xs-6">
					<label>Longitud</label>
					<s:textfield cssClass="form-control" name="usuario.longitud"></s:textfield>
				</div>
				<div class="form-group col-xs-6">
					<label>Agencias</label> <select class="form-control"
						name="usuario.agencia">
						<option value="-1">-- SELECCIONE --</option>
						<s:iterator value="agencias">
							<option value="<s:property value="%{codigo+','+detalle}"/>"><s:property
									value="detalle" /></option>
						</s:iterator>
					</select>
				</div>

			</sec:authorize>
		</sec:authorize>
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