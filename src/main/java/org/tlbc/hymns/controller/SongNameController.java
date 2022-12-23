package org.tlbc.hymns.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.tlbc.hymns.model.ElasticSearchSong;
import org.tlbc.hymns.model.HymnGroup;
import org.tlbc.hymns.model.HymnEntity;
import org.tlbc.hymns.model.SongSummary;
import org.tlbc.hymns.service.ElasticSearchSongService;
import org.tlbc.hymns.service.HymnService;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController @Slf4j @CrossOrigin
@RequestMapping("/songs")
public class SongNameController {
    @Resource
    private ElasticSearchSongService elasticSearchSongService;
    @Resource
    private HymnService hymnService;

    @GetMapping(value = "/hymns")
    public List<HymnEntity> getHymns() {
        return hymnService.getHymns();
    }
    @GetMapping(value = "/hymns/groups")
    public List<HymnGroup> getGroups() {
        return hymnService.getHymnGroups();
    }
    @GetMapping(value = "/hymns/group1")
    public Set<String> getGroup1() {
        return hymnService.getGroup1();
    }
    @GetMapping(value = "/summary")
    public SongSummary getSummary() {
        return elasticSearchSongService.getSummary();
    }
    @GetMapping(value = "/group2songs")
    public List<ElasticSearchSong> searchSongs(@RequestParam("group2Name") String group2Name) {
        return elasticSearchSongService.findByGroup2(group2Name);
    }
    @GetMapping
    public List<ElasticSearchSong> searchSongs(@RequestParam("label") String[] labels) {
        return elasticSearchSongService.findByLabels(new HashSet<>(Arrays.asList(labels)));
    }
    @GetMapping(value = "/{id}")
    public ElasticSearchSong getSong(@PathVariable Integer id) {
        return new ElasticSearchSong(id, "分类1", "分类2", "中文", "Eng");
    }
}
