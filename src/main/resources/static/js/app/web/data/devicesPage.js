$(function () {
    var $devicesTableForm = $(".devices-table-form");

    var totalChange = function() {
        var totalArr = totalSlider.bootstrapSlider('getValue');
        $('#totalValue').val(totalArr[0] + '~' + totalArr[1]);
    };

    var totalSlider = $("#totalSlider").bootstrapSlider().on('change',totalChange);


    var priceChange = function() {
        var priceArr = priceSlider.bootstrapSlider('getValue');
        $('#priceValue').val(priceArr[0] + '~' + priceArr[1]);
    };

    var priceSlider = $("#priceSlider").bootstrapSlider().on('change',priceChange);


    var areaChange = function() {
        var areaArr = areaSlider.bootstrapSlider('getValue');
        $('#areaValue').val(areaArr[0] + '~' + areaArr[1]);
    };

    var areaSlider = $("#areaSlider").bootstrapSlider().on('change',areaChange);


    var settings = {
        url: ctx + "data/devices",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                total: $devicesTableForm.find("#totalValue").val().trim(),
                price: $devicesTableForm.find("#priceValue").val().trim(),
                area: $devicesTableForm.find("#areaValue").val().trim(),
                shi: $devicesTableForm.find("input[name='shi']").val().trim(),
                wei: $devicesTableForm.find("input[name='wei']").val().trim(),
                item: $devicesTableForm.find("input[name='item']").val().trim(),
                flat: $devicesTableForm.find("input[name='flat']").val().trim(),
                city_name: $devicesTableForm.find("input[name='city_name']").val().trim()
            };
        },

        columns: [
            {
                field: 'device_id',
                title: '设备Id',
                width: 150
            }, {
                field: 'total',
                title: '总价'
            }, {
                field: 'price',
                title: '单价'
            }, {
                field: 'shi',
                title: '室'
            }, {
                field: 'ting',
                title: '厅'
            }, {
                field: 'wei',
                title: '卫生间'
            },{
                field: 'area',
                title: '面积'
            }, {
                field: 'item',
                title: '小区名'
            }
        ]
    };
    $MB.initTable('devicesTable', settings);
});

function search() {
    $MB.refreshTable('devicesTable');
}

function refresh() {
    $(".devices-table-form")[0].reset();
    search();
}


function deleteDicts() {
    var selected = $("#dictTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的字典！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].dictId;
        if (i !== (selected_length - 1)) ids += ",";
    }
    $MB.confirm({
        text: "确定删除选中的字典？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post(ctx + 'dict/delete', {"ids": ids}, function (r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function exportDictExcel() {
    $.post(ctx + "dict/excel", $(".dict-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportDictCsv() {
    $.post(ctx + "dict/csv", $(".dict-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}