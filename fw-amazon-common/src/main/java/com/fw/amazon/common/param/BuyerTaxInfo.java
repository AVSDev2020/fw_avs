package com.fw.amazon.common.param;

import java.util.List;

import lombok.Data;

@Data
public class BuyerTaxInfo {
	private String companyLegalName;
	private String taxingRegion;
	private List<TaxClassification> taxClassifications; 
}
