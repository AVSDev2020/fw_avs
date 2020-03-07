package com.fw.amazon.controller;

import com.fw.amazon.component.Department;
import com.fw.amazon.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentMapper departmentMapper;

    @GetMapping("/dept/{id}")
    public Department getDepartmentById(@PathVariable Integer id) {

        return departmentMapper.getDepartmentById(id);
    }
}
