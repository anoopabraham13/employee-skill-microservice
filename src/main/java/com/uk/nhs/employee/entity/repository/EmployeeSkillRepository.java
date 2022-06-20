package com.uk.nhs.employee.entity.repository;

import com.uk.nhs.employee.entity.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for accessing EmployeeSkill entity directly from database.
 * 
 * @author Anoop Abraham
 *
 */
public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, Long> {

}
