package com.fw.amazon.mapper;

import com.fw.amazon.component.Employee;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EmployeeMapper {

    @Select("select * from tbl_employee where id=#{id}")
    @Results({
            @Result(id=true,property = "id", column = "id"),
            @Result(property = "dept", column = "fk_department_id",
                    one = @One(select = "com.fw.amazon.mapper.DepartmentMapper.getDepartmentById")),
            @Result(property = "orders",column = "id",
                    many = @Many(select = "com.fw.amazon.mapper.OrderMapper.getOrdersByEmoId"))
    })
    public Employee getEmployeeById(Integer id);


}
