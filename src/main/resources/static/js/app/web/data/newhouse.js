$(function () {
    var $newhouseTableForm = $(".newhouse-table-form");
    var settings = {
        url: ctx + "data/newHouseDetail",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                startDate: $newhouseTableForm.find("input[name='startDate']").val().trim(),
                endDate: $newhouseTableForm.find("input[name='endDate']").val().trim(),
                deviceId: $newhouseTableForm.find("input[name='deviceId']").val().trim()
            };
        },
        columns: [
            {
                field: 'deviceId',
                title: '设备Id',
                width: 150
            }, {
                field: 'city_name',
                title: '城市'
            }, {
                field: 'prj_itemname',
                title: '小区名称'
            }, {
                field: 'price_show',
                title: '单价'
            }, {
                field: 'pic_hx_totalprice',
                title: '总价'
            },{
                field: 'pic_area',
                title: '面积'
            }
        ]
    };
    $MB.initTable('newhouseTable', settings);
});

function search() {
    $MB.refreshTable('newhouseTable');
}

function refresh() {
    $(".newhouse-table-form")[0].reset();
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