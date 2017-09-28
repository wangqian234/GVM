<!DOCTYPE html>
<html lang=zh-cmn-Hans>

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>合同信息管理</title>
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
	<div id="container" class="app-container">
		<div class="row content-container">
			<nav class="navbar navbar-default navbar-fixed-top navbar-top">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-expand-toggle"
							onclick="saveLeftState()">
							<i class="fa fa-bars icon"></i>
						</button>
						<button type="button"
							class="navbar-right-expand-toggle pull-right visible-xs"
							onclick="saveLeftState()">
							<i class="fa fa-th icon"></i>
						</button>
						<ol id="bread" class="breadcrumb navbar-breadcrumb">
							<li>首页</li>
							<!-- <li class="active">Tabs & Steps</li> -->
						</ol>
					</div>
					<ul class="nav navbar-nav navbar-right">
						<button type="button"
							class="navbar-right-expand-toggle pull-right visible-xs">
							<i class="fa fa-times icon"></i>
						</button>

						<li class="dropdown danger"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown" role="button"
							aria-expanded="false"><i class="fa fa-star-half-o"></i> 4</a>
							<ul class="dropdown-menu danger  animated fadeInDown">
								<!-- <li class="title">Notification <span
									class="badge pull-right">4</span>
								</li> -->
								<li>
									<ul class="list-group notifications">
										<!-- <a href="#">
											<li class="list-group-item"><span class="badge">1</span>
												<i class="fa fa-exclamation-circle icon"></i> new
												registration</li>
										</a>
										<a href="#">
											<li class="list-group-item"><span class="badge success">1</span>
												<i class="fa fa-check icon"></i> new orders</li>
										</a> -->
										<a href="/GVM/routeController/toErrorPage.do#/Expend">
											<li class="list-group-item" onclick="setBread()"><span
												id="alertNum" class="badge danger">2</span> <i
												class="fa fa-comments icon"></i>设备报警</li>
										</a>
										<!-- <a href="#">
											<li class="list-group-item message">view all</li>
										</a> -->
									</ul>
								</li>
							</ul></li>

					</ul>
				</div>
				<audio id="audio" class="hidden">
					<source src="/GVM/audio/Msg.mp3"></source>
					<source src="/GVM/audio/msg.wav"></source>
				</audio>
			</nav>
			<section>
				<script type="text/javascript">
					//消息闪烁
					var show = function() { //有新消息时在title处闪烁提示
						var step = 0, _title = document.title;
						var timer = setInterval(function() {
							step++;
							if (step == 3) {
								step = 1
							}
							;
							if (step == 1) {
								document.title = '【　　　】' + _title
							}
							;
							if (step == 2) {
								document.title = '【新消息】' + _title
							}
							;
						}, 500);
						return [ timer, _title ];
					}
					var clear = function(timerArr) { //去除闪烁提示，恢复初始title文本
						if (timerArr) {
							clearInterval(timerArr[0]);
							document.title = timerArr[1];
						}
						;
					}
					//因为点击报警跳转显示页面时对于面包屑的处理
					function setBread() {
						sessionStorage
								.setItem("bread",
										"<li>首页</li><li>设备异常状态报警</li><li class='active'>实时异常状态</li>");
					}
					var clear = function(timerArr) { //去除闪烁提示，恢复初始title文本
						if (timerArr) {
							clearInterval(timerArr[0]);
							document.title = timerArr[1];
						}
						;
					}
					//初始化报警数据
					function initData() {
						$.getJSON("/GVM/errorState/selectErrorList.do", {},
								function(data) {
									$("#alertNum").text(data.alertTotalNum);
									sessionStorage.setItem("alertTotalNum",
											data.alertTotalNum);

								})
					}
					initData();
				/* 	window.setInterval(showalert, 1000 * 60 * 5); */
				window.setInterval(showalert,  200 * 5);
					function showalert() {
					
						var lastMsgCnt = sessionStorage
								.getItem("alertTotalNum");
						$.getJSON("/GVM/errorState/selectErrorList.do", {},
								function(data) {
									$("#alertNum").text(data.alertTotalNum);
									sessionStorage.setItem("alertTotalNum",
											data.alertTotalNum);
									if (data.alertTotalNum > lastMsgCnt) {
										//todo--提示爆闪
										var timerArr = show();
										$('#audio')[0].play();
										setTimeout(function() { //此处是过一定时间后自动消失
											clear(timerArr);
										}, 5000);
									} else {
										document.title = title;
									}

								});
					}
				</script>