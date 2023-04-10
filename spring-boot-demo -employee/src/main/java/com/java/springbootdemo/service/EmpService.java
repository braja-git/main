package com.java.springbootdemo.service;

import com.java.springbootdemo.model.Employee;

import java.util.List;

public interface EmpService {
    List<Employee> getAllEmployees();

    String saveBook(Employee employee);

    Employee getEmployee(int empId);

    String deleteEmployee(int empId);

    String deleteAllEmployees();

    Employee updateEmployee(int empId, Employee employee);
}
