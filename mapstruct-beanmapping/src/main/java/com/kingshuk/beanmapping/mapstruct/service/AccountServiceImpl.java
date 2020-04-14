package com.kingshuk.beanmapping.mapstruct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kingshuk.beanmapping.mapstruct.entities.AccountEntity;
import com.kingshuk.beanmapping.mapstruct.entities.SimpleAccountEntity;
import com.kingshuk.beanmapping.mapstruct.mappers.fmmappers.AccountObjectsMapper;
import com.kingshuk.beanmapping.mapstruct.pojos.AccountDTO;
import com.kingshuk.beanmapping.mapstruct.repository.AccountRepository;
import com.kingshuk.beanmapping.mapstruct.repository.SimpleAccountRepository;

import lombok.extern.slf4j.Slf4j;

@Service("accountService")
@Slf4j
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountObjectsMapper accountMapper;

	@Autowired
	private SimpleAccountRepository simpleAccountRepository;
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addAccount(SimpleAccountEntity simpleAccount) {
		simpleAccountRepository.save(simpleAccount);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public AccountDTO addAccount(AccountDTO account) {
		log.info("The account Dto is: ", account.toString());

		AccountEntity accountEntity = accountMapper.mapDtoToEntity(account);

		AccountEntity savedEntity = accountRepository.save(accountEntity);
		
		return accountMapper.mapEntityToDto(savedEntity);
		
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public AccountEntity getAccountEntity(String accountNumber) {
		return accountRepository.findByAccountNumber(accountNumber);
	}

}
