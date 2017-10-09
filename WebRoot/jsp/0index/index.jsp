<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<jsp:include page="/jsp/top.jsp" />
<jsp:include page="/jsp/left.jsp" />
<div class="container-fluid">
	<div class="side-body  padding-top" >
	<section id="index" class="row"  ng-app="index" style="min-height: 40px;">
	<div ng-view></div>
	</section> 
	</div>
</div>
</div>
<jsp:include page="/jsp/footer.jsp" />
</div>

</div>
</div>
<script src="/GVM//js/vmjs/0index.js"></script>
</body>
</html>