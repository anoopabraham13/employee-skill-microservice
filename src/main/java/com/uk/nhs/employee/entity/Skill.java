package com.uk.nhs.employee.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Skill entity
 *
 * @author Anoop Abraham
 *
 */
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skill  {


	/**
	 * The id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "skill_sequence")
	@SequenceGenerator(name = "skill_sequence", sequenceName = "skill_sequence", allocationSize = 1)
	private Long id;

	/**
	 * short name of the skill
	 */
	private String skillName;

	/**
	 * description about the skill.
	 */
	private String skillDescription;

}
