$(function () {
    Highcharts.getOptions().colors = Highcharts.map(Highcharts.getOptions().colors, function (color) {
        return {
            radialGradient: {
                cx: 0.5,
                cy: 0.3,
                r: 0.7
            },
            stops: [
                [0, color],
                [1, Highcharts.Color(color).brighten(-0.3).get('rgb')] // darken
            ]
        };
    });
	
    $('#pie_container').highcharts({
        chart: {
            type: 'pie',
            plotShadow: true,
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
            pointFormat: '<b>{series.name}:</b><br/> {point.percentage:.1f}%'
        },
        plotOptions: {
            pie: {
            	animation: true,
            	showInLegend: true,
            	//size:'100%',
            	allowPointSelect: true,
                cursor: 'pointer',
                depth: 40,
                dataLabels: {
                    enabled: true,
                    useHTML: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                    	color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    },
                    connectorColor: 'silver'
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'Participacion',
            data: $.parseJSON($('input[id$="pie_data_series"]').val())
        }]
    },
    function (chart) { // on complete
        chart.renderer.image('https://upload.wikimedia.org/wikipedia/commons/d/db/Senplades.png', top, top, 80, 30).add();
    });
});