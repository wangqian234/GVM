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
		templateUrl : '/GVM/jsp/4preMain/testIndex.html',
		controller : 'preMainController'
	}).when('/qingyuan',{
		templateUrl : '/GVM/jsp/4preMain/testIndex.html',
		controller : 'preMainController'
	}).when('/guangming',{
		templateUrl : '/GVM/jsp/4preMain/testIndex.html',
		controller : 'preMainController'
	}).when('/pre',{
		templateUrl : '/GVM/jsp/4preMain/pre.html',
		controller : 'preMainController'
	})
} ]);

app.constant('baseUrl', '/GVM/');
app.factory('services', [ '$http', 'baseUrl', function($http, baseUrl) {
	var services = {};
	
	//设备基本信息
	services.selectEqInfo = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'preMainController/selectEqInfo.do',
			data : data
		});
	};

	//选择不停系统
	services.selectFacilityList = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'publicController/selectFacilityList.do',
			data : data
		});
	}
	
	//获取故障日期、维护日期等
	services.selectEquipDateById=function(data){
		return $http({
			method : 'post',
			url : baseUrl + 'preMainController/selectEquipDateById.do',
			data : data
		});
	}
	
	//preList
	services.selectPreList=function(data){
		return $http({
			method : 'post',
			url : baseUrl + 'preMainController/selectPreList.do',
			data : data
		});
	}
	
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
			
			//查询设备基本信息
			pre.selectEqInfo = function(){
				services.selectEqInfo({
					page : 1,
					limit : JSON.stringify(pre.limit)
				}).success(function(data){
					pre.list = data.list;
					getFirstInfo(data.list[0]);
					pageTurn(data.totalPage,1,selectEqInfo);
					
				});
			}
			
			//换页查询
			function selectEqInfo(page){
				services.selectEqInfo({
					page : page,
					limit : JSON.stringify(pre.limit)
				}).success(function(data){
					pre.list = data.list;
					getFirstInfo(data.list[0]);
				});
			}
			function getFirstInfo(e){
				services.selectEquipDateById({
					equipmentId : e.detector_Equipment_Id,
					equipmentTypeId : e.equipmentType_Id
				}).success(function(data) {
					pre.preInfo = data.preInfo;
				});
			}
			
			//查询系统列表
			pre.selectFacilityList = function(fun){
				services.selectFacilityList().success(function(data){
					pre.facilityList = data.list;
					pre.limit.facility = data.list[0].detector_Facility_Id;
					fun();
				});
			}
			
			// 查询不同的设备
			pre.selectEquipList = function(f) {
				
				pre.limit.facility = f.detector_Facility_Id;
				pre.eInfo = null;
				pre.selectEqInfo();
			}
			
			//点击表格的每一行变色
			pre.change = function(e, index) {
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
				pre.tableIndex=100;
				services.selectEquipDateById({
					equipmentId : e.detector_Equipment_Id,
					equipmentTypeId : e.equipmentType_Id
				}).success(function(data) {
					pre.preInfo = data.preInfo;
				});
			}
			
			
			
			/*//preList
				services.selectPreList({
					
				}).success(function(data){
					
					pre.list = data.list;
				});*/
			
			// 初始化
			function initPage() {
				console.log("初始化页面信息");
				pre.limit = {
						facility : "",
						project : sessionStorage.getItem("projectId")
				}
				pre.chosedIndex = 0;
				pre.tableIndex=0;
				if ($location.path().indexOf('/testIndex') == 0) {
					pre.selectFacilityList(pre.selectEqInfo);
				}else if($location.path().indexOf('/qingyuan') == 0){
					pre.selectFacilityList(pre.selectEqInfo);
				}else if($location.path().indexOf('/guangming') == 0){
					pre.selectFacilityList(pre.selectEqInfo);
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
// 维护类型过滤器
app.filter('mainType',function(){
	return function(input){
		var type = "";
		if(input==1){
			type = "状态检测";
		}else if(input == 2){
			type = "定期";
		}else if(input = 3){
			type = "事后";
		}else if(input == 4){
			type = "改善性";
		}
		return type;
	}
});

//设备状态
app.filter('mainState',function(){
	return function(input){
		var type = "";
		if(input== 1){
			type = "正常";
		}else if(input == 2){
			type = "维护";
		}else if(input = 3){
			type = "停用";
		}else if(input == 4){
			type = "报废";
		}
		return type;
	}
});
