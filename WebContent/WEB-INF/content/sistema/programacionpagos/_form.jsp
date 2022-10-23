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
		<div class="col-md-3 col-sm-6 col-xs-12">
			<label>Cliente</label>
			<s:select id="Usuario" list="listaUsuarios" cssClass="form-control"
				listKey="ruc" headerKey="-1" listValue="razonSocial"
				name="programacionpagos.Usuario" />
		</div>
	</div>
	<div class="row">
		<div class="form-group col-xs-12">
			<div class="col-xs-2">
				<label>Periodo</label>
				<s:textfield id="periodo" type="text" class="form-control"
					name="programacionpagos.Periodo" />
			</div>
		</div>
	</div>
	<br>
	<div class="row">
		<div class="form-group col-xs-12">
			<div class="col-md-5">
				<label>Enero</label>
				<div class="input-group input-append date" id="datePickerEnero">
					<s:textfield id="enero" type="text" class="form-control"
						name="programacionpagos.Enero" />
					<span class="input-group-addon add-on"><span
						class="glyphicon glyphicon-calendar"></span></span>
				</div>
			</div>
			<div class='col-md-5'>
				<label>Febrero</label>
				<div class="input-group input-append date" id="datePickerFebrero">
					<s:textfield id="febrero" type="text" class="form-control"
						name="programacionpagos.Febrero" />
					<span class="input-group-addon add-on"><span
						class="glyphicon glyphicon-calendar"></span></span>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group col-xs-12">
			<div class="col-md-5">
				<label>Marzo</label>
				<div class="input-group input-append date" id="datePickerMarzo">
					<s:textfield id="marzo" type="text" class="form-control"
						name="programacionpagos.Marzo" />
					<span class="input-group-addon add-on"><span
						class="glyphicon glyphicon-calendar"></span></span>
				</div>
			</div>
			<div class='col-md-5'>
				<label>Abril</label>
				<div class="input-group input-append date" id="datePickerAbril">
					<s:textfield id="abril" type="text" class="form-control"
						name="programacionpagos.Abril" />
					<span class="input-group-addon add-on"><span
						class="glyphicon glyphicon-calendar"></span></span>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group col-xs-12">
			<div class='col-md-5'>
				<label>Mayo</label>
				<div class="input-group input-append date" id="datePickerMayo">
					<s:textfield id="mayo" type="text" class="form-control"
						name="programacionpagos.Mayo" />
					<span class="input-group-addon add-on"><span
						class="glyphicon glyphicon-calendar"></span></span>
				</div>
			</div>
			<div class='col-md-5'>
				<label>Junio</label>
				<div class="input-group input-append date" id="datePickerJunio">
					<s:textfield id="junio" type="text" class="form-control"
						name="programacionpagos.Junio" />
					<span class="input-group-addon add-on"><span
						class="glyphicon glyphicon-calendar"></span></span>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group col-xs-12">
			<div class='col-md-5'>
				<label>Julio</label>
				<div class="input-group input-append date" id="datePickerJulio">
					<s:textfield id="julio" type="text" class="form-control"
						name="programacionpagos.Julio" />
					<span class="input-group-addon add-on"><span
						class="glyphicon glyphicon-calendar"></span></span>
				</div>
			</div>
			<div class='col-md-5'>
				<label>Agosto</label>
				<div class="input-group input-append date" id="datePickerAgosto">
					<s:textfield id="agosto" type="text" class="form-control"
						name="programacionpagos.Agosto" />
					<span class="input-group-addon add-on"><span
						class="glyphicon glyphicon-calendar"></span></span>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group col-xs-12">
			<div class='col-md-5'>
				<label>Septiembre</label>
				<div class="input-group input-append date" id="datePickerSeptiembre">
					<s:textfield id="septiembre" type="text" class="form-control"
						name="programacionpagos.Septiembre" />
					<span class="input-group-addon add-on"><span
						class="glyphicon glyphicon-calendar"></span></span>
				</div>
			</div>
			<div class='col-md-5'>
				<label>Octubre</label>
				<div class="input-group input-append date" id="datePickerOctubre">
					<s:textfield id="octubre" type="text" class="form-control"
						name="programacionpagos.Octubre" />
					<span class="input-group-addon add-on"><span
						class="glyphicon glyphicon-calendar"></span></span>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group col-xs-12">
			<div class='col-md-5'>
				<label>Noviembre</label>
				<div class="input-group input-append date" id="datePickerNoviembre">
					<s:textfield id="noviembre" type="text" class="form-control"
						name="programacionpagos.Noviembre" />
					<span class="input-group-addon add-on"><span
						class="glyphicon glyphicon-calendar"></span></span>
				</div>
			</div>
			<div class='col-md-5'>
				<label>Diciembre</label>
				<div class="input-group input-append date" id="datePickerDiciembre">
					<s:textfield id="diciembre" type="text" class="form-control"
						name="programacionpagos.Diciembre" />
					<span class="input-group-addon add-on"><span
						class="glyphicon glyphicon-calendar"></span></span>
				</div>
			</div>
		</div>
	</div>
	<s:textfield type="hidden" name="usuariohorarios.Id"></s:textfield>
	<script
		src="${pageContext.request.contextPath}/_lib/sistema/programacionpagos/programacionpagos.js"
		charset="UTF-8"></script>
</body>
</html>