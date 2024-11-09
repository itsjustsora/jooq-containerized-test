package dev.lab.jooqcontainerizedtest.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import dev.lab.jooqcontainerizedtest.domain.Member;

public class MemberItemProcessor implements ItemProcessor<Member, Member> {

	private static final Logger log = LoggerFactory.getLogger(MemberItemProcessor.class);

	/**
	 * @param member
	 * @return
	 * @throws Exception
	 */
	@Override
	public Member process(final Member member) {
		final String firstName = member.firstName().toUpperCase();
		final String lastName = member.lastName().toUpperCase();
		final String email = member.email();

		final Member trasformedMember = new Member(firstName, lastName, email);

		log.info("Converting (" + member + ") into (" + trasformedMember + ")");

		return trasformedMember;
	}
}
