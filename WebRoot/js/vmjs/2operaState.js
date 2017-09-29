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
		templateUrl : '/GVM/jsp/2operaState/operaState.html',
		controller : 'operaStateController'
	})
	$routeProvider.when('/qingyuan', {
		templateUrl : '/GVM/jsp/2operaState/operaState.html',
		controller : 'operaStateController'
	})
	$routeProvider.when('/guangming', {
		templateUrl : '/GVM/jsp/2operaState/operaState.html',
		controller : 'operaStateController'
	})
} ]);

app.constant('baseUrl', '/GVM/');
app.factory('services', [ '$http', 'baseUrl', function($http, baseUrl) {
	var services = {};
	
	services.getoperaState = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'operaState/getOperaState.do',
			data : data
		});
	};

	return services;
} ]);
app.controller('operaStateController', [ '$scope', 'services', '$location',
		function($scope, services, $location) {
			var operaState = $scope;

			operaState.selectEquipList=function(state){
				services.getoperaState({
					facility : state,
					project: sessionStorage.getItem("project")
				}).success(function(data) {
					operaState.operaList = data.list;
					operaState.operaListOth = data.listOth;
					console.log(operaState.operaList);
					console.log(operaState.operaListOth);
					if(!data.list.length != [] && !data.listOth.length != []){
						$("#nolist").show();
						$("#getSwitch").hide();
					}else{
						$("#nolist").hide();
					};
					 setTimeout(function(){
							for(var i=0;i<operaState.operaList.length;i++){
								var maxValue;
							if(operaState.operaList[i].Detector_Sensor_Unit == "℃"){
								if(operaState.operaList[i].Detector_SensorData_Value>100){
									maxValue = operaState.operaList[i].Detector_SensorData_Value+100;
								} else {
									maxValue = 100;
								};
							 var data = {
									 elementId:'#'+i,
							            width: "200",//宽高
							            height: "120",
							            range: {//量程
							                min: -50,
							                max: maxValue,
							            },
							            unit: operaState.operaList[i].Detector_Sensor_Unit,//单位
							            value: operaState.operaList[i].Detector_SensorData_Value//值
							        };
							 var dial = new Dial(data);
						     dial.init();
						}else{
							if(operaState.operaList[i].Detector_SensorData_Value>700){
								maxValue = operaState.operaList[i].Detector_SensorData_Value+100;
							} else {
								maxValue = 700;
							};
							var data = {
									 elementId:'#'+i,
							            width: "200",//宽高
							            height: "120",
							            range: {//量程
							                min: 0,
							                max: maxValue,
							            },
							            unit: operaState.operaList[i].Detector_Sensor_Unit,//单位
							            value: operaState.operaList[i].Detector_SensorData_Value//值
							        };
							 var dial = new Dial(data);
						     dial.init();
						} }},0);
				});
			}
			
			// zq初始化
			function initPage() {
				console.log("初始化页面信息");
				if ($location.path().indexOf('/testIndex') == 0) {
					sessionStorage.setItem("project", 1);
				} else if ($location.path().indexOf('/qingyuan') == 0) {
					sessionStorage.setItem("project", 9);
				} else if ($location.path().indexOf('/guangming') == 0) {
					sessionStorage.setItem("project", 13);
				}
				services.getoperaState({
					facility : 1,
					project: sessionStorage.getItem("project")
				}).success(function(data) {
					operaState.operaList = data.list;
					operaState.operaListOth = data.listOth;
					console.log(operaState.operaList);
					console.log(operaState.operaListOth);
					if(!data.list.length != [] && !data.listOth.length != []){
						$("#nolist").show();
						$("#getSwitch").hide();
					}else{
						$("#nolist").hide();
					};
					
					 setTimeout(function(){
							for(var i=0;i<operaState.operaList.length;i++){
								var maxValue;
							if(operaState.operaList[i].Detector_Sensor_Unit == "℃"){
								if(operaState.operaList[i].Detector_SensorData_Value>100){
									maxValue = operaState.operaList[i].Detector_SensorData_Value+100;
								} else {
									maxValue = 100;
								};
							 var data = {
									 elementId:'#'+i,
							            width: "200",//宽高
							            height: "120",
							            range: {//量程
							                min: -50,
							                max: maxValue,
							            },
							            unit: operaState.operaList[i].Detector_Sensor_Unit,//单位
							            value: operaState.operaList[i].Detector_SensorData_Value//值
							        };
							 var dial = new Dial(data);
						     dial.init();
						}else{
							if(operaState.operaList[i].Detector_SensorData_Value>700){
								maxValue = operaState.operaList[i].Detector_SensorData_Value+100;
							} else {
								maxValue = 700;
							};
							var data = {
									 elementId:'#'+i,
							            width: "200",//宽高
							            height: "120",
							            range: {//量程
							                min: 0,
							                max: maxValue,
							            },
							            unit: operaState.operaList[i].Detector_Sensor_Unit,//单位
							            value: operaState.operaList[i].Detector_SensorData_Value//值
							        };
							 var dial = new Dial(data);
						     dial.init();
						} }},0);
				});
			}
			initPage();
		} ]);
app.filter('alertState', function() {
	return function(input) {
		var type = "";
		switch (input) {
		case "0": type = "已解除";break;
		case "1": type = "报警中";break;
		default: break;
		}
		return type;
	}
});


