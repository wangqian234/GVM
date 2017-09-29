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


