# @AuthenticationPrincipal

***

## 웹 MVC 핸들러 아규먼트로 Principal 객체를 받을 수 있다.

***

- 커스텀 유저 클래스 구현
~~~java
public class UserAccount extends User {

    private Account account;

    public UserAccount(Account account) {
        super(account.getUsername(), account.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_" + account.getRole())));
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
}
~~~

***

- AccountService 수정
~~~java
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }

        return new UserAccount(account);
    }
~~~

***

## @AuthenticationPrincipal 애노테이션 적용 예제

***

### 예제 1

~~~java
@AuthenticationPrincipal UserAccount userAccount
~~~
- 익명 Authentication인 경우 (“anonymousUser”)에는 null 아닌 경우에는 account 필드를 사용한다.
- Account를 바로 참조할 수 있다.

### 예제 2
~~~java
@CurrentUser Account account
~~~
- @AuthenticationPrincipal을 메타 애노테이션으로 사용하여 커스텀 애노테이션을 만들어 쓸 수 있다.


### @CurrentUser
~~~java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : account")
public @interface CurrentUser {
}
~~~








