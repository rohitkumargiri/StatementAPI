package net.cerner.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.cerner.dto.StatementRequestDTO;
import net.cerner.entity.Statement;
import net.cerner.mapper.StatementMapper;
import net.cerner.repository.StatementRepo;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StatementServiceTest {	

	@Mock
	private StatementRepo stmtrepo;

	@InjectMocks
	private StatementMapper stmtMapper;


	@Test
	public void getStatementIdTest() throws Exception{
		Optional<Statement> stmt = Optional.ofNullable(Statement.builder().id("test").recordName("test").build());
		when(stmtrepo.findById(Mockito.anyString())).thenReturn(stmt);
		StatementRequestDTO response = stmtMapper.toDto(stmt.get());
		Assert.assertEquals("test", response.getRecordName());
	}
}
