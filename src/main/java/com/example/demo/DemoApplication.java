package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.auth.DemoUserDetailsService;
import com.example.demo.auth.User;

@RestController
@SpringBootApplication
public class DemoApplication {

  @Autowired
  private DemoUserDetailsService userDetailsService;

  public static void main(final String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @RequestMapping("/")
  public SecurityContext helloSecurity() {
    return SecurityContextHolder.getContext();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(encoder());
    return authProvider;
  }

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder(11);
  }

  @EventListener(ApplicationReadyEvent.class)
  public void insertDefaultUser() {
    final User user = new User();
    user.setUsername("javacasts");
    user.setPassword(encoder().encode("secret"));
    userDetailsService.save(user);
  }

}
