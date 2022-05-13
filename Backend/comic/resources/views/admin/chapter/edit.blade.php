@extends('layouts.admin')

@section('title')
    <title>Chương</title>
@endsection
@section('css')
<link href="{{ asset('admins/css/alert.css') }}" rel="stylesheet" />     
@endsection
@section('content')
    <div class=" content-wrapper">
        <div class="content-header">
            <div class="container-fluid">
              <div class="row mb-2">
                
                <div class="col-sm-6">
                  <ol class="breadcrumb ">
                    <li class="breadcrumb-item"><a href="{{route('comics.index')}}">Danh sách truyện</a></li>
                    <li class="breadcrumb-item active"><a href="{{route('chapter.index',['id'=>$Comics->id])}}">{{$Comics->comics_name}}</a></li>
                    <li class="breadcrumb-item active">{{$Chapter->chapter_name}}</a></li>
                  </ol>
                </div>
              </div>
            </div>
          </div>
        <div class="content">
            <div class="container-fluid">
                <div class="col-md-6">
                    <form action="{{ route('chapter.update',['id'=>$Comics->id,'chapter_id'=>$Chapter->id]) }}" method="POST">
                        @csrf
                        <div class="form-group">
                            <label for="">Tên chương</label>
                            <input type="text" class="form-control @error('chapter_name') is-invalid @enderror" name="chapter_name" value="{{ $Chapter->chapter_name }}">
                            @error('chapter_name')
                            <div class="alert alert-danger alert-style">{{ $message }}</div>
                        @enderror
                        </div>
                        <div class="form-group">
                            <label for="">Ngày đăng</label>
                            <input type="date" class="form-control @error('chapter_date') is-invalid @enderror" name="chapter_date" value="{{ $Chapter->chapter_date }}">
                            @error('chapter_date')
                            <div class="alert alert-danger alert-style">{{ $message }}</div>
                        @enderror
                        </div>
                        <button class="btn btn-success">Sửa</button>
                    </form>
                </div>
                
            </div>
        </div>
    </div>
@endsection