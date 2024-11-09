package dev.lab.jooqcontainerizedtest.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DatabaseConfiguration {

	@Value("${spring.datasource.url}")
	private String databaseUrl;

	@Bean
	public DataSource dataSource() {
		return DataSourceBuilder.create()
			.driverClassName("org.h2.Driver")
			.url(databaseUrl)
			.username("sa")
			.password("")
			.build();
	}

	@Bean
	@Primary
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}
