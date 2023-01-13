package org.tlbc.hymns.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.tlbc.hymns.model.ElasticSearchSong;
import org.tlbc.hymns.model.SongSummary;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j @SpringBootTest
class ElasticSearchSongServiceTest {
    private ElasticSearchSong elasticSearchSong;

    @Resource
    private ElasticSearchSongService elasticSearchSongService;

    @BeforeEach
    void setUp() {
        elasticSearchSong = new ElasticSearchSong(12345, "测试", "Eng", "分类1", "分类2");
        elasticSearchSong.setNotationUrl("http://song-url");
        elasticSearchSong.setLabels(null);
    }

    @Test
    void test() {
        log.debug("new song: {}", elasticSearchSong);
    }

    @Test
    void save() {
        ElasticSearchSong savedElasticSearchSong = elasticSearchSongService.save(elasticSearchSong);
        assertNotNull(savedElasticSearchSong);
        log.debug("savedSong: {}", savedElasticSearchSong);
        ElasticSearchSong foundElasticSearchSong = elasticSearchSongService.getByNameCn(savedElasticSearchSong.getNameCn()).get(0);
        log.debug("foundSong: {}", foundElasticSearchSong);
        assertNotNull(foundElasticSearchSong);
    }

    @Test
    void delete() {
        elasticSearchSongService.delete(12345);
        ElasticSearchSong foundElasticSearchSong = elasticSearchSongService.getById(12345).orElse(null);
        assertNull(foundElasticSearchSong);
    }

    @Test
    void getAll() {
        List<ElasticSearchSong> elasticSearchSongs = elasticSearchSongService.getAll();
        log.debug("songs: {}", elasticSearchSongs);
        assertTrue(elasticSearchSongs.size() > 0);
    }

    @Test
    void getById() {
        ElasticSearchSong elasticSearchSong = elasticSearchSongService.getById(280).orElse(null);
        log.debug("song: {}", elasticSearchSong);
        assertNotNull(elasticSearchSong);
    }

    @Test
    void getByNameCn() {
        List<ElasticSearchSong> elasticSearchSongs = elasticSearchSongService.getByNameCn("颂赞");
        log.debug("songs size:{}  , {}", elasticSearchSongs.size(), elasticSearchSongs);
        assertTrue(elasticSearchSongs.size() > 0);
    }

    @Test
    void findByLabels() {
        List<ElasticSearchSong> elasticSearchSongs = elasticSearchSongService.findByLabels(Set.of("慈爱恩慈", "救主拯救得救"));
        List<String> songNames = elasticSearchSongs.stream().map(ElasticSearchSong::getNameCn).collect(Collectors.toList());
        log.debug("song count: {}, names: {}", elasticSearchSongs.size(), songNames);
        assertTrue(elasticSearchSongs.size() > 0);
    }

    @Test
    void count() {
        assertTrue(elasticSearchSongService.count() > 0);
    }

    @Test
    void getSummary() {
        SongSummary songSummary = elasticSearchSongService.getSummary();
        log.debug("songSummary: {}", songSummary);
        assertTrue(songSummary.getTotalNumber() > 0);
    }

    @Test
    void findByGroup2() {
        List<ElasticSearchSong> elasticSearchSongs = elasticSearchSongService.findByGroup2("创造");
        List<String> songNames = elasticSearchSongs.stream().map(ElasticSearchSong::getNameCn).collect(Collectors.toList());
        log.debug("song count: {}, names: {}", elasticSearchSongs.size(), songNames);
        assertTrue(elasticSearchSongs.size() > 0);
    }
}