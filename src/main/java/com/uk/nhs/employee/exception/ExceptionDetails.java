package com.uk.nhs.employee.exception;


import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * ExceptionDetails POJO class.
 * 
 * @author Anoop Abraham
 *
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ExceptionDetails {

	private Timestamp timeStamp;
	private int status;
	private String message;
	private String details;

}
