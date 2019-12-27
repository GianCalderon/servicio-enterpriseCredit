package com.springboot.enterpriseCredit.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EnterpriseCreditDto {

	private String idEnterpriseCredit;

	private Double creditAmount;
	private Double tea;
	private int cantShare;
	private EnterpriseDto enterprise;

}
