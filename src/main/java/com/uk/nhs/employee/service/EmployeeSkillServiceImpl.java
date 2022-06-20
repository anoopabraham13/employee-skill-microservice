package com.uk.nhs.employee.service;


import com.uk.nhs.employee.entity.Employee;
import com.uk.nhs.employee.entity.EmployeeSkill;
import com.uk.nhs.employee.entity.repository.EmployeeSkillRepository;
import com.uk.nhs.employee.exception.EntityNotFoundException;
import com.uk.nhs.employee.requestmodel.EmployeeSkillRequest;
import com.uk.nhs.employee.requestmodel.SkillDetailRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Implementation for EmployeeSkillService interface.
 *
 * @author Anoop Abraham
 *
 */
@Service
public class EmployeeSkillServiceImpl implements EmployeeSkillService {

    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeSkillServiceImpl.class);

    /**
     * Instance variable for EmployeeSkillRepository
     */
    @Autowired
    EmployeeSkillRepository employeeSkillRepository;

    /**
     * Instance variable to hold EmployeeService instance.
     */
    @Autowired
    private EmployeeService employeeService;

    /**
     * Instance variable to hold EmployeeService instance.
     */
    @Autowired
    private SkillService skillService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void createEmployeeSkill(EmployeeSkillRequest employeeSkillRequest) {
        Employee employee =employeeService.getEmployee(employeeSkillRequest.getEmployeeId());
        if(null == employee)
            throw new EntityNotFoundException("Employee is not available for the given id");

        Collection<SkillDetailRequest> skillDetails = employeeSkillRequest.getSkillDetailRequest();
        if(null != skillDetails ){
            for (SkillDetailRequest skillDetailRequest : skillDetails) {
                EmployeeSkill employeeSkill = EmployeeSkill.builder()
                        .skill(skillService.getSkill(skillDetailRequest.getSkillId()))
                        .skillLevel(skillDetailRequest.getSkillLevel()).build();
                employeeSkillRepository.saveAndFlush(employeeSkill);
                employee.getEmployeeSkills().add(employeeSkill);
            }
        }
        employeeService.updateEmployee(employee);
        LOGGER.debug("EmployeeSkills are added");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public EmployeeSkill getEmployeeSkill(Long id) {
        LOGGER.debug("getting employee for the id {}", id);
        Optional<EmployeeSkill> employeeSkills = employeeSkillRepository.findById(id);
        if (employeeSkills.isPresent())
            return employeeSkills.get();

        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<EmployeeSkill> getAllEmployeeSkills(Long employeeId) {
        Employee employee =employeeService.getEmployee(employeeId);
        if(null == employee)
            throw new EntityNotFoundException("Employee is not available for the given id");

        return employee.getEmployeeSkills();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateEmployeeSkill(EmployeeSkill employeeSkill) {
        Optional<EmployeeSkill> employeeSkills = employeeSkillRepository.findById(employeeSkill.getId());
        if (!employeeSkills.isPresent())
            throw new EntityNotFoundException("EmployeeSkill is not available for the given id");

        employeeSkillRepository.saveAndFlush(employeeSkill);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteEmployeeSkill(Long id) {
        Optional<EmployeeSkill> employeeSkills = employeeSkillRepository.findById(id);

        if (employeeSkills.isPresent()) {
            employeeSkillRepository.deleteById(id);
            LOGGER.debug("deleted the employee skill for the id {}", id);
        }else {
            throw new EntityNotFoundException("Employee skill is not available for the given id");
        }
    }
}
