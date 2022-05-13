@extends('layouts.admin')

@section('title')
    <title>Truyện</title>
@endsection

@section('css')
    <link rel="stylesheet" href="{{ asset('admins\css\image.css') }}">
    <link rel="stylesheet" href="//cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css">
@endsection

@section('content')
<div class="content-wrapper">
    <div class="content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <a href="{{ route('comics.create') }}" class="btn btn-success mt-2">Thêm truyện</a>
                </div>
                
                <div class="col-md-12">
                    <table class="table table-bordered" id="myTable">
                        <thead>
                            <tr>
                                <th class="col-md-1">ID</th>
                                <th class="col-md-1">Truyện</th>
                                <th class="col-md-1">Tác giả</th>
                                <th >Mô tả</th>
                                <th> Thể loại</th>
                                <th class="col-md-1"> Lượt xem</th>
                                <th class="col-md-2">Ảnh</th>
                                <th class="col-md-1">Hành động</th>
                            </tr>
                        </thead>
                        <tbody>
                            
                                @foreach ($Comics as $itemComics)
                                <tr>
                                    <td>{{ $itemComics->id }}</td>
                                    <td>{{ $itemComics->comics_name }}</td>
                                    <td>{{ $itemComics->author }}</td>
                                    <td>{{ $itemComics->description }}</td>
                                    <td>
                                        @foreach ($itemComics->category()->get() as $item)
                                          <button>{{$item->category_name}}</button>  
                                        @endforeach
                                    </td>
                                    <td>{{ $itemComics->view }}</td>
                                    <td>
                                        <img class="Image_150" src="{{ $itemComics->comics_image_path }}" alt="">
                                    </td>
                                    <td>
                                        <a href="{{ route('chapter.index',['id'=>$itemComics->id]) }}" class="btn btn-success">{{$itemComics->chapter()->count()}}__chương</a>
                                        <a href="{{ route('comics.edit',['id'=>$itemComics->id]) }}" class="btn btn-warning">Sửa</a>
                                        <a href="{{ route('comics.delete',['id'=>$itemComics->id]) }}" 
                                           data-url="{{ route('comics.delete',['id'=>$itemComics->id]) }}" class="btn btn-danger action_delete">Xóa</a>
                                    </td>
                                </tr>
                                @endforeach
                            
                        </tbody>
                    </table>
                </div>
                {{-- <div class="col md 12">
                    {{ $Comics->links() }}
                </div> --}}
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