package org.tlbc.hymns.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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

    private final static Integer MIN_LABELS_COUNT = 1;
    private final static Integer MAX_LABELS_COUNT = 6;
    private final static Integer MAX_LABEL_LENGTH = 20;

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
    public List<ElasticSearchSong> findByNameCn(String nameCn) {
        return elasticSearchSongRepository.findByNameCn(nameCn);
    }
    public List<ElasticSearchSong> findByGroup2(String group2Name) {
        if (StringUtils.hasText(group2Name)) {
            return new ArrayList<>(elasticSearchSongRepository.findByGroup2(group2Name));
        } else {
            log.error("group2Name should NOT be empty: {}", group2Name);
            return List.of();
        }
    }
    public List<ElasticSearchSong> findByLabels(Set<String> labelSet) {
        if (labelSet.size() >= MIN_LABELS_COUNT && labelSet.size() <= MAX_LABELS_COUNT) {
            boolean labelIsValid = true;
            for (String label : labelSet) {
                if (label.length() > MAX_LABEL_LENGTH) {
                    log.error("length of a SINGLE label should <= " + MAX_LABEL_LENGTH);
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
