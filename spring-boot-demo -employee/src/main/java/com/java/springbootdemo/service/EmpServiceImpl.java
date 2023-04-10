package com.java.springbootdemo.service;

import com.java.springbootdemo.model.Employee;
import com.java.springbootdemo.repo.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    EmpRepo empRepo;
    @Override
    public List<Employee> getAllEmployees() {
        return empRepo.findAll();
    }

    @Override
    public String saveBook(Employee employee) {
        Employee employeeFromDb =  empRepo.save(employee);
        return "Inserted successfully in DB. and ID generated for this is: "+ employeeFromDb.getEmpId();
    }

    @Override
    public Employee getEmployee(int empId) {
        Optional<Employee> empOptional = empRepo.findById(empId);
        if(empOptional.isPresent()){
            return empOptional.get();
        }
        return null;
    }

    @Override
    public String deleteEmployee(int empId) {
        Optional<Employee> optionalEmployee = empRepo.findById(empId);
        if(optionalEmployee.isPresent()) {
            Employee employeeFromDb = optionalEmployee.get();
            empRepo.delete(employeeFromDb);
            return "Successfully Deleted the book with id : "+empId;
        }
        return "Error in Deletion.No Such Id present in DB. "+ empId;
    }

    @Override
    public String deleteAllEmployees() {
        List<Employee> employeeList = empRepo.findAll();
        if(employeeList.size()>0) {
            empRepo.deleteAll();
            return "All Employees deleted successfully!!";
        }else{
            return "No Employees are there to delete!!!!";
        }
    }

    @Override
    public Employee updateEmployee(int empId, Employee employee) {
        Optional<Employee> empOptional = empRepo.findById(empId);
        if(empOptional.isPresent()){
            Employee employeeFromDb = empOptional.get();
            employeeFromDb.setEmpName(employee.getEmpName());
            employeeFromDb.setDepartment(employee.getDepartment());
            employeeFromDb.setDesignation(employee.getDesignation());
            employeeFromDb.setSalary(employee.getSalary());
            empRepo.save(employeeFromDb);
            return employeeFromDb;
        }
        return null;
    }

}
