package com.uk.nhs.employee.controller;

import com.uk.nhs.employee.entity.EmployeeSkill;
import com.uk.nhs.employee.requestmodel.EmployeeSkillRequest;
import com.uk.nhs.employee.service.EmployeeSkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * This is the implementation of EmployeeSkillContract class having the logic for various end points.
 *
 * @author Anoop Abraham
 *
 */
@RestController
@RequestMapping("/api/employee/skill")
public class EmployeeSkillController implements EmployeeSkillContract{


    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeSkillController.class);

    /**
     * Instance variable to hold EmployeeService instance.
     */
    @Autowired
    private EmployeeSkillService employeeSkillService;

    @Override
    @PostMapping("/create")
    public ResponseEntity<Void> createEmployeeSkill(@RequestBody EmployeeSkillRequest employeeSkillRequest) {
        LOGGER.debug("creating EmployeeSkill");
        employeeSkillService.createEmployeeSkill(employeeSkillRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeSkill> getEmployeeSkill(@PathVariable(name = "id", required = true) final Long id) {
        LOGGER.debug("getting employee for the id {}", id);
        EmployeeSkill employeeSkill = employeeSkillService.getEmployeeSkill(id);
        if (employeeSkill == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(employeeSkill, HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<EmployeeSkill>> getAllEmployeeSkills(@RequestParam(name = "employeeId", required = true) final Long employeeId) {
        LOGGER.debug("getting all the skills of the employee");
        List<EmployeeSkill> employeeList = employeeSkillService.getAllEmployeeSkills(employeeId);
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @Override
    @PutMapping(value = "/update")
    public ResponseEntity<Void> updateEmployeeSkill(@RequestBody EmployeeSkill employeeSkill) {
        LOGGER.debug("updating the employeeSkill for the id {}", employeeSkill.getId());
        employeeSkillService.updateEmployeeSkill(employeeSkill);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteEmployeeSkill(Long id) {
        LOGGER.debug("deleting EmployeeSkill having the id {}", id);
        employeeSkillService.deleteEmployeeSkill(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
