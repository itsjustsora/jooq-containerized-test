package dev.lab.jooqcontainerizedtest.repository;

import static org.jooq.Records.*;
import static org.jooq.generated.tables.Member.*;

import java.util.Optional;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import dev.lab.jooqcontainerizedtest.domain.Member;
import dev.lab.jooqcontainerizedtest.dto.MemberInfoDTO;

@Repository
public class MemberRepository {

	private final DSLContext dslContext;

	public MemberRepository(DSLContext dslContext) {
		this.dslContext = dslContext;
	}

	public MemberInfoDTO createMember(Member member) {
		return this.dslContext
			.insertInto(MEMBER)
			.set(MEMBER.FIRST_NAME, member.firstName())
			.set(MEMBER.LAST_NAME, member.lastName())
			.set(MEMBER.EMAIL, member.email())
			.returningResult(MEMBER.FIRST_NAME, MEMBER.LAST_NAME, MEMBER.EMAIL)
			.fetchOneInto(MemberInfoDTO.class);
	}

	public Optional<Member> getMemberByEmail(String email) {
		return this.dslContext
			.select(
				MEMBER.ID,
				MEMBER.FIRST_NAME,
				MEMBER.LAST_NAME,
				MEMBER.EMAIL
			)
			.from(MEMBER)
			.where(MEMBER.EMAIL.equal(email))
			.fetchOptionalInto(Member.class);
	}
}
