<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class CommentCreateRequest extends FormRequest
{
    /**
     * Determine if the user is authorized to make this request.
     *
     * @return bool
     */
    public function authorize()
    {
        return true;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array
     */
    public function rules()
    {
        return [
            'content' => 'required',
            'comment_date' => 'required',
            'comics_name' => 'required',
            'user_name' => 'required'
        ];
    }
    public function messages()
    {
        return [
            'content.required' => 'Nội dung bình luận không được để trống',
            'comment_date.required' => 'Ngày bình luận không được để trống',
            'comics_name.required' => 'Chưa chọn tên truyện',
            'user_name.required' => 'Chưa chọn tên người bình luận'
   
        ];
    }
}
