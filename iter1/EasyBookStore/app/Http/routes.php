<?php

/*
|--------------------------------------------------------------------------
| Routes File
|--------------------------------------------------------------------------
|
| Here is where you will register all of the routes in an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the controller to call when that URI is requested.
|
*/

Route::get('/', function () {
    return view('welcome');
});

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| This route group applies the "web" middleware group to every route
| it contains. The "web" middleware group is defined in your HTTP
| kernel and includes session state, CSRF protection, and more.
|
*/

Route::group(['middleware' => ['web']], function () {
    Route::auth();

    Route::get('admin/login', 'Admin\AuthController@getLogin');
    Route::post('admin/login', 'Admin\AuthController@postLogin');
    Route::get('admin/register', 'Admin\AuthController@getRegister');
    Route::post('admin/register', 'Admin\AuthController@postRegister');
    Route::get('admin/logout', 'Admin\AuthController@logout');
    Route::get('admin/home', 'AdminController@index');
    Route::get('admin/user_management', 'AdminController@manageUser');
    Route::get('admin', 'AdminController@index');

    //User CRUD
    Route::get('admin/user_management', 'UserController@index');
    Route::get('admin/user_management/get_user', 'UserController@getUser');
    Route::post('admin/user_management/{id}', 'UserController@updateUser');
    Route::post('admin/user_management', 'UserController@saveUser');
    Route::delete('admin/user_management/{id}', 'UserController@destroyUser');

    //Book CRUD
    Route::get('admin/book_management', 'BookController@index');
    Route::get('admin/book_management/get_book', 'BookController@getBook');
    Route::post('admin/book_management/{id}', 'BookController@updateBook');
    Route::post('admin/book_management', 'BookController@saveBook');
    Route::delete('admin/book_management/{id}', 'BookController@destroyBook');

    //Order CRUD
    Route::get('admin/order_management', 'OrderController@index');
    Route::get('admin/order_management/get_order', 'OrderController@getOrder');
    Route::post('admin/order_management/{id}', 'OrderController@updateOrder');
    Route::post('admin/order_management', 'OrderController@saveOrder');
    Route::delete('admin/order_management/{id}', 'OrderController@destroyOrder');
});

Route::group(['middleware' => 'web'], function () {
    Route::auth();

    Route::get('/home', 'HomeController@index');
});
