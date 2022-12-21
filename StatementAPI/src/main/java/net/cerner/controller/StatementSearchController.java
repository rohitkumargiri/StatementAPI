package net.cerner.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.cerner.dto.StatementRequestDTO;
import net.cerner.entity.PrimaryConcepts;
import net.cerner.entity.Statement;
import net.cerner.entity.StatementFunctionsRelationships;
import net.cerner.entity.StatementSettingsRelationships;
import net.cerner.service.StatementService;

@RestController
@RequestMapping("/statements/search")
public class StatementSearchController {

	@Autowired
	private StatementService statementService;

	@GetMapping("/settings/{settingId}")
	@ResponseBody
	@ApiOperation(value = "Search the statement by using settingId")
	public ResponseEntity<Object> getStatementSettingsById(
			@PathVariable("settingId") String settingId) {
		Optional<StatementSettingsRelationships> settingsById = statementService.getSettingsById(settingId);
			return new ResponseEntity<>(settingsById,HttpStatus.OK);
		
	}
	
	@GetMapping("/{statementId}")
	public ResponseEntity<StatementRequestDTO> getStatment(@PathVariable("statementId")String statementId){
				return new ResponseEntity<>(statementService.getStatementId(statementId),HttpStatus.OK);
	}

	
	@GetMapping("/functions/{functionId}")
	@ResponseBody
	@ApiOperation(value = "Search the functions of the statement by using functionId")
	public ResponseEntity<Object> getStatementFunctionsById(
			@PathVariable("functionId") String id) {
		Optional<StatementFunctionsRelationships> functionsById = statementService.getFunctionsById(id);
		if(functionsById.isPresent()) {
			return new ResponseEntity<>(functionsById,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		

	}
	
	@GetMapping("/contexts/{contextId}/concepts/{conceptAlias}")
	@ResponseBody
	@ApiOperation(value = "Search the context of the schema by using contextId and conceptAlias")
	public ResponseEntity<Object> getStatementConceptAliasByContextId(
			@PathVariable("contextId") String contextId,
			@PathVariable("conceptAlias") String conceptAlias) {
		Optional<PrimaryConcepts> contextById = statementService.getContextById(contextId, conceptAlias);
		if(contextById.isPresent()) {
			return new ResponseEntity<>(contextById,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
}
