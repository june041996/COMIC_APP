@extends('layouts.admin')

@section('title')
    <title>Truyện</title>
@endsection

@section('css')
<link href="{{ asset('vendors/select2/select2.min.css') }}" rel="stylesheet" />
<link href="{{ asset('admins/css/image.css') }}" rel="stylesheet" />    
<link href="{{ asset('admins/css/select2.css') }}" rel="stylesheet" />  
<link href="{{ asset('admins/css/alert.css') }}" rel="stylesheet" />    

@endsection

@section('content')
<div class="content-wrapper">
    <div class="content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12 mb-2">
                    <a href="{{ route('comics.index') }}" class="btn btn-default ">Danh sách truyện/</a>
                </div>
                <div class="col-md-6">
                    <form action="{{ route('comics.update',['id'=>$Comics->id]) }}" method="POST" enctype="multipart/form-data">
                        @csrf
                        <div class="form-group">
                            <label >Tên truyện</label>
                            <input class="form-control @error('comics_name') is-invalid @enderror" type="text" name="comics_name" value="{{ $Comics->comics_name }}">
                            @error('comics_name')
							    <div class="alert alert-danger alert-style">{{ $message }}</div>
							@enderror
                        </div>
                        <div class="form-group">
                            <label >Tên tác giả</label>
                            <input class="form-control @error('author') is-invalid @enderror" type="text" name="author" value="{{ $Comics->author }}">
                            @error('author')
							    <div class="alert alert-danger alert-style">{{ $message }}</div>
							@enderror
                        </div>
                        <div class="form-group">
                            <label >Mô tả</label>
                            <textarea name="description" id="description"  rows="5" class="form-control @error('description') is-invalid @enderror">{{ $Comics->description }}</textarea>
                            
                            @error('description')
							    <div class="alert alert-danger alert-style">{{ $message }}</div>
							@enderror
                        </div>
                        <div >
                            <label for="">Thể loại</label>
                            <select name="tags[]" class="form-control tag_select_choose js-example-disabled " multiple="multiple">
                                @foreach($category as $tag)
									<option value="{{$tag->category_name}}" selected>{{$tag->category_name}}</option>
								@endforeach	
                            </select>
                            
                        </div>
                        <div >
                            <label for="">Chọn thể loại</label>
                            <select name="tags[]" class="form-control tag_select_choose @error('tags') is-invalid @enderror" multiple="multiple">
         
                                {!! $htmlSlelect !!}
                            </select>
                            @error('tags')
							    <div class="alert alert-danger alert-style">{{ $message }}</div>
							@enderror
                        </div>
                        <div class="form-group">
                            <label for="">Ảnh cũ</label><br>
                            <label for="">{{ $Comics->image_name }}</label>
                            <img class="form-control-file Image_150"  src="{{ $Comics->comics_image_path }}" >
                        </div>
                       
                        <div class="form-group">
							<label for="">Ảnh</label>
                            <div class="preview-img-container">
                                <img src="" id="preview-img" width="200px" />
                                </div>
							<input type="file" class="form-control-file @error('comics_image') is-invalid @enderror" id="comics_image" name="comics_image" placeholder="">
						</div>
                        @error('comics_image')
							    <div class="alert alert-danger alert-style">{{ $message }}</div>
							@enderror
						<div class="form-group">
                      
                        <button class="btn btn-success" name="create">Sửa</button>
                    </form>
                </div>
                
            </div>
        </div>    
    </div>   
</div>   
@endsection
@section('js')
<script>
    // Hiển thị ảnh preview (xem trước) khi người dùng chọn Ảnh
    const reader = new FileReader();
    const fileInput = document.getElementById("comics_image");
    const img = document.getElementById("preview-img");
    reader.onload = e => {
      img.src = e.target.result;
    }
    fileInput.addEventListener('change', e => {
      const f = e.target.files[0];
      reader.readAsDataURL(f);
    })
  </script>
<script src="{{ asset('vendors/select2/select2.min.js') }}"></script>
<script src="{{ asset('admins/js/select2.js') }}"></script>
@endsection