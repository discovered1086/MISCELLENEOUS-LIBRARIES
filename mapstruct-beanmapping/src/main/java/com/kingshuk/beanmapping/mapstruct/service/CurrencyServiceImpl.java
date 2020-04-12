package com.kingshuk.beanmapping.mapstruct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kingshuk.beanmapping.mapstruct.entities.CurrencyEntity;
import com.kingshuk.beanmapping.mapstruct.repository.CurrencyRepository;

@Service("currencyService")
public class CurrencyServiceImpl implements ICurrencyService {
	
	@Autowired
	private CurrencyRepository currencyRepository;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public CurrencyEntity getCurrencyEntity(String currencyCode) {
		return currencyRepository.getOne(currencyCode);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addNewCurrency(CurrencyEntity currencyEntity) {
		currencyRepository.save(currencyEntity);
	}

}
