package com.fw.amazon.mapper;

import com.fw.amazon.component.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PathVariable;

@Mapper
public interface DepartmentMapper {

    @Select("select * from tbl_department where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "name",column = "name")
    })
    public Department getDepartmentById(@PathVariable Integer id);
}
