package net.cerner.dto;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Builder;
import lombok.Data;
import net.cerner.entity.Model;
@Builder
@Data
public class StatementRequestDTO {

	@NotNull(message = "RecordName can not be null")
	private String recordName;
	
	@NotNull(message = "scopeId Can't be null")
	private String scopeId;
	
	@NotNull(message = "scope Can't be null")
	private String scope;
	
	@NotNull(message = "statementAlias Can't be null")
	private String statementAlias;
	
	@NotNull(message = "statementDisplay Can't be null")
	private String statementDisplay;
	
	@NotNull(message = "statementText Can't be null")
	private String statementText;
	
	@NotNull(message = "statementDependencyAlias Can't be null")
	private String statementDependencyAlias;
	
	@NotNull(message = "models Can't be null")
	private Set<Model> models;
	
	private String recordOperations;
	private String dateOperations;
	private String requiredOperations;
	private String selector;
	private Long cardinality;
	private String withDistinct;
	private boolean negateStatement;
	private String[] tags;
	private String frequencyType;
	private String frequencyValue;
	private String status;
	private String version;
	private String baseVersion;
	private String author;
	
	@Column(updatable =  false)
	@CreationTimestamp
	private LocalDate createdAt;
	private String createdBy;
	
	@Column(insertable = false)
	@UpdateTimestamp
	private LocalDate updatedAt;
	private String updatedBy;
}
