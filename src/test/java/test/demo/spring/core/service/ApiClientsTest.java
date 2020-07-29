package test.demo.spring.core.service;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import test.demo.spring.core.api.ApiClients;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiClientsTest {


    @Autowired
    private ApiClients apiClients;

    @Test
    @Ignore
    public void testFetchExternalUsers() {
        final ResponseEntity<String> objectResponseEntity = apiClients.restCall();
        Assert.assertNotNull(objectResponseEntity);
    }
}