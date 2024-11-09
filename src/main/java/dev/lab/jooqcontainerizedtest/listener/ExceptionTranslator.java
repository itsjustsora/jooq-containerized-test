package dev.lab.jooqcontainerizedtest.listener;

import org.jooq.ExecuteContext;
import org.jooq.ExecuteListener;
import org.jooq.SQLDialect;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;

/**
 * Spring에서 데이터베이스 오류를 다룰 때의 일관된 예외 형식을 사용하기 위해
 * jOOQ의 ExecuteListener 인터페이스 구현
 */
public class ExceptionTranslator implements ExecuteListener {

	/**
	 * jOOQ 실행 시 발생하는 예외를 Spring에서 사용하는 DataAccessException 형식으로 변환해주는 설정
	 * @param context
	 */
	public void exception(ExecuteContext context) {
		SQLDialect dialect = context.configuration().dialect();

		SQLExceptionTranslator translator = new SQLErrorCodeSQLExceptionTranslator(dialect.name());
		context.exception(translator.translate(
			"Access database using jOOQ",
			context.sql(),
			context.sqlException())
		);
	}
}
