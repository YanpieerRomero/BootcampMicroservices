package com.everis.account.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.everis.account.controller.AccountController;
import com.everis.account.dto.AccountRequest;
import com.everis.account.entity.Account;
import com.everis.account.entity.Currency;
import com.everis.account.exception.NotFoundException;
import com.everis.account.repository.AccountRepository;
import com.everis.account.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

	private static List<AccountRequest> accountList = new ArrayList<AccountRequest>();
	
	private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public AccountRequest create(AccountRequest account) {
		Account accountEntity = new Account();
		BeanUtils.copyProperties(account, accountEntity);
		accountRepository.save(accountEntity);
		account.setId(accountEntity.getId());
		log.info("============================"+account.getId()+"============================");
		return account;
	}

	@Override
	public ResponseEntity<AccountRequest> update(AccountRequest account) {
		Account accountEntity = new Account();
		BeanUtils.copyProperties(account, accountEntity);
		accountRepository.save(accountEntity);
		BeanUtils.copyProperties(accountEntity, account);
		return new ResponseEntity<AccountRequest>(account, HttpStatus.OK);
	}

	@Override
	public AccountRequest get(Integer id) throws NotFoundException {
		/*List<Account> account = getAll();
		AccountRequest item = new AccountRequest();
		BeanUtils.copyProperties(item, account);*/
		accountList.add(new AccountRequest(1, "ONE ACCOUNT", new Date()));
		accountList.add(new AccountRequest(2, "TWO ACCOUNT", new Date()));
		accountList.add(new AccountRequest(3, "THREE ACCOUNT", new Date()));
		for (AccountRequest item: accountList) {
			if (item.getId().equals(id)) {
				return item;
			}
		}
		throw new NotFoundException("id " + id);
	}

	@Override
	public List<Account> getAll() {
		return accountRepository.findAll();
	}

	@Override
	public void delete(Integer id) throws NotFoundException {
		Optional<Account> accountOptional = accountRepository.findById(id);
		if(!accountOptional.isPresent()) {
			throw new NotFoundException("id " + id);
		}
		accountRepository.deleteById(id);
	}

	@Override
	public List<Currency> getCurrencies(Integer accountId) throws NotFoundException {
		Optional<Account> accountOptional = accountRepository.findById(accountId);
		
		if(!accountOptional.isPresent()) {
			throw new NotFoundException("id " + accountId);
		}
		
		return accountOptional.get().getCurrencies();
	}

}
