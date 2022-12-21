package net.cerner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.cerner.dto.StatementRequestDTO;
import net.cerner.dto.StatementResponseDTO;
import net.cerner.service.StatementService;

@RestController
public class StatementController {

	@Autowired
	private StatementService stmtService;
	
	
	@PostMapping("/statements")
	@ApiOperation(value = "Save the statement")
	public ResponseEntity<StatementResponseDTO> saveStatement(@RequestBody StatementRequestDTO statement){
		return new ResponseEntity<>(stmtService.createStatement(statement),HttpStatus.OK);
		
	}
	@GetMapping("/statements/allStatements")
	public ResponseEntity<List<StatementRequestDTO>> getResponse(){
		return new ResponseEntity<>(stmtService.getAllStatement(), HttpStatus.OK);
		
	}
	
	
	
	
	@PutMapping("/statements/{statementId}")
	@ApiOperation(value = "Update the statement")
	public ResponseEntity<StatementResponseDTO> updateStatementById(@PathVariable("statementId")String statementId, @RequestBody StatementRequestDTO dto){
		return new ResponseEntity<>(stmtService.updateStatement(statementId, dto),HttpStatus.OK);
		
	}
	
	@DeleteMapping("/statements/{statementId}")
	@ApiOperation(value = "Delete the statement")
	public ResponseEntity<String> deleteStatement(@PathVariable("statementId")String statementId){
		String deleteStatement = stmtService.deleteStatementById(statementId);
		return new ResponseEntity<>(deleteStatement,HttpStatus.OK);
		
	}

}
