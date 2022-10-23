<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<br />
<form action="paso2" method="post" accept-charset="UTF-8">


	<div class="row">
		<div class="col-md-3 col-sm-6 col-xs-12">
			<sec:authorize access="!hasAnyRole('C')">
				<input id="ida" type="radio" name="paso1Form.idaVuelta"
					checked="checked" value="false" />
				<label for="ida">Ida</label>
			</sec:authorize>

			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<sec:authorize access="!hasAnyRole('C')">
				<input id="idavuelta" type="radio" name="paso1Form.idaVuelta"
					value="true" />
				<label for="idavuelta">Ida y vuelta</label>
			</sec:authorize>
		</div>
		<div class="col-md-9 col-sm-6 col-xs-12" style="text-align: right;">
			<img alt=""
				src="${pageContext.request.contextPath}/_lib/img/timer.png"
				height="35px" width="35px"> Tiempo restante:
		</div>

	</div>
	<div class="row">
		<jsp:include page="../template/timer.jsp"></jsp:include>
	</div>
	<hr style="background-color: #5cb85c; color: #5cb85c; height: 1px;">
	<div class="row">
		<div class="col-md-3 col-sm-6 col-xs-12">
			<label>Origen</label> <select id="select2Origen"
				class="js-example-responsive" style="width: 100%"
				name="paso1Form.origenIda">
			</select>
		</div>
		<input id="idorigendescripcion" type="hidden"
			name="paso1Form.origenDescripcion">
		<div class="col-md-3 col-sm-6 col-xs-12">
			<label>Fecha de Ida</label>
			<div class="input-group input-append date" id="dateRangePickerIda">
				<input id="fechaIda" type="text" class="form-control"
					name="paso1Form.fechaIda" /> <span
					class="input-group-addon add-on"><span
					class="glyphicon glyphicon-calendar"></span></span>
			</div>
		</div>
		<div class="col-md-3 col-sm-6 col-xs-12">
			<label>Destino</label> <select id="select2Destino"
				class="js-example-responsive" style="width: 100%"
				name="paso1Form.destinoIda">
			</select>
		</div>
		<input id="iddestinodescripcion" type="hidden"
			name="paso1Form.destinoDescripcion">
		<div class="col-md-3 col-sm-6 col-xs-12 fechaVueltaDiv"
			style="display: none;">
			<label>Fecha de Vuelta</label>
			<div class="input-group input-append date" id="dateRangePickerVuelta">
				<input id="fechaVuelta" type="text" disabled="disabled"
					class="form-control" name="paso1Form.fechaVuelta" /> <span
					class="input-group-addon add-on"><span
					class="glyphicon glyphicon-calendar"></span></span>
			</div>
		</div>
	</div>
	<sec:authorize access="!hasAnyRole('C')">
		<hr style="background-color: #5cb85c; color: #5cb85c; height: 1px;">
		<div class="row">
			<div class="col-md-1 col-sm-6 col-xs-12">
				<label>Cantidad Pasajeros</label>
			</div>
			<div class="col-md-2 col-sm-6 col-xs-12">
				<select class="form-control"
					name="paso1Form.cantidadMaximaPasajeros">
					<s:iterator value="%{#session.listacantidadPasajeros}">
						<option value="<s:property value="cantidadMaximaPasajeros"/>"><s:property
								value="cantidadMaximaPasajeros" /></option>
					</s:iterator>
				</select>
			</div>
		</div>
	</sec:authorize>

	<br />
	<div class="row">
		<div class="pull-right">
			<input type="submit" value="CONTINUAR" class="btn btn-success" />
		</div>
	</div>

</form>
<script src="${pageContext.request.contextPath}/_lib/venta/paso1.js"
	charset="UTF-8"></script>