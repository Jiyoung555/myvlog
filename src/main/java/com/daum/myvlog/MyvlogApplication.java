package com.daum.myvlog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MyvlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyvlogApplication.class, args);
	}

}
