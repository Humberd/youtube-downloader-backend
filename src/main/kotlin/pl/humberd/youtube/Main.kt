package pl.humberd.youtube

import mu.KLogging
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Main

private var logger = KLogging().logger()

fun main(args: Array<String>) {
    SpringApplication.run(Main::class.java, *args)
    logger.info { "Hello from Main Function" }

}
//    val url = "https://www.youtube.com/watch?v=sI420QgLL64";
//    val path = "D:\\inspector gadget";
//
//    AppManagedDownload.downloadVideo("https://www.youtube.com/watch?v=sI420QgLL64&list=PLvFEJbMqWahVwOTF0cqnpemb_xZmn5Nmw", path)
//}