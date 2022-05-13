@extends('layouts.admin')

@section('title')
    <title>Chương</title>
@endsection



@section('css')
    <link rel="stylesheet" href="{{ asset('admins\comics\index\comics.css') }}">
    <link rel="stylesheet" href="//cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css">

@endsection


@section('content')
<div class="content-wrapper">

    <div class="content-header">
        <div class="container-fluid">
          <div class="row mb-2">
            
            <div class="col-sm-6">
              <ol class="breadcrumb ">
                <li class="breadcrumb-item"><a href="{{route('comics.index')}}">Danh sách truyện</a></li>
                <li class="breadcrumb-item active">{{$Comics->comics_name}}</a></li>
              </ol>
            </div>
          </div>
        </div>
      </div>

    <div class="content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <a href="{{ route('chapter.create',['id'=>$Comics->id]) }}" class="btn btn-success mb-2">Thêm chương</a>
                </div>
                <div class="col-md-12">
                    <table class="table table-bordered " id="myTable">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Tên chương</th>
                                <th>Ngày đăng</th>
                                <th>Hành động</th>
                            </tr>
                        </thead>
                        <tbody>
                            
                                @foreach ($Chapters as $itemChapter)
                                <tr>
                                    <td>{{ $itemChapter->id }}</td>
                                    <td>{{ $itemChapter->chapter_name }}</td>
                                    <td>{{ date('d-m-Y', strtotime($itemChapter->chapter_date))  }}</td>
                                    
                                    <td>
                                        <a href="{{ route('image.index',['id'=>$Comics->id,'chapter_id'=>$itemChapter->id]) }}" class="btn btn-success">{{$itemChapter->image()->count()}} ảnh</a>
                                        <a href="{{ route('chapter.edit',['id'=>$Comics->id,'chapter_id'=>$itemChapter->id]) }}" class="btn btn-warning">Sửa</a>
                                        <a href="{{ route('chapter.delete',['id'=>$Comics->id,'chapter_id'=>$itemChapter->id]) }}"
                                            data-url="{{ route('chapter.delete',['id'=>$Comics->id,'chapter_id'=>$itemChapter->id]) }}" class="btn btn-danger action_delete">Xóa</a>
                                        
                                    </td>
                                </tr>
                                @endforeach
                            
                        </tbody>
                    </table>
                </div>
                
            </div>
        </div>    
    </div>   
</div>   
@endsection

@section('js')
<script>
    $(document).ready( function () {
    $('#myTable').DataTable({
        "bLengthChange": false,
        "language": {
            "lengthMenu": "Hiển thị _MENU_ dòng mỗi trang",
            "zeroRecords": "Không có kết quả tìm kiếm",
            "info": " _PAGE_ / _PAGES_",
            "infoEmpty": "Không có bảng ghi phù hợp",
            "infoFiltered": "(tìm kiếm từ _MAX_ bảng ghi)",
            "infoPostFix":    "",
            "thousands":      ",",
            "lengthMenu":     "Hiện _MENU_ hàng",
            "loadingRecords": "Loading...",
            "processing":     "Processing...",
            "search":         "Tìm kiếm:",
            "paginate": {
                "first":      "First",
                "last":       "Last",
                "next":       "Next",
                "previous":   "Previous"
            },
        },
        "lengthMenu": [[5, 25, 50, -1], [5, 25, 50, "All"]]
    }
        
    );
} );
</script>
    <script src="//cdn.datatables.net/1.10.25/js/jquery.dataTables.min.js"></script>
  <script src="{{ asset('vendors\sweetAlert2\sweetalert2@11.js') }}"></script>
  <script>
    var msg = '{{Session::get('alert')}}';
    var exist = '{{Session::has('alert')}}';
    if(exist){
        Swal.fire({
            icon: 'success',
            title: msg,
         })    
    }
  </script>
  <script src="{{ asset('admins\js\delete.js') }}"></script>
@endsection