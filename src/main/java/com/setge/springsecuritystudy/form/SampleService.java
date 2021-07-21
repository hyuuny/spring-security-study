package com.setge.springsecuritystudy.form;

import com.setge.springsecuritystudy.common.SecurityLogger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

  public void dashboard() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    System.out.println("=======================");
    System.out.println(userDetails.getUsername());
  }

  @Async // 별도의 Thread를 만들어서 비동기로 호출해준다.
  public void asyncService() {
    SecurityLogger.log("Async Service");
    System.out.println("Async service is called.");
  }
}
