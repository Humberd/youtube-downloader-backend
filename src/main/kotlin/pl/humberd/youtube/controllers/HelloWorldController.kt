package pl.humberd.youtube.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController()
class HelloWorldController {

    @GetMapping("/", produces = arrayOf("text/html"))
    fun helloWorld(): String {
        return """
            <h2>Hello from Spring!</h2>
            <div>Container Id: ${System.getenv("HOSTNAME")}</div>
            <div>Build number: ${System.getenv("BUILD_NO")}</div>
            <hr>
            <div>
                <pre>${System.getenv("COMMIT")}</pre>
            </div>
            """
    }
}