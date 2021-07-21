package com.setge.springsecuritystudy.form;

import static org.springframework.util.ObjectUtils.isEmpty;

import com.setge.springsecuritystudy.account.AccountContext;
import com.setge.springsecuritystudy.account.AccountRepository;
import com.setge.springsecuritystudy.common.SecurityLogger;
import java.security.Principal;
import java.util.concurrent.Callable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

  @Autowired
  private SampleService sampleService;

  @Autowired
  private AccountRepository accountRepository;


  @GetMapping("/")
  public String index(Model model, Principal principal) {
    if (isEmpty(principal)) {
      model.addAttribute("message", "Hello Spring Security");
    } else {
      model.addAttribute("message", "Hello, " + principal.getName());
    }
    return "index";
  }

  @GetMapping("/info")
  public String info(Model model) {
    model.addAttribute("message", "Info");
    return "info";
  }

  @GetMapping("/dashboard")
  public String dashboard(Model model, Principal principal) {
    model.addAttribute("message", "Hello " + principal.getName());
    AccountContext.setAccount(accountRepository.findByUsername(principal.getName()));
    sampleService.dashboard();
    return "dashboard";
  }

  @GetMapping("/admin")
  public String admin(Model model, Principal principal) {
    model.addAttribute("message", "Hello Admin, " + principal.getName());
    return "admin";
  }

  @GetMapping("/user")
  public String user(Model model, Principal principal) {
    model.addAttribute("message", "Hello User, " + principal.getName());
    return "user";
  }

  @GetMapping("/async-handler")
  @ResponseBody // 응답에 대한 요청값을 body에 실어 보냄.
  public Callable<String> asyncHandler() {
    SecurityLogger.log("MVC");
    return () -> {
      SecurityLogger.log("Callable");
      return "Async Handler";
    };
  }

  @GetMapping("/async-service")
  @ResponseBody
  public String asyncService() {
    SecurityLogger.log("MVC, before async service");
    sampleService.asyncService();
    SecurityLogger.log("MVC, after async service");
    return "Async Handler";
  }


}
