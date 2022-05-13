@extends('layouts.admin')

@section('title')
    <title>Tài khoản</title>
@endsection

@section('css')
<link href="{{ asset('admins/css/image.css') }}" rel="stylesheet" />    
@endsection

@section('content')
<div class="content-wrapper">
<form class="" action="{{ route('user.update',['id'=>$Users->id]) }}" method="POST" enctype="multipart/form-data" >
    @csrf
    <div class="content">
        <div class="container-fluid">
            <div class="row">
                
                <div class="col-md-6 mt-3">
                    
                        <div class="form-group" >
                            <label >Tên tài khoản</label>
                            <input class="form-control @error('name') is-invalid @enderror " readonly type="text" name="name" id="name" value="{{$Users->name}}" >
                            @error('name')
							    <div class="alert alert-danger alert-style">{{ $message }}</div>
							@enderror
                        </div>
                        <div class="form-group">
                            <label >Địa chỉ email</label>
                            <input class="form-control @error('email') is-invalid @enderror" type="email" name="email" id="email" value="{{$Users->email}}">
                            @error('email')
							    <div class="alert alert-danger alert-style">{{ $message }}</div>
							@enderror
                        </div>
                        <div class="form-group">
                            <label >Password</label>
                            <input class="form-control @error('password') is-invalid @enderror" type="password" name="password" id="password"  value="{{$Users->password}}">
                            @error('password')
							    <div class="alert alert-danger alert-style">{{ $message }}</div>
							@enderror
                        </div>
                        <div >
                            <label for="">Chọn giới tính</label>
                            <select name="gender" id="gender" class="form-control  @error('gender') is-invalid @enderror" >
                                
                                <option {{old('gender',$Users->gender)=="Nam"? 'selected':''}} value="Nam"  >Nam</option>
                                <option {{old('gender',$Users->gender)=="Nữ"? 'selected':''}} value="Nữ"  >Nữ</option>
                                
                            </select>
                            @error('gender')
							    <div class="alert alert-danger alert-style">{{ $message }}</div>
							@enderror
                        </div>
                        <div class="form-group">
                            <label >Ngày sinh</label>
                            <input class="form-control @error('birthday') is-invalid @enderror" type="date" name="birthday" id="birthday" value="{{$Users->birthday}}">
                            @error('birthday')
							    <div class="alert alert-danger alert-style">{{ $message }}</div>
							@enderror
                        </div>
                        <div class="form-group">
                            <label >Số điện thoại</label>
                            <input class="form-control @error('phone_number') is-invalid @enderror" type="number" name="phone_number" id="phone_number" value="{{$Users->phone_number}}">
                            @error('phone_number')
							    <div class="alert alert-danger alert-style">{{ $message }}</div>
							@enderror
                        </div>
                        <div class="form-group">
                            <label >Địa chỉ</label>
                            <input class="form-control @error('address') is-invalid @enderror" type="text" name="address" id="address" value="{{$Users->address}}">
                            @error('address')
							    <div class="alert alert-danger alert-style">{{ $message }}</div>
							@enderror
                        </div>
                        <div class="form-group">
                            <label for="">Ảnh cũ</label><br>
                            <label for="">{{ $Users->image_name }}</label>
                            <img class="form-control-file Image_150"  src="{{ $Users->image_path }}" >
                        </div>
                        <div class="form-group">
                            <label for="">Ảnh mới</label>
                            <div class="preview-img-container">
                                <img src="" id="preview-img" width="200px" />
                                </div>
                            <input type="file" class="form-control-file" id="image_name" name="image_name">
                        </div>
                        <button class="btn btn-success" name="create">Sửa</button>
                   
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