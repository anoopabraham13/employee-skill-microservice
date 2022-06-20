package com.uk.nhs.employee.service;

import com.uk.nhs.employee.entity.Employee;
import com.uk.nhs.employee.entity.EmployeeSkill;
import com.uk.nhs.employee.entity.Skill;
import com.uk.nhs.employee.entity.domain.SkillLevel;
import com.uk.nhs.employee.entity.repository.EmployeeRepository;
import com.uk.nhs.employee.entity.repository.EmployeeSkillRepository;
import com.uk.nhs.employee.entity.repository.SkillRepository;
import com.uk.nhs.employee.requestmodel.EmployeeSkillRequest;
import com.uk.nhs.employee.requestmodel.SkillDetailRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * Test cases for EmployeeSkillServiceImpl class
 *
 * @author  Anoop Abraham
 */
@RunWith(MockitoJUnitRunner.class)
public class EmployeeSkillServiceImplTest {


    /** Test values. */
    private static final Long TEST_EMPLOYEE_ID = Long.valueOf(1);
    private static final Long TEST_SKILL_ID = Long.valueOf(2);
    private static final Long TEST_EMPLOYEE_SKILL_ID = Long.valueOf(3);

    @InjectMocks
    private EmployeeSkillServiceImpl employeeSkillService;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private SkillServiceImpl skillService;

    @Mock
    EmployeeSkillRepository employeeSkillRepository;

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    SkillRepository skillRepository;

    /**
     * Show that a new EmployeeSkill can be successfully saved.
     */
    @Test
    public void shouldSuccessfullySaveNewEmployeeSkill() {
        Employee employee = getEmployee();
        List<EmployeeSkill> employeeSkills = new ArrayList<>();
        employee.setEmployeeSkills(employeeSkills);
        given(employeeService.getEmployee(isA(Long.class))).willReturn(employee);
        given(skillService.getSkill(isA(Long.class))).willReturn(getSkill());

        List<SkillDetailRequest> skills = new ArrayList<>();
        skills.add(getSkillDetailRequest());
        EmployeeSkillRequest employeeSkillRequest = EmployeeSkillRequest.builder()
                .employeeId(TEST_EMPLOYEE_ID).skillDetailRequest(skills).build();

        EmployeeSkill employeeSkill = getEmployeeSkill();
        given(employeeSkillRepository.saveAndFlush(isA(EmployeeSkill.class))).willReturn(employeeSkill);


        this.employeeSkillService.createEmployeeSkill(employeeSkillRequest);

        verify(employeeSkillRepository, times(1)).saveAndFlush(isA(EmployeeSkill.class));
        verifyNoMoreInteractions(employeeSkillRepository);
    }

    /**
     * Show that an EmployeeSkill can be successfully retrieved.
     */
    @Test
    public void shouldSuccessfullyGetAnEmployeeSkill() {
        given(employeeSkillRepository.findById(isA(Long.class))).willReturn(Optional.of(getEmployeeSkill()));

        EmployeeSkill employeeSkillResponse = this.employeeSkillService.getEmployeeSkill(TEST_EMPLOYEE_SKILL_ID);

        assertNotNull("response should not be null", employeeSkillResponse);
        assertEquals("ID's doesn't match", TEST_EMPLOYEE_SKILL_ID, employeeSkillResponse.getId());

        verify(employeeSkillRepository, times(1)).findById(isA(Long.class));
        verifyNoMoreInteractions(employeeSkillRepository);
    }

    /**
     * Show that all the EmployeeSkills can be successfully retrieved.
     */
    @Test
    public void shouldSuccessfullyGetAllTheSkillsOfEmployee() {
        Employee employee = getEmployee();
        List<EmployeeSkill> employeeSkills = new ArrayList<>();
        employeeSkills.add(getEmployeeSkill());
        employee.setEmployeeSkills(employeeSkills);
        given(employeeService.getEmployee(isA(Long.class))).willReturn(employee);

        List<EmployeeSkill> employeeList = this.employeeSkillService.getAllEmployeeSkills(TEST_EMPLOYEE_ID);

        assertNotNull("response should not be null", employeeList);
        assertEquals("Size doesn't match", 1, employeeList.size());

        verify(employeeService, times(1)).getEmployee(isA(Long.class));
        verifyNoMoreInteractions(employeeRepository);
    }

    /**
     * Show that the EmployeeSkill can be updated successfully.
     */
    @Test
    public void shouldSuccessfullyUpdateAnEmployeeSkill() {
        EmployeeSkill employeeSkill = getEmployeeSkill();
        given(employeeSkillRepository.findById(isA(Long.class))).willReturn(Optional.of(employeeSkill));

        this.employeeSkillService.updateEmployeeSkill(employeeSkill);

        verify(employeeSkillRepository, times(1)).saveAndFlush(isA(EmployeeSkill.class));
        verify(employeeSkillRepository, times(1)).findById(isA(Long.class));
        verifyNoMoreInteractions(employeeRepository);
    }

    /**
     * Show that the EmployeeSkill can be deleted successfully.
     */
    @Test
    public void shouldSuccessfullyDeleteAnEmployeeSkill() {
        given(employeeSkillRepository.findById(isA(Long.class))).willReturn(Optional.of(getEmployeeSkill()));

        this.employeeSkillService.deleteEmployeeSkill(TEST_EMPLOYEE_SKILL_ID);

        verify(employeeSkillRepository, times(1)).deleteById(isA(Long.class));
        verify(employeeSkillRepository, times(1)).findById(isA(Long.class));
        verifyNoMoreInteractions(employeeSkillRepository);
    }

    /**
     * Method to get an EmployeeSkill instance
     *
     * @return
     */
    private EmployeeSkill getEmployeeSkill(){
        return EmployeeSkill.builder().id(TEST_EMPLOYEE_SKILL_ID).build();
    }

    /**
     * Method to get an employee instance
     *
     * @return
     */
    private Employee getEmployee(){
        return Employee.builder().id(TEST_EMPLOYEE_ID).build();
    }

    /**
     * Method to get an SkillDetailRequest instance
     *
     * @return
     */
    private SkillDetailRequest getSkillDetailRequest(){
        return SkillDetailRequest.builder().skillId(TEST_SKILL_ID).skillLevel(SkillLevel.EXPERT).build();
    }

    /**
     * Method to get an Skill instance
     *
     * @return
     */
    private Skill getSkill(){
        return Skill.builder().id(TEST_SKILL_ID).build();
    }

}
