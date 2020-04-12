package com.kingshuk.beanmapping.mapstruct.service;

import com.kingshuk.beanmapping.mapstruct.entities.AccountEntity;
import com.kingshuk.beanmapping.mapstruct.entities.SimpleAccountEntity;

public interface AccountService {

	void addAccount(SimpleAccountEntity simpleAccount);

	void addAccount(AccountEntity account);

}