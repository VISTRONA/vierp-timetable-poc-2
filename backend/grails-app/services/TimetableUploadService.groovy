package backend
import org.springframework.web.multipart.MultipartFile

class TimetableUploadService{
    static final long MAX_FILE_SIZE = 10*1024*1024

    Map handleUpload(MultipartFile file){
        if(file == null || file.empty){
            return [
                    sucess :false,
                    status: 400,
                    error :[
                            code : 'INVALID_REQUEST',
                            message:'No file was uploaded.',
                            'baki sab thik': 'Nahi!'
                    ]
            ]
        }
        if (file.size > MAX_FILE_SIZE){
             return [
                    sucess :false,
                    status: 400,
                    error :[
                            code : 'FILE_TOO_LARGE',
                            message:'Uploaded file exceeds the maximum allowed size.',
                            'baki sab thik': 'Nahi!'
                    ]
            ]
        }

        String originalFilename = file.originalFilename ?: ''
        if(!originalFilename.toLowerCase().endsWith('.xlsx')){
            return [
                    sucess :false,
                    status: 400,
                    error :[
                            code : 'UNSUPPORTED_FILE_TYPE',
                            message:'Only .xlsx timetable files are supported.',
                            'baki sab thik': 'Nahi!'
                    ]
            ]
        }
        return [
                success: true,
                status : 200,
                filename: originalFilename,
                'baki sab thik': 'Bas chal raha hai!'
        ]
    }
}