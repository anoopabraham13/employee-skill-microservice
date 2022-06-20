package com.uk.nhs.employee.service;

import com.uk.nhs.employee.entity.Skill;
import com.uk.nhs.employee.entity.repository.SkillRepository;
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
 * Test cases for SkillServiceImpl class
 *
 * @author  Anoop Abraham
 */
@RunWith(MockitoJUnitRunner.class)
public class SkillServiceImplTest {


    /** Test values. */
    private static final Long TEST_SKILL_ID = Long.valueOf(1);

    @InjectMocks
    private SkillServiceImpl skillService;

    @Mock
    SkillRepository skillRepository;

    /**
     * Show that a new Skill can be successfully saved.
     */
    @Test
    public void shouldSuccessfullySaveNewSkill() {
        Skill skill = getSkill();
        given(skillRepository.saveAndFlush(isA(Skill.class))).willReturn(skill);

        this.skillService.createSkill(skill);

        verify(skillRepository, times(1)).saveAndFlush(isA(Skill.class));
        verifyNoMoreInteractions(skillRepository);
    }

    /**
     * Show that an Skill can be successfully retrieved.
     */
    @Test
    public void shouldSuccessfullyGetSkill() {
        given(skillRepository.findById(isA(Long.class))).willReturn(Optional.of(getSkill()));

        Skill skillResponse = this.skillService.getSkill(TEST_SKILL_ID);

        assertNotNull("response should not be null", skillResponse);
        assertEquals("ID's doesn't match", TEST_SKILL_ID, skillResponse.getId());

        verify(skillRepository, times(1)).findById(isA(Long.class));
        verifyNoMoreInteractions(skillRepository);
    }

    /**
     * Show that all the Skills can be successfully retrieved.
     */
    @Test
    public void shouldSuccessfullyGetAllSkills() {
        List<Skill> skills = new ArrayList<>();
        skills.add(getSkill());
        Page<Skill> employeePagedResult = new PageImpl<>(skills);
        given(skillRepository.findAll(isA(Pageable.class))).willReturn(employeePagedResult);

        List<Skill> skillList = this.skillService.getAllSkills(0,1);

        assertNotNull("response should not be null", skillList);
        assertEquals("Size doesn't match", 1, skillList.size());

        verify(skillRepository, times(1)).findAll(isA(Pageable.class));
        verifyNoMoreInteractions(skillRepository);
    }

    /**
     * Show that the Skill can be updated successfully.
     */
    @Test
    public void shouldSuccessfullyUpdateAnSkill() {
        Skill skill = getSkill();
        given(skillRepository.findById(isA(Long.class))).willReturn(Optional.of(skill));

        this.skillService.updateSkill(skill);

        verify(skillRepository, times(1)).saveAndFlush(isA(Skill.class));
        verify(skillRepository, times(1)).findById(isA(Long.class));
        verifyNoMoreInteractions(skillRepository);
    }

    /**
     * Show that the Skill can be deleted successfully.
     */
    @Test
    public void shouldSuccessfullyDeleteAnSkill() {
        given(skillRepository.findById(isA(Long.class))).willReturn(Optional.of(getSkill()));

        this.skillService.deleteSkill(TEST_SKILL_ID);

        verify(skillRepository, times(1)).deleteById(isA(Long.class));
        verify(skillRepository, times(1)).findById(isA(Long.class));
        verifyNoMoreInteractions(skillRepository);
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
