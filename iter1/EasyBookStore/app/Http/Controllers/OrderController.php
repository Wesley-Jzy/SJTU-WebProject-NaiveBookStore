<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Http\Requests;

use DB;

class OrderController extends Controller
{
    public function __construct()
    {
        $this->middleware('auth:admin');
    }

    //list all users
    public function index()
    {     
        return view('admin/order_management');
    }
    
    //return users' info in json
    public function getOrder(Request $request)
    {
        $page = $request->input('page', 1);
        $rows = $request->input('rows', 10);
        $result = array();
        $result['total'] = DB::table('orders')->count();
        $orders = DB::table('orders')
        				->join('books', 'orders.book_id','=','books.book_id')
        				->join('users', 'orders.user_id','=','users.id')
        				->select('orders.*', 'books.name as book_name', 'users.name as user_name')
        				->skip($rows * ($page - 1))
        				->take($rows)
        				->get();
        $result['rows'] = $orders;
        return json_encode($result);
    }

    /**
     * Store a newly created user
     */
    public function saveOrder(Request $request)
    {
        DB::table('orders')->insert(
            ['order_id'=>$request->input('order_id'),
            'book_id'=>$request->input('book_id'), 
            'user_id'=>$request->input('user_id'), 
            'quantity'=>$request->input('quantity'),
            'status'=>$request->input('status')]
        );
        
        return json_encode(array('success' => true));
    }

    /**
     * Update user
     */
    public function updateOrder(Request $request, $id)
    {
        DB::table('orders')->where('order_id', $id)
            ->update(
            	['order_id'=>$request->input('order_id'),
            	'book_id'=>$request->input('book_id'), 
            	'user_id'=>$request->input('user_id'), 
            	'quantity'=>$request->input('quantity'),
            	'status'=>$request->input('status')]
            );
            
        return json_encode(array('success' => true));
    }

    /**
     * Remove user
     */
    public function destroyOrder($id)
    {
        DB::table('orders')->where('order_id', '=', $id)->delete();
        return json_encode(array('success' => true));
    }
}
