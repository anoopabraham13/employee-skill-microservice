package com.uk.nhs.employee.controller;

import com.uk.nhs.employee.entity.Employee;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * This interface defines the API contract definition for the Employee service.
 *
 * @author Anoop Abraham
 */
@Api(value = "/api/employee", tags = { "NHS Employee Management" })
public interface EmployeeContract {

    /**
     * Method to Create the Employee Entity.
     *
     * @param employee
     * @return
     */
    @ApiOperation(value = "Create a new Employee")
    public ResponseEntity<Void> createEmployee(@RequestBody Employee employee);


    /**
     * Method to Fetch the Employee for the id.
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "Fetch the Employee for the id")
    public ResponseEntity<Employee> getEmployee(@ApiParam(value = "Employee id", required = true) Long id);


    /**
     * Method to Fetch all the Employees.
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "Fetch all the Employees")
    public ResponseEntity<List<Employee>> getAllEmployees(
            @RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize);


    /**
     * Method to Update the the Employee.
     *
     * @param employee
     * @return
     */
    @ApiOperation(value = "Update the the Employee")
    public ResponseEntity<Void>  updateEmployee(Employee employee);

    /**
     * Method to Delete the Employee for the id.
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "Delete the Employee for the id")
    public ResponseEntity<Void>  deleteEmployee(@ApiParam(value = "Employee id", required = true) Long id);

}
