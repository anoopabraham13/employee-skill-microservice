package com.uk.nhs.employee.requestmodel;


import com.uk.nhs.employee.entity.domain.SkillLevel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request model used to add Skill details
 *
 * @author Anoop Abraham
 *
 */
@ApiModel(description = "Add the skill details of an Employee")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillDetailRequest {

    /** The Skill id */
    @ApiModelProperty(notes = "Employee id", required = true, position = 1)
    private Long skillId;

    /** The skill level */
    @ApiModelProperty(notes = "Employee id", required = true, position = 2)
    private SkillLevel skillLevel;
}
