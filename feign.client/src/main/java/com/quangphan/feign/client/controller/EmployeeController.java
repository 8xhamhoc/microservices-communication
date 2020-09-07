package com.quangphan.feign.client.controller;

import com.quangphan.feign.client.EmployeeClient;
import com.quangphan.feign.client.model.EmployeeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeClient employeeClient;

    @GetMapping("/feign/employee/{id}")
    public EmployeeInfo getEmployee(@PathVariable("id") Long id) {
        return employeeClient.getEmployee(id);
    }

    @GetMapping("/feign/employee/findall")
    public List<EmployeeInfo> getEmployees() {
        return employeeClient.getEmployees();
    }

}
