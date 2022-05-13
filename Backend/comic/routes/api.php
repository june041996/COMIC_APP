<?php

use Illuminate\Http\Request;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});

Route::get('layTruyen', 'Api\TestController@index');
Route::get('layChap/{id}', 'Api\TestController@chapter');
Route::get('layAnh/{id}', 'Api\TestController@image');
Route::get('layTheLoai', 'Api\TestController@category');
Route::get('layTheLoaiTheoTruyen/{id}', 'Api\TestController@comics_category');
Route::get('theodoi/{name}', 'Api\TestController@follow');
Route::post('login', 'Api\TestController@login');
Route::post('register', 'Api\TestController@register');
Route::post('changePass', 'Api\TestController@changePass');

Route::post('createFollow', 'Api\TestController@createFollow');
Route::post('getFollow', 'Api\TestController@getFollow');
Route::post('deleteFollow', 'Api\TestController@deleteFollow');

Route::get('getComment/{id}', 'Api\TestController@getComment');
Route::get('deleteComment/{id}', 'Api\TestController@deleteComment');
Route::post('postComment', 'Api\TestController@postComment');

Route::get('getInforUser/{name}', 'Api\TestController@getInforUser');
Route::post('changeInforUser', 'Api\TestController@changeInforUser');
Route::post('countView', 'Api\TestController@countView');











