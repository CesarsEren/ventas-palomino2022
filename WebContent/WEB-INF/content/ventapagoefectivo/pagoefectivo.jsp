<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<form id="frmPagoEfectivo"
	action="<s:property value="UrlPagoEfectivo"/>" method="post"
	target="iframpagoefectivo"></form>

<div class="row">
	<iframe name="iframpagoefectivo" width="100%" height="650px;"></iframe>
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
		
		document.getElementById("frmPagoEfectivo").submit();
	}

	enviar();
</script>