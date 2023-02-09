package com.garanti.SpringBootRestJDBC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication//(exclude = TransactionAutoConfiguration.class)
public class SpringBootRestJdbcApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(SpringBootRestJdbcApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}