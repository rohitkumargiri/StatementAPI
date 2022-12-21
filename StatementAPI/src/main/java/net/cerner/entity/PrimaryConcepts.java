package net.cerner.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Primary_Concepts")
@Data
public class PrimaryConcepts {

	@Id
	@GeneratedValue
	private UUID id;
	private String contextId;
	private String conceptAlias;
	private boolean modelId;
	@ManyToOne
	@JoinColumn(name = "model_uid", nullable=false)
	private Model model;
}
