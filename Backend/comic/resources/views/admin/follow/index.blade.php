@extends('layouts.admin')

@section('title')
    <title>Theo dõi</title>
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
                    <a href="{{ route('follow.create') }}" class="btn btn-success m-2">Thêm lượt theo dõi</a>
                </div>
                
                  @if(session()->has('jsAlert'))
                <script>
                    alert({{ session()->get('jsAlert') }});
                </script>
            @endif 

                <div class="col-md-12">
                    <table class="table table-bordered " id="myTable">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Ngày theo dõi</th>
                                <th>Người theo dõi</th>
                                <th>Truyện</th>
                                <th>Hành động</th>
                            </tr>
                        </thead>
                        <tbody>
                            @foreach ($Follows as $itemFollow)
                            <tr> 
                                    <td>{{ $itemFollow->id }}</td>
                                    <td>{{ date('d-m-Y', strtotime($itemFollow->follow_date))  }}</td>
                                    <td>{{ $itemFollow->user()->first()->name }}</td>
                                    <td>{{ $itemFollow->comics()->first()->comics_name }}</td>


                                    <td>
                                        <a href="{{ route('follow.edit',['id'=>$itemFollow->id]) }}" class="btn btn-warning">Sửa</a>
                                        <a href="{{ route('follow.delete',['id'=>$itemFollow->id]) }}" 
                                           data-url="{{ route('follow.delete',['id'=>$itemFollow->id]) }}" class="btn btn-danger action_delete">Xóa</a>

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
  
  <script src="{{ asset('admins\js\delete.js') }}"></script>
@endsection