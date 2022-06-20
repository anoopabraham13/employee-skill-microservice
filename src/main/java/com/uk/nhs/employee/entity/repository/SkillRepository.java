package com.uk.nhs.employee.entity.repository;


import com.uk.nhs.employee.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for accessing Skill entity directly from database.
 * 
 * @author Anoop Abraham
 *
 */
public interface SkillRepository extends JpaRepository<Skill, Long> {

}
