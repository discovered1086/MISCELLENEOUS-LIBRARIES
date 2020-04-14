package com.kingshuk.beanmapping.mapstruct.mappers.fmmappers;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.kingshuk.beanmapping.mapstruct.entities.AccountEntity;
import com.kingshuk.beanmapping.mapstruct.pojos.AccountDTO;

public abstract class AccountObjectsMapperDecorator implements AccountObjectsMapper {

	@Autowired
	@Qualifier("delegate")
	private AccountObjectsMapper delegate;

	@Override
	public AccountEntity mapDtoToEntity(AccountDTO source) {
		AccountEntity destination = delegate.mapDtoToEntity(source);
		ZonedDateTime accountOpenningDate = source.getAccountOpenningDate();

		if (Objects.nonNull(accountOpenningDate)) {
			String openningDate = accountOpenningDate.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

			destination.setAccountOpenningDate(ZonedDateTime.parse(openningDate));
		}

		ZonedDateTime accountClosingDate = source.getAccountClosingDate();

		if (Objects.nonNull(accountClosingDate)) {
			String closingDate = accountClosingDate.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

			destination.setAccountClosingDate(ZonedDateTime.parse(closingDate));
		}
		return destination;
	}

	@Override
	public AccountDTO mapEntityToDto(AccountEntity source) {
		AccountDTO destination = delegate.mapEntityToDto(source);
		ZonedDateTime accountOpenningDate = source.getAccountOpenningDate();

		if (Objects.nonNull(accountOpenningDate)) {
			String openningDate = accountOpenningDate.format(DateTimeFormatter.ISO_DATE_TIME);

			destination.setAccountOpenningDate(ZonedDateTime.parse(openningDate));
		}

		ZonedDateTime accountClosingDate = source.getAccountClosingDate();

		if (Objects.nonNull(accountClosingDate)) {
			String closingDate = accountClosingDate.format(DateTimeFormatter.ISO_DATE_TIME);

			destination.setAccountClosingDate(ZonedDateTime.parse(closingDate));
		}

		return destination;
	}

}
