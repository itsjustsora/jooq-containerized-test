package dev.lab.jooqcontainerizedtest.domain;

import lombok.Builder;

@Builder
public record Member(
	Long id,
	String firstName,
	String lastName,
	String email
) {
}
