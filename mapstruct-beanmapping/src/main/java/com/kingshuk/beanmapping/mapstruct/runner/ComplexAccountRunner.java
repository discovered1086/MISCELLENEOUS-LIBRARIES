package com.kingshuk.beanmapping.mapstruct.runner;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.money.Monetary;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.kingshuk.beanmapping.mapstruct.entities.CurrencyEntity;
import com.kingshuk.beanmapping.mapstruct.pojos.AccountDTO;
import com.kingshuk.beanmapping.mapstruct.service.AccountService;
import com.kingshuk.beanmapping.mapstruct.service.ICurrencyService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ComplexAccountRunner implements CommandLineRunner {

	private boolean runDataScript = false;

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

		AccountDTO accountDto = AccountDTO.builder().accountNumber("30911566592")
				.accountDescription("SBI Another account")
				.accountOpenningDate(ZonedDateTime.now().truncatedTo(ChronoUnit.SECONDS))
				.currency(Monetary.getCurrency("INR")).build();

		AccountDTO savedDto = accountService.addAccount(accountDto);

		log.info("The saved Entity is: ", savedDto.toString());

	}

}
