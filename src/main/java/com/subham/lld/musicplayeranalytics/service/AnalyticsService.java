package com.subham.lld.musicplayeranalytics.service;


import com.subham.lld.musicplayeranalytics.model.Song;
import com.subham.lld.musicplayeranalytics.repository.SongRepository;
import com.subham.lld.musicplayeranalytics.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: the_odd_human
 * Date: 15/04/25
 */

public class AnalyticsService {
    private final SongRepository songRepository;
    private final UserRepository userRepository;

    public AnalyticsService() {
        songRepository = SongRepository.getInstance();
        userRepository = UserRepository.getInstance();
    }

    public void playSong(long songId, long userId) {
        this.songRepository.playSong(songId);
        this.userRepository.playSong(userId, songId);
    }

    public List<Song> topKSongs(int k) {
        return this.songRepository.topKSongs(k);
    }

    public List<Song> topKSongsByUser(long userId, int k) {
        List<Long> songs = userRepository.topKSongs(userId, k);
        return songs.stream().map(songRepository::getSong).collect(Collectors.toList());
    }
}
