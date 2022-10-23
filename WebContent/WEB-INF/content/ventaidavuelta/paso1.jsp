<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="calendarIda"></div>

<div class="modal fade" id="modalPaso1" tabindex="-1" role="dialog"
	aria-labelledby="modalLabelPaso1">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="modalLabelPaso1">Seleccione el
					origen y el destino del viaje</h4>
			</div>
			<div class="modal-body">
				<form id="frmIda1" action="test3" method="post">
					<div class="row">
						<div class="col-xs-6">
							<select class="form-control" name="ventaPaso1Form.nroDestino">
								<option>[Seleccione]</option>
								<option value="1">Arequipa</option>
							</select>
						</div>
						<div class="col-xs-6">
							<select class="form-control" name="ventaPaso1Form.nroOrigen">
								<option>[Seleccione]</option>
								<option value="2">Arequipa</option>
							</select>
						</div>
					</div>
					<input type="hidden" name="ventaPaso1Form.fecha">
				</form>
				<br />
				<div class="row">
					<div class="col-xs-12">
						<table id="tblSalidas" data-toggle="table" data-locale="es-ES">
							<thead>
								<tr>
									<th>Servicio</th>
									<th>Precio 1er piso</th>
									<th>Precio 2do piso</th>
									<th>1ra Agencia</th>
									<th>Hora</th>
									<th>2da Agencia</th>
									<th>Hora</th>
									<th>3ra Agencia</th>
									<th>Hora</th>
									<th>4ta Agencia</th>
									<th>Hora</th>
									<th>Bus</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
				<button id="btnAPaso2" type="button" class="btn btn-primary">Siguiente
					paso</button>
			</div>
		</div>
	</div>
</div>