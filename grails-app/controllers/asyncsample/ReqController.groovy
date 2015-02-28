package asyncsample

import static grails.async.Promises.*

class ReqController {

    def index() {}

    def context(Long id) {
        log.info("Inside action: context.")
        if (id < 1000) {
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

    def persons() {
        tasks books: Person.async.list(),
                totalBooks: Person.async.count()
    }

    def sticker(String ticker) {
        task {
            ticker = ticker ?: 'GOOG'
            def url = new URL("http://download.finance.yahoo.com/d/quotes.csv?s=${ticker}&f=nsl1op&e=.csv")
            Double price = url.text.split(',')[-1] as Double
            render "ticker: $ticker, price: $price"
        }
    }
}
