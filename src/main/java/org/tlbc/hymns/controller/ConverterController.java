package org.tlbc.hymns.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tlbc.hymns.service.ConverterService;

import javax.annotation.Resource;

@RestController @Slf4j @CrossOrigin
@RequestMapping("/convert")
public class ConverterController {
    @Resource
    private ConverterService converterService;

    @GetMapping(value = "/db2es")
    public Integer db2es() {
        Integer count = converterService.hymn2ElasticSearchSong();
        log.debug("count: {}", count);
        return count;
    }
}
