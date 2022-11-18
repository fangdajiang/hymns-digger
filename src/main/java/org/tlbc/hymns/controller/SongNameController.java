package org.tlbc.hymns.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.tlbc.hymns.model.Song;

import java.util.List;

@RestController
public class SongNameController {
    private RestTemplate restTemplate;

    @GetMapping(value = "songs")
    public List<Song> getSongs() {
        return null;
    }
    @GetMapping(value = "songs/{id}")
    public Song getSong(@PathVariable String id) {
        return new Song("中文", "http://" + id);
    }
}
