package com.kingshuk.beanmapping.mapstruct.enums;

import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DateFormatEnum {
	ISO_DATE_TIME(DateTimeFormatter.ISO_DATE_TIME);
	
	private DateTimeFormatter dateTimeFormatter;
}
