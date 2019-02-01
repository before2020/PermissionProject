<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>角色管理</title>
    <%@ include file="/static/common/common.jsp" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/role.js"></script>
    <style>
        .panel-header {
            padding: 0;
        }

        #role_form .panel-title {
            height: 30px;
            line-height: 30px;
            color: black;
        }

    </style>
</head>

<body>
<div id="tb">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" id="add">添加</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" id="edit">编辑</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" id="delete">删除</a>
    <input name="searchbox" style="width:200px"/>
</div>

<table id="role_datagrid"></table>

<div id="dialog">
    <form id="role_form">
        <table style="font-size: 15px;margin: 0 auto;border-spacing: 15px;">
            <input type="hidden" name="rid">
            <tr>
                <td>角色编号：<input class="easyui-validatebox" type="text" name="rnum"></td>
                <td>角色名称：<input class="easyui-validatebox" type="text" name="rname"></td>
            </tr>
            <tr>
                <td> <div id="role_dg1"></div> </td>
                <td> <div id="role_dg2"></div> </td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>
