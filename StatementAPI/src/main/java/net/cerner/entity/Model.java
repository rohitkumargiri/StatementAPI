package net.cerner.entity;

import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Model")
@Data
public class Model {

	@Id
	@GeneratedValue
	private UUID id;
	private String model;
	
	@OneToMany(mappedBy = "model" , fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<PrimaryConcepts> primaryConcepts;
	
	@OneToMany(mappedBy = "model" , fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ModifierConcepts> modifierConcepts;
	
	@OneToMany(mappedBy = "model" , fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<AdditionalChecks> additionalChecks;
	
	private boolean negateModifier;
	
	@ManyToOne
	@JoinColumn(name = "statement_uid", nullable=false)
	private Statement statementUId;
}
