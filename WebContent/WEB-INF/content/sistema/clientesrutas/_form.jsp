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
		<div class="form-group col-xs-4">
			<label>Clientes</label>
			<s:select list="listaClientes" cssClass="form-control" listKey="ruc"
				listValue="razonSocial" name="clienteruta.Ruc" />
		</div>
		<div class="form-group col-xs-4">
			<label>Ruta</label> <input type="hidden" id="hidDescripcionRuta"
				name="clienteruta.DescripcionRuta">
			<s:select list="listaRutas" cssClass="form-control" id="idRuta"
				listKey="Nro" listValue="NroDetalle" name="clienteruta.NroRuta" />

		</div>
		<div class="form-group col-xs-4">
			<label>Servicio</label> <input type="hidden"
				id="hidDescripcionServicio" name="clienteruta.DescripcionServicio">
			<s:select list="listaServicio" cssClass="form-control"
				id="idServicio" listKey="Codigo" listValue="Detalle"
				name="clienteruta.NroServicio" />

		</div>
	</div>
	<div class="row">
		<div class="form-group col-xs-4">
			<label>Precio 1er Piso</label>
			<s:textfield cssClass="form-control precio1"
				name="clienteruta.precio1" maxlength="7"
				onblur="javascript:CerosDecimalesRutas(this,1);"></s:textfield>
		</div>
		<div class="form-group col-xs-4">
			<label>Precio 2do Piso</label>
			<s:textfield cssClass="form-control precio2"
				name="clienteruta.precio2" maxlength="7"
				onblur="javascript:CerosDecimalesRutas(this,2);"></s:textfield>
		</div>
	</div>

	<s:textfield type="hidden" name="clienteruta.Id"></s:textfield>
	<script
		src="${pageContext.request.contextPath}/_lib/sistema/clientesrutas/clientesrutas.js"
		charset="UTF-8"></script>
	<script
		src="${pageContext.request.contextPath}/_lib/venta/onkeypress.js"
		charset="UTF-8"></script>
</body>
</html>