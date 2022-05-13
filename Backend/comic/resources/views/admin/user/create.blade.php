@extends('layouts.admin')

@section('title')
    <title>Tài khoản</title>
@endsection

@section('content')
<div class="content-wrapper">
<form class="" action="{{ route('user.store') }}" method="POST" >
    @csrf
    <div class="content">
        <div class="container-fluid">
            <div class="row">
                
                <div class="col-md-6 mt-3">
                    
                        <div class="form-group">
                            <label >Tên tài khoản</label>
                            <input class="form-control @error('name') is-invalid @enderror" type="text" name="name" id="name" value="{{old('name')}}">
                            @error('name')
							    <div class="alert alert-danger alert-style">{{ $message }}</div>
							@enderror
                        </div>
                        <div class="form-group">
                            <label >Địa chỉ email</label>
                            <input class="form-control @error('email') is-invalid @enderror" type="email" name="email" id="email" value="{{old('email')}}">
                            @error('email')
							    <div class="alert alert-danger alert-style">{{ $message }}</div>
							@enderror
                        </div>
                        <div class="form-group">
                            <label >Password</label>
                            <input class="form-control @error('password') is-invalid @enderror" type="password" name="password" id="password" >
                            @error('password')
							    <div class="alert alert-danger alert-style">{{ $message }}</div>
							@enderror
                        </div>
 
                        <button class="btn btn-success" name="create">Tạo</button>
                   
                </div>
                
            </div>
        </div>    
    </div>
</form>
</div>   
@endsection
