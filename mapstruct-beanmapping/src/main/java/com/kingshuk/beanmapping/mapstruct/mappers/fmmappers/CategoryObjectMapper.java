package com.kingshuk.beanmapping.mapstruct.mappers.fmmappers;

import java.util.HashSet;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.kingshuk.beanmapping.mapstruct.entities.CategoryEntity;
import com.kingshuk.beanmapping.mapstruct.entities.CategoryEntity.CategoryEntityBuilder;
import com.kingshuk.beanmapping.mapstruct.entities.SubCategoryEntity;
import com.kingshuk.beanmapping.mapstruct.pojos.CategoryDTO;
import com.kingshuk.beanmapping.mapstruct.pojos.SubCategoryDTO;

@Mapper(componentModel = "spring", uses = { CommonDateConverter.class,
		SubCategoryObjectMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public abstract class CategoryObjectMapper {

	@Autowired
	@Qualifier("subCategoryMapper")
	private SubCategoryObjectMapper subCategoryObjectMapper;

	@Autowired
	private CommonDateConverter commonDateConverter;

	public CategoryEntity mapDtoToEntity(CategoryDTO categoryDTO) {
		if (categoryDTO == null) {
			return null;
		}

		CategoryEntityBuilder categoryEntity = CategoryEntity.builder();

		categoryEntity.categoryEffectiveDate(
				commonDateConverter.convertEffectiveDate(categoryDTO.getCategoryEffectiveDate()));
		if (categoryDTO.getCategoryTerminationDate() != null) {
			categoryEntity.categoryTerminationDate(
					commonDateConverter.convertTerminationDate(categoryDTO.getCategoryTerminationDate()));
		} else {
			categoryEntity.categoryTerminationDate(null);
		}

		categoryEntity.categoryId(categoryDTO.getCategoryId());
		categoryEntity.categoryName(categoryDTO.getCategoryName());
		categoryEntity.categoryDescription(categoryDTO.getCategoryDescription());
		categoryEntity.categoryTransactionType(categoryDTO.getCategoryTransactionType());
		CategoryEntity categoryEntityObject = categoryEntity.build();
		categoryEntity.subCategoryEntities(
				subCategoryDTOSetToSubCategoryEntitySet(categoryDTO.getSubCategories(), categoryEntityObject));

		return categoryEntityObject;
	}

	private Set<SubCategoryEntity> subCategoryDTOSetToSubCategoryEntitySet(Set<SubCategoryDTO> subCategories,
			CategoryEntity entity) {
		if (subCategories == null) {
			return null;
		}

		Set<SubCategoryEntity> set1 = new HashSet<>(Math.max((int) (subCategories.size() / .75f) + 1, 16));
		for (SubCategoryDTO subCategoryDTO : subCategories) {
			SubCategoryEntity subCategoryEntity = subCategoryObjectMapper.mapDtoToEntity(subCategoryDTO);
			subCategoryEntity.setParentCategory(entity);
			set1.add(subCategoryEntity);
		}

		return set1;
	}

	@Mapping(target = "subCategories", source = "subCategoryEntities")
	@Mapping(target = "categoryTerminationDate", source = "categoryTerminationDate", qualifiedByName = "convertDate", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
	@Mapping(target = "categoryEffectiveDate", source = "categoryEffectiveDate", qualifiedByName = "convertDate")
	public abstract CategoryDTO mapEntityToDto(CategoryEntity categoryEntity);

}