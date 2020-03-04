package com.fw.amazon.common.param;

import lombok.Data;

@Data
public class Address {
	private String name;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String city;
	private String municipality;
	private String country;
	private String district;
	private String stateOrRegion;
	private String postalCode;
	private String countryCode;
	private String phone;
	private String addressType;
}
