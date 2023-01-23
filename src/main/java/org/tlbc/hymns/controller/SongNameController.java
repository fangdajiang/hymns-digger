package org.tlbc.hymns.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.tlbc.hymns.model.*;
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
    public List<AnnotatedHymnGroup> getGroups() {
        return hymnService.getLoadedHymnGroups();
    }
    @GetMapping(value = "/hymns/group1")
    public Set<String> getGroup1() {
        return hymnService.getGroup1();
    }
    @GetMapping(value = "/summary")
    public SongSummary getSummary() {
        return elasticSearchSongService.getSummary();
    }
    @GetMapping(value = "/group2-songs")
    public List<ElasticSearchSong> searchSongsByGroup2Name(@RequestParam("group2Name") String group2Name) {
        return elasticSearchSongService.findByGroup2(group2Name);
    }
    @GetMapping
    public List<ElasticSearchSong> searchSongsByLabels(@RequestParam("label") String[] labels) {
        return elasticSearchSongService.findByLabels(new HashSet<>(Arrays.asList(labels)));
    }
    @GetMapping(value = "/cnSongs")
    public List<ElasticSearchSong> searchSongsByNameCn(@RequestParam("name") String nameCn) {
        return elasticSearchSongService.findByNameCn(nameCn);
    }
    @GetMapping(value = "/{id}")
    public ElasticSearchSong getSong(@PathVariable Integer id) {
        return new ElasticSearchSong(id, "分类1", "分类2", "中文", "Eng");
    }
}
