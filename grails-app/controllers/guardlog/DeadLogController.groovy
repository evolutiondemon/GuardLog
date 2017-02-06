package guardlog

import org.joda.time.*
class DeadLogController {

    def index() {


    }

    def doParse() {

        def deadDate
        def deadIp
        def logfile = new File("d:/dev/openswan.20170122_040208.mlcdc02.mlcnet.local.txt")
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

                    //SimpleDateFormat format = new SimpleDateFormat("MMM dd yyyy HH:mm")
                    //Date parsed = format.parse(deadDate.group())
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

        render "Done!"
    }

    def dropsByDate() {


        def maxDate = DeadLog.list(max: 1, offset: 0, sort: "dateDead", order: "desc").dateDead[0]
        def minDate = new DateTime(maxDate).minusDays(7).toDate()
        [maxDate: maxDate,minDate: minDate]
    }

    def maxDate() {
        def maxDate = DeadLog.list(max: 1, offset: 0, sort: "dateDead", order: "desc").dateDead
        [maxDate: maxDate]


    }

    def getDrops() {

        def drop = DeadLog.executeQuery("select dateDead, count(*) from DeadLog group by dateDead order by 1")


        drop.each {
            println it
        }
        [dropped: drop]

    }
    def showDrops() {

        def drop = DeadLog.executeQuery("select dateDead, count(*) from DeadLog group by dateDead order by 1")


        drop.each {
            println it
        }
        [dropped: drop]

    }

}

