package com.quangphan.feign.client;

import com.quangphan.feign.client.model.EmployeeInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "config-client-demo")
public interface EmployeeClient {

    @GetMapping("/employee/find/{id}")
    EmployeeInfo getEmployee(@PathVariable("id") Long id);

    @GetMapping("/employee/findall")
    List<EmployeeInfo> getEmployees();

}
