package org.quangphan.resttemplate.client.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.quangphan.resttemplate.client.model.EmployeeInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;

@Service
public class EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EurekaClient eurekaClient;

    @Value("${service.employyesearch.serviceId}")
    private String employeeSearchServiceId;

    @HystrixCommand(fallbackMethod = "fallback")
    public EmployeeInfo findOne(Long myself) {
        Application application = eurekaClient.getApplication(employeeSearchServiceId);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String ip = instanceInfo.getIPAddr();
        log.info("IP {}", ip);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/employee/find/" + myself;
        log.info("URL" + url);
        EmployeeInfo emp = restTemplate.getForObject(url, EmployeeInfo.class);
        log.info("RESPONSE " + emp);
        return emp;
    }

    @HystrixCommand(fallbackMethod = "fallback2")
    public Collection<EmployeeInfo> findAll() {
        Application application = eurekaClient.getApplication(employeeSearchServiceId);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/employee/findall";
        log.info("URL" + url);
        Collection<EmployeeInfo> list = restTemplate.getForObject(url, Collection.class);
        log.info("RESPONSE " + list);
        return list;
    }

    // Hystrix fallback method should have the same input parameters and return type like original method
    private EmployeeInfo fallback(Long id) {
        return null;
    }

    // Hystrix fallback method should have the same input parameters and return type like original method
    private Collection<EmployeeInfo> fallback2() {
        return Collections.EMPTY_LIST;
    }

}
