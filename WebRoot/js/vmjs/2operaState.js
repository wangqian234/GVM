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
					if(data.size == 0){
						$("#nolist").show();
					}else{
						$("#nolist").hide();
					}
					console.log(data.list);
					//TODO
				});
			}
			function Dial(data) {
			    this.elementId = data.elementId;
			    this.width = data.width;
			    this.height = data.height;
			    this.title = data.title;
			    this.range = data.range;
			    this.unit = data.unit;
			    this.value = data.value;
			}

			Dial.prototype.init = function () {
				   var chart = {      
						      type: 'gauge',
						      plotBackgroundColor: null,
						      plotBackgroundImage: null,
						      plotBorderWidth: 0,
						      plotShadow: false
						   };
						   var title = {
						      text: '车速表'   
						   };     

						   var pane = {
						      startAngle: parseInt(-150),
						      endAngle: parseInt(150),
						      background: [{
						         backgroundColor: {
						            linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
						            stops: [
						               [0, '#FFF'],
						               [1, '#333']
						            ]
						         },
						         borderWidth: 0,
						         outerRadius: '109%'
						      }, {
						         backgroundColor: {
						            linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
						            stops: [
						               [0, '#333'],
						               [1, '#FFF']
						            ]
						         },
						         borderWidth: 1,
						         outerRadius: '107%'
						     }, {
						         // default background
						     }, {
						         backgroundColor: '#DDD',
						         borderWidth: [0],
						         outerRadius: '105%',
						         innerRadius: '103%'
						     }]
						   };

						   // the value axis
						   var yAxis = {
						      min: [0],
						      max: [200],

						      minorTickInterval: 'auto',
						      minorTickWidth: [1],
						      minorTickLength: [10],
						      minorTickPosition: 'inside',
						      minorTickColor: '#666',

						      tickPixelInterval: [30],
						      tickWidth: [2],
						      tickPosition: 'inside',
						      tickLength: [10],
						      tickColor: '#666',
						      labels: {
						         step: [2],
						         rotation: 'auto'
						      },
						      title: {
						         text: 'km/h'
						      },
						      plotBands: [{
						         from: [0],
						         to: [120],
						         color: '#55BF3B' // green
						      }, {
						         from: [120],
						         to: [160],
						         color: '#DDDF0D' // yellow
						      }, {
						         from: [160],
						         to: [200],
						         color: '#DF5353' // red
						      }]
						   };

						   var series= [{
						        name: 'Speed',
						        data: parseInt(80),
						        tooltip: {
						           valueSuffix: ' km/h'
						        }
						   }];     
						      
						   var json = {};   
						   json.chart = chart; 
						   json.title = title;       
						   json.pane = pane; 
						   json.yAxis = yAxis; 
						   json.series = series;     
						   
						   // Add some life
						   var chartFunction = function (chart) {
						      if (!chart.renderer.forExport) {
						         setInterval(function () {
						         var point = chart.series[0].points[0], newVal, inc = Math.round((Math.random() - 0.5) * 20);
						         newVal = point.y + inc;
						         if (newVal < 0 || newVal > 200) {
						            newVal = point.y - inc;
						         }
						         point.update(newVal);
						         }, 3000);
						      }
						   };
						   $('#container').highcharts(json,chartFunction);
						
			}	
			
			/*
			function Chart(data) {
				this.elementId = data.elementId;
				this.title = data.title;
				this.name = data.name;
				this.data = data.data;
				this.subtitle=data.subtitle;
			}
			Chart.prototype.init = function() {
				$(this.elementId)
						.highcharts(
								{
									credits:{
										text:'',
										href:''
									},
									chart : {
										plotBackgroundColor : null,
										plotBorderWidth : null,
										plotShadow : false
									},
									title : {
										text : this.title
									},
									subtitle:{
										text : this.subtitle,
								        style:{
								        	color:"red"
								        }
									},
									tooltip : {
										pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
									},
									plotOptions : {
										pie : {
											allowPointSelect : true,
											cursor : 'pointer',
											dataLabels : {
												enabled : true,
												format : '<b>{point.name}</b>: {point.percentage:.1f} %',
												style : {
													color : (Highcharts.theme && Highcharts.theme.contrastTextColor)
															|| 'black'
												}
											}
										}
									},
									series : [ {
										type : 'pie',
										name : this.name,
										data : this.data
									} ]
								});
			}*/
			
			
			// zq初始化
			function initPage() {
				console.log("初始化页面信息");
				if ($location.path().indexOf('/testIndex') == 0) {
					sessionStorage.setItem("project", 1);
					services.getoperaState({
						facility : 1,
						project: 1
					}).success(function(data) {
						
						operaState.operaList = data.list;
						console.log(operaState.operaList);
						if(data.size == 0){
							$("#nolist").show();
						}else{
							$("#nolist").hide();
						};
						
						 setTimeout(function(){
								for(var i=0;i<operaState.operaList.length;i++){
								 var data = {
										 elementId:'#'+i,
								            width: "250",//宽高
								            height: "150",
								            title: "title",//标题
								            range: {//量程
								                min: 0,
								                max: 500,
								            },
								            unit: "Kg",//单位
								            value: operaState.operaList[i].Detector_SensorData_Value//值
								        };
								 var cetu=new Cetu(data);
								 cetu.init();
								/* var dial = new Dial(data);
								dial.init();*/
//								 var data1={elementId:'#'+i,
//										 name:"123",
//										 title:"123",
//										 data:[20,20,20,20,20]
//											 };
//								 var chart=new Chart(data1);
//								 chart.init();
								 
								
							} },0);
					});
				} else if ($location.path().indexOf('/qingyuan') == 0) {
					sessionStorage.setItem("project", 9);
				} else if ($location.path().indexOf('/guangming') == 0) {
					sessionStorage.setItem("project", 13);
				}
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

