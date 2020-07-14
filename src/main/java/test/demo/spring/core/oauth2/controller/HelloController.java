package test.demo.spring.core.oauth2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private static final Logger LOG = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/api/hello")
    public String sayHello() {
        LOG.info("Received request for hello endpoint!");
        return "Hello!";
    }
}
