package guardlog

import grails.transaction.Transactional

@Transactional
class MonitorService {
def ExtractService

    def getFiles() {
        def path
        def guardNameMatcher = /(mlcdc)\d[0-2]/
        def findGuardName
        def guardName
        def pattern = "^openswan.+"

        path = new File("d:/dev/ingest/")
        def filter = new FilenameFilter() {
            @Override
            boolean accept(File dir, String name) {
                return name.matches(pattern)
            }
        }

        path.list(filter).each {
            findGuardName = (it =~ /$guardNameMatcher/)
            findGuardName.find()
            guardName = findGuardName.group()
            ExtractService.extractText(it, guardName)
        }
    }

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
