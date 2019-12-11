package com.springboot.enterpriseCredit.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.springboot.enterpriseCredit.document.EnterpriseCredit;

public interface EnterpriseCreditRepo extends ReactiveMongoRepository<EnterpriseCredit, String> {

}
