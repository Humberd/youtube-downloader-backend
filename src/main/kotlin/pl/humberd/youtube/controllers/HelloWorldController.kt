package pl.humberd.youtube.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController()
class HelloWorldController {

    @GetMapping("/")
    fun helloWorld(): String {
        return "Hello from Spring!!! This is a container id: ${System.getenv("HOSTNAME")}"
    }
}