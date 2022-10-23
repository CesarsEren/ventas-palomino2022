<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="ventawizard">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container">
				<ul>
					<li><a href="#tab1" data-toggle="tab"><span class="badge">1</span>&nbsp;Destino
							y programación</a></li>
					<li><a href="#tab2" data-toggle="tab"><span class="badge">2</span>&nbsp;Reserva
							de boletos</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="tab-content">
		<div class="tab-pane" id="tab1">
			<jsp:include page="paso1.jsp"></jsp:include>
		</div>
		<div class="tab-pane" id="tab2">
			<jsp:include page="paso2.jsp"></jsp:include>
		</div>
		<div class="tab-pane" id="tab3">
			<jsp:include page="paso3.jsp"></jsp:include>
		</div>
		<div class="tab-pane" id="tab4">
			<jsp:include page="paso4.jsp"></jsp:include>
		</div>

		<ul class="pager wizard">
			<li class="previous first" style="display: none;"><a href="#">Primero</a></li>
			<li class="previous"><a href="#">Anterior</a></li>
			<li class="next last" style="display: none;"><a href="#">Ultimo</a></li>
			<li class="next"><a href="#">Siguiente</a></li>
		</ul>
	</div>
</div>