package asyncsample

import static grails.async.Promises.waitAll


class PromiseController {

    def promiseService

    def index() { }

    def zip(){
      render(promiseService.printZipCodesInfo())
    }

}
