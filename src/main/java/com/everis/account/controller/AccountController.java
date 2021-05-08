package com.everis.account.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.everis.account.dto.AccountRequest;
import com.everis.account.dto.AccountRequest2;
import com.everis.account.entity.Account;
import com.everis.account.entity.Currency;
import com.everis.account.exception.NotFoundException;
import com.everis.account.service.AccountService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
@RequestMapping(value = "/accounts",
		consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
		produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	private static final Logger log = LoggerFactory.getLogger(AccountController.class);

	@PostMapping
	public ResponseEntity<AccountRequest> create(@Valid @RequestBody AccountRequest account) {

		AccountRequest accountRequest = accountService.create(account);

		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(accountRequest.getId())
					.toUri();
		return ResponseEntity.created(uri).build();
		// new ResponseEntity<AccountRequest>(account, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<AccountRequest> update(@RequestBody AccountRequest account) {
		//return new ResponseEntity<AccountRequest>(account, HttpStatus.OK);
		return accountService.update(account);
	}

	@GetMapping(consumes = MediaType.ALL_VALUE)
	public List<Account> getAll() {
		return accountService.getAll();
	}

	@GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE, produces = { "application/vnd.company.app-v1+json", "application/vnd.company.app-v1+xml"} /*headers = "X-API-VERSION=1"*/)
	public MappingJacksonValue getV1One(@PathVariable("id") Integer id) throws NotFoundException {

		AccountRequest account = accountService.get(id);

		//En esta fila se especifica que varios atributos se van a filtrar y mostrar normal
		SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name");

		//En esta fila se registra aquello que quieres que se fitre y aparte se coloca el identificador de la clase a filtrar @JsonFilter("accountRequest")
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("accountRequest",simpleBeanPropertyFilter);

		// En esta linea de código se aplica filtros a un objeto especifico, en otras palabras se define a que objeto se le aplica el filtro
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(account);

		//Filtro aplicado
		mappingJacksonValue.setFilters(filterProvider);

		return mappingJacksonValue;
	}

	// Versionamiento de servicio
	@GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE, produces = { "application/vnd.company.app-v2+json", "application/vnd.company.app-v2+xml" } /*params = "version=2"*/)
	public AccountRequest2 getV2One(@PathVariable("id") Integer id) throws NotFoundException {

		return new AccountRequest2(id, "CURRENT", "ACCOUNT");
	}

	@DeleteMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws NotFoundException {
		accountService.delete(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/{id}/currencies", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<Currency>> getAllCurrencies(@PathVariable("id") Integer id) throws NotFoundException {
		return new ResponseEntity<List<Currency>>(accountService.getCurrencies(id), HttpStatus.OK);
	}

}
