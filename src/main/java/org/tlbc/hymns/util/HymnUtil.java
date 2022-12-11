package org.tlbc.hymns.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class HymnUtil {
    public static String set2string(Set<String> labelSet) {
        StringBuilder newLabelSet = new StringBuilder();
        labelSet.forEach((s -> newLabelSet.append(s.trim()).append(" ")));
        return newLabelSet.toString().trim();
    }

    public static boolean isIdenticalLabels(Set<String> labelSet, String labels) {
        Set<String> matchedLabelSetTemp = new HashSet<>(labelSet);
        log.debug("matchedLabelSetTemp before removing all: {}", matchedLabelSetTemp);
        List<String> labelList = Arrays.asList(labels.split("\\s+"));
        log.debug("labelList: {}", labelList);
        matchedLabelSetTemp.removeAll(new HashSet<>(labelList));
        log.debug("matchedLabelSetTemp after removing all: {}", matchedLabelSetTemp);
        return matchedLabelSetTemp.isEmpty();
    }
    public static <T> ResponseEntity<T> sendHttpRequest(String url, HttpMethod httpMethod, String json, Class<T> responseType, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Token 311c2369d1d88160a9802b545a06ed86f8f22c73");
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(url, httpMethod, new HttpEntity<>(json, headers), responseType, param);
    }

}
