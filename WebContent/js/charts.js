/*
 JS for populating the google charts
 */

var cakes = 0;
var cookies = 0;
var cupcakes = 0;
var other = 0;

var cakesLastMonth = 0;
var cookiesLastMonth = 0;
var cupcakesLastMonth = 0;
var otherLastMonth = 0;

var cakesTwoMonthsAgo = 0;
var cookiesTwoMonthsAgo = 0;
var cupcakesTwoMonthsAgo = 0;
var otherTwoMonthsAgo = 0;

var fullDate = new Date();


//used for counting up the products and month totals
var twoDigitMonth = ((fullDate.getMonth().length + 1) === 1) ? (fullDate
        .getMonth() + 1) : (fullDate.getMonth() + 1);
var lastMonth = (twoDigitMonth - 1);
var twoMonthsAgo = (twoDigitMonth - 2);

$.get('convert-orders.do', function (responseText) {
    var json = JSON.parse(responseText);
    console.log(json);
    $.each(json, function (key, value) {
        
        //get order month
        var date = value.dueDate;
        var split = date.substr(date.indexOf('-') + 1);
        var month = split.split('-')[0];

        //convert all relevant months to same type
        var thisMonthAsInt = parseInt(twoDigitMonth);
        var orderMonthAsInt = parseInt(month);
        var lastMonthAsInt = parseInt(lastMonth);
        var twoMonthsAgoAsInt = parseInt(twoMonthsAgo);
        //perform logic to count up product and month totals for the charts
        console.log(orderMonthAsInt + " " + thisMonthAsInt);
      //  if (orderMonthAsInt <= thisMonthAsInt) {
            
            switch (value.product) {
                case 'cake':
                    cakes++;
                    break;
                case 'cookies':
                    cookies++;
                    break;
                case 'cupcakes':
                    cupcakes++;
                    break;
                default:
                    other++;
                    break;
            }
            //if(orderMonthAsInt === lastMonthAsInt) {
                    switch (value.product) {
                        case 'cake':
                            cakesLastMonth++;
                            break;
                        case 'cookies':
                            cookiesLastMonth++;
                            break;
                        case 'cupcakes':
                            cupcakesLastMonth++;
                            break;
                        default:
                            otherLastMonth++;
                            break;
                    }
              //  } else if (orderMonthAsInt === twoMonthsAgoAsInt) {
                    switch (value.product) {
                        case 'cake':
                            cakesTwoMonthsAgo++;
                            break;
                        case 'cookies':
                            cookiesTwoMonthsAgo++;
                            break;
                        case 'cupcakes':
                            cupcakesTwoMonthsAgo++;
                            break;
                        default:
                            otherTwoMonthsAgo++;
                            break;
                    }
//            }
        //}
    });
});

//load charts, plug in the data
google.charts.load('current', {
    packages: ['corechart', 'bar']
});
google.charts.setOnLoadCallback(drawChart);
function drawChart() {

    var piechart_data = new google.visualization.DataTable();
    piechart_data.addColumn('string', 'Product');
    piechart_data.addColumn('number', 'Sold');
    piechart_data.addRows([['Cake', cakes], ['Cookies', cookies],
        ['Cupcakes', cupcakes], ['Other', other]]);
    var piechart_options = {
        'title': 'Product Sales',
        'legend': 'left',
        'is3D': true,
        'backgroundColor': '#CFC3F8'

    };
    var piechart = new google.visualization.PieChart(document
            .getElementById('piechart'));
    piechart.draw(piechart_data, piechart_options);

    var barchart_data = new google.visualization.arrayToDataTable([
        ['Product', 'Two Months Ago', 'Last Month'],
        ['Cakes', cakesTwoMonthsAgo, cakesLastMonth],
        ['Cookies', cookiesTwoMonthsAgo, cookiesLastMonth],
        ['Cupcakes', cupcakesTwoMonthsAgo, cupcakesLastMonth],
        ['Other', otherTwoMonthsAgo, otherLastMonth]]);
    var barchart_options = {
        width: 600,
        height: 350,
        backgroundColor: '#CFC3F8',
        chartArea: {
            backgroundColor: '#CFC3F8'
        },
        chart: {
            title: 'Sales',
            subtitle: 'Sales for the past two months by product type'
        },
        series: {
            0: {
                axis: 'number sold'
            }  // Bind series 0 to an axis named 'number sold'.
        },
        axes: {
            y: {
                distance: {
                    label: 'number sold'
                } // Left y-axis.
            }
        },
        vAxis: {
            viewWindow: {
                min: 0,
                max: 50
            },
            ticks: [1, 5, 10, 20, 30, 50] // display labels every 25
        }
    };
    var barchart = new google.charts.Bar(document.getElementById('chart_div'));
    barchart.draw(barchart_data, google.charts.Bar
            .convertOptions(barchart_options));


    //nicely resize the charts on page resize
    $(window).resize(function () {
        drawChart();
    });
}
