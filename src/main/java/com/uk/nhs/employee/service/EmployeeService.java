package com.uk.nhs.employee.service;

import com.uk.nhs.employee.entity.Employee;

import java.util.List;

/**
 * Interface for EmployeeService to handle the employee operations.
 *
 * @author Anoop Abraham
 *
 */
public interface EmployeeService {

    /**
     * Method to create the Employee entity.
     *
     * @param employee
     */
    public void createEmployee(Employee employee);

    /**
     * Method to fetch the Employee based on employee id.
     *
     * @param id
     * @return
     */
    public Employee getEmployee(Long id);

    /**
     * Method to fetch all the employees in the database.
     *
     *  @param pageNo
     *  @param pageSize
     * @return
     */
    public List<Employee> getAllEmployees(Integer pageNo, Integer pageSize);

    /**
     * Method to update the Employee entity attributes.
     *
     * @param employee
     */
    public void updateEmployee(Employee employee);

    /**
     * Method to delete the Employee based on employee id.
     *
     * @param id
     * @return
     */
    public void deleteEmployee(Long id);

}
