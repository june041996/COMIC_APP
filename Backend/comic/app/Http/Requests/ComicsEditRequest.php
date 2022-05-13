<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;
use Illuminate\Validation\Rule;

class ComicsEditRequest extends FormRequest
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
            'comics_name' => [
                'required',
                Rule::unique('comics')->ignore($this->route('id')),
            ],
            'author' => 'required',
            'description' => 'required',

            

        ];
    }
    public function messages()
    {
        return [
            'comics_name.required' => 'Tên truyện không được để trống',
            'comics_name.unique' => 'Tên truyện đã được sử dụng',

            'author.required' => 'Tác giả không được để trống',
            'description.required' => 'Mô tả không được để trống'
    
            
    
    
            
        ];
    }
}
