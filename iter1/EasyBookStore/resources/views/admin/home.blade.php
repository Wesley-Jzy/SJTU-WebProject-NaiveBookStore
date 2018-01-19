@extends('layouts.admin')

@section('content')
<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="panel panel-default">
                <div class="panel-heading">管理界面</div>

                <div class="panel-body">
                    <p>管理员登陆成功！</p>
                </div>
                <div align="center">
                    <a class="lsit-group-item" href="{{ url('/admin/user_management') }}">
                        <h3>用户管理</h3>
                    </a>

                    <a class="lsit-group-item" href="{{ url('/admin/book_management') }}">
                        <h3>书籍管理</h3>
                    </a>

                    <a class="lsit-group-item" href="{{ url('/admin/order_management') }}">
                        <h3>订单管理</h3>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
@stop

