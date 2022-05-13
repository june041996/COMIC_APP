<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Category;
use App\Comics_Category;
use DB;
use App\Http\Requests\CategoryCreateRequest;
use App\Http\Requests\CategoryEditRequest;

class CategoryController extends Controller
{   
    private $category;
    public function __construct(Category $category)
    {
       $this->category=$category;
    }
    public function index()
    {   
        $Categories=Category::all();
        //dd($Categories);
        return view('admin.category.index',compact('Categories'));
    }

    public function create()
    {
        return view('admin.category.create');
    }

    public function store(CategoryCreateRequest $request)     
    {
        $this->category->firstOrCreate([
            'category_name'=>$request->category_name
        ]);
        return redirect()->route('category.index')->with('alert', "Thêm thể loại thành công");
    }

    public function edit($id)
    {
        
        //dd($truyen);
        //DB::enableQueryLog();
        $Category=Category::where('id','=',$id)->first();
        //dd(DB::getQueryLog());
        //dd($Category);
        
        return view('admin.category.edit',compact('Category'));
    }
    public function update($id, CategoryEditRequest $request)
    {
        
            $this->category->where('id','=',$id)->update([
                'category_name'=>$request->category_name
            ]);
            $message="Cập nhập thể loại thành công";
            return redirect()->route('category.index')->with('alert', $message);
      
        
        
    }
    public function delete($id)
    {
        
        try{
            $this->category->where('id','=',$id)->delete();
            Comics_Category::where('category_id',$id)->delete();
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
