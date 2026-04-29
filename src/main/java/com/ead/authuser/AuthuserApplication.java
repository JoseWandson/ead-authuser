package com.ead.authuser;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode;

import java.util.TimeZone;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = PageSerializationMode.VIA_DTO)
public class AuthuserApplication {

    static void main(String[] args) {
        SpringApplication.run(AuthuserApplication.class, args);
    }

    @PostConstruct
    private void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

}
