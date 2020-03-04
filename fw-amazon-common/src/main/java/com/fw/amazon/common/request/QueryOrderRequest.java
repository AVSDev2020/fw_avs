package com.fw.amazon.common.request;

import java.util.Date;

import lombok.Data;

@Data
public class QueryOrderRequest {

	private Date createStart;
	private Date createEnd;
	private Date lastUpdateStart;
	private Date lastUpdateEnd;
	
	private String orderStatus;
	private String marketPlaceId;
	private String fulfillmentChannel;
	private String paymentMethod;
	private String buyerEmail;
	
	private String sellerOrderId;
	private int maxResultsPerPage;
	private String easyShipShipmentStatus;
	
	
}
