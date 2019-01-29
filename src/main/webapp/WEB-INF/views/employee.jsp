<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/employee.js"></script>
</head>
<body>
<div id="tb">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" id="add">添加</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true">刷新</a>
</div>


<table id="datagrid"></table>

<div id="dialog">
    <table align="center" style="font-size:15px;margin-top:10px;border-spacing: 5px">
        <tr>
            <td>用户名</td>
            <td><input type="text" class="easyui-validatebox" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" class="easyui-validatebox" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>手机</td>
            <td><input type="text" class="easyui-validatebox" /></td>
        </tr>
        <tr>
            <td>邮箱</td>
            <td><input type="text" class="easyui-validatebox" data-options="validType:'email'" /></td>
        </tr>

        <tr>
            <td>入职时间</td>
            <td><input type="text" class="easyui-datebox" data-options="editable:false" /></td>
        </tr>
        <tr>
            <td>部门</td>
            <td><input type="text" id="department" placeholder="请选择部门"/></td>
        </tr>
        <tr>
            <td>状态</td>
            <td>
                <select class="easyui-combobox" data-options="width:160,panelHeight:'fit',editable:false">
                    <option value=true>在职</option>
                    <option value=false>离职</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>是否管理员</td>
            <td>
                <select class="easyui-combobox"
                        data-options="value:false,width:160,panelHeight:'fit',editable:false">
                    <option value=true>是</option>
                    <option value=false>否</option>
                </select>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
