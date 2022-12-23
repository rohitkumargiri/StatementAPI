package net.cerner.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.cerner.dto.StatementRequestDTO;
import net.cerner.dto.StatementResponseDTO;
import net.cerner.entity.Model;
import net.cerner.entity.Statement;
import net.cerner.entity.StatementSettingsRelationships;
import net.cerner.repository.StatementRepo;
import net.cerner.service.StatementService;

@SpringBootTest
@AutoConfigureMockMvc
public class StatementControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StatementService service;

	ObjectMapper om = new ObjectMapper();

	@Mock
	StatementRepo repository;

	@Test
	void getStatementTest() throws Exception {
		StatementRequestDTO statement = new StatementRequestDTO();
		statement.setAuthor("rohit");
		statement.setBaseVersion("100");
		when(service.getStatementId("100")).thenReturn(statement);
		mockMvc.perform(get("/statements/search/100").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	void getResponseTest() throws Exception {
		StatementRequestDTO statement = new StatementRequestDTO();
		statement.setAuthor("rohit");
		statement.setBaseVersion("101");
		statement.setCreatedBy("rohit");
		statement.setDateOperations("10-10-2022");

		when(service.getAllStatement()).thenReturn(Arrays.asList(statement));
		mockMvc.perform(get("/statements/allStatements").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void saveStatementTest() throws Exception {
		Statement statement = new Statement();
		statement.setAuthor("rohit");
		statement.setBaseVersion("101");

		String jsonRequest = om.writeValueAsString(statement);
		mockMvc.perform(post("/statements").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	void updateStatementByIdTest() throws Exception {
		StatementResponseDTO statement = new StatementResponseDTO();
		statement.setCreatedBy("rohit");
		statement.setScope("Health");

		String jsonRequest = om.writeValueAsString(statement);
		mockMvc.perform(put("/statements/100", statement).content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	void deleteStatementTest() throws Exception {
		mockMvc.perform(delete("/statements/100").contentType("Deleted Successfully")).andExpect(status().isOk())
				.andReturn();
	}

	@Test
	void getStatementFunctionsByIdTest() throws Exception {
		mockMvc.perform(get("/statements/search/functions/100").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	void getStatementSettingsByIdTest() throws Exception {
		StatementSettingsRelationships sr = new StatementSettingsRelationships();
		sr.setSettingId("100");
		sr.setCreatedBy("rohit");

		when(service.getSettingsById("100")).thenReturn(Optional.of(sr));
		mockMvc.perform(get("/statements/search/settings/100").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
