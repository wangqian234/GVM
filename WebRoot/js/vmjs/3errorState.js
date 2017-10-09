var app = angular
		.module(
				'test',
				[ 'ngRoute' ],
				function($httpProvider) {// ngRoute引入路由依赖
					$httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
					$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

					// Override $http service's default transformRequest
					$httpProvider.defaults.transformRequest = [ function(data) {
						/**
						 * The workhorse; converts an object to
						 * x-www-form-urlencoded serialization.
						 * 
						 * @param {Object}
						 *            obj
						 * @return {String}
						 */
						var param = function(obj) {
							var query = '';
							var name, value, fullSubName, subName, subValue, innerObj, i;

							for (name in obj) {
								value = obj[name];

								if (value instanceof Array) {
									for (i = 0; i < value.length; ++i) {
										subValue = value[i];
										fullSubName = name + '[' + i + ']';
										innerObj = {};
										innerObj[fullSubName] = subValue;
										query += param(innerObj) + '&';
									}
								} else if (value instanceof Object) {
									for (subName in value) {
										subValue = value[subName];
										fullSubName = name + '[' + subName
												+ ']';
										innerObj = {};
										innerObj[fullSubName] = subValue;
										query += param(innerObj) + '&';
									}
								} else if (value !== undefined
										&& value !== null) {
									query += encodeURIComponent(name) + '='
											+ encodeURIComponent(value) + '&';
								}
							}

							return query.length ? query.substr(0,
									query.length - 1) : query;
						};

						return angular.isObject(data)
								&& String(data) !== '[object File]' ? param(data)
								: data;
					} ];
				});
app.run([ '$rootScope', '$location', function($rootScope, $location) {
	$rootScope.$on('$routeChangeSuccess', function(evt, next, previous) {
		console.log('路由跳转成功');
		$rootScope.$broadcast('reGetData');
	});
} ]);

// 路由配置
app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/Expend', {
		templateUrl : '/GVM/jsp/3errorState/Expend.html',
		controller : 'errorStateController'
	})
	$routeProvider.when('/Analyse', {
		templateUrl : '/GVM/jsp/3errorState/Analyse.html',
		controller : 'errorStateController'
	})
} ]);

app.constant('baseUrl', '/GVM/');
app.factory('services', [ '$http', 'baseUrl', function($http, baseUrl) {
	var services = {};

	services.selectErrorList = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'errorState/selectErrorList.do',
			data : data
		});
	};
	services.analyseError = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'errorState/analyseError.do',
			data : data
		});
	};
	services.selectErrorDetails = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'errorState/selectErrorDetails.do',
			data : data
		});
	};
	// 获取首页中的报警设备数量和预警设备数量
	services.selectErrorsByMode = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'publicController/selectErrorsByMode.do',
			data : data
		});
	}
	return services;
} ]);
app
		.controller(
				'errorStateController',
				[
						'$scope',
						'services',
						'$location',
						function($scope, services, $location) {
							var errorState = $scope;

							// 换页
							function pageTurn(totalPage, page, Func) {
								$(".tcdPageCode").empty();
								var $pages = $(".tcdPageCode");
								if ($pages.length != 0) {
									$(".tcdPageCode").createPage({
										pageCount : totalPage,
										current : page,
										backFn : function(p) {
											Func(p);
										}
									});
								}
							}

							errorState.limit = {
								startTime : "",
								endTime : ""
							}
							errorState.limits = {
								state : "0",
								type : "5"
							}

							function selectErrorListpage(p) {
								var expendErrorLimit = JSON
										.stringify(errorState.limit);
								services.selectErrorList({
									limit : expendErrorLimit,
									state : errorState.limits.state,
									page : p,
									type : errorState.limits.type
								}).success(function(data) {
									errorState.errorList = data.list;
									errorState.totalPage = data.totalPage;
								});
								showAlarmNums();
							}

							errorState.selectErrorListpage = function() {
								if (errorState.limit != "") {
									if (errorState.limit.startTime == "") {
										alert("请选择开始时间！");
										return false;
									}
									if (errorState.limit.endTime == "") {
										alert("请选择截止时间！");
										return false;
									}
									if (compareDateTime(
											errorState.limit.startTime,
											errorState.limit.endTime)) {
										alert("截止时间不能大于开始时间！");
										return false;
									}
								}
								var expendErrorLimit = JSON
										.stringify(errorState.limit);
								services
										.selectErrorList({
											limit : expendErrorLimit,
											state : errorState.limits.state,
											page : 1,
											type : errorState.limits.type
										})
										.success(
												function(data) {
													$(".tcdPageCode").empty();
													errorState.errorList = data.list;
													errorState.totalPage = data.totalPage;
													pageTurn(
															errorState.totalPage,
															1,
															selectErrorListpage);
												});
								showAlarmNums();
							}

							errorState.getPieChart = function() {
								if (errorState.limit.startTime == "") {
									alert("请选择开始时间！");
									return false;
								}
								if (errorState.limit.endTime == "") {
									alert("请选择截止时间！");
									return false;
								}
								if (compareDateTime(errorState.limit.startTime,
										errorState.limit.endTime)) {
									alert("截止时间不能大于开始时间！");
									return false;
								}
								var expendErrorLimit = JSON
										.stringify(errorState.limit);
								services.analyseError({
									limit : expendErrorLimit
								}).success(function(data) {
									errorAnalysePie = data.listPie;
									errorAnalyseLine = data.listLine;
									getPieData(errorAnalysePie, "", "");
									getLineData(errorAnalyseLine, "", "");
								})
							}
							function getLineData(errorAnalyseLine, title,
									subtitle) {
								var chart = {
									type : 'bar'
								};
								var title = {
									text : title
								};
								var subtitle = {
									text : subtitle
								};
								var categories = [];
								for (var i = 0; i < errorAnalyseLine.length; i++) {
									var obj = errorAnalyseLine[i];
									categories
											.push(obj.Detecter_Equipment_Name);
									data.push(obj.Detecter_Equipment_Num)
								}
								var xAxis = {
									categories : categories,
									title : {
										text : null
									}
								};
								var yAxis = {
									min : 0,
									title : {
										text : 'Population (millions)',
										align : 'high'
									},
									labels : {
										overflow : 'justify'
									}
								};
								var tooltip = {
									valueSuffix : '次'
								};
								var plotOptions = {
									bar : {
										dataLabels : {
											enabled : true
										}
									}
								};
								var legend = {
									layout : 'vertical',
									align : 'right',
									verticalAlign : 'top',
									x : -40,
									y : 100,
									floating : true,
									borderWidth : 1,
									backgroundColor : ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
									shadow : true
								};
								var credits = {
									enabled : false
								};
								var series = [ {
									name : '报警次数',
									data : data
								} ];
								var json = {};
								json.chart = chart;
								json.title = title;
								json.subtitle = subtitle;
								json.tooltip = tooltip;
								json.xAxis = xAxis;
								json.yAxis = yAxis;
								json.series = series;
								json.plotOptions = plotOptions;
								json.legend = legend;
								json.credits = credits;
								$('#linechart').highcharts(json);
							}
							function getPieData(errorAnalysePie, title, xtitle) {
								var colors = Highcharts.getOptions().colors;
								var categories = [];
								for (var i = 0; i < errorAnalysePie.length; i++) {
									var obj = errorAnalysePie[i];
									categories.push(obj.Detector_Project_Name);
								}
								var data = [];
								for (var i = 0; i < errorAnalysePie.length; i++) {
									var categories_Eupdata = [];
									var data_Eupdata = [];
									for (var j = 0; j < errorAnalysePie.length; j++) {
										var obj = errorAnalysePie[i].listEup[j].Detecter_Equipment_Name;
										var data = errorAnalysePie[i].listEup[j].Detecter_Equipment_Num;
										categories_Eup.push(obj);
										data_Eup.push(data);
									}
									var obj = {
										y : errorAnalysePie.Detector_Project_Num,
										color : colors[i],
										drilldown : {
											name : errorAnalysePie.Detector_Project_Name,
											categories_Eup : categories_Eupdata,
											data_Eup : data_Eupdata,
											color : colors[i]
										}
									}
									data.push(obj);
								}
								var browserData = [];
								var versionsData = [];
								var i, j;
								var dataLen = data.length;
								var drillDataLen;
								var brightness;

								for (i = 0; i < dataLen; i += 1) {
									// add browser data
									browserData.push({
										name : categories[i],
										y : data[i].y,
										color : data[i].color
									});
									drillDataLen = data[i].drilldown.data.length;
									for (j = 0; j < drillDataLen; j += 1) {
										brightness = 0.2 - (j / drillDataLen) / 5;
										versionsData
												.push({
													name : data[i].drilldown.categories[j],
													y : data[i].drilldown.data[j],
													color : Highcharts.Color(
															data[i].color)
															.brighten(
																	brightness)
															.get()
												});
									}
								}
								var chart = {
									type : 'pie'
								};
								var title = {
									text : title
								};
								var yAxis = {
									title : {
										text : xtitle
									}
								};
								var tooltip = {
									valueSuffix : '%'
								};
								var plotOptions = {
									pie : {
										shadow : false,
										center : [ '50%', '50%' ]
									}
								};
								var series = [
										{
											name : '数量',
											data : browserData,
											size : '60%',
											dataLabels : {
												formatter : function() {
													return this.y > 5 ? this.point.name
															: null;
												},
												color : 'white',
												distance : -50
											}
										},
										{
											name : '数量',
											data : versionsData,
											size : '80%',
											innerSize : '60%',
											dataLabels : {
												formatter : function() {
													// display only if larger
													// than 1
													return this.y > 1 ? ''
															+ this.point.name
															+ ': ' + this.y
															+ '%' : null;
												}
											}
										} ];
								var json = {};
								json.chart = chart;
								json.title = title;
								json.yAxis = yAxis;
								json.tooltip = tooltip;
								json.series = series;
								json.plotOptions = plotOptions;
								$('#piechart').highcharts(json);

							}

							function compareDateTime(startDate, endDate) {
								var date1 = new Date(startDate);
								var date2 = new Date(endDate);
								if (date2.getTime() < date1.getTime()) {
									return true;
								} else {
									return false;
								}
							}
							errorState.findDrawData = function() {
								if (errorState.limit.startTime == "") {
									alert("请选择开始时间！");
									return false;
								}
								if (errorState.limit.endTime == "") {
									alert("请选择截止时间！");
									return false;
								}
								if (compareDateTime(errorState.limit.startTime,
										errorState.limit.endTime)) {
									alert("截止时间不能大于开始时间！");
									return false;
								}
								getPieDraw1(errorState.limit.startTime,
										errorState.limit.endTime);
								getLineDraw1(errorState.limit.startTime,
										errorState.limit.endTime);
								$("#drawAnalyse").empty();
								$("#drawAnalyse")
										.append(
												"分析结果：报警次数最多的设备为展会演示中的现场供配电检测，其最可能的原因是A相负载高。");
							}

							function getPieDraw() {
								var colors = Highcharts.getOptions().colors;
								var categories = [ '展会演示', '清远凤城郦都', '光明迈瑞' ];
								var data = [
										{
											y : 55.11,
											color : colors[0],
											drilldown : {
												name : '展会演示报警次数',
												categories : [ '现场供配电检测',
														'1#变压器', '2#变压器',
														'现场给排水', '3#水泵房',
														'4#水泵房' ],
												data : [ 9.85, 5.35, 3.00,
														25.06, 2.81, 8.00 ],
												color : colors[0]
											}
										},
										{
											y : 21.63,
											color : colors[1],
											drilldown : {
												name : '清远凤城郦都报警次数',
												categories : [ '1#变压器',
														'2#变压器', '2#配电房',
														'3#配电房', '3#水泵房',
														'4#水泵房' ],
												data : [ 0.20, 7.00, 0.83,
														1.58, 6.12, 5.43 ],
												color : colors[3]
											}
										},
										{
											y : 23.26,
											color : colors[2],
											drilldown : {
												name : '光明迈瑞报警次数',
												categories : [ '1#变压器',
														'2#变压器', '2#配电房',
														'3#配电房', '4#配电房',
														'2#水泵房', '3#水泵房',
														'4#水泵房' ],
												data : [ 10.51, 2.31, 0.56,
														3.45, 4.37, 1.02, 0.64,
														0.40 ],
												color : colors[2]
											}
										} ];
								var browserData = [];
								var versionsData = [];
								var i, j;
								var dataLen = data.length;
								var drillDataLen;
								var brightness;

								// Build the data arrays
								for (i = 0; i < dataLen; i += 1) {
									// add browser data
									browserData.push({
										name : categories[i],
										y : data[i].y,
										color : data[i].color
									});

									// add version data
									drillDataLen = data[i].drilldown.data.length;
									for (j = 0; j < drillDataLen; j += 1) {
										brightness = 0.2 - (j / drillDataLen) / 5;
										versionsData
												.push({
													name : data[i].drilldown.categories[j],
													y : data[i].drilldown.data[j],
													color : Highcharts.Color(
															data[i].color)
															.brighten(
																	brightness)
															.get()
												});
									}
								}

								var chart = {
									type : 'pie'
								};
								var title = {
									text : '各小区报警分析'
								};
								var yAxis = {
									title : {
										text : 'Total percent market share'
									}
								};
								var tooltip = {
									valueSuffix : '%'
								};
								var plotOptions = {
									pie : {
										shadow : false,
										center : [ '50%', '35%' ]
									}
								};
								var series = [
										{
											name : '占比',
											data : browserData,
											size : '60%',
											dataLabels : {
												formatter : function() {
													return this.y > 5 ? this.point.name
															: null;
												},
												color : 'white',
												distance : -30
											}
										},
										{
											name : '占比',
											data : versionsData,
											size : '60%',
											innerSize : '60%',
											dataLabels : {
												formatter : function() {
													// display only if larger
													// than 1
													return this.y > 1 ? ''
															+ this.point.name
															+ ': ' + this.y
															+ '%' : null;
												}
											}
										} ];

								var json = {};
								json.chart = chart;
								json.title = title;
								json.yAxis = yAxis;
								json.tooltip = tooltip;
								json.series = series;
								json.plotOptions = plotOptions;
								$('#piechart').highcharts(json);
							}
							function getPieDraw1(startTime, endTime) {
								var colors = Highcharts.getOptions().colors;
								var categories = [ '展会演示' ];
								var data = [ {
									y : 100,
									color : colors[0],
									drilldown : {
										name : '展会演示报警次数',
										categories : [ '现场供配电检测', '现场给排水' ],
										data : [ 72.5, 27.5 ],
										color : colors[0]
									}
								} ];
								var browserData = [];
								var versionsData = [];
								var i, j;
								var dataLen = data.length;
								var drillDataLen;
								var brightness;

								// Build the data arrays
								for (i = 0; i < dataLen; i += 1) {
									// add browser data
									browserData.push({
										name : categories[i],
										y : data[i].y,
										color : data[i].color
									});

									// add version data
									drillDataLen = data[i].drilldown.data.length;
									for (j = 0; j < drillDataLen; j += 1) {
										brightness = 0.2 - (j / drillDataLen) / 5;
										versionsData
												.push({
													name : data[i].drilldown.categories[j],
													y : data[i].drilldown.data[j],
													color : Highcharts.Color(
															data[i].color)
															.brighten(
																	brightness)
															.get()
												});
									}
								}

								var chart = {
									type : 'pie'
								};
								var title = {
									text : startTime + "至" + endTime
											+ '各小区报警分析'
								};
								var yAxis = {
									title : {
										text : 'Total percent market share'
									}
								};
								var tooltip = {
									valueSuffix : '%'
								};
								var plotOptions = {
									pie : {
										shadow : false,
										center : [ '50%', '35%' ]
									}
								};
								var series = [
										{
											name : '占比',
											data : browserData,
											size : '60%',
											dataLabels : {
												formatter : function() {
													return this.y > 5 ? this.point.name
															: null;
												},
												color : 'white',
												distance : -30
											}
										},
										{
											name : '占比',
											data : versionsData,
											size : '60%',
											innerSize : '60%',
											dataLabels : {
												formatter : function() {
													// display only if larger
													// than 1
													return this.y > 1 ? ''
															+ this.point.name
															+ ': ' + this.y
															+ '%' : null;
												}
											}
										} ];

								var json = {};
								json.chart = chart;
								json.title = title;
								json.yAxis = yAxis;
								json.tooltip = tooltip;
								json.series = series;
								json.plotOptions = plotOptions;
								$('#piechart').highcharts(json);
							}
							$("#closeButten").click(function() {
								$(".modal-content").hide();
							});
							function getLineDraw() {
								var chart = {
									type : 'bar'
								};
								var title = {
									text : '占比前十设备'
								};
								var subtitle = {
									text : ''
								};
								var xAxis = {
									categories : [ '展会演示-现场给排水', '光明迈瑞-1#变压器',
											'展会演示-现场供配电检测', '展会演示-4#水泵房',
											'清远凤城郦都-2#变压器', '清远凤城郦都-3#水泵房',
											'清远凤城郦都-4#水泵房', '展会演示-1#变压器',
											'光明迈瑞-4#配电房', '光明迈瑞3#配电房' ],
									title : {
										text : null
									}
								};
								var yAxis = {
									min : 0,
									title : {
										text : '单位（次）',
										align : 'high'
									},
									labels : {
										overflow : 'justify'
									}
								};
								var tooltip = {
									valueSuffix : '次'
								};
								var plotOptions = {
									bar : {
										dataLabels : {
											enabled : true
										}
									}
								};
								var legend = {
									layout : 'vertical',
									align : 'right',
									verticalAlign : 'top',
									x : -40,
									y : 100,
									floating : true,
									borderWidth : 1,
									backgroundColor : ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
									shadow : true
								};
								var credits = {
									enabled : false
								};

								var series = [ {
									name : '报警次数',
									data : [ 183, 77, 72, 59, 51, 45, 40, 39,
											31, 25 ]
								} ];

								var json = {};
								json.chart = chart;
								json.title = title;
								json.subtitle = subtitle;
								json.tooltip = tooltip;
								json.xAxis = xAxis;
								json.yAxis = yAxis;
								json.series = series;
								json.plotOptions = plotOptions;
								json.legend = legend;
								json.credits = credits;
								$('#linechart').highcharts(json);
							}
							function getLineDraw1(startTime, endTime) {
								var chart = {
									type : 'bar'
								};
								var title = {
									text : startTime + "至" + endTime + '占比前十设备'
								};
								var subtitle = {
									text : ''
								};
								var xAxis = {
									categories : [ '展会演示-现场给排水', '展会演示-现场供配电检测' ],
									title : {
										text : null
									}
								};
								var yAxis = {
									min : 0,
									title : {
										text : '单位（次）',
										align : 'high'
									},
									labels : {
										overflow : 'justify'
									}
								};
								var tooltip = {
									valueSuffix : '次'
								};
								var plotOptions = {
									bar : {
										dataLabels : {
											enabled : true
										}
									}
								};
								var legend = {
									layout : 'vertical',
									align : 'right',
									verticalAlign : 'top',
									x : -40,
									y : 100,
									floating : true,
									borderWidth : 1,
									backgroundColor : ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
									shadow : true
								};
								var credits = {
									enabled : false
								};

								var series = [ {
									name : '报警次数',
									data : [ 108, 42 ]
								} ];

								var json = {};
								json.chart = chart;
								json.title = title;
								json.subtitle = subtitle;
								json.tooltip = tooltip;
								json.xAxis = xAxis;
								json.yAxis = yAxis;
								json.series = series;
								json.plotOptions = plotOptions;
								json.legend = legend;
								json.credits = credits;
								$('#linechart').highcharts(json);
							}

							errorState.selectErrorDetails = function(
									detector_Sensor_Id) {
								services.selectErrorDetails({
									SensorId : detector_Sensor_Id
								}).success(function(data) {
									errorState.errorDetileList = data.list;
									$(".modal-content").show();
								})
							}
							function showAlarmNums() {
								errorState.result = {
									alarmNum1 : "",
									alarmNum2 : "",
									alarmNum3 : "",
									alarmNum5 : "",
									preAlarmNum : ""
								}
								services
										.selectErrorsByMode({

										})
										.success(
												function(data) {
													
													errorState.result.alarmNum1 = data.alarmNum1;
													errorState.result.alarmNum2 = data.alarmNum2;
													errorState.result.alarmNum3 = data.alarmNum3;
													errorState.result.alarmNum5 = data.alarmNum5;
													errorState.result.preAlarmNum = data.preAlarmNum;
												});
							}
							// zq初始化
							function initPage() {
								console.log("初始化页面信息");
								if ($location.path().indexOf('/Expend') == 0) {
									var mode = sessionStorage.getItem("mode");
									if (mode) {
										errorState.limits.type = mode;
									}
									services
											.selectErrorList(
													{
														state : errorState.limits.state,
														page : 1,
														type : errorState.limits.type
													})
											.success(
													function(data) {
														errorState.errorList = data.list;
														errorState.totalPage = data.totalPage;
														pageTurn(
																errorState.totalPage,
																1,
																selectErrorListpage);
													});
									showAlarmNums();
													
								} else if ($location.path().indexOf('/Analyse') == 0) {
									// services.analyseError({
									//						
									// }).success(function(data){
									getPieDraw();
									getLineDraw();
									$("#drawAnalyse").empty();
									$("#drawAnalyse")
											.append(
													"分析结果：报警次数最多的设备为展会演示中的现场给排水设备，其最可能的原因是水压超高。");
									// errorAnalysePie = data.listPie;
									// errorAnalyseLine = data.listLine;
									// getPieData(errorAnalysePie,"","");
									// getLineData(errorAnalyseLine,"","");
									// })
								}
							}
							initPage();
						} ]);
// 时间的格式化的判断
app.filter('dateType', function() {
	return function(input) {
		return input.substring(0, input.length - 3);
	}
});
// 报警类型的判断
app.filter('alertType', function() {
	return function(input) {
		var type = "";
		switch (input) {
		case "1":
			type = "微信提醒";
			break;
		case "2":
			type = "短信提醒";
			break;
		case "3":
			type = "邮件提醒";
			break;
		case "4":
			type = "提交工单";
			break;
		case "5":
			type = "平台告警";
			break;
		case "6":
			type = "预警";
			break;
		default:
			type = "无";
			break;
		}

		return type;
	}
});
// 报警类型的判断
app.filter('alertState', function() {
	return function(input) {
		var type = "";
		switch (input) {
		case "0":
			type = "报警中";
			break;
		case "1":
			type = "已解除";
			break;
		default:
			break;
		}
		return type;
	}
});
