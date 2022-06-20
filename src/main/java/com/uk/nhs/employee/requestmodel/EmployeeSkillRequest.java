package com.uk.nhs.employee.requestmodel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 * Request model used to add the skills of an Employee
 *
 * @author Anoop Abraham
 *
 */
@ApiModel(description = "Add the skill of an Employee")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSkillRequest {


    /** The Employee id */
    @ApiModelProperty(notes = "Employee id", required = true, position = 1)
    private Long employeeId;


    /** The list of skills */
    @ApiModelProperty(notes = "List of employee skills", required = false, position = 2)
    private Collection<SkillDetailRequest> skillDetailRequest;

}
