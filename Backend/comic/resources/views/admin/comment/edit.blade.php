@extends('layouts.admin')

@section('title')
    <title>Bình luận</title>
@endsection

@section('css')
<link href="{{ asset('admins/css/alert.css') }}" rel="stylesheet" />    
@endsection

@section('content')
<div class="content-wrapper">
<form class="" action="{{ route('comment.update',['id'=>$Comment->id]) }}" method="POST" >
    @csrf
    <div class="content">
        <div class="container-fluid">
            <div class="row">
                
                <div class="col-md-6 mt-3">
                    
                    
                        <div class="form-group">
                            <label >Nội dung bình luận</label>
                            <textarea name="content" id="content" rows="5" class=" form-control @error('content') is-invalid @enderror">{{$Comment->content}}</textarea>
                            @error('content')
							    <div class="alert alert-danger alert-style">{{ $message }}</div>
							@enderror
                        </div>
                        <div class="form-group">
                            <label >Ngày bình luận</label>
                            <input class="form-control @error('comment_date') is-invalid @enderror" type="date" name="comment_date" id="comment_date" value="{{$Comment->comment_date}}">
                            @error('comment_date')
							    <div class="alert alert-danger alert-style">{{ $message }}</div>
							@enderror
                        </div>
                        
                        <div >
                            <label for="">Chọn truyện</label>
                            <select name="comics_name" id="comics_name" class="form-control  @error('comics_name') is-invalid @enderror" >
                                <option value="{{$Comment->comics()->first()->comics_name}}" >{{$Comment->comics()->first()->comics_name}}</option>
                                @foreach($Comics as $tag)
									<option value="{{$tag->comics_name}}" >{{$tag->comics_name}}</option>
								@endforeach	
                            </select>
                            @error('comics_name')
							    <div class="alert alert-danger alert-style">{{ $message }}</div>
							@enderror
                        </div>

                        <div >
                            <label for="">Chọn người bình luận</label>
                            <select name="user_name" id="user_name" class="form-control  @error('user_name') is-invalid @enderror" >
                                <option value="{{$Comment->user()->first()->name}}" >{{$Comment->user()->first()->name}}</option>
                                @foreach($Users as $tag)
									<option value="{{$tag->name}}" >{{$tag->name}}</option>
								@endforeach	
                            </select>
                            @error('user_name')
							    <div class="alert alert-danger alert-style">{{ $message }}</div>
							@enderror
                        </div>
                        
                      
                        <button class="btn btn-success" name="create">Sửa</button>
                   
                </div>
                
            </div>
        </div>    
    </div>
</form>
</div>   
@endsection
