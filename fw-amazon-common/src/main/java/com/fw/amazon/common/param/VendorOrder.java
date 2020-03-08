package com.fw.amazon.common.param;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class VendorOrder {
    private boolean BackOrder;
    private String AmazonOrderId;
    private Date EarlistDeliveryDate;
    private Date LatestDeliveryDate;
    private Date PurchaseDate;
    private String ShippingAddressCode;
    private List<Product> Products;
}
