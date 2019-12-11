package com.springboot.enterpriseCredit.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="entrepriseCredit")
public class EnterpriseCredit {
	
	@Id
	private String id;
	private String creditAmount;
	private String dateCredit;
	private Double tea;

}
