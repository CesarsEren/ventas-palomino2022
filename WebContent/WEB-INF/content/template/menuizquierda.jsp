<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<div class="col-md-3 left_col">
	<div class="left_col scroll-view">
		<div class="navbar nav_title" style="border: 0; background: #FEEE00;">
			<a href="http://www.grupopalomino.com.pe" class="site_title"
				target="_blank"> <!--  <span>Bienvenido <s:property value="#session.user.NomUsuario"/></span>-->
				<img style="padding-left: 10px; width: 110px; text-align: center;"
				class="img-responsive"
				src="${pageContext.request.contextPath}/_lib/img/logoMenu.png">
			</a>
		</div>
		<div class="clearfix"></div>
		<br />
		<sec:authorize access="isAuthenticated()">
			<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
				<div class="menu_section">
					<h3>Menú</h3>
					<ul class="nav side-menu">
						<li><sec:authorize access="isAuthenticated()">
								<sec:authorize
									access="!hasAnyRole('SMS','R','F','E','K','H','D','4','T')">
									<a><i class="fa fa-shopping-cart"></i> Ventas <span
										class="fa fa-chevron-down"></span> </a>
									<ul class="nav child_menu">
										<li><a href="ventapaso1">Venta de Pasajes</a></li>
									</ul>
								</sec:authorize>
							</sec:authorize></li>

						<sec:authorize access="hasAnyRole('1')">
							<li><a><i class="fa fa-edit"></i> Mantenimiento <span
									class="fa fa-chevron-down"></span></a>
								<ul class="nav child_menu">
									<li><a href="accedelistausuarios">Usuarios</a></li>
									<li><a href="accedelistaagencias">Agencias</a></li>
									<li><a href="accedelistaclienteruta">Clientes Rutas</a></li>
									<li><a href="accedelistausuarioshorarios">Clientes
											Horarios</a></li>
									<li><a href="accedelistaprogramacionpagos">Programacion
											Pagos</a></li>
									<li><a href="accedelistaversionesapp">Versiones APP</a></li>
								</ul></li>
							<li><a><i class="fa fa-edit"></i>Consultas MedioPago<span
									class="fa fa-chevron-down"></span></a>
								<ul class="nav child_menu">
									<li><a href="accedeconsultavisa">Consultar Visa</a>
									<li><a href="accedeconsultapagoefectivo">Consultar
											PagoEfectivo</a> <!--<li><a href="accedemantenimiento">Mantenimiento</a></li>-->
								</ul></li>
						</sec:authorize>

						<sec:authorize access="hasAnyRole('1','F','D')">
							<li><a><i class="fa fa-line-chart"></i> Facturación
									Electrónica <span class="fa fa-chevron-down"></span></a> <sec:authorize
									access="hasAnyRole('1','F')">
									<ul class="nav child_menu">
										<li><a href="documentosenvio">Envios a la Sunat</a></li>
										<!--li><a href="estadodocumentos">Estado de los Documentos</a></li-->
										<li><a href="documentosenviados">Documentos Enviados</a></li>
									</ul>
								</sec:authorize> <sec:authorize access="hasAnyRole('D')">
									<ul class="nav child_menu">
										<li><a href="documentosenviados">Documentos Enviados</a></li>
									</ul>
								</sec:authorize></li>
						</sec:authorize>
						<sec:authorize access="hasAnyRole('1','3','2')">
							<li><a><i class="fa fa-bar-chart-o"></i> Reportes <span
									class="fa fa-chevron-down"></span></a>
								<ul class="nav child_menu">
									<li><a href="voucher">Imprimir Voucher</a></li>
									<li><a href="estadocuenta">Estado de Cuenta</a></li>
								</ul></li>
						</sec:authorize>
						<!--<sec:authorize access="!hasAnyRole('SMS','R')">
											<li><a><i class="fa fa-user fa-fw"></i> Mi cuenta <span
												class="fa fa-chevron-down"></span></a>
											<ul class="nav child_menu">
												<li><a href="logout">Salir</a></li>
											</ul></li>
								</sec:authorize>-->

						<sec:authorize access="hasAnyRole('1','R','E','K','H')">


							<li><a><i class="fa fa-edit"></i>Reclamos<span
									class="fa fa-chevron-down"></span></a>
								<ul class="nav child_menu">
									<li><a href="atencionreclamos">Reclamos</a></li>
									<sec:authorize access="hasAnyRole('1','R','H')">
										<!-- <li><a href="listareclamos">Lista Reclamos de Agencia</a></li>-->
										<li><a href="reclamosestado">Estado Reclamos</a></li>
										<li><a href="atencionreclamosreporte">Reportes</a></li>
									</sec:authorize>
								</ul></li>
						</sec:authorize>


						<sec:authorize access="isAuthenticated()">
							<sec:authorize access="hasAnyRole('1','SMS')">
								<li><a><i class="fa fa-edit"></i> Mis Compras<span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="accedeverhistorial">Compras</a></li>
									</ul></li>
							</sec:authorize>
							<sec:authorize access="hasAnyRole('SMS')">
								<li><a><i class="fa fa-user fa-fw"></i> Mi cuenta <span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="salir">Salir</a></li>
									</ul></li>
							</sec:authorize>
						</sec:authorize>

						<sec:authorize access="isAuthenticated()">
							<sec:authorize access="hasAnyRole('1','B','K','H')">
								<li><a><i class="fa fa-edit"></i>Bitacora de Fallas<span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="accederegistrofalla">Registrar Falla</a></li>
										<li><a href="verfallas">Ver Fallas</a></li>
										<!-- <li><a href="accederTiposFalla">Registro Tipo Falla</a></li>-->
									</ul></li>
							</sec:authorize>
						</sec:authorize>

						<sec:authorize access="isAuthenticated()">
							<sec:authorize access="hasAnyRole('1','V')">
								<li><a><i class="fa fa-edit"></i>Venta Telefonica<span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="tableroventatelefonica">Tablero</a></li>
										<li><a href="reconfirmarCIP">Reconfirmar CIP(reenviar
												correo)</a></li>
										<li><a href="liberarasientos">Liberar Asientos</a></li>
										<!-- <li><a href="accederTiposFalla">Registro Tipo Falla</a></li>-->
									</ul></li>
							</sec:authorize>
						</sec:authorize>

						<sec:authorize access="isAuthenticated()">
							<sec:authorize access="hasAnyRole('T','1')">
								<li><a><i class="fa fa-cog"></i>Configuración TDP<span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="ciudadestdp">Ciudades</a></li>
										<li><a href="rutastdp">Rutas</a></li>
										<li><a href="programaciontdp">Programaciones</a></li>
										<li><a href="accedelistausuarios">Usuarios</a></li>
										<!-- <li><a href="accederTiposFalla">Registro Tipo Falla</a></li>-->
									</ul></li>
								<li><a><i class="fa fa-book"></i>Cuentas por Pagar TDP<span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="accederegistrofalla">Estados de Cuenta</a></li>
									</ul></li>
							</sec:authorize>
						</sec:authorize>


						<sec:authorize access="isAuthenticated()">
							<sec:authorize access="hasAnyRole('1','4')">
								<li><a><i class="fa fa-shopping-cart"></i>Ventas TDP<span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="ventatdpd">Ventas de Pasaje de
												Transporte de Personal</a></li>
									</ul></li>
								<li><a><i class="fa fa-book"></i>Reportes TDP<span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="estadocuenta">Mi Estados de Cuenta</a></li>
									</ul></li>
							</sec:authorize>
						</sec:authorize>


						<sec:authorize access="isAuthenticated()">
							<sec:authorize access="!hasAnyRole('SMS')">
								<li><a><i class="fa fa-user fa-fw"></i> Mi cuenta <span
										class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu">
										<li><a href="logout">Salir</a></li>
									</ul></li>
							</sec:authorize>
						</sec:authorize>
						<li>
					</ul>
				</div>

			</div>
		</sec:authorize>

		<!-- /sidebar menu -->

		<!--  
	 		<div class="sidebar-footer hidden-small">
			 	<img alt="" style="width:200px; height:150px;"  align="right" src="${pageContext.request.contextPath}/_lib/img/bus.png">
			</div>
			 -->
		<!-- /menu footer buttons -->

	</div>

</div>
