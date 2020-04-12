package com.kingshuk.beanmapping.mapstruct.runner;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.kingshuk.beanmapping.mapstruct.entities.CurrencyEntity;
import com.kingshuk.beanmapping.mapstruct.entities.SimpleAccountEntity;
import com.kingshuk.beanmapping.mapstruct.pojos.SimpleAccountDTO;
import com.kingshuk.beanmapping.mapstruct.service.AccountService;
import com.kingshuk.beanmapping.mapstruct.service.ICurrencyService;
import com.kingshuk.beanmapping.mapstruct.simplemapping.SimpleAccountMapper;

@Component
public class SimpleAccountRunner implements CommandLineRunner {

	private boolean runDataScript = true;

	@Resource(name = "currencyService")
	private ICurrencyService currencyService;

	@Resource(name = "accountService")
	private AccountService accountService;

	@Override
	public void run(String... args) throws Exception {
		if (runDataScript) {

			List<CurrencyEntity> currencies = new ArrayList<>();

			CurrencyEntity currencyEntity = CurrencyEntity.builder().country("India").currencyCode("INR")
					.description("Indian Rupee").numericCode("356").build();

			currencies.add(currencyEntity);

			currencies.forEach(currency -> currencyService.addNewCurrency(currencyEntity));

		}
		SimpleAccountDTO simpleAccount = SimpleAccountDTO.builder().accountNumber("30911566592")
				.accountDescription("SBI Account").accountOpenningDate(ZonedDateTime.now()).build();

		SimpleAccountEntity simpleAccountEntity = SimpleAccountMapper.INSTANCE.mapSimpleDTOToEntity(simpleAccount);

		accountService.addAccount(simpleAccountEntity);
	}

}
