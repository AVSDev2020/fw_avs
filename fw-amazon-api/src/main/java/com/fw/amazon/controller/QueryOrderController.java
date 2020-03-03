package com.fw.amazon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fw.amazon.common.response.QueryOrderListResponse;
import com.fw.amazon.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api
@RestController
public class QueryOrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping(path="/orders", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(tags="Order", value="Get Order List", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "offset", value = "offset", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "limit", value = "limit", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "sort", value = "sort field (prefixing the field [-] is desc)", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "id", value = "filtering by id", required = false, paramType = "query", dataType = "String")
	})
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK", response = QueryOrderListResponse.class)
	})
	public QueryOrderListResponse getOrder(
			@RequestParam(required = false) Integer offset,
			@RequestParam(required = false) Integer limit,
			@RequestParam(required = false) String sort) {
		QueryOrderListResponse response = new QueryOrderListResponse();
		
		return response;
	}
}
