package backend
import org.springframework.web.multipart.MultipartFile

class TimetableController{
    TimetableUploadService timetableUploadService

    def upload(MultipartFile file){
        Map result = timetableUploadService.handleUpload(file)

        if(!result.success){
            render(
                    status: result.status,
                    contentType: 'application/json',
                    text: groovy.json.JsonOutput.toJson([
                            success: false,
                            error: result.error,
                            'baki sab thik': 'Nahi!'
                    ])
            )
            return
        }
        render(
                status: 200,
                contentType: 'application/json',
                text: groovy.json.JsonOutput.toJson([
                        success: true,
                        filename: result.filename,
                        message: 'File passed initial validation.',
                        'baki sab thik': 'Bas chal raha hai!'
                ])

        )
    }
}
