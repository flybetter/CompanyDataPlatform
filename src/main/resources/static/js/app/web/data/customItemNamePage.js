$(function () {

    var $customItemNamePageTableForm = $(".customItemNamePage-form");

    $('input[name="startDate"]').daterangepicker({
        startDate: moment().subtract(6, 'days'),
        singleDatePicker: true,
        showDropdowns: true,
        locale: {
            format: 'YYYY-MM-DD'
        },


    });

    $('input[name="endDate"]').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
        locale: {
            format: 'YYYY-MM-DD'
        },
    });

    var settings = {
        url: ctx + "data/customItemName",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                startDate: $customItemNamePageTableForm.find("input[name='startDate']").val().trim(),
                endDate: $customItemNamePageTableForm.find("input[name='endDate']").val().trim(),
                itemName: $customItemNamePageTableForm.find("input[name='itemName']").val().trim(),
                city:$customItemNamePageTableForm.find("input[name='city']").val().trim()
            };
        },
        columns: [
            {
                field: 'device_id',
                title: '设备Id',
                width: '50%'
            }, {
                field: 'login_account',
                title: '手机号'
            }, {
                field: 'counting',
                title: '次数'
            }
        ]
    };
    $MB.initTable('customItemNamePageTable', settings);
});

function search() {
    $MB.refreshTable('customItemNamePageTable');
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