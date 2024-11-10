package dev.lab.jooqcontainerizedtest.config;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import dev.lab.jooqcontainerizedtest.domain.Member;
import dev.lab.jooqcontainerizedtest.utility.TestcontainersConfiguration;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
public class ImportCsvConfigurationTest {

	@Autowired
	private FlatFileItemReader<Member> fileItemReader;

	@BeforeEach
	public void init() {
		fileItemReader.open(new ExecutionContext());
	}

	@AfterEach
	public void close() {
		fileItemReader.close();
	}

	@Test
	void whenValidDataFileIsGiven_shouldReadFileSuccessfully() throws Exception {
		List<Member> members = new ArrayList<>();

		Member member;
		while (Objects.nonNull(member = fileItemReader.read())) {
			members.add(member);
		}

		assertFalse(members.isEmpty());

		for (Member mem : members) {
			assertNotNull(mem.firstName());
			assertNotNull(mem.lastName());
			assertNotNull(mem.email());
			assertTrue(mem.email().contains("@"));
		}
	}
}
