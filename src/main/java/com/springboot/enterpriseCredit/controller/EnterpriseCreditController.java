package com.springboot.enterpriseCredit.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.enterpriseCredit.document.EnterpriseCredit;
import com.springboot.enterpriseCredit.dto.EnterpriseCreditDto;
import com.springboot.enterpriseCredit.service.EnterpriseCreditInterface;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/enterpriseCredit")
public class EnterpriseCreditController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EnterpriseCreditController.class);


	  @Autowired
	  EnterpriseCreditInterface service;

	  @GetMapping
	  public Mono<ResponseEntity<Flux<EnterpriseCredit>>> toList() {

	    return Mono.just(ResponseEntity.ok()
	          .contentType(MediaType.APPLICATION_JSON).body(service.findAll()));

	  }

	  @GetMapping("/{id}")
	  public Mono<ResponseEntity<EnterpriseCredit>> search(@PathVariable String id) {

	    return service.findById(id).map(e -> ResponseEntity.ok()
	      .contentType(MediaType.APPLICATION_JSON).body(e))
	      .defaultIfEmpty(ResponseEntity.notFound().build());

	  }

	  @PostMapping
		public Mono<ResponseEntity<EnterpriseCreditDto>> saveDto(@RequestBody EnterpriseCreditDto enterpriseCreditDto) {

			LOGGER.info(enterpriseCreditDto.toString());

			return service.saveDto(enterpriseCreditDto).map(s -> ResponseEntity.created(URI.create("/api/enterpriseCredit"))
					.contentType(MediaType.APPLICATION_JSON).body(s));

		}
	  

	  @PutMapping("/{id}")
	  public Mono<ResponseEntity<EnterpriseCredit>> update(@RequestBody EnterpriseCredit enterpriseCredit,
	                    @PathVariable String id) {

	    return service.update(enterpriseCredit, id)
	             .map(e -> ResponseEntity.created(URI.create("/api/enterpriseCredit".concat(e.getId())))
	             .contentType(MediaType.APPLICATION_JSON).body(e))
	             .defaultIfEmpty(ResponseEntity.notFound().build());

	  }

	  @DeleteMapping("/{id}")
	  public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {

	    return service.findById(id).flatMap(e -> {
	      return service.delete(e).then(Mono.just(new ResponseEntity<Void>(HttpStatus.ACCEPTED)));
	    }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

	  }
	  


}
