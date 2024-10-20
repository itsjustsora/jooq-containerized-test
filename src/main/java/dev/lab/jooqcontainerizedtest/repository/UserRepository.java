package dev.lab.jooqcontainerizedtest.repository;

import static java.util.stream.Collectors.*;

import java.time.LocalDateTime;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

	private final DSLContext dslContext;

	public UserRepository(DSLContext dslContext) {
		this.dslContext = dslContext;
	}
}
