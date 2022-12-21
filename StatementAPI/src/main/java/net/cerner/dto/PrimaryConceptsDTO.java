package net.cerner.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
public class PrimaryConceptsDTO {

	private String id;
	
	@NotNull(message = "contextId can't be null")
	private String contextId;
	
	@NotNull(message = "conceptAlias can't be null")
	private String conceptAlias;
	private boolean modelId;

}
