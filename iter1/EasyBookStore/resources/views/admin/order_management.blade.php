@extends('layouts.admin')

@section('content')
    <div class="container" align="center">
        <h2>管理员界面</h2>
        <p>订单 数据管理</p>
    </div>
    
    <div class="container" align="center">
        <table id="dg" title="订单信息" class="easyui-datagrid" style="width:700px;height:370px"
                url="order_management/get_order" method="get"
                toolbar="#toolbar" pagination="true" idField="order_id"
                rownumbers="true" fitColumns="true" singleSelect="true">
            <thead>
                <tr>
                    <th field="order_id" width="50" editor="{type:'validatebox',options:{required:true}}">order_id</th>
                    <th field="book_id" width="50" editor="{type:'validatebox',options:{required:true}}">book_id</th>
                    <th field="book_name" width="120" editor="{type:'validatebox',options:{required:true}}">书名</th>
                    <th field="user_id" width="50" editor="{type:'validatebox',options:{required:true}}">user_id</th>  
                    <th field="user_name" width="50" editor="{type:'validatebox',options:{required:true}}">用户名</th>                 
                    <th field="quantity" width="50" editor="{type:'validatebox',options:{required:true}}">购买数量</th>
                    <th field="status" width="50" data-options="formatter:showOrderStatus" 
                        editor="{type:'validatebox',options:{required:true}}">订单状态</th>
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
          <label>order_id:</label>
          <input name="order_id" class="easyui-textbox" required="true">
        </div>
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
        var url;

        function createOrder(){
          $('#dlg').dialog('open').dialog('center').dialog('setTitle','新建书籍');
          $('#fm').form('clear');
          url = 'order_management';
        }

        function editOrder(){
          var row = $('#dg').datagrid('getSelected');
          console.log(row);
          if (row){
            $('#dlg').dialog('open').dialog('center').dialog('setTitle','编辑书籍');
            $('#fm').form('load',row);
            url = 'order_management/' + row.order_id;
          }
        }

        function saveOrder(){
          $('#fm').form('submit',{
            url: url,
            onSubmit: function(){
              return $(this).form('validate');
            },
            success: function(result){
              if (result.errorMsg){
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

        function removeOrder(){
          var row = $('#dg').datagrid('getSelected');
          if (row){
            $.messager.confirm('确认','真的要删除吗？',function(r){
              if (r){
                $.post('order_management/'+row.book_id, {_method:"delete"}, function(result){
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
          if (val == 1) {
            return "已处理";
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
@stop