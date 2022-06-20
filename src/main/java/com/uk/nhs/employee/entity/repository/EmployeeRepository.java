package com.uk.nhs.employee.entity.repository;


import com.uk.nhs.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for accessing Employee entity directly from database.
 * 
 * @author Anoop Abraham
 *
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
