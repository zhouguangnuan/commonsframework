<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctxBase" value="${pageContext.request.contextPath}"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
<c:set var="CSRFtotken" value="${pageContext.response.getHeader('CSRFtoken')}"/>
<script type="text/javascript" src="${ctxBase}/static/js/jquery.min.js"></script>
<script type="text/javascript">
	var CSRFtoken = "${CSRFtotken}";
	$(document).on('ajaxSend', function (elem, xhr, s) {
	    if (s.type.toUpperCase() == 'POST') {
	        if (xhr.setRequestHeader) {
	            xhr.setRequestHeader('CSRFtoken', CSRFtoken);
	        } else {
	            s.url += (s.url.indexOf("?") == -1) ? "?" : "&";
	            s.url += "CSRFtoken=" + CSRFtoken;
	        }
	    } 
	}); 
</script>
<%--<%@ taglib prefix="fns" uri="http://cn.singno.commonsframework.web/jsp/jstl/functions"%>--%>