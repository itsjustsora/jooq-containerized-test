package dev.lab.jooqcontainerizedtest.batch;

import static dev.lab.jooqcontainerizedtest.public_.tables.Member.*;

import org.jooq.DSLContext;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import dev.lab.jooqcontainerizedtest.domain.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MemberItemWriter implements ItemWriter<Member> {

	private final DSLContext dslContext;

	public MemberItemWriter(DSLContext dslContext) {
		this.dslContext = dslContext;
	}

	@Override
	public void write(Chunk<? extends Member> chunk) {
		for (Member member : chunk) {
			dslContext.insertInto(MEMBER)
				.set(MEMBER.FIRST_NAME, member.firstName())
				.set(MEMBER.LAST_NAME, member.lastName())
				.set(MEMBER.EMAIL, member.email())
				.execute();
		}
	}
}
