<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/home', [
    'as' => 'home',
    'uses' => 'AdminController@home'
])->middleware('auth');
Route::get('/', [
    'as' => 'login',
    'uses' => 'AdminController@loginAdmin'
]);
Route::post('/login', [
    'as' => 'post-login',
    'uses' => 'AdminController@postLoginAdmin'
]);

Route::get('/logout', [
    'as' => 'logout',
    'uses' => 'AdminController@logout'
]);

Route::group(['prefix' => 'comics', 'middleware' => ['auth']],function(){

  
    //comics
    Route::prefix('comics')->group(function(){
        //index
        Route::get('/',[
            'as' => 'comics.index',
            'uses'=>'ComicsController@index'
        ]);
       
        //
        Route::get('/create', [
            'as'=>'comics.create',
            'uses'=>'ComicsController@create'
        ]);
        Route::post('/store', [
            'as'=>'comics.store',
            'uses'=>'ComicsController@store'

        ]);
        Route::get('/edit/{id}', [
            'as'=>'comics.edit',
            'uses'=>'ComicsController@edit'
        ]);
        Route::post('/update/{id}', [
            'as'=>'comics.update',
            'uses'=>'ComicsController@update'
        ]);
        Route::get('/delete/{id}', [
            'as'=>'comics.delete',
            'uses'=>'ComicsController@delete'
        ]);
//Chapter
            Route::get('/{id}/chapter/', [
                'as'=>'chapter.index',
                'uses'=>'ChapterController@index'
            ]);

            Route::get('/{id}/chapter/create', [
                'as'=>'chapter.create',
                'uses'=>'ChapterController@create'
            ]);

            Route::post('/{id}/chapter/store', [
                'as'=>'chapter.store',
                'uses'=>'ChapterController@store'
            ]);

            Route::get('/{id}/chapter/edit/{chapter_id}', [
                'as'=>'chapter.edit',
                'uses'=>'ChapterController@edit'
            ]);

            Route::post('/{id}/chapter/update/{chapter_id}', [
                'as'=>'chapter.update',
                'uses'=>'ChapterController@update'
            ]);

            Route::get('/{id}/chapter/delete/{chapter_id}', [
                'as'=>'chapter.delete',
                'uses'=>'ChapterController@delete'
            ]);
//Image
                Route::get('/{id}/chapter/{chapter_id}/image', [
                    'as'    =>  'image.index',
                    'uses'  =>  'ImageComicsController@index'
                ]);
                Route::get('/{id}/chapter/{chapter_id}/image/create', [
                    'as'    =>  'image.create',
                    'uses'  =>  'ImageComicsController@create'
                ]);
                Route::post('/{id}/chapter/{chapter_id}/image/store', [
                    'as'    =>  'image.store',
                    'uses'  =>  'ImageComicsController@store'
                ]);
                Route::get('/{id}/chapter/{chapter_id}/image/edit/{image_id}', [
                    'as'    =>  'image.edit',
                    'uses'  =>  'ImageComicsController@edit'
                ]);
                Route::post('/{id}/chapter/{chapter_id}/image/update/{image_id}', [
                    'as'    =>  'image.update',
                    'uses'  =>  'ImageComicsController@update'
                ]);
                Route::get('/{id}/chapter/{chapter_id}/image/delete/{image_id}', [
                    'as'    =>  'image.delete',
                    'uses'  =>  'ImageComicsController@delete'
                ]);
            
        
    });
    //category
    Route::prefix('category')->group(function(){
        //index
        Route::get('/',[
            'as' => 'category.index',
            'uses'=>'CategoryController@index'
        ]);
        Route::get('/create', [
            'as'=>'category.create',
            'uses'=>'CategoryController@create'
        ]);
        Route::post('/store',[
            'as' => 'category.store',
            'uses' => 'CategoryController@store'
        ]);
        Route::get('/edit{id}', [
            'as'=> 'category.edit',
            'uses'=> 'CategoryController@edit'
        ]);
        Route::post('/update/{id}',[
            'as'=>'category.update',
            'uses'=>'CategoryController@update'
        ]);
        Route::get('/delete/{id}', [
            'as'=>'category.delete',
            'uses'=>'CategoryController@delete'
        ]);

        

    });
//comment
    Route::prefix('comment')->group(function(){
        //index
        Route::get('/',[
            'as' => 'comment.index',
            'uses'=>'CommentController@index'
        ]);
        Route::get('/create', [
            'as'=>'comment.create',
            'uses'=>'CommentController@create'
        ]);
        Route::post('/store',[
            'as' => 'comment.store',
            'uses' => 'CommentController@store'
        ]);
        Route::get('/edit{id}', [
            'as'=> 'comment.edit',
            'uses'=> 'CommentController@edit'
        ]);
        Route::post('/update/{id}',[
            'as'=>'comment.update',
            'uses'=>'CommentController@update'
        ]);
        Route::get('/delete/{id}', [
            'as'=>'comment.delete',
            'uses'=>'CommentController@delete'
        ]);

        

    });
//follow
    Route::prefix('follow')->group(function(){
        //index
        Route::get('/',[
            'as' => 'follow.index',
            'uses'=>'FollowController@index'
        ]);
        Route::get('/create', [
            'as'=>'follow.create',
            'uses'=>'FollowController@create'
        ]);
        Route::post('/store',[
            'as' => 'follow.store',
            'uses' => 'FollowController@store'
        ]);
        Route::get('/edit{id}', [
            'as'=> 'follow.edit',
            'uses'=> 'FollowController@edit'
        ]);
        Route::post('/update/{id}',[
            'as'=>'follow.update',
            'uses'=>'FollowController@update'
        ]);
        Route::get('/delete/{id}', [
            'as'=>'follow.delete',
            'uses'=>'FollowController@delete'
        ]);

        

    });
//user
    Route::prefix('user')->group(function(){
        //index
        Route::get('/',[
            'as' => 'user.index',
            'uses'=>'UserController@index'
        ]);
        Route::get('/create', [
            'as'=>'user.create',
            'uses'=>'UserController@create'
        ]);
        Route::post('/store',[
            'as' => 'user.store',
            'uses' => 'UserController@store'
        ]);
        Route::get('/edit{id}', [
            'as'=> 'user.edit',
            'uses'=> 'UserController@edit'
        ]);
        Route::post('/update/{id}',[
            'as'=>'user.update',
            'uses'=>'UserController@update'
        ]);
        Route::get('/delete/{id}', [
            'as'=>'user.delete',
            'uses'=>'UserController@delete'
        ]);

        

    });

    
});

