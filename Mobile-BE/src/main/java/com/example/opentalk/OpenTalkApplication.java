package com.example.opentalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableAsync
@EnableCaching
public class OpenTalkApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OpenTalkApplication.class, args);
    }
}
