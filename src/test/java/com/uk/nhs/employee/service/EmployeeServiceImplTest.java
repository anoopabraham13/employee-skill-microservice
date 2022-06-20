package com.uk.nhs.employee.service;

import com.uk.nhs.employee.entity.Employee;
import com.uk.nhs.employee.entity.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * Test cases for EmployeeServiceImpl class
 *
 * @author  Anoop Abraham
 */
@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {


    /** Test values. */
    private static final Long TEST_EMPLOYEE_ID = Long.valueOf(1);

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    EmployeeRepository employeeRepository;

    /**
     * Show that a new Employee can be successfully saved.
     */
    @Test
    public void shouldSuccessfullySaveNewEmployee() {
        Employee employee = getEmployee();
        given(employeeRepository.saveAndFlush(isA(Employee.class))).willReturn(employee);

        this.employeeService.createEmployee(employee);

        verify(employeeRepository, times(1)).saveAndFlush(isA(Employee.class));
        verifyNoMoreInteractions(employeeRepository);
    }

    /**
     * Show that an Employee can be successfully retrieved.
     */
    @Test
    public void shouldSuccessfullyGetAnEmployee() {
        given(employeeRepository.findById(isA(Long.class))).willReturn(Optional.of(getEmployee()));

        Employee employeeResponse = this.employeeService.getEmployee(TEST_EMPLOYEE_ID);

        assertNotNull("response should not be null", employeeResponse);
        assertEquals("ID's doesn't match", TEST_EMPLOYEE_ID, employeeResponse.getId());

        verify(employeeRepository, times(1)).findById(isA(Long.class));
        verifyNoMoreInteractions(employeeRepository);
    }

    /**
     * Show that all the Employees can be successfully retrieved.
     */
    @Test
    public void shouldSuccessfullyGetAllEmployee() {
        List<Employee> employees = new ArrayList<>();
        employees.add(getEmployee());
        Page<Employee> employeePagedResult = new PageImpl<>(employees);
        given(employeeRepository.findAll(isA(Pageable.class))).willReturn(employeePagedResult);

        List<Employee> employeeList = this.employeeService.getAllEmployees(0,1);

        assertNotNull("response should not be null", employeeList);
        assertEquals("Size doesn't match", 1, employeeList.size());

        verify(employeeRepository, times(1)).findAll(isA(Pageable.class));
        verifyNoMoreInteractions(employeeRepository);
    }

    /**
     * Show that the Employee can be updated successfully.
     */
    @Test
    public void shouldSuccessfullyUpdateAnEmployee() {
        Employee employee = getEmployee();
        given(employeeRepository.findById(isA(Long.class))).willReturn(Optional.of(employee));

        this.employeeService.updateEmployee(employee);

        verify(employeeRepository, times(1)).saveAndFlush(isA(Employee.class));
        verify(employeeRepository, times(1)).findById(isA(Long.class));
        verifyNoMoreInteractions(employeeRepository);
    }

    /**
     * Show that the Employee can be deleted successfully.
     */
    @Test
    public void shouldSuccessfullyDeleteAnEmployee() {
        given(employeeRepository.findById(isA(Long.class))).willReturn(Optional.of(getEmployee()));

        this.employeeService.deleteEmployee(TEST_EMPLOYEE_ID);

        verify(employeeRepository, times(1)).deleteById(isA(Long.class));
        verify(employeeRepository, times(1)).findById(isA(Long.class));
        verifyNoMoreInteractions(employeeRepository);
    }

    /**
     * Method to get an employee instance
     *
     * @return
     */
    private Employee getEmployee(){
        return Employee.builder().id(TEST_EMPLOYEE_ID).build();
    }

}
