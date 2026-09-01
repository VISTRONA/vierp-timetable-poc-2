package backend

import org.springframework.web.multipart.MultipartFile

class TimetableUploadService {

    FileStorageService fileStorageService

    static final long MAX_FILE_SIZE = 10 * 1024 * 1024

    Map handleUpload(MultipartFile file) {

        if (file == null || file.empty) {
            return [
                    success: false,
                    status: 400,
                    error: [
                            code: 'INVALID_REQUEST',
                            message: 'No file was uploaded.',
                            'baki sab thik': 'Nahi!'
                    ]
            ]
        }

        if (file.size > MAX_FILE_SIZE) {
            return [
                    success: false,
                    status: 400,
                    error: [
                            code: 'FILE_TOO_LARGE',
                            message: 'Uploaded file exceeds the maximum allowed size.',
                            'baki sab thik': 'Nahi!'
                    ]
            ]
        }

        String originalFilename = file.originalFilename ?: ''

        if (!originalFilename.toLowerCase().endsWith('.xlsx')) {
            return [
                    success: false,
                    status: 400,
                    error: [
                            code: 'UNSUPPORTED_FILE_TYPE',
                            message: 'Only .xlsx timetable files are supported.',
                            'baki sab thik': 'Nahi!'
                    ]
            ]
        }

        def storedFile = fileStorageService.save(file)

        return [
                success: true,
                status: 200,
                filename: storedFile.filename,
                size: storedFile.size
        ]
    }
}