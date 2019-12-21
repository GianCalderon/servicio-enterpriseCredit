package com.springboot.enterpriseCredit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringbootServicioEnterpriseCreditApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioEnterpriseCreditApplication.class, args);
	}

}
