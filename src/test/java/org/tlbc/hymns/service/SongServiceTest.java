package org.tlbc.hymns.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.tlbc.hymns.model.Song;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j @SpringBootTest
class SongServiceTest {
    private Song song;

    @Resource
    private SongService songService;

    @BeforeEach
    void setUp() {
        song = new Song("测试");
        song.setId(1);
        song.setNotationUrl("http://song-url");
        List<String> labels = new ArrayList<>();
        labels.add("三一");
        labels.add("公义");
        labels.add("圣洁");
        song.setLabels(labels);
    }

    @Test
    void save() {
        Song savedSong = songService.save(song);
        assertNotNull(savedSong);
        log.debug("savedSong: {}", savedSong);
        Song foundSong = songService.getById(1).orElse(null);
        log.debug("foundSong: {}", foundSong);
        assertNotNull(foundSong);
    }

    @Test
    void delete() {
        songService.delete(song);
        Song foundSong = songService.getById(1).orElse(null);
        assertNull(foundSong);
    }

    @Test
    void getAll() {
        List<Song> songs = songService.getAll();
        assertTrue(songs.size() > 0);
    }

    @Test
    void getById() {
        Song song = songService.getById(1).orElse(null);
        assertNotNull(song);
    }

    @Test
    void getByName() {
        List<Song> songs = songService.getByName("测试");
        assertTrue(songs.size() > 0);
    }

    @Test
    void count() {
        assertTrue(songService.count() > 0);
    }
}