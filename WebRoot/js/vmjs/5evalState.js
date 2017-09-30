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
	$routeProvider.when('/testIndex', {
		templateUrl : '/GVM/jsp/5evalState/equipment.html',
		controller : 'baseInfoController'
	}).when('/qingyuan', {
		templateUrl : '/GVM/jsp/5evalState/equipment.html',
		controller : 'baseInfoController'
	}).when('/guangming', {
		templateUrl : '/GVM/jsp/5evalState/equipment.html',
		controller : 'baseInfoController'
	})
} ]);

app.constant('baseUrl', '/GVM/');
app.factory('services', [ '$http', 'baseUrl', function($http, baseUrl) {
	var services = {};
	// ghl查询设备
	services.selectEquipment = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'evalStateController/selectEquipment.do',
			data : data
		});
	};
	// 查询分析设备数据
	services.analysisEquipment = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'evalStateController/analysisEquipment.do',
			data : data
		});
	};
	// 查询facility类型列表
	services.selectFacilityList = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'publicController/selectFacilityList.do',
			data : data
		});
	}
	return services;
} ]);
app
		.controller(
				'baseInfoController',
				[
						'$scope',
						'services',
						'$location',
						function($scope, services, $location) {
							var evalState = $scope;
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
							// 用于基本信息换页查询
							function selectEquipList(page) {
								services.selectEquipment({
									page : page,
									limit : JSON.stringify(evalState.limit)
								}).success(function(data) {
									evalState.list = data.list;
								})
							}
							// 查询设备
							evalState.selectEquipList = function() {
								services.selectEquipment({
									page : 1,
									limit : JSON.stringify(evalState.limit)
								}).success(
										function(data) {
											pageTurn(data.totalPage, 1,
													selectEquipList);
											evalState.list = data.list;
										})
							}
							evalState.selectEList = function(f) {
								evalState.limit.facility = f.detector_Facility_Id;
								evalState.selectEquipList();
							}
							// 查询分析设备的数据
							evalState.analysisEquipment = function(
									e) {
								var oObj = window.event.srcElement;
								if (oObj.tagName.toLowerCase() == "td") {
									var oTr = oObj.parentNode;
									for (var i = 1; i < document.all.infoList.rows.length; i++) {
										document.all.infoList.rows[i].style.backgroundColor = "";
										document.all.infoList.rows[i].tag = false;
									}
									oTr.style.backgroundColor = "#EAEAEA";
									oTr.tag = true;
								}
								services
										.analysisEquipment(
												{
													detector_Equipment_Id : e.detector_Equipment_Id
												}).success(
												function(data) {
													var arr=[data.failarenum,data.Safety,data.life,data.Maintenance,data.Replacement];
													var xAxis=["故障概率","安全性能","寿命周期","维护成本","更换概率",];
													drawRadar("#radarChart", e.detector_Equipment_Name+"健康状态评估",
															"单项", arr,xAxis)
													/*
													 * evalState.evlist =
													 * data.list;
													 */
												})
							}
							/*
							 * evalState.change = function(index) { var oObj =
							 * window.event.srcElement; //
							 * alert(change.tagName.toLowerCase()); if
							 * (oObj.tagName.toLowerCase() == "td") { var oTr =
							 * oObj.parentNode; for (var i = 1; i <
							 * document.all.infoList.rows.length; i++) {
							 * document.all.infoList.rows[i].style.backgroundColor =
							 * ""; document.all.infoList.rows[i].tag = false; }
							 * oTr.style.backgroundColor = "#EAEAEA"; oTr.tag =
							 * true; } }
							 */
							// 查询facility类型列表
							evalState.selectFacilityList = function(fun) {
								services
										.selectFacilityList({

										})
										.success(
												function(data) {
													evalState.facilityList = data.list;
													evalState.limit.facility = data.list[0].detector_Facility_Id;
													fun();
												})
							}
							// 绘制雷达图
							function drawRadar(elementId, title, name, info,xAxis) {
								var data = {
									elementId : elementId,
									title : title,
									name : name,
									data : info,
									xAxis:xAxis
								};
								var radar = new Radar(data);

								radar.init();
							}
							// zq初始化
							function initPage() {
								// 初始化小区和系统的参数
								evalState.limit = {
									facility : " ",
									project : sessionStorage
											.getItem("projectId")
								};
								evalState.chosedIndex = 0;
								console.log("初始化页面信息");
								if ($location.path().indexOf('/testIndex') == 0) {
									evalState
											.selectFacilityList(evalState.selectEquipList);
								} else if ($location.path()
										.indexOf('/qingyuan') == 0) {
									evalState
											.selectFacilityList(evalState.selectEquipList);
								} else if ($location.path().indexOf(
										'/guangming') == 0) {
									evalState
											.selectFacilityList(evalState.selectEquipList);

								}
							}
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
app.filter('isOrNotNull', function() {
	return function(input) {
		var type = "";
		if (input) {
			type = input;
		} else {
			type = "无";
		}

		return type;
	}
});
