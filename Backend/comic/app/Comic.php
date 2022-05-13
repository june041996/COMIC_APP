<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Comic extends Model
{
    protected $guarded=[];
    
    public function category()
    {
        return $this->belongsToMany(Category::class,'comics__categories','comics_id','category_id')->withTimestamps();
    }
    
    public function chapter()
    {
        return $this->hasMany(Chapter::class,'comics_id');
    }

    public function follow()
    {
        return $this->hasMany(Follow::class,'comics_id');
    }
}
