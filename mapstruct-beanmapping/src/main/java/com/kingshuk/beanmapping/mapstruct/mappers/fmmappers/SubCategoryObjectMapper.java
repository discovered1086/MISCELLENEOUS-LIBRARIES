package com.kingshuk.beanmapping.mapstruct.mappers.fmmappers;

import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.kingshuk.beanmapping.mapstruct.entities.SubCategoryEntity;
import com.kingshuk.beanmapping.mapstruct.pojos.SubCategoryDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
				nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface SubCategoryObjectMapper {
	
	@Mapping(target = "subCategoryEffectiveDate", source = "subCategoryEffectiveDate", qualifiedByName = "convertEffectiveDate")
	@Mapping(target = "subCategoryTerminationDate", source = "subCategoryTerminationDate", qualifiedByName = "convertTerminationDate",
			nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
			nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
	SubCategoryEntity mapDtoToEntity(SubCategoryDTO subCategoryDTO);

	@Mapping(target = "subCategoryTerminationDate", source = "subCategoryTerminationDate", qualifiedByName = "convertDate",
			nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
			nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
	@Mapping(target = "subCategoryEffectiveDate", source = "subCategoryEffectiveDate", qualifiedByName = "convertDate")
	SubCategoryDTO mapEntityToDto(SubCategoryEntity subCategoryEntity);
	
	@Named("convertDate")
	default ZonedDateTime convertSubCategoryEffectiveDate(OffsetDateTime dateTime) {
		return dateTime.toZonedDateTime();
	}
	
	@Named("convertEffectiveDate")
	default OffsetDateTime convertSubCategoryEntityEffectiveDate(ZonedDateTime dateTime) {
		Optional<ZonedDateTime> categoryEffectiveDate = Optional.ofNullable(dateTime);
		
		return categoryEffectiveDate.orElse(ZonedDateTime.now().with(LocalTime.MIDNIGHT)).toOffsetDateTime();
	}
	
	@Named("convertTerminationDate")
	default OffsetDateTime convertSubCategoryEntityTerminationDate(ZonedDateTime dateTime) {
		return dateTime.toOffsetDateTime();
	}
	
}