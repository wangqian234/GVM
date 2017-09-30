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
}

var Dial = function (data) {
	this.elementId = data.elementId;
    this.width = data.width;
    this.height = data.height;
    this.range = data.range;
    this.unit = data.unit;
    this.value = data.value;
}

Dial.prototype.init = function () {
    var _this = this;
    var data=new Array();
    data.push(parseInt(_this.value));
    $(document).ready(function () {
        var chart = {
            type: 'gauge',
            plotBorderWidth: 0,
            plotBackgroundColor: {
                linearGradient: { x1: 0, y1: 0 },
                stops: [
                    [0, '#FFF4C6'],
                    [0.3, '#FFFFFF'],
                    [1, '#FFF4C6']
                ]
            },
            plotBackgroundImage: null,
            height: _this.height,
            width: _this.width
        };
        var credits = {
            enabled: false
        };

        var title = {
            text: ""
        };

        var pane = [{
            startAngle: -50,
            endAngle: 50,
            background: null,
            center: ['50%', '125%'],
            size: 200
        }];

        var yAxis = [{
            min: _this.range.min,
            max: _this.range.max,
            minorTickPosition: 0,
            tickPosition: 0,
            labels: {
                rotation: 'auto',
                distance: 0
            },
            pane: 0,
            title: {
                text: '<br/><span style="font-size:14px">单位：' + _this.unit + '</span>',
                y: -15
            }
        }];

        var plotOptions = {
            gauge: {
                dataLabels: {
                    enabled: false
                },
                dial: {
                    radius: '100%'
                }
            }
        };
        var series = [{
            data: data,
            yAxis: 0
        }];

        var json = {};
        json.chart = chart;
        json.credits = credits;
        json.title = title;
        json.pane = pane;
        json.yAxis = yAxis;
        json.plotOptions = plotOptions;
        json.series = series;

        $(_this.elementId).highcharts(json);
    });
}

var Line = function (data) {
	this.elementId = data.elementId;
    this.xAxisUnit = data.xAxisUnit;//X轴数组
    this.yAxisUnit = data.yAxisUnit;//Y轴名称
    this.title = data.title;//标题
    this.name = data.name;//折线名称
    this.value = data.value;//折线数据
}

Line.prototype.init = function () {
	var _this = this;
	   var title = {
	      text: _this.title   
	   };
	   var subtitle = {
	      text: ''
	   };
	   var xAxis = {
	      categories: _this.xAxisUnit
	   };
	   var yAxis = {
	      title: {
	         text: _this.yAxisUnit
	      },
	      plotLines: [{
	         value: 0,
	         width: 1,
	         color: '#808080'
	      }]
	   };   

	   var tooltip = {
	      valueSuffix: ''
	   }

	   var legend = {
	      layout: 'vertical',
	      align: 'right',
	      verticalAlign: 'middle',
	      borderWidth: 0
	   };

	   var series =  [
	      {
	         name: _this.name,
	         data: _this.value
	      }
	   ];

	   var json = {};

	   json.title = title;
	   json.subtitle = subtitle;
	   json.xAxis = xAxis;
	   json.yAxis = yAxis;
	   json.tooltip = tooltip;
	   json.legend = legend;
	   json.series = series;

	   $(this.elementId).highcharts(json);
	}



//散点图造假
var Scatter = function (data) {
	this.value = data.value;
	this.title = data.title;
	this.elementId = data.elementId;
	this.height = data.height
	this.width = data.width
}

Scatter.prototype.init = function () {
	var _this = this;
	$(document).ready(function() {  
		   var chart = {
		      type: 'scatter',
			  zoomType: 'xy',
			  height: _this.height,
	          width: _this.width
		   };
		   var title = {
		      text: _this.title   
		   };
		   var subtitle = {
		      text: ''  
		   };
		   var xAxis = {
		      title: {
		      enabled: true,
		         text: 'x轴坐标'
		      },
		      startOnTick: true,
		      endOnTick: true,
		      showLastLabel: true
		   };
		   var yAxis = {
		      title: {
		         text: 'y轴坐标'
		      }
		   };
		   var legend = {   
		      layout: 'vertical',
		      align: 'left',
		      verticalAlign: 'top',
		      x: 100,
		      y: 70,
		      floating: true,
		      backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF',
		      borderWidth: 1
		   }  
		   var plotOptions = {
		      scatter: {
		         marker: {
		            radius: 5,
		            states: {
		               hover: {
		                  enabled: true,
		                  lineColor: 'rgb(100,100,100)'
		               }
		            }
		         },
		         states: {
		            hover: {
		               marker: {
		                  enabled: false
		               }
		            }
		         },
		         tooltip: {
		            headerFormat: '',
		            pointFormat: '{point.x} , {point.y} '
		         }
		      }
		   };
		   var series= [{
		            color: 'rgba(223, 83, 83, .5)',
		            data: _this.value

		        }
		   ];     
		      
		   var json = {};   
		   json.chart = chart; 
		   json.title = title;   
		   json.subtitle = subtitle; 
		   json.legend = legend;
		   json.xAxis = xAxis;
		   json.yAxis = yAxis;  
		   json.series = series;
		   json.plotOptions = plotOptions;
		   $(_this.elementId).highcharts(json);
		  
		});
}

function Radar(data) {
	/*this.elementId = data.elementId;
	this.title = data.title;
	this.name = data.name;
	this.data = data.data;
	this.subtitle=data.subtitle;*/
}

Radar.prototype.init = function() {
	$(radarChart)
			.highcharts(
					{
				        chart: {
				            polar: true
				        },
				        title: {
				            text: this.title
				        },
				        pane: {
				            startAngle: 0,
				            endAngle: 360
				        },
				        xAxis: {
				            tickInterval: 72,
				            min: 0,
				            max: 360,
				            labels: {
				                formatter: function () {
				                    return this.value + '°';
				                }
				            }
				        },
				        yAxis: {
				            min: 0
				        },
				        plotOptions: {
				            series: {
				                pointStart: 0,
				                pointInterval: 72
				            },
				            column: {
				                pointPadding: 0,
				                groupPadding: 0
				            }
				        },
				        series: [ {
				            type: 'area',
				            name: '面积',
				            data: [1, 8, 2, 7, 3]
				        }]
				    });
}
