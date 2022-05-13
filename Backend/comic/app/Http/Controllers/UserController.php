<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\User;
use App\Http\Requests\UserCreateRequest;
use App\Http\Requests\UserEditRequest;
use Storage;
use File;
use App\Traits\StorageImageTrait;
class UserController extends Controller
{
    use StorageImageTrait;

    public function index()
    {
        $Users=User::all();
        return view('admin.user.index',compact('Users'));
    }
    public function create()
    {
        return view('admin.user.create');
    }
    public function store(UserCreateRequest $request)
    {
        User::create([
            'name' => $request->name,
            'email' => $request->email,
            'password' => bcrypt($request->password)
        ]);

        return redirect()->route('user.index')->with('alert', "Thêm tài khoản thành công");
    }
    public function edit($id)
    {
        $Users=User::find($id);
        return view('admin.user.edit',compact('Users'));    
    }
    public function update($id,UserEditRequest $request)
    {
        
        if($request->hasFile('image_name')){
            //dd('a');
            File::delete(public_path(User::find($id)->image_path));
        
            
            $dataUploadImage=$this->storageTraitUploadMutiple($request->image_name,'users');
            //dd($dataUploadImage);
            User::find($id)->update([
                'image_name'=>$dataUploadImage['file_name'],
                'image_path'=>$dataUploadImage['file_path']
            ]);
        }
        
        //$this->comics->where('id','=',$id)->update($dataCreate);
        //dd($request->address);
        User::find($id)->update([
            
            'email' => $request->email,
            'password' => bcrypt($request->password),
            'gender' => $request->gender,
            'birthday' => $request->birthday,
            'phone_number' => $request->phone_number,
            'address' => $request->address


        ]);

        return redirect()->route('user.index')->with('alert', "Cập nhập tài khoản thành công");
    }
    public function delete($id)
    {
        try{
            User::find($id)->delete();
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
