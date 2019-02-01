$(function () {
    //员工数据列表
    $('#role_datagrid').datagrid({
        url: '/getRoleList',
        toolbar: '#tb',
        columns: [[
            {field: 'rnum', title: '角色编号', width: 100, align: 'center'},
            {field: 'rname', title: '角色名称', width: 100, align: 'center'}
        ]],
        fit: true,
        fitColumns: true,
        pagination: true,
        rownumbers: true,
        striped: true,
        singleSelect: true
    });

    $('#dialog').dialog({
        title:'增加角色',
        width:550,
        height:600,
        closed:true,
        buttons:[{
            text:'保存',
            handler:function () {
                var url;
                var rid = $("[name='rid']").val();
                if(rid) {
                    url = 'updateRole';
                } else {
                    url = 'saveRole';
                }
                $('#role_form').form('submit', {
                    url:url,
                    onSubmit:function(param) {
                        var rows = $('#role_dg2').datagrid('getRows');
                        for(i = 0; i < rows.length; i++) {
                            param["permissions[" + i + "].pid"] = rows[i].pid;
                        }
                    },
                    success:function (data) {
                        data = $.parseJSON(data);
                        $.messager.alert('提示',data.msg);
                        if(data.success) {
                            $('#dialog').dialog('close');
                            $('#role_datagrid').datagrid('reload');
                        }
                    }
                })
            }
        },{
            text:'关闭',
            handler:function () {
                $('#dialog').dialog('close');
            }
        }]
    });

    $('#role_dg1').datagrid({
        title:'所有权限',
        height:400,
        url:'/getPermissionList',
        columns:[[
            {field:'pname',title:'权限名称',width:140, align:'center'}
        ]],
        fitColumns:true,
        singleSelect:true,
        onClickRow:function (rowIndex, rowData) {
            var rows = $('#role_dg2').datagrid('getRows');
            for(i = 0; i < rows.length; i++) {
                if(rows[i] == rowData) {
                    $('#role_dg2').datagrid('selectRow', i);
                    return;
                }
            }
            $('#role_dg2').datagrid('appendRow', rowData);
        }
    });

    $('#role_dg2').datagrid({
        title:'已选权限',
        height:400,
        columns:[[
            {field:'pname',title:'权限名称', width:140, align:'center'}
        ]],
        fitColumns:true,
        singleSelect:true,
        onClickRow:function (rowIndex, rowData) {
            $('#role_dg2').datagrid('deleteRow', rowIndex);
        }
    });

    $('#add').click(function () {
        $('#role_form').form('clear');
        $('#role_dg1').datagrid('clearSelections');
        $('#role_dg2').datagrid('loadData',{rows:[]});
        $('#dialog').dialog('open');
    });

    $('#edit').click(function () {
        var rowData = $('#role_datagrid').datagrid('getSelected');
        if(!rowData) {
            $.messager.alert('提示','未选中任何数据');
            return;
        }
        $('#dialog').dialog('open');
        $('#role_dg1').datagrid('clearSelections');
        //回显选中的数据
        $('#role_form').form('load', rowData);
        $('#role_dg2').datagrid('load', '/getPermissionsByRid?rid=' + rowData.rid);
    });
    
    $('#delete').click(function () {
        var rowData = $('#role_datagrid').datagrid('getSelected');
        if(!rowData) {
            $.messager.alert('提示','未选中任何数据');
            return;
        }
        $.messager.confirm('确认', '确定要删除该角色吗？', function (res) {
            if(res) {
                $.get('/deleteRole?rid='+rowData.rid, function (data) {
                    $.messager.alert('提示', data.msg);
                    $('#role_datagrid').datagrid('reload');
                })
            }
        })
    })
});