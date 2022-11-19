package org.tlbc.hymns.service;

import org.springframework.stereotype.Service;
import org.tlbc.hymns.model.Song;
import org.tlbc.hymns.repository.SongRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SongService {
    @Resource
    private SongRepository songRepository;

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
        List<Song> list = new ArrayList<>();
        Iterable<Song> iterable = songRepository.findSongsByLabels(labels);
        iterable.forEach(list::add);
        return list;
    }
    public long count() {
        return songRepository.count();
    }
}
