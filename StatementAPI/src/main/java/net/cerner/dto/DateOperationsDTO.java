package net.cerner.dto;

import java.util.List;

import lombok.Data;

@Data
public class DateOperationsDTO {

	private String fnName;
	private List<String> arguments;
}
