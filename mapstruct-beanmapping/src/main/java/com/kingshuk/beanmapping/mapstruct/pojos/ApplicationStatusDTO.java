package com.kingshuk.beanmapping.mapstruct.pojos;

import java.io.Serializable;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.kingshuk.beanmapping.mapstruct.enums.ApplicationStatusType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
@JsonRootName("applicationStatus")
@JsonPropertyOrder(value = {"statusCode","typeOfStatus","statusMessage","statusNotes"})
public class ApplicationStatusDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 995642617560236207L;

	private String statusCode;

	@JsonInclude(Include.NON_NULL)
	private ApplicationStatusType typeOfStatus;

	@JsonInclude(Include.NON_NULL)
	private String statusNotes;

	@JsonInclude(Include.NON_NULL)
	private String statusMessage;

	@JsonIgnore
	private ZonedDateTime statusTypeEffectiveDate;

	@JsonIgnore
	private ZonedDateTime statusTypeTerminationDate;

}
