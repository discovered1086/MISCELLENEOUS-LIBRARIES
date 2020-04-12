package com.kingshuk.beanmapping.mapstruct.service;

import com.kingshuk.beanmapping.mapstruct.entities.CurrencyEntity;

public interface ICurrencyService {

	CurrencyEntity getCurrencyEntity(String currencyCode);
	
	void addNewCurrency(CurrencyEntity currencyEntity);
}
