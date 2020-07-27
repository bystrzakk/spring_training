package test.demo.spring.core.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static java.lang.String.valueOf;

@Component
public class ApiClients {

    private final RestTemplate restTemplate;
    private final String BASE_URI;

    @Autowired
    public ApiClients(RestTemplate restTemplate,
                      @Value("${external.api.client.url}") String baseUri) {
        this.restTemplate = restTemplate;
        this.BASE_URI = baseUri;
    }

    public ResponseEntity<String> restCall() {
        return restTemplate.getForEntity(buildUri(null), String.class);
    }

    private URI buildUri(Integer id) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromUriString(BASE_URI);

        if (id != null) {
            uriComponentsBuilder.pathSegment(valueOf(id));
        }

        return uriComponentsBuilder
                .build()
                .toUri();
    }
}
