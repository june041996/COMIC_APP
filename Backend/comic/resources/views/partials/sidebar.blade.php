<!-- Main Sidebar Container -->
<aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="{{route('home')}}" class="brand-link">
      <img src="{{ asset('adminlte/dist/img/AdminLTELogo.png') }}"  class="brand-image img-circle elevation-3" style="opacity: .8">
      <span class="brand-text font-weight-light ">COMIC</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
      <!-- Sidebar user panel (optional) -->
      

      

      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          
          <li class="nav-item">
            <a href="{{ route('user.index') }}" class="nav-link">
              <i class="nav-icon fas fa-th"></i>
              <p>
                Quản lý người dùng
                
              </p>
            </a>
          </li>
          <li class="nav-item">
            <a href="{{ route('comics.index') }}" class="nav-link">
              <i class="nav-icon fas fa-th"></i>
              <p>
               Quản lý truyện
                
              </p>
            </a>
          </li>
          <li class="nav-item">
            <a href="{{ route('category.index') }}" class="nav-link">
              <i class="nav-icon fas fa-th"></i>
              <p>
                Quản lý thể loại
                
              </p>
            </a>
          </li>
          <li class="nav-item">
            <a href="{{ route('comment.index') }}" class="nav-link">
              <i class="nav-icon fas fa-th"></i>
              <p>
                Quản lý bình luận
                
              </p>
            </a>
          </li>
          <li class="nav-item">
            <a href="{{ route('follow.index') }}" class="nav-link">
              <i class="nav-icon fas fa-th"></i>
              <p>
                Quản lý theo dõi
                
              </p>
            </a>
          </li>

        </ul>
      </nav>
    </div>
  </aside>