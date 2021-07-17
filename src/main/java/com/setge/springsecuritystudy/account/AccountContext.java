package com.setge.springsecuritystudy.account;

public class AccountContext {

  /**
   * ThreadLocal
   * - 같은 쓰레드내에서만 공유
   * - 따라서 같은 쓰레드라면 해당 데이터를 메소드 매개변수로 넘겨줄 필요 없음
   * - SecurityContextHolder의 기본 전략
   */
  private static final ThreadLocal<Account> ACCOUNT_THREAD_LOCAL = new ThreadLocal<>();

  public static void setAccount(Account account) {
    ACCOUNT_THREAD_LOCAL.set(account);
  }

  public static Account getAccount() {
    return ACCOUNT_THREAD_LOCAL.get();
  }

}
