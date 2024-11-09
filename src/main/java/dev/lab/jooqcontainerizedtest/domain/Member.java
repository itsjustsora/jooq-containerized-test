package dev.lab.jooqcontainerizedtest.domain;

import lombok.Builder;

@Builder
public record Member(
	String firstName,
	String lastName,
	String email
) {
}
