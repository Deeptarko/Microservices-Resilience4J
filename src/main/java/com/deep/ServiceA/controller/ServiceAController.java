package com.deep.ServiceA.controller;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/a")
public class ServiceAController {
    @Autowired
    private RestTemplate restTemplate;
    private static final String SERVICE_A="serviceA";
    private static final String BASE_URL="http://localhost:8081/";

    @GetMapping
    @CircuitBreaker(name=SERVICE_A,fallbackMethod = "serviceFallbackMethod")
    public String serviceA(){
        return restTemplate.getForObject(BASE_URL+"b",String.class);
    }

    public String serviceFallbackMethod(Exception e){
        return "This is a fallback method";
    }
}
