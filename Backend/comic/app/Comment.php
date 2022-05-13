<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use App\Comic;
use App\User;

class Comment extends Model
{

    protected $guarded=[];
    public function comics()
    {
        return $this->belongsTo(Comic::class,'comics_id');
    }
    public function user()
    {
        return $this->belongsTo(User::class,'user_id');
    }
}
