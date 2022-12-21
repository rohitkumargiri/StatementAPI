package net.cerner.dto;

import java.util.List;

import lombok.Data;
@Data
public class RecordOperationsDTO {

	private String fnName;
	private List<String> arguments;
}
