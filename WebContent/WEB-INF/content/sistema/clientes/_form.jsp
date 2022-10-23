<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div class="modal fade" id="modalRegistro" tabindex="-1" role="dialog"
	aria-labelledby="modalLabelPaso2">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header"
				style="background: linear-gradient(#33AD45, #327d3d), #D5DC93; color: white;">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="modalLabelPaso2">Crear una Cuenta</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<!--  <input type="hidden" id="hidrefresh" value="0"> -->
					<p>Para poder continuar con su compra en linea, debe
						registrarse como cliente del sistema. Este registro solo se hace
						una vez.</p>
				</div>
				<div class="row">
					<div id="mensajeserver"></div>
				</div>
				<div class="row" style="padding-left: 5px;">
					<form method="post" id="formRegistraCliente" autocomplete="off"
						accept-charset="UTF-8">
						<div class="col-md-11">
							<div class="row">
								<div class="form-group col-xs-12">
									<label>Nombres</label>
									<s:textfield name="cliente.nombres"
										cssClass="form-control nombres" maxlength="60"></s:textfield>
								</div>
								<div class="form-group col-xs-12">
									<label>Apellidos</label>
									<s:textfield name="cliente.apellidos"
										cssClass="form-control apellidos" maxlength="60"></s:textfield>
								</div>
								<div class="form-group col-xs-12">
									<label>Telefono</label>
									<s:textfield name="cliente.telefono"
										cssClass="form-control telefono" maxlength="15"></s:textfield>
								</div>
								<div class="form-group col-xs-12">
									<label>Identidad</label> <select id="identidad"
										name="cliente.identidad" class="form-control">
										<option value="0">D.N.I</option>
										<option value="1">Pasaporte</option>
										<option value="2">Carnet Extranjeria</option>
									</select>
								</div>
								<div class="form-group col-xs-12">
									<label>Nro. Documento</label>
									<s:textfield name="cliente.numerodocumento"
										cssClass="form-control documento" maxlength="15"></s:textfield>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-12">
									<label>E-mail (Nombre de usuario)</label>
									<s:textfield name="cliente.email" cssClass="form-control email"
										maxlength="100"></s:textfield>
								</div>
								<div class="form-group col-xs-12">
									<label>Confirme su E-mail</label>
									<s:textfield name="cliente.confirm_email"
										cssClass="form-control confirmemail" maxlength="100"></s:textfield>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-12">
									<label>Contraseña</label>
									<s:textfield type="password" name="cliente.password"
										cssClass="form-control pass" maxlength="10"></s:textfield>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-12">
									<label>Confirmar contraseña</label>
									<s:textfield type="password" name="cliente.confirm_password"
										cssClass="form-control confirmpass" maxlength="10"></s:textfield>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="modal-footer">
				<div class="row">
					<div class="col-xs-12">
						<div>
							<div id="cargaRegistroCliente" style="display: none;"
								align="left">
								<img src="_lib/gif/ajax-loader-registra-cliente.gif" />
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-xs-12">
						<button id="btnRegistraCliente" type="button"
							class="btn btn-success">Crear una Cuenta</button>
						<button id="btnCerrarCliente" type="button"
							class="btn btn-default" data-dismiss="modal">Cerrar</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script
	src="${pageContext.request.contextPath}/_lib/clientes/clientesregistro.js"
	charset="UTF-8"></script>