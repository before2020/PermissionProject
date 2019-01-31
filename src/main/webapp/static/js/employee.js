$(function () {
    //员工数据列表
    $('#datagrid').datagrid({
        url: '/employeeList',
        toolbar: '#tb',
        columns: [[
            {field: 'username', title: '姓名', width: 100, align: 'center'},
            {field: 'registime', title: '入职时间', width: 100, align: 'center'},
            {field: 'tel', title: '电话', width: 100, align: 'center'},
            {field: 'email', title: '邮箱', width: 100, align: 'center'},
            {
                field: 'department', title: '部门', width: 100, align: 'center',
                formatter: function (value, row, index) {
                    if (value) return value.name;
                }
            }, {
                field: 'state', title: '状态', width: 100, align: 'center',
                formatter: function (value, row, index) {
                    if (row.state) return '在职';
                    else return "<font style='color:red'>离职</font>";
                }
            }, {
                field: 'admin', title: '管理员', width: 100, align: 'center',
                formatter: function (value, row, index) {
                    if (row.admin)
                        return '是';
                    else
                        return '否';
                }
            }
        ]],
        fit: true,
        fitColumns: true,
        pagination: true,
        rownumbers: true,
        striped: true,
        singleSelect: true,
        onClickRow: function (rowIndex, rowData) {
            if (!rowData.state) {
                $('#delete').linkbutton('disable');
            }
        }
    });
    //对话框
    $('#dialog').dialog({
        width: 350,
        height: 350,
        closed: true,
        modal: true,
        buttons: [
            {
                text: '保存',
                handler: function () {
                    var id = $("[name='id']").val();
                    var url = null;
                    if (id) {
                        url = '/updateEmployee';
                    } else {
                        url = '/saveEmployee';
                    }
                    $('#employeeForm').form('submit', {
                        url: url,
                        success: function (data) {
                            data = $.parseJSON(data);
                            $.messager.alert('提示', data.msg);
                            if (data.success) {
                                $('#dialog').dialog('close');
                                $('#datagrid').datagrid('reload');
                            }
                        }
                    })
                }
            }, {
                text: '关闭',
                handler: function () {
                    $('#dialog').dialog('close')
                }
            }
        ]
    });
    //部门
    $('#department').combobox({
        url: '/departmentList',
        valueField: 'id',
        textField: 'name',
        width: 160,
        panelHeight: 'fit',
        editable: false,
        onLoadSuccess: function () {
            $('#department').each(function (i) {
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if (targetInput) {
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            })
        }
    });
    //监听add
    $('#add').click(function () {
        $('#employeeForm').form('clear');
        $('#dialog').dialog('setTitle', '添加');
        $('#password').show();
        $("[name='password']").validatebox({required: true});
        $('#dialog').dialog('open')
    });
    //监听edit
    $('#edit').click(function () {
        //得到选中的条目
        var rowData = $('#datagrid').datagrid('getSelected');
        console.log(rowData);
        if (!rowData) {
            $.messager.alert('提示', "未选中任何数据");
            return;
        }
        $('#dialog').dialog('setTitle', '编辑');
        //edit时不准改密码
        $('#password').hide();
        $("[name='password']").validatebox({required: false});
        $('#dialog').dialog('open');

        rowData['department.id'] = rowData.department.id;
        rowData['state'] = rowData['state'] + '';
        rowData['admin'] = rowData['admin'] + '';
        $('#employeeForm').form('load', rowData);
    });
    //监听离职
    $('#delete').click(function () {
        var rowData = $('#datagrid').datagrid('getSelected');
        if (!rowData) {
            $.messager.alert("提示", "未选中任何数据");
            return;
        }
        if (!rowData.state) {
            return;
        }
        $.messager.confirm("确认", "确定要更改员工状态吗？", function (res) {
            if (res) {
                $.get('/updateState?id=' + rowData.id, function (data) {
                    //get会自动把data解析，不用下一条语句了
                    //data = $.parseJSON(data);
                    $.messager.alert(data.msg);
                    if (data.success) {
                        $('#datagrid').datagrid('reload');
                    } else {
                        $.messager.alert('提示', data.msg);
                    }
                })
            }
        })
    });
    //搜索
    $('#searchbtn').click(function () {
        var keyword = $("[name='searchbox']").val();
        $('#datagrid').datagrid('load', {
            keyword: keyword
        })
    });
    //刷新
    $('#reload').click(function () {
        //清空搜索栏
        $("[name='searchbox']").val('');
        $('#datagrid').datagrid('load', {})
    });
});

