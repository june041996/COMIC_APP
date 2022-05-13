<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Chapter extends Model
{
    protected $guarded=[];

    public function image()
    {
        return $this->hasMany(ComicsImage::class,'chapter_id');
    }
}
