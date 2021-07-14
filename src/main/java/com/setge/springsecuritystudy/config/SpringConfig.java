package com.setge.springsecuritystudy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringConfig extends WebSecurityConfigurerAdapter {


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/", "/info", "/account/**").permitAll() // 누구나 접근 가능
        .mvcMatchers("/admin").hasRole("ADMIN") // 어드민만 접근 가능
        .anyRequest().authenticated(); // anyRequest().authenticated() <- 그 외 인증만 하면 접근 가능
    http.formLogin();
    http.httpBasic();
  }

}
