package com.fw.amazon.service;

import com.fw.amazon.common.param.Order;
import com.fw.amazon.common.param.Product;
import com.fw.amazon.common.param.VendorOrder;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EdiService {

    public List<VendorOrder> getAllOrders()
    {
        List<VendorOrder> orderList = new ArrayList<VendorOrder>();
        try {
            List<File> fileList = EdiFileList();
            for(File file:fileList) {
                orderList.add(EdiParser(file));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return orderList;
    }

    private Date EdiStringToDate(String strDate) throws ParseException {
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        date = sdf.parse(strDate);
        return date;
    }
    private VendorOrder EdiParser(File file) throws IOException, ParseException {
        VendorOrder order = null;
        FileInputStream fin = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader buffReader = new BufferedReader(reader);
        String strTmp = "";
        while ((strTmp = buffReader.readLine()) != null) {
            order = new VendorOrder();
            List<Product> products = new ArrayList<Product>();
            String segments[] = strTmp.split("~");

            for(int i = 0; i < segments.length; i++) {
                System.out.println(segments[i]);
                String items[] = segments[i].split("\\*");
                if (items[0].equals("BEG")){
                    order.setAmazonOrderId(items[3]);
                    order.setPurchaseDate(EdiStringToDate(items[5]));
                }
                else if (items[0].equals("CSH")) {
                    if (items[1].equals("Y")) {
                        order.setBackOrder(true);
                    }
                }
                else if (items[0].equals("DTM")) {
                    if (items[1].equals("064")) {
                        String dateStr = items[2].split("~")[0];
                        order.setEarlistDeliveryDate(EdiStringToDate(dateStr));
                    }
                    else if (items[1].equals("063")){
                        String dateStr = items[2].split("~")[0];
                        order.setLatestDeliveryDate(EdiStringToDate(dateStr));
                    }
                }
                else if (items[0].equals("N1")) {
                    String strLocationCode = items[4].replace("~","");
                    order.setShippingAddressCode(strLocationCode);
                }
                else if (items[0].equals("PO1")) {
                    Product product = new Product();
                    product.setSku(items[7]);
                    String Price = String.format("%.2f",Float.valueOf(items[4]));
                    product.setPrice(new BigDecimal(Price));
                    product.setQuantity(Integer.valueOf(items[2]));
                    products.add(product);
                }
            }
            if ( products.size() > 0) {
                order.setProducts(products);
            }
        }
        buffReader.close();
        return order;
    }

    private List<File> EdiFileList() {

        String ediPath = System.getProperty("user.dir") + "/avc-edi-orders";
        List <File> fileList = new ArrayList<File>();

        File file = new File(ediPath);
        File[] fs = file.listFiles();
        for(File f:fs){
            if(!f.isDirectory())
                fileList.add(f);
        }
        return fileList;
    }
}
