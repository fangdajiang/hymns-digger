package org.tlbc.hymns.helper;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class HttpRequestHelper {
    public static <T> ResponseEntity<T> sendHttpRequest(String url, HttpMethod httpMethod, String json, Class<T> responseType, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Token 311c2369d1d88160a9802b545a06ed86f8f22c73");
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(url, httpMethod, new HttpEntity<>(json, headers), responseType, param);
    }

}
