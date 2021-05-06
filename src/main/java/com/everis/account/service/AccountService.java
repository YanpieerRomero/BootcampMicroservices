package com.everis.account.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.everis.account.dto.AccountRequest;
import com.everis.account.entity.Account;
import com.everis.account.entity.Currency;
import com.everis.account.exception.NotFoundException;

public interface AccountService {
	public AccountRequest create(AccountRequest account);
	public ResponseEntity<AccountRequest> update(AccountRequest account);
	public AccountRequest get(Integer id) throws NotFoundException;
	public List<Account> getAll();
	public void delete(Integer id) throws NotFoundException;
	public List<Currency> getCurrencies(Integer accountId) throws NotFoundException;
}
