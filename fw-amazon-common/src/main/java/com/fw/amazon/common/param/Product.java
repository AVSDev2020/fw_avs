package com.fw.amazon.common.param;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private String Sku;
    private BigDecimal Price;
    private Integer Quantity;
}
