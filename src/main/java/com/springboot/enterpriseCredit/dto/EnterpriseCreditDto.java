package com.springboot.enterpriseCredit.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EnterpriseCreditDto {

    @NotBlank
	private String name;
	
	@NotBlank
	private String creditAmount;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateCredit;
	
	@NotBlank
	private Double tea;
	
	@NotBlank
	private int cantShare;
	
	private EnterpriseDto holders;

}
