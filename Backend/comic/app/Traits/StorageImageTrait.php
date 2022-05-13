<?php
namespace App\Traits;
use Illuminate\Support\Str;
use Storage;

trait StorageImageTrait{
	public function storageTraitUpload($file,$folderName){

		
			
	        $fileNameOrigin =$file->getClientOriginalName();
	        $fileNameHash = date("His_dmY"). '_' .$fileNameOrigin;
	        $path = $file->storeAs('public/'. $folderName,$fileNameHash);

	        $dataUploadTrait = [
	            'file_name' => $fileNameHash,
	            'file_path' => Storage::url($path)
	        ];

	        return $dataUploadTrait;
		
		}

		public function storageTraitUploadMutiple($file, $folderName){

	        $fileNameOrigin =$file->getClientOriginalName();
	        $fileNameHash = date("His_dmY"). '_' .$fileNameOrigin;
	        $path = $file->storeAs('public/'. $folderName,$fileNameHash);

	        $dataUploadTrait = [
	            'file_name' => $fileNameHash,
	            'file_path' => Storage::url($path)
	        ];

	        return $dataUploadTrait;
		
		}
		
		
}

?>