var app = angular
		.module(
				'preMain',
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
	$routeProvider.when('/test', {
		templateUrl : '/GVM/jsp/4preMain/test.html',
		controller : 'preMainController'
	})
	$routeProvider.when('/equipInfo', {
		templateUrl : '/GVM/jsp/4preMain/equipInfo.html',
		controller : 'preMainController'
	})
} ]);

app.constant('baseUrl', '/GVM/');
app.factory('services', [ '$http', 'baseUrl', function($http, baseUrl) {
	var services = {};
	// 设备列表
	services.selectEquipList = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'preMainController/selectEquipList.do',
			data : data
		});
	};
	
	// 分析列表
	services.analyzeList = function(data){
		return $http({
			method : 'post',
			url : baseUrl + 'preMainController/analyzeList.do',
			data : data
		});
	};

	return services;
} ]);
app.controller(
		'preMainController', 
		[ 
		  '$scope', 
		  'services', 
		  '$location',
		function($scope, services, $location) {
			var pre = $scope;

			//获取设备list
			pre.selectEquipList = function(){
				console.log("fsdfs");
				console.log("fsdfs");
				console.log("fsdfs");
				console.log("fsdfs");
				services.selectEquipList({
					name : "你猜"
				}).success(function(data){
							alert(data.list);
							console.log("da" + JSON.stringify(data.list));
							pre.list = data.list;
						});
			}
			
			pre.show = function(data) {
				alert("fsdf");
			}
			
			pre.selectEquipList = function(data) {
				alert(data);
			}
			
			//设备的分析list
			pre.analyzeList = function(data){
				alert("分析");
				services.analyzeList({
					name : "你猜"
				}).success(function(data){
					console.log("da" + JSON.stringify(data.list));
				});
			}
			
			// zq初始化
			function initPage() {
				console.log("初始化页面信息");
				if ($location.path().indexOf('/equipInfo') == 0) {
					
				}else if($location.path().indexOf('/test')==0){
					pre.show = {
							isActive0 : true,
							isActive1 : false,
							isActive2 : false,
							isActive3 : false,
							isActive4 : false
					};
					pre.selectEquipList();
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
