package org.tlbc.hymns.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import org.tlbc.hymns.model.ElasticSearchSong;

import java.util.List;
import java.util.Set;

@Repository
public interface ElasticSearchSongRepository extends ElasticsearchRepository<ElasticSearchSong, Integer> {
    List<ElasticSearchSong> findSongsByNameCnLike(String songName);

    List<ElasticSearchSong> findSongsByLabels(Set<String> labelList);

    List<ElasticSearchSong> findSongsByLabeled(Boolean labeled);
}