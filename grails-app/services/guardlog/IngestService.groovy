package guardlog

import grails.transaction.Transactional

@Transactional
class IngestService {

    def watchDir() {
        def deadDate
        def deadIp
        def ipDead
        def saveDate
        def deadIpMatcher = /(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\s/
        def dateMatcher = /\D{3}\s\d{2}\s\d{2}\:\d{2}/
        new File("d:/dev/ingest").eachFile { file ->


            file.eachLine { line ->
                println line
                deadDate = (line =~ /$dateMatcher/)
                deadIp = (line =~ /$deadIpMatcher/)
                deadDate.find()
                if (deadIp.find()) {
                    def preParse = deadDate.group() + " 2017"
                    saveDate = Date.parse("MMM dd HH:mm yyyy", preParse)
                    ipDead = deadIp.group()
                }

                println "$saveDate" + "---" + "$ipDead"

            }

        }
    }
/*
    def doParse(String logFile) {

        def deadDate
        def deadIp
        def logfile = new File(logFile)
        def deadIpMatcher = /(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\s/
        def dateMatcher = /\D{3}\s\d{2}\s\d{2}\:\d{2}/
        def extracted = []


        println "Parsing the file for declaring"
        logfile.eachLine { line ->
            if (line.contains('declaring')) {
                extracted << line
            }

            extracted.each { eline ->
                deadDate = (eline =~ /$dateMatcher/)
                deadIp = (eline =~ /$deadIpMatcher/)
                deadDate.find()
                if (deadIp.find()) {
                    def preParse = deadDate.group() + " 2017"
                    def saveDate = Date.parse("MMM dd HH:mm yyyy", preParse)

                    def zombie = new DeadLog()
                    zombie.dateDead = saveDate
                    zombie.ipDead = deadIp.group()
                    zombie.save(flush: true)
                    if (!zombie.save())
                        zombie.errors.allErrors.each {
                            println it
                        }


                }
            }


        }

    }
    */

}