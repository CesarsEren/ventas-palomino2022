<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="row">
	<div class="row">
		<div class="col-xs-12">
			<table id="tblReimprimirVoucher" data-toggle="table"
				data-locale="es-ES" data-pagination="true"
				data-url="ListaBoletoReservadoGrid" data-height="350"
				data-unique-id="id" data-side-pagination="server"
				data-pagination="true" data-response-handler="responseHandler"
				data-filter-control="true" data-click-to-select="true"
				data-single-select="true">
				<thead>
					<tr>
						<th data-field="state" data-checkbox="true" data-width="1%"></th>
						<th data-field="nro" data-filter-control="input" data-width="1%">Voucher</th>
						<th data-field="salida" data-filter-control="input"
							data-width="1%">Programacion</th>
						<th data-field="destino" data-filter-control="input"
							data-width="3%">Destino de Viaje</th>
						<th data-field="fechaemision" data-filter-control="input"
							data-width="2%">Fecha</th>
						<th data-field="hora" data-filter-control="input"
							data-visible="false">Hora</th>
						<th data-field="nombre" data-filter-control="input"
							data-width="15%">Pasajero</th>
						<th data-field="dni" data-filter-control="input" data-width="2%">Dni</th>
						<th data-field="asiento" data-filter-control="input"
							data-width="1%">Asiento</th>
						<th data-field="fechavoucher" data-filter-control="input"
							data-visible="false">FechaVoucher</th>
						<th data-field="nroembarque" data-filter-control="input"
							data-visible="false">NroEmbarque</th>
						<th data-field="embarque" data-filter-control="input"
							data-visible="false">embarque</th>
						<th data-field="monto" data-filter-control="input"
							data-visible="false">Monto</th>
						<th data-field="Usuario" data-filter-control="input"
							data-visible="false">Usuario</th>
						<th data-field="servicio" data-filter-control="input"
							data-width="5%">Servicio</th>
					</tr>
				</thead>
			</table>
			<input type="submit" value="IMPRIMIR" id="btnVoucher"
				class="btn btn-success btn-sm" onclick="javascript:Imprimir();" />
		</div>
		<form id="formImprimir">
			<div class="col-md-2">
				<s:hidden id="hidenselect" value="-1"></s:hidden>
				<s:textfield id="txtNrovoucher" name="Nrovoucher" type="hidden"></s:textfield>
				<s:textfield id="txtSalida" name="Salida" type="hidden"></s:textfield>
				<s:textfield id="txtUsuario" name="Usuario" type="hidden"></s:textfield>
			</div>
		</form>
	</div>
</div>
<script
	src="${pageContext.request.contextPath}/_lib/reportes/reimprimirvoucher.js"
	charset="UTF-8"></script>
