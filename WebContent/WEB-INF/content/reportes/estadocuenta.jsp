<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="row">
	<form id="formImprimirEstadoCuenta" action="imprimirestadocuenta">
		<div class="row">
			<div class="col-md-3">
				<input type="hidden" id="rbtConsulta" value="H" name="Tipo">
				<input id="rbpendiente" type="radio" name="estadocuenta"
					value="false" /> <label for="ida">Pendientes</label> <br /> <input
					id="rbhistorico" type="radio" name="estadocuenta" checked="checked"
					value="true" /> <label for="idavuelta">Historico</label>
			</div>
			<div id="DivfechaEstadoCuenta" style="display: block;">
				<div class="col-md-2 col-sm-6 col-xs-12">
					<label>Fecha inicial</label>
					<div class="input-group input-append date"
						id="dateRangePickerEstadoIni">
						<input id="fechaInicial" type="text" class="form-control"
							name="FechaInicial" /> <span class="input-group-addon add-on"><span
							class="glyphicon glyphicon-calendar"></span></span>
					</div>
				</div>
				<div class="col-md-2 col-sm-6 col-xs-12">
					<label>Fecha Final</label>
					<div class="input-group input-append date"
						id="dateRangePickerEstadoFin">
						<input id="fechaFinal" type="text" class="form-control"
							name="FechaFinal" /> <span class="input-group-addon add-on"><span
							class="glyphicon glyphicon-calendar"></span></span>
					</div>
				</div>
			</div>
			<sec:authorize access="hasAnyRole('1','2')">

				<div class="col-md-3 col-sm-6 col-xs-12">
					<label>Usuarios</label>
					<s:select id="Usuario" list="listaUsuarios" cssClass="form-control"
						listKey="Id" headerKey="-1" headerValue=" TODOS "
						listValue="nombreCompleto" name="Usuario" />
				</div>
			</sec:authorize>

			<div class="col-xs-3" style="padding-top: 20px;">
				<input id="btnBuscarEstadoCuenta" type="button" value="BUSCAR"
					class="btn btn-success" />
			</div>

		</div>
	</form>
	<div class="row">
		<div class="col-xs-12">
			<table id="tblEstadoCuenta" data-toggle="table" data-locale="es-ES"
				data-pagination="true" data-height="350" data-unique-id="id"
				data-side-pagination="server" data-pagination="true"
				data-response-handler="responseHandler"
				data-query-params="queryParams">
				<thead>
					<tr class="success">
						<th data-field="voucher">Voucher</th>
						<th data-field="fechaemision">Fecha Emision</th>
						<th data-field="destino">Destino</th>
						<th data-field="total">Total</th>
						<th data-field="pago">Pagos</th>
						<th data-field="saldo">Saldo</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<br>
	<div class="row">
		<div class="col-md-2">
			<sj:submit id="btnEstadoCuenta" formIds="formImprimirEstadoCuenta"
				disabled="true" value="Imprimir Estado de Cuenta"
				cssClass="btn btn-success btn-sm"></sj:submit>
		</div>

	</div>
</div>
<script
	src="${pageContext.request.contextPath}/_lib/reportes/estadocuenta.js"
	charset="UTF-8"></script>