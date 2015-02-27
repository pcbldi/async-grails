package asyncsample

import grails.transaction.Transactional

import static grails.async.Promises.task
import static grails.async.Promises.waitAll

@Transactional
class PromiseService {


   def printZipCodesInfo() {
        def t=["74172", "64840", "67202", "68508", "37201"].collect {z->
            task {
                def response = new URL("http://zip.getziptastic.com/v2/US/$z").content.text
                def json = grails.converters.JSON.parse(response)
                json.state as String
            }
        }
       waitAll(t)
    }

}
