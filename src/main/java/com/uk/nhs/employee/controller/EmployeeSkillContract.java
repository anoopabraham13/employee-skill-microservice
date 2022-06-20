package com.uk.nhs.employee.controller;

import com.uk.nhs.employee.entity.EmployeeSkill;
import com.uk.nhs.employee.requestmodel.EmployeeSkillRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * This interface defines the API contract definition for the Employee skill service.
 *
 * @author Anoop Abraham
 */
@Api(value = "/api/employee/skill", tags = { "NHS Employee Skill Management" })
public interface EmployeeSkillContract {

    /**
     * Method to Create the EmployeeSkill entity.
     *
     * @param employeeSkillRequest
     * @return
     */
    @ApiOperation(value = "Add new Skills to employee")
    public ResponseEntity<Void> createEmployeeSkill(@RequestBody EmployeeSkillRequest employeeSkillRequest);


    /**
     * Method to Fetch the EmployeeSkill for the id.
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "Get the EmployeeSkill for the id")
    public ResponseEntity<EmployeeSkill> getEmployeeSkill(@ApiParam(value = "EmployeeSkill id", required = true) Long id);

    /**
     * Method to Fetch all the EmployeeSkills.
     *
     * @return
     */
    @ApiOperation(value = "Fetch all the Skills of the Employee")
    public ResponseEntity<List<EmployeeSkill>> getAllEmployeeSkills(
            @ApiParam(value = "Employee id", required = true) Long employeeId);

    /**
     * Method to Update the the EmployeeSkill for the id.
     *
     * @param employeeSkill
     * @return
     */
    @ApiOperation(value = "Update the the EmployeeSkill")
    public ResponseEntity<Void> updateEmployeeSkill(@RequestBody EmployeeSkill employeeSkill);

    /**
     * Method to Delete the Skill for the id.
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "Delete the deleteEmployeeSkill for the id")
    public ResponseEntity<Void> deleteEmployeeSkill(@ApiParam(value = "EmployeeSkill id", required = true) Long id);
}
