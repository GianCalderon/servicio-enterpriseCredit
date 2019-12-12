package com.springboot.enterpriseCredit.client;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.springboot.enterpriseCredit.dto.EnterpriseDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EnterpriseClient {

private static final Logger LOGGER = LoggerFactory.getLogger(EnterpriseClient.class);
	
	@Autowired
	private WebClient clientEmp;
	
	public Flux<EnterpriseDto> findAll() {
		
		return clientEmp.get().accept(MediaType.APPLICATION_JSON)
				.exchange()
				.flatMapMany(response ->response.bodyToFlux(EnterpriseDto.class));
	}


	public Mono<EnterpriseDto> findById(String id) {
		
		Map<String,Object> param=new HashMap<String,Object>();
		
		return clientEmp.get().uri("/{id}",param)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(EnterpriseDto.class);
		        
//		        .exchange()
//		        .flatMapMany(response ->response.bodyToMono(FamilyDTO.class));
	}

	
	public Mono<EnterpriseDto> save(EnterpriseDto enterpriseDto) {
		
		LOGGER.info("listo a enviar: "+enterpriseDto.toString());
		
		return clientEmp.post()
			   .accept(MediaType.APPLICATION_JSON)
			   .contentType(MediaType.APPLICATION_JSON)
		       .body(BodyInserters.fromValue(enterpriseDto))
			   .retrieve()
			   .bodyToMono(EnterpriseDto.class);
		
		
		
		
	}

	public Mono<Void> delete(String id) {
		
		return clientEmp.delete()
				.uri("/{id}",Collections.singletonMap("id",id))
				.exchange()
				.then();
	}

	public Mono<EnterpriseDto> update(EnterpriseDto enterpriseDto, String id) {
		
		return clientEmp.post()
				   .accept(MediaType.APPLICATION_JSON)
				   .contentType(MediaType.APPLICATION_JSON)
				   .syncBody(enterpriseDto)
				   .retrieve()
				   .bodyToMono(EnterpriseDto.class);
	}
}
