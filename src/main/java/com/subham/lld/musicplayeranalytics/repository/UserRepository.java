package com.subham.lld.musicplayeranalytics.repository;


import com.subham.lld.musicplayeranalytics.model.Song;
import com.subham.lld.musicplayeranalytics.model.User;

import java.util.*;

/**
 * Author: the_odd_human
 * Date: 15/04/25
 */
public class UserRepository {
    private static volatile UserRepository INSTANCE;

    private Map<Long, User> users;

    private UserRepository() {
        users = new HashMap<>();
    }
    public static UserRepository getInstance() {
        if(INSTANCE == null) {
            synchronized (SongRepository.class) {
                if(INSTANCE == null) {
                    INSTANCE = new UserRepository();
                }
            }
        }
        return INSTANCE;
    }

    public void playSong(long userId, long songId) {
        if(!users.containsKey(userId)) {
            users.put(userId, new User(userId));
        }
        users.get(userId).incrementPlayCount(songId, 1);
    }

    public void starSong(long userId, long songId) {
        User user = users.get(userId);
        user.starSong(songId);
    }

    public void unstarSong(long userId, long songId) {
        User user = users.get(userId);
        user.unstarSong(songId);
    }

    public List<Long> topKSongs(long userId, long k) {
        List<Map.Entry<Long, Long>> songList = new ArrayList<>(users.get(userId).getSongPlayCount().entrySet());
        songList.sort(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()));
        List<Long> result = new ArrayList<>();
        for(int i=0; i<k; i++) {
            result.add(songList.get(i).getKey());
        }
        return result;
    }
}
