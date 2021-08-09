# sec 네임스페이스

***

## Sec 네임스페이스 등록

***

~~~
xmlns:sec="http://www.thymeleaf.org/extras/spring-security"
~~~

***

## Sec 네임스페이스 사용하기

***

~~~html
<div sec:authorize="isAuthenticated()">
    <h2 sec:authentication="name">Name</h2>
    <a href="/logout" th:href="@{/logout}">Logout</a>
</div>
<div sec:authorize="!isAuthenticated()">
    <a href="/login" th:href="@{/login}">Login</a>
</div>
~~~