package com.uk.nhs.employee.service;

import com.uk.nhs.employee.entity.Skill;

import java.util.List;


/**
 * Interface for SkillService to handle the skill operations.
 *
 * @author Anoop Abraham
 *
 */
public interface SkillService {

    /**
     * Method to create the Skill entity.
     *
     * @param skill
     */
    public void createSkill(Skill skill);

    /**
     * Method to fetch the Skill for the id.
     *
     * @param id
     * @return
     */
    public Skill getSkill(Long id);


    /**
     * Method to fetch all the Skills.
     *
     *  @param pageNo
     *  @param pageSize
     * @return
     */
    public List<Skill> getAllSkills(Integer pageNo, Integer pageSize);

    /**
     * Method to update the Skill entity.
     *
     * @param skill
     */
    public void updateSkill(Skill skill);

    /**
     * Method to delete the Skill for the id.
     *
     * @param id
     * @return
     */
    public void deleteSkill(Long id);

}
