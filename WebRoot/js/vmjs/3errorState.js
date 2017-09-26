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

	return services;
} ]);
app.controller('errorStateController', [ '$scope', 'services', '$location',
		function($scope, services, $location) {
			var errorState = $scope;

			// 换页
			function pageTurn(totalPage, page, Func) {

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
			
			errorState.selectErrorList = function(){
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
				var expendErrorLimit = JSON.stringify(errorState.limit);
				services.selectErrorList({
					limit:expendErrorLimit
				}).success(function(data){
					errorState.erroeList = data.list
				})
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
			
			// zq初始化
			function initPage() {
				console.log("初始化页面信息");
				if ($location.path().indexOf('/Expend') == 0) {
					services.selectErrorList({
						
					}).success(function(data){
						errorState.erroeList = data.list
					})
				} else if ($location.path().indexOf('/Analyse') == 0) {
					
				}
			}
			initPage();
		} ]);

