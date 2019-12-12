package com.springboot.enterpriseCredit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.enterpriseCredit.client.EnterpriseClient;
import com.springboot.enterpriseCredit.document.EnterpriseCredit;
import com.springboot.enterpriseCredit.dto.EnterpriseCreditDto;
import com.springboot.enterpriseCredit.repo.EnterpriseCreditRepo;
import com.springboot.enterpriseCredit.util.UtilConvert;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EnterpriseCreditImpl implements EnterpriseCreditInterface {

	@Autowired
	EnterpriseCreditRepo repo;
	
	@Autowired
	UtilConvert convert;
	
	@Autowired
	EnterpriseClient webCLient;
	
	@Override
	public Flux<EnterpriseCredit> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Mono<EnterpriseCredit> findById(String id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Mono<EnterpriseCredit> save(EnterpriseCredit enterpriseCredit) {
		// TODO Auto-generated method stub
		return repo.save(enterpriseCredit);
	}

	@Override
	public Mono<EnterpriseCredit> update(EnterpriseCredit enterpriseCredit, String id) {
		// TODO Auto-generated method stub
	    return repo.findById(id).flatMap(e -> {

	    	e.setCreditAmount(enterpriseCredit.getCreditAmount());
	        e.setDateCredit(enterpriseCredit.getDateCredit());
	        e.setTea(enterpriseCredit.getTea());
	        
	        return repo.save(e);

	      });
	}

	@Override
	public Mono<Void> delete(EnterpriseCredit enterpriseCredit) {
		// TODO Auto-generated method stub
		return repo.delete(enterpriseCredit);
	}

	@Override
	public Mono<EnterpriseCreditDto> saveDto(EnterpriseCreditDto enterpriseCreditDto) {
		
		return save(convert.convertEnterpriseCredit(enterpriseCreditDto)).flatMap(sa -> {

			enterpriseCreditDto.getHolders().setIdCuenta(sa.getId());
			webCLient.save(enterpriseCreditDto.getHolders()).block();
			

			return Mono.just(enterpriseCreditDto);
		});
		
		
		
	}

}
