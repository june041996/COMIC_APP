<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Comic;
use App\Category;
use App\Comics_Category;
use App\Follow;
use App\Comment;


use Storage;
use App\Traits\StorageImageTrait;
use Illuminate\Support\Facades\DB;
use App\Http\Requests\ComicsCreateRequest;
use App\Http\Requests\ComicsEditRequest;
use File;


class ComicsController extends Controller
{
    use StorageImageTrait;
    private $comics;
    private $category;
    //construct
    public function __construct(Comic $comics,Category $category){
        $this->comics=$comics;
        $this->category=$category;
    }
    //index
    public function index(){
        $Comics=$this->comics->get();

       //dd($Comics);
        return view('admin.comics.index',compact('Comics'));
    }
   
    //create
    public function create(){
        $Comics=$this->comics;

        $dataCategory =$this->category->all();
        $htmlSlelect = '';
        foreach($dataCategory as $value){
			$htmlSlelect .= "<option value='".$value['category_name']."'>" . $value['category_name'] . "</option>";
		}
        //dd($htmlSlelect);
        return view('admin.comics.create',compact('htmlSlelect','Comics'));
    }

    public function store(Request $request)         
    {   
        //dd($request->comics_image);
        $Comics=Comic::all();
        try {
            
            DB::beginTransaction();
            $dataCreate=[
                'comics_name'=>$request->comics_name,
                'author'=>$request->author,
                'description'=>$request->description
            ];
            //dd($request->comics_image->getClientOriginalName());
    
            $dataUploadImage=$this->storageTraitUpload($request->comics_image,'comics');
           
            //dd($dataUploadImage);
            if(!empty($dataUploadImage)){
                $dataCreate['comics_image']=$dataUploadImage['file_name'];
                $dataCreate['comics_image_path']=$dataUploadImage['file_path'];
            }
            //dd($dataCreate);
            $comics=$this->comics->create($dataCreate);
    
            //insert tag
            
            if(!empty($request->tags)){
                foreach($request->tags as $tagItem){
                $tagInstance=$this->category->firstOrCreate(['category_name'=>$tagItem]);
                //dd($tagInstance);
                if($tagInstance->id==null){
                    $tagIds[]=$tagInstance->id;
                }else{
                    $tagIds[]=$tagInstance->id;
                }
                
                }
                $comics->category()->attach($tagIds);
            }
            DB::commit();
        } catch (\Exception $e) {
            DB::rollBack();
        }
        return redirect()->route('comics.index')->with('alert', "Thêm truyện tranh thành công");
    }

    public function edit($id)
    {    
        $Comics=$this->comics->where('id','=',$id)->first();
        //DB::enableQueryLog();
        // $category=DB::table('Categories')->leftJoin('comics__categories', 'Categories.category_id', '=', 'comics__categories.category_id')
        // ->leftJoin('Comics', 'comics__categories.comics_id', '=', 'Comics.id')->where('Comics.id','=',$id)->get();;
        $category=Comic::find($id)->category()->get();
        
       // dd(DB::getQueryLog());
       // dd($Comicss);
        
        $dataCategory =$this->category->all();
        $htmlSlelect = '';
        foreach($dataCategory as $value){
			$htmlSlelect .= "<option value='".$value['category_name']."'>" . $value['category_name'] . "</option>";
		}
        return view('admin.comics.edit',compact('htmlSlelect','category','Comics'));
    }

    public function update(ComicsEditRequest $request,$id)         
    {   
        try {
            DB::beginTransaction();
            $dataCreate=[
                'comics_name'=>$request->comics_name,
                'author'=>$request->author,
                'description'=>$request->description
            ];
            
            if($request->hasFile('comics_image')){
                File::delete(public_path(Comic::find($id)->comics_image_path));
            
                
                $dataUploadImage=$this->storageTraitUploadMutiple($request->comics_image,'comics');
                //dd($dataUploadImage);
                Comic::find($id)->update([
                    'comics_image'=>$dataUploadImage['file_name'],
                    'comics_image_path'=>$dataUploadImage['file_path']
                ]);
            }
            
            $this->comics->where('id','=',$id)->update($dataCreate);
            $comics=$this->comics->where('id','=',$id)->first();
            //dd($comics);
            //insert tag
            
            if(!empty($request->tags)){
                foreach($request->tags as $tagItem){
                $tagInstance=$this->category->firstOrCreate(['category_name'=>$tagItem]);
                
                if($tagInstance->id==null){
                    $tagIds[]=$tagInstance->id;
                }else{
                    $tagIds[]=$tagInstance->id;
                }
                
                }
                //dd($tagIds);
                $comics->category()->sync($tagIds);
            }
            DB::commit();
        } catch (\Exception $e) {
            DB::rollBack();
        }
        return redirect()->route('comics.index')->with('alert', "Cập nhập truyện tranh thành công");
    }
    public function delete($id)
    {   
        

        try{
            $Comics=Comic::find($id);
            $Chapter=$Comics->chapter()->get();
            //xóa thể loại
            Comics_Category::where('comics_id',$id)->delete();
            // Xóa chapter và ảnh
            foreach($Chapter as $ChapterItem){
                //dd($ChapterItem->id);
                
                $Image=$ChapterItem->image()->get();
                //list image comics
                foreach ($Image as $ImageItem ) {
                    //dd($ImageItem->id);
                    File::delete(public_path($ImageItem->image_path));
                    $ImageItem->delete();
                }
                $ChapterItem->delete();
            }
            //xóa file ảnh
            File::delete(public_path($Comics->comics_image_path));
            //xóa follow
            Follow::where('comics_id',$id)->delete();
            //xóa comment
            Comment::where('comics_id',$id)->delete();
            //xóa truyện
            $Comics->delete();
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
