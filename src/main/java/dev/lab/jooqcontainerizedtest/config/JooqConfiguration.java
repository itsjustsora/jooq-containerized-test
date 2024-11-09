package dev.lab.jooqcontainerizedtest.config;

import javax.sql.DataSource;

import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class JooqConfiguration {

	@Autowired
	private DataSource dataSource;

	/**
	 * 자동 구성된 DataSource 빈을 필드에 주입
	 * @return
	 */
	@Bean
	public DataSourceConnectionProvider connectionProvider() {
		return new DataSourceConnectionProvider(new TransactionAwareDataSourceProxy(dataSource));
	}

	/**
	 * DSLContext
	 * @return
	 */
	@Bean
	public DefaultDSLContext dslContext() {
		return new DefaultDSLContext(configuration());
	}

	public DefaultConfiguration configuration() {
		DefaultConfiguration jooqConfiguration = new DefaultConfiguration();

		jooqConfiguration.set(connectionProvider());
		jooqConfiguration.set(new DefaultExecuteListenerProvider(new SpringExceptionTranslationExecuteListener()));

		return jooqConfiguration;
	}
}
