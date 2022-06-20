package com.uk.nhs.employee.entity;

import javax.persistence.*;

import com.uk.nhs.employee.entity.domain.SkillLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The EmployeeSkill entity
 *
 * @author Anoop Abraham
 *
 */
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSkill {

	/**
	 * The id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "skill_detail")
	@SequenceGenerator(name = "skill_detail", sequenceName = "skill_detail", allocationSize = 1)
	private Long id;

	/**
	 * Skill
	 */
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Skill skill;

	/**
	 * Skill level.
	 */
	@Enumerated(EnumType.STRING)
	private SkillLevel skillLevel;

}
