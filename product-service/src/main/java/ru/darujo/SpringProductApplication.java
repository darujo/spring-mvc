package ru.darujo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "ru.darujo.*")
@EntityScan(value = "ru.darujo.*")
public class SpringProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringProductApplication.class, args);
	}

}
