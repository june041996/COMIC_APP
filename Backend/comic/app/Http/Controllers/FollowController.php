<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Comic;
use App\User;
use App\Follow;
use App\Http\Requests\FollowRequest;

class FollowController extends Controller
{
    public function index()
    {
        $Follows=Follow::all();
        return view('admin.follow.index',compact('Follows'));
    }

    public function create()
    {
        $Comics=Comic::all();
        $Users=User::all();
        return view('admin.follow.create',compact('Comics','Users'));
    }

    public function store(FollowRequest $request) 
    {
        $comics_id=Comic::where('comics_name',$request->comics_name)->first()->id;
        $user_id=User::where('name',$request->user_name)->first()->id;
        $Follow=Follow::where('user_id',$user_id)->where('comics_id',$comics_id)->count();
        //dd($Follow);
        if($Follow==0){
            Follow::firstOrCreate([
                'follow_date'   =>  $request->follow_date,
                'user_id'   => $user_id,
                'comics_id' => $comics_id
            ]);
            $message="Thêm lượt theo dõi thành công";
        }else{
            $message="Lượt theo dõi đã tồn tại";
        }
        
        
  
        return redirect()->route('follow.index')->with('alert', $message);
    }
    public function edit($id)
    {
        $Follows=Follow::find($id);
        $Comics=Comic::all();
        $Users=User::all();
        return view('admin.follow.edit',compact('Comics','Users','Follows'));
    }
    public function update($id,FollowRequest $request)        
    {   
        $comics_id=Comic::where('comics_name',$request->comics_name)->first()->id;
        $user_id=User::where('name',$request->user_name)->first()->id;
        Follow::find($id)->update([
            'follow_date'   =>  $request->follow_date,
            'user_id'   => $user_id,
            'comics_id' => $comics_id
        ]);
        $message="Cập nhập lượt theo dõi thành công";
        return redirect()->route('follow.index')->with('alert', $message);
    }
    public function delete($id)
    {
        try{
            Follow::find($id)->delete();
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
