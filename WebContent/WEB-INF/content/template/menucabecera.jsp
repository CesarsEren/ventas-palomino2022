<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div class="top_nav">

	<div class="nav_menu">
		<nav class="" role="navigation">
			<div class="nav toggle">
				<a id="menu_toggle" style="color: #009045;"><i
					class="fa fa-bars"></i></a>
			</div>
			<div class="pull-right">
				<div class="nav-toggle">
					<sec:authorize access="isAuthenticated()">
						<div style="padding-top: 10px;">
							<div>
								<b>Usuario: <span id="user"><sec:authentication
											property="principal.nombreCompleto" /></span></b>
							</div>
							<sec:authorize access="hasAnyRole('1','R','E')">
								<div>
									<b>Agencia: <sec:authentication
											property="principal.agenciaD" /></b>
								</div>
							</sec:authorize>
						</div>
					</sec:authorize>
					<sec:authorize access="isAnonymous()">
						<b>Usted es un cliente anónimo.</b>
					</sec:authorize>
					<div>
						<sec:authorize access="isAnonymous()">
					VENTA 	 POR INTERNET
				</sec:authorize>
						<sec:authorize access="isAuthenticated()">
							<sec:authorize access="hasAnyRole('3')">
						AGENTE AUTORIZADO
					</sec:authorize>
						</sec:authorize>
					</div>
				</div>
			</div>
		</nav>
	</div>

</div>