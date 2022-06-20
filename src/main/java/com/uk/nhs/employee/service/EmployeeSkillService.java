package com.uk.nhs.employee.service;

import com.uk.nhs.employee.entity.EmployeeSkill;
import com.uk.nhs.employee.requestmodel.EmployeeSkillRequest;

import java.util.List;

/**
 * Interface for EmployeeSkillService to handle the employee skill operations.
 *
 * @author Anoop Abraham
 *
 */
public interface EmployeeSkillService {

    /**
     * Method to create the EmployeeSkill entity.
     *
     * @param employeeSkillRequest
     */
    public void createEmployeeSkill(EmployeeSkillRequest employeeSkillRequest);

    /**
     * Method to fetch the EmployeeSkill based on EmployeeSkill id.
     *
     * @param id
     * @return
     */
    public EmployeeSkill getEmployeeSkill(Long id);

    /**
     * Method to fetch all the EmployeeSkill in the database.
     *
     * @param employeeId
     * @return
     */
    public List<EmployeeSkill> getAllEmployeeSkills(Long employeeId);

    /**
     * Method to update the EmployeeSkill entity attributes.
     *
     * @param employeeSkill
     */
    public void updateEmployeeSkill(EmployeeSkill employeeSkill);

    /**
     * Method to delete the EmployeeSkill based on employee id.
     *
     * @param id
     * @return
     */
    public void deleteEmployeeSkill(Long id);

}
