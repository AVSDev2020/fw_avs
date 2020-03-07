package com.fw.amazon.component;

import java.util.List;

public class Employee {
    private Integer id;
    private String name;
    private boolean gender;
    private String email;
    private Department dept;
    private List<Order> orders;

//    public Employee(Integer id, String name, Integer age, Float salary, Integer fk_department_id) {
//        this.id = id;
//        this.name = name;
//        this.age = age;
//        this.salary = salary;
//        this.fk_department_id = fk_department_id;
//    }

    public Employee() {
        this.dept = new Department();
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", dept=" + dept +
                '}';
    }
}
