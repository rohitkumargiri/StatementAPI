package net.cerner.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import net.cerner.dto.StatementRequestDTO;
import net.cerner.dto.StatementResponseDTO;
import net.cerner.entity.AdditionalChecks;
import net.cerner.entity.Model;
import net.cerner.entity.ModifierConcepts;
import net.cerner.entity.PrimaryConcepts;
import net.cerner.entity.Statement;
import net.cerner.entity.StatementDependencies;
import net.cerner.entity.StatementFunctionsRelationships;
import net.cerner.entity.StatementSettingsRelationships;
import net.cerner.exception.ConflictOccuredException;
import net.cerner.exception.StatementNotFoundException;
import net.cerner.mapper.StatementMapper;
import net.cerner.repository.AdditionalChecksRepo;
import net.cerner.repository.ModelRepo;
import net.cerner.repository.ModifierConceptsRepo;
import net.cerner.repository.PrimaryConceptsRepo;
import net.cerner.repository.StatementDependenciesRepo;
import net.cerner.repository.StatementFunctionsRelationshipsRepo;
import net.cerner.repository.StatementRepo;
import net.cerner.repository.StatementSettingsRelationshipsRepo;

@Service
public class StatementServiceImpl implements StatementService {

	@Autowired
	private StatementRepo stmtrepo;

	@Autowired
	private StatementSettingsRelationshipsRepo stmtsettingsrepo;

	@Autowired
	private StatementFunctionsRelationshipsRepo stmtfuncrepo;

	@Autowired
	private StatementDependenciesRepo statmenmtDependency;

	@Autowired
	private ModelRepo modelRepo;

	@Autowired
	private PrimaryConceptsRepo primaryConceptsRepo;

	@Autowired
	private AdditionalChecksRepo additionalChecksRepo;

	@Autowired
	private ModifierConceptsRepo modifierConceptsRepo;
	@Autowired
	private StatementMapper stmtMapper;

	@Override
	public Optional<StatementSettingsRelationships> getSettingsById(String id) {
		return stmtsettingsrepo.findById(id);
	}

	@Override
	public StatementRequestDTO getStatementId(String id) {
		Optional<Statement> stmt=stmtrepo.findById(id);
		StatementRequestDTO response=stmtMapper.toDto(stmt.get());
		return response;
	}

	@Override
	public Optional<StatementFunctionsRelationships> getFunctionsById(String id) {
		return stmtfuncrepo.findById(id);

	}

	@Override
	public Optional<PrimaryConcepts> getContextById(String contextId, String conceptAlias) {
		return null;
	}

	@Override
	public StatementResponseDTO createStatement(StatementRequestDTO statementDTO) {
		try {
			Statement statement = stmtMapper.toEntity(statementDTO);
			Statement saveStatement = stmtrepo.save(statement);

			statementDTO.getModels().forEach(mod -> {
				Model model = modelRepo.save(Model.builder().model(mod.getModel()).statementUId(saveStatement)
						.negateModifier(Boolean.TRUE).build());
				mod.getPrimaryConcepts().forEach(primare -> {
					primaryConceptsRepo.save(PrimaryConcepts.builder().contextId(primare.getContextId())
							.conceptAlias(primare.getConceptAlias()).model(model).modelId(primare.isModelId()).build());
				});
				mod.getAdditionalChecks().forEach(additional -> {
					additionalChecksRepo.save(AdditionalChecks.builder().type(additional.getType())
							.conceptAlias(additional.getConceptAlias()).modelId(additional.isModelId())
							.type(additional.getType()).model(model).build());
				});
				mod.getModifierConcepts().forEach(modification -> {
					modifierConceptsRepo.save(ModifierConcepts.builder().conceptAlias(modification.getConceptAlias())
							.contextId(modification.getContextId()).modelId(modification.isModelId()).model(model)
							.build());
				});
			});

			StatementDependencies stateSet = StatementDependencies.builder()
					.dependencyStatementId(UUID.randomUUID().toString()).statementUId(saveStatement).build();
			statmenmtDependency.save(stateSet);

			return StatementResponseDTO.builder().statementId(saveStatement.getId()).scope(saveStatement.getScopeName())
					.statementAlias(saveStatement.getStatementAlias()).statementDisplay(saveStatement.getStatementDisplay())
					.createdAt(saveStatement.getCreatedAt()).createdBy(saveStatement.getCreatedBy()).build();

		} catch (Exception e) {
			throw new ConflictOccuredException("Request Data Not Saved");
		}

	}

	@Override
	public StatementResponseDTO updateStatement(String statementId, StatementRequestDTO statementDTO) {
		Statement existingStatement = stmtrepo.findById(statementId).get();
		try{
			existingStatement.setAuthor(statementDTO.getAuthor());
			existingStatement.setBaseVersion(statementDTO.getBaseVersion());
			existingStatement.setCardinality(statementDTO.getCardinality());
			existingStatement.setCreatedBy(statementDTO.getCreatedBy());
			existingStatement.setDateOperations(statementDTO.getDateOperations());
			existingStatement.setFrequencyType(statementDTO.getFrequencyType());
			existingStatement.setFrequencyValue(statementDTO.getFrequencyValue());
			existingStatement.setCreatedAt(statementDTO.getCreatedAt());
			existingStatement.setRecordName(statementDTO.getRecordName());
			existingStatement.setRecordOperations(statementDTO.getRecordOperations());
			existingStatement.setRequiredOperations(statementDTO.getRequiredOperations());
			existingStatement.setScopeId(statementDTO.getScopeId());
			existingStatement.setScopeName(statementDTO.getScope());
			existingStatement.setSelector(statementDTO.getSelector());
			existingStatement.setStatementAlias(statementDTO.getStatementAlias());
			existingStatement.setStatementDependencyAlias(statementDTO.getStatementDependencyAlias());
			existingStatement.setStatementDisplay(statementDTO.getStatementDisplay());
			existingStatement.setStatementText(statementDTO.getStatementText());
			existingStatement.setStatus(statementDTO.getStatus());
			existingStatement.setTags(statementDTO.getTags());
			existingStatement.setUpdatedAt(statementDTO.getUpdatedAt());
			existingStatement.setUpdatedBy(statementDTO.getUpdatedBy());
			existingStatement.setVersion(statementDTO.getVersion());
			existingStatement.setWithDistinct(statementDTO.getWithDistinct());

			Statement saveStatement= stmtrepo.save(existingStatement);
			return StatementResponseDTO.builder().statementId(saveStatement.getId()).scope(saveStatement.getScopeName())
					.statementAlias(saveStatement.getStatementAlias()).statementDisplay(saveStatement.getStatementDisplay())
					.createdAt(saveStatement.getCreatedAt()).createdBy(saveStatement.getCreatedBy()).build();
		} catch(Exception e){
			throw new StatementNotFoundException("Statement Not Found");
		}
	}

	@Override
	public String deleteStatementById(String statementId) {
		stmtrepo.deleteById(statementId);
		return "Statement Deleted Successfully with statementId = " + statementId;
	}

	@Override
	public List<StatementRequestDTO> getAllStatement() {
		List<Statement> stmt= stmtrepo.findAll();
		List<StatementRequestDTO> list= new ArrayList<>();
		stmt.forEach(stm ->{
			list.add(stmtMapper.toDto(stm))	;
			});
		return list;
	}

}
