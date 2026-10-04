package com.example.BService.Controller;

import com.example.BService.OpenFeignt.AServiceFeignt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Profile("dev")
@RequestMapping("/BA")
public class ABController {

    @Autowired
    private AServiceFeignt aServiceFeignt;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${valueFromProp}")
    private String valuefromprop;


    @GetMapping("/A/get")
    public ResponseEntity<String> getADetailsFromB()
    {
        System.out.println(valuefromprop);
//        return new ResponseEntity<>(restTemplate.getForObject("http://localhost:8081/A",String.class),HttpStatus.OK);
        return aServiceFeignt.getADetails();
    }
}
