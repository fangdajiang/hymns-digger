package org.tlbc.hymns.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.tlbc.hymns.model.Song;
import org.tlbc.hymns.service.SongService;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RestController @Slf4j @CrossOrigin
public class SongNameController {
    @Resource
    private SongService songService;

    @GetMapping(value = "/songs")
    public List<Song> searchSongs(@RequestParam("label") String[] labels) {
        return songService.findByLabels(Arrays.asList(labels));
    }
    @GetMapping(value = "/songs/{id}")
    public Song getSong(@PathVariable Integer id) {
        return new Song(id, "中文");
    }
}
