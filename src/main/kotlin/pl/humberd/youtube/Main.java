package pl.humberd.youtube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

//    val url = "https://www.youtube.com/watch?v=sI420QgLL64";
//    val path = "D:\\inspector gadget";
//
//    AppManagedDownload.downloadVideo("https://www.youtube.com/watch?v=sI420QgLL64&list=PLvFEJbMqWahVwOTF0cqnpemb_xZmn5Nmw", path)
//}