<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang=zh-cmn-Hans>

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>物业设备管理分析系统</title>
<!-- Fonts -->
<link
	href='http://fonts.googleapis.com/css?family=Roboto+Condensed:300,400'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900'
	rel='stylesheet' type='text/css'>
<!-- CSS Libs -->
<link rel="stylesheet" type="text/css"
	href="/GVM/lib/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="/GVM/lib/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="/GVM/lib/css/animate.min.css">
<link rel="stylesheet" type="text/css"
	href="/GVM/lib/css/bootstrap-switch.min.css">
<link rel="stylesheet" type="text/css"
	href="/GVM/lib/css/checkbox3.min.css">
<link rel="stylesheet" type="text/css"
	href="/GVM/lib/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css"
	href="/GVM/lib/css/dataTables.bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="/GVM/lib/css/select2.min.css">
<!-- CSS App -->
<link rel="stylesheet" type="text/css" href="/GVM/css/style.css">
<link rel="stylesheet" type="text/css" href="/GVM/css/vmcss.css">
<link rel="stylesheet" type="text/css"
	href="/GVM/css/themes/flat-blue.css">
<script type="text/javascript" src="/GVM/lib/js/jquery.min.js"></script>
</head>

<body class="flat-blue">
	<div id="container" class="app-container"  style="padding-bottom:0px">
		<div class="row content-container">
			<section>
				<div class="container-fluid" >
					
						<section id="index" class="row" ng-app="index"
							style="min-height: 40px;">
							<div ng-view></div>
						</section>
					
				</div>
	
		</section>

	</div>
	</div>
	<!-- <footer> </footer> -->
	<script type="text/javascript" src="/GVM/lib/js/jquery.min.js"></script>
	<script type="text/javascript" src="/GVM/lib/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/GVM/js/app.js"></script>
	<script type="text/javascript" src="/GVM/js/vmjs/chart.js"></script>
	<script type="text/javascript" src="/GVM/js/lib/angular/angular.js"></script>
	<!-- <script type="text/javascript" src="/GVM/js/lib/highcharts.js"></script> -->
	<script type="text/javascript"
		src="/GVM/js/lib/angular/angular-route.js"></script>
	<script type="text/javascript"
		src="/GVM/lib/js/bootstrap-switch.min.js"></script>
	<script type="text/javascript"
		src="/GVM/lib/js/jquery.matchHeight-min.js"></script>
	<script type="text/javascript"
		src="/GVM/lib/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="/GVM/lib/js/dataTables.bootstrap.min.js"></script>
	<script type="text/javascript" src="/GVM/lib/js/select2.full.min.js"></script>
	<script type="text/javascript" src="/GVM/lib/js/ace/ace.js"></script>
	<script type="text/javascript" src="/GVM/lib/js/ace/mode-html.js"></script>
	<script type="text/javascript" src="/GVM/lib/js/ace/theme-github.js"></script>
	<script type="text/javascript"
		src="/GVM/lib/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="/GVM/js/lib/pageTurn.js"></script>
	<!-- <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script> -->
	<script src="https://code.highcharts.com/highcharts.js"></script>
	<script src="https://code.highcharts.com/highcharts-more.js"></script>
	<script src="/GVM//js/vmjs/0index.js"></script>
</body>
</html>