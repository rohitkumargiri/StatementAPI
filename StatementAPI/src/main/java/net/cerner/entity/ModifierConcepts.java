package net.cerner.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
@Builder
@Entity
@Table(name = "Modifier_Concepts")
@Data
public class ModifierConcepts {

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
