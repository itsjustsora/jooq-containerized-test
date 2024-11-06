package dev.lab.jooqcontainerizedtest.config;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import dev.lab.jooqcontainerizedtest.domain.Member;

@Configuration
public class ImportCsvConfiguration {

	/**
	 * 지정한 파일을 찾아 각 라인을 파싱하고, 데이터를 지정한 객체로 변환
	 * @return
	 */
	@Bean
	public FlatFileItemReader<Member> fileReader() {
		return new FlatFileItemReaderBuilder<Member>()
			.name("memberItemReader") 								// 고유한 이름 설정
			.resource(new ClassPathResource("sample-data.csv"))		// 읽어올 파일의 경로 지정
			.delimited()											// 파일이 구분자로 분리되어 있음을 지정
			.names("firstName", "lastName", "email")				// 파일의 각 열 이름 지정, 이후 매핑할 필드 이름과 일치시킴
			.targetType(Member.class)								// 각 레코드를 매핑할 대상 객체 타입 지정
			.build();
	}
}
