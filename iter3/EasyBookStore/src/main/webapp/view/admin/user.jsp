<%--
  admin的用户管理页
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../layouts/header.jsp"%>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>用户管理</title>
</head>
<body>
    <div class="container" align="center">
        <h2>管理员界面</h2>
        <p>用户数据管理</p>
    </div>

    <div class="container" align="center">
        <table id="dg" title="用户信息" class="easyui-datagrid" style="width:1100px;height:670px"
               url="user/list" method="get"
               toolbar="#toolbar" pagination="true" idField="id"
               rownumbers="true" fitColumns="true" singleSelect="true">
            <thead>
            <tr>
                <th field="id" width="50" editor="{type:'validatebox',options:{required:true}}">user_id</th>
                <th field="username" width="50" editor="{type:'validatebox',options:{required:true}}">用户名</th>
                <th field="email" width="100" editor="{type:'validatebox',options:{required:true}}">email</th>
                <th field="password" width="100" editor="{type:'validatebox',options:{required:true}}">密码</th>
            </tr>
            </thead>
        </table>
        <div id="toolbar">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="createUser()">建立新用户</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑该用户</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeUser()">删除该用户</a>
        </div>
    </div>

    <div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
         closed="true" buttons="#dlg-buttons">
        <div class="ftitle">Information</div>
        <form id="fm" method="post" novalidate>
            <div class="fitem">
                <label>用户名:</label>
                <input name="username" class="easyui-textbox" required="true">
            </div>
            <div class="fitem">
                <label>email:</label>
                <input name="email" class="easyui-textbox" required="true">
            </div>
            <div class="fitem">
                <label>密码:</label>
                <input name="password" class="easyui-textbox" required="true">
            </div>
        </form>
    </div>

    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">确定</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')"
           style="width:90px">取消</a>
    </div>
</body>
</html>

<script type="text/javascript">
    var isEdit = false;
    var baseUrl = 'user/';

    function createUser(){
        isEdit = false;
        $('#dlg').dialog('open').dialog('center').dialog('setTitle','新建用户');
        $('#fm').form('clear');
    }

    function editUser(){
        isEdit = true;
        var row = $('#dg').datagrid('getSelected');
        if (row){
            $('#dlg').dialog('open').dialog('center').dialog('setTitle','编辑用户');
            $('#fm').form('load',row);
        }
    }

    function saveUser(){
        var arr = $('#fm').serializeArray();
        var data = {};
        if (isEdit) {
            var row = $('#dg').datagrid('getSelected');
            data['id'] = row.id;
        }
        arr.forEach(function (item) {
            data[item.name] = item.value;
        });

        $.ajax({
            type: "POST",
            url: baseUrl + 'save',
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(data),
            success: function (result) {
                if (result.errorMsg) {
                    $.messager.show({
                        title: 'Error',
                        msg: result.errorMsg
                    });
                } else {
                    $('#dlg').dialog('close');      // close the dialog
                    $('#dg').datagrid('reload');    // reload the user data
                }
            }
        });
    }

    function removeUser(){
        var row = $('#dg').datagrid('getSelected');
        if (row){
            $.messager.confirm('确认','真的要删除吗？',function(r){
                if (r){
                    $.post(baseUrl + 'remove', {id: row.id}, function(result){
                        if (result.success){
                            $('#dg').datagrid('reload');// reload the user data
                        } else {
                            $.messager.show({    // show error message
                                title: 'Error',
                                msg: result.errorMsg
                            });
                        }
                    },'json');
                }
            });
        }
    }
</script>

<style type="text/css">
    #fm {
        margin:0;
        padding:10px 30px;
    }
    .ftitle {
        font-size:14px;
        font-weight:bold;
        padding:5px 0;
        margin-bottom:10px;
        border-bottom:1px solid #ccc;
    }
    .fitem{
        margin-bottom:5px;
    }
    .fitem label{
        display:inline-block;
        width:80px;
    }
    .fitem input{
        width:160px;
    }

    .datagrid-row {
        height:40px
    }
</style>

<%@ include file="../layouts/footer.jsp"%>