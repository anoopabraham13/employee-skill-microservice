package com.uk.nhs.employee.service;

import com.uk.nhs.employee.entity.Employee;
import com.uk.nhs.employee.entity.repository.EmployeeRepository;
import com.uk.nhs.employee.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation for EmployeeService interface.
 * 
 * @author Anoop Abraham
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	/**
	 * Instance variable for employeeRepository
	 */
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void createEmployee(Employee employee) {
		employeeRepository.saveAndFlush(employee);
		LOGGER.debug("created a new employee");
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Employee getEmployee(Long id) {
		LOGGER.debug("getting employee for the id {}", id);
		Optional<Employee> employees = employeeRepository.findById(id);
		if (employees.isPresent())
			return employees.get();

		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Employee> getAllEmployees(Integer pageNo, Integer pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("firstName"));
		Page<Employee> employeePagedResult = employeeRepository.findAll(paging);

		if(employeePagedResult.hasContent()) {
			return employeePagedResult.getContent();
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateEmployee(Employee employee) {
		Optional<Employee> employees = employeeRepository.findById(employee.getId());
		if (!employees.isPresent())
			throw new EntityNotFoundException("Employee is not available for the given id");

		employeeRepository.saveAndFlush(employee);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteEmployee(Long id) {
		Optional<Employee> employees = employeeRepository.findById(id);

		if (employees.isPresent()) {
			employeeRepository.deleteById(id);
			LOGGER.debug("deleted the employee for the id {}", id);
		}else {
			throw new EntityNotFoundException("Employee is not available for the given id");
		}
	}

}
