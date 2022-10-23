<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="hasActionErrors()">
	<div class="row">
		<div class="alert alert-danger">
			<s:actionerror />
		</div>
	</div>
</s:if>