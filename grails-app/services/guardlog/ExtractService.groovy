package guardlog

import grails.transaction.Transactional

@Transactional
class ExtractService {

    def extractText(File fileName, String guardName) {
        def iPath = "d:/dev/ingest/"
        def newName = "$iPath" + "$guardName" + ".extracted.log"
        def newFile = new File(newName).canonicalFile
        fileName.eachLine { line ->
            if (line.contains('declaring')) {
                newFile << line
            }

        }

    }
}
