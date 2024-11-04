package dev.lab.jooqcontainerizedtest.repository;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import dev.lab.jooqcontainerizedtest.domain.Member;
import dev.lab.jooqcontainerizedtest.dto.MemberInfoDTO;
import dev.lab.jooqcontainerizedtest.utility.TestcontainersConfiguration;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
@Sql("/test-data.sql")
public class MemberRepositoryJooqTest {

	@Autowired
	MemberRepository memberRepository;

	@Test
	void shouldCreateUserSuccessfully() {
		Member member = new Member(101L, "Zeb", "Gatward", "zgatward7@gmail.com");

		MemberInfoDTO savedMember = memberRepository.createMember(member);

		assertThat(savedMember.firstName()).isEqualTo("Zeb");
		assertThat(savedMember.lastName()).isEqualTo("Gatward");
		assertThat(savedMember.email()).isEqualTo("zgatward7@gmail.com");
	}

	@Test
	void shouldGetUserByEmail() {
		Member member = memberRepository.getMemberByEmail("stollmeier@gmail.com").orElseThrow();

		System.out.println(member.firstName() + member.lastName() + member.email());

		assertThat(member.id()).isEqualTo(1L);
		assertThat(member.firstName()).isEqualTo("Simonne");
		assertThat(member.lastName()).isEqualTo("Stollmeier");
		assertThat(member.email()).isEqualTo("stollmeier@gmail.com");
	}
}
