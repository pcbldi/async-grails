import asyncsample.Person

class BootStrap {

    def peronService
    def init = { servletContext ->
        (1..500).each { new Person(name: "Prakash - ${it}", age: it).save(failOnError: true, flush: true) }
    }

    def destroy = {
    }
}
