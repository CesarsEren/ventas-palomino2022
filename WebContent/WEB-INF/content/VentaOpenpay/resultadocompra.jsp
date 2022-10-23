<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link
	href="${pageContext.request.contextPath}/_lib/bootstrap/css/bootstrap.css"
	rel="stylesheet">
<!-- Facebook Pixel Code -->
<script>
		  !function(f,b,e,v,n,t,s)
		  {if(f.fbq)return;n=f.fbq=function(){n.callMethod?
		  n.callMethod.apply(n,arguments):n.queue.push(arguments)};
		  if(!f._fbq)f._fbq=n;n.push=n;n.loaded=!0;n.version='2.0';
		  n.queue=[];t=b.createElement(e);t.async=!0;
		  t.src=v;s=b.getElementsByTagName(e)[0];
		  s.parentNode.insertBefore(t,s)}(window, document,'script',
		  'https://connect.facebook.net/en_US/fbevents.js');
		  fbq('init', '372758356524832');
		  fbq('track', 'PageView');
	</script>
<noscript>
	<img height="1" width="1" style="display: none"
		src="https://www.facebook.com/tr?id=372758356524832&ev=PageView&noscript=1" />
</noscript>
<!-- End Facebook Pixel Code -->
<!-- Global site tag (gtag.js) - Google Analytics -->
<script async
	src="https://www.googletagmanager.com/gtag/js?id=UA-107487118-1"></script>
<script>
		 window.dataLayer = window.dataLayer || [];
		 function gtag(){dataLayer.push(arguments);}
		 gtag('js', new Date());
		 gtag('config', 'UA-107487118-1');
	</script>
</head>

<body>
	<div class="row">
		<div class="col-xs-8 col-xs-offset-2">
			<table class="table table-condensed">
				<tbody>
					<tr>
						<td align="center"><img alt=""
							src="${pageContext.request.contextPath}/_lib/img/logo.png"
							height="80px" width="120px"></td>
						<td align="right"><s:form action="generarvoucherVisa"
								id="formVoucherVisa">
								<s:textfield name="pedidoID" type="hidden"
									value="%{respuestaVisa.pedidoID}"></s:textfield>
							</s:form> <input type="button" id="btnVoucherVisa" style="height: 50px;"
							value="IMPRIMIR TICKET DE VIAJE" class="btn btn-success"
							onclick="javascript:enviarCorreo();"></td>

					</tr>
					<tr>
						<td>Tienda :</td>
						<td>EMP. TRANS. EXPR. INTERNACIONAL PALOMINO S.A.C</td>
					</tr>
					<tr>
						<td>Telefono :</td>
						<td>2020600</td>
					</tr>
					<tr>
						<td>Direccion Comercial :</td>
						<td>AV. LUNA PIZARRO N° 343 LA VICTORIA</td>
					</tr>
					<tr>
						<td>Dominio :</td>
						<td>www.grupopalomino.com.pe</td>
					</tr>
					<tr>
						<td>ID. del Pedido :</td>
						<td><%=session.getAttribute("iddelpedido")%></td>
					</tr>
					<tr>
						<td>Numero de Tarjeta :</td>
						<td><%=session.getAttribute("tarjeta")%></td>

					</tr>					
					<tr>
						<td>Importe :</td>
						<td><%=session.getAttribute("pagado")%></td>
					</tr>
					<tr>
						<td>Moneda :</td>
						<td>SOL</td>
					</tr>
					<tr>
						<td>Producto :</td>
						<td>Pasajes de Viaje</td>
					</tr>
					<tr>
						<td>Nombre del Emisor</td>
						<td><%=session.getAttribute("nombre")%></td>
					</tr>
					<tr>
						<td>Codigo Accion :</td>
						<td>Confirmado</td>
					</tr>
					<tr>
						<td>Estimado cliente, se le ha enviado a su correo el voucher
							de la compra, por favor imprimirla para poder realizar el canje
							del pasaje.</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="row">
		<s:if test="%{respuestaServer == false}">
			<div class="alert alert-danger">
				<!-- <s:property value="%{mensajeServer}" />-->
			</div>
		</s:if>
		<s:else>
			<div class="alert alert-success">
				<!--<s:property value="%{mensajeServer}" /> -->
			</div>
		</s:else>
	</div>
	<script type="text/javascript">
		
		function enviarCorreo(){
			
			document.getElementById("formVoucherVisa").submit();
		}
		
		function enviar(){
			var div = parent.document.getElementById("mensajevisa");
			
			div.className = "";
			div.innerHTML = "";
		}
		
		enviar();
	</script>
</body>

</html>