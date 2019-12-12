package com.springboot.enterpriseCredit.util;

import org.springframework.stereotype.Component;

import com.springboot.enterpriseCredit.document.EnterpriseCredit;
import com.springboot.enterpriseCredit.dto.EnterpriseCreditDto;

@Component
public class UtilConvert {
	
	
	public EnterpriseCredit convertEnterpriseCredit(EnterpriseCreditDto enterpriseCreditDto) {

		EnterpriseCredit enterpriseCredit = new EnterpriseCredit();

		enterpriseCredit.setName("Prestamo-Empresarial");
		enterpriseCredit.setCreditAmount(enterpriseCreditDto.getCreditAmount());
		enterpriseCredit.setDateCredit(enterpriseCreditDto.getDateCredit());
		enterpriseCredit.setTea(enterpriseCreditDto.getTea());
		enterpriseCredit.setCantShare(enterpriseCreditDto.getCantShare());
		
		return enterpriseCredit;

	}


}
