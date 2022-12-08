package org.tlbc.hymns.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tlbc.hymns.model.ElasticSearchSong;
import org.tlbc.hymns.model.SongSummary;
import org.tlbc.hymns.repository.ElasticSearchSongRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service @Slf4j
public class ElasticSearchSongService {
    @Resource
    private ElasticSearchSongRepository elasticSearchSongRepository;

    public SongSummary getSummary() {
        List<ElasticSearchSong> allElasticSearchSongs = getAll();
        List<ElasticSearchSong> annotatedElasticSearchSongs = elasticSearchSongRepository.findSongsByLabeled(true);
        return new SongSummary(allElasticSearchSongs.size(), annotatedElasticSearchSongs.size());
    }
    public ElasticSearchSong save(ElasticSearchSong elasticSearchSong) {
        return elasticSearchSongRepository.save(elasticSearchSong);
    }
    public void delete(Integer id) {
        elasticSearchSongRepository.deleteById(id);
    }
    public List<ElasticSearchSong> getAll() {
        List<ElasticSearchSong> elasticSearchSongs = new ArrayList<>();
        for (ElasticSearchSong elasticSearchSong : elasticSearchSongRepository.findAll()) {
            elasticSearchSongs.add(elasticSearchSong);
        }
        return elasticSearchSongs;
    }
    public Optional<ElasticSearchSong> getById(Integer id) {
        return elasticSearchSongRepository.findById(id);
    }
    public List<ElasticSearchSong> getByName(String name) {
        return elasticSearchSongRepository.findSongsByNameCnLike(name);
    }
    public List<ElasticSearchSong> findByLabels(List<String> labelList) {
        if (labelList.size() > 0 && labelList.size() <= 6) {
            boolean labelIsValid = true;
            for (String label : labelList) {
                if (label.length() > 10) {
                    log.error("length of a SINGLE label should <= 10");
                    labelIsValid = false;
                    break;
                }
            }
            if (labelIsValid) {
                return new ArrayList<>(elasticSearchSongRepository.findSongsByLabels(labelList));
            } else {
                return List.of();
            }
        } else {
            log.error("size of labels should > 0 and <= 6");
            return List.of();
        }
    }
    public long count() {
        return elasticSearchSongRepository.count();
    }
}
