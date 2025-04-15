package com.subham.lld.musicplayeranalytics.model;


import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Author: the_odd_human
 * Date: 15/04/25
 */
@Getter
@Setter
public class User {
    private long userId;

    private Set<Long> starredSongs;

    private Map<Long, Long> songPlayCount;

    public User(long userId) {
        this.userId = userId;
        this.starredSongs = new HashSet<>();
        this.songPlayCount = new HashMap<>();
    }

    public void incrementPlayCount(long songId, long count) {
        this.songPlayCount.put(songId, this.songPlayCount.getOrDefault(songId, 0L) + count);
    }

    public void starSong(long songId) {
        starredSongs.add(songId);
    }

    public void unstarSong(long songId) {
        starredSongs.remove(songId);
    }
}
