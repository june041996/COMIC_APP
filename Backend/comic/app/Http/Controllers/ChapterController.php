<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Comic;
use App\Chapter;
use App\ComicsImage;
use App\Http\Requests\ChapterEditRequest;
use App\Http\Requests\ChapterCreateRequest;

class ChapterController extends Controller
{
    public function index($id)
    {   
        $Comics=Comic::find($id);
        $Chapters=$Comics->chapter()->get();
        //dd($Comics->id);
        return view('admin.chapter.index',compact('Chapters','Comics'));
    }
    
    public function create($id)
    {
        //dd($id);
        $Comics=Comic::find($id);
        return view('admin.chapter.create',compact('Comics'));
    }

    public function store($id,ChapterCreateRequest $request)
    {   
        //dd($request);
        $dataCreate=[
            'chapter_name'=>$request->chapter_name,
            'chapter_date'=>$request->chapter_date,
            'comics_id'=>$id
        ];
        //dd($dataCreate);
        Chapter::create($dataCreate);
        return redirect()->route('chapter.index',['id'=>$id])->with('alert', "Thêm chương truyện thành công");
    }
    public function edit($id,$chapter_id)
    {
        $Comics=Comic::find($id);
        $Chapter=Chapter::find($chapter_id);
        return view('admin.chapter.edit',compact('Chapter','Comics'));
    }

    public function update($id,$chapter_id,ChapterEditRequest $request)
    {
        $dataUpdate=[
            'chapter_name'=>$request->chapter_name,
            'chapter_date'=>$request->chapter_date
        ];

        //dd($dataUpdate);
        Chapter::find($chapter_id)->update($dataUpdate);
        return redirect()->route('chapter.index',['id'=>$id])->with('alert', "Cập nhập chương truyện thành công");
    }

    public function delete($id,$chapter_id)
    {
        try{
            Chapter::find($chapter_id)->delete();
            ComicsImage::where('chapter_id',$chapter_id)->delete();
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

