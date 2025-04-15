package com.subham.lld.musicplayeranalytics.service;


import com.subham.lld.musicplayeranalytics.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: the_odd_human
 * Date: 15/04/25
 */

@Service
public class SongService {
    private final SongRepository songRepository;

    public SongService() {
        songRepository = SongRepository.getInstance();
    }

    public void addSong(long songId, String title) {
        this.songRepository.addSong(songId, title);
    }
}
