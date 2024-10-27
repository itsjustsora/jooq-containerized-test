package dev.lab.jooqcontainerizedtest.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import dev.lab.jooqcontainerizedtest.repository.UserRepository;
import dev.lab.jooqcontainerizedtest.utility.TestcontainersConfiguration;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
@Sql("/test-data.sql")
public class UserRepositoryJooqTest {

	@Autowired
	UserRepository userRepository;

	@Test
	void shouldCreateUserSuccessfully() {
		User user = new User(null, "John", "john@gmail.com");

		User savedUser = userRepository.createUser(user);

		assertThat(savedUser.id()).isNotNull();
		assertThat(savedUser.name()).isEqualTo("John");
		assertThat(savedUser.email()).isEqualTo("john@gmail.com");
	}

	@Test
	void shouldGetUserByEmail() {
		User user = userRepository.getUserByEmail("siva@gmail.com").orElseThrow();

		assertThat(user.id()).isEqualTo(101L);
		assertThat(user.name()).isEqualTo("Siva");
		assertThat(user.email()).isEqualTo("siva@gmail.com");
	}
}
