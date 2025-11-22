package br.com.fiap.chatia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ChatIaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatIaApplication.class, args);
    }

}
