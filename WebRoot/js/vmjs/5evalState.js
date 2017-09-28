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
		templateUrl : '/GVM/jsp/5evalState/test.html',
		controller : 'baseInfoController'
	}).when('/qingyuan', {
		templateUrl : '/GVM/jsp/5evalState/equipment.html',
		controller : 'baseInfoController'
	})
} ]);

app.constant('baseUrl', '/GVM/');
app.factory('services', [ '$http', 'baseUrl', function($http, baseUrl) {
	var services = {};
	// ghl查询设备
	services.selectEquipment = function(data){
		return $http({
			method : 'post',
			url : baseUrl + 'evalStateController/selectEquipment.do',
			data : data
		});
	};
	//查询分析设备数据
	services.analysisEquipment = function(data){
		return $http({
			method : 'post',
			url : baseUrl +'evalStateController/analysisEquipment.do',
			data:data
		});
	};
	return services;
} ]);
app.controller('baseInfoController', [ '$scope', 'services', '$location',
		function($scope, services, $location) {
			var evalState = $scope;	
			//查询设备
			evalState.selectEquipList = function (data){
				alert(data)
				services.selectEquipment({
					Detector_Facility_Id : data
				}).success(function(data){
					evalState.list = data.list;
				})
			}
			//查询分析设备的数据
			evalState.analysisEquipment = function (detector_Equipment_Id){
				alert(detector_Equipment_Id);
				services.analysisEquipment({
					detector_Equipment_Id:detector_Equipment_Id,					
				}).success(function(data){
					evalState.evlist = data.list;
				})
			}
/*			evalState.change = function(index) {
				var oObj = window.event.srcElement;
				// alert(change.tagName.toLowerCase());
				if (oObj.tagName.toLowerCase() == "td") {
					var oTr = oObj.parentNode;
					for (var i = 1; i < document.all.infoList.rows.length; i++) {
						document.all.infoList.rows[i].style.backgroundColor = "";
						document.all.infoList.rows[i].tag = false;
					}
					oTr.style.backgroundColor = "#EAEAEA";
					oTr.tag = true;
				}
			}*/
			// zq初始化
			function initPage() {
				console.log("初始化页面信息");
				if ($location.path().indexOf('/testIndex') == 0) {
					
				}else if ($location.path().indexOf('/qingyuan')==0){
					evalState.show= {
							isActive0 : true,
							isActive1 : false,
							isActive2 : false,
							isActive3 : false,
							isActive4 : false
					};
					evalState.selectEquipList (1);
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
