package com.setge.springsecuritystudy.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

  Account findByUsername(final String username);

}
