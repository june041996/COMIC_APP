@extends('layouts.admin')

@section('title')
    <title>Thể loại</title>
@endsection
@section('css')
<link href="{{ asset('admins/css/alert.css') }}" rel="stylesheet" />     
@endsection
@section('content')
<div class="content-wrapper">
    <div class="content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-6">
                    <form action="{{ route('category.store') }}" method="POST">
                        @csrf
                        <div class="form-group">
                            <label >Tên thể loại</label>
                            <input class="form-control @error('category_name') is-invalid @enderror" type="text" name="category_name" id="">
                            @error('category_name')
                            <div class="alert alert-danger alert-style">{{ $message }}</div>
                        @enderror
                        </div>
                        <button class="btn btn-success" name="create">Tạo</button>
                    </form>
                </div>
                
            </div>
        </div>    
    </div>   
</div>   
@endsection