package dev.lab.jooqcontainerizedtest.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import dev.lab.jooqcontainerizedtest.batch.JobCompletionNotificationListener;
import dev.lab.jooqcontainerizedtest.batch.MemberItemProcessor;
import dev.lab.jooqcontainerizedtest.batch.MemberItemWriter;
import dev.lab.jooqcontainerizedtest.domain.Member;

@Configuration
@Import({ImportCsvConfiguration.class, MemberItemWriter.class})
@EnableBatchProcessing
public class BatchConfiguration {

	@Bean
	public MemberItemProcessor processor() {
		return new MemberItemProcessor();
	}

	@Bean
	public Job importCsvToMemberJob(JobRepository jobRepository, Step step1, JobCompletionNotificationListener listener) {
		return new JobBuilder("importCsvToMemberJob", jobRepository)
			.listener(listener)
			.start(step1)
			.build();
	}

	@Bean
	public Step importMemberCsvStep(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
		FlatFileItemReader<Member> reader, MemberItemProcessor processor, MemberItemWriter writer) {
		return new StepBuilder("importMemberCsvStep", jobRepository)
			.<Member, Member> chunk(3, transactionManager)
			.reader(reader)
			.processor(processor)
			.writer(writer)
			.build();
	}
}
