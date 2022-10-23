<link
	href="${pageContext.request.contextPath}/_lib/bootstrap/css/bootstrap.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/_lib/font-awesome/font-awesome.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/_lib/gentella/custom.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/_lib/gentella/mapabus.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/_lib/select2/css/select2.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/_lib/select2/css/select2-bootstrap.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/_lib/bootstrap-table/bootstrap-table.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/_lib/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/_lib/bootstrap/css/Stylos.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/_lib/sweetalert2/sweetalert2.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/_lib/bootstrap-datepicker/css/bootstrap-datepicker3.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/_lib/bootstrap-dialog/css/bootstrap-dialog.css"
	rel="stylesheet">

<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="panel panel-danger">
	<div class="panel-heading">
		<div class="row">
			<h2>Ocurrio un Problema en el Proceso de Pago</h2>
		</div>
	</div>
	<div class="panel-body" style="width: 100%; height: 95%">
		<div class="row">
			<label>a continuación se especifican los detalles:</label>
		</div>
		<br>
		<br>
		<div class="row">
			<div class="col-lg-6 col-sm-6 col-xs-6">
				<ul class="list-group">
					<li class="list-group-item"><label>N° Ticket :</label> <s:label
							name="ETICKET" style="color:#009045;"></s:label></li>
					<li class="list-group-item"><label> Estado :</label>
					<s:label id="descripcionError" name="ESTADO" style="color:#009045;"></s:label>
					</li>
				</ul>
			</div>
			<div class="col-lg-6 col-sm-6 col-xs-6">
				<ul class="list-group">
					<li class="list-group-item"><label> Medio Pago : </label> <label
						style="color: #009045;">Visa</label></li>
					<li class="list-group-item"><label> Descripción Error:</label>
						<s:label id="descripcionError" style="color:#E30613;"
							name="mensajeServer"></s:label></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="panel-footer">
		<div class="row">
			<a style="color: black;" href="http://www.grupopalomino.com.pe"
				target="_blank">www.grupopalomino.com.pe</a>
		</div>
	</div>
</div>
<script
	src="${pageContext.request.contextPath}/_lib/jquery/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/_lib/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/_lib/gentella/custom.js"></script>
<script
	src="${pageContext.request.contextPath}/_lib/select2/js/select2.full.min.js"></script>
<script
	src="${pageContext.request.contextPath}/_lib/select2/js/i18n/es.js"></script>
<script
	src="${pageContext.request.contextPath}/_lib/bootstrap-dialog/js/bootstrap-dialog.js"></script>
<script
	src="${pageContext.request.contextPath}/_lib/bootstrap-table/bootstrap-table.min.js"></script>
<script
	src="${pageContext.request.contextPath}/_lib/bootstrap-table/bootstrap-table-locale-all.min.js"
	charset="UTF-8"></script>
<script
	src="${pageContext.request.contextPath}/_lib/bootstrap-table/bootstrap-table-filter.js"
	charset="UTF-8"></script>
<script
	src="${pageContext.request.contextPath}/_lib/bootstrap-table/bootstrap-table-filter-control.js"
	charset="UTF-8"></script>
<script
	src="${pageContext.request.contextPath}/_lib/usuarios/usuarios.js"
	charset="UTF-8"></script>
<script
	src="${pageContext.request.contextPath}/_lib/clientes/clientes.js"
	charset="UTF-8"></script>