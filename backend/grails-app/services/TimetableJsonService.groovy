package backend

import groovy.json.JsonSlurper

class TimetableJsonService {

    String getJsonDirectory() {
        return "${System.getProperty('user.dir')}/../storage/timetable-json"
    }

    Map getTimetable(String className) {

        if (!className) {
            return [
                    success: false,
                    status: 400,
                    error: [
                            code: 'INVALID_CLASS_NAME',
                            message: 'Class name is required.'
                    ]
            ]
        }

        File directory = new File(jsonDirectory)
        if (!directory.exists()) {
            return [
                    success: false,
                    status: 404,
                    error: [
                            code: 'TIMETABLE_DIRECTORY_NOT_FOUND',
                            message: 'Timetable JSON storage directory does not exist.'
                    ]
            ]
        }

        File jsonFile = new File(
                directory,
                "${className}.json"
        )

        if (!jsonFile.exists() || !jsonFile.isFile()) {
            return [
                    success: false,
                    status: 404,
                    error: [
                            code: 'TIMETABLE_NOT_FOUND',
                            message: "No timetable found for class '${className}'."
                    ]
            ]
        }

        try {
            def data = new JsonSlurper().parse(jsonFile)

            return [
                    success: true,
                    status: 200,
                    data: data
            ]

        } catch (Exception e) {
            return [
                    success: false,
                    status: 500,
                    error: [
                            code: 'INVALID_JSON',
                            message: 'Timetable JSON could not be read.'
                    ]
            ]
        }
    }
}