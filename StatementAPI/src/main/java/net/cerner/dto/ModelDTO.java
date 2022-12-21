package net.cerner.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ModelDTO {

	private String model;
	
	@NotNull(message = "primaryConcepts can't be null")
	private List<PrimaryConceptsDTO> primaryConcepts;
	
	@NotNull(message = "modifierConcepts can't be null")
	private List<ModifierConceptsDTO> modifierConcepts;
	
	@NotNull(message = "additionalChecks can't be null")
	private List<AdditionalChecksDTO> additionalChecks;
	private String statementUId;
	private boolean negateModifier;
	

}
