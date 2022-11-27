package org.tlbc.hymns.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.tlbc.hymns.model.Song;
import org.tlbc.hymns.model.SongSummary;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j @SpringBootTest
class SongServiceTest {
    private Song song;

    @Resource
    private SongService songService;

    @BeforeEach
    void setUp() {
        song = new Song(12345, "测试", "Eng", "分类1", "分类2");
        song.setNotationUrl("http://song-url");
        song.setLabels("三一 公义 圣洁");
    }

    @Test
    void test() {
        log.debug("new song: {}", song);
    }

    @Test
    void save() {
        Song savedSong = songService.save(song);
        assertNotNull(savedSong);
        log.debug("savedSong: {}", savedSong);
        Song foundSong = songService.getByName(savedSong.getNameCn()).get(0);
        log.debug("foundSong: {}", foundSong);
        assertNotNull(foundSong);
    }

    @Test
    void delete() {
        songService.delete(12345);
        Song foundSong = songService.getById(12345).orElse(null);
        assertNull(foundSong);
    }

    @Test
    void getAll() {
        List<Song> songs = songService.getAll();
        log.debug("songs: {}", songs);
        assertTrue(songs.size() > 0);
    }

    @Test
    void getById() {
        Song song = songService.getById(12345).orElse(null);
        log.debug("song: {}", song);
        assertNotNull(song);
    }

    @Test
    void getByName() {
        List<Song> songs = songService.getByName("爱主更深");
        log.debug("songs size:{}  , {}", songs.size(), songs);
        assertTrue(songs.size() > 0);
    }

    @Test
    void findByLabels() {
        List<Song> songs = songService.findByLabels(List.of("真神", "赞美"));
        List<String> songNames = songs.stream().map(Song::getNameCn).collect(Collectors.toList());
        log.debug("song count: {}, names: {}", songs.size(), songNames);
        assertTrue(songs.size() > 0);
    }

    @Test
    void count() {
        assertTrue(songService.count() > 0);
    }

    @Test
    void getSummary() {
        SongSummary songSummary = songService.getSummary();
        log.debug("songSummary: {}", songSummary);
        assertTrue(songSummary.getTotalNumber() > 0);
    }
}