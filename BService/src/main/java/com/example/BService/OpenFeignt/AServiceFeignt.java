package com.example.BService.OpenFeignt;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ASERVICE", path = "/A")
public interface AServiceFeignt {

    @GetMapping("/get")
    public ResponseEntity<String> getADetails();
}
