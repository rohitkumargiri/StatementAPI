package net.cerner.mapper;

import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Component;

import net.cerner.dto.StatementRequestDTO;
import net.cerner.entity.Model;
import net.cerner.entity.Statement;

@Component
public class StatementMapper {

	public Statement toEntity(StatementRequestDTO statementDTO) {
		 
		String id=UUID.randomUUID()+statementDTO.getVersion()+statementDTO.getAuthor();
		return Statement.builder()
				.id(id)
				.scopeName(statementDTO.getScope())
				.recordName(statementDTO.getRecordName())
				.statementAlias(statementDTO.getStatementAlias())
				.statementDisplay(statementDTO.getStatementDisplay())
				.statementText(statementDTO.getStatementText())
				.statementDependencyAlias(statementDTO.getStatementDependencyAlias())
				.recordOperations(statementDTO.getRecordOperations())
				.dateOperations(statementDTO.getDateOperations())
				.requiredOperations(statementDTO.getRequiredOperations())
				.selector(statementDTO.getSelector())
				.cardinality(statementDTO.getCardinality())
				.withDistinct(statementDTO.getWithDistinct())
				.negateStatement(statementDTO.isNegateStatement())
				.frequencyType(statementDTO.getFrequencyType())
				.frequencyValue(statementDTO.getFrequencyValue())
				.status(statementDTO.getStatus())
				.version(statementDTO.getVersion())
				.baseVersion(statementDTO.getBaseVersion())
				.author(statementDTO.getAuthor())
				.createdAt(statementDTO.getCreatedAt())
				.createdBy(statementDTO.getCreatedBy())
				.updatedAt(statementDTO.getUpdatedAt())
				.updatedBy(statementDTO.getUpdatedBy())
				.build();
	}
	
	public StatementRequestDTO toDto(Statement statement) {
	
		return StatementRequestDTO.builder().recordName(statement.getRecordName()).build();
	}
}
