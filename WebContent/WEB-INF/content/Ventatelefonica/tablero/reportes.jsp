<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<html lang="es">
<head>
<script src="${pageContext.request.contextPath}/_lib/chart/Chart.min.js"
	charset="UTF-8"></script>
<script src="${pageContext.request.contextPath}/_lib/chart/utils.js"
	charset="UTF-8"></script>
<sj:head locale="%{locale.language}" jquerytheme="start" />
<jsp:include page="../../template/header.jsp"></jsp:include>

<style>
canvas {
	-moz-user-select: none;
	-webkit-user-select: none;
	-ms-user-select: none;
}
</style>
</head>

<body class="nav-md">
	<div class="container body">
		<div class="main_container">

			<jsp:include page="../../template/menuizquierda.jsp"></jsp:include>

			<!-- top navigation -->
			<div class="top_nav">

				<div class="nav_menu">
					<nav class="" role="navigation">
						<div class="nav toggle">
							<a id="menu_toggle"><i class="fa fa-bars"></i></a>
						</div>
					</nav>
				</div>

			</div>
			<!-- /top navigation -->

			<!-- page content -->
			<div class="right_col" role="main">
				<div class="">
					<div class="clearfix">
						<div class="col-md-12 col-sm-12 col-xs-12 venta-pasajes">
							Reporte de Ventas Telefónicas</div>
					</div>
					<div class="row">

						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_content">
									<div style="width: 50%;">
										<canvas id="canvas"></canvas>
									</div>
									<br> <br>
									<!--<button id="randomizeData">Randomize Data</button>
	<button id="addDataset">Add Dataset</button>
	<button id="removeDataset">Remove Dataset</button>
	<button id="addData">Add Data</button>
	<button id="removeData">Remove Data</button>-->
									<script>
		var MONTHS = ['Enero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Setiembre', 'Octubre', 'Noviembre', 'Diciembre'];
		var config = {
			type: 'line',
			data: {
				labels: ['01-Ene-2020', '02-Ene-2020', '03-Ene-2020', '04-Ene-2020', '05-Ene-2020', '06-Ene-2020', '07-Ene-2020', '08-Ene-2020', '09-Ene-2020', '10-Ene-2020', '11-Ene-2020'],
				datasets: [{
					label: 'VentaTelefonica1@grupopalomino.com.pe',
					backgroundColor: window.chartColors.red,
					borderColor: window.chartColors.red,
					data: [
					    250,
						//250,
						350,
						450,
						650,
						1250,
						1550 
					],
					fill: false,
				}, {
					label: 'VentaTelefonica2@grupopalomino.com.pe',
					fill: false,
					backgroundColor: window.chartColors.blue,
					borderColor: window.chartColors.blue,
					data: [
						280,
						550,
						850,
						950,
						1350,
						1650 
					],
				}]
			},
			options: {
				responsive: true,
				title: {
					display: true,
					text: 'Reporte de Venta Telefónica (Ganancia por Usuario y Mes)'
				},
				tooltips: {
					mode: 'index',
					intersect: false,
				},
				hover: {
					mode: 'nearest',
					intersect: true
				},
				/*scales: {
					x: {
						display: true,
						scaleLabel: {
							display: true,
							labelString: 'Month'
						}
					},
					y: {
						display: true,
						scaleLabel: {
							display: true,
							labelString: 'Value'
						}
					}
				}*/
			}
		};

		window.onload = function() {
			var ctx = document.getElementById('canvas').getContext('2d');
			window.myLine = new Chart(ctx, config);
		};

	/*	document.getElementById('randomizeData').addEventListener('click', function() {
			config.data.datasets.forEach(function(dataset) {
				dataset.data = dataset.data.map(function() {
					return randomScalingFactor();
				});

			});

			window.myLine.update();
		});*/

	/*	var colorNames = Object.keys(window.chartColors);
		document.getElementById('addDataset').addEventListener('click', function() {
			var colorName = colorNames[config.data.datasets.length % colorNames.length];
			var newColor = window.chartColors[colorName];
			var newDataset = {
				label: 'Dataset ' + config.data.datasets.length,
				backgroundColor: newColor,
				borderColor: newColor,
				data: [],
				fill: false
			};

			for (var index = 0; index < config.data.labels.length; ++index) {
				newDataset.data.push(randomScalingFactor());
			}

			config.data.datasets.push(newDataset);
			window.myLine.update();
		});*/

	/*	document.getElementById('addData').addEventListener('click', function() {
			if (config.data.datasets.length > 0) {
				var month = MONTHS[config.data.labels.length % MONTHS.length];
				config.data.labels.push(month);

				config.data.datasets.forEach(function(dataset) {
					dataset.data.push(randomScalingFactor());
				});

				window.myLine.update();
			}
		});*/

		/*document.getElementById('removeDataset').addEventListener('click', function() {
			config.data.datasets.splice(0, 1);
			window.myLine.update();
		});

		document.getElementById('removeData').addEventListener('click', function() {
			config.data.labels.splice(-1, 1); // remove the label first

			config.data.datasets.forEach(function(dataset) {
				dataset.data.pop();
			});

			window.myLine.update();
		});
		*/
		
	</script>



								</div>
							</div>
							<div class="x_panel">
								<jsp:include page="../../template/footer-visa.jsp"></jsp:include>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /page content -->

			<!-- footer content -->




			<!-- /footer content -->
		</div>
	</div>
	<jsp:include page="../../template/footer.jsp"></jsp:include>
	<script
		src="${pageContext.request.contextPath}/_lib/Ventatelefonica/tablero/reportes.js"
		charset="UTF-8"></script>

</body>
</html>
