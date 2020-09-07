package com.quangphan.config.client.controller;

import com.google.inject.internal.cglib.core.$ReflectUtils;
import com.quangphan.config.client.domain.Employee;
import com.quangphan.config.client.service.EmployeeSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RefreshScope
@RestController
public class EmployeeSearchController {

    private EmployeeSearchService employeeSearchService;

    @Autowired
    public void setEmployeeSearchService(EmployeeSearchService employeeSearchService) {
        this.employeeSearchService = employeeSearchService;
    }

    @GetMapping("/employee/find/{id}")
    public Employee findById(@PathVariable("id") Long id) {
        return employeeSearchService.findById(id);
    }

    @GetMapping("/employee/findall")
    public Collection<Employee> findAll() {
        return employeeSearchService.findAll();
    }

}
