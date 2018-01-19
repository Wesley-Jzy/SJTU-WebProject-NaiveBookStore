<%--
  admin的订单管理页
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../layouts/header.jsp"%>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>订单管理</title>
</head>
<body>
<div class="container" align="center">
    <h2>管理员界面</h2>
    <p>订单 数据管理</p>
</div>

<div class="container" align="center">
    <table id="dg" title="订单信息" class="easyui-datagrid" style="width:800px;height:570px"
           url="order/list" method="get"
           toolbar="#toolbar" pagination="true" idField="id"
           rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
        <tr>
            <th field="id" width="50" editor="{type:'validatebox',options:{required:true}}">order_id</th>
            <th field="status" width="50" editor="{type:'validatebox',options:{required:true}}">订单状态</th>
            <th field="quantity" width="50" editor="{type:'validatebox',options:{required:true}}">购买数量</th>
            <th field="book_id" width="50" editor="{type:'validatebox',options:{required:true}}">book_id</th>
            <th field="user_id" width="50" editor="{type:'validatebox',options:{required:true}}">user_id</th>
            <!--
            <th field="book_name" width="120" editor="{type:'validatebox',options:{required:true}}">书名</th>
            -->
            <!--
            <th field="user_name" width="50" editor="{type:'validatebox',options:{required:true}}">用户名</th>
            -->
        </tr>
        </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="createOrder()">建立新订单</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editOrder()">编辑该订单</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeOrder()">删除该订单</a>
    </div>
</div>

<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <div class="ftitle">Information</div>
    <form id="fm" method="post" novalidate>
        <div class="fitem">
            <label>book_id:</label>
            <input name="book_id" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>user_id:</label>
            <input name="user_id" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>购买数量:</label>
            <input name="quantity" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>订单状态:</label>
            <input name="status" class="easyui-textbox" required="true">
        </div>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveOrder()" style="width:90px">确定</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')"
       style="width:90px">取消</a>
</div>

<script type="text/javascript">
    var isEdit = false;
    var baseUrl = 'order/';

    function createOrder(){
        isEdit = false;
        $('#dlg').dialog('open').dialog('center').dialog('setTitle','新建');
        $('#fm').form('clear');
    }

    function editOrder(){
        isEdit = true;
        var row = $('#dg').datagrid('getSelected');
        console.log(row);
        if (row){
            $('#dlg').dialog('open').dialog('center').dialog('setTitle','编辑');
            $('#fm').form('load',row);
        }
    }

    function saveOrder(){
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
                        msg: result.errorMsg,

                    });
                } else {
                    $('#dlg').dialog('close');      // close the dialog
                    $('#dg').datagrid('reload');    // reload the user data
                }
            }
        });
    }

    function removeOrder(){
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

    function showOrderStatus(val,row) {
        if (val == 2) {
            return "已处理";
        }

        else if (vall == 1) {
            return "处理中";
        }

        else if (val == 0){
            return "待处理";
        }

        else {
            return "不明状态";
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
</body>
</html>

<%@ include file="../layouts/footer.jsp"%>