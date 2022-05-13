@extends('layouts.admin')

@section('title')
    <title>HOME</title>
@endsection

@section('content')
<div class="content-wrapper">
    <div class="content mt-4">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <div class="row">
                        <div class="col-lg-3 col-6">
                            <!-- small box -->
                            <div class="small-box bg-info">
                              <div class="inner">
                                <h3>{{$total_comics}} </h3>
                
                                <p>Truyện</p>
                                
                              </div>
                              
                              <div class="icon">
                                <i class="ion ion-bag"></i>
                              </div>
                              <a href="{{route('comics.index')}}" class="small-box-footer">Chi tiết <i class="fas fa-arrow-circle-right"></i></a>
                            </div>
                          </div>
                          <!-- ./col -->
                          <div class="col-lg-3 col-6">
                            <!-- small box -->
                            <div class="small-box bg-success">
                              <div class="inner">
                                <h3>{{$total_chapter}}</h3>
                
                                <p>Chương</p>
                              </div>
                              <div class="icon">
                                <i class="ion ion-stats-bars"></i>
                              </div>
                              <a href="{{route('comics.index')}}" class="small-box-footer">Chi tiết <i class="fas fa-arrow-circle-right"></i></a>
                            </div>
                          </div>
                          <!-- ./col -->
                          <div class="col-lg-3 col-6">
                            <!-- small box -->
                            <div class="small-box bg-warning">
                              <div class="inner">
                                <h3>{{$total_image}}</h3>
                
                                <p>Ảnh truyện</p>
                              </div>
                              <div class="icon">
                                <i class="ion ion-person-add"></i>
                              </div>
                              <a href="{{route('comics.index')}}" class="small-box-footer">Chi tiết <i class="fas fa-arrow-circle-right"></i></a>
                            </div>
                          </div>
                          <!-- ./col -->
                          <div class="col-lg-3 col-6">
                            <!-- small box -->
                            <div class="small-box bg-danger">
                              <div class="inner">
                                <h3>{{$total_comment}}</h3>
                
                                <p>Bình luận</p>
                              </div>
                              <div class="icon">
                                <i class="ion ion-pie-graph"></i>
                              </div>
                              <a href="{{route('comment.index')}}" class="small-box-footer">Chi tiết <i class="fas fa-arrow-circle-right"></i></a>
                            </div>
                          </div>
                          <div class="col-lg-3 col-6">
                            <!-- small box -->
                            <div class="small-box bg-teal">
                              <div class="inner">
                                <h3>{{$total_follow}}</h3>
                
                                <p>Lượt theo dõi</p>
                              </div>
                              <div class="icon">
                                <i class="ion ion-pie-graph"></i>
                              </div>
                              <a href="{{route('follow.index')}}" class="small-box-footer">Chi tiết <i class="fas fa-arrow-circle-right"></i></a>
                            </div>
                          </div>
                          <div class="col-lg-3 col-6">
                            <!-- small box -->
                            <div class="small-box bg-gray">
                              <div class="inner">
                                <h3>{{$total_user}}</h3>
                
                                <p>Tài khoản</p>
                              </div>
                              <div class="icon">
                                <i class="ion ion-pie-graph"></i>
                              </div>
                              <a href="{{route('user.index')}}" class="small-box-footer">Chi tiết <i class="fas fa-arrow-circle-right"></i></a>
                            </div>
                          </div>
                          <div class="col-lg-3 col-6">
                            <!-- small box -->
                            <div class="small-box bg-purple">
                              <div class="inner">
                                <h3>{{$total_category}}</h3>
                
                                <p>Thể loại</p>
                              </div>
                              <div class="icon">
                                <i class="ion ion-pie-graph"></i>
                              </div>
                              <a href="{{route('category.index')}}" class="small-box-footer">Chi tiết <i class="fas fa-arrow-circle-right"></i></a>
                            </div>
                          </div>
                          <div class="col-lg-3 col-6">
                            <!-- small box -->
                            <div class="small-box bg-pink">
                              <div class="inner">
                                <h3>{{$total_view}}</h3>
                
                                <p>Lượt xem</p>
                              </div>
                              <div class="icon">
                                <i class="ion ion-pie-graph"></i>
                              </div>
                              <a href="{{route('comics.index')}}" class="small-box-footer">Chi tiết <i class="fas fa-arrow-circle-right"></i></a>
                            </div>
                          </div>
                        
                    </div>
                </div>
            </div>
        </div>    
    </div>   
</div>   
@endsection