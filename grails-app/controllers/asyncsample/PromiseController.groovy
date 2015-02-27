package asyncsample


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
        render "<br/> Result is  - ${result.get()}"
    }


    def index() { }

    def zip(){
      render(promiseService.printZipCodesInfo())
    }




}
