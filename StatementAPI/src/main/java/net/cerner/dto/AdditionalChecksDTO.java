package net.cerner.dto;

import javax.persistence.Id;

import lombok.Data;


@Data
public class AdditionalChecksDTO {

	@Id
	private String id;
	private String type;
	private String contextId;
	private String conceptAlias;
	private boolean modelId;
	
}
