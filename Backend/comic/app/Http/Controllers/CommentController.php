<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Comment;
use App\Comic;
use App\User;
use App\Http\Requests\CommentCreateRequest;
use App\Http\Requests\CommentEditRequest;

class CommentController extends Controller
{
    public function index()
    {
        $Comments=Comment::all();
        //dd($Comments->find(1)->comics()->first()->comics_name);
        return view('admin.comment.index',compact('Comments'));
    }
    public function create()
    {
        $Comics=Comic::all();
        $Users=User::all();
        return view('admin.comment.create',compact('Comics','Users'));
    }

    public function store(CommentCreateRequest $request)
    {
        $user_id=User::where('name',$request->user_name)->first()->id;
        $comics_id=Comic::where('comics_name',$request->comics_name)->first()->id;
        //dd($comics_id);
        Comment::create([
            'content'=>$request->content,
            'comment_date'  =>  $request->comment_date,
            'user_id'   =>  $user_id,
            'comics_id' =>  $comics_id
        ]);
        $message="Thêm bình luận thành công";
        return redirect()->route('comment.index')->with('alert', $message);
    }
    public function edit($id)
    {
        $Comics=Comic::all();
        $Users=User::all();
        $Comment=Comment::find($id);
        return view('admin.comment.edit',compact('Comics','Users','Comment'));
    }
    public function update($id,CommentEditRequest $request)
    {
        $user_id=User::where('name',$request->user_name)->first()->id;
        $comics_id=Comic::where('comics_name',$request->comics_name)->first()->id;

        Comment::find($id)->update([
            'content'=>$request->content,
            'comment_date'  =>  $request->comment_date,
            'user_id'   =>  $user_id,
            'comics_id' =>  $comics_id
        ]);
        $message="Cập nhập bình luận thành công";
        return redirect()->route('comment.index')->with('alert', $message);
    }

    public function delete($id)
    {
        try{
            Comment::find($id)->delete();
             return response()->json([
                'code'=>200,
                'message'=>'success'
            ],200);
        }catch(\Exception $exception){
            // Log::error('message'.$exception->getMessage().'Line:'.$exception->getFile());

            return response()->json([
                'code'=>500,
                'message'=>'fail'
            ],500);
        }
    }
    
}
