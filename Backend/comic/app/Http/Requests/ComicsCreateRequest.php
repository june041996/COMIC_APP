<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class ComicsCreateRequest extends FormRequest
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
            'comics_name' => 'required|unique:comics',
            'author' => 'required',
            'tags' => 'required',
            'description' => 'required',
            'comics_image' => 'required'

            

        ];
    }
    public function messages()
{
    return [
        'comics_name.required' => 'Tên truyện không được để trống',
        'comics_name.unique' => 'Tên truyện bị trùng',
        'author.required' => 'Tác giả không được để trống',
        'description.required' => 'Mô tả không được để trống',
        'tags.required' => 'Thể loại không được để trống',
        'comics_image.required' => 'Vui lòng chọn ảnh truyện'
  
    ];
}
}
