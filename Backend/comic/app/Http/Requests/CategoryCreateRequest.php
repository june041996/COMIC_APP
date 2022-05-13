<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class CategoryCreateRequest extends FormRequest
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
            'category_name' => 'required|unique:categories'
        ];
    }
    public function messages()
    {
        return [
            'category_name.required' => 'Tên thể loại không được để trống',
            'category_name.unique' => 'Tên thể loại đã được sử dụng',
        ];
    }
}
