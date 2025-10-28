package org.example.customerservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope
public class ConfigTestRestController {

    @Value("${global.params.p1}")
    private String p1 ;
    @Value("${global.params.p2}")
    private String p2 ;

    @Autowired
    private CustomerConfigParams customerConfigParams;

    @GetMapping("/testConfig1")
    public Map<String,String> getMap(){
        return Map.of("a",p1,"b",p2);
    }

    @GetMapping("/testConfig2")
    public CustomerConfigParams getMap2(){
        return customerConfigParams;
    }



}
