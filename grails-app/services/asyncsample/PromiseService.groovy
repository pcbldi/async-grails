package asyncsample

import grails.transaction.Transactional

import static grails.async.Promises.task
import static grails.async.Promises.waitAll

@Transactional
class PromiseService {


   def printZipCodesInfo() {
        def t=["74172", "64840", "64841", "64842", "64843"].collect {z->
            task {
                def response = new URL("http://zip.getziptastic.com/v2/US/$z").content.text
                def json = grails.converters.JSON.parse(response)
                json.state as String
            }
        }
       waitAll(t)
    }

    def getFactorialForVeryLargeNumber(BigDecimal num){
       (1..num).inject(1, {fact,i->(fact*i) as BigDecimal})
    }

    def getPromiseOfFactorialForVeryLargeNumber(BigDecimal num){
        task{
            getFactorialForVeryLargeNumber(num)
        }
    }

    def countNumberOfZeroesInFactorial(def num){
        task{
            "${getPromiseOfFactorialForVeryLargeNumber(num).get()}".count("0")
        }
    }

}
