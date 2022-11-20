package org.tlbc.hymns.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tlbc.hymns.model.Song;
import org.tlbc.hymns.model.SongSummary;
import org.tlbc.hymns.repository.SongRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service @Slf4j
public class SongService {
    @Resource
    private SongRepository songRepository;

    public SongSummary getSummary() {
        List<Song> allSongs = getAll();
        List<Song> annotatedSongs = songRepository.findSongsByLabeled(true);
        return new SongSummary(allSongs.size(), annotatedSongs.size());
    }
    public Song save(Song song) {
        return songRepository.save(song);
    }
    public void delete(Integer id) {
        songRepository.deleteById(id);
    }
    public List<Song> getAll() {
        List<Song> songs = new ArrayList<>();
        for (Song song : songRepository.findAll()) {
            songs.add(song);
        }
        return songs;
    }
    public Optional<Song> getById(Integer id) {
        return songRepository.findById(id);
    }
    public List<Song> getByName(String name) {
        List<Song> list = new ArrayList<>();
        Iterable<Song> iterable = songRepository.findSongsByNameCnLike(name);
        iterable.forEach(list::add);
        return list;
    }
    public List<Song> findByLabels(List<String> labels) {
        if (labels.size() > 0 && labels.size() <= 6) {
            boolean labelIsValid = true;
            for (String label : labels) {
                if (label.length() > 10) {
                    log.error("length of a SINGLE label should <= 10");
                    labelIsValid = false;
                    break;
                }
            }
            if (labelIsValid) {
                List<Song> list = new ArrayList<>();
                Iterable<Song> iterable = songRepository.findSongsByLabels(labels);
                iterable.forEach(list::add);
                return list;
            } else {
                return List.of();
            }
        } else {
            log.error("size of labels should > 0 and <= 6");
            return List.of();
        }
    }
    public long count() {
        return songRepository.count();
    }
}
