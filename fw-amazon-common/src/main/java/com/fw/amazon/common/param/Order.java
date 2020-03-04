package com.fw.amazon.common.param;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Order {
	private String amazonOrderId;
	private String sellerOrderId;
	private Date purchaseDate;
	private Date lastUpdateDate;
	private String orderStatus;
	
	private String fulfillmentChannel;
	private String salesChannel;
	private String orderChannel;
	
	private String shipServiceLevel;
	private Address shippingAddress;
	
	private Money OrderTotal;
	
	private int numberOfItemsShipped;
	private int numberOfItemsUnshipped;
	
	private List<PaymentExecutionDetailItem> paymentExecutionDetail;
	private String paymentMethod;
	private List<PaymentExecutionDetail> paymentExecutionDetails;
	
	private boolean isReplacementOrder;
	private String replacedOrderId;
	
	private String marketplaceId;
	
	private String buyerEmail;
	private String buyerName;
	private String buyerCountry;
	
	private BuyerTaxInfo buyerTaxInfo;
	private String shipmentServiceLevelCategory;
	private String easyShipShipmentStatus;
	private String orderType;
	
	private Date earlistShipDate;
	private Date latestShipDate;
	private Date earlistDeliveryDate;
	private Date latestDeliveryDate;
	
	private boolean isBusinessOrder;
	private String purchaseOrderNumber;
	private boolean isPrime;
	private boolean isPremiumOrder;
	
	private Date promiseResponseDueDate;
	private boolean isEstimatedShipDateSet;
	
}
