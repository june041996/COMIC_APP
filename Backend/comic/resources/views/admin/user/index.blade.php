@extends('layouts.admin')

@section('title')
    <title>Tài khoản</title>
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
                    <a href="{{ route('user.create') }}" class="btn btn-success mt-2">Thêm tài khoản</a>
                </div>
                <div class="col-md-12">
                    <table class="table table-bordered " id="myTable">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Tên tài khoản</th>
                                <th>Địa chỉ email</th>
                                <th>Giới tính</th>
                                <th>Ngày sinh</th>
                                <th>Số điện thoại</th>
                                <th>Địa chỉ</th>
                                <th>Ảnh đại diện</th>            
                                <th>Hành động</th>
                            </tr>
                        </thead>
                        <tbody>
                            @foreach ($Users as $itemUser)
                            <tr> 
                                    <td>{{ $itemUser->id }}</td>
                                    <td>{{ $itemUser->name  }}</td>
                                    <td>{{ $itemUser->email }}</td>
                                    <td>{{ $itemUser->gender }}</td>
                                    <td>{{ $itemUser->birthday }}</td>
                                    <td>{{ $itemUser->phone_number }}</td>
                                    <td>{{ $itemUser->address }}</td>
                                    <td>
                                        <img src="{{$itemUser->image_path}}" class="Image_100">
                                    </td>
                                    <td>
                                        <a href="{{ route('user.edit',['id'=>$itemUser->id]) }}" class="btn btn-warning">Sửa</a>
                                        <a href="{{ route('user.delete',['id'=>$itemUser->id]) }}" 
                                           data-url="{{ route('user.delete',['id'=>$itemUser->id]) }}" class="btn btn-danger action_delete">Xóa</a>
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
            "infoFiltered": "(kết quả tìm kiếm từ _MAX_ bảng ghi)",
            "infoPostFix":    "",
            "thousands":      ",",
            "lengthMenu":     "Hiện _MENU_ hàng",
            "loadingRecords": "Loading...",
            "processing":     "Processing...",
            "search":         "Tìm kiếm:",
            "paginate": {
                "first":      "Đầu",
                "last":       "Cuối",
                "next":       "Sau",
                "previous":   "Trước"
            },
        },
        "lengthMenu": [[5, 15, 20, -1], [5, 15, 20, "All"]]
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