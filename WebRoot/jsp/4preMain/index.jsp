<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<jsp:include page="/jsp/top.jsp" />
<jsp:include page="/jsp/left.jsp" />
<div class="container-fluid">
	<div class="side-body padding-top">
	<section id="test" class="row"  ng-app="preMain" style="min-height: 40px;">
	<!-- ng-app="preMain" 和 js里的module保持一致 -->
	<div ng-view></div>
	</section> 
	</div>
</div>
</div>
<jsp:include page="/jsp/footer.jsp" />
</div>

</div>
</div>
<script src="/GVM/js/vmjs/4preMain.js"></script>
</body>
</html>