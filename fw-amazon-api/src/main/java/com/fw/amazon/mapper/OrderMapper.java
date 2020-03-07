package com.fw.amazon.mapper;

import com.fw.amazon.component.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper{

    @Select("select * from tbl_order where id=#{id}")
    public Order getOrderById(Integer id);

    @Select("select * from tbl_orders where fk_employee_id=#{emp_id}")
    public List<Order> getOrdersByEmoId(Integer emp_id);
}
