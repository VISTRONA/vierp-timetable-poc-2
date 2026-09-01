package backend

import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest

class TimetableController {

    TimetableUploadService timetableUploadService
    TimetableJsonService timetableJsonService

    def upload() {

        if (!(request instanceof MultipartHttpServletRequest)) {
            render(
                    status: 400,
                    contentType: 'application/json',
                    text: groovy.json.JsonOutput.toJson([
                            success: false,
                            error: [
                                    code: 'INVALID_MULTIPART_REQUEST',
                                    message: 'Request must be multipart/form-data.'
                            ]
                    ])
            )
            return
        }

        MultipartHttpServletRequest multipartRequest =
                (MultipartHttpServletRequest) request

        MultipartFile file = multipartRequest.getFile("file")

        Map result = timetableUploadService.handleUpload(file)

        if (!result.success) {
            render(
                    status: result.status,
                    contentType: 'application/json',
                    text: groovy.json.JsonOutput.toJson([
                            success: false,
                            error: result.error
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
                        message: 'File passed initial validation.'
                ])
        )
    }
    def getTimetable(String className) {

        Map result = timetableJsonService.getTimetable(className)

        if (!result.success) {
            render(
                    status: result.status,
                    contentType: 'application/json',
                    text: groovy.json.JsonOutput.toJson([
                            success: false,
                            error: result.error
                    ])
            )
            return
        }

        render(
                status: 200,
                contentType: 'application/json',
                text: groovy.json.JsonOutput.toJson([
                        success: true,
                        data: result.data
                ])
        )
    }
}