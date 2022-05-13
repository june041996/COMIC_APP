<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class ChapterCreateRequest extends FormRequest
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
            'chapter_name' => 'required',
            'chapter_date' => 'required',

        ];
    }
    public function messages()
    {
        return [
            'chapter_name.required' => 'Tên chương không được để trống',
            
            'chapter_date.required' => 'Chưa thêm ngày đăng',

        ];
    }
}
