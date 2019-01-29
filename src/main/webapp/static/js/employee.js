$(function() {
    //员工数据列表
    $('#datagrid').datagrid({
        url:'/employeeList',
        toolbar:'#tb',
        columns:[[
            {field:'username',title:'姓名',width:100,align:'center'},
            {field:'registime',title:'入职时间',width:100,align:'center'},
            {field:'tel',title:'电话',width:100,align:'center'},
            {field:'email',title:'邮箱',width:100,align:'center'},

            {field:'department',title:'部门',width:100,align:'center',
                formatter:function (value,row,index) {
                    if(value.name) return value.name;
                }},

            {field:'state',title:'状态',width:100,align:'center',
                formatter:function (value,row,index) {
                    if(row.state) return '在职';
                    else return "<font style='color:red'>离职</font>";
                }},

            {field:'admin',title:'管理员',width:100,align:'center',
                formatter:function (value,row,index) {
                    if(row.admin)
                        return '是';
                    else
                        return '否';
                }}
        ]],
        fit:true,
        fitColumns:true,
        pagination:true,
        rownumbers:true,
        stripped:true,
    });
    //对话框
    $('#dialog').dialog({
        width:350,
        height:350,
        // closed:true,
        modal:true
    });
    //部门
    $('#department').combobox({
        url:'/departmentList',
        valueField:'id',
        textField:'name',
        width:160,
        panelHeight:'fit',
        editable:false,
        onLoadSuccess:function () {
            $('#department').each(function (i) {
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if(targetInput) {
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            })
        }
    });
    //监听add
    $('#add').click(function () {
       $('#dialog').dialog('open')
    })

});