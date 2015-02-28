package asyncsample

class ReqController {

    def index() {}

    def context(Long id) {
        log.info("Inside action: context.")
        if(id < 1000) {
            log.info("Done Quickly")
            render "Done Quickly"
        } else {
            def ctx = startAsync()
            ctx.start {
                Thread.sleep(id)
                log.info("Long Running")
                render "Long Running"
                ctx.complete()
            }
        }
    }
}
