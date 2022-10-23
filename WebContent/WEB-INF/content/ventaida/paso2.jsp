<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div class="row">
	<div class="col-md-12 col-xs-12">
		<br />
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12" style="text-align: right;">
				<img alt=""
					src="${pageContext.request.contextPath}/_lib/img/timer.png"
					height="35px" width="35px"> Tiempo restante:
			</div>
			<div class="col-md-12 col-sm-12 col-xs-12">
				<label>Programaciones de Salidas</label>
				<jsp:include page="../template/timer.jsp"></jsp:include>
			</div>
		</div>
		<div class="row" style="text-align: center;">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<s:label value="%{#session.paso1Form.fechaIda}"
					cssStyle="color: #009045; font-size:20px;"></s:label>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<s:label id="lblInfo"
					value="%{#session.paso1Form.origendescripcion} - "
					cssStyle="color: #009045; font-size:20px;"></s:label>
				<s:label id="lblInfo1"
					value="%{#session.paso1Form.destinodescripcion}"
					cssStyle="color: #009045; font-size:20px;"></s:label>
			</div>
		</div>
		<table id="tblProgramacionSalidaIda" data-toggle="table"
			data-locale="es-ES" data-unique-id="id" data-click-to-select="true"
			data-single-select="true" class="table table-fixed">
			<thead>
				<tr>
					<th data-field="state" data-checkbox="true" data-width="1%"></th>
					<th data-field="nro" data-visible="false">Nro</th>
					<th data-field="destino" data-visible="false">Destino</th>
					<th data-field="servicio" data-visible="false">Servicio</th>
					<th data-field="servicioD">Servicio</th>
					<th data-field="precio1" data-formatter="FormatterDescuento1">Precio
						1er Piso</th>
					<th data-field="precio2" data-formatter="FormatterDescuento2">Precio
						2do Piso</th>
					<th data-field="agencia1D">1ra Agencia</th>
					<th data-field="hora1">Hora</th>
					<th data-field="agencia2D">2da Agencia</th>
					<th data-field="hora2">Hora</th>
					<th data-field="agencia3D">3ra Agencia</th>
					<th data-field="hora3">Hora</th>
					<th data-field="agencia4D">4ta Agencia</th>
					<th data-field="hora4">Hora</th>
					<th data-field="agencia5D">5ta Agencia</th>
					<th data-field="hora5">Hora</th>
					<th data-field="bus">Bus</th>
				</tr>
			</thead>
		</table>
		<br />
		<div id="infoinkaida" style="display: none">
			<table>
				<tr>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/icono4_filas_50.png" /></td>
					<td style="padding: 5px">Filas de asientos: 4 filas de
						asientos</td>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/icono_aire_50.png" /></td>
					<td style="padding: 5px">Aire acondicionado: Sistema de aire
						acondicionado y calefacción</td>
				<tr>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/icono_alimentacion_50.png" /></td>
					<td style="padding: 5px">Alimentacion: Servicio de Refrigerio
						a bordo,que puede consistir en desayuno,almuerzo o cena según
						horario</td>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/icono_tripulante_50.png" "/></td>
					<td style="padding: 5px">Atención: Atención a bordo a cargo de
						tripulantes capacitados que le brindaran una atención
						personalizada</td>
				<tr>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/icono_gps_50.png" /></td>
					<td style="padding: 5px">GPS: Monitoreo para control de
						seguridad y ubicación real del bus por GPS</td>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/manta_50.png" /></td>
					<td style="padding: 5px">Kit Viajero: Mantas y almohadas</td>
				<tr>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/icon_sshh_50.png" /></td>
					<td style="padding: 5px">SSHH: Servicios higienicos con
						tratamiento químico</td>
			</table>
		</div>
		<div id="infochasquiida" style="display: none">

			<table>
				<tr>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/icono4_filas_50.png" /></td>
					<td style="padding: 5px">Filas de asientos: 4 filas de
						asientos</td>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/icono_aire_50.png" /></td>
					<td style="padding: 5px">Aire acondicionado: Sistema de aire
						acondicionado y calefacción</td>
				<tr>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/icono_gps_50.png" /></td>
					<td style="padding: 5px">GPS: Monitoreo para control de
						seguridad y ubicación real del bus por GPS</td>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/manta_50.png" /></td>
					<td style="padding: 5px">SSHH: Servicios higienicos con
						tratamiento químico</td>
			</table>
		</div>
		<div id="infoplusida" style="display: none">
			<table>
				<tr>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/icono4_filas_50.png" /></td>
					<td style="padding: 5px">Filas de asientos: 4 filas de
						asientos</td>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/icono_aire_50.png" /></td>
					<td style="padding: 5px">Aire acondicionado: Sistema de aire
						acondicionado y calefacción</td>
				<tr>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/icono_alimentacion_50.png" /></td>
					<td style="padding: 5px">Alimentacion: Servicio de Refrigerio
						a bordo,que puede consistir en desayuno,almuerzo o cena según
						horario</td>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/icono_tripulante_50.png" "/></td>
					<td style="padding: 5px">Atención: Atención a bordo a cargo de
						tripulantes capacitados que le brindaran una atención
						personalizada</td>
				<tr>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/icono_gps_50.png" /></td>
					<td style="padding: 5px">GPS: Monitoreo para control de
						seguridad y ubicación real del bus por GPS</td>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/manta_50.png" /></td>
					<td style="padding: 5px">Kit Viajero: Mantas y almohadas</td>
				<tr>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/icon_wifi_50.png" /></td>
					<td style="padding: 5px">Internet: Servicio de Internet WiFi
						durante el viaje.</td>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/icon_sshh_50.png" /></td>
					<td style="padding: 5px">SSHH: Servicios higienicos con
						tratamiento químico</td>
			</table>
		</div>
		</br>
	</div>
	<s:if test="%{#session.paso1Form.idaVuelta}">
		<div class="col-md-12 col-xs-12">
			<br />
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<label>Programaciones de Salidas de Vuelta</label>
				</div>
			</div>
			<div class="row" style="text-align: center;">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<s:label value="%{#session.paso1Form.fechaVuelta}"
						cssStyle="color: #009045; font-size:20px;"></s:label>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<s:label id="lblInfo"
						value="%{#session.paso1Form.destinodescripcion} - "
						cssStyle="color: #009045; font-size:20px;"></s:label>
					<s:label id="lblInfo1"
						value="%{#session.paso1Form.origendescripcion}"
						cssStyle="color: #009045; font-size:20px;"></s:label>
				</div>
			</div>

			<table id="tblProgramacionSalidaIdaVuelta" data-toggle="table"
				data-locale="es-ES" data-unique-id="id" data-click-to-select="true"
				data-single-select="true" class="table table-fixed">
				<thead>
					<tr>
						<th data-field="state" data-checkbox="true" data-width="1%"></th>
						<th data-field="nro" data-visible="false">Nro</th>
						<th data-field="destino" data-visible="false">Destino</th>
						<th data-field="servicio" data-visible="false">Servicio</th>
						<th data-field="servicioD">Servicio</th>
						<th data-field="precio1" data-formatter="FormatterDescuento1">Precio
							1er Piso</th>
						<th data-field="precio2" data-formatter="FormatterDescuento2">Precio
							2do Piso</th>
						<th data-field="agencia1D">1ra Agencia</th>
						<th data-field="hora1">Hora</th>
						<th data-field="agencia2D">2da Agencia</th>
						<th data-field="hora2">Hora</th>
						<th data-field="agencia3D">3ra Agencia</th>
						<th data-field="hora3">Hora</th>
						<th data-field="agencia4D">4ta Agencia</th>
						<th data-field="hora4">Hora</th>
						<th data-field="agencia5D">5ta Agencia</th>
						<th data-field="hora5">Hora</th>
						<th data-field="bus">Bus</th>
					</tr>
				</thead>
			</table>
			<br />
		</div>
		<br />
		<div id="infoinkavuelta" style="display: none">
			<table>
				<tr>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/icono4_filas_50.png" /></td>
					<td style="padding: 5px">Filas de asientos: 4 filas de
						asientos</td>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/icono_aire_50.png" /></td>
					<td style="padding: 5px">Aire acondicionado: Sistema de aire
						acondicionado y calefacción</td>
				<tr>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/icono_alimentacion_50.png" /></td>
					<td style="padding: 5px">Alimentacion: Servicio de Refrigerio
						a bordo,que puede consistir en desayuno,almuerzo o cena según
						horario</td>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/icono_tripulante_50.png" "/></td>
					<td style="padding: 5px">Atención: Atención a bordo a cargo de
						tripulantes capacitados que le brindaran una atención
						personalizada</td>
				<tr>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/icono_gps_50.png" /></td>
					<td style="padding: 5px">GPS: Monitoreo para control de
						seguridad y ubicación real del bus por GPS</td>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/manta_50.png" /></td>
					<td style="padding: 5px">Kit Viajero: Mantas y almohadas</td>
				<tr>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/icon_sshh_50.png" /></td>
					<td style="padding: 5px">SSHH: Servicios higienicos con
						tratamiento químico</td>
			</table>
		</div>
		<div id="infochasquivuelta" style="display: none">

			<table>
				<tr>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/icono4_filas_50.png" /></td>
					<td style="padding: 5px">Filas de asientos: 4 filas de
						asientos</td>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/icono_aire_50.png" /></td>
					<td style="padding: 5px">Aire acondicionado: Sistema de aire
						acondicionado y calefacción</td>
				<tr>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/icono_gps_50.png" /></td>
					<td style="padding: 5px">GPS: Monitoreo para control de
						seguridad y ubicación real del bus por GPS</td>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/manta_50.png" /></td>
					<td style="padding: 5px">SSHH: Servicios higienicos con
						tratamiento químico</td>
			</table>
		</div>
		<div id="infoplusvuelta" style="display: none">

			<table>
				<tr>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/icono4_filas_50.png" /></td>
					<td style="padding: 5px">Filas de asientos: 4 filas de
						asientos</td>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/icono_aire_50.png" /></td>
					<td style="padding: 5px">Aire acondicionado: Sistema de aire
						acondicionado y calefacción</td>
				<tr>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/icono_alimentacion_50.png" /></td>
					<td style="padding: 5px">Alimentacion: Servicio de Refrigerio
						a bordo,que puede consistir en desayuno,almuerzo o cena según
						horario</td>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/icono_tripulante_50.png" "/></td>
					<td style="padding: 5px">Atención: Atención a bordo a cargo de
						tripulantes capacitados que le brindaran una atención
						personalizada</td>
				<tr>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/icono_gps_50.png" /></td>
					<td style="padding: 5px">GPS: Monitoreo para control de
						seguridad y ubicación real del bus por GPS</td>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/manta_50.png" /></td>
					<td style="padding: 5px">Kit Viajero: Mantas y almohadas</td>
				<tr>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/icon_wifi_50.png" /></td>
					<td style="padding: 5px">Internet: Servicio de Internet WiFi
						durante el viaje.</td>
					<td style="padding: 5px"><img
						src="http://www.grupopalomino.com.pe/icon/icon_sshh_50.png" /></td>
					<td style="padding: 5px">SSHH: Servicios higienicos con
						tratamiento químico</td>
			</table>

		</div>
</div>

</s:if>
<div class="pull-left">
	<form id="frmPaso1" action="ventapaso1" method="post"></form>
	<sj:submit formIds="frmPaso1" cssClass="btn btn-success"
		value="REGRESAR"></sj:submit>
</div>
<form action="paso3" method="post">
	<input id="hidnroProgramacionIda" type="hidden"
		name="paso2FormIDA.nroProgramacion" />
	<s:hidden id="hidnroDestinoIda" name="paso2FormIDA.nroDestino"></s:hidden>
	<input id="hidnroBusIda" type="hidden" name="paso2FormIDA.nroBus" /> <input
		id="hidnroServicioIda" type="hidden" name="paso2FormIDA.nroServicio" />
	<input type="hidden" id="hidDescServicioIda"
		name="paso2FormIDA.descripcionServicioIda"> <input
		type="hidden" id="hidHora1Ida" name="paso2FormIDA.hora1Ida">

	<s:if test="%{#session.paso1Form.idaVuelta}">
		<input id="hidnroProgramacionIdaVuelta" type="hidden"
			name="paso2FormVUELTA.nroProgramacion" />
		<s:hidden id="hidnroDestinoIdaVuelta"
			name="paso2FormVUELTA.nroDestino"></s:hidden>
		<input id="hidnroBusIdaVuelta" type="hidden"
			name="paso2FormVUELTA.nroBus" />
		<input id="hidnroServicioIdaVuelta" type="hidden"
			name="paso2FormVUELTA.nroServicio" />
		<input type="hidden" id="hidDescServicioVuelta"
			name="paso2FormVUELTA.descripcionServicioVuelta">
		<input type="hidden" id="hidHora1Vuelta"
			name="paso2FormVUELTA.hora1Vuelta">
	</s:if>
	<div class="pull-right">
		<input type="submit" value="CONTINUAR" class="btn btn-success" />
	</div>

</form>
</div>
<div class="row">
	<div class="modal fade" id="modalImagenes" tabindex="-1" role="dialog"
		aria-labelledby="modalLabelPaso2">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="modalLabelPaso2">Detalle de Bus</h4>
					<s:label id="title-bus" cssStyle="color: #009045; font-size:20px;"></s:label>
				</div>
				<div class="modal-body"
					style="width: 800px; background: #eeeeee; margin: 50px auto; border-radius: 20px;">
					<div id="divCuerpoFotos" class="row" style="height: 100px;">

					</div>
				</div>
				<div class="modal-footer">
					<div class="row">
						<div class="col-xs-12">
							<div>
								<img alt="" style="padding-right: 10px;" width="100px"
									align="left"
									src="${pageContext.request.contextPath}/_lib/img/VerifiedVisa_Blanco.jpg">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="${pageContext.request.contextPath}/_lib/venta/paso2.js"
	charset="UTF-8"></script>
<script
	src="${pageContext.request.contextPath}/_lib/oneBook-3D/js/jquery.onebook3d.min.js"
	charset="UTF-8"></script>
<script
	src="${pageContext.request.contextPath}/_lib/oneBook-3D/js/jquery.mousewheel.min.js"
	charset="UTF-8"></script>
