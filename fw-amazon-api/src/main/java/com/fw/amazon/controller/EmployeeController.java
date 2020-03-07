package com.fw.amazon.controller;

import com.fw.amazon.component.Employee;
import com.fw.amazon.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired(required = false)
    EmployeeMapper employeeMapper;

    @GetMapping("/")
    public String getMsg() {
        return "this is a test2";
    }

    @GetMapping("/emp/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
       return employeeMapper.getEmployeeById(id);
    }
}
