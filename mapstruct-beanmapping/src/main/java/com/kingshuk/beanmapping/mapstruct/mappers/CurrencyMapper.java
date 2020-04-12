package com.kingshuk.beanmapping.mapstruct.mappers;

import javax.annotation.Resource;
import javax.money.CurrencyUnit;
import javax.money.Monetary;

import org.mapstruct.Mapper;

import com.kingshuk.beanmapping.mapstruct.entities.CurrencyEntity;
import com.kingshuk.beanmapping.mapstruct.service.ICurrencyService;

@Mapper(componentModel = "spring")
public abstract class CurrencyMapper {

	@Resource(name = "currencyService")
	private ICurrencyService currencyService;

	protected CurrencyEntity mapCurrencyUnitToCurrency(CurrencyUnit currencyUnit) {
		String currencyCode = currencyUnit.getCurrencyCode();

		return currencyService.getCurrencyEntity(currencyCode);
	}

	protected CurrencyUnit mapCurrencyToCurrencyUnit(CurrencyEntity currencyEntity) {
		return Monetary.getCurrency(currencyEntity.getCurrencyCode());
	}
}
