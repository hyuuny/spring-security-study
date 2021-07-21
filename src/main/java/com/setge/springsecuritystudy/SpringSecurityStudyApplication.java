package com.setge.springsecuritystudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableAsync
public class SpringSecurityStudyApplication {

	@Bean
	public PasswordEncoder passwordEncoder() {
		// 애플리케이션 로딩 시점에 주입 받아 비밀번호 암호화 가능
		// 5버전 부터 NoOpPasswordEncoder이 아니라,
		// PasswordEncoderFactories.createDelegatingPasswordEncoder() 사용
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityStudyApplication.class, args);
	}

}
