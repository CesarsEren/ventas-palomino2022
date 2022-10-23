<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<form id="frmHidden"
	action="https://www.multimerchantvisanet.com/formularioweb/formulariopago.aspx"
	method="post" target="iframvisa">
	<s:textfield name="ETICKET" type="hidden"></s:textfield>
</form>

<s:if test="%{tarjetaCredito != null}">
	<s:textfield id="descripcionError" type="hidden"
		name="tarjetaCredito.descripcionError"></s:textfield>
</s:if>

<div id="diviframevisa" class="row">
	<iframe name="iframvisa" width="100%" height="650px;"></iframe>
</div>
<div id="divtiempo" class="row" style="display: none;">
	<div class="col-md-5">
		<label class="alert alert-danger">Su tiempo ha expirado, por
			favor regresar al paso 1 para realizar una nueva compra.</label>
	</div>
	<div class="col-md-1">
		<img alt=""
			src="${pageContext.request.contextPath}/_lib/img/visa-flecha.png"
			height="35px" width="30px">
	</div>
	<div class="col-md-4">
		<a href="venta" class="btn btn-success"><b
			style="font-size: 15px;">Regresar al Paso1</b></a>
	</div>
</div>

<script>

	function enviar(){
		
		var div = parent.document.getElementById("mensajevisa");
		
		if(document.getElementById("descripcionError")){
			div.innerHTML = document.getElementById("descripcionError").value;
			div.className = "alert alert-danger";
		}
		else{
			div.className = "";
			div.innerHTML = "";
		}
		
		document.getElementById("frmHidden").submit();
	}

	enviar();
</script>