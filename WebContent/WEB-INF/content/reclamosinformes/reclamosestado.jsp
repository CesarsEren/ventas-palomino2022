<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div class="x_panel">
	<div class="row">
		<div class="clearfix" style="text-align: center;">
			<h4>SEGUIMIENTO DE LOS RECLAMOS</h4>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-3 col-md-3 col-xs-3">
			<label>Tipo de Consulta:</label> <select id="cboTipoConsulta"
				class="form-control input-sm identidad" name="reclamos.Identidad">
				<option value="T">TODOS</option>
				<option value="P">PENDIENTES</option>
				<option value="N">NO ATENDIDOS</option>
			</select>
		</div>
		<div class="col-lg-3 col-md-3 col-xs-3">
			<label>Agencias:</label> <select id="cboAgencia"
				class="form-control input-sm identidad">
				<option value="T">TODOS</option>
				<s:iterator value="listaAgencias">
					<option value="<s:property value="%{Agencia}"/>"><s:property
							value="AgenciaD" /></option>
				</s:iterator>
			</select>
		</div>
		<div class="col-lg-3 col-md-3 col-xs-3">
			<label>Servicio:</label> <select id="cboServicio"
				class="form-control input-sm identidad">
				<option value="T">TODOS</option>
				<option value="B">PASAJES</option>
				<option value="E">ENCOMIENDAS</option>
			</select>
		</div>
		<div class="col-lg-3 col-md-3 col-xs-3">
			<label>Dni:</label> <input type="text" maxlength="8"
				class="form-control input-sm" id="txtdni" />
		</div>
		<div></div>
	</div>
	<div class="row" style="padding-top: 15px;">
		<form id="frmEstadoReclamos" action="cambiaestadoreclamos"
			method="post" accept-charset="UTF-8">
			<table id="tblReclamosEstado" data-toggle="table" data-locale="es-ES"
				data-url="listareclamosinformes?tipo=T&agencia=T&servicio=T"
				data-side-pagination="server" data-pagination="true"
				data-response-handler="responseHandler" data-unique-id="id"
				data-click-to-select="true" class="table table-fixed">
				<thead>
					<tr>
						<th data-field="nro">Nro</th>
						<th data-field="empresaD">Empresa</th>
						<th data-field="fechaReclamo">Fecha Reclamo</th>
						<th data-field="fechaCaducidad">Fecha Caducidad</th>
						<th data-field="tipoReclamo">Tipo Reclamo</th>
						<th data-field="atendido" data-formatter="FormatterEstado">Atendido</th>
						<th data-field="fechaAtencion">Fecha de Atención</th>
						<th data-field="usuario">Usuario</th>
						<th data-field="agenciad">Agencia</th>
						<th data-field="Id" data-formatter="operacionesReclamos">Operaciones</th>
					</tr>
				</thead>
			</table>
			<input type="hidden" value="-1" id="hidId" name="Id" /> <input
				type="hidden" id="hidDetalle" name="detalle" />
		</form>
	</div>

</div>
<br>
<script
	src="${pageContext.request.contextPath}/_lib/atencionreclamos/atencionreclamosestado.js"
	charset="UTF-8"></script>
