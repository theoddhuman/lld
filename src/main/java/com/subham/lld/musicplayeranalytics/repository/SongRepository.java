package com.subham.lld.musicplayeranalytics.repository;


import com.subham.lld.musicplayeranalytics.model.Song;
import com.subham.lld.musicplayeranalytics.model.User;

import java.util.*;

/**
 * Author: the_odd_human
 * Date: 15/04/25
 */

public class SongRepository {
    private static volatile SongRepository INSTANCE;

    private Map<Long, Song> songs;

    private SongRepository() {
        songs = new HashMap<>();
    }

    public static SongRepository getInstance() {
        if(INSTANCE == null) {
            synchronized (SongRepository.class) {
                if(INSTANCE == null) {
                    INSTANCE = new SongRepository();
                }
            }
        }
        return INSTANCE;
    }

    public void addSong(long songId, String title) {
        if(songs.containsKey(songId)) {
            return;
        }
        songs.put(songId, new Song(songId, title));
    }

    public void playSong(long songId) {
        songs.get(songId).incrementPlayCount(1);
    }

    public Song getSong(long songId) {
        return songs.get(songId);
    }

    public List<Song> topKSongs(long k) {
        List<Song> songList = new ArrayList<>(songs.values());
        songList.sort(Comparator.comparing(Song::getPlayCount, Comparator.reverseOrder()));
        List<Song> result = new ArrayList<>();
        for(int i=0; i<k; i++) {
            result.add(songList.get(i));
        }
        return result;
    }
}
