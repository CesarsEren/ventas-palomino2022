<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="row">
	<div class="col-md-8 col-xs-12">
		<br /> <label>LISTA DE PASAJEROS AGREGADOS</label>
		<table id="tblPasajeros" data-toggle="table" data-locale="es-ES"
			data-unique-id="id">
			<thead>
				<tr>
					<th data-field="id" data-visible="false"></th>
					<th data-field="nroDocumento">Nro. Documento</th>
					<th data-field="nombre">Nombre</th>
					<th data-field="nroAsiento">Nro. Asiento</th>
					<th data-field="precio">Precio</th>
					<th data-formatter="acciones">Operaciones</th>
				</tr>
			</thead>
		</table>
		<br />
	</div>
	<div class="col-md-4 col-xs-12">
		<div id="mapaIda"></div>
	</div>
</div>

