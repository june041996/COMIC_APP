@extends('layouts.admin')

@section('title')
    <title>CATEGORY</title>
@endsection

@section('css')
<link href="{{ asset('vendors/select2/select2.min.css') }}" rel="stylesheet" />
<link href="{{ asset('admins/css/image.css') }}" rel="stylesheet" />    
<link href="{{ asset('admins/css/select2.css') }}" rel="stylesheet" />  
<link href="{{ asset('admins/css/alert.css') }}" rel="stylesheet" />     
@endsection

@section('content')
<div class="content-wrapper">
<form class="" action="{{ route('comics.store') }}" method="POST" enctype="multipart/form-data">
    @csrf
    <div class="content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <a href="{{ route('comics.index') }}" class="btn btn-success">Truyện</a>
                </div>
                <div class="col-md-6 mt-3">
                    
                    
                        <div class="form-group">
                            <label >Tên truyện</label>
                            <input class="form-control @error('comics_name') is-invalid @enderror" type="text" name="comics_name" id="comics_name" value="{{old('comics_name')}}">
                            @error('comics_name')
							    <div class="alert alert-danger alert-style">{{ $message }}</div>
							@enderror
                        </div>
                        <div class="form-group">
                            <label >Tên tác giả</label>
                            <input class="form-control @error('author') is-invalid @enderror" type="text" name="author" id="author" value="{{old('author')}}">
                            @error('author')
							    <div class="alert alert-danger alert-style">{{ $message }}</div>
							@enderror
                        </div>
                        <div class="form-group">
                            <label >Mô tả truyện</label>
                            <input class="form-control @error('description') is-invalid @enderror" type="text" name="description" id="description" value="{{old('description')}}">
                            @error('description')
							    <div class="alert alert-danger alert-style">{{ $message }}</div>
							@enderror
                        </div>
                        <div >
                            <label for="">Chọn thể loại</label>
                            
                            <select name="tags[]" id="tags[]" class="form-control tag_select_choose @error('tags') is-invalid @enderror" multiple="multiple">
                                {{-- @foreach($Comics->category as $tag)
									<option value="{{$tag->category_name}}" selected="0">{{$tag->category_name}}</option>
								@endforeach	
                                {!!$htmlSlelect!!} --}}
                                {!! $htmlSlelect !!}
                                

                            </select>
                            @error('tags')
							    <div class="alert alert-danger alert-style">{{ $message }}</div>
							@enderror
                        </div>
                        <div class="form-group">
							<label for="">Ảnh đại diện</label>
                            <div class="preview-img-container">
                                <img src="" id="preview-img" width="200px" />
                                </div>
							<input type="file" class="form-control-file @error('comics_image') is-invalid @enderror" id="comics_image" name="comics_image" placeholder="">
                            @error('comics_image')
							    <div class="alert alert-danger alert-style">{{ $message }}</div>
							@enderror
						</div>
						<div class="form-group">
                      
                        <button class="btn btn-success" name="create">Tạo</button>
                   
                </div>
                
            </div>
        </div>    
    </div>
</form>
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