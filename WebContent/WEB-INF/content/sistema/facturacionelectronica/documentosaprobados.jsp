<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<sj:head locale="%{locale.language}" jquerytheme="start" />
<jsp:include page="../../template/header.jsp"></jsp:include>
</head>

<body class="nav-md">
	<div class="container body">
		<div class="main_container">

			<jsp:include page="../../template/menuizquierda.jsp"></jsp:include>

			<!-- top navigation -->
			<jsp:include page="../../template/menucabecera.jsp"></jsp:include>
			<!-- /top navigation -->

			<!-- page content -->
			<div class="right_col" role="main">
				<div class="">

					<div class="clearfix">
						<h4>CONSULTA DOCUMENTOS ELECTRONICOS</h4>
					</div>

					<div class="row">

						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_content">
									<div class="row">
										<div class="row">
											<div class="col-md-3 col-sm-6 col-xs-12">
												<label>Fecha</label>
												<div class="input-group input-append date"
													id="dateRangePickerDocumentos">
													<input id="fechaDocumentos" type="text"
														class="form-control" name="fechaDocumentos" /> <span
														class="input-group-addon add-on"><span
														class="glyphicon glyphicon-calendar"></span></span>
												</div>
											</div>
											<div class="col-md-3 col-sm-6 col-xs-12">
												<label>Empresa</label> <select class="form-control"
													id="empresa" name="empresa">
													<option value="002">EXPRESO INTERNACIONAL PALOMINO
														SAC</option>
													<option value="003">WARI CARGO SAC</option>
													<option value="004">TRANSPORTE WARI SAC</option>
												</select>
											</div>
											<div class="col-md-3 col-sm-6 col-xs-12">
												<label>Tipo Documento</label> <select class="form-control"
													id="tipodocumento" name="tipodocumento">
													<option value="01">FACTURA / ENCOMIENDA</option>
													<option value="02">BOLETA VENTA / ENCOMIENDA</option>
													<option value="03">FACTURA / PASAJES</option>
													<option value="04">BOLETA VENTA / PASAJES</option>
													<option value="05">NOTA CREDITO</option>
													<option value="06">NOTA DEBITO</option>
												</select>
											</div>
										</div>
										<div class="row" id="divConsulta"
											style="padding-bottom: 20px;">
											<div class="col-md-3 col-sm-6 col-xs-12">
												<label>Serie</label> <input id="serie" type="text"
													class="form-control" name="serie" placeholder="000"
													maxlength="4" onblur="javascript:AumentarCerosSerie(this);" />
											</div>
											<div class="col-md-3 col-sm-6 col-xs-12">
												<label>Numero</label> <input id="numero" type="text"
													class="form-control" placeholder="0000000" maxlength="7"
													name="numero"
													onblur="javascript:AumentarCerosNumero(this);"
													onkeypress="return validarSoloNumerosEnteros(event);" />
											</div>

										</div>
										<div class="row" style="padding-bottom: 20px;">
											<div id="divFactura" style="display: block;">
												<div class="col-md-3 col-sm-6 col-xs-12">
													<label>Ruc</label> <input id="ruc" type="text"
														class="form-control" name="ruc" maxlength="11"
														onblur="javascript:RucExistente(this);" />
												</div>
												<div class="col-md-3 col-sm-6 col-xs-12">
													<label>Razon</label> <input id="razon" type="text"
														class="form-control" name="razon" disabled="disabled" />
												</div>
											</div>
											<div class="col-md-3 col-sm-6 col-xs-12"
												style="padding-top: 23px;">
												<input id="btnConsultaDocumentos" type="button"
													class="btn btn-success" value="CONSULTAR DOCUMENTOS">
											</div>
										</div>
										<div class="row">
											<div class="col-xs-12">
												<table class="table table-condensed table-sm"
													data-toggle="table" data-locale="es-ES"
													data-pagination="true" data-height="350"
													data-unique-id="id" data-side-pagination="server"
													data-response-handler="responseHandlerDocumentos"
													data-query-params="queryParams"
													id="tblDocumentosElectronicos">
													<thead>
														<tr>
															<th data-field="id" data-visible="false">Nro</th>
															<th data-field="descarga" data-visible="false">Descarga</th>
															<th data-field="fechaEmision">Fecha Emision</th>
															<th data-field="empresa">Empresa</th>
															<th data-field="documentoElectronico">Documento</th>
															<th data-field="agencia">Agencia</th>
															<th data-field="servicio" data-visible="false">Servicio</th>
															<th data-field="enviar" data-visible="false">Enviar</th>
															<th data-field="servicioD">Servicio</th>
															<th data-field="estado" data-formatter="FormatterEstado"
																align="center">Estado</th>
															<th data-field="observaciones"
																data-formatter="FormatterDescargar">Descargar</th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
							<a class="btn btn-danger"
								href="https://www.escondatagate.net/receiver/page/c/U2FsdGVkX1/OXdPXNjWQPVa6+1u66OkQoUgjrW0+WbsD7aoc47TUXrSYGXbAB9G29ePlfdnZ+chj7u+bgzoMV3WLX1xssoWGxqTJRnkfcwE=">
								Documentos Después de 29/06/2019 EXPRESO INTERNACIONAL PALOMINO</a>
							<a class="btn btn-danger"
								href="https://www.escondatagate.net/receiver/page/c/U2FsdGVkX1/FeOynMYdYd8Zz+VpsDROA6WaXij1cvalFE1g0Tkw5VJP3q3YkxoWDoRQRovs/wB+FRzM9H7L5wXrWUKd8DDH0ftJfQ8IJkzE=">
								Documentos Después de 29/06/2019 TRANSPORTES WARI</a> <a
								class="btn btn-danger"
								href="https://www.escondatagate.net/receiver/page/c/U2FsdGVkX1+fWAveScrQvU0JmXjaepoi42RLaigitdNzpNbFqAPUVYtU/WPK98QXJv37NWNegdZmpiV4lv/afB/W7/uDd48TN6sTwi1iQ3w=">
								Documentos Después de 29/06/2019 WARI CARGO</a>
							<div class="row">
								<jsp:include page="../../template/footer-visa.jsp"></jsp:include>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../../template/footer.jsp"></jsp:include>
	<script
		src="${pageContext.request.contextPath}/_lib/sistema/facturacionelectronica/documentosaprobados.js"
		charset="UTF-8"></script>
	<script type="text/javascript">
	</script>
</body>
</html>
