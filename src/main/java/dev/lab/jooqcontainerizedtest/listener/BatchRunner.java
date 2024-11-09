package dev.lab.jooqcontainerizedtest.listener;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BatchRunner implements CommandLineRunner {

	private final JobLauncher jobLauncher;
	private final Job job;

	public BatchRunner(JobLauncher jobLauncher, Job job) {
		this.jobLauncher = jobLauncher;
		this.job = job;
	}

	/**
	 * 배치 실행
	 * @param args incoming main method arguments
	 */
	@Override
	public void run(String... args) {
		try {
			jobLauncher.run(job, new JobParameters());
		} catch (Exception e) {
			log.error("Exception occurred: {}", e.getMessage(), e);
		}
	}
}
