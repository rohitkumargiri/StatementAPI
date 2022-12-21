package net.cerner.dto;

import java.util.List;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
public class RequiredOperationsDTO {

	private String fnName;
	private List<String> arguments; 
}
