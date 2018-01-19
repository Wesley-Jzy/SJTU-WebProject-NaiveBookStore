<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Http\Requests;

use DB;

use Hash;

class UserController extends Controller
{

	public function __construct()
    {
        $this->middleware('auth:admin');
    }

    //list all users
    public function index()
    {     
        return view('admin/user_management');
    }
    
    //return users' info in json
    public function getUser(Request $request)
    {
        $page = $request->input('page', 1);
        $rows = $request->input('rows', 10);
        $result = array();
        $result['total'] = DB::table('users')->count();
        $users = DB::table('users')->skip($rows * ($page - 1))->take($rows)->get();
        $result['rows'] = $users;
        return json_encode($result);
    }

    /**
     * Store a newly created user
     */
    public function saveUser(Request $request)
    {
        DB::table('users')->insert(
            ['id'=>$request->input('id'),'name'=>$request->input('name'), 'password'=>bcrypt($request->input('password')), 'email'=>$request->input('email')]
        );
        
        return json_encode(array('success' => true));
    }

    /**
     * Update user
     */
    public function updateUser(Request $request, $id)
    {
        DB::table('users')->where('id', $id)
            ->update(
            	['id'=>$request->input('id'),'name'=>$request->input('name'), 'password'=>bcrypt($request->input('password')), 'email'=>$request->input('email')]
            );
            
        return json_encode(array('success' => true));
    }

    /**
     * Remove user
     */
    public function destroyUser($id)
    {
        DB::table('users')->where('id', '=', $id)->delete();
        return json_encode(array('success' => true));
    }
}
