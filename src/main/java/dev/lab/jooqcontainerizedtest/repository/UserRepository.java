package dev.lab.jooqcontainerizedtest.repository;

import static org.jooq.Records.*;
import static org.jooq.generated.tables.Users.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import dev.lab.jooqcontainerizedtest.domain.User;

@Repository
public class UserRepository {

	private final DSLContext dslContext;

	public UserRepository(DSLContext dslContext) {
		this.dslContext = dslContext;
	}

	public User createUser(User user) {
		return this.dslContext.insertInto(USERS)
			.set(USERS.NAME, user.name())
			.set(USERS.EMAIL, user.email())
			.set(USERS.REG_DATE, LocalDateTime.now())
			.returningResult(USERS.ID, USERS.NAME, USERS.EMAIL)
			.fetchOne(mapping(User::new));
	}

	public Optional<User> getUserByEmail(String email) {
		return this.dslContext.select(USERS.ID, USERS.NAME, USERS.EMAIL)
			.from(USERS)
			.where(USERS.EMAIL.equalIgnoreCase(email))
			.fetchOptional(mapping(User::new));
	}
}
