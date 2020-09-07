package org.quangphan.resttemplate.client.controller;

import org.quangphan.resttemplate.client.model.EmployeeInfo;
import org.quangphan.resttemplate.client.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RefreshScope
@RestController
public class EmployeeInfoController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/dashboard/{myself}")
    public EmployeeInfo findme(@PathVariable Long myself) {
        return employeeService.findOne(myself);
    }

    @RequestMapping("/dashboard/peers")
    public Collection<EmployeeInfo> findPeers() {
        return employeeService.findAll();
    }

}
