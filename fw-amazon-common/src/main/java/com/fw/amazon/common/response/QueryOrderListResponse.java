package com.fw.amazon.common.response;

import java.util.Date;
import java.util.List;

import com.fw.amazon.common.param.Order;

import lombok.Data;

@Data
public class QueryOrderListResponse {
	private Date lastUpdatedBefore;
	private Date createdBefore;
	private List<Order> orders;

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
