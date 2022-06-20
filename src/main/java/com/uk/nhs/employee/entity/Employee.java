package com.uk.nhs.employee.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;

import com.uk.nhs.employee.entity.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Employee entity
 * 
 * @author Anoop Abraham
 *
 */
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee  {

	/**
	 * The id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_sequence")
	@SequenceGenerator(name = "employee_sequence", sequenceName = "employee_sequence", allocationSize = 1)
	private Long id;

	/**
	 * Employee first Name
	 */
	private String firstName;

	/**
	 * Employee middle Name
	 */
	private String middleName;

	/**
	 * Employee last Name
	 */
	private String lastName;

	/**
	 * Employee date Of Birth
	 */
	private Date dateOfBirth;

	/**
	 * Employee gender
	 */
	@Enumerated(EnumType.STRING)
	private Gender gender;

	/**
	 * Employee skills
	 */
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "employee_id")
	private List<EmployeeSkill> employeeSkills;

}
