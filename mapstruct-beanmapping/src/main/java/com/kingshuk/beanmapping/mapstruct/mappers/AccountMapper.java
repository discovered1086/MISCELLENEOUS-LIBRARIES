package com.kingshuk.beanmapping.mapstruct.mappers;

import org.mapstruct.Mapper;

import com.kingshuk.beanmapping.mapstruct.entities.AccountEntity;
import com.kingshuk.beanmapping.mapstruct.pojos.AccountDTO;

@Mapper(componentModel = "spring", uses = CurrencyMapper.class)
public interface AccountMapper {

	AccountEntity mapDtoToEntity(AccountDTO accountDto);

}