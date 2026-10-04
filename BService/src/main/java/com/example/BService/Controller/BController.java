package com.example.BService.Controller;

import com.example.BService.OpenFeignt.AServiceFeignt;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Profile("prod")
@RestController
@RequestMapping("/B")
public class BController {

    @Autowired
    private AServiceFeignt aServiceFeignt;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/get")
    @RateLimiter(name="BService",fallbackMethod = "rateLimiterFallBackMethod")
    public ResponseEntity<String> getBDetails()
    {
        return new ResponseEntity<>("B is working fine and returned", HttpStatus.OK);
    }

    @GetMapping("/A/get")
    @RateLimiter(name="ABService",fallbackMethod = "rateLimiterFallBackMethod")
    public ResponseEntity<String> getADetailsFromB()
    {
//        return new ResponseEntity<>(restTemplate.getForObject("http://localhost:8081/A",String.class),HttpStatus.OK);
       return aServiceFeignt.getADetails();
    }

    public ResponseEntity<String> rateLimiterFallBackMethod(Throwable t)
    {
        return new ResponseEntity<>(t.getMessage(),HttpStatus.TOO_MANY_REQUESTS);
    }
}
