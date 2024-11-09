package dev.lab.jooqcontainerizedtest.batch;

import static dev.lab.jooqcontainerizedtest.public_.tables.Member.*;

import org.jooq.DSLContext;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private final DSLContext dslContext;

	public JobCompletionNotificationListener(DSLContext dslContext) {
		this.dslContext = dslContext;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (BatchStatus.COMPLETED.equals(jobExecution.getStatus())) {
			log.info("[JOOQ-BATCH] Job finished. It's time to verify the results.");

			dslContext.selectFrom(MEMBER)
				.fetch()
				.forEach(memberRecord -> log.info("[JOOQ-BATCH] Member record: {}", memberRecord));
		}
	}
}
