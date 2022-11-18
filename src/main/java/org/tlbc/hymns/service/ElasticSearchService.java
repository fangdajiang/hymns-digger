package org.tlbc.hymns.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.tlbc.hymns.model.LabelStudioAction;
import org.tlbc.hymns.model.Song;

import javax.annotation.Resource;

@Service @Slf4j
public class ElasticSearchService {

    @Resource
    private RestTemplate restTemplate;

    public Song update(LabelStudioAction action) {
        return new Song("中文", "http://1234");
    }
}
