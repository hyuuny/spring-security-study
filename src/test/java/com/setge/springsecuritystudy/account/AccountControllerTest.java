package com.setge.springsecuritystudy.account;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@Transactional // javax Transactional 권장
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  AccountService accountService;


  @Test
  @WithAnonymousUser
  public void index_anonymous() throws Exception {
    mockMvc.perform(get("/")) // import : MockMvcRequestBuilders.get
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  @WithUser
  public void index_user() throws Exception {
    // seonghyun이라는 유저가 로그인을 한 상태라고 가정을 하는 것.
    // 그랬을 때 응답이 어떻게 나올 것인가
    mockMvc.perform(get("/"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  @WithUser
  public void admin_user() throws Exception {
    mockMvc.perform(get("/admin"))
        .andDo(print())
        .andExpect(status().isForbidden());
  }

  @Test
  @WithAdmin
  public void admin_admin() throws Exception {
    mockMvc.perform(get("/admin"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void login_success() throws Exception {
    String username = "seonghyun";
    String password = "123";
    Account user = createUser(username, password);
    mockMvc.perform(formLogin().user(user.getUsername()).password("123"))
        .andExpect(authenticated());
  }

  @Test
  public void login_fail() throws Exception {
    String username = "seonghyun";
    String password = "123";
    Account user = createUser(username, password);
    mockMvc.perform(formLogin().user(user.getUsername()).password("123123"))
        .andExpect(unauthenticated());
  }

  private Account createUser(String username, String password) {
    Account account = new Account();
    account.setUsername(username);
    account.setPassword(password);
    account.setRole("USER");
    return accountService.createNew(account);
  }

}