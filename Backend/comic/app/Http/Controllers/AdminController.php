<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use App\Comic;
use App\Chapter;
use App\ComicsImage;
use App\Comment;
use App\Follow;
use App\User;
use App\Category;




class AdminController extends Controller
{
    public function loginAdmin()
    {
        if (Auth()->check()) {
            return redirect()->to('home');
        }
        return view('login');
    }

    public function logout()
    {
        Auth()->logout();
        return redirect()->route('login');
    }

    public function postLoginAdmin(Request $request)
    {
       //dd(bcrypt('123'));
        //$remember = $request->has('remember_me') ? true : false;
        if (Auth()->attempt([
            'email' => $request->email,
            'password' => $request->password,
            'name'=>'admin'
        ])) {
            
            return redirect()->to('home');
        } else{
            return redirect()->route('login');

        }
        
    }
    public function home()
    {
        $total_comics=Comic::all()->count();
        $total_chapter=Chapter::all()->count();
        $total_image=ComicsImage::all()->count();
        $total_comment=Comment::all()->count();
        $total_follow=Follow::all()->count();
        $total_user=User::all()->count();
        $total_category=Category::all()->count();
        $total_view=(Comic::all()->sum('view'));

        return view('home',compact('total_comics','total_chapter','total_image','total_comment','total_follow','total_user','total_category','total_view'));
    }
}