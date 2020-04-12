package com.kingshuk.beanmapping.mapstruct.service;

import com.kingshuk.beanmapping.mapstruct.entities.AccountEntity;
import com.kingshuk.beanmapping.mapstruct.entities.SimpleAccountEntity;
import com.kingshuk.beanmapping.mapstruct.pojos.AccountDTO;

public interface AccountService {

	void addAccount(SimpleAccountEntity simpleAccount);

	AccountDTO addAccount(AccountDTO account);
	
	AccountEntity getAccountEntity(String accountNumber); 

}