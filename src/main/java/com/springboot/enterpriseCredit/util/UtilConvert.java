package com.springboot.enterpriseCredit.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.springboot.enterpriseCredit.document.EnterpriseCredit;
import com.springboot.enterpriseCredit.dto.EnterpriseCreditDto;

@Component
public class UtilConvert {
	
	
	public EnterpriseCredit convertEnterpriseCredit(EnterpriseCreditDto enterpriseCreditDto) {

		EnterpriseCredit enterpriseCredit = new EnterpriseCredit();

		enterpriseCredit.setNameCredit("Credito-Empresarial");
		enterpriseCredit.setNumDoc(enterpriseCreditDto.getNumberRuc());
		enterpriseCredit.setCreditAmount(enterpriseCreditDto.getCreditAmount());
		enterpriseCredit.setTea(enterpriseCreditDto.getTea());
		enterpriseCredit.setCantShare(enterpriseCreditDto.getCantShare());
		enterpriseCredit.setAmountShare((enterpriseCreditDto.getCreditAmount()+
				                      (enterpriseCreditDto.getCreditAmount()*(enterpriseCreditDto.getTea()/100)))
				                      /enterpriseCreditDto.getCantShare());
		
		enterpriseCredit.setDateCreate(new Date());
		enterpriseCredit.setDateUpdate(new Date());
		
		
		
		return enterpriseCredit;

	}


}
