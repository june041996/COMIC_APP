<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Comic;
use App\Chapter;
use App\ComicsImage;
use App\Traits\StorageImageTrait;
use Storage;
use File;

class ImageComicsController extends Controller
{
    use StorageImageTrait;

    public function index($id,$chapter_id)
    {   
        $Comics=Comic::find($id);
        $Chapter=Chapter::find($chapter_id);
        $Image=$Chapter->image()->get();
        //dd($Image);
        return view('admin.image.index',compact('Chapter','Image','Comics'));
    }
    public function create($id,$chapter_id)
    {
        $Comics=Comic::find($id);
        $Chapter=Chapter::find($chapter_id);
        $Image=$Chapter->image()->get();
        return view('admin.image.create',compact('Comics','Chapter','Image'));
    }
    public function store($id,$chapter_id,Request $request)
    {
        //dd($request->hasFile('image_name'));
        if($request->hasFile('image_name')){
            
            foreach($request->image_name as $fileItem){
                
                $dataUploadImage=$this->storageTraitUploadMutiple($fileItem,'images');
                //dd($dataUploadImage);
                ComicsImage::create([
                    'image_name'=>$dataUploadImage['file_name'],
                    'image_path'=>$dataUploadImage['file_path'],
                    'chapter_id'=>$chapter_id
                ]);
            }
        }
        return redirect()->route('image.index',['id'=>$id,'chapter_id'=>$chapter_id])->with('alert', "Thêm ảnh truyện thành công");
        
    }

    public function edit($id,$chapter_id,$image_id)
    {
        $Comics=Comic::find($id);
        $Chapter=Chapter::find($chapter_id);
        $Image=ComicsImage::find($image_id);
        return view('admin.image.edit',compact('Image','Comics','Chapter'));
    }

    public function update($id,$chapter_id,$image_id,Request $request)
    {
        if($request->hasFile('image_name')){
            //dd(storage_path().ComicsImage::find($image_id)->image_path);
            File::delete(public_path(ComicsImage::find($image_id)->image_path));
            
                
                $dataUploadImage=$this->storageTraitUploadMutiple($request->image_name,'images');
                //dd($dataUploadImage);
                ComicsImage::find($image_id)->update([
                    'image_name'=>$dataUploadImage['file_name'],
                    'image_path'=>$dataUploadImage['file_path']
                ]);
            
        }
        //dd($id);
        return redirect()->route('image.index',['id'=>$id,'chapter_id'=>$chapter_id])->with('alert', "Cập nhập ảnh truyện thành công");

    }
    public function delete($id,$chapter_id,$image_id)
    {
       
        try{
            File::delete(public_path(ComicsImage::find($image_id)->image_path));
            ComicsImage::find($image_id)->delete();
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
