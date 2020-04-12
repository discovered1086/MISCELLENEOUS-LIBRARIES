package com.kingshuk.beanmapping.mapstruct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kingshuk.beanmapping.mapstruct.entities.AccountEntity;
import com.kingshuk.beanmapping.mapstruct.entities.SimpleAccountEntity;
import com.kingshuk.beanmapping.mapstruct.repository.AccountRepository;
import com.kingshuk.beanmapping.mapstruct.repository.SimpleAccountRepository;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Autowired
	private SimpleAccountRepository simpleAccountRepository;
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
	public void addAccount(SimpleAccountEntity simpleAccount) {
		simpleAccountRepository.save(simpleAccount);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
	public void addAccount(AccountEntity account) {
		accountRepository.save(account);
	}

}
