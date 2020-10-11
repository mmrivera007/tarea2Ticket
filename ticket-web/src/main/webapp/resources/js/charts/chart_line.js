$(function () {
    $('#line_container').highcharts({
        chart: {
//            type: 'spline'
            type: 'area'            	
        },
        title: {
            text: $('input[id$="line_title"]').val()
        },
        subtitle: {
            text: $('input[id$="line_subtitle"]').val() 
        },
        legend: {
            itemHoverStyle: {
                color: '#f00',
                text:'Comic'	
            }
        },	        
        xAxis: {
            categories: $.parseJSON($('input[id$="line_xAxisCategories"]').val())
        },
        yAxis: {
            title: {
                text: $('input[id$="line_yAxisTitle"]').val() 
            }
        },
        tooltip: {
            //borderColor: null
        	enable:false,
       	 	shared: true,
       	 	crosshairs: true
        },        
        plotOptions: {
        	line: {
                dataLabels: {
                    enabled: false
                },
                enableMouseTracking: true
            },
            series: {
                animation: {
                    duration: 1500
                },
                marker: {
                enabled: false
            	}
            }
        },
        series: $.parseJSON($('input[id$="line_data_series"]').val())
        
    },
    function (chart) { // on complete
        chart.renderer.image('https://upload.wikimedia.org/wikipedia/commons/d/db/stpe.png', top, top, 80, 30).add();
    });
    
});