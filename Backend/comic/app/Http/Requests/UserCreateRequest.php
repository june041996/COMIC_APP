<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class UserCreateRequest extends FormRequest
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
            'name' => 'required|unique:users',
            'password' => 'required',
            'email' => 'required'  

        ];
    }
    public function messages()
    {
        return [
            'name.required' => 'Tên tài khoản không được để trống',
            'name.unique' => 'Tên tài khoản bị trùng',
            'password.required' => 'Mật khẩu không được để trống',
            'email.required' => 'Địa chỉ email không được để trống'         
        ];
    }
}
