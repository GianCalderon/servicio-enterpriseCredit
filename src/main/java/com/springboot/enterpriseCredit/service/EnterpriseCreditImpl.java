package com.springboot.enterpriseCredit.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.enterpriseCredit.client.EnterpriseClient;
import com.springboot.enterpriseCredit.controller.EnterpriseCreditController;
import com.springboot.enterpriseCredit.document.EnterpriseCredit;
import com.springboot.enterpriseCredit.dto.EnterpriseCreditDto;
import com.springboot.enterpriseCredit.repo.EnterpriseCreditRepo;
import com.springboot.enterpriseCredit.util.UtilConvert;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EnterpriseCreditImpl implements EnterpriseCreditInterface {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EnterpriseCreditImpl.class);

	@Autowired
	EnterpriseCreditRepo repo;
	
	@Autowired
	UtilConvert convert;
	
	@Autowired
	EnterpriseClient client;
	
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
	    return repo.findById(id).flatMap(credito-> {

	    	credito.setTea(enterpriseCredit.getTea());
	        credito.setCantShare(enterpriseCredit.getCantShare());
	        credito.setDateUpdate(new Date());
	        
	        return repo.save(credito);

	      });
	}

	@Override
	public Mono<Void> delete(EnterpriseCredit enterpriseCredit) {
		// TODO Auto-generated method stub
		return repo.delete(enterpriseCredit);
	}

	@Override
	public Mono<EnterpriseCreditDto> saveDto(EnterpriseCreditDto enterpriseCreditDto) {
		
		LOGGER.info("service:"+enterpriseCreditDto.toString());

		return repo.save(convert.convertEnterpriseCredit(enterpriseCreditDto)).flatMap(sa -> {

              client.save(enterpriseCreditDto.getEnterprise()).block();

			return Mono.just(enterpriseCreditDto);
		});
		
		
		
	}

}
