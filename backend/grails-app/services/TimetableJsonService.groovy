package backend

import groovy.json.JsonSlurper

class TimetableJsonService {

    String getJsonDirectory() {
        return "${System.getProperty('user.dir')}/../storage/timetable-json"
    }

    String cleanFilename(String className) {
        return className.replaceAll(/\s+/, "_") + ".json"
    }

    Map getTimetable(String className) {

        String filename = cleanFilename(className)
        File jsonFile = new File(getJsonDirectory(), filename)

        if (!jsonFile.exists()) {
            return null
        }

        def json = new JsonSlurper().parse(jsonFile)

        return json as Map
    }
}