<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class ComicsImage extends Model
{
    protected $fillable=['image_name','image_path','id','chapter_id'];
}
