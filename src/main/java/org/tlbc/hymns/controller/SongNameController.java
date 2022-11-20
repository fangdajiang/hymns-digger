package org.tlbc.hymns.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.tlbc.hymns.model.Song;
import org.tlbc.hymns.model.SongSummary;
import org.tlbc.hymns.service.SongService;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RestController @Slf4j @CrossOrigin
@RequestMapping("/songs")
public class SongNameController {
    @Resource
    private SongService songService;

    @GetMapping(value = "/summary")
    public SongSummary getSummary() {
        return songService.getSummary();
    }
    @GetMapping
    public List<Song> searchSongs(@RequestParam("label") String[] labels) {
        return songService.findByLabels(Arrays.asList(labels));
    }
    @GetMapping(value = "/{id}")
    public Song getSong(@PathVariable Integer id) {
        return new Song(id, "中文");
    }
}
