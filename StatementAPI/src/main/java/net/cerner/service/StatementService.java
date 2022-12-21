package net.cerner.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import net.cerner.dto.StatementRequestDTO;
import net.cerner.dto.StatementResponseDTO;
import net.cerner.entity.PrimaryConcepts;
import net.cerner.entity.Statement;
import net.cerner.entity.StatementFunctionsRelationships;
import net.cerner.entity.StatementSettingsRelationships;

@Service
public interface StatementService {

	public Optional<StatementSettingsRelationships> getSettingsById(String id);
	public StatementRequestDTO getStatementId(String id);
	public Optional<StatementFunctionsRelationships> getFunctionsById(String id); 
	public Optional<PrimaryConcepts> getContextById(String contextId,String conceptAlias);
	
	public StatementResponseDTO createStatement(StatementRequestDTO statementDTO);
	public StatementResponseDTO updateStatement(String statementId,StatementRequestDTO statementDTO);
	public String deleteStatementById(String statementId);
	
	public List<StatementRequestDTO> getAllStatement();
	
}
