<script type="text/javascript" src="/GVM/js/lib/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/GVM/js/lib/jquery.json-2.2.min.js"></script>
<div class="side-menu sidebar-inverse">
	<nav class="navbar navbar-default" role="navigation">
		<div class="side-menu-container">
			<div class="navbar-header"
				style="background-color: rgba(124, 189, 187, 1)">
				<a class="navbar-brand" href="#">
					<div class="icon">
						<img src="/GVM/img/lg2-50.png" height="25" width="25" />
					</div>
					<div class="title">公元物业管理平台</div>
				</a>
				<button type="button"
					class="navbar-expand-toggle pull-right visible-xs">
					<i class="fa fa-times icon"></i>
				</button>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a
					href="/GVM/routeController/toIndexPage.do"
					onclick="setProjectId()"> <span
						class="icon fa fa-tachometer"></span><span class="title">首页</span>
				</a></li>
				<li class="panel panel-default dropdown"
					onclick="changeBread('设备基本信息')"><a data-toggle="collapse"
					href="#dropdown-element"> <span class="icon fa fa-desktop"></span><span
						class="title">设备基本信息</span>
				</a> <!-- Dropdown level 1 -->
					<div id="dropdown-element" class="panel-collapse collapse">
						<div class="panel-body">
							<ul class="nav navbar-nav">
								<li onclick="setProjectId(1,1)"><a
									href="/GVM/routeController/toTestPage.do#/testIndex">展会演示</a></li>
								<li onclick="setProjectId(1,9)"><a
									href="/GVM/routeController/toTestPage.do#/qingyuan">清远凤城郦都</a></li>
								<li onclick="setProjectId(1,13)"><a
									href="/GVM/routeController/toTestPage.do#/guangming">光明迈瑞</a></li>
							</ul>
						</div>
					</div></li>
				<li class="panel panel-default dropdown"
					onclick="changeBread('设备运行状态信息')"><a data-toggle="collapse"
					href="#dropdown-table"> <span class="icon fa fa-table"></span><span
						class="title">设备运行状态信息</span>
				</a> <!-- Dropdown level 1 -->
					<div id="dropdown-table" class="panel-collapse collapse">
						<div class="panel-body">
							<ul class="nav navbar-nav">
								<li onclick="setProjectId(2,1)"><a
									href="/GVM/routeController/toOperaPage.do#/testIndex">展会演示</a></li>
								<li onclick="setProjectId(2,9)"><a
									href="/GVM/routeController/toOperaPage.do#/qingyuan">清远凤城郦都</a></li>
								<li onclick="setProjectId(2,13)"><a
									href="/GVM/routeController/toOperaPage.do#/guangming">光明迈瑞</a></li>
							</ul>
						</div>
					</div></li>
				<li class="panel panel-default dropdown"
					onclick="changeBread('设备异常状态报警')"><a data-toggle="collapse"
					href="#dropdown-form"> <span
						class="icon glyphicon glyphicon-exclamation-sign"></span><span
						class="title">设备异常状态报警</span>
				</a> <!-- Dropdown level 1 -->
					<div id="dropdown-form" class="panel-collapse collapse">
						<div class="panel-body">
							<ul class="nav navbar-nav">
								<li onclick="setProjectId(3,4)"><a
									href="/GVM/routeController/toErrorPage.do#/Expend">实时异常状态</a></li>
								<li onclick="setProjectId(3,5)"><a
									href="/GVM/routeController/toErrorPage.do#/Analyse">异常分析</a></li>
							</ul>
						</div>
					</div></li>

				<!-- Dropdown-->
				<li class="panel panel-default dropdown"
					onclick="changeBread('设备预测性维修保障策略分析')"><a
					data-toggle="collapse" href="#component-example"> <span
						class="icon fa fa-cubes"></span><span class="title">设备预测性维修保障策略分析</span>
				</a> <!-- Dropdown level 1 -->
					<div id="component-example" class="panel-collapse collapse">
						<div class="panel-body">
							<ul class="nav navbar-nav">
								<li onclick="setProjectId(4,1)"><a
									href="/GVM/routeController/toPrePage.do#/testIndex">展会演示</a></li>
								<li onclick="setProjectId(4,9)"><a
									href="/GVM/routeController/toPrePage.do#/qingyuan">清远凤城郦都</a></li>
								<li onclick="setProjectId(4,13)"><a
									href="/GVM/routeController/toPrePage.do#/guangming">光明迈瑞</a></li>
							</ul>
						</div>
					</div></li>
				<!-- Dropdown-->
				<li class="panel panel-default dropdown"
					onclick="changeBread('设备健康状态评估')"><a data-toggle="collapse"
					href="#dropdown-example"> <span class="icon fa fa-slack"></span><span
						class="title">设备健康状态评估</span>
				</a> <!-- Dropdown level 1 -->
					<div id="dropdown-example" class="panel-collapse collapse">
						<div class="panel-body">
							<ul class="nav navbar-nav">
								<li onclick="setProjectId(5,1)"><a
									href="/GVM/routeController/toEvalPage.do#/testIndex">展会演示</a></li>
								<li onclick="setProjectId(5,9)"><a
									href="/GVM/routeController/toEvalPage.do#/qingyuan">清远凤城郦都</a></li>
								<li onclick="setProjectId(5,13)"><a
									href="/GVM/routeController/toEvalPage.do#/guangming">光明迈瑞</a></li>
							</ul>
						</div>
					</div></li>
				<!-- Dropdown-->
				<!-- <li><a href="license.html"> <span
						class="icon fa fa-thumbs-o-up"></span><span class="title">License</span>
				</a></li> -->
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</nav>
</div>
<script type="text/javascript">
	//左侧栏菜单改变时修改题头面包屑
	function setProjectId(type, projectId) {

		sessionStorage.setItem("projectId", projectId);
		event.stopPropagation();
		var typeText = "";
		var project = "";
		switch (type) {
		case 1:
			typeText = "设备基本信息";
			break;
		case 2:
			typeText = "设备运行状态信息";
			break;
		case 3:
			typeText = "设备异常状态报警";
			break;
		case 4:
			typeText = "设备预测性维修保障策略分析";
			break;
		case 5:
			typeText = "设备健康状态评估";
			break;
		default:
			typeText = "";
		}
		;
		switch (projectId) {
		case 1:
			project = "展会演示";
			break;
		case 9:
			project = "清远凤城郦都";
			break;
		case 13:
			project = "光明迈瑞";
			break;
		case 4:
			project = "实时异常状态";
			break;
		case 5:
			project = "异常分析";
			break;
		default:
			project = "";
		}
		;

		$("#bread").html(
				"<li>首页</li><li>" + typeText + "</li><li class='active'>"
						+ project + "</li>");
		sessionStorage.setItem("bread", "<li>首页</li><li>" + typeText
				+ "</li><li class='active'>" + project + "</li>");
	}
	//左侧栏菜单改变时修改题头面包屑
	function changeBread(content) {
		$("#bread").html("<li>首页</li><li class='active'>" + content + "</li>");
		sessionStorage.setItem("bread", "<li>首页</li><li class='active'>"
				+ content + "</li>");
	}
	$(document).ready(function() {
		var content = sessionStorage.getItem("bread");
		var containerClass = sessionStorage.getItem("containerClass");
		//保持面包屑的上一个原始状态
		if (content != null) {
			$("#bread").html(content)
		}
		//保持页面伸展上一个原始状态（左侧）
		if (containerClass != null) {
			$("#container").attr("class", containerClass);
		}

	});
	//获取容器的状态，左侧是否展开
	function saveLeftState() {
		var val = $("#container").attr("class");
		var arr = val.split(" ");
		if (arr.length == 1) {
			sessionStorage.setItem("containerClass", "app-container expanded");
		} else if (arr.length == 2) {
			sessionStorage.setItem("containerClass", "app-container");
		}

	}
</script>