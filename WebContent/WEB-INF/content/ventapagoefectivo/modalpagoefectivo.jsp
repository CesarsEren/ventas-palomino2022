<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div class="modal fade" id="modalEfectivo" tabindex="-1" role="dialog"
	aria-labelledby="modalLabelPaso2">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header"
				style="background: linear-gradient(#33AD45, #327d3d), #D5DC93; color: white;">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="modalLabelPaso2">¿Qué es
					PagoEfectivo?</h4>
			</div>
			<div class="modal-body">
				<div class="row" align="center">
					<div>
						<div id="cargaPagoEfectivo"
							style="display: none; padding-left: 15px;" align="center">
							<img src="_lib/gif/ajax-loader-pagoefectivo.gif" />
						</div>
					</div>
					<iframe
						src="https://pagoefectivo.pe/CNT/QueEsPagoEfectivo.aspx?COD=PAL&mon=1"
						style="border: 0" width="68%" height="550px;"></iframe>
				</div>
			</div>
			<div class="modal-footer">
				<div class="row">
					<div class="col-xs-12"></div>
				</div>

				<div class="row">
					<div class="col-xs-12">
						<button id="btnCerrarCliente" type="button"
							class="btn btn-default" data-dismiss="modal">Cerrar</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
