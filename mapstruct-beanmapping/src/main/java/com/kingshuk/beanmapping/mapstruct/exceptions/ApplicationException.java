package com.kingshuk.beanmapping.mapstruct.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ApplicationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7097679708956227777L;

	
}
