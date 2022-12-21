package net.cerner.dto;

import javax.persistence.Id;

import lombok.Data;


@Data
public class ModifierConceptsDTO {

	@Id
	private String id;
	private String contextId;
	private String conceptAlias;
	private boolean modelId;

}
