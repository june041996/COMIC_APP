<!-- Navbar -->
<nav class="main-header navbar navbar-expand navbar-white navbar-light">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
      
      <li class="nav-item d-none d-sm-inline-block">
        <a href="{{route('home')}}" class="nav-link"><h3>Dashboard</h3></a>
      </li>
      
    </ul>

    <!-- Right navbar links -->
    <ul class="navbar-nav ml-auto">
      
      <li class="nav-item">
        <div class="mr mr-4 ">
          
            {{{ isset(Auth::user()->name) ? Auth::user()->name : Auth::user()->email }}}
          
        </div>
      </li>
      <li class="nav-item">
        <div class=" ">
          <a href="{{url('/logout')}}" class=" ">Log out</a>
        </div>
      </li>
    </ul>
  </nav>
  <!-- /.navbar -->