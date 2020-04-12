package com.kingshuk.beanmapping.mapstruct.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.kingshuk.beanmapping.mapstruct.entities.SimpleAccountEntity;
import com.kingshuk.beanmapping.mapstruct.pojos.SimpleAccountDTO;

@Mapper
public interface SimpleAccountMapper {
	SimpleAccountMapper INSTANCE = Mappers.getMapper(SimpleAccountMapper.class);
	SimpleAccountEntity mapSimpleDTOToEntity(SimpleAccountDTO simpleDto);
	SimpleAccountDTO mapSimpleEntityToDto(SimpleAccountEntity simpleEntity);
}
