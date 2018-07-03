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
	$routeProvider.when('/', {
		templateUrl : '/GVM/jsp/1baseInfo/baseInfo.html',
		controller : 'baseInfoController'
	}).when('/testIndex', {
		templateUrl : '/GVM/jsp/1baseInfo/baseInfo.html',
		controller : 'baseInfoController'
	}).when('/qingyuan', {
		templateUrl : '/GVM/jsp/1baseInfo/baseInfo.html',
		controller : 'baseInfoController'
	}).when('/guangming', {
		templateUrl : '/GVM/jsp/1baseInfo/baseInfo.html',
		controller : 'baseInfoController'
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
	// zq查询FacilityList
	services.selectFacilityList = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'publicController/selectFacilityList.do',
			data : data
		});
	}
	services.selectEquipmentById = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'baseInfoController/selectEquipmentById.do',
			data : data
		});

	};
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
							var baseInfo = $scope;
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

							// zq查询设备基本信息列表
							baseInfo.selectBaseList = function() {
								console.log("zq"
										+ JSON.stringify(baseInfo.limit));
								services
										.selectBaseList(
												{
													page : 1,
													limit : JSON
															.stringify(baseInfo.limit)
												})
										.success(
												function(data) {
													baseInfo.list = data.list;
													getFirstInfo(data.list[0].equipment_Id);
													pageTurn(data.totalPage, 1,
															selectBaseList);
													
													
												});
							}
							// zq用于基本信息换页查询
							function selectBaseList(page) {
								services.selectBaseList({
									page : page,
									limit : JSON.stringify(baseInfo.limit)
								}).success(function(data) {
									baseInfo.list = data.list;
									getFirstInfo(data.list[0].equipment_Id);
								});
							}
							function getFirstInfo(equipment_Id) {
								services.selectEquipmentById({
									equipmentId : equipment_Id
								}).success(function(data) {
									baseInfo.eInfo = data.equipmentInfo;
								});
							}
							// zq点击表格的每一行变色
							baseInfo.change = function(e, index) {
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
								baseInfo.tableIndex=100;
								services.selectEquipmentById({
									equipmentId : e.equipment_Id
								}).success(function(data) {
									baseInfo.eInfo = data.equipmentInfo;
								});
							}

							// 查询系统类型列表
							baseInfo.selectFacilityList = function(fun) {
								services
										.selectFacilityList()
										.success(
												function(data) {
													baseInfo.facilityList = data.list;
													baseInfo.limit.facility = data.list[0].detector_Facility_Id;
													fun();
												});
							};
							// 点击不同的系统查询不同的设备
							baseInfo.selectEquipList = function(f) {
								baseInfo.limit.facility = f.detector_Facility_Id;
								baseInfo.eInfo = null;
								baseInfo.selectBaseList();
							}
							// zq初始化
							function initPage() {
								// zq查询设备基本信息小区和系统的参数初始化

								if (sessionStorage.getItem("projectId") == "undefined"
										|| sessionStorage.getItem("projectId") == null
										|| sessionStorage.getItem("projectId") == "") {
									sessionStorage.setItem("projectId", '1');
								}

								baseInfo.limit = {
									facility : "",
									project : sessionStorage
											.getItem("projectId")
								};

								// 给ul的第一个li显示样式
								baseInfo.chosedIndex = 0;
								baseInfo.tableIndex = 0;
								console.log("初始化页面信息");
								if ($location.path().indexOf('/testIndex') == 0) {
									baseInfo
											.selectFacilityList(baseInfo.selectBaseList);
								} else if ($location.path()
										.indexOf('/qingyuan') == 0) {
									baseInfo
											.selectFacilityList(baseInfo.selectBaseList);

								} else if ($location.path().indexOf(
										'/guangming') == 0) {
									baseInfo
											.selectFacilityList(baseInfo.selectBaseList);
								} else {
									baseInfo
											.selectFacilityList(baseInfo.selectBaseList);
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
