package com.kingshuk.beanmapping.mapstruct.mappers.fmmappers;

import javax.annotation.Resource;
import javax.money.CurrencyUnit;
import javax.money.Monetary;

import org.mapstruct.Mapper;

import com.kingshuk.beanmapping.mapstruct.entities.CurrencyEntity;
import com.kingshuk.beanmapping.mapstruct.exceptions.ApplicationException;
import com.kingshuk.beanmapping.mapstruct.service.ICurrencyService;

@Mapper(componentModel = "spring")
public abstract class CurrencyObjectsMapper {

	@Resource(name = "currencyService")
	private ICurrencyService currencyService;

	protected CurrencyEntity mapCurrencyUnitToCurrency(CurrencyUnit currencyUnit) throws ApplicationException {
		String currencyCode = currencyUnit.getCurrencyCode();

		return currencyService.getCurrencyEntity(currencyCode);
	}

	protected CurrencyUnit mapCurrencyToCurrencyUnit(CurrencyEntity currencyEntity) throws ApplicationException {
		return Monetary.getCurrency(currencyEntity.getCurrencyCode());
	}
}
