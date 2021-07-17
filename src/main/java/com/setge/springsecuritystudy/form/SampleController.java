package com.setge.springsecuritystudy.form;

import static org.springframework.util.ObjectUtils.isEmpty;

import com.setge.springsecuritystudy.account.AccountContext;
import com.setge.springsecuritystudy.account.AccountRepository;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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


}
