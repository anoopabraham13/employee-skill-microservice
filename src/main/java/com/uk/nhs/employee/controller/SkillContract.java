package com.uk.nhs.employee.controller;

import com.uk.nhs.employee.entity.Skill;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This interface defines the API contract definition for the skill service.
 *
 * @author Anoop Abraham
 */
@Api(value = "/api/skill", tags = { "NHS Skill Management" })
public interface SkillContract {

    /**
     * Method to Create the Skill entity.
     *
     * @param skill
     * @return
     */
    @ApiOperation(value = "Create a new Skill")
    public ResponseEntity<Void>  createSkill(@RequestBody Skill skill);


    /**
     * Method to Fetch the Skill for the id.
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "Get the Skill for the id")
    public ResponseEntity<Skill> getSkill(@ApiParam(value = "Skill id", required = true) Long id);

    /**
     * Method to Fetch all the Skills.
     *
     * @param pageNo
     * @param pageSize
     *
     * @return
     */
    @ApiOperation(value = "Fetch all the Skills")
    public ResponseEntity<List<Skill>> getAllSkills(
            @RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize);

    /**
     * Method to Update the the Skill for the id.
     *
     * @param skill
     * @return
     */
    @ApiOperation(value = "Update the the Skill")
    public ResponseEntity<Void> updateSkill(@RequestBody Skill skill);

    /**
     * Method to Delete the Skill for the id.
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "Delete the Skill for the id")
    public ResponseEntity<Void> deleteSkill(@ApiParam(value = "Skill id", required = true) Long id);

}
