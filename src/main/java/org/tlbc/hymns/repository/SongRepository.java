package org.tlbc.hymns.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import org.tlbc.hymns.model.Song;

import java.util.List;

@Repository
public interface SongRepository extends ElasticsearchRepository<Song, Integer> {
    List<Song> findSongByNameCnLike(String songName);
}
