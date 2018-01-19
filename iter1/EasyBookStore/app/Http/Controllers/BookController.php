<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Http\Requests;

use DB;

class BookController extends Controller
{
    public function __construct()
    {
        $this->middleware('auth:admin');
    }

    //list all users
    public function index()
    {     
        return view('admin/book_management');
    }
    
    //return users' info in json
    public function getBook(Request $request)
    {
        $page = $request->input('page', 1);
        $rows = $request->input('rows', 10);
        $result = array();
        $result['total'] = DB::table('books')->count();
        $books = DB::table('books')->skip($rows * ($page - 1))->take($rows)->get();
        $result['rows'] = $books;
        return json_encode($result);
    }

    /**
     * Store a newly created user
     */
    public function saveBook(Request $request)
    {
        DB::table('books')->insert(
            ['book_id'=>$request->input('book_id'),
            'name'=>$request->input('name'), 
            'pic_url'=>$request->input('pic_url'), 
            'price'=>$request->input('price'), 
            'quantity'=>$request->input('quantity')]
        );
        
        return json_encode(array('success' => true));
    }

    /**
     * Update user
     */
    public function updateBook(Request $request, $id)
    {
        DB::table('books')->where('book_id', $id)
            ->update(
            	['book_id'=>$request->input('book_id'),
            	'name'=>$request->input('name'), 
            	'pic_url'=>$request->input('pic_url'), 
            	'price'=>$request->input('price'), 
            	'quantity'=>$request->input('quantity')]
            );
            
        return json_encode(array('success' => true));
    }

    /**
     * Remove user
     */
    public function destroyBook($id)
    {
        DB::table('books')->where('book_id', '=', $id)->delete();
        return json_encode(array('success' => true));
    }
}
