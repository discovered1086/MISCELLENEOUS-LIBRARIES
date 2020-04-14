package com.kingshuk.beanmapping.mapstruct.mappers.fmmappers;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import com.kingshuk.beanmapping.mapstruct.entities.AccountEntity;
import com.kingshuk.beanmapping.mapstruct.pojos.AccountDTO;

@Mapper(componentModel = "spring", uses = CurrencyObjectsMapper.class)
@DecoratedWith(AccountObjectsMapperDecorator.class)
public interface AccountObjectsMapper {

	AccountEntity mapDtoToEntity(AccountDTO accountDto);
	
	AccountDTO mapEntityToDto(AccountEntity accountEntity);
	
}