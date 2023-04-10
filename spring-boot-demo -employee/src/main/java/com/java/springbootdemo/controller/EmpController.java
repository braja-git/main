package com.java.springbootdemo.controller;

import com.java.springbootdemo.model.Employee;
import com.java.springbootdemo.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1")
public class EmpController {
    @Autowired
    EmpService empService;
    //http://localhost:8090/api/v1/employees
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getBooks(){
        List<Employee> employeeList = empService.getAllEmployees();
         if(employeeList.size()>0){
             return new ResponseEntity<List<Employee>>(employeeList,HttpStatus.OK);
         }else{
             return new ResponseEntity<List<Employee>>(employeeList,HttpStatus.NO_CONTENT);
         }
    }
    //http://localhost:8090/api/v1/employees
    @PostMapping("/employees")
    public ResponseEntity<String> saveBook(@RequestBody Employee employee){
        //Inserted successfully
        String msg = empService.saveBook(employee);
        if(null!=msg && msg.contains("Inserted successfully")){
            return new ResponseEntity<String>(msg,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<String>(msg,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //http://localhost:8090/api/v1/employees/5
    @GetMapping("/employees/{empId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int empId){

        Employee searchedEmployee =  empService.getEmployee(empId);
        if(null!= searchedEmployee){
            return new ResponseEntity<Employee>(searchedEmployee, HttpStatus.OK);
        }else{
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
    }
    //http://localhost:8090/api/v1/employees/500
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int empId){
        String msg = empService.deleteEmployee(empId);
        //"Succesfully".indexOf(msg)!=-1
        if(null!=msg && msg.contains("Successfully")){
            return new ResponseEntity<String>(msg,HttpStatus.OK);
        }else{
            return new ResponseEntity<String>(msg,HttpStatus.NOT_FOUND);
        }
    }
    //http://localhost:8090/api/v1/employees
    @DeleteMapping("/employees")
    public ResponseEntity<String> deleteAllEmployees(){

        String msg =  empService.deleteAllEmployees();
        if(null!=msg && msg.contains("successfully")){
            return  new ResponseEntity<String>(msg,HttpStatus.OK);
        }else{
            return  new ResponseEntity<String>(msg,HttpStatus.NO_CONTENT);
        }
    }
    //http://localhost:8090/api/v1/employees/1
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable int empId, @RequestBody Employee employee){
        Employee employeeFromDb = empService.updateEmployee(empId, employee);
        if(null!= employeeFromDb){
          return  new ResponseEntity<Employee>(employeeFromDb,HttpStatus.OK);
        }
        return  new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
    }

}