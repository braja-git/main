package com.java.springbootdemo.repo;

import com.java.springbootdemo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepo extends JpaRepository<Employee,Integer> {
}
