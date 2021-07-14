package com.setge.springsecuritystudy.account;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.security.test.context.support.WithMockUser;

@Retention(RetentionPolicy.RUNTIME)
@WithMockUser(username = "admin", roles = "ADMIN")
public @interface WithAdmin {

}
