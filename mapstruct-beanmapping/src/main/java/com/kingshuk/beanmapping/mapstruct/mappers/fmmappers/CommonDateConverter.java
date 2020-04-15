package com.kingshuk.beanmapping.mapstruct.mappers.fmmappers;

import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CommonDateConverter {

	@Named("convertDate")
	default ZonedDateTime convertDate(OffsetDateTime dateTime) {
		return dateTime.toZonedDateTime();
	}

	@Named("convertEffectiveDate")
	default OffsetDateTime convertEffectiveDate(ZonedDateTime dateTime) {
		Optional<ZonedDateTime> effectiveDate = Optional.ofNullable(dateTime);

		return effectiveDate.orElse(ZonedDateTime.now().with(LocalTime.MIDNIGHT)).toOffsetDateTime();
	}

	@Named("convertTerminationDate")
	default OffsetDateTime convertTerminationDate(ZonedDateTime dateTime) {
		return dateTime.toOffsetDateTime();
	}
}
