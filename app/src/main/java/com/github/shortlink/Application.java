package com.github.shortlink;

import com.github.shortlink.adapter.in.AuthController;
import com.github.shortlink.adapter.in.LinkController;
import com.github.shortlink.adapter.in.UsersController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@SpringBootApplication
// We use direct @Import instead of @ComponentScan to speed up cold starts
// @ComponentScan(basePackages = "my.service.controller")
@Import({UsersController.class, AuthController.class, LinkController.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}