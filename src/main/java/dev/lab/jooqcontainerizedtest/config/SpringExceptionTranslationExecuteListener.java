package dev.lab.jooqcontainerizedtest.config;

import java.util.Objects;

import org.jooq.ExecuteContext;
import org.jooq.ExecuteListener;
import org.jooq.SQLDialect;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.jdbc.support.SQLStateSQLExceptionTranslator;

public class SpringExceptionTranslationExecuteListener implements ExecuteListener {

	@Override
	public void exception(ExecuteContext context) {
		SQLDialect dialect = context.configuration().dialect();
		SQLExceptionTranslator translator = getSqlExceptionTranslator(dialect);
		DataAccessException dataAccessException = translator.translate("Data Access using jOOQ", context.sql(), context.sqlException());
		DataAccessException translation = Objects.requireNonNullElseGet(
			dataAccessException, () -> new UncategorizedSQLException("translation of exception", context.sql(), context.sqlException())
		);

		context.exception(translation);
	}

	private SQLExceptionTranslator getSqlExceptionTranslator(SQLDialect dialect) {
		return (dialect != null)
			? new SQLErrorCodeSQLExceptionTranslator(dialect.name())
			: new SQLStateSQLExceptionTranslator();
	}
}
