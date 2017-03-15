/**
 * 管理员统计订单信息图表 
 */

/**
 * Step:3 为模块加载器配置echarts的路径，从当前页面链接到echarts.js，定义所需图表路径
 */
require.config({
    paths: {
        echarts: './js'
    }
});
/**
 * Step:4 动态加载echarts然后在回调函数中开始使用，注意保持按需加载结构定义图表路径 
 */
/**
 * 打印机管理员统计订单状况图表
 */
require(
	    [
	        'echarts',
	        'echarts/chart/line',
	        'echarts/chart/bar'
	    ],
	    function (ec) {
	        //--- 折柱 ---
	       // var dayChart = ec.init(document.getElementById('day'));
	       var monthChart = ec.init(document.getElementById('printer-month-order'));
	      //  var yearChart = ec.init(document.getElementById('yaer'));
	        var option = {
	        	title : {
	                text: '月订单情况',
	        	},
	            tooltip : {
	                trigger: 'axis'
	            },
	            legend: {
	                data:['发起订单','成功订单','完成订单']
	            },
	            toolbox: {
	                show : true,
	                feature : {
	                    mark : {show: true},
	                    dataView : {show: true, readOnly: false},
	                    magicType : {show: true, type: ['line', 'bar']},
	                    restore : {show: true},
	                    saveAsImage : {show: true}
	                }
	            },
	            calculable : true,
	            xAxis : [
	                {
	                    type : 'category',
	                    boundaryGap : false,
	                    data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
	                }
	            ],
	            yAxis : [
	                {
	                    type : 'value',
	                    axisLabel : {
	                        formatter: '{value} 元'
	                    }
	                }
	            ],
	            series : [
	                {
	                    name:'发起订单',
	                    type:'line',
	                    data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
	                },
	                {
	                    name:'成功订单',
	                    type:'line',
	                    data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
	                }
	                ,
	                {
	                    name:'完成订单',
	                    type:'line',
	                    data:[1.3, 2.7, 4.5, 13.2, 14.3, 35.2, 85, 91, 24.3, 9.4, 3.2, 1.2]
	                }
	            ]
	        };
	        debugger
	      //  dayChart.setTheme("macarons");
	      //  dayChart.setOption(option);
	          monthChart.setTheme("macarons");
	          monthChart.setOption(option);
	     //   yearChart.setTheme("macarons");
	      //  yearChart.setOption(option);
	        
	    }
	);
