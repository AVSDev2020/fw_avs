package com.fw.amazon.common.param;

import lombok.Data;

@Data
public class PaymentExecutionDetailItem {
	private Money payment;
	private String paymentMethod;
}
