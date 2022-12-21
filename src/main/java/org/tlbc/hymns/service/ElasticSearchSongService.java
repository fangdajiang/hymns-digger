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
import java.util.Set;

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
    public List<ElasticSearchSong> findByLabels(Set<String> labelSet) {
        if (labelSet.size() > 0 && labelSet.size() <= 6) {
            boolean labelIsValid = true;
            for (String label : labelSet) {
                if (label.length() > 20) {
                    log.error("length of a SINGLE label should <= 20");
                    labelIsValid = false;
                    break;
                }
            }
            if (labelIsValid) {
                return new ArrayList<>(elasticSearchSongRepository.findSongsByLabels(labelSet));
            } else {
                return List.of();
            }
        } else {
            log.error("size of labels should > 0 and <= 6, but is: {},{}", labelSet.size(), labelSet);
            return List.of();
        }
    }
    public long count() {
        return elasticSearchSongRepository.count();
    }
}
