package com.springboot.enterpriseCredit.service;

import com.springboot.enterpriseCredit.document.EnterpriseCredit;
import com.springboot.enterpriseCredit.dto.EnterpriseCreditDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EnterpriseCreditInterface {

	  public Flux<EnterpriseCredit> findAll();
	  
	  public Mono<EnterpriseCredit> findById(String id);
	  
	  public Mono<EnterpriseCredit> save(EnterpriseCredit enterpriseCredit);
	  
	  public Mono<EnterpriseCredit> update(EnterpriseCredit enterpriseCredit,String id);
	  
	  public Mono<Void> delete(EnterpriseCredit enterpriseCredit);
	  
	  public Mono<EnterpriseCreditDto> saveDto(EnterpriseCreditDto enterpriseCreditDto);


	
}
