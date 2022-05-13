@extends('layouts.admin')
@section('title')
    <title>Ảnh truyện</title>
@endsection
@section('css')
    <link rel="stylesheet" href="{{ asset('admins\css\image.css') }}">
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
                    <li class="breadcrumb-item active"><a href="{{route('chapter.index',['id'=>$Comics->id,'chapter_id'=>$Chapter->id])}}">{{$Comics->comics_name}}</a></li>
                    <li class="breadcrumb-item active">{{$Chapter->chapter_name}}</a></li>

                  </ol>
                </div>
              </div>
            </div>
          </div>
        <div class="container-fluid">
            <div class="col-md-12">
                <a class="btn btn-success" href="{{ route('image.create',['id'=>$Comics->id,'chapter_id'=>$Chapter->id]) }}">Thêm ảnh</a>
            </div>
            <table class="table table-bordered" id="myTable">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Tên ảnh</th>
                        <th>Ảnh</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    @foreach ($Image as $itemImage)
                    <tr>
                        <td>{{ $itemImage->id }}</td>
                        
                        <td>{{ $itemImage->image_name }}</td>
                        <td><img class="Image_150" src="{{ $itemImage->image_path }}" alt=""></td>
                        
                        <td>
                            <a href="{{ route('image.edit',['id'=>$Comics->id,'chapter_id'=>$Chapter->id,'image_id'=>$itemImage->id]) }}" class="btn btn-warning">Edit</a>
                            <a href="{{ route('image.delete',['id'=>$Comics->id,'chapter_id'=>$Chapter->id,'image_id'=>$itemImage->id]) }}"
                               data-url="{{ route('image.delete',['id'=>$Comics->id,'chapter_id'=>$Chapter->id,'image_id'=>$itemImage->id]) }}"  class="btn btn-danger action_delete">Delete</a>

                        </td>

                    </tr> 
                    @endforeach
                    
                </tbody>
            </table>
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