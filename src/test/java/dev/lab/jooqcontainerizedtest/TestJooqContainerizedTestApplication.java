package dev.lab.jooqcontainerizedtest;

import org.springframework.boot.SpringApplication;

public class TestJooqContainerizedTestApplication {

	public static void main(String[] args) {
		SpringApplication.from(JooqContainerizedTestApplication::main)
			.with(TestcontainersConfiguration.class)
			.run(args);
	}

}
