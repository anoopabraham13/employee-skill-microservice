package com.uk.nhs.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.uk.nhs.employee.entity.Skill;
import com.uk.nhs.employee.exception.EntityNotFoundException;
import com.uk.nhs.employee.entity.repository.SkillRepository;
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


/**
 * Implementation for SkillService interface.
 *
 * @author Anoop Abraham
 *
 */
@Service
public class SkillServiceImpl implements SkillService {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SkillServiceImpl.class);

	/**
	 * Instance variable for skillRepository
	 */
	@Autowired
	SkillRepository skillRepository;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void createSkill(Skill skill) {
		skillRepository.saveAndFlush(skill);
		LOGGER.debug("created the new skill");
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Skill getSkill(Long id) {
		LOGGER.debug("getting skill for the id {}", id);
		Optional<Skill> skills = skillRepository.findById(id);
		if (skills.isPresent())
			return skills.get();

		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Skill> getAllSkills(Integer pageNo, Integer pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("skillName"));
		Page<Skill> skillsPagedResult = skillRepository.findAll(paging);

		if(skillsPagedResult.hasContent()) {
			return skillsPagedResult.getContent();
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateSkill(Skill skill) {
		Optional<Skill> skills = skillRepository.findById(skill.getId());
		if (!skills.isPresent())
			throw new EntityNotFoundException("Skill record is not available for the given id");
		skillRepository.saveAndFlush(skill);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteSkill(Long id) {
		Optional<Skill> skills = skillRepository.findById(id);

		if (skills.isPresent()) {
			skillRepository.deleteById(id);
			LOGGER.debug("deleted the skill for the id {}", id);
		}else {
			throw new EntityNotFoundException("the skill is not available for the given id");
		}
	}

}
