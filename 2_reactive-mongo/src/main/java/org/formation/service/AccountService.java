package org.formation.service;

import org.formation.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountService {

	@Autowired
	ReactiveMongoTemplate template;

	public Mono<Account> findById(String id) {
		return template.findById(id, Account.class);
	}

	public Flux<Account> findAll() {
		return template.findAll(Account.class);
	}

	public Mono<Account> save(Mono<Account> account) {
		return template.save(account);
	}

	public Flux<Account> findByValue(Double value) {
		Query query = new Query();
		query.addCriteria(Criteria.where("value").is(value));
		return template.find(query,Account.class);
	}
}
