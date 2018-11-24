package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableOAuth2Sso
@SpringBootApplication
public class DemoApplication {

  public static void main(final String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @RequestMapping("/")
  public SecurityContext helloSecurity() {
    return SecurityContextHolder.getContext();
  }
}
