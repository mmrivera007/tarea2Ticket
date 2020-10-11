$(function () {
	$(document).ready(function () {
	$('#contenedorpie').highcharts({
        chart: {
			plotBackgroundColor: null,
			plotBorderWidth: null,
			plotShadow: false,
            type: 'pie',
            options3d: {
                enabled: true,
                alpha: 45,
                beta: 0
        	}
        },
        title: {
            text: $('input[id$="pie_title"]').val()
        },
        subtitle: {
            text: $('input[id$="pie_subtitle"]').val() 
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                animation: true,
                allowPointSelect: true,
                cursor: 'pointer',
                depth: 40,
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                },
				
                showInLegend: true
            }
        },
        series: [{
            name: 'Brands',
            colorByPoint: true,
            data: $.parseJSON($('input[id$="pie_data_series"]').val())
        }]
    });
	});
});	