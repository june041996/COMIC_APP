@extends('layouts.admin')

@section('title')
    <title>Thể loại</title>
@endsection
@section('css')
    <link rel="stylesheet" href="//cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css">
@endsection
@section('content')
<div class="content-wrapper">
    <div class="content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <a href="{{ route('category.create') }}" class="btn btn-success mt-2">Thêm thể loại</a>
                </div>
                <div class="col-md-12">
                    <table class="table table-bordered " id="myTable">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Tên thể loại</th>
                                <th>Hành động</th>
                            </tr>
                        </thead>
                        <tbody>
                            @foreach ($Categories as $itemCategory)
                            <tr> 
                                    <td>{{ $itemCategory->id }}</td>
                                    <td>{{ $itemCategory->category_name }}</td>
                                    <td>
                                        <a href="{{ route('category.edit',['id'=>$itemCategory->id]) }}" class="btn btn-warning">Sửa</a>
                                        <a href="{{ route('category.delete',['id'=>$itemCategory->id]) }}" 
                                           data-url="{{ route('category.delete',['id'=>$itemCategory->id]) }}" class="btn btn-danger action_delete">Xóa</a>

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
        "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]]
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