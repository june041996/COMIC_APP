@extends('layouts.admin')
@section('title')
    <title>Ảnh truyện</title>
@endsection
@section('content')
    <div class=" content-wrapper">
        <div class="content-header">
            <div class="container-fluid">
              <div class="row mb-2">
                
                <div class="col-sm-6">
                  <ol class="breadcrumb ">
                    <li class="breadcrumb-item"><a href="{{route('comics.index')}}">Danh sách truyện</a></li>
                    <li class="breadcrumb-item active"><a href="{{route('chapter.index',['id'=>$Comics->id,'chapter_id'=>$Chapter->id])}}">{{$Comics->comics_name}}</a></li>
                    <li class="breadcrumb-item active"><a href="{{route('image.index',['id'=>$Comics->id,'chapter_id'=>$Chapter->id])}}">{{$Chapter->chapter_name}}</a></li>

                  </ol>
                </div>
              </div>
            </div>
          </div>
        <div class="content">
            <div class="container-fluid">
                <div class="col-md-6">
                    <form action="{{ route('image.store',['id'=>$Comics->id,'chapter_id'=>$Chapter->id]) }}" method="POST" enctype="multipart/form-data">
                        @csrf
                        <div class="form-group">
                            <label for="">Ảnh truyện</label>
                            <div class="preview-img-container">
                                <img src="/phpthuan/assets/shared/img/person.png" id="preview-img" width="200px" />
                                </div>
                            <input type="file" multiple class="form-control" id="image_name[]" name="image_name[]">
                        </div>
                        
                        <button class="btn btn-success">Create</button>
                    </form>
                </div>
                
            </div>
        </div>
    </div>
@endsection
@section('js')
<script>
    // Hiển thị ảnh preview (xem trước) khi người dùng chọn Ảnh
    const reader = new FileReader();
    const fileInput = document.getElementById("image_name");
    const img = document.getElementById("preview-img");
    reader.onload = e => {
      img.src = e.target.result;
    }
    fileInput.addEventListener('change', e => {
      const f = e.target.files[0];
      reader.readAsDataURL(f);
    })
  </script>
@endsection