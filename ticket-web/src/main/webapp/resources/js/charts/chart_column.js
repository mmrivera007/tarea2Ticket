var chart1; // globally available
//Highcharts.setOptions(Highcharts.theme);
$(document).ready(function () {
chart1 = new Highcharts.Chart({
 chart: {
  renderTo: 'contenedorbarras',
//  backgroundColor: {
//      linearGradient: { x1: 0, y1: 1, x2: 0, y2: 1 },
//      stops: [
//          [0, 'rgb(255, 255, 255)'],
//          [1, 'rgb(200, 200, 255)']
//      ]
//  },
  plotShadow: true,
  type: $('input[id$="col_type"]').val() , // change with your choice
      options3d: {
          enabled: true,
          alpha: 0,
          beta: 25,
          depth: 100,
          viewDistance: 25
  	}	  
 },
 title: {
  text: $('input[id$="col_title"]').val() 
 },
 subtitle: {
     text: $('input[id$="col_subtitle"]').val() 
 },
 
 plotOptions: {
     column: {
         animation: true,
         allowPointSelect: false,
         cursor: 'pointer',
         depth: 35,
         showInLegend: true,
         dataLabels: {
             enabled: true,
             format: '<b>{point.y}</b>: {point.percentage:.1f} %'
         }
     },
 series: {
     borderWidth: 0,
     dataLabels: {
         enabled: false,
         format: '<b>{point.y}</b>'//format: '{point.y:.1f}%'
     }
 }
 
 },
 tooltip: {
     //borderColor: null
	 shared: true,
	 crosshairs: true
 },
 xAxis: {
  categories: $.parseJSON($('input[id$="col_xAxisCategories"]').val())
  // Should be replace with JSON
 },
 yAxis: {
  title: {
   text: $('input[id$="col_yAxisTitle"]').val() 
  }
 },
 series: $.parseJSON($('input[id$="col_data_series"]').val())
},
function (chart) { // on complete
    chart.renderer.image('https://upload.wikimedia.org/wikipedia/commons/d/db/stpe.png', top, top, 80, 30).add();
}
);

});