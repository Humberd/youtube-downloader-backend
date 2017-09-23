package pl.humberd.youtube.controllers

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController()
class HelloWorldController {

    @GetMapping("/", produces = arrayOf("text/html"))
    fun helloWorld(): String {
        return """
            <h2>Hello from Spring!</h2>
            <div>Container Id: ${System.getenv("HOSTNAME")}</div>
            """
    }
}