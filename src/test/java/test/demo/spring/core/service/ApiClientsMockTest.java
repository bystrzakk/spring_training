package test.demo.spring.core.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import test.demo.spring.core.api.ApiClients;

import java.net.URI;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ApiClientsMockTest {

    @Mock
    RestTemplate restTemplate;

    private ApiClients apiClients;

    @Before
    public void init() {
        initMocks(this);
        apiClients = new ApiClients(restTemplate, "fake_URL");
    }

    @Test
    public void testFetchExternalUsers() {
        //given
        final String mockedResponse = "JEST OK";
        final String expectedResponse = "JEST OK";
        //when
        when(restTemplate.getForEntity(any(URI.class), any())).thenReturn(ResponseEntity.ok(mockedResponse));
        final ResponseEntity<String> objectResponseEntity = apiClients.restCall();
        //then
        Assert.assertNotNull(objectResponseEntity);
        Assert.assertNotNull(objectResponseEntity.getBody());
        Assert.assertEquals(expectedResponse, objectResponseEntity.getBody());
    }
}