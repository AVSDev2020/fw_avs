package com.fw.amazon.common.param;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Money {
	private String currencyCode;
	private BigDecimal amount;
}
