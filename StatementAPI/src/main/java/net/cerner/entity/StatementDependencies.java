package net.cerner.entity;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Entity
@Table(name = "statement_dependencies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatementDependencies {

	@Id
	@GeneratedValue
	private UUID id;
	
	private String dependencyStatementId;
	
	@Column(updatable = false)
	@CreationTimestamp
	private LocalDate createdAt;
	
	@Column(insertable = false)
	@UpdateTimestamp
	private LocalDate updatedAt;
	
	@ManyToOne
	@JoinColumn(name="statement_uid", nullable=false)
	private Statement statementUId;
	
}
