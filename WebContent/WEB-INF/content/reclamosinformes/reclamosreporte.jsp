<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<style>
.loader {
	border: 8px solid #f3f3f3;
	border-radius: 50%;
	border-top: 8px solid #5cb85c;
	width: 40px;
	height: 40px;
	-webkit-animation: spin 2s linear infinite; /* Safari */
	animation: spin 2s linear infinite;
}

/* Safari */
@
-webkit-keyframes spin { 0% {
	-webkit-transform: rotate(0deg);
}

100%
{
-webkit-transform
:
 
rotate
(360deg);
 
}
}
@
keyframes spin { 0% {
	transform: rotate(0deg);
}
100%
{
transform
:
 
rotate
(360deg);
 
}
}
</style>
<div class="x_panel">
	<div class="row">
		<div class="clearfix" style="text-align: center;">
			<h4>REPORTE DE ATENCION RECLAMOS</h4>
		</div>
	</div>
	<div class="row">
		<table id="tblReclamosReporte" data-toggle="table" data-locale="es-ES"
			data-url="listareclamosreporte" data-side-pagination="server"
			data-pagination="true" data-response-handler="responseHandler"
			data-unique-id="id" data-click-to-select="true"
			class="table table-fixed">
			<thead>
				<tr>
					<th data-field="nro">Nro</th>
					<th data-field="empresa">Empresa</th>
					<th data-field="fechaReclamo">Fecha Reclamo</th>
					<th data-field="reclamante">Reclamante</th>
					<th data-field="fechaIncidente">Fecha Incidente</th>
					<th data-field="servicio">Servicio</th>
					<th data-field="motivoReclamo">Motivo</th>
					<th data-field="tipoReclamo">Tipo</th>
					<th data-field="documento">Documento</th>
					<th data-field="atendido" data-formatter="FormatterReporte">Atendido</th>
					<th data-field="fechaAtencion">Fecha de Atención</th>
					<th data-field="detalleAtencion">Detalle</th>
				</tr>
			</thead>
		</table>
	</div>
	<div class="row">
		<div class="form-group col-xs-1">
			<form action="imprimiratencionreclamosreporte"
				id="formAtencionReclamos" method="post" autocomplete="off"
				accept-charset="UTF-8">
				<input type="hidden" id="hidTiporeclamo" name="tiporeclamo">
				<input type="hidden" id="hidTiporeclamoTexto"
					name="tiporeclamoTexto">
				<sj:submit id="btnAtencionReclamos" formIds="formAtencionReclamos"
					value="IMPRIMIR REPORTE" cssClass="btn btn-success btn-sm"></sj:submit>
			</form>
		</div>
		<div class="form-group col-xs-2">
			<select class="form-control" id="lstReclamo">
				<option value="1">TODOS</option>
				<option value="P">PENDIENTES</option>
				<option value="S">ATENDIDOS</option>
				<option value="N">NO ATENDIDOS ( > 72 Hrs.)</option>
			</select>
		</div>

		<div class="form-group col-xs-9">
			<a href="javascript:void(0);" id="crearexcel" class="btn btn-warning">CREAR
				ESTRUCTURA DE EXCEL</a> <a id="descargarexcel" href="descargarreclamos"
				target="_blank" class="btn btn-success ">DESCARGAR EN EXCEL</a>
			<div id="loader" class="loader"></div>
			<small id="responseexcel"></small>
		</div>

	</div>
</div>
<br>
<script
	src="${pageContext.request.contextPath}/_lib/atencionreclamos/atencionreclamosreporte.js"
	charset="UTF-8"></script>
