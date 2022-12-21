package net.cerner.dto;

import java.time.LocalDate;

import javax.persistence.Column;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StatementResponseDTO {

	private String statementId;
	private String scope;
	private String statementAlias;
	private String statementDisplay;
	
	@Column(updatable = false)
	@CreationTimestamp
	private LocalDate createdAt;
	private String createdBy;
}
