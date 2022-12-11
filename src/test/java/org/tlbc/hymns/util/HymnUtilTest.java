package org.tlbc.hymns.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class HymnUtilTest {

    @Test
    void isIdenticalLabels() {
        Set<String> labelSet = Set.of("流血牺牲舍命", "奉献");
        String labels = "流血牺牲舍命  奉献";
        boolean result = HymnUtil.isIdenticalLabels(labelSet, labels);
        log.debug("result: {}", result);
        assertTrue(result);
    }

    @Test
    void sendHttpRequest() {
    }
}