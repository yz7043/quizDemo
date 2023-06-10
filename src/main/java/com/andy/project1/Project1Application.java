package com.andy.project1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan // this annotation will help you scan filter
public class Project1Application {

    public static void main(String[] args) {
        SpringApplication.run(Project1Application.class, args);
    }

}
