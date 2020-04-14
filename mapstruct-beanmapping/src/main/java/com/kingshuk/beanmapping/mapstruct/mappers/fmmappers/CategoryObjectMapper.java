package com.kingshuk.beanmapping.mapstruct.mappers.fmmappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.kingshuk.beanmapping.mapstruct.entities.CategoryEntity;
import com.kingshuk.beanmapping.mapstruct.pojos.CategoryDTO;

@Mapper(componentModel = "spring", uses = SubCategoryObjectMapper.class, 
		unmappedTargetPolicy = ReportingPolicy.IGNORE, 
		nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface CategoryObjectMapper {

	@Mapping(target = "subCategoryEntities", source = "subCategories")
	@Mapping(target = "categoryEffectiveDate", source = "categoryEffectiveDate", qualifiedByName = "convertEffectiveDate")
	@Mapping(target = "categoryTerminationDate", source = "categoryTerminationDate", qualifiedByName = "convertTerminationDate",
					nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
					nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
	public abstract CategoryEntity mapDtoToEntity(CategoryDTO categoryDTO);

	@Mapping(target = "subCategories", source = "subCategoryEntities")
	@Mapping(target = "categoryTerminationDate", source = "categoryTerminationDate", qualifiedByName = "convertDate",
			nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
			nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
	@Mapping(target = "categoryEffectiveDate", source = "categoryEffectiveDate", qualifiedByName = "convertDate")
	public abstract CategoryDTO mapEntityToDto(CategoryEntity categoryEntity);

}