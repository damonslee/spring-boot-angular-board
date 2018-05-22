package com.tram.springbootangularboard;

import com.tram.springbootangularboard.domain.Account;
import com.tram.springbootangularboard.domain.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringBootAngularBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAngularBoardApplication.class, args);
	}
	@Bean
	CommandLineRunner bootstrapTestAccount(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			Account account = new Account();
			account.setPassword(passwordEncoder.encode("디지몬"));
			accountRepository.save(account);
		};
	}
}
