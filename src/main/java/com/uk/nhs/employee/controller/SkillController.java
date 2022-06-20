package com.uk.nhs.employee.controller;

import com.uk.nhs.employee.entity.Skill;
import com.uk.nhs.employee.service.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * This is the implementation of SkillContract class having the logic for various end points.
 *
 * @author Anoop Abraham
 *
 */
@RestController
@RequestMapping("/api/skill")
public class SkillController implements SkillContract {


	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SkillController.class);

	/**
	 * Instance variable to hold SkillService instance.
	 */
	@Autowired
	private SkillService skillService;

	@Override
	@PostMapping("/create")
	public ResponseEntity<Void> createSkill(@RequestBody Skill skill) {
		LOGGER.debug("creating skill");
		skillService.createSkill(skill);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}


	@Override
	@GetMapping(value = "/{id}")
	public ResponseEntity<Skill> getSkill(@PathVariable("id") final Long id) {
		LOGGER.debug("getting skill for the id {}", id);
		Skill skill = skillService.getSkill(id);
		if (skill == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(skill, HttpStatus.OK);
	}

	@Override
	@GetMapping
	public ResponseEntity<List<Skill>> getAllSkills(
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
		LOGGER.debug("getting all the skills");
		List<Skill> skillList = skillService.getAllSkills(pageNo, pageSize);
		return new ResponseEntity<>(skillList, HttpStatus.OK);
	}

	@Override
	@PutMapping(value = "/update")
	public ResponseEntity<Void> updateSkill(@RequestBody Skill skill) {
		LOGGER.debug("updating the skill for the id {}", skill.getId());
		skillService.updateSkill(skill);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteSkill(@PathVariable("id") final Long id) {
		skillService.deleteSkill(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
