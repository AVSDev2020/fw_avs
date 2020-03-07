package com.fw.amazon.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class SysLog implements Serializable {

	private static final long serialVersionUID = 5119439548479184094L;
	
	private long traceId;
	private Date createTime;
	private String status;
	private String method;
	private String params;
	private String url;
	private String userName;//API caller name
}
