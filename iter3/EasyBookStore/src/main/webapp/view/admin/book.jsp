<%--
  admin的书籍管理页
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../layouts/header.jsp"%>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>书籍管理</title>
</head>
<body>
    <div class="container" align="center">
        <h2>管理员界面</h2>
        <p>书籍数据管理</p>
    </div>

    <div class="container" align="center">
        <table id="dg" title="书籍信息" class="easyui-datagrid" style="width:1100px;height:670px"
               url="book/list" method="get"
               toolbar="#toolbar" pagination="true" idField="id"
               rownumbers="true" fitColumns="true" singleSelect="true">
            <thead>
            <tr>
                <th field="id" width="50" editor="{type:'validatebox',options:{required:true}}">book_id</th>
                <th field="ISBN" width="50" editor="{type:'validatebox',options:{required:true}}">ISBN</th>
                <th field="name" width="80" editor="{type:'validatebox',options:{required:true}}">书名</th>
                <th field="category" width="60" editor="{type:'validatebox',options:{required:true}}">分类</th>
                <th field="author" width="60" editor="{type:'validatebox',options:{required:true}}">作者</th>
                <th field="publishingHouse" width="80" editor="{type:'validatebox',options:{required:true}}">出版社</th>
               <!--
                <th field="pic_url" width="100" data-options="formatter:showBookPic" editor="{type:'validatebox',
                 options:{required:true}}">图片</th>
               -->
                <th field="price" width="50" editor="{type:'validatebox',options:{required:true}}">价格</th>
                <th field="quantity" width="50" editor="{type:'validatebox',options:{required:true}}">剩余数量</th>
            </tr>
            </thead>
        </table>
        <div id="toolbar">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="createBook()">建立新书籍</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editBook()">编辑该书籍</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeBook()">删除该书籍</a>
        </div>
    </div>

    <div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
         closed="true" buttons="#dlg-buttons">
        <div class="ftitle">Information</div>
        <form id="fm" method="post" novalidate>
            <div class="fitem">
                <label>ISBN:</label>
                <input name="ISBN" class="easyui-textbox" required="true">
            </div>
            <div class="fitem">
                <label>书名:</label>
                <input name="name" class="easyui-textbox" required="true">
            </div>
            <div class="fitem">
                <label>分类:</label>
                <input name="category" class="easyui-textbox" required="true">
            </div>
            <div class="fitem">
                <label>作者:</label>
                <input name="author" class="easyui-textbox" required="true">
            </div>
            <div class="fitem">
                <label>出版社:</label>
                <input name="publishingHouse" class="easyui-textbox" required="true">
            </div>
            <!--
            <div class="fitem">
                <label>图片链接:</label>
                <input name="pic_url" class="easyui-textbox" required="true">
            </div>
            -->
            <div class="fitem">
                <label>价格:</label>
                <input name="price" class="easyui-textbox" required="true">
            </div>
            <div class="fitem">
                <label>剩余数量:</label>
                <input name="quantity" class="easyui-textbox" required="true">
            </div>
        </form>
    </div>

    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveBook()" style="width:90px">确定</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')"
           style="width:90px">取消</a>
    </div>
</body>
</html>

<script type="text/javascript">
    var isEdit = false;
    var baseUrl = 'book/';

    function createBook(){
        isEdit = false;
        $('#dlg').dialog('open').dialog('center').dialog('setTitle','新建书籍');
        $('#fm').form('clear');
    }

    function editBook(){
        isEdit = true;
        var row = $('#dg').datagrid('getSelected');
        console.log(row);
        if (row){
            $('#dlg').dialog('open').dialog('center').dialog('setTitle','编辑书籍');
            $('#fm').form('load',row);
        }
    }

    function saveBook(){
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

    function removeBook(){
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

    function showBookPic(val,row) {
        return "<img width=100 height=100% src="+"../"+val+"/>";
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
        height:120px
    }
</style>

<%@ include file="../layouts/footer.jsp"%>