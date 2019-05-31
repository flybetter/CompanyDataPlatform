var theme_color = $MB.getThemeColor(theme);

$(document).ready(function () {
    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });

    var newHouseListData ;

    $.ajaxSetup({async:false});

    $.getJSON(ctx + 'data/newHouseList', function (data) {
        newHouseListData = JSON.parse(data.msg)
    })

    var secondHouseListData;

    $.getJSON(ctx + 'data/secondHouseList', function (data) {
        secondHouseListData = JSON.parse(data.msg)
    })

    var series = [{
        name: '新房',
        data: newHouseListData
    }];

    var chart = {
        type: 'spline'
    };
    var title = {
        text: 'Snow depth at Vikjafjellet, Norway'
    };
    var subtitle = {
        text: 'Irregular time data in Highcharts JS'
    };
    var xAxis = {
        type: 'datetime',
        dateTimeLabelFormats: { // don't display the dummy year
            month: '%e. %b',
            year: '%b'
        },
        title: {
            text: 'Date'
        }
    };
    var yAxis = {
        title: {
            text: 'Snow depth (m)'
        },
        min: 0
    };
    var tooltip = {
        headerFormat: '<b>{series.name}</b><br>',
        pointFormat: '{point.x:%e. %b}: {point.y:.2f} m'
    };
    var plotOptions = {
        spline: {
            marker: {
                enabled: true
            }
        }
    };

    var json = {};
    json.chart = chart;
    json.title = title;
    json.subtitle = subtitle;
    json.tooltip = tooltip;
    json.xAxis = xAxis;
    json.yAxis = yAxis;
    json.series = series;
    json.plotOptions = plotOptions;
    $('#container').highcharts(json);

    var series_2 = [{
        name: '二手房',
        data: secondHouseListData
    }];

    var json2 = {};
    json2.chart = chart;
    json2.title = title;
    json2.subtitle = subtitle;
    json2.tooltip = tooltip;
    json2.xAxis = xAxis;
    json2.yAxis = yAxis;
    json2.series = series;
    json2.plotOptions = plotOptions;
    json2.series=series_2
    $('#keysChart').highcharts(json2);


});