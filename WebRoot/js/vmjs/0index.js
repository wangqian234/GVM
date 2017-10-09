var app = angular
		.module(
				'index',
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
	$routeProvider.when('/', {
		templateUrl : '/GVM/jsp/0index/index.html',
		controller : 'indexController'
	})
} ]);

app.constant('baseUrl', '/GVM/');
app.factory('services', [ '$http', 'baseUrl', function($http, baseUrl) {
	var services = {};
	// zq添加班车定制需求
	services.selectBaseList = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'baseInfoController/selectBaseList.do',
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
	// 获取故障概率表
	services.selectFautList = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'publicController/selectFaultList.do',
			data : data
		});
	}
	// 查找设备预测日期
	services.selectPreList = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'publicController/selectPreList.do',
			data : data
		});
	}
	return services;
} ]);
app
		.controller(
				'indexController',
				[
						'$scope',
						'services',
						'$location',
						function($scope, services, $location) {
							var index = $scope;
							// 换页函数
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
							// 点击报警图标跳转页面
							index.jumpAlarmJsp = function(mode) {
								sessionStorage.setItem("mode", mode);
							}
							// 绘制横向柱状图
							function drawLineColumn(elementId, title, name,
									info, xAxis, unit, yAxis) {
								var data = {
									elementId : elementId,
									title : title,
									name : name,
									data : info,
									xAxis : xAxis,
									unit : unit,
									yAxis : yAxis
								};
								var lineColumn = new LineColumn(data);
								;

								lineColumn.init();
							}
							// zq将小数保留两位小数
							function changeNumType(number) {
								if (!number) {
									var defaultNum = 0;
									var num = parseFloat(parseFloat(defaultNum)
											.toFixed(2));
								} else {
									var num = parseFloat(parseFloat(number)
											.toFixed(2));
								}
								return num;
							}
							// zq初始化
							function initPage() {
								// 显示各个类型的报警设备
								// zq查询设备基本信息小区和系统的参数初始化
								index.result = {
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
													index.result.alarmNum1 = data.alarmNum1;
													index.result.alarmNum2 = data.alarmNum2;
													index.result.alarmNum3 = data.alarmNum3;
													index.result.alarmNum5 = data.alarmNum5;
													index.result.preAlarmNum = data.preAlarmNum;
												});
								// 查询故障概率较高的设备列表
								services
										.selectFautList({})
										.success(
												function(data) {
													
													var list = data.list;
													var info = new Array();
													var xAxis = new Array();
													for (var i = 0; i < list.length
															&& i < 8; i++) {
														info
																.push(changeNumType(list[i].equipment_Fault));
														xAxis
																.push(list[i].equipment_Project
																		+ "-"
																		+ list[i].equipment_Name);

													}
													drawLineColumn(
															"#LineColumnChart",
															"设备故障率前八统计表",
															"故障率", info, xAxis,
															"", "单位：1");
													index.result0=data.result0;//
													index.result1=data.result1;//
													index.result2=data.result2;//
													index.result3=data.result3;//

												});
							}
							services.selectPreList({}).success(function(data) {
								index.preList=data.list;
								
								index.result4=data.result1;//
								index.result5=data.result2;//
								index.result6=data.result3;//

							});
							initPage();
						} ]);

// 时间的格式化的判断
app.filter('dateType', function() {
	return function(input) {
		var type = "";
		if (input) {
			type = new Date(input).toLocaleDateString().replace(/\//g, '-');
		}

		return type;
	}
});
// 时间的格式化的判断
app.filter('analyse', function() {
	return function(input) {
		var type = "";
		if (input) {
			type = input;
		} else {
			type = "无";
		}

		return input;
	}
});
