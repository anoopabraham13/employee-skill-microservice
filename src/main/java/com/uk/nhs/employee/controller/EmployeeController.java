package com.uk.nhs.employee.controller;

import com.uk.nhs.employee.entity.Employee;
import com.uk.nhs.employee.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * This is the implementation of EmployeeContract class having the logic for various end points.
 *
 * @author Anoop Abraham
 *
 */
@RestController
@RequestMapping("/api/employee")
public class EmployeeController implements EmployeeContract{


	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	/**
	 * Instance variable to hold EmployeeService instance.
	 */
	@Autowired
	private EmployeeService employeeService;

	@Override
	@PostMapping("/create")
	public ResponseEntity<Void> createEmployee(@RequestBody Employee employee) {
		LOGGER.debug("creating employee");
		employeeService.createEmployee(employee);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@Override
	@GetMapping(value = "/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable(name = "id", required = true) final Long id) {
		LOGGER.debug("getting employee for the id {}", id);
		Employee employee = employeeService.getEmployee(id);
		if (employee == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(employee, HttpStatus.OK);
	}

	@Override
	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees(
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
		LOGGER.debug("getting all the employees");
		List<Employee> employeeList = employeeService.getAllEmployees(pageNo, pageSize);
		return new ResponseEntity<>(employeeList, HttpStatus.OK);
	}

	@Override
	@PutMapping(value = "/update")
	public ResponseEntity<Void>  updateEmployee(@RequestBody Employee employee) {
		LOGGER.debug("updating the employee for the id {}", employee.getId());
		employeeService.updateEmployee(employee);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable("id") final Long id) {
		LOGGER.debug("deleting employee having the id {}", id);
		employeeService.deleteEmployee(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
