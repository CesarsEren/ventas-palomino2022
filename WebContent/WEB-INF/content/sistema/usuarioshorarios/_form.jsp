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
		<div class="col-md-3 col-sm-6 col-xs-12">
			<label>Usuarios</label>
			<s:select id="Usuario" list="listaUsuarios" cssClass="form-control"
				listKey="username" headerKey="-1" listValue="nombreCompleto"
				name="usuariohorarios.Usuario" />
		</div>
	</div>
	<br>
	<div class="row">
		<div class="form-group col-xs-1">
			<label>Lunes</label>
		</div>
		<div class="form-group col-xs-6">
			<div class='col-sm-3'>
				<div class="form-group">
					<div class='input-group date' id='datelunesdesde'>
						<s:textfield cssClass="form-control"
							name="usuariohorarios.LunesDesde" />
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</div>
			</div>
			<div class="form-group col-xs-1">Hasta</div>
			<div class='col-sm-3'>
				<div class="form-group">
					<div class='input-group date' id='dateluneshasta'>
						<s:textfield cssClass="form-control"
							name="usuariohorarios.LunesHasta" />
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group col-xs-1">
			<label>Martes</label>
		</div>
		<div class="form-group col-xs-6">
			<div class='col-sm-3'>
				<div class="form-group">
					<div class='input-group date' id='datemartesdesde'>
						<s:textfield cssClass="form-control"
							name="usuariohorarios.MartesDesde" />
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</div>
			</div>
			<div class="form-group col-xs-1">Hasta</div>
			<div class='col-sm-3'>
				<div class="form-group">
					<div class='input-group date' id='datemarteshasta'>
						<s:textfield cssClass="form-control"
							name="usuariohorarios.MartesHasta" />
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group col-xs-1">
			<label>Miercoles</label>
		</div>
		<div class="form-group col-xs-6">
			<div class='col-sm-3'>
				<div class="form-group">
					<div class='input-group date' id='datemiercolesdesde'>
						<s:textfield cssClass="form-control"
							name="usuariohorarios.MiercolesDesde" />
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</div>
			</div>
			<div class="form-group col-xs-1">Hasta</div>
			<div class='col-sm-3'>
				<div class="form-group">
					<div class='input-group date' id='datemiercoleshasta'>
						<s:textfield cssClass="form-control"
							name="usuariohorarios.MiercolesHasta" />
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group col-xs-1">
			<label>Jueves</label>
		</div>
		<div class="form-group col-xs-6">
			<div class='col-sm-3'>
				<div class="form-group">
					<div class='input-group date' id='datejuevesdesde'>
						<s:textfield cssClass="form-control"
							name="usuariohorarios.JuevesDesde" />
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</div>
			</div>
			<div class="form-group col-xs-1">Hasta</div>
			<div class='col-sm-3'>
				<div class="form-group">
					<div class='input-group date' id='datejueveshasta'>
						<s:textfield cssClass="form-control"
							name="usuariohorarios.JuevesHasta" />
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group col-xs-1">
			<label>Viernes</label>
		</div>
		<div class="form-group col-xs-6">
			<div class='col-sm-3'>
				<div class="form-group">
					<div class='input-group date' id='dateviernesdesde'>
						<s:textfield cssClass="form-control"
							name="usuariohorarios.ViernesDesde" />
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</div>
			</div>
			<div class="form-group col-xs-1">Hasta</div>
			<div class='col-sm-3'>
				<div class="form-group">
					<div class='input-group date' id='datevierneshasta'>
						<s:textfield cssClass="form-control"
							name="usuariohorarios.ViernesHasta" />
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group col-xs-1">
			<label>Sabado</label>
		</div>
		<div class="form-group col-xs-6">
			<div class='col-sm-3'>
				<div class="form-group">
					<div class='input-group date' id='datesabadodesde'>
						<s:textfield cssClass="form-control"
							name="usuariohorarios.SabadoDesde" />
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</div>
			</div>
			<div class="form-group col-xs-1">Hasta</div>
			<div class='col-sm-3'>
				<div class="form-group">
					<div class='input-group date' id='datesabadohasta'>
						<s:textfield cssClass="form-control"
							name="usuariohorarios.SabadoHasta" />
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group col-xs-1">
			<label>Domingo</label>
		</div>
		<div class="form-group col-xs-6">
			<div class='col-sm-3'>
				<div class="form-group">
					<div class='input-group date' id='datedomingodesde'>
						<s:textfield cssClass="form-control"
							name="usuariohorarios.DomingoDesde" />
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</div>
			</div>
			<div class="form-group col-xs-1">Hasta</div>
			<div class='col-sm-3'>
				<div class="form-group">
					<div class='input-group date' id='datedomingohasta'>
						<s:textfield cssClass="form-control"
							name="usuariohorarios.DomingoHasta" />
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>


	<s:textfield type="hidden" name="usuariohorarios.Id"></s:textfield>
	<script
		src="${pageContext.request.contextPath}/_lib/sistema/usuarioshorarios/usuarioshorarios.js"
		charset="UTF-8"></script>
	<script
		src="${pageContext.request.contextPath}/_lib/venta/onkeypress.js"
		charset="UTF-8"></script>
</body>
</html>