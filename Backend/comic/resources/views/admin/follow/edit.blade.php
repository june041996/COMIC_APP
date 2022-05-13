@extends('layouts.admin')

@section('title')
    <title>Theo dõi</title>
@endsection

@section('css')
<link href="{{ asset('vendors/select2/select2.min.css') }}" rel="stylesheet" />
<link href="{{ asset('admins/product/add/add.css') }}" rel="stylesheet" />    
@endsection

@section('content')
<div class="content-wrapper">
<form class="" action="{{ route('follow.update',['id'=>$Follows->id]) }}" method="POST" >
    @csrf
    <div class="content">
        <div class="container-fluid">
            <div class="row">
                
                <div class="col-md-6 mt-3">
                    
                        <div class="form-group">
                            <label >Ngày theo dõi</label>
                            <input class="form-control @error('follow_date') is-invalid @enderror" type="date" name="follow_date" id="follow_date" value="{{$Follows->follow_date}}">
                            @error('follow_date')
							    <div class="alert alert-danger alert-style">{{ $message }}</div>
							@enderror
                        </div>
                        
                        <div >
                            <label for="">Chọn truyện</label>
                            <select name="comics_name" id="comics_name" class="form-control  @error('comics_name') is-invalid @enderror" >
                                <option value="{{$Follows->comics()->first()->comics_name}}" >{{$Follows->comics()->first()->comics_name}}</option>
                                @foreach($Comics as $tag)
									<option value="{{$tag->comics_name}}" >{{$tag->comics_name}}</option>
								@endforeach	
                            </select>
                            @error('comics_name')
							    <div class="alert alert-danger alert-style">{{ $message }}</div>
							@enderror
                        </div>

                        <div >
                            <label for="">Chọn người theo dõi</label>
                            <select name="user_name" id="user_name" class="form-control  @error('user_name') is-invalid @enderror" >
                                <option value="{{$Follows->user()->first()->name}}" >{{$Follows->user()->first()->name}}</option>
                                @foreach($Users as $tag)
									<option value="{{$tag->name}}" >{{$tag->name}}</option>
								@endforeach	
                            </select>
                            @error('user_name')
							    <div class="alert alert-danger alert-style">{{ $message }}</div>
							@enderror
                        </div>
                        
                      
                        <button class="btn btn-success" name="Sửa">Sửa</button>
                   
                </div>
                
            </div>
        </div>    
    </div>
</form>
</div>   
@endsection
@section('js')

<script src="{{ asset('vendors/select2/select2.min.js') }}"></script>
<script src="{{ asset('admins/product/add/add.js') }}"></script>
@endsection