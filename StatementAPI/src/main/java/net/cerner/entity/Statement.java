package net.cerner.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Table(name = "statement")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Statement {
	@Id
	private String id;
	private String recordName;
	private String scopeId;
	private String scopeName;
	private String statementAlias;
	private String statementDisplay;
	private String statementText;
	private String statementDependencyAlias;
	
	@OneToMany(mappedBy = "statementUId",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Model> models;
	
	@OneToMany(mappedBy = "statementUId",  fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<StatementDependencies> stmtDependencies;
	
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
	
	@Column(updatable = false)
	@CreationTimestamp
	private LocalDate createdAt;
	private String createdBy;
	
	@Column(insertable = false)
	@UpdateTimestamp
	private LocalDate updatedAt;
	private String updatedBy;
}
