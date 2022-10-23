<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<script
	src="${pageContext.request.contextPath}/_lib/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/_lib/gentella/custom.js"></script>
<script
	src="${pageContext.request.contextPath}/_lib/select2/js/select2.full.min.js"></script>
<script
	src="${pageContext.request.contextPath}/_lib/select2/js/i18n/es.js"></script>
<script
	src="${pageContext.request.contextPath}/_lib/bootstrap-dialog/js/bootstrap-dialog.js"></script>
<script
	src="${pageContext.request.contextPath}/_lib/bootstrap-table/bootstrap-table.min.js"></script>
<script
	src="${pageContext.request.contextPath}/_lib/bootstrap-table/bootstrap-table-locale-all.min.js"
	charset="UTF-8"></script>
<script
	src="${pageContext.request.contextPath}/_lib/bootstrap-table/bootstrap-table-filter.js"
	charset="UTF-8"></script>
<script
	src="${pageContext.request.contextPath}/_lib/bootstrap-table/bootstrap-table-filter-control.js"
	charset="UTF-8"></script>
<script
	src="${pageContext.request.contextPath}/_lib/bootstrap-wizard/jquery.bootstrap.wizard.min.js"
	charset="UTF-8"></script>
<script
	src="${pageContext.request.contextPath}/_lib/sweetalert2/sweetalert2.min.js"
	charset="UTF-8"></script>
<script
	src="${pageContext.request.contextPath}/_lib/bootstrap-datepicker/js/bootstrap-datepicker.min.js"
	charset="UTF-8"></script>
<script
	src="${pageContext.request.contextPath}/_lib/bootstrap-datepicker/locales/bootstrap-datepicker.es.min.js"
	charset="UTF-8"></script>
<script
	src="${pageContext.request.contextPath}/_lib/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"
	charset="UTF-8"></script>
<script
	src="${pageContext.request.contextPath}/_lib/timer/flipclock.min.js"
	charset="UTF-8"></script>
<script src="${pageContext.request.contextPath}/_lib/venta/paso1.js"
	charset="UTF-8"></script>
<script
	src="${pageContext.request.contextPath}/_lib/timer-moment/js/jquery.time-to.js"
	charset="UTF-8"></script>
<script
	src="${pageContext.request.contextPath}/_lib/timer-moment/js/visa.js"
	charset="UTF-8"></script>
<!--  <script src="${pageContext.request.contextPath}/_lib/datetimepicker-master/jquery.datetimepicker.full.js" charset="UTF-8"></script> -->

<sec:authorize access="isAuthenticated()">
	<input id="oculto" type="hidden"
		value="<sec:authentication property="principal.nivel"/>" />
</sec:authorize>

<!-- <script src="${pageContext.request.contextPath}/_lib/timer-moment/js/Gulpfile.js" charset="UTF-8"></script> -->



