package net.cerner.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "Statement_Settings_Relationships")
@Data
public class StatementFunctionsRelationships {

	@Id
	private String id;
	private String statementUId;
	private String functionId;
	
	@Column(updatable = false)
	@CreationTimestamp
	private Date createdAt;
	
	private String createdBy;
	
	@Column(insertable = false)
	@UpdateTimestamp
	private Date updatedAt;
	private String updatedBy;
}
