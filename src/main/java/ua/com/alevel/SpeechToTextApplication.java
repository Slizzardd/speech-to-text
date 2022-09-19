package ua.com.alevel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.com.alevel.service.SpeechToText;

@SpringBootApplication
public class SpeechToTextApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpeechToTextApplication.class, args);
    }
}
