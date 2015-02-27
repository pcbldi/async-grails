package asyncsample

import static grails.async.Promises.task
import static grails.async.Promises.waitAll


class PromiseController {

    def promiseService


    def fact(Long id){
       Date date= new Date()
       def result= promiseService.getFactorialForVeryLargeNumber(id)
        Date endDate=new Date()
        render "<br/> Method Returned in  - ${(endDate.time-date.time)/(1000)} Sec"
        render "<br/> Result is  - ${result}"
    }

    def pfact(Long id){
        Date date= new Date()
        def result= promiseService.getPromiseOfFactorialForVeryLargeNumber(id)
        Date endDate=new Date()
        render "<br/> Method Returned in  - ${(endDate.time-date.time)/(1000)} Sec"
        result.onComplete {render "Found Factorial........"}
        result.onComplete {render "Error Factorial........"}
        render "<br/> Result is  - ${result.get()}"
    }


    def index() { }

    def zip(){
      render(promiseService.printZipCodesInfo())
    }

    def processText(){
        render 'Starting stuff'
       def tasks=readFileContent()
        println '>>'
        tasks.each{
            it.then{String str-> countCharactersTask(str)}.then{String str-> containsWordTask(str,'Exception')}
        }
        waitAll(tasks)
        render "done"
    }

    Closure countCharactersTask={str->
        render str.count()
    }

    Closure containsWordTask={str,word->
        render str.contains(word)
    }


    String getFileContent(){
        File file=new File("/home/sa1nt/app_log")
        file.text
    }

    def readFileContent(){
        getFileContent().split("\\r?\\n").collect{String line->
            task{return(line)}}
    }

}
